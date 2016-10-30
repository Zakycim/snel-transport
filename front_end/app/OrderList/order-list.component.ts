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
  trucklist = [];
  trucklistUrl = ("http://localhost:8080/snel-transport/api/trucks");

  //private headers = new Headers({ 'Content-Type': 'application/json' });
private todos = ['Angular Notification', 'Filter', 'Request API'];

  constructor(private http: Http) {
    this.todos
  }
  
 ngOnInit() {
    this.http.get(this.trucklistUrl).
      toPromise().then(r => r.json()).then(r => this.trucklist = r);
  }
} 
