export class Order{

    customerId: number;
    id: number;
    orderDate: string;
    orderLines = [];
    status: number;


    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
