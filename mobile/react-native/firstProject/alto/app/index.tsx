// index.tsx
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Link } from 'expo-router';
import withLifecycleEvents from './withLifecycleEvents';

const RootLayout: React.FC = () => {
  return (
    <View style={styles.container}>
      <Text>Hello World!</Text>
      <Link href={'./screens/SecondPage'} style={styles.ls}>
        Go to Next
      </Link>
    </View>
  );
};

export default withLifecycleEvents(RootLayout, 'RootLayout');

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  ls: {
    color: 'blue',
  },
});
