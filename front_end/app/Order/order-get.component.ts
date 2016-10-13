// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Component({
  moduleId: module.id,
  selector: 'order',
  templateUrl : `order.component.html`
})

// Component class implementing OnInit
export class OrderGetComponent implements OnInit {
  
  customers = [];
  products = [];
  getTotal = '500';

  constructor(private http: Http) {

  }

  // // without strong typing
  // onKey(event:any) {
  //   newTotal = getTotal(this.values);
  // }

  // getTotal(quantity){
  //     var total = 0;
  //     total = products.price * quantity;
  //     }
  //     return total;
  // }

  ngOnInit() {
    this.http.get("http://localhost:8080/snelTransport/resources/customers").
    toPromise().then(r => r.json()).then(r => this.customers = r);

    this.http.get("http://localhost:8080/snelTransport/resources/products").
    toPromise().then(r => r.json()).then(r => this.products = r);
  }
}
