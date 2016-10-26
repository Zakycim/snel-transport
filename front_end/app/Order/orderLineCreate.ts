export class orderLineCreate{

    productId: number;
    name: string;
    category: string;
    code: string;
    price: number;
    amount: number;
    total: number;

    constructor(id, name, category, code, price, amount, total){
        this.productId = id;
        this.name = name;
        this.category = category;
        this.code = code;
        this.price = price;
        this.amount = amount;
        this.total = total;
    }

    getamount(){
        return this.amount;
    }
    
    getProductId() {
        return this.productId;
    }
  
    getId(){
        return this.productId;
    }

    setamount(amount){
        this.amount = amount;

        this.setnewPrice(amount);
    }

    setnewPrice(amount){
        this.total = this.price * amount;
    }
    
}