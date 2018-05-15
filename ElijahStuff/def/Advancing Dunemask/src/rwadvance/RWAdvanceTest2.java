package rwadvance;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import dunemask.util.FileUtil;
import dunemask.util.RW;

public class RWAdvanceTest2 {

	public static void main(String[] args) {
		String dtop = System.getProperty("user.home")+"/Desktop/";
		String fpath = dtop+"rwtest.txt";
		File file = new File(fpath);
		String ls = System.lineSeparator();
		String text = "temp"+ls;
		/*for(int i=0;i<20000;i++) {
			text= text + "temp"+ls;
		}*/

		try {
			/*long start = Calendar.getInstance().getTimeInMillis();
			System.out.println(RWAlt.lineCount(file.toURI().toURL()));
			long sec =Calendar.getInstance().getTimeInMillis()-start;
			System.out.println(sec);*/
			//System.out.println(RWAlt.readAll(file.toURI().toURL()));
			var x = new ArrayList<String>(Arrays.asList(new String[] {"Turtle","Green","Alt","walrus"}));
			//RWAlt.write(file,x,5);
			RW.writeAll(file, x);
			/*var lines = RWAlt.readAll(file.toURI().toURL());
			for(String s:lines) {
				System.out.println(s);
			}
			System.out.println("End");*/
		/*	start = Calendar.getInstance().getTimeInMillis();
			System.out.println(RWAlt.countLines(file.toURI().toURL()));
			sec = Calendar.getInstance().getTimeInMillis()-start;
			System.out.println(sec);*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
