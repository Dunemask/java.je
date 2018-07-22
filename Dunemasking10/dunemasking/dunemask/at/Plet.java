/**
 * 
 */
package dunemask.at;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author Dunemask
 *
 */
public class Plet {

	private HashMap<String,Object> map;
	
	public static Plet load(File f) {
		Plet p = new Plet();
		try {
			var lines = RW.readAll(f.toURI().toURL());
			String line;
			String uuid;
			String data;
			final var splitChar = ":{";
			int ind = 0;
			for(int i=0;i<lines.size();i++) {
				line = lines.get(i);
				ind = line.indexOf(splitChar);
				uuid = line.substring(0,ind);
				data = line.substring(ind+splitChar.length(), line.length());
				data = StringUtil.replaceLast(data, "}", "");
				p.getMap().put(uuid, data);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	
	
	/**<p> What is a plet? </p> 
	 * <p>A Plet is a data organizer that uses a UUID for every data value</p>
	 * 
	 */
	public Plet() {
	 this.setMap((new HashMap<String,Object>()));
	}
	/** Get attached Object from uuid
	 * @param uuid
	 * @return Attached Object
	 * 
	 * */
	public Object getData(String uuid) {
		return this.getMap().get(uuid);
	}
	/** Add Data
	 * @param uuid
	 * @param data
	 * 
	 * */
	public void addData(String uuid,Object data) {
		if(uuid.contains(":{")) {
			throw new RuntimeException("UUID CANNOT CONTAIN :{");
		}else if(this.getMap().containsKey(uuid)) {
			throw new RuntimeException("DATA ALREADY EXISTS UNDER THAT ENTRY!");
		}else {
			this.getMap().put(uuid, data);
		}
	}
	/** Edit Data 
	 * @param uuid
	 * @param newData
	 * 
	 * */
	public void editData(String uuid,Object newData) {
		if(!this.getMap().containsKey(uuid)) {
			throw new RuntimeException("UUID HAS NOT BEEN ESTABLISHED YET!");
		}else {
			this.getMap().put(uuid, newData);
		}
	}
	
	/** Remove the entry
	 * @param uuid
	 * @return the value that was there if was there else null
	 * 
	 * */
	public Object removeEntry(String uuid) {
		return this.getMap().remove(uuid);
	}

	/** Write the document
	 * @param out file
	 * 
	 * */
	public void writeOut(File out) {
		ArrayList<String> lines = new ArrayList<String>(getMap().size());
		var keys = new ArrayList<String>(getMap().keySet());
		String format;
		String key;
		String data;
		for(int i=0;i<keys.size();i++) {
			key = keys.get(i);
			data = getMap().get(key).toString();
			format = key+":{"+data+"}"; 
			lines.add(format);
		}
		RW.writeAll(out, lines);
		
	}



	/**
	 * @return the map
	 */
	 HashMap<String,Object> getMap() {
		return map;
	}



	/**
	 * @param map the map to set
	 */
	void setMap(HashMap<String,Object> map) {
		this.map = map;
	}
	
	
}
