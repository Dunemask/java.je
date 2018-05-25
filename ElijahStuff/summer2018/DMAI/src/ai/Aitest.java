/**
 * 
 */
package ai;

import java.io.File;
import java.util.Calendar;

import dunemask.util.ResourceUtil;
import play.RuneScroll;

/**
 * @author dunemask
 *
 */
public class Aitest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//RuneScroll in = RuneScroll.ParseScroll(new File("src/ai/record.crm"));
		RuneScroll in = new RuneScroll(new File("src/ai/record.crm"));
		in.addObject("attributes");
		in.addElement("attributes", "name", null);
		in.addElement("attributes", "askstyle","Grettings I Am "+in.getElement("attributes", "name")+" What Can I Do For You?");
		in.addObject("keyword");
		in.addElement("keyword", "set", "set");
		in.addElement("keyword", "dismiss", "dismiss");
		in.addElement("keyword", "setlong", "export");
		in.addElement("keyword", "echo", "echo");
		in.write();
		System.out.println("Finished writing");
		//RuneScroll rsout = RuneScroll.ParseScroll(ResourceUtil.writeUrl(DMAI.class.getResource("/ai/record.crm")));
		
		DMAI ai = new DMAI(in);

	}

}
