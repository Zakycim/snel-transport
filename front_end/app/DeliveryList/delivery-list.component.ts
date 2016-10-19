// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Truck} from './Truck';
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
  truck: Truck;

  constructor(private http: Http) {
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
//    this._truckService.getTrucks()
//      .subscribe(
//      res => {
//        console.log("app trucks ");
//        //log the response which shows a session
//        console.log("truck yeye");
//        //                  this.truck = res;
//      },
//      error => {
//        console.log("app trucks error");
//        console.log(error);
//      }
//      );
  }
}
