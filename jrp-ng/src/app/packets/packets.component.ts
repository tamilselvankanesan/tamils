import {BreadCrumbService} from '../bread-crumb/bread-crumb.service';
import {Time} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, PRIMARY_OUTLET, NavigationEnd} from '@angular/router';
import {MenuItem} from 'primeng/api';
import 'rxjs/add/operator/filter';

@Component({
  selector: 'app-packets',
  templateUrl: './packets.component.html',
  styleUrls: ['./packets.component.css']
})
export class PacketsComponent implements OnInit {

  text = 'hello';
  breadcrumbs: MenuItem[] = [];

  constructor(private activatedRoute: ActivatedRoute, private breadcrumbService: BreadCrumbService, private router: Router) {
    this.activatedRoute.url.subscribe(params => {
      console.log('hello');
      //      debugger;
      this.text = new Date().toLocaleString();
      let tree: ActivatedRoute[] = this.activatedRoute.pathFromRoot;
      tree.forEach(item => {
        console.log(item.outlet);
      });
      console.log('url is ' + activatedRoute.snapshot['_routerState'].url);
      this.breadcrumbs = [{label: 'Home'}];
      this.breadcrumbs.push({label: this.text});
      this.breadcrumbService.setBreadcrumb(this.breadcrumbs);
    });

    // subscribe to the NavigationEnd event
    //    this.router.events.filter(event => event instanceof NavigationEnd).subscribe(event => {
    //      this.text = new Date().toLocaleString();
    //
    //      let root: ActivatedRoute = this.activatedRoute.root;
    //      console.log('URL is : ' + activatedRoute.url.toArray.toString);
    //      console.log(activatedRoute.snapshot['_routerState'].url);
    //
    //            this.breadcrumbs = this.getBreadcrumbs(root);
    //            this.breadcrumbService.setBreadcrumb(this.breadcrumbs);
    //    });

    //private route: AatedRoute, 
    //private router: Router

    //    this.router.events.subscribe(eventData => {
    //      let event: any = eventData;
    //      if (event.state) {
    //        this.text = new Date().toLocaleString();
    //        let currentUrlPart = event.state._root;
    //        while (currentUrlPart.children.length > 0) {
    //          currentUrlPart = currentUrlPart.children[0];
    //          console.log(currentUrlPart);
    //        }
    //      }
    //
    //    });

    //    this.route.params.forEach(
    //      params => {
    //        console.log('hello');
    //        this.text = new Date().toLocaleString();
    //      }
    //    );
  }

  ngOnInit() {


  }

  onselect() {
    console.log('clikced');
  }
  changeTheme(item: string) {
    console.log('clikced');
  }

  /**
   * Returns array of IBreadcrumb objects that represent the breadcrumb
   *
   * @class DetailComponent
   * @method getBreadcrumbs
   * @param {ActivateRoute} route
   * @param {string} url
   * @param {IBreadcrumb[]} breadcrumbs
   */
  private getBreadcrumbs(route: ActivatedRoute, url: string = '', breadcrumbs: MenuItem[] = []): MenuItem[] {
    const ROUTE_DATA_BREADCRUMB = 'breadcrumb';

    // get the child routes
    let children: ActivatedRoute[] = route.children;

    // return if there are no more children
    if (children.length === 0) {
      console.log('zero');
      return breadcrumbs;
    }

    // iterate over each children
    for (let child of children) {
      // verify primary route
      if (child.outlet !== PRIMARY_OUTLET) {
        continue;
      }

      // verify the custom data property "breadcrumb" is specified on the route
      if (!child.snapshot.data.hasOwnProperty(ROUTE_DATA_BREADCRUMB)) {
        return this.getBreadcrumbs(child, url, breadcrumbs);
      }

      // get the route's URL segment
      let routeURL: string = child.snapshot.url.map(segment => segment.path).join("/");

      // append route URL to URL
      url += `/${routeURL}`;

      // add breadcrumb
      let breadcrumb: MenuItem = {
        label: child.snapshot.data[ROUTE_DATA_BREADCRUMB],
        //        params: child.snapshot.params,
        url: url
      };
      breadcrumbs.push(breadcrumb);

      //recursive
      return this.getBreadcrumbs(child, url, breadcrumbs);
    }
  }

}
