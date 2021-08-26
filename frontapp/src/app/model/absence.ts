import { User } from "./user";

export class Absence {

    id: any;
    startAbsence: Date;
    endAbsence: Date;
    pharmacist: User;
    dermatologist: User;
    approved: boolean;
}
