import { Medicine } from "./medicine";
import { Pharmacy } from "./pharmacy";

export class Pricelist {

    id: any;
    medicine: Medicine;
    pharmacy: Pharmacy;
    price: any;
    fromPeriod: Date;
    toPeriod: Date;
    
}
