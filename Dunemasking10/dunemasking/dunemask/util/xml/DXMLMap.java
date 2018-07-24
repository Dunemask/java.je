package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;

import dunemask.util.IOUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

class DXMLMap {
	private static ArrayList<String> newLines = new ArrayList<String>();
	
	
	public void writeOut(File f) {
		this.update();
		f.delete();
		//System.out.println(f.getAbsolutePath()+"HERE");
		//System.out.println("^^");
		RW.writeAll(f, newLines);
		//System.out.println("^^");
	}
	public void writeOut() {
		this.writeOut(this.file);
	}
	
	public Attr addElement(String url,Object val) {
		//System.out.println("Wants to add:"+url);
		if(url.endsWith("/")) {
			url = url.substring(0,url.length()-1);
		}
		Attr child = new Attr(url,String.valueOf(val));
		boolean found = false;
		for(int i=0;i<this.attributes.size();i++) {
			Attr cur = attributes.get(i);
			//System.out.println(cur.getUrl()+"!="+child.getParent());
			if(cur.isContainer()) {
				if(cur.getUrl().equals(child.getParent())) {
					attributes.get(i).addChild(child);
					found = true;
					i=this.attributes.size();

				}else{
				found = itterFind(cur,child);
				if(found) {
					i=this.attributes.size();
				}
				}
			}
		}

		
		if(this.tabsNeeded(child)>0&&!found) {
			//System.out.println("Sorry You need to create the other things first :(");
		}else if(this.tabsNeeded(child)==0){
			this.attributes.add(child);
			found = true;
		}
		if(found) {
			//this.update();
			return child;
		}else {
			return null;
		}
		
	}
	
	public String getVal(String url) {
		return getAttr(url).getValue();
		
	}
	
	boolean itemIsContainer(String url) {
		return getAttr(url).isContainer();
		
		
	}

	public boolean itemExists(String url) {
		//System.out.println(this.getAttr(url)+"@From "+url);
		return this.getAttr(url)!=null;

		
		
		
	}
	
	
	
	Attr getAttr(String url) {
		Attr child = new Attr(url);
		Attr found = null;
		for(int i=0;i<this.attributes.size();i++) {
			Attr cur = attributes.get(i);
			if(child.getParent()==null&&cur.getUrl().equals(child.getUrl())) {
				found = (attributes.get(i));
				i=this.attributes.size();
			}else if(cur.isContainer()) {
				if(cur.getUrl().equals(child.getParent())) {
					found = attributes.get(i).getChild(child.getUrl());
					i=this.attributes.size();
				}else{
				found = findAttr(cur,child);
				if(found!=null) {
					i=this.attributes.size();
				}
				
				}
			}
		}
		
		
		return found;
		
	}
	
	
	
	
	
	
	
	public void removeAttr(String url) {
		
		Attr child = new Attr(url);
		boolean found = false;
		for(int i=0;i<this.attributes.size();i++) {
			Attr cur = attributes.get(i);
			if(child.getParent()==null&&cur.getUrl().equals(child.getUrl())) {
				attributes.remove(attributes.get(i));
				found = true;
				i=this.attributes.size();
			}else if(cur.isContainer()) {
				if(cur.getUrl().equals(child.getParent())) {
					attributes.get(i).removeChild(child);

					found = true;
					i=this.attributes.size();
				}else{
				found = findRem(cur,child);
				if(found) {
					i=this.attributes.size();
				}
				
				}
			}
		}
		if(found) {
		//this.update();
		}else {
			//System.out.println("DID't INFD "+url);
		}
	}
	
	private Attr findAttr(Attr at,Attr elm) {
		Attr found = null;
		//System.out.println(" URL FOR PAR "+at.getUrl());
		if(at.getUrl().equals(elm.getParent())) {
			return at.getChild(elm.getUrl());
		}else if(at.isContainer()) {
				for(int i=0;i<at.getChildren().size();i++) {
					//System.out.println(toadd.getParent());
						found = findAttr(at.getChildren().get(i),elm);
						if(found!=null) {
							//found = at.getChild(elm.getUrl());
							//System.out.println("Wants to return:"+found);
							i=at.getChildren().size();
						}
			}
		}else {
			//System.out.println("ATTR:"+at.getUrl()+"IS NOT A CONTAINER");
		}
		return found;
	}
	
	
	
