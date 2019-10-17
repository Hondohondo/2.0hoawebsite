package hoa.api.services.ticket.microservices.ticket_crud_service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.cliftonlabs.json_simple.JsonObject;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import hoa.api.services.Service;
import hoa.api.services.CRUD;
import hoa.api.services.ServiceConstants;
import hoa.api.services.SqlOperationType;

/**
 * Returns a Ticket object. Jsonable is similar to Serializable for Java Beans,
 * but for JSON. </br>
 * </br>
 * {@link #ticketId} = TicketID in UtilityDB.TICKET </br>
 * etc... </br>
 * TODO: Finish this class documentation.
 * 
 * @author Owner
 *
 */
public abstract class Ticket extends Service implements CRUD {
	//@JsonProperty(required = false)
	public volatile int ticketId; //protected
	//@JsonProperty(required = false)
	public volatile String subject;	//protected
	//@JsonProperty(required = false)
	public volatile String message;	//private 
	//@JsonProperty(required = false)
	public volatile String createdBy;	//...
	//@JsonProperty(required = false)
	public volatile String createdDate;
	//@JsonProperty(required = false)
	public volatile String name;
	public volatile String phoneNumber;
	public volatile String email;
	public volatile String memberID;
	public volatile String status;
	public volatile boolean isActive;
	
	private final String connectionString = ServiceConstants.CONNECTION_STRING;	
	protected final SqlOperationType operation;
	private Map<SqlOperationType, Ticket> ticketTable;
	private JsonObject ticketSelectGetAllJson;

	protected List<Integer> jsonTicketIdList;
	protected List<String> jsonSubjectList;
	protected List<String> jsonMessageList;
	protected List<Boolean> jsonIsActiveList;
	protected List<String> jsonCreatedByList;
	protected List<String> jsonCreatedDateList;
	protected List<String> jsonNameList;
	protected List<String> jsonPhoneNumberList;
	protected List<String> jsonMemberIdList;
	//protected List<String> jsonEmailList;

	/**
	 * @return the operation
	 */
	public synchronized SqlOperationType getOperation() {
		return operation;
	}

	protected Ticket(SqlOperationType operation) {
		this.operation = operation;
		// ticketTable = new Hashtable<SqlOperationType, Ticket>();
		// ticketTable.put(SqlOperationType.select, new TicketSelect());
		// queryDb();
	}

//	@Override
//	public void toJson(Writer writer) throws IOException {
//		// this.queryDb();
//		final JsonObject json = new JsonObject();
//		json.put("ticketId", this.getTicketId());
//		json.put("subject", this.getSubject());
//		json.put("message", this.getMessage());
//		json.put("createdBy", this.getCreatedBy());
//		json.put("createdDate", this.getCreatedDate());
//		json.put("name", this.getName());
//		json.put("phoneNumber", this.getPhoneNumber());
//		json.put("email", this.getEmail());
//		json.put("memberId", this.getMemberID());
//		json.toJson(writer);
//	}

	public final String queryDb() {
		if (this.getOperation().equals(SqlOperationType.select)) {
			return executeQuerySelect(this.getOperation());
		}
//		else if(this.getOperation().equals(SqlOperationType.select_get_all)) {
//			return executeQuerySelectGetAll();
//		}
		else if (this.getOperation().equals(SqlOperationType.insert)) {
			return executeQueryInsert();
		} else if(this.getOperation().equals(SqlOperationType.update) || this.getOperation().equals(SqlOperationType.delete)) {
			return executeQueryInsert();
		}
		
		return executeQuerySelect(this.getOperation());
	}
	
	public final String queryDb(SqlOperationType operation) {
		if (this.getOperation().equals(SqlOperationType.select)) {
			return executeQuerySelect(this.getOperation());
		}
		else if (this.getOperation().equals(SqlOperationType.insert)) {
			return executeQueryInsert();
		}
		return executeQuerySelect(this.getOperation());
	}

