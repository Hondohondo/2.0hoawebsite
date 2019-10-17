package hoa.api.services.ticket.microservices.ticket_crud_service.read;

import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import hoa.api.services.Services;
import hoa.api.services.SqlOperationType;
import hoa.api.services.ticket.microservices.ticket_crud_service.Ticket;

public class TicketSelectGetAll extends Ticket{
	
//	public TicketSelect(int ticketId) {
//		this.ticketId = ticketId;
//		this.subject = super.getSubject();
//	}
	
	public TicketSelectGetAll() {
		super(SqlOperationType.select_get_all);
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
		return "SELECT * from TICKET ORDER BY TicketID DESC";
	}

	@Override
	public Services getServiceName() {
		// TODO Auto-generated method stub
		return Services.TicketGetAll;
	}

	@Override
	public void toJson(Writer writer) throws IOException {
		// TODO Auto-generated method stub
		
		JsonArray jsonArray = new JsonArray();	
		JsonObject tickets = new JsonObject();
		Iterator<Integer> iter = jsonTicketIdList.iterator();
		int size = jsonTicketIdList.size();
		for(int index=0;index<size;index++) {
			JsonObject ticket = new JsonObject();
			ticket.put("ticketId", jsonTicketIdList.get(index));
			ticket.put("subject", jsonSubjectList.get(index));
			//ticket.put("ticketMessage", jsonMessageList.get(index));
			ticket.put("isActive", jsonIsActiveList.get(index));
			//ticket.put("createdBy", jsonCreatedByList.get(index));
			ticket.put("createdDate", jsonCreatedDateList.get(index));
			ticket.put("name", jsonNameList.get(index));
			ticket.put("phoneNumber", jsonPhoneNumberList.get(index));
			ticket.put("memberId", jsonMemberIdList.get(index));
			jsonArray.add(ticket);
		}
		tickets.put("tickets", jsonArray);
		System.out.println("in get allllll: " + tickets);
		tickets.toJson(writer);
	}

//	@Override
//	public void update(Observable obsv, Object obj) {
//		// TODO Auto-generated method stub
//		if(obj.equals(Services.TicketGetAll))
//			this.toJson();
//	}
}