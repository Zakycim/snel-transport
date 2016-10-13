// Imports
// Deprecated import
// import { RouterConfig } from '@angular/router';
import { Routes } from '@angular/router';

import { DeliveryListComponent }    from './delivery-list.component';

// Route Configuration
export const deliveryRoutes: Routes = [
  { path: 'deliverylist', component: DeliveryListComponent }
];