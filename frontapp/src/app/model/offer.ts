import { Order } from "./order";
import { User } from "./user";

export class Offer {
    id: any;
    supplier: User;
    order: Order;
    price: any;
    status: string;
}
