export class Order{
    id: number;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
