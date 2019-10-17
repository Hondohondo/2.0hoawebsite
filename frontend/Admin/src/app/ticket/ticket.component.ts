import { Component, OnInit } from '@angular/core';
import { HOAService } from '../hoa.service';
import { Ticket } from '../Ticket';
import { TicketPost } from '../TicketPost';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  tickets: Ticket[];
  headers = ["ticketId", "name", "subject", "createdDate"];
  intakeForm: FormGroup;
  isOpen : boolean = false;

  ticket: TicketPost = {
    ticketMessage:'',
    subject:'',
    email:'',
    phoneNumber:'',
    name:''
  }

  constructor(public HOAService : HOAService, private fb: FormBuilder) {
    this.intakeForm = fb.group({
      ticketMessage: ['', Validators.required],
      subject: ['', Validators.required],
      email:['', Validators.required],
      phoneNumber:['', Validators.required],
      name:['', Validators.required]
    })
   }

  ngOnInit() {
    
  }

  getAllTickets(){
    this.HOAService.getAllTickets()
      .subscribe((data)=>{
        console.log(data);
        this.tickets=data['tickets'];
        console.log(this.tickets);
      });
  }

  addNewTicket(){
    // this.ticket.ticketMessage = this.ticketMessage.value;
    // this.ticket.email='';
    // this.ticket.name='';
    // this.ticket.subject='';
    // this.ticket.phoneNumber=''; 
    // this.HOAService.addTicket(this.ticket);
    this.HOAService.addTicket(this.intakeForm.value)
    .subscribe(data => {
      console.log(data);
      this.intakeForm.reset();
    });
    console.warn(this.intakeForm.value);
  }
}
