// Imports
import { Component, OnInit, } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, RequestOptions, RequestOptionsArgs, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Component({
  moduleId: module.id,
  selector: 'orderlist',
  templateUrl: `order-list.component.html`
})

//Leveringsstatus bijwerken

// Component class implementing OnInit
export class OrderListComponent implements OnInit {
  customers = [];
  deliverylist = [];
  statuses = [];
  heroesUrl = 'http://localhost:8080/snelTransport/resources/deliverylist';

  private headers = new Headers({ 'Content-Type': 'application/json' });

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  constructor(private http: Http) {

  }

  ngOnInit() {

    this.http.get("http://localhost:8080/snelTransport/resources/status").
      toPromise().then(r => r.json()).then(r => this.statuses = r);

    this.http.get("http://localhost:8080/snelTransport/resources/deliverylist").
      toPromise().then(r => r.json()).then(r => this.deliverylist = r);
  }

  statusChange(orderId, statusId) {

    this.http.post(this.heroesUrl, JSON.stringify({ orderId: orderId, statusId: statusId }), { headers: this.headers })
      .toPromise().then(res => res.json().data).catch(this.handleError);
  }

}