	@Override
	public final Object excuteQuery(SqlOperationType operation) {
		if (this.getOperation().equals(SqlOperationType.select)) {
			final String sql = getQuery();
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connected.");
				PreparedStatement stmt = connection.prepareStatement(sql);
				if (operation.equals(SqlOperationType.insert)) {
					stmt.executeUpdate();
					status = "success";
				} else if (operation.equals(SqlOperationType.select)) {
					ResultSet rs = stmt.executeQuery();
					status = "null data.";
					if (rs != null) {
						status = "success";
						while (rs.next()) {
							this.subject = rs.getString(TicketColumns.Subject.toString());
							this.message = rs.getString(TicketColumns.TicketMessage.toString());
							this.isActive = (rs.getInt(TicketColumns.IsActive.toString()) == 1) ? true : false;
							this.createdBy = rs.getString(TicketColumns.CreatedBy.toString());
							this.createdDate = rs.getString(TicketColumns.CreatedDate.toString());
							this.name = rs.getString(TicketColumns.Name.toString());
							this.email = rs.getString(TicketColumns.Email.toString());
							this.phoneNumber = rs.getString(TicketColumns.PhoneNumber.toString());
							this.memberID = rs.getString(TicketColumns.MemberID.toString());
						}
					}
				}
			} catch (SQLServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			return status;
		} else if (this.getOperation().equals(SqlOperationType.select_get_all)) {
			final String sql = getQuery();
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connected.");
				PreparedStatement stmt = connection.prepareStatement(sql);
				if (operation.equals(SqlOperationType.insert)) {
					stmt.executeUpdate();
					status = "success";
				} else if (operation.equals(SqlOperationType.select)) {
					ResultSet rs = stmt.executeQuery();
					status = "null data.";
					if (rs != null) {
						status = "success";
						while (rs.next()) {
							this.subject = rs.getString(TicketColumns.Subject.toString());
							this.message = rs.getString(TicketColumns.TicketMessage.toString());
							this.isActive = (rs.getInt(TicketColumns.IsActive.toString()) == 1) ? true : false;
							this.createdBy = rs.getString(TicketColumns.CreatedBy.toString());
							this.createdDate = rs.getString(TicketColumns.CreatedDate.toString());
							this.name = rs.getString(TicketColumns.Name.toString());
							this.email = rs.getString(TicketColumns.Email.toString());
							this.phoneNumber = rs.getString(TicketColumns.PhoneNumber.toString());
							this.memberID = rs.getString(TicketColumns.MemberID.toString());
						}
					}
				}
			} catch (SQLServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			return status;
		}
		return "jdbc failure.";
	}

	private String executeQuerySelect(SqlOperationType operation) {
		// TODO Auto-generated method stub
		if (this.getOperation().equals(SqlOperationType.select)) {
			final String sql = getQuery();
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connected.");
				PreparedStatement stmt = connection.prepareStatement(sql);
				if (operation.equals(SqlOperationType.insert)) {
					stmt.executeUpdate();
					status = "success";
				} else if (operation.equals(SqlOperationType.select)) {
					ResultSet rs = stmt.executeQuery();
					status = "null data.";
					if (rs != null) {
						status = "inserted";
						while (rs.next()) {
							this.subject = rs.getString(TicketColumns.Subject.toString());
							this.message = rs.getString(TicketColumns.TicketMessage.toString());
							this.isActive = (rs.getInt(TicketColumns.IsActive.toString()) == 1) ? true : false;
							this.createdBy = rs.getString(TicketColumns.CreatedBy.toString());
							this.createdDate = rs.getString(TicketColumns.CreatedDate.toString());
							this.name = rs.getString(TicketColumns.Name.toString());
							this.email = rs.getString(TicketColumns.Email.toString());
							this.phoneNumber = rs.getString(TicketColumns.PhoneNumber.toString());
							this.memberID = rs.getString(TicketColumns.MemberID.toString());
						}
					}
				}
			} catch (SQLServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			return status;
		} else if (this.getOperation().equals(SqlOperationType.select_get_all)) {
			final String sql = getQuery();
			JsonObject json = new JsonObject();
			
			jsonTicketIdList = new ArrayList<Integer>();
			//Iterator<Integer> ticketIdIter = jsonTicketIdList.iterator();
			jsonSubjectList = new ArrayList<String>();
			//Iterator<String> subjectIter = jsonSubjectList.iterator();
			jsonMessageList = new ArrayList<String>();
			//Iterator<String> messageIter = jsonSubjectList.iterator();
			jsonIsActiveList = new ArrayList<Boolean>();
			//Iterator<Boolean> isActiveIter = jsonIsActiveList.iterator();
			jsonCreatedByList = new ArrayList<String>();
			//Iterator<String> createdByIter = jsonCreatedByList.iterator();
			jsonCreatedDateList = new ArrayList<String>();
			//Iterator<String> createdDateIter = jsonCreatedDateList.iterator();
			//jsonEmailList = new ArrayList<String>();
			jsonNameList = new ArrayList<String>();
			//Iterator<String> nameIter = jsonNameList.iterator();
			jsonPhoneNumberList = new ArrayList<String>();
			//Iterator<String> phoneNumberIter = jsonPhoneNumberList.iterator();
			jsonMemberIdList = new ArrayList<String>();
			//Iterator<String> MemberIdIter = jsonMemberIdList.iterator();
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connected.");
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				status = "null data.";
				// JSONArray ticketsJsonArray = new JSONArray();
				JsonObject tickets = new JsonObject();
				if (rs != null) {
					status = "success";
					while (rs.next()) {
						// JsonObject ticket = new JsonObject();
						this.ticketId = rs.getInt(TicketColumns.TicketID.toString());
						this.subject = rs.getString(TicketColumns.Subject.toString());
						this.message = rs.getString(TicketColumns.TicketMessage.toString());
						this.isActive = (rs.getInt(TicketColumns.IsActive.toString()) == 1) ? true : false;
						this.createdBy = rs.getString(TicketColumns.CreatedBy.toString());
						this.createdDate = rs.getString(TicketColumns.CreatedDate.toString());
						this.name = rs.getString(TicketColumns.Name.toString());
						this.email = rs.getString("email");
						this.phoneNumber = rs.getString(TicketColumns.PhoneNumber.toString());
						this.memberID = rs.getString(TicketColumns.MemberID.toString());

						jsonTicketIdList.add(this.getTicketId());
						jsonSubjectList.add(this.getSubject());
						jsonMessageList.add(this.getMessage());
						jsonIsActiveList.add(this.getIsActive());
						jsonCreatedByList.add(this.getCreatedBy());
						jsonCreatedDateList.add(this.getCreatedDate());
						jsonNameList.add(this.getName());
						jsonPhoneNumberList.add(this.getPhoneNumber());
						jsonMemberIdList.add(this.getMemberID());
						//jsonEmailList.add(this.getEmail());
						System.out.println("email: " + this.getEmail());
						// tickets.put("ticket", ticket);
						// ticketsJsonArray.add(ticket);
					}
					// json.put("tickets", tickets);
					// this.ticketSelectGetAllJson = json;
				}

			} catch (SQLServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			// return null;
			return json.toJson();
		}

		return "error.";
	}

