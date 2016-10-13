 import { Component } from '@angular/core';

// Import router directives
// Deprecated
// import { ROUTER_DIRECTIVES } from '@angular/router';

import {OrderGetComponent} from "./order/order-get.component";

@Component({
  selector: 'my-app',
  styleUrls: ['../styles.css'],
  template: `
      <div class="demo-layout-transparent mdl-layout mdl-js-layout">
      <header class="mdl-layout__header mdl-layout__header--transparent">
        <div class="mdl-layout__header-row">
          <!-- Add spacer, to align navigation to the right -->
          <div class="mdl-layout-spacer"></div>
          <!-- Navigation with router directives-->
          <nav class="mdl-navigation">
            <a class="mdl-navigation__link" [routerLink]="['/']">Home</a>
            <a class="mdl-navigation__link" [routerLink]="['/order']">Bestelling</a>
            <a class="mdl-navigation__link" [routerLink]="['/orderlist']">Leveringsstatus</a>
          </nav>
        </div>
      </header>
    </div>
    <!-- Router Outlet -->
    <div class="container">
      <router-outlet></router-outlet>
    </div>
  `,
  // Deprecated
  // Tell component to use router directives
  // directives: [ROUTER_DIRECTIVES]
})

// App Component class
export class AppComponent{}
