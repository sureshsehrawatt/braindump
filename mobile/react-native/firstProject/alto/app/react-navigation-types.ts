// react-navigation-types.ts

export type Route<
  RouteName extends string,
  Params extends object | undefined = object | undefined
> = Readonly<{
  key: string;
  name: RouteName;
}> &
  (undefined extends Params
    ? Readonly<{ params?: Readonly<Params> }>
    : Readonly<{ params: Readonly<Params> }>);

export type NavigationContainerRef<T = any> = {
  canGoBack(): boolean;
  getCurrentRoute(): Route<string> | undefined;
  addListener<EventName extends string>(
    type: EventName,
    callback: (...args: any[]) => void
  ): () => void;
  removeListener<EventName extends string>(
    type: EventName,
    callback: (...args: any[]) => void
  ): void;
};

export type NavigationListener = (event: { type: string }) => void | null;
