// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Component({
  moduleId: module.id,
  selector: 'OrderList',
  templateUrl: `order-list.component.html`
})

// Component class implementing OnInit
export class OrderListComponent {

  // customers = [];
  // trucks = [];
  trucklist = [];
  assignlist = [];
  confirmlist= [];
  trucklistUrl = ("http://localhost:8080/snel-transport/api/trucks");
  assignlistUrl = ("http://localhost:8080/snel-transport/api/orderlist");
  confirmURL = ("http://localhost:8080/snel-transport/api/orderlist/create");

  //private headers = new Headers({ 'Content-Type': 'application/json' });
  private todos = ['Angular Notification', 'Filter', 'Request API'];

  constructor(private http: Http) {
    this.todos
  }

  AssignOrders() {
    console.log("Trucklist: "+this.trucklist);
    this.trucklist = this.trucklist;

    this.http.get(this.assignlistUrl).
      toPromise().then(r => r.json()).then(r => this.assignlist = r);

      // TODO Laad te snel daarom even een timer
    this.http.get(this.trucklistUrl).
      toPromise().then(r => r.json()).then(r => this.trucklist = r);
  }
  ConfirmOrderLits(){
    console.log("Trucklist: "+this.trucklist);
    this.trucklist = this.trucklist;
    
    this.http.get(this.confirmUrl).
      toPromise().then(r => r.json()).then(r => this.confirmlist = r);

  }
  ngOnInit() {

    // this.http.get(this.trucklistUrl).
    //   toPromise().then(r => r.json()).then(r => this.trucklist = r);
  }


} 