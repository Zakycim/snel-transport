// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { OrderService } from '../services/order.service';

@Component({
  moduleId: module.id,
  selector: 'OrderList',
  templateUrl: `order-list.component.html`,
  providers: [OrderService]
})

// Component class implementing OnInit
export class OrderListComponent {

  // customers = [];
  // trucks = [];
  trucklist = [];
  trucklistUrl = ("http://localhost:8080/snel-transport/api/trucks");

  //private headers = new Headers({ 'Content-Type': 'application/json' });
  private todos = ['Angular Notification', 'Filter', 'Request API'];

  constructor(private http: Http, private _orderService: OrderService) {
    this.todos
  }

  ngOnInit() {
    this.http.get(this.trucklistUrl).
      toPromise().then(r => r.json()).then(r => this.trucklist = r);
  }

  verdeelOrders() {
    this._orderService.divideOrders()
      .subscribe(
      res => {
        location.reload();
        console.log("res");
        //De response geeft een 200 statuscode maar ik heb geen idee waarom res.status undefined is..
        if (res.status == 200) {
//          alert("Orders zijn succesvol verdeeld"); 
        }
        
        alert("Orders zijn succesvol verdeeld");
        location.reload();
      },
      error => {
        // ugly bugsolve
        location.reload();
        console.log("app divide orders error");
      }
      );
  }
} 
