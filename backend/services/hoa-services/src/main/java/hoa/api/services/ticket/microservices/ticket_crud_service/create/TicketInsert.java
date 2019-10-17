package hoa.api.services.ticket.microservices.ticket_crud_service.create;

import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

import org.json.simple.JSONObject;

import com.github.cliftonlabs.json_simple.JsonObject;

import hoa.api.services.Services;
import hoa.api.services.SqlOperationType;
import hoa.api.services.ticket.microservices.ticket_crud_service.Ticket;
import hoa.api.services.ticket.microservices.ticket_crud_service.TicketColumns;

public class TicketInsert extends Ticket{

//	protected TicketInsert(SqlOperationType operation) {
//		super(operation);
//		// TODO Auto-generated constructor stub
//	}
	
	public TicketInsert() {
		super(SqlOperationType.insert);
		//super.queryDb();
	}


	public TicketInsert(String subject, String ticketMessage, boolean isActive, String name, String phoneNumber,
			String email, String memberId) {
		// TODO Auto-generated constructor stub
		super(SqlOperationType.insert);
		this.setSubject(subject);
		this.setMessage(ticketMessage);
		this.setActive(isActive);
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
		this.setMemberID(memberId);
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
		System.out.println(this.getSubject());
//		return "insert into TICKET (Subject, TicketMessage, IsActive, CreatedBy, Name, PhoneNumber, Email, MemberID) values ('Neal','Is Awesome',0,'Neally','Neal test','(123) abc-5150','nkumar20@jcu.edu',1);";
		return "INSERT into TICKET ("
				+ TicketColumns.Subject + ", "
						+ TicketColumns.TicketMessage + ", "
								+ TicketColumns.IsActive + ", "
										+ TicketColumns.CreatedBy + ", "
												+ TicketColumns.Name + ", "
														+ TicketColumns.PhoneNumber + ", "
																+ TicketColumns.Email + ", "
																		+ TicketColumns.MemberID + ") VALUES ('"
				+ this.getSubject() + "','"
						+ this.getMessage() + "','"
								+ this.getIsActive() + "','"
										+ this.getCreatedBy() + "','"
												+ this.getName() + "','"
														+ this.getPhoneNumber() + "','"
																+ this.getEmail() + "','"
																		+ /*this.getMemberID()*/ 1 + "');";
	}

	@Override
	public Services getServiceName() {
		// TODO Auto-generated method stub
		return Services.TicketInsert;
	}


	@Override
	public void toJson(Writer writer) throws IOException {
		// TODO Auto-generated method stub
		String status = "inserted.";//super.getStatus();
		JsonObject json = new JsonObject();
		json.put("status", status);
		json.toJson(writer);
	}

}