	private String executeQueryInsert() {
		final String sql = getQuery();
		String status = "failed.";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionString);
			System.out.println("Connected.");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	protected abstract ResultSet executeSql(PreparedStatement stmt);

	protected abstract String getQuery();

	@JsonProperty("ticketId")
	/**
	 * @return the ticketId
	 */
	protected synchronized int getTicketId() {
		return ticketId;
	}

	@JsonProperty("ticketMessage")
	/**
	 * @return the message
	 */
	protected synchronized String getMessage() {
		return message;
	}

	@JsonProperty("subject")
	/**
	 * @return the subject
	 */
	protected synchronized String getSubject() {
		return subject;
	}

	@JsonProperty("createdBy")
	/**
	 * @return the createdBy
	 */
	protected synchronized String getCreatedBy() {
		return createdBy;
	}

	@JsonProperty("createdDate")
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	@JsonProperty("name")
	/**
	 * @return the name
	 */
	protected synchronized String getName() {
		return name;
	}

	@JsonProperty("phoneNumber")
	/**
	 * @return the phoneNumber
	 */
	protected synchronized String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("email")
	/**
	 * @return the email
	 */
	protected synchronized String getEmail() {
		return email;
	}

	@JsonProperty("memberId")
	/**
	 * @return the memberID
	 */
	protected synchronized String getMemberID() {
		return memberID;
	}

	@JsonProperty("isActive")
	/**
	 * @return the isActive
	 */
	protected synchronized boolean getIsActive() {
		return isActive;
	}

	//@JsonProperty("status")
	/**
	 * @return the status
	 */
	protected synchronized String getStatus() {
		return status;
	}

	/**
	 * @return the ticketSelectGetAllJson
	 */
	protected synchronized JsonObject getTicketSelectGetAllJson() {
		return ticketSelectGetAllJson;
	}

	protected synchronized List<String> getJsonSubjectList() {
		return jsonSubjectList;
	}

	@JsonProperty("ticketId")
	/**
	 * @param ticketId the ticketId to set
	 */
	protected synchronized void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	@JsonProperty("subject")
	/**
	 * @param subject the subject to set
	 */
	protected synchronized void setSubject(String subject) {
		this.subject = subject;
	}

	@JsonProperty("ticketMessage")
	/**
	 * @param message the message to set
	 */
	protected synchronized void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("createdBy")
	/**
	 * @param createdBy the createdBy to set
	 */
	protected synchronized void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonProperty("name")
	/**
	 * @param name the name to set
	 */
	protected synchronized void setName(String name) {
		this.name = name;
	}

	@JsonProperty("phoneNumber")
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	protected synchronized void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("email")
	/**
	 * @param email the email to set
	 */
	protected synchronized void setEmail(String email) {
		this.email = email;
	}

	//@JsonProperty("memberID")
	/**
	 * @param memberID the memberID to set
	 */
	protected synchronized void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	@JsonProperty("status")
	/**
	 * @param status the status to set
	 */
	protected synchronized void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("isActive")
	/**
	 * @param isActive the isActive to set
	 */
	protected synchronized void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@JsonProperty("memberId")
	/**
	 * @param isActive the isActive to set
	 */
	protected synchronized void setMemberId(String memeberId) {
		this.memberID = memeberId;
	}

	//@JsonProperty("createdDate")
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
