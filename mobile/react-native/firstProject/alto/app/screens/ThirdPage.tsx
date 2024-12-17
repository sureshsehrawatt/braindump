// SecondPage.tsx
import { StyleSheet, Text, View } from 'react-native';
import React from 'react';
import withLifecycleEvents from '../withLifecycleEvents'; // Import HOC

const ThirdPage: React.FC = () => {
  return (
    <View style={styles.container}>
      <Text>ThirdPage!!!</Text>
    </View>
  );
};

// Wrap SecondPage with the HOC
export default withLifecycleEvents(ThirdPage, 'ThirdPage');

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
