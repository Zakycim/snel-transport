// Imports
// Deprecated import
// import { RouterConfig } from '@angular/router';
import { Routes } from '@angular/router';
import {OrderListComponent }    from './order-list.component';

// Route Configuration
export const orderListRoutes: Routes = [
  { path: 'orderlist', component: OrderListComponent }
];