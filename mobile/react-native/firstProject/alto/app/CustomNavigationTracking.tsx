// CustomNavigationTracking.ts
import {
    AppState,
    BackHandler,
    AppStateStatus,
    NativeEventSubscription,
  } from 'react-native';
  import {
    NavigationContainerRef,
    Route,
  } from '@react-navigation/native';
  
  type AppStateListener = (appStateStatus: AppStateStatus) => void;
  
  export type ViewNamePredicate = (
    route: Route<string, any | undefined>,
    trackedName: string
  ) => string | null;
  
  export class CustomNavigationTracking {
    private static registeredContainer: NavigationContainerRef<any> | null = null;
    private static navigationStateChangeListener: (() => void) | null = null;
    private static viewNamePredicate: ViewNamePredicate = (_route, name) => name;
    private static backHandler: NativeEventSubscription | null = null;
    private static appStateSubscription: NativeEventSubscription | null = null;
    private static trackingState: 'TRACKING' | 'NOT_TRACKING' = 'NOT_TRACKING';
  
    static ROUTE_UNDEFINED_NAVIGATION_WARNING_MESSAGE =
      'Navigation change detected but the route was undefined.';
    static NULL_NAVIGATION_REF_ERROR_MESSAGE =
      'Cannot track views with a null navigationRef.';
    static NAVIGATION_REF_IN_USE_ERROR_MESSAGE =
      'Cannot track new navigation container while another one is still tracked. Please stop tracking the previous container reference.';
  
    static isAppExitingOnBackPress = (): boolean => {
      return !CustomNavigationTracking.registeredContainer?.canGoBack();
    };
  
    static onBackPress = () => {
      if (CustomNavigationTracking.isAppExitingOnBackPress()) {
        CustomNavigationTracking.stopTrackingViews(
          CustomNavigationTracking.registeredContainer
        );
      }
      return false;
    };
  
    static startTrackingViews(
      navigationRef: NavigationContainerRef<any> | null,
      viewNamePredicate: ViewNamePredicate = (_route, name) => name
    ): void {
      if (navigationRef === null) {
        console.error(CustomNavigationTracking.NULL_NAVIGATION_REF_ERROR_MESSAGE);
        return;
      }
  
      if (
        CustomNavigationTracking.registeredContainer != null &&
        CustomNavigationTracking.registeredContainer !== navigationRef
      ) {
        console.error(
          CustomNavigationTracking.NAVIGATION_REF_IN_USE_ERROR_MESSAGE
        );
        return;
      }
  
      CustomNavigationTracking.viewNamePredicate = viewNamePredicate;
      const listener =
        CustomNavigationTracking.resolveNavigationStateChangeListener();
      CustomNavigationTracking.handleRouteNavigation(
        navigationRef.getCurrentRoute(),
        AppState.currentState
      );
      navigationRef.addListener('state', listener);
      CustomNavigationTracking.registeredContainer = navigationRef;
      CustomNavigationTracking.backHandler = BackHandler.addEventListener(
        'hardwareBackPress',
        CustomNavigationTracking.onBackPress
      );
      CustomNavigationTracking.appStateSubscription = AppState.addEventListener(
        'change',
        CustomNavigationTracking.appStateListener
      );
    }
  
    static stopTrackingViews(
      navigationRef: NavigationContainerRef<any> | null
    ): void {
      if (navigationRef) {
        navigationRef.removeListener(
          'state',
          CustomNavigationTracking.navigationStateChangeListener!
        );
        CustomNavigationTracking.backHandler?.remove();
        CustomNavigationTracking.backHandler = null;
        CustomNavigationTracking.registeredContainer = null;
        CustomNavigationTracking.viewNamePredicate = (_route, name) => name;
      }
  
      CustomNavigationTracking.appStateSubscription?.remove();
      CustomNavigationTracking.appStateSubscription = null;
    }
  
    private static handleRouteNavigation(
      route: Route<string, any | undefined> | undefined,
      appStateStatus: AppStateStatus
    ) {
      if (!route) {
        console.warn(
          CustomNavigationTracking.ROUTE_UNDEFINED_NAVIGATION_WARNING_MESSAGE
        );
        return;
      }
      const key = route.key;
      const screenName = CustomNavigationTracking.viewNamePredicate(
        route,
        route.name
      );
  
      if (key && screenName) {
        if (appStateStatus !== 'background') {
          CustomNavigationTracking.trackingState = 'TRACKING';
          console.log(`Start tracking view: ${screenName} (Key: ${key})`);
        }
      }
    }
  
    private static handleAppStateChanged(
      route: Route<string, any | undefined>,
      appStateStatus: AppStateStatus
    ) {
      const key = route.key;
      const screenName = CustomNavigationTracking.viewNamePredicate(
        route,
        route.name
      );
  
      if (key && screenName) {
        if (appStateStatus === 'background') {
          CustomNavigationTracking.trackingState = 'NOT_TRACKING';
          console.log(`Stop tracking view: ${screenName} (Key: ${key})`);
        } else if (
          appStateStatus === 'active' &&
          CustomNavigationTracking.trackingState === 'NOT_TRACKING'
        ) {
          CustomNavigationTracking.trackingState = 'TRACKING';
          console.log(`Resume tracking view: ${screenName} (Key: ${key})`);
        }
      }
    }
  
    private static resolveNavigationStateChangeListener(): () => void {
      if (CustomNavigationTracking.navigationStateChangeListener === null) {
        CustomNavigationTracking.navigationStateChangeListener = () => {
          const route =
            CustomNavigationTracking.registeredContainer?.getCurrentRoute();
  
          if (!route) {
            console.warn(
              CustomNavigationTracking.ROUTE_UNDEFINED_NAVIGATION_WARNING_MESSAGE
            );
            return;
          }
  
          CustomNavigationTracking.handleRouteNavigation(
            route,
            AppState.currentState
          );
        };
      }
      return CustomNavigationTracking.navigationStateChangeListener;
    }
  
    private static appStateListener: AppStateListener = (
      appStateStatus: AppStateStatus
    ) => {
      const currentRoute =
        CustomNavigationTracking.registeredContainer?.getCurrentRoute();
      if (!currentRoute) {
        console.error(
          `Could not determine the route on app state change to: ${appStateStatus}.`
        );
        return;
      }
  
      CustomNavigationTracking.handleAppStateChanged(
        currentRoute,
        appStateStatus
      );
    };
  }
  