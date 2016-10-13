// Imports
// Deprecated import
// import { RouterConfig } from '@angular/router';
import { Routes } from '@angular/router';

import { OrderGetComponent }    from './order-get.component';
import { OrderCreateComponent }    from './order-create.component';

// Route Configuration
export const orderRoutes: Routes = [
  { path: 'order', component: OrderGetComponent },
  { path: 'order/create', component: OrderCreateComponent }
];