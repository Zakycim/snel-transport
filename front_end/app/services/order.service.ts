import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Order} from '../models/Order';
import {Status} from '../models/Status';
import { Observable }     from  'rxjs/Rx';

@Injectable()
export class OrderService {
    private url: string;

    constructor(private _http: Http) {

    }
    
    updateOrder(orderId, statusId) {
        this.url = "http://localhost:8080/snel-transport/api/orders";
      
        let data = { "id": orderId, "status": {"id": statusId }};
        let body = JSON.stringify(data);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this._http.put(this.url, body, options)
            .map(this.extractData)
            .catch(this.handleError);
    }
  
    getOrder(id: number) {
        this.url = "http://localhost:8080/snel-transport/api/orders/" + id.toString();
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        options.body = '';

        return this._http.get(this.url, options)
            .map(this.extractData)
            .catch(this.handleError);
    }
  
    getOrders() {
        this.url = "http://localhost:8080/snel-transport/api/orders";
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        options.body = '';

        return this._http.get(this.url, options)
            .map(this.extractData)
            .catch(this.handleError);
    }
  
    divideOrders() {
        this.url = "http://localhost:8080/snel-transport/api/orders/deliverylist";
        let data = { name : "AA" };
        let body = JSON.stringify(data);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this._http.post(this.url, body, options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body || {};
    } 
    
    private handleError(error: any) {
        //the Observable catches and throws an error
        return Observable.throw(error.message || error);
    }
}