package hoa.api.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hoa.api.services.ticket.microservices.CRUDService_CRUDFactory;
import hoa.api.services.ticket.microservices.ticket_crud_service.Ticket;
import hoa.api.services.ticket.microservices.ticket_crud_service.TicketCRUDService_CRUDFactory;
import hoa.api.services.ticket.microservices.ticket_crud_service.TicketCRUDService_Constants;
import hoa.api.services.ticket.microservices.ticket_crud_service.create.TicketInsert;
import hoa.api.services.ticket.microservices.ticket_crud_service.update.TicketUpdate;

/**
 * </br>
 * All new request maps must have the following comments: </br>
 * SERVICE: ServiceName/subfolder </br>
 * TYPE: Http Type (GET, POST, PUT, DELETE) </br>
 * Description: Include whether its a batch or individual, the database name,
 * and details about the request params.
 * 
 * @author Owner
 *
 */
@RestController
public final class ApiController {

	/**
	 * </br>
	 * SERVICE: / </br>
	 * TYPE: GET </br>
	 * DESCRIPTION: Individual record retrieval from TicketDB by TicketID number.
	 * </br>
	 * @return - Greeting message for Services home.
	 */
	@RequestMapping(method = RequestMethod.GET, value = ServiceConstants.SERVICES)
	@ResponseStatus(HttpStatus.OK)
	public String index() {
		return new CRUDService_CRUDFactory().getServiceHomeMessage();
	}

	/**
	 * </br>
	 * SERVICE: ticket/microservices/ticket_crud_service/read </br>
	 * TYPE: GET </br>
	 * DESCRIPTION: Individual record retrieval from TicketDB by TicketID number.
	 * </br>
	 * @param id </br>
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = TicketCRUDService_Constants.READ_BY_TICKETID, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin()
	public String retrierveByTicketId(@RequestParam(value = "id") int id) {
		return new TicketCRUDService_CRUDFactory().init(id);
	}

	/**
	 * </br>
	 * SERVICE: ticket/microservices/ticket_crud_service/read </br>
	 * TYPE: GET </br>
	 * DESCRIPTION: Bulk retreival. </br>
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = TicketCRUDService_Constants.READ_ALL, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin(/* origins = "http://hoa.ngrok.io" /*+ TicketCRUDService_Constants.READ_ALL */)
	public String retrierveByAll() {
		return new TicketCRUDService_CRUDFactory().init();
	}

	/**
	 * </br>
	 * SERVICE: ticket/microservices/ticket_crud_service/ </br>
	 * TYPE: POST </br>
	 * DESCRIPTION: Insert a new ticket. </br>
	 * @param id </br>
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = TicketCRUDService_Constants.CREATE)
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin()
	public /* @ResponseBody */ String insertNew(@RequestBody TicketInsert ticket) {
		System.out.println("Subject = " + ticket.subject);
		return new TicketCRUDService_CRUDFactory().init(ticket.subject, ticket.message, ticket.isActive, ticket.name,
				ticket.phoneNumber, ticket.email, ticket.memberID);
	}

	/**
	 * </br>
	 * SERVICE: ticket/microservices/ticket_crud_service/ </br>
	 * TYPE: PUT </br>
	 * DESCRIPTION: Update an existing ticket. </br>
	 * @param id </br>
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = TicketCRUDService_Constants.UPDATE)
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@CrossOrigin()
	public /* @ResponseBody */ String updateExisting(@RequestBody TicketUpdate ticket) {
		System.out.println("Update on ticket = " + ticket.ticketId + ticket.message);
		return new TicketCRUDService_CRUDFactory().init(ticket.ticketId, ticket.message, ticket.isActive);
	}

	/**
	 * </br>
	 * SERVICE: ticket/microservices/ticket_crud_service/ </br>
	 * TYPE: DELETE </br>
	 * DESCRIPTION: Delete an existing ticket. </br>
	 * @param id </br>
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = TicketCRUDService_Constants.UPDATE)
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@CrossOrigin()
	public String deleteByTicketId(@RequestParam(value = "id") int id) {
		return new TicketCRUDService_CRUDFactory().init(id, id);
	}

}
