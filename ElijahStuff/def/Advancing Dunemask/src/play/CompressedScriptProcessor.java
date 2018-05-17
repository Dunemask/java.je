/**
 * 
 */
package play;

import java.util.ArrayList;
import dunemask.util.StringUtil;

/**
 * @author dunemask
 *
 */
public class CompressedScriptProcessor {

	String fullLine;
	/** Assembles an Out Line
	 * 
	 * */
	public void assemble() {
		fullLine = "";
		for(int i=0;i<compressedRunes.size();i++) {
			CompressedRune open = compressedRunes.get(i);
			fullLine +=opencform(open.getName());
			if(open.isContainer()) {
				assembleCont((CompressedRuneSlot)open);
			}else {
				assembleElm((CompressedRuneElement)open);
			}
			fullLine+="}";
			//System.out.println(fullLine);
			
			
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	/**
	 * @param open
	 */
	private void assembleElm(CompressedRuneElement open) {
		//String op = opencform(open.getName());
		//System.out.println("OPENING:"+open.getName());
		ArrayList<String> key = new ArrayList<String>(open.getValues().keySet());
		for(int i=0;i<key.size();i++) {
			String inval = open.getValues().get(key.get(i));
			String inop = key.get(i)+":\""+inval+"\"";
			fullLine+=inop;
		}
		
	}






	/**
	 * @param open
	 */
	private void assembleCont(CompressedRuneSlot parent) {
		for(int i=0;i<parent.getChildren().size();i++) {
			CompressedRune open = parent.getChildren().get(i);
			fullLine +=opencform(open.getName());
			if(open.isContainer()) {
				assembleCont((CompressedRuneSlot)open);
			}else {
				assembleElm((CompressedRuneElement)open);
			}
			fullLine+="}";
			
			
			
			
		}
		
	}






	private String opencform(String cont) {
		return cont+":{";
	}
	
	
	
	
	
	
	
	 void printAll() {
		for(int i=0;i<compressedRunes.size();i++) {
			System.out.println(compressedRunes.get(i).getName());
			if(compressedRunes.get(i).isContainer()) {
				printStuff((CompressedRuneSlot) compressedRunes.get(i));
			}
		}
		
		
	}
	private void printStuff(CompressedRuneSlot rs) {
		for(int i=0;i<rs.getChildren().size();i++) {
			System.out.println(rs.getChildren().get(i).getName()+"FROM:"+rs.getName());
			if(rs.getChildren().get(i).isContainer()) {
				printStuff((CompressedRuneSlot) rs.getChildren().get(i));
			}
		}
	}
	
	
	
	
	private static final String regex = "\\s+(?=((\\\\[\\\\\"]|[^\\\\\"])*\"(\\\\[\\\\\"]|[^\\\\\"])*\")*(\\\\[\\\\\"]|[^\\\\\"])*$)";
	private ArrayList<CompressedRune> compressedRunes;
	
	public CompressedScriptProcessor() {
		this.setRunes(new ArrayList<CompressedRune>());
	}
	public CompressedScriptProcessor(String textAsLine) {
		this.setRunes(new ArrayList<CompressedRune>());
		this.process(textAsLine);
	}
	
	/** Remove a Rune	
	 * @param url 
	 * 
	 * */
	public void removeRune(String url) {
		String[] split = url.split("/");
		String name = split[split.length-1];
		if(split.length==1) {
			boolean found = false;
			for(int i=0;i<compressedRunes.size();i++) {
				if(compressedRunes.get(i).getName().equals(name)) {
						int was = i;
						i = compressedRunes.size();
						found = compressedRunes.remove(compressedRunes.get(was));	
				}
			}
			if(!found) {
				throw new play.CompressedInvalidRuneUrlExcpetion("COULD NOT FIND"+url);
			}

		}else {
			boolean found = false;
			int lvl = 1;
			for(int i=0;i<compressedRunes.size();i++) {
				if(compressedRunes.get(i).isContainer()) {
				found = removeRuneSub(((CompressedRuneSlot)compressedRunes.get(i)),split,lvl);
					if(found) {
						i=compressedRunes.size();
					}
				}
			}
			
			if(!found) {
				throw new play.CompressedInvalidRuneUrlExcpetion("COULD NOT FIND"+url);
			}

		
		
		}
		
	}
	
	
	/**
	 * @param compressedRuneSlot
	 * @param split
	 * @param lvl
	 * @return
	 */
	private boolean removeRuneSub(CompressedRuneSlot compressedRuneSlot, String[] split, int lvl) {
		String nameWantHere = split[lvl];
		boolean wname,container;
		boolean lim = lvl+1 == split.length;
		boolean found = false;
		for(int i=0;i<compressedRuneSlot.getChildren().size();i++) {
			wname = compressedRuneSlot.getChildren().get(i).getName().equals(nameWantHere);
			container = compressedRuneSlot.getChildren().get(i).isContainer();
			if(wname&&container) {
				lvl++;
				found = this.removeRuneSub((CompressedRuneSlot)compressedRuneSlot.getChildren().get(i), split, lvl);
			}else if(wname&&lim) {
				int was = i;
				i = compressedRuneSlot.getChildren().size();
				compressedRuneSlot.getChildren().remove(compressedRuneSlot.getChildren().get(was));
				found = true;
				
			}
			
		}
		return found;
	}

	public CompressedRune getRune(String url) {
		String[] split = url.split("/");
		String name = split[split.length-1];
		if(split.length==1) {
			CompressedRune found = null;
			for(int i=0;i<compressedRunes.size();i++) {
				if(compressedRunes.get(i).getName().equals(name)) {
						int was = i;
						i = compressedRunes.size();
						found = compressedRunes.get(was);	
				}
			}
			if(found ==null) {
				throw new play.CompressedInvalidRuneUrlExcpetion("COULD NOT FIND");
			}
			return found;
		}else {
			CompressedRune found = null;
			int lvl = 1;
			for(int i=0;i<compressedRunes.size();i++) {
				if(compressedRunes.get(i).isContainer()) {
				found = getRuneSub(((CompressedRuneSlot)compressedRunes.get(i)),split,lvl);
					if(found!=null) {
						i=compressedRunes.size();
					}
				}
			}
			if(found ==null) {
				throw new play.CompressedInvalidRuneUrlExcpetion("COULD NOT FIND "+url+" Have you created it yet?");
			}
			
			return found;
		}
		
		
	}
	private CompressedRune getRuneSub(CompressedRuneSlot compressedRuneSlot, String[] split, int lvl) {
		String nameWantHere = split[lvl];
		boolean wname,container;
		boolean lim = lvl+1 == split.length;
		//System.out.println("PATH:"+Arrays.asList(split));
		CompressedRune found=null;
		for(int i=0;i<compressedRuneSlot.getChildren().size();i++) {
			//System.out.println(compressedRuneSlot.getChildren().get(i).getName());
			wname = compressedRuneSlot.getChildren().get(i).getName().equals(nameWantHere);
			container = compressedRuneSlot.getChildren().get(i).isContainer();
			//System.out.println(nameWantHere+"!="+compressedRuneSlot.getChildren().get(i).getName());
			//System.out.println((lvl+1)+"!="+split.length);
			if(wname&&container) {
				lvl++;
				found = this.getRuneSub((CompressedRuneSlot)compressedRuneSlot.getChildren().get(i), split, lvl);
			}else if(wname&&lim) {
				int was = i;
				i = compressedRuneSlot.getChildren().size();
				return compressedRuneSlot.getChildren().get(was);
			}
			
		}
		
		
		
		return found;
		
	}
	
	
	
	public void addRune(String url) {
		boolean isCont = false;
		CompressedRune re=null;
		if(url.endsWith("/")) {
			isCont=true;
			url = StringUtil.replaceLast(url, "/", "");
		}
		String[] split = url.split("/");
		String name = split[split.length-1];
		if(!isCont) {
			re = new CompressedRuneElement(name);
		}else {
			re = new CompressedRuneSlot(name);
		}
		addRune(url,re);
	}
	
	
	public void addRune(String url,CompressedRune re) {
		String[] split = url.split("/");
		String name = split[split.length-1];
		boolean found = false;
		if(split.length==1) {
			for(int i=0;i<compressedRunes.size();i++) {
				boolean wname = compressedRunes.get(i).getName().equals(name);
				boolean container = compressedRunes.get(i).isContainer();
				if(wname&&container&&!re.isContainer()){
					throw new CompressedRuneConflictException("ATTRIBUTE IS CONTAINER");
				}else if(wname&&!container&&re.isContainer()){
					throw new CompressedRuneConflictException("ATTRIBUTE IS Element");
				}
			}
			compressedRunes.add(re);
			found = true;
		}else {
			int lvl = 1;
			for(int i=0;i<compressedRunes.size();i++) {
				if(compressedRunes.get(i).isContainer()) {
				found = addRuneSub(re,((CompressedRuneSlot)compressedRunes.get(i)),split,lvl);
					if(found) {
						i=compressedRunes.size();
					}
				}
			}
		}
		if(!found) {
			throw new CompressedRuneConflictException("COULD NOT CREATE!");
		}

		
	}
	
	private boolean addRuneSub(CompressedRune re, CompressedRuneSlot compressedRuneSlot, String[] split, int lvl) {
		String nameWantHere = split[lvl];
		boolean wname,container;
		boolean itemIsCont = re.isContainer();
		boolean lim = lvl+1 == split.length;
		boolean found=false;
		if(lim) {
			compressedRuneSlot.getChildren().add(re);
			found = true;
			return found;
		}
		for(int i=0;i<compressedRuneSlot.getChildren().size();i++) {
			wname = compressedRuneSlot.getChildren().get(i).getName().equals(nameWantHere);
			container = compressedRuneSlot.getChildren().get(i).isContainer();
			if(wname&&container&&lim&&!itemIsCont){
				throw new CompressedRuneConflictException("ATTRIBUTE IS CONTAINER");
			}else if(wname&&!container&&lim&&itemIsCont){
				throw new CompressedRuneConflictException("ATTRIBUTE IS Element");
			}else if(wname&&container&&!lim) {
				lvl++;
				found = this.addRuneSub(re, (CompressedRuneSlot)compressedRuneSlot.getChildren().get(i), split, lvl);
			}
			
		}
		
		
		
		return found;
		
	}
	
	
	
	
	/** Call this
	 * 
	 * */
	public void process(String str) {
		str = str.replaceAll(regex, "");
		//System.out.println(str);
		String full = str;
		String change = full;
		while(change.contains("}")) {
			//System.out.println("LINE:"+change);
			int ind = change.indexOf(":{");
			if(ind!=-1) {
				String name = (change.substring(0, ind));
				boolean st = false;
				int counter=0;
				int want = 0;
				for(int i=0;i<change.length();i++) {
					if(change.charAt(i)=='{') {
						if(!st) {
							st = true;
						}
						counter++;
					}else if(change.charAt(i)=='}') {
						counter--;
					}
					
					if(counter==0&&st) {
							st = false;
							want=i;
							i=change.length();
					}
				}
				String attr  = change.substring(0,want+1);
				//System.out.println("ATTR:"+attr);
				change= change.substring(change.indexOf(attr)+attr.length(),change.length());
				if(change.startsWith("}")) {
					change = change.replaceFirst("}", "");
				}
				String alpha = StringUtil.replaceLast(attr, "}", "");
				alpha = alpha.substring(alpha.indexOf("{")+1, alpha.length());
				boolean container = alpha.contains("{");
				if(container) {
					//System.out.println("INPUT:"+alpha);
					CompressedRuneSlot rs = mapCont(name,alpha);
					this.compressedRunes.add(rs);
				}else {
					CompressedRuneElement re = mapElm(name,alpha);
					this.compressedRunes.add(re);
					
				}
			}
		}
		
	}
	/*private Rune contProcess(Rune r,String s) {
		for(int i=0;i<s.length();i++) {
			int ind = s.indexOf(":{");
			if(ind!=-1) {
				String name = (s.substring(i, ind));
				boolean container = (s.substring(ind+2,s.indexOf("}")).contains("{"));
				if(container) {
					RuneSlot rs = new RuneSlot(name);
					rs = contProcess(rs,s.substring(ind+2,s.indexOf("}")));	
				}else {
					
				}
			}
		}
		
	}*/

	/**
	 * @param name
	 * @param tmp
	 * @return
	 */
	private CompressedRuneSlot mapCont(String topname, String str) {
		CompressedRuneSlot out = new CompressedRuneSlot(topname);
		String full = str;
		String change = full;
		while(change.contains("}")) {
			//System.out.println("LINE:"+change);
			int ind = change.indexOf(":{");
			if(ind!=-1) {
				String name = (change.substring(0, ind));
				boolean st = false;
				int counter=0;
				int want = 0;
				for(int i=0;i<change.length();i++) {
					if(change.charAt(i)=='{') {
						if(!st) {
							st = true;
						}
						counter++;
					}else if(change.charAt(i)=='}') {
						counter--;
					}
					
					if(counter==0&&st) {
							st = false;
							want=i;
							i=change.length();
					}
				}
				String attr  = change.substring(0,want+1);
				//System.out.println("ATTR:"+attr);
				change= change.substring(change.indexOf(attr)+attr.length(),change.length());
				if(change.startsWith("}")) {
					change = change.replaceFirst("}", "");
				}
				String alpha = StringUtil.replaceLast(attr, "}", "");
				alpha = alpha.substring(alpha.indexOf("{")+1, alpha.length());
				boolean container = alpha.contains("{");
				if(container) {
					//System.out.println("INPUT:"+alpha);
					CompressedRuneSlot rs = mapCont(name,alpha);
					out.getChildren().add(rs);
				}else {
					CompressedRuneElement re = mapElm(name,alpha);
					out.getChildren().add(re);
					
				}
			}
		}
		
		
		return out;
	}

	/**
	 * @param name
	 * @param s
	 * @return 
	 */
	private CompressedRuneElement mapElm(String name, String s) {
		//System.out.println(s);
		CompressedRuneElement re = new CompressedRuneElement(name);
		String nt = s.substring(s.indexOf(":{")+1,s.length());
		//System.out.println("NT:"+nt);
			while(nt.contains("\"")) {
				String intname = nt.substring(0,nt.indexOf(":"));
				int begind = nt.indexOf(":\"")+2;
				String ns = nt.substring(begind,nt.length());
				String val = ns.substring(0,ns.indexOf("\""));
				//System.out.println("NAME:"+intname+", VAL:"+val);
				re.getValues().put(intname,val);
				String rep = intname+":\""+val+"\"";
				//System.out.println("REP:"+rep);
				nt = nt.replace(rep, "");
				//System.out.println("S:"+nt);
			}
			//System.out.println("Done:"+re.getValues());
		return re;
		
	}

	/**
	 * @return the runes
	 */
	public ArrayList<CompressedRune> getRunes() {
		return compressedRunes;
	}

	/**
	 * @param compressedRunes the runes to set
	 */
	public void setRunes(ArrayList<CompressedRune> compressedRunes) {
		this.compressedRunes = compressedRunes;
	}

}
