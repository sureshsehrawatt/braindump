import React, { useEffect, useRef } from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer, NavigationContainerRef } from '@react-navigation/native';
import { CustomNavigationTracking } from './CustomNavigationTracking';
import YourComponent from './index'; 

const Stack = createStackNavigator();

const Layout = () => {
  const navigationRef = useRef<NavigationContainerRef<any>>(null);

  useEffect(() => {
    if (navigationRef.current) {
      CustomNavigationTracking.startTrackingViews(navigationRef.current);
    }

    return () => {
      if (navigationRef.current) {
        CustomNavigationTracking.stopTrackingViews(navigationRef.current);
      }
    };
  }, []);

  return (
    <NavigationContainer independent={true} ref={navigationRef}>
      <Stack.Navigator>
        <Stack.Screen
          name="index"
          component={YourComponent}
          options={{ headerShown: false }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default Layout;
