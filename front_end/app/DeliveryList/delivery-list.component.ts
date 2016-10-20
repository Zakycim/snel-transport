// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Order} from './Order';
import { ActivatedRoute, Params }   from '@angular/router';
import { OrderService } from './order.service';

@Component({
  moduleId: module.id,
  selector: 'DeliveryList',
  templateUrl: `delivery-list.component.html`,
  providers: [OrderService]
})

// Component class implementing OnInit
export class DeliveryListComponent implements OnInit {
  order: Order;

  orderId: number;
  orderURL: string;

  constructor(private http: Http, private route: ActivatedRoute, private _orderService: OrderService) {
  }

  printDeliveryList(divName) {
    var printContents = document.getElementById(divName).innerHTML;

    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;

    window.clearInterval(2);

  }



  ngOnInit() {
    this.route.params.subscribe(
      (param: any) => {
        let id = param['id'];
        //        console.log(id);
        this.orderId = id;
      });



    this._orderService.getOrder(this.orderId)
      .subscribe(
      res => {
        console.log("app res ");
        console.log(res);
        this.order = res;
        console.log("order res ");
        console.log(this.order);
      },
      error => {
        console.log("app trucks error");
        console.log(error);
      }
      );
      console.log(" outside res order ");
      console.log(this.order);
  }
}
