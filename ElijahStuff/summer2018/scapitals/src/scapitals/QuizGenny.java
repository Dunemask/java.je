/**
 * 
 */
package scapitals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import dunemask.util.StringUtil;
import dunemask.util.xml.Runemap;

/**
 * @author Dunemask
 *
 */
public class QuizGenny {

	public static void main(String[] args) throws URISyntaxException {
		/*File caps = ResourceUtil.writeUrl(QuizGenny.class.getResource("/scapitals/Caps.txt"));
		File states = ResourceUtil.writeUrl(QuizGenny.class.getResource("/scapitals/States.txt"));
		String[] clines = RW.readAll(caps);
		String[] slines = RW.readAll(states);*/
		int studentcount = 35;
		File drm = new File(QuizGenny.class.getResource("/scapitals/sandc.drm").getFile());
		Runemap map = Runemap.parseRunemap(drm);
		String dtop = System.getProperty("user.home")+"/Desktop/";
		String testpath = dtop+"tests/";
		new File(testpath).mkdirs();
		ArrayList<String> states = new ArrayList<String>(map.getFullMap().keySet());
		ArrayList<String> caps = new ArrayList<String>(map.getFullMap().values());
		for(int i=0;i<states.size();i++) {
			System.out.println(states.get(i)+","+caps.get(i));
		}
		int wrongAnswerCnt = 3;
		int rightAnswerCnt = 1;
		int statecount = 50;
		
		for(int i=1;i<=studentcount;i++) {
			ArrayList<String> altList = new ArrayList<String>(states);
			java.util.Collections.shuffle(altList);
			ArrayList<String> lines = new ArrayList<String>();
			lines.add("Name:");
			lines.add("Date:");
			lines.add(StringUtil.tab+"States and Capitals Test");
			File out = new File(testpath+"Test "+i);
			for(int c=0;c<statecount;c++) {
				
			}
			
			
			
			
			
			
			
			
		}
		
		

		
		
		
		
		
		
		
		
	}
}