	private boolean findRem(Attr at,Attr rmv) {
		boolean found = false;
		//System.out.println(" URL FOR PAR "+at.getUrl());
		if(at.isContainer()) {
			
			if(at.getUrl().equals(rmv.getParent())) {
				//System.out.println("Adding:"+toadd.getUrl()+"@TO:"+at.getUrl());
				at.removeChild(rmv);
				//System.out.println("Upward");
				for(int i=0;i<at.getChildren().size();i++) {
					
				}
				
				return true;
			}else {
				for(int i=0;i<at.getChildren().size();i++) {
					//System.out.println(toadd.getParent());
						if(at.getUrl().equals(rmv.getParent())) {
							//System.out.println("Adding:"+toadd.getUrl()+"@TO:"+at.getUrl());
							at.removeChild(rmv);
							return true;
						}else {
							found = findRem(at.getChildren().get(i),rmv);
						if(found==true) {
							return true;
						}
						}
				}
			}
		}else {
			//System.out.println("ATTR:"+at.getUrl()+"IS NOT A CONTAINER");
		}
		return found;
	}
		

	
	
	
	
	public Attr addContainer(String url) {
		if(!url.endsWith("/")) {
			url+="/";
		}
		Attr child = new Attr(url);
		//System.out.println("CHILD: "+url+" IS CONTAINER: "+child.isContainer());
		boolean found = false;
		for(int i=0;i<this.attributes.size();i++) {
			Attr cur = attributes.get(i);
			if(cur.isContainer()) {
				if(cur.getUrl().equals(child.getParent())) {
					attributes.get(i).addChild(child);
					found = true;
					i=this.attributes.size();
					//System.out.println("Found");
				}else{
				found = itterFind(cur,child);
				if(found) {
					i=this.attributes.size();
				}
				}
			}
		}
		if(this.tabsNeeded(child)>0&&!found) {
			throw new DMXMLException("Sorry You need to create the other things first :(");
		}else if(this.tabsNeeded(child)==0){
			this.attributes.add(child);
			found = true;
		}
		if (found){
			//this.update();
			return child;
		}else {
			return null;
		}
	}
	
	
	
	
	private boolean itterFind(Attr at,Attr toadd) {
		boolean found = false;
		//System.out.println(" URL FOR PAR "+at.getUrl());
		if(at.isContainer()) {
			
			if(at.getUrl().equals(toadd.getParent())) {
				//System.out.println("Adding:"+toadd.getUrl()+"@TO:"+at.getUrl());
				at.addChild(toadd);
				return true;
			}else {
				for(int i=0;i<at.getChildren().size();i++) {
					//System.out.println(toadd.getParent());
						if(at.getUrl().equals(toadd.getParent())) {
							//System.out.println("Adding:"+toadd.getUrl()+"@TO:"+at.getUrl());
							at.addChild(toadd);
							return true;
						}else {
							found = itterFind(at.getChildren().get(i),toadd);
						if(found==true) {
							return true;
						}
						}
				}
			}
		}else {
			//System.out.println("ATTR:"+at.getUrl()+"IS NOT A CONTAINER");
		}
		return found;
	}

	public void printNewLines() {
		for(int i=0;i<newLines.size();i++) {
		System.out.println(newLines.get(i));
		}
	}
	
	
	public void update() {
		newLines = new ArrayList<String>();
		for(int i=0;i<attributes.size();i++) {
			itter(attributes.get(i));
		}
		
	}
	private void itter(Attr at) {
		makeOp(at);
		if(at.isContainer()) {
			for(int i=0;i<at.getChildren().size();i++) {
				itter(at.getChildren().get(i));
			}
			makeClose(at);
		}
		
	}
	
