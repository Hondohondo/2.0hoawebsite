import { Injectable, Inject } from '@angular/core';
import { Ticket } from '../app/Ticket';
import { TicketPost } from '../app/TicketPost';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Observable, from } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { ArrayType } from '@angular/compiler';
import { JsonPipe } from '@angular/common';
import { HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class HOAService {
  Tickets = [];
  constructor(private http: HttpClient) { }

  public getAllTicketURL: string =
    "http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/GetAll";
  public getTicketByIdURL: string =
    "http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/by_ticketid?id=";
  public postTicketURL: string =
    "http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/";
  public updateTicketURL: string =
    "http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/";
  public deleteTicketURL: string =
    "http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/?id=";

  getAllTickets(): Observable<Ticket> {
    // return this.http.get(this.getAllTicketURL)
    //         .pipe(map((result: Response) => this.Tickets = result['tickets']));\
    return this.http.get(this.getAllTicketURL)
      .pipe(map(data => new Ticket(data)));
  };

  getTicketById(id: number): Observable<Ticket> {
    return this.http.get(
      this.getTicketByIdURL + id).pipe(map(data=>new Ticket(data)));
  };

  addTicket(ticket: TicketPost): Observable<TicketPost> {
    return this.http.post<TicketPost>(this.postTicketURL, ticket, httpOptions);
  }

  updateTicket(ticket: Ticket): Observable<Ticket> {
    return this.http.put<Ticket>(this.updateTicketURL, ticket, httpOptions);
  }

  /** DELETE: delete the ticket from the server */
  deleteTicket(id: number): Observable<{}> {
    return this.http.delete(this.deleteTicketURL+id, httpOptions);
  }
}
