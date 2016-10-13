// Imports
// Deprecated import
// import { RouterConfig } from '@angular/router';
import { Routes } from '@angular/router';

import { ContactsComponent }    from './contacts.component';

// Route Configuration
export const contactsRoutes: Routes = [
  { path: 'contacts', component: ContactsComponent }
];