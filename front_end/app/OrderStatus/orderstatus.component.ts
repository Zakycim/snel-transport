// Imports
import { Component, OnInit, } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, RequestOptions, RequestOptionsArgs, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Order} from '../models/Order';
import {Status} from '../models/Status';
import { OrderService } from '../services/order.service';
import { StatusService } from '../services/status.service';

@Component({
  moduleId: module.id,
  selector: 'orderstatus',
  templateUrl: `orderstatus.component.html`,
  providers: [OrderService, StatusService]
})

//Leveringsstatus bijwerken

// Component class implementing OnInit
export class OrderStatusComponent implements OnInit {
  orders: Order;
  statuses: Status;
  trucks: Truck;
  customers = [];
  deliverylist = [];

  private headers = new Headers({ 'Content-Type': 'application/json' });

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  constructor(private http: Http, private _orderService: OrderService, private _statusService: StatusService) {

  }

  ngOnInit() {
    this._orderService.getTrucks()
      .subscribe(
      res => {
        this.trucks = res;
      },
      error => {
        console.log("app get order error");
        console.log(error);
      }
      );
    
    this._statusService.getStatuses()
      .subscribe(
      res => {
        this.statuses = res;
      },
      error => {
        console.log("app get status error");
        console.log(error);
      }
      );
  }

  statusChange(orderId, statusId) {
    console.log("order id " + orderId + "statud id" + statusId);
    this._orderService.updateOrder(orderId, statusId)
      .subscribe(
      res => {
        this.statuses = res;
      },
      error => {
        console.log("app put order error");
        console.log(error);
      }
      location.reload();
      );
  }

}
