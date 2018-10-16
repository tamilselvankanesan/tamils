1. app-component -> include Menu, Include J Menu, Include BreadCrumb and router-outlet for packets
    2. Menu component includes CM Menu and J Menu
        1. CM Menu is returned by jrp.services
        2. J Menu is also returned by jrp.services as a BehaviorSubject. MenuComponent subscribe to this BehaviorSubject. Any changes to J Menu will be automatically notified
2. Default route is Navigation component.
    1. It loads the BreadCrumb, and retrieves packets (TODO) and redirect to JRP-home component
        1. retrieved packets will be added to BehaviorSubject which will automatically notify the pages
    2. packets are BehaviorSubjects and automatically notified when there is a change.
3. The navigation is handled by navigation component. will update BreadCrumb using BreadCrumb service. and based on the menu selection, packets will be retrieved or other actions will be carried out. retrieved packets will be added to BehaviorSubject using jrp.services
    3.1 For navigation to work, make sure the routing is configured and handled in updateBreadCrumb method also the actual navigation is handled in the constructor.
    3.2 When navigation happens thru NavigationComponent, the updateBreadCrumb() function which is listening to routechange will be invoked. BreadCrumb can be updated using this functions. Then the constructor will be called, which can be used to perform the navigation.
4. jrp.services is for all general service calls whereas packets.service is for packet search and packet specific tasks
5. BreadCrumb.service is only for notification purpose
