// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Component({
  moduleId: module.id,
  selector: 'DeliveryList',
  templateUrl : `delivery-list.component.html`
})

// Component class implementing OnInit
export class DeliveryListComponent{
  
  private todos = ['Angular Notification', 'Filter', 'Request API'];

  constructor(private http: Http) {
    this.todos
  }

}
