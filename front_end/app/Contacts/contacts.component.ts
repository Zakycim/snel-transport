// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Contact} from './contact';

@Component({
    moduleId: module.id,
    selector: 'contacts',
    templateUrl: './contacts.component.html'
})
export class ContactsComponent {

    contacts: Array<Contact>;
    constructor(){
        this.contacts = [];
    }

    addContact(name,phone){
        let contact = new Contact(name,phone);
        this.contacts.push(contact);
    }

    removeContact(contact){
        let index = this.contacts.indexOf(contact);
        this.contacts.splice(index,1);
    }
}