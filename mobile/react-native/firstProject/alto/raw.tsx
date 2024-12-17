// // _layout.tsx
// import React, { useRef, useEffect } from 'react';
// import { Stack } from "expo-router";
// import NvCap from "cav-react-fetch-native_newversion";
// import { AppLifecycle } from "react-native-applifecycle";
// import globalEventEmitter from "./GlobalEventEmitter";
// import {
//   BatchSize,
//   DatadogProvider,
//   DatadogProviderConfiguration,
//   SdkVerbosity,
//   UploadFrequency,
// } from "@datadog/mobile-react-native";
// import { DdRumReactNavigationTracking } from "@datadog/mobile-react-navigation";
// import { NavigationContainer, NavigationContainerRef } from '@react-navigation/native';



// import { CustomNavigationTracking } from './CustomNavigationTracking'; // path to the file where the tracking is defined


// const Layout = () => {

//   const config = new DatadogProviderConfiguration(
//     "pub0db63c1d569b1bcc0ab642c012d0a51b",
//     "test",
//     "903435ea-8a49-41f0-9039-bb04a7b138e3",
//     true, // track User interactions (e.g.: Tap on buttons. You can use 'accessibilityLabel' element property to give tap action the name, otherwise element type will be reported)
//     true, // track XHR Resources
//     true // track Errors
//   );
//   // Optional: Select your Datadog website (one of "US1", "EU1", "US3", "US5", "AP1" or "GOV")
//   config.site = "US5";
//   // Optional: Enable JavaScript long task collection
//   config.longTaskThresholdMs = 100;
//   // Optional: enable or disable native crash reports
//   config.nativeCrashReportEnabled = true;
//   // Optional: sample RUM sessions (here, 100% of session will be sent to Datadog. Default = 100%)
//   config.sampleRate = 100;

//   if (__DEV__) {
//     // Optional: Send data more frequently
//     config.uploadFrequency = UploadFrequency.FREQUENT;
//     // Optional: Send smaller batches of data
//     config.batchSize = BatchSize.SMALL;
//     // Optional: Enable debug logging
//     config.verbosity = SdkVerbosity.DEBUG;
//   }

//   const navigationRef = React.useRef(null);
//   // const navigationRef = useRef<NavigationContainerRef>(null);

//   useEffect(() => {


//     if (navigationRef.current) {
//       CustomNavigationTracking.startTrackingViews(navigationRef.current);
//     }

//     return () => {
//       if (navigationRef.current) {
//         CustomNavigationTracking.stopTrackingViews(navigationRef.current);
//       }
//     };

//     // NvCap.start();

//     // App-wide lifecycle listener
//     // const listener = AppLifecycle.addEventListener("change", (state) => {
//     //   console.log(`App lifecycle state changed: ${state}`); // Debugging output
//     // });

//     // const handleComponentDidMount = (componentName: string) => {
//     //   console.log(`${componentName} mounted`);
//     // };

//     // const handleComponentWillUnmount = (componentName: string) => {
//     //   console.log(`${componentName} will unmount`);
//     // };

//     // // Register global lifecycle listeners
//     // globalEventEmitter.on("componentDidMount", handleComponentDidMount);
//     // globalEventEmitter.on("componentWillUnmount", handleComponentWillUnmount);

//     // // Clean up listeners on unmount
//     // return () => {
//     //   listener.remove();
//     //   globalEventEmitter.off("componentDidMount", handleComponentDidMount);
//     //   globalEventEmitter.off(
//     //     "componentWillUnmount",
//     //     handleComponentWillUnmount
//     //   );
//     // };
//   }, []);

//   return (
//     // <DatadogProvider configuration={config}>
//     // <NavigationContainer
//     //   ref={navigationRef}
//     //   independent={true}
//     //   onReady={() => {
//     //     DdRumReactNavigationTracking.startTrackingViews(
//     //       navigationRef.current
//     //     );
//     //   }}
//     // >

//     <NavigationContainer 
//     independent={true}
//     ref={navigationRef}>
//       <Stack>
//         <Stack.Screen name="index" options={{ headerShown: false }} />
//       </Stack>

//     </NavigationContainer>
//   // </DatadogProvider>
//   );
// };

// export default Layout;






// index.tsx
// import React from 'react';
// import { StyleSheet, Text, View } from 'react-native';
// import { Link } from 'expo-router';
// import withLifecycleEvents from './withLifecycleEvents';

// const RootLayout: React.FC = () => {
//   return (
//     <View style={styles.container}>
//       <Text>Hello World!</Text>
//       <Link href={'./screens/SecondPage'} style={styles.ls}>
//         Go to Next
//       </Link>
//     </View>
//   );
// };

// export default withLifecycleEvents(RootLayout, 'RootLayout');

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     alignItems: 'center',
//     justifyContent: 'center',
//   },
//   ls: {
//     color: 'blue',
//   },
// });
