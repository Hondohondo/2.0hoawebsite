package hoa.api.services.ticket.microservices.ticket_crud_service.delete;

import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.cliftonlabs.json_simple.JsonObject;

import hoa.api.services.Services;
import hoa.api.services.SqlOperationType;
import hoa.api.services.ticket.microservices.ticket_crud_service.Ticket;

public class TicketDelete extends Ticket{

	protected TicketDelete() {
		super(SqlOperationType.delete);
		// TODO Auto-generated constructor stub
		super.queryDb();
	}
	
	public TicketDelete(int ticketId) {
		super(SqlOperationType.delete);
		this.setTicketId(ticketId);
		super.queryDb();
	}

	@Override
	protected ResultSet executeSql(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		try {
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getQuery() {
		// TODO Auto-generated method stub
		return "DELETE from TICKET WHERE TicketID = " + this.getTicketId();
	}

	@Override
	public void toJson(Writer writer) throws IOException {
		// TODO Auto-generated method stub
		String status = "deleted.";
		JsonObject json = new JsonObject();
		json.put("status", status);
		json.toJson(writer);
	}

	@Override
	public Services getServiceName() {
		// TODO Auto-generated method stub
		return Services.TicketDelete;
	}

}
