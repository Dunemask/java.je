package xml;

import java.io.File;
import java.util.ArrayList;

import dunemask.util.RW;
import dunemask.util.StringUtil;

public class FullBlown {
		static ArrayList<String> lines;
		static ArrayList<NR> runes;
	public static void main(String[] args) {
		lines = new ArrayList<String>();
		lines.add("<Parent id=\"Potato\" altid=\"yup\"> ");
		lines.add(StringUtil.tab+"<RuneKey id=\"potato soup\"/>");
		lines.add(StringUtil.tab+"<RuneKey  id=\"ftw\">value</RuneKey>");
		lines.add("</Parent>");
		/*for(String s:lines) {
			System.out.println(s);
		}*/
		File out = new File(System.getProperty("user.home")+"/Desktop/Out.txt");
		//RW.writeAll(out, lines);
		var mp = XMap.ParseMap(out);
		var ls = mp.getAttributes();
		System.out.println(ls);
		//Parse o.-
		/*int counter =1;
		for(int i=0;i<lines.size();i++) {
			int tb = tabCount(lines.get(i));
			if(tb==0) {
				NR tmp = new NR(counter);		
				tmp.solve(lines.get(i));
				if(tmp.isContainer()) {
					int nl = findNext(i,tmp);
				}
				counter++;
			}
			
			
			
		}*/

	}
	
	
	/**
	 * @param tabs
	 * @param tmp
	 * @return
	 */
	private static int findNext(int tabs, NR tmp) {
		
		
		
		
		
		return 0;
	}


	private static int tabCount(String elm) {
		String orig = elm;
		String want=elm.replace(StringUtil.tab, "");
		int count=0;
		////System.out.println((orig!=want)+" elm:"+elm);
		while(orig!=want&&orig.charAt(0)!='<') {
			orig=orig.replaceFirst(StringUtil.tab, "");
			++count;
		}
		return count;
	}

}
