export class TicketPost {

    ticketMessage: string;
    phoneNumber: string;
    subject: string;
    name: string;
    email: string;

    constructor(ticketMessage:string, phoneNumber:string, email:string, name:string, subject:string){
        this.ticketMessage = ticketMessage;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.subject = subject;
    }

}