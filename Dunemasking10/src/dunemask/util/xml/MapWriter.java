/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;

import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author dunemask
 *
 */
public class MapWriter {

	private static ArrayList<String> newLines = new ArrayList<String>();
	
	private static String cle(String str) {
		return "</"+str+">";
	}
	private static String oe(String str) {
		return "<"+str+">";
		
	}
	
	public static void write(DXMLMap map, File out) {
		newLines = new ArrayList<String>();
		for(int i=0;i<map.attributes.size();i++) {
			itter(map.attributes.get(i));
		}
		for(int i=0;i<newLines.size();i++) {
			System.out.println(newLines.get(i));
		}
		RW.writeAll(out, newLines.toArray(new String[newLines.size()]));
	}
	
	private static void itter(Attr at) {
		makeOp(at);
		if(at.isContainer()) {
			for(int i=0;i<at.getChildren().size();i++) {
				itter(at.getChildren().get(i));
			}
			makeClose(at);
		}
		
	}
	
	private static void makeClose(Attr at) {
		String tab = "";
		int tabs = tabsNeeded(at);
		for(int i=1;i<=tabs;i++) {
			tab+=StringUtil.tab;
		}
		String first = tab+cle(at.getName());
		newLines.add(first);
	}
	
	/**
	 * @param at
	 */
	private static void makeOp(Attr at) {
		String tab = "";
		int tabs = tabsNeeded(at);
		for(int i=1;i<=tabs;i++) {
			tab+=StringUtil.tab;
		}
		String first = tab+oe(at.getName());
		if(at.isContainer()) {
			newLines.add(first);
		}else {
			String second = cle(at.getName());
			newLines.add(first+at.getValue()+second);
		}
		
	}
	private static int tabsNeeded(Attr at) {
		int tabs=0;
		String cur = at.getUrl();
		System.out.println(cur);
		if(at.getUrl().endsWith("/")) {
		//If Container add it and remove it for checks
			cur = StringUtil.replaceLast(cur, "/", "");
		}
		while(!cur.equals(at.getName())) {
			int ind = cur.indexOf("/");
			cur = cur.substring(ind+1);
			tabs++;
		}
		
		
		
		return tabs;
		
	}


	
}
