import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';

import { AppComponent }         from './app.component';
import { OrderGetComponent }  from './order/order-get.component';
import { OrderCreateComponent }  from './order/order-create.component';
import { ContactsComponent }  from './contacts/contacts.component';
import { OrderStatusComponent } from './orderstatus/orderstatus.component';
// import { FiltersComponent }  from './filters/filters.component';
import { routing } from './app.routes';
import { DeliveryListComponent } from './deliverylist/delivery-list.component';
import { FilterPipe } from './filter.pipe';
import { ExampleComponent } from './example/example.component';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    routing
  ],
  declarations: [
    AppComponent,
    OrderGetComponent,
    OrderCreateComponent,
    ContactsComponent,
    OrderStatusComponent,
    DeliveryListComponent,
    FilterPipe,
    ExampleComponent
    // FiltersComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

