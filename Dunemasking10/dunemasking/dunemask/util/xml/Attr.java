package dunemask.util.xml;

import java.util.ArrayList;

import dunemask.util.StringUtil;

class Attr {

	private String url;
	private ArrayList<Attr> children;
	private boolean container;
	private String value;
	private String name;
	private String parent;

	public Attr(String url,String val) {
		this.url=url;
		this.value = val;
		this.container=false;
		this.name = this.deriveName();
		this.parent = (this.deriveParent());
	}
	
	
	public Attr(String url) {
		this.url = url;
		this.children = new ArrayList<Attr>();
		if(url.endsWith("/")) {
			this.container=true;
		}else {
			this.container=false;
		}
		this.name = this.deriveName();
		this.parent = (this.deriveParent());
		
	}

	
	public void addChild(Attr child) {
		this.children.add(child);
	}
	
	
	
	public String getUrl() {
		return url;
	}


	public ArrayList<Attr> getChildren() {
		return children;
	}
	public Attr getChild(String url){
		Attr at = null;
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
	
	
	public boolean removeChild(Attr at) {
		//System.out.println("Wants to rmv:"+at.getUrl());
		boolean ret = false;
		for(int i=0;i<this.children.size();i++) {
			Attr cur = this.children.get(i);
			if(at.getUrl().equals(cur.getUrl())) {
				ret = 	this.children.remove(cur);
				i=this.children.size();
			}
		}
		
		return ret;
	}


	public void addChildren(ArrayList<Attr> children) {
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



}
