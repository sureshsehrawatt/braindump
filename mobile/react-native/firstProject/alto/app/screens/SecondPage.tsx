// SecondPage.tsx
import { StyleSheet, Text, View } from 'react-native';
import React from 'react';
import { Link } from 'expo-router';
import withLifecycleEvents from '../withLifecycleEvents'; // Import HOC

const SecondPage: React.FC = () => {
  return (
    <View style={styles.container}>
    <Text>SecondPage!!</Text>
    <Link href={'/screens/ThirdPage'} style={styles.ls}>
      Go to Next
    </Link>
  </View>
  );
};

export default withLifecycleEvents(SecondPage, 'SecondPage');

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
