package hoa.api.services;

/**
 * All CRUD services should implement this.
 *
 */
public interface CRUD {
	
	abstract String queryDb();
	abstract Object excuteQuery(SqlOperationType operation);

}
