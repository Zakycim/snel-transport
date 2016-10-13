// Imports
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {orderLineCreate} from './orderLineCreate';

@Component({
  moduleId: module.id,
  selector: 'order',
  // templateUrl : `order-create.component.html`
  templateUrl: `./order-create.component.html`
})

// Component class implementing OnInit
export class OrderCreateComponent implements OnInit {

  customers = [];
  products = [];
  selectedProducts = [];

  // customer variables
  customerAddress: string;
  customerZipCode: string;
  customerCity: string;
  customerId = 0;

  // product variables
  productSelectId = 0;
  productId: number;
  productName: string;
  productCat: string;
  productCode: string;
  productPrice: number;
  productStock: string;
  productQuantity = 1;

  // total
  quantity = 1;
  updateQuantity: number;
  totalPrice = 0;
  orderTotal = 0;
  orderLineTotal: number;
  indexOfOrderLine: number;
  newOrderTotal = 0;
  orderlines: Array<orderLineCreate>;
  constructor(private http: Http) {
    this.orderlines = [];
  }

  addOrderLine(id, quantity: number) {
    if (!isNaN(id)) {
      this.indexOfOrderLine = this.getIndexByValue("Id", id);

      if (!isNaN(this.indexOfOrderLine)) {

        alert("Nee sorry!");
        // var test = quantity.toString(quantity);
        // var test_2 = parseInt(test);
        // var testQuantity =  this.orderlines[this.indexOfOrderLine].getQuantity().toString(this.orderlines[this.indexOfOrderLine].getQuantity());
        // var testQuantity_2 = parseInt(testQuantity);

        // this.orderlines[this.indexOfOrderLine].getQuantity();
        // this.updateQuantity = this.orderlines[this.indexOfOrderLine].getQuantity() + quantity;
        // console.log(this.updateQuantity);
        // console.log(this.orderlines[this.indexOfOrderLine].getQuantity());
        // console.log(quantity);
        // this.orderlines[this.indexOfOrderLine].setQuantity(this.updateQuantity);

      } else {

        this.productName = this.products[this.productSelectId].name;
        this.productCat = this.products[this.productSelectId].category;
        this.productCode = this.products[this.productSelectId].code;
        this.productPrice = this.products[this.productSelectId].price;

        this.orderLineTotal = this.productPrice * quantity;

        let orderline = new orderLineCreate(id, this.productName, this.productCat, this.productCode, this.productPrice, quantity, this.orderLineTotal);
        this.orderlines.push(orderline);
        this.orderTotal = this.orderTotal + this.orderLineTotal;

        this.getProductInfo(null);
      }
    }
  }

  removeOrderLine(orderline) {
    let index = this.orderlines.indexOf(orderline);
    this.orderTotal = this.orderTotal - orderline.total;
    this.orderlines.splice(index, 1);

  }

  setOrderTotal(total: number) {
    total = 0;
    this.orderlines.forEach(function (item) {
      total = total + item["total"];
    });
    this.orderTotal = total;
  }

  getCustomerInfo(customerId) {
    this.customerId = parseInt(customerId) - 1;
    this.customerAddress = this.customers[this.customerId].adres;
    this.customerZipCode = this.customers[this.customerId].zipCode;
    this.customerCity = this.customers[this.customerId].city;
    //this.order.type=value;
    // this.selectedOption = this.options.filter((item)=> item.id == optionid)[0];
  }

  getProductInfo(productId) {
    if (productId) {
      this.productSelectId = parseInt(productId) - 1;
      this.productCat = this.products[this.productSelectId].category;
      this.productCode = this.products[this.productSelectId].code;
      this.productPrice = this.products[this.productSelectId].price;
      this.productStock = this.products[this.productSelectId].stock;
      this.productId = parseInt(productId);

      //this.selectedProducts[this.productSelectId].push(this.products[this.productSelectId].price);
      this.totalPrice = this.products[this.productSelectId].price * this.quantity;
      this.selectedProducts = [
        { productId: this.productId, totalPrice: this.products[this.productSelectId].price },
      ];
    }else{
      this.productCat = null;
      this.productCode = null;
      this.productPrice = null;
      this.productStock = null;
      this.productId = null;
      this.totalPrice = null;
      this.productQuantity = 1;
    }
  }

  getTotalPrice(quantity, productId) {
    this.quantity = parseInt(quantity);
    if (productId) {
      this.productSelectId = parseInt(productId) - 1;
      this.totalPrice = this.products[this.productSelectId].price * quantity;
    }
  }

  updateTotalPrice(quantity, productId) {
    this.indexOfOrderLine = this.getIndexByValue("Id", productId);

    this.orderlines[this.indexOfOrderLine].setQuantity(quantity);

    this.setOrderTotal(0);
    // return this.orderlines[]
    // this.quantity = parseInt(quantity);
    // if (productId) {
    //   this.productSelectId = parseInt(productId) - 1;
    //   this.totalPrice = this.products[this.productSelectId].price * quantity;
    // }
  }

  getIndexByValue(key, value) {
    for (var i = 0; i < this.orderlines.length; i++) {

      if (this.orderlines[i][key] == value) {
        return i;
      }
    }
    return;
  }


  ngOnInit() {
    this.http.get("http://localhost:8080/snelTransport/resources/customers").
      toPromise().then(r => r.json()).then(r => this.customers = r);

    this.http.get("http://localhost:8080/snelTransport/resources/products").
      toPromise().then(r => r.json()).then(r => this.products = r);
  }
}
