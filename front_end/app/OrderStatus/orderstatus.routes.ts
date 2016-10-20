// Imports
// Deprecated import
// import { RouterConfig } from '@angular/router';
import { Routes } from '@angular/router';
import {OrderStatusComponent }    from './orderstatus.component';

// Route Configuration
export const orderStatusRoutes: Routes = [
  { path: 'orderstatus', component: OrderStatusComponent }
];