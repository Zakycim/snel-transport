// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { ActivatedRoute, Params, Router }   from '@angular/router';
import { OrderService } from './order.service';

@Component({
  moduleId: module.id,
  selector: 'DeliveryList',
  templateUrl: `delivery-list.component.html`,
  providers: [OrderService]
})

// Component class implementing OnInit
export class ShortestRouteComponent implements OnInit {
  routelist = [];

  truckId: number;
  orderURL: string;

  constructor(private http: Http, private route: ActivatedRoute, private _orderService: OrderService,
    private router: Router) {
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
        this.truckId = id;
      });


    this._orderService.getTruck(this.truckId)
      .subscribe(
      res => {
        this.routelist = res;
      },
      error => {
        console.log("app order error");
        console.log(error);
        this.router.navigateByUrl('/');
      }
      );
    console.log(" outside res order ");
    console.log(this.routelist);
  }
}
