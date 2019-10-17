package hoa.api.services;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Observable;
import java.util.Observer;

import com.github.cliftonlabs.json_simple.Jsonable;

/**
 * Uses Template pattern with Observer-Observable to serialize (similar JEE JavaBeans) 
 * @author NEALK
 *
 */
public abstract class Service extends Observable implements Observer, Jsonable {
	
	public Service() {
		this.addObserver(this);
	}
	
	public final String toJson(Class<Service> request) {
		setChanged();
		notifyObservers(request);
		return toJson();
	}
	
	public final String toJson(Services requestedService) {
		setChanged();
		notifyObservers(requestedService);
		return toJson();
	}
	
	@Override
	public final void update(Observable obsv, Object obj) {
		if(obj.equals(getServiceName())) {
			String json = toJson();
			System.out.println(json);
		}
	}
	

	@Override
	public final String toJson() {
		final StringWriter writable = new StringWriter();
		try {
			this.toJson(writable);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return writable.toString();
	}
	
	/**
	 * Create your json object to return as a String, ex:
	 * </br>
	 * <code>
	 * </br>final JsonObject json = new JsonObject();
	 * </br>json.put("name", this.getName());
	 * </br>json.toJson(writer);
	 * </code>
	 */
	@Override
	public
	abstract void toJson(Writer writer) throws IOException;
	


	/** 
	 * </br>Returns the Enum for your Service in:
	 * </br>hoa-services -> hoa.api.services -> Service.java.
	 * </br>
	 * </br>If your Service is not listed, please add it here.
	 * </br>This method adds your Service to the Observer-Observable chain.
	 * </br>
	 * @return - See above
	 * @author - NEALK
	 */
	public abstract Services getServiceName();
	
	

}
