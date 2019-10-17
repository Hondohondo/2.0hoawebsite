# Service Endpoints for the Front-End. Remember to use Postman and plug in the following data. The Ajax code is automatically generated.

### Ticket
##### Insert New Ticket
TYPE: HTTP POST
</br>URI: http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/
</br>Params: 
<ol>
  <li>subject (String)</li>
  <li>ticketMessage (String)</li>
  <li>isActive (bit - 1 or 0)</li>
  <li>createdBy (String)</li>
  <li>name (String)</li>
  <li>phoneNumber (String)</li>
  <li>email (String)</li>
  <li>memberId (String)</li>
</ol>
</br>

##### Get All Ticket Records (for populating the admin table)
TYPE: HTTP GET
</br>URI: http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/GetAll
</br>Params: None!
</br>

##### Get A Ticket Record By ID (for the expansion overlay on the admin table)
TYPE: HTTP GET
</br>URI: http://hoa.api.ngrok.io/services/ticket/microservices/ticket_crud_service/by_ticketid
</br>Params: 
<ol>
  <li>id (Integer)</li>
</ol>
</br>
