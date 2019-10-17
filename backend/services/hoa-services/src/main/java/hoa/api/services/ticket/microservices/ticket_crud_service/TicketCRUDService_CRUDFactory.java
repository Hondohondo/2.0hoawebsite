package hoa.api.services.ticket.microservices.ticket_crud_service;

import com.github.cliftonlabs.json_simple.Jsoner;

import hoa.api.services.Services;
import hoa.api.services.ticket.microservices.CRUDService_CRUDFactory;
import hoa.api.services.ticket.microservices.ticket_crud_service.read.TicketSelectGetAll;
import hoa.api.services.ticket.microservices.ticket_crud_service.update.TicketUpdate;
import hoa.api.services.ticket.microservices.ticket_crud_service.create.TicketInsert;
import hoa.api.services.ticket.microservices.ticket_crud_service.delete.TicketDelete;
import hoa.api.services.ticket.microservices.ticket_crud_service.read.TicketSelect;

public class TicketCRUDService_CRUDFactory extends CRUDService_CRUDFactory{
	
	public String init(int ticketId) {
		TicketSelect ticketSelect = new TicketSelect(ticketId);
		String json = /*Jsoner.serialize(*/ticketSelect.toJson(Services.TicketSelect)/*)*/;
		System.out.println("factory: " + json);
		return Jsoner.prettyPrint(json);
	}
	
	public String init(int ticketId, int throwaway) {
		TicketDelete ticketDelete = new TicketDelete(ticketId);
		String json = /*Jsoner.serialize(*/ticketDelete.toJson(Services.TicketDelete)/*)*/;
		System.out.println("factory: " + json);
		return Jsoner.prettyPrint(json);
	}
	
	public String init(){
		TicketSelectGetAll ticketSelectGetAll = new TicketSelectGetAll();
		String json = ticketSelectGetAll.toJson(Services.TicketGetAll);
		System.out.println("factory: " + json);
		//return Jsoner.prettyPrint(json);
		return Jsoner.prettyPrint(json);
	}
	
	public String init(String subject, String ticketMessage, boolean isActive, String name, String phoneNumber, String email, String memberId) {
		isActive = true;
		memberId = (memberId == null) ? "1" : memberId;
		TicketInsert ticketInsert = new TicketInsert(subject, ticketMessage, isActive, name, phoneNumber, email, memberId);
		String json = /*(Jsoner.serialize(*/ticketInsert.toJson(Services.TicketInsert)/*)*/;
		System.out.println("factory: " + json);
		return json;
	}
	
	public String init(int ticketId, String ticketMessage, boolean isActive) {
		TicketUpdate ticketUpdate = new TicketUpdate(ticketId, ticketMessage, isActive);
		String json = ticketUpdate.toJson(Services.TicketUpdate);
		System.out.println("factory: " + json);
		return json;
	}


}
