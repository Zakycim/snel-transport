export class orderLineCreate{

    Id: string;
    name: string;
    category: string;
    code: string;
    price: number;
    quantity: number;
    total: number;

    constructor(id, name, category, code, price, quantity, total){
        this.Id = id;
        this.name = name;
        this.category = category;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    getQuantity(){
        return this.quantity;
    }

    setQuantity(quantity){
        this.quantity = quantity;

        this.setnewPrice(quantity);
    }

    setnewPrice(quantity){
        this.total = this.price * quantity;
    }
    
}