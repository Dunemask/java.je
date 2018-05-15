/**
 * 
 */
package scapitals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import dunemask.util.StringUtil;
import dunemask.util.rw.RW;
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
		Random r = new Random();
		//Quiz count number
		for(int i=1;i<=studentcount;i++) {
			ArrayList<String> altList = new ArrayList<String>(states);
			java.util.Collections.shuffle(altList);
			ArrayList<String> lines = new ArrayList<String>();
			lines.add("Name:");
			lines.add("Date:");
			lines.add(StringUtil.tab+"States and Capitals Test");
			File out = new File(testpath+"Test "+i+".txt");
			File keyOut = new File(testpath+"TestKey "+i+".txt");
			ArrayList<String> keyLines = new ArrayList<String>();
			for(int c=0;c<altList.size();c++) {
				String cstate = altList.get(c);
				String rcap = map.getvalue(cstate);
				
				ArrayList<String> altCaps = new ArrayList<String>(caps);
				Collections.shuffle(altCaps);
				ArrayList<String> answers = new ArrayList<String>();
				answers.add(rcap);
				altCaps.remove(rcap);
				for(int a=0;a<wrongAnswerCnt;a++) {
					String tmp = altCaps.get(r.nextInt(altCaps.size()));
					answers.add(tmp);
					altCaps.remove(tmp);	
				}
				Collections.shuffle(answers);
				int num = answers.indexOf(rcap);
				/*System.out.println("State:"+cstate);
				System.out.println("A:"+answers.get(0));
				System.out.println("B:"+answers.get(1));
				System.out.println("C:"+answers.get(2));
				System.out.println("D:"+answers.get(3));*/
				lines.add(c +".  State: "+cstate);
				lines.add("A:"+answers.get(0));
				lines.add("B:"+answers.get(1));
				lines.add("C:"+answers.get(2));
				lines.add("D:"+answers.get(3));
				lines.add(System.lineSeparator());
				keyLines.add(Arrays.asList("ABCD".toCharArray()).get(num)+"");
				
				
				
				
			}
			RW.writeAll(out, lines.toArray(new String[lines.size()]));
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
	}
}
