package hoa.api.services.ticket.microservices.ticket_crud_service.read;

import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.cliftonlabs.json_simple.JsonObject;

import hoa.api.services.Services;
import hoa.api.services.SqlOperationType;
import hoa.api.services.ticket.microservices.ticket_crud_service.Ticket;

public class TicketSelect extends Ticket{
	
	public TicketSelect(int ticketId) {
		super(SqlOperationType.select);
		this.ticketId = ticketId;
		super.queryDb();
	}

	@Override
	protected ResultSet executeSql(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		try {
			return stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getQuery() {
		// TODO Auto-generated method stub
		return "SELECT * FROM Ticket WHERE TicketID = " + this.getTicketId();
	}


	@Override
	public Services getServiceName() {
		// TODO Auto-generated method stub
		return Services.TicketSelect;
	}
	
	@Override
	public void toJson(Writer writer) throws IOException {
		// this.queryDb();
		final JsonObject json = new JsonObject();
		json.put("ticketId", this.getTicketId());
		json.put("subject", this.getSubject());
		json.put("message", this.getMessage());
		json.put("createdBy", this.getCreatedBy());
		json.put("createdDate", this.getCreatedDate());
		json.put("name", this.getName());
		json.put("phoneNumber", this.getPhoneNumber());
		json.put("email", this.getEmail());
		json.put("memberId", this.getMemberID());
		json.toJson(writer);
	}

}
