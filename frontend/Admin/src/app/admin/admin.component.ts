import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HOAService } from '../hoa.service';
import { Ticket } from '../Ticket';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { NgbModal, ModalDismissReasons, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { resolve } from 'url';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {
  closeResult: string;
  tickets: Ticket[];
  modalForm: FormGroup;
  ticketData: Ticket;
  ticketUpdate: Ticket;

  modalRef:NgbModalRef;

  modalTicketName: any;
  modalTicketId: any;
  modalTicketDate: any;
  modalTicketMessage: any;
  modalTicketSubject: any;
  modalIsActive: any;
  modalTicketPhoneNumber: any;

  headers = ["ticketId", "createdDate", "name", "subject"];

  constructor(public HOAService: HOAService, private modalService: NgbModal, private fb: FormBuilder) {
    this.modalForm = fb.group({
      ticketMessage: ['', Validators.required],
      isActive: ['', Validators.required],
      ticketId: ['']
    })
  }

  ngOnInit() {
    this.getAllTickets();
  }

  getAllTickets() {
    this.HOAService.getAllTickets()
      .subscribe((data) => {
        console.log(data);
        this.tickets = data['tickets'];
        //console.log(this.tickets);
      });
  };

  async getSelectedTicket(id: number) {
    this.HOAService.getTicketById(id).subscribe(data => {
      this.ticketData = <Ticket>data;
      console.log(this.ticketData.name);
    });
    return;
  }

  fillModal() {
    this.modalTicketName = this.ticketData.name;
  }
  async getTicketInfo(content, ticket: Ticket) {
    this.HOAService.getTicketById(ticket.ticketId).subscribe(data => {
      this.ticketData = data;
      // console.log(data);
      // console.log(this.ticketData.message);
      this.modalTicketDate = this.ticketData.createdDate;
      //this.modalIsActive
      this.modalTicketMessage = this.ticketData.message;
      this.modalTicketId = this.ticketData.ticketId;
      this.modalTicketName = this.ticketData.name;
      this.modalTicketPhoneNumber = this.ticketData.phoneNumber;
      this.modalTicketSubject = this.ticketData.subject;
      this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
    })
  }

  open(content) {
    this.modalRef=this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  };

  sendUpdate() {
    this.ticketUpdate=this.modalForm.value;
    this.ticketUpdate.ticketId=this.modalTicketId;
    console.log(this.ticketUpdate);
    this.HOAService.updateTicket(this.ticketUpdate)
      .subscribe(data => {
        console.log(data);
      });
      this.modalForm.reset();
      this.modalService.dismissAll();
    alert("Your ticket is now updated!");
    console.warn(this.modalForm.value);
  };

  sendDelete() {
    this.HOAService.deleteTicket(this.modalTicketId).subscribe();
    this.modalService.dismissAll();
  }
}
