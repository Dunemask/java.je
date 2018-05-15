package xml;

import java.util.ArrayList;
import java.util.HashMap;

import dunemask.util.StringUtil;

class Rune {

	private String url;
	private ArrayList<Rune> children;
	private boolean container;
	private String value;
	private String name;
	private String parent;
	private String fullLine;
	/** This Value will be blank if it was a double value type
	 * @see NR.value
	 * 
	 * */
	private HashMap<String,String> sub = new HashMap<String,String>();

	/*public Rune(String url,String val) {
		this.url=url;
		this.value = val;
		this.container=false;
		this.name = this.deriveName();
		this.parent = (this.deriveParent());
	}*/
	
	
	public Rune(String url,String fullLine) {
		this.url = url;
		this.children = new ArrayList<Rune>();
		if(url.endsWith("/")) {
			this.container=true;
		}else {
			this.container=false;
		}
		this.name = this.deriveName();
		this.parent = (this.deriveParent());
		this.fullLine = fullLine;
		this.solve(this.fullLine);
		
	}
	public Rune(String url) {
		this.url = url;
		this.children = new ArrayList<Rune>();
		if(url.endsWith("/")) {
			this.container=true;
		}else {
			this.container=false;
		}
		this.name = this.deriveName();
		this.parent = (this.deriveParent());
		
	}

	public void solve(String fullLine) {
		fullLine = fullLine.replace(StringUtil.tab, "");
		boolean sval = fullLine.endsWith("/>");
		boolean dval = fullLine.contains("</");
		if(!sval||!dval) {
			this.container = true;
		}
		System.out.println("****");
		var scheck = fullLine.indexOf(" ")!=-1;
		int vert = 0;
		if(!scheck) {
			vert = fullLine.indexOf(">");
		}else {
			vert = fullLine.indexOf(" ");
		}
		
		String name = fullLine.substring(fullLine.indexOf("<")+1,vert);
		String tmp=fullLine.substring(name.length()+1);
		String altTmp=tmp;
			for(int i=name.length()+1;i<tmp.length();i++) {
				tmp = tmp.substring(tmp.indexOf(" ")+1);
				String sname = null;
				String val = null;
				if(tmp.contains("=\"")) {
					sname = tmp.substring(0, tmp.indexOf("=\""));
					tmp = tmp.substring(tmp.indexOf("=\"")+2,tmp.length());
					val = tmp.substring(0,tmp.indexOf("\""));
					tmp = tmp.substring(tmp.indexOf("\"")+1,tmp.length());
					this.sub.put(sname, val);
				}
				
				
			}
			if(dval&&!altTmp.equals(">")) {
			altTmp = altTmp.substring(0,altTmp.lastIndexOf("</"));
			altTmp = altTmp.substring(altTmp.indexOf(">")+1,altTmp.length());
			this.value = altTmp;
			}else if(dval) {
				//throw new RuntimeException("You should probably catch the second one here");
			}
		
		if(this.name.equals(name)&&!this.name.equals("tmp")) {
			System.out.println("You should probably check names"+"\r\n Act:"+this.name+" Alt:"+name);
		}else {
			this.name = name;
		}
		
	}
	
	public void addChild(Rune child) {
		this.children.add(child);
	}
	
	
	
	public String getUrl() {
		return url;
	}


	public ArrayList<Rune> getChildren() {
		return children;
	}
	public Rune getChild(String url){
		Rune at = null;
		if(this.isContainer()) {	
			for(int i=0;i<this.getChildren().size();i++) {
				if(this.getChildren().get(i).getUrl().equals(url)) {
					at = this.getChildren().get(i);
					i=this.getChildren().size();
				}
			}
		}else {
			return null;
		}	
		return at;
		
	}
	
	
	public boolean removeChild(Rune at) {
		//System.out.println("Wants to rmv:"+at.getUrl());
		boolean ret = false;
		for(int i=0;i<this.children.size();i++) {
			Rune cur = this.children.get(i);
			if(at.getUrl().equals(cur.getUrl())) {
				ret = 	this.children.remove(cur);
				i=this.children.size();
			}
		}
		
		return ret;
	}


	public void addChildren(ArrayList<Rune> children) {
		this.children.addAll(children);
	}



	public boolean isContainer() {
		return container;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}

	private String deriveName() {
		String ur = this.url;
		if(ur.endsWith("/")) {
			ur = StringUtil.replaceLast(ur, "/", "");
		}
		
		
		
		int lastind = ur.lastIndexOf("/");
		return ur.substring(lastind+1);
		
	}
	
	private String deriveParent() {
		String ur = this.url;
		int nameInd = ur.lastIndexOf(this.deriveName());
		if(nameInd==0) {//If It has no parent
			return null;
		}
		/*nameInd--;
		String newUr = ur.substring(0,nameInd);
		int lastind = newUr.lastIndexOf("/");
		newUr = newUr.substring(lastind+1);
		return newUr;*/ //Remove All but name
		return ur.substring(0,nameInd);
		
	}
	public String getParent() {
		return parent;
	}
	/**
	 * @return the sub
	 */
	public HashMap<String,String> getSub() {
		return sub;
	}


	/**
	 * @param sub the sub to set
	 */
	public void setSub(HashMap<String,String> sub) {
		this.sub = sub;
	}


}
