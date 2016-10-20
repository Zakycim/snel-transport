// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Order} from './Order';
import { ActivatedRoute, Params }   from '@angular/router';
import { TruckService } from './truck.service';

@Component({
  moduleId: module.id,
  selector: 'DeliveryList',
  templateUrl: `delivery-list.component.html`,
  providers: [TruckService]
})

// Component class implementing OnInit
export class DeliveryListComponent implements OnInit {
  _truckService: TruckService;
  order: Order;
  
   orderId: number;
   orderURL: string;

  constructor(private http: Http, private route: ActivatedRoute) {
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
    
    this.orderURL = "http://localhost:8080/snel-transport/api/orders/" + this.orderId.toString();
    
    console.log(this.orderId);
    
    this.http.get(this.orderURL).
      toPromise().then(r => r.json()).then(r => this.order = r);
    console.log("qwe res");
    console.log(this.order);
    
//    TO DO:
    this._truckService.getTrucks()
      .subscribe(
      res => {
        console.log("app trucks ");
        //log the response which shows a session
        console.log("truck yeye");
        //                  this.truck = res;
      },
      error => {
        console.log("app trucks error");
        console.log(error);
      }
      );
  }
}
