// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Component({
  moduleId: module.id,
  selector: 'OrderList',
  templateUrl : `order-list.component.html`
})

// Component class implementing OnInit
export class OrderListComponent{
   
  // customers = [];
  // trucks = [];
   orderlist = [];
  orderlistUrl = ("http://localhost:8080/snelTransport/resources/orderlist");

  //private headers = new Headers({ 'Content-Type': 'application/json' });
private todos = ['Angular Notification', 'Filter', 'Request API'];

  constructor(private http: Http) {
    this.todos
  }
 ngOnInit() {

    this.http.get(this.orderlistUrl).
      toPromise().then(r => r.json()).then(r => this.orderlist = r);
  }
 

} 