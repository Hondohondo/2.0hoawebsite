package hoa.api.services.ticket.microservices;

/**
 * TODO: Make all CRUD_Service_CRUDFactories a singleton based on this class.
 * @author Owner
 *
 */
public class CRUDService_CRUDFactory {
	
	private final String serviceHomeMessage;
	
	public CRUDService_CRUDFactory() {
		serviceHomeMessage = "Welcome to HoA REST API Services Home. See service index in ApiController.java for navigation guide.";
	}
	
	public String getServiceHomeMessage() {
		return serviceHomeMessage;
	}

}