	private void makeClose(Attr at) {
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
	private void makeOp(Attr at) {
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
	/**For write*/
	private  int tabsNeeded(Attr at) {
		int tabs=0;
		String cur = at.getUrl();
		//System.out.println(cur);
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
	
	
	
	
	private ArrayList<String> lines = new ArrayList<String>();
	private ArrayList<Attr> attributes = new ArrayList<Attr>();
	/**
	 * @return the lines
	 */
	public ArrayList<String> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}

	/**
	 * @return the attributes
	 */
	 ArrayList<Attr> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	void setAttributes(ArrayList<Attr> attributes) {
		this.attributes = attributes;
	}




	File file;
	
	private String parseElement(String str) {
		str = str.replace(StringUtil.tab, "");
		int opcharind = str.indexOf("</");
		if(opcharind!=-1) {
			str = str.substring(opcharind+2);
		}else {
			str = str.replaceFirst("<","");
		}
		int clchar = str.indexOf(">");

		
		if(clchar!=-1) {
		str = str.substring(0,clchar);
		}
		return str;
	}
	
	
	private String cle(String str) {
		return "</"+str+">";
	}
	private String oe(String str) {
		return "<"+str+">";
		
	}
	
	
	public static DXMLMap ParseMap(File file) {
		DXMLMap m = new DXMLMap();
		m.file = file;
		m.lines = RW.readAll(IOUtil.FTU(file));
		m.parse();	
		return m;
		
	}
	
	public DXMLMap() {
		
	}
	
	/** Create new Map
	 * 
	 * */
	public DXMLMap(File file) {
		this.file = file;
		
	/*for(int i=0;i<this.attributes.size();i++) {
		Attr cur = this.attributes.get(i);

		printAll(cur);
		
	}*/
	
	}



	private int tabCount(String elm) {
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
	
	
	
	private int findClose(int min,int max,String elm,int cl) {
		String orig = elm;
		elm = this.parseElement(elm);
		String wantedClose = this.cle(elm);
		////System.out.println("ELM:____"+elm+"-------Wants:"+wantedClose);
		int tabNeed = this.tabCount(orig);
		int line = -1;
		///Iterate all
		for(int i=min;i<max;i++) {
			String curel = this.parseElement(lines.get(i));
			String closcurel = this.cle(curel);
			boolean rightelm = closcurel.equals(wantedClose)&&i!=cl;
			boolean rtab = tabNeed == this.tabCount(lines.get(i));
			////System.out.println(closcurel+"!="+wantedClose+"():"+rightelm+" HAS TABS:"+tabNeed+"GOT TABS:"+this.tabCount(lines.get(i)));
			if(rtab&&rightelm) {
				line = i;
				i=max;
			}

		}
		boolean tried = tryElement(orig);
		if(tried&&!orig.replace(StringUtil.tab, "").equals(this.cle(elm))) {
			return cl;
		}
		
		return line;
		
	}
	
	
	
	
	/**
	 * @param elm
	 */
	private boolean tryElement(String elm) {
		String actElm = this.parseElement(elm);
		int la = elm.lastIndexOf("</");
		if(la!=-1) {
			la+=2;
		}else {
			return false;
		}
		elm = elm.substring(la, elm.length()-1);
		if(this.parseElement(elm).equals(actElm)) {
			return true;
		}else {
			return false;
		}
		
		
		
	}

	private String parseValue(String fullLine) {
		int tabCount = this.tabCount(fullLine);
		for(int i=0;i<tabCount;i++) {
			fullLine = fullLine.replaceFirst(StringUtil.tab, "");
		}
		
		int beg = fullLine.indexOf(">")+1;
		int end = fullLine.indexOf("<", 2);
		return fullLine.substring(beg, end);
		
		
	}

	/**
	 * @param lines2
	 */
	private void parse() {
		for(int i=0;i<lines.size();i++) {
			String curLine = lines.get(i);
			if(this.tabCount(curLine)==0) {
				int closeLoc = this.findClose(i, lines.size(), curLine,i);
				if(closeLoc!=-1&&closeLoc==i) {
					//Element
					String elmForm = this.parseElement(curLine);
					Attr celm = new Attr(elmForm,this.parseValue(curLine));
					this.attributes.add(celm);
				}else if(closeLoc!=-1) {
					//Container
					////System.out.println("DONE WIF FIRST!++++++++++++++++++++++++++++");
					this.attributes.add(this.map(this.parseElement(curLine),i+1,closeLoc));
					
				}
			
			
			
			}
			
		}
		
		
		
	}





	/**
	 * @param elm
	 * @param start
	 * @param close
	 */
	private Attr map(String elm, int start, int close) {
		if(!elm.endsWith("/")) {
			elm+="/";
		}
		Attr cont = new Attr(elm);
		int tbcount = this.tabCount(lines.get(start));
		////System.out.println(tbcount+"@from:"+lines.get(start));
		////System.out.println("CUR:"+elm);
		////System.out.println(lines.get(start));
		////System.out.println(lines.get(close-1 ));
		for(int i=start;i<close+1;i++) {
			String curLine = lines.get(i);
				if(tbcount==this.tabCount(curLine)) {
					int closeLoc = this.findClose(i, close, curLine,i);
					////System.out.println(lines.get(i)+"@return"+closeLoc);
					if(closeLoc!=-1&&closeLoc==i) {
						//Element
						String elmForm = this.parseElement(curLine);
						Attr celm = new Attr(elm+elmForm,this.parseValue(curLine));
						cont.addChild(celm);
					}else if(closeLoc!=-1) {
						//Container
						String elmForm = this.parseElement(curLine);
						////System.out.println("ELM@I:"+curLine);
						////System.out.println("Wants to Map:"+elm+elmForm);
						////System.out.println("Will Find Lines"+Arrays.asList(RW.read(file, i,closeLoc)));
						Attr child = (this.map(elm+elmForm,i+1,closeLoc));
						cont.addChild(child);
					}	
				}else {
					////System.out.println("Line was:"+curLine+" @WIF:"+this.tabCount(curLine));
				}
			
			
			
			}

		return cont;
		
	}

}
