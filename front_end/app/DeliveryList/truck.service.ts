import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable }     from  'rxjs/Rx';;
import { Truck } from './Truck';

@Injectable()
export class TruckService {
    private url: string;

    constructor(private _http: Http) {

    }
    
    getTrucks() {
        this.url = "http://localhost:8080/snel-transport/api/trucks";
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        options.body = '';

        return this._http.get(this.url, options)
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