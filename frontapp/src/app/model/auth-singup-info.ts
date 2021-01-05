import { CompileStylesheetMetadata } from "@angular/compiler";

export class AuthSingupInfo {
    email: string;
    password: string;
    password_cnfrm: string;
    name: string;
    surname: string;
    address: string;
    city: string;
    country: string;
    phone: string;
    role: string[];
   
 
    constructor(email: string, password: string, password_cnfrm: string, name: string, surname: string, address: string, city: string, country: string, phone: string) {
        this.email = email;
        this.password = password;
        this.password_cnfrm = password_cnfrm;
        this.name = name;
        this.surname = surname;
        this.address=address;
        this.city=city;
        this.country=country;
        this.phone=phone;
        this.role = ['USER_ROLE'];
    }
}
