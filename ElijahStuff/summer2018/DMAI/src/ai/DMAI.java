/**
 * 
 */
package ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import play.RuneScroll;

/**
 * @author dunemask
 *
 */
public class DMAI {

	private boolean dismiss;
	private RuneScroll rs;
	private Thread processor;
	private HashMap<String,String> keywords = new HashMap<String,String>();
	
	private Runnable pr = new Runnable() {

		@Override
		public void run() {
			Scanner in = new Scanner(System.in);
			while(!dismiss) {
				System.out.println(rs.getElement("attributes", "askstyle"));
				String command = in.nextLine();
				if(command.equalsIgnoreCase(keywords.get("dismiss"))) {
					dismiss();
				}else {
					try {
					handleCommand(command);
					}catch(Exception e) {
						System.err.println("Sorry Invalid Syntax :(");
					}
				}

				
			}
			in.close();
			
		}
		
	};

	/**
	 * 
	 */
	public DMAI(RuneScroll rs) {
		this.rs = rs;
		this.dismiss = false;
		this.boot();
		processor = new Thread(pr);
		processor.start();
		
	}

	public void dismiss() {
		rs.write();
		this.dismiss = true;
	}
	/**
	 * @param cm (Command)
	 */
	protected void handleCommand(String fullCommand) {
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(fullCommand);
		while (m.find())
		    list.add(m.group(1));
		
		String[] com = list.toArray(new String[list.size()]);
		if(com[0].equalsIgnoreCase(keywords.get("set"))) {
			set(com);
		}else if(com[0].equalsIgnoreCase(keywords.get("setlong"))) {
			setlong(com);
		}else if(com[0].equalsIgnoreCase(keywords.get("echo"))) {
			if(com[1].startsWith("$")) {
				String get = com[1].substring(1, com[1].length());
				//System.out.println("patch:"+get);
				System.out.println(keywords.get(get));
			}else {
				System.out.println(fullCommand.replaceFirst(com[0]+" ", ""));
			}
		}
		
	}
	/**Set longterm command
	 * @param com
	 */
	private void setlong(String[] com) {
		if(com[1].toLowerCase().equalsIgnoreCase(keywords.get("set"))) {
			rs.changeElement("keyword","set", com[2]);
		}else if(com[1].toLowerCase().equalsIgnoreCase(keywords.get("longset"))) {
			rs.changeElement("keyword","longset", com[2]);
		}
		
		set(com);//Same On Both
	}

	public void boot() {
		keywords.put("set", rs.getElement("keyword","set"));
		keywords.put("setlong", rs.getElement("keyword","setlong"));
		keywords.put("dismiss", rs.getElement("keyword","dismiss"));
		keywords.put("echo", rs.getElement("keyword","echo"));
		
		
	}
	
	/**
	 * @param com
	 */
	private void set(String[] com) {
		if(com[1].toLowerCase().contains("greeting")) {
			rs.changeElement("attributes", "askstyle", com[2].replace("\"", ""));
			return;
		}
		if(com[1].toLowerCase().contains("name")) {
			rs.changeElement("attributes", "name", com[2].replace("\"", ""));
			return;
		}
		if(com[1].toLowerCase().contains(keywords.get("dismiss"))) {
			keywords.put("dismiss", com[2]);
			return;
		}
		if(com[1].toLowerCase().equalsIgnoreCase(keywords.get("set"))) {
			keywords.put("set", com[2]);
		}else if(com[1].toLowerCase().equalsIgnoreCase(keywords.get("longset"))) {
			keywords.put("longset", com[2]);
		}
		
	}

	
	
}
