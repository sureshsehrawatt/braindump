// withLifecycleEvents.tsx
import React, { useEffect, ComponentType } from 'react';
import globalEventEmitter from './GlobalEventEmitter';

const withLifecycleEvents = <P extends object>(
  WrappedComponent: ComponentType<P>,
  componentName: string
): ComponentType<P> => {
  const LifecycleComponent: React.FC<P> = (props: P) => {
    useEffect(() => {
      console.log(`Mounting ${componentName}`);  // Debugging output
      globalEventEmitter.emit('componentDidMount', componentName);

      return () => {
        console.log(`Unmounting ${componentName}`);  // Debugging output
        globalEventEmitter.emit('componentWillUnmount', componentName);
      };
    }, []);

    return <WrappedComponent {...props} />;
  };

  return LifecycleComponent;
};

export default withLifecycleEvents;
