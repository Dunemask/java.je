import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import dunemask.objects.ArrayListState;
import dunemask.util.xml.XMLMap;
import dunemask.util.xml.XMLRW;

/**
 * 
 */

/**
 * @author dunemask
 *
 */
public class XMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//fullChar();
		sframe();
		//tframe();
		//frame();
		
		
	}

	/**
	 * 
	 */
	private static void sframe() {
		File file = new File(System.getProperty("user.home")+"/Desktop/Frame.xml");
		int x=10;
		int y=20;
		int w=100;
		int h=200;
		String title = "Test!";
		XMLMap mp = new XMLMap(file,"JFrame");
		//mp.addContainer(title,mp.getLastParent());
		//mp.addContainer("dim", mp.getLastParent());
		//mp.addContainer("attr", mp.getParentByState("JFrame"));
		ArrayList<String> conts = new ArrayList<String>();
		conts.add("dim");
		conts.add("attr");
		mp.addContainers(conts, mp.getParentByState("JFrame"));
		mp.setLastState("dim");
		mp.addElement("X", mp.getParentByState(mp.getLastState()), "23");
		mp.addElement("Y", mp.lastParent(), "42");
		mp.setLastState("attr");
		ArrayList<String> els = new ArrayList<String>();
		els.add("Taco");
		els.add("HotDog");
		ArrayList<String> vals = new ArrayList<String>();
		vals.add("\"The Best\"");
		vals.add("\"Almost The Best\"");
		mp.addElements(els,mp.lastParent(), vals);
	}

	/**
	 * 
	 */
	private static void tframe() {
		int x=10;
		int y=20;
		int w=100;
		int h=200;
		String title = "Test!";
		ArrayList<String> holder = new ArrayList<String>();
		ArrayListState ha = new ArrayListState();
		holder.add("JFrame");
		ha.addState(holder, "top");
		/***Hold*/
		holder.add("Title");//Add Component to holder
		ha.addState(holder, "Title"); //Add Holder to the state
		holder.removeAll(holder);//Obliderate List
		holder.addAll(ha.getState("top")); //Reset Holder
		/***Hold*/
		holder.add("dim");
		ha.addState(holder, "dim");
		//System.out.println(ha.getState("top").get(0));
		//System.out.println("_________________________");
		//System.out.println(ha.getState("dim").get(0));
		holder.add("X");//Add Component to holder
		ha.addState(holder, "X"); //Add Holder to the state
		holder.removeAll(holder);//Obliderate List
		holder.addAll(ha.getState("dim")); //Reset Holder
		holder.add("Y");
		ha.addState(holder, "Y"); //Add Holder to the state
		holder.removeAll(holder); //Obliderate List
		holder.addAll(ha.getState("dim")); //Reset Holder
		//Width and Height
		holder.add("W");//Add Component to holder
		ha.addState(holder, "W"); //Add Holder to the state
		holder.removeAll(holder);//Obliderate List
		holder.addAll(ha.getState("dim")); //Reset Holder
		//
		holder.add("H");
		ha.addState(holder, "H"); //Add Holder to the state
		holder.removeAll(holder); //Obliderate List
		holder.addAll(ha.getState("dim")); //Reset Holder
		for(String s:ha.getState("dim")) {
			//System.out.println(s);
		}
		//Write XML
		File file = new File(System.getProperty("user.home")+"/Desktop/Frame.xml");
		XMLRW.newXMLFile(file, "JFrame");
		XMLRW.addElement(file, ha.getStateAsArray("top"), "Title", title);
		XMLRW.addElementContainer(file, ha.getStateAsArray("top"), "dim");
		XMLRW.addElements(file, ha.getStateAsArray("dim"), new String[] {"X","Y","H","W"}, new String[] {String.valueOf(x),String.valueOf(y),String.valueOf(w),String.valueOf(h)});
		ArrayList<String> elms = XMLRW.getElements(file, ha.getStateAsArray("dim"));
		ArrayList<String> vals = XMLRW.getElementsValues(file, ha.getStateAsArray("dim"),elms);
		for(String s:vals) {
			//System.out.println(s);
		}
		JFrame f = new JFrame(XMLRW.getElementValue(file, ha.getStateAsArray("Title")));
		
		
		
		
	}

	/**
	 * 
	 */
	private static void frame() {
		String title = "Test";
		JFrame f = new JFrame(title);
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		double x = f.getLocation().getX();
		double y = f.getLocation().getY();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		File file = new File(System.getProperty("user.home")+"/Desktop/Frame.xml");
		XMLRW.newXMLFile(file, XMLRW.NOBODY);
		XMLRW.addElementContainer(file, null, "JFrame");
		ArrayList<String> ha = new ArrayList<String>();
		ha.add("JFrame");
		ArrayListState fa = new ArrayListState();
		fa.addState(ha, "top");
		XMLRW.addElementContainer(file, fa.getStateAsArray("top"), "dim");
		XMLRW.addElement(file, fa.getStateAsArray("top"), "Title", title);
		ha.add("Title");
		fa.addState(ha, "Title");
		ha=fa.getState("top");
		ha.add("dim");
		fa.addState(ha, "dim");
		ha.add("X");
		fa.addState(ha, "X");
		ha=fa.getState("dim");//asdfasdfsadfsdfasdfasdf
		for(String t:fa.getState("dim")) {
			//System.out.println(t);
		}//asdfsadfasdfsadf
		//System.out.println("asdf");
		ha.add("Y");
		for(String t:fa.getState("dim")) {//asdfasdfasdfsadf
			//System.out.println(t);
		}
		fa.addState(ha, "Y");
		ha=fa.getState("dim");
		ha.add("W");
		fa.addState(ha, "W");
		ha=fa.getState("dim");
		ha.add("H");
		fa.addState(ha, "H");
		ha=fa.getState("dim");

		XMLRW.addElements(file, fa.getStateAsArray("dim"), new String[] {"X","Y","H","W"}, new String[] {String.valueOf(x),String.valueOf(y),String.valueOf(f.getWidth()),String.valueOf(f.getHeight())});
		JFrame n = new JFrame();
		double X = Double.valueOf(XMLRW.getElementValue(file, fa.getStateAsArray("X")));
		double Y = Double.valueOf(XMLRW.getElementValue(file, fa.getStateAsArray("Y")));
		double W = Double.valueOf(XMLRW.getElementValue(file, fa.getStateAsArray("W")));
		double H = Double.valueOf(XMLRW.getElementValue(file, fa.getStateAsArray("H")));
		String TITLE = XMLRW.getElementValue(file, fa.getStateAsArray("Title"));
		n.setSize((int)W,(int)H);
		n.setLocation((int)X, (int)Y);
		n.setTitle(TITLE);
		n.setVisible(true);
		
		
	}

	/**
	 * 
	 */
	private static void fullChar() {
		ArrayList<String>	ha = new ArrayList<String>();
		ha.add("Character");
		ArrayListState fa = new ArrayListState();
		fa.addState(ha, "Character");
		ha.add("Attributes");
		fa.addState(ha, "Attributes");
		File file = new File(System.getProperty("user.home")+"/Desktop/Mario.xml");
		XMLRW.newXMLFile(file, "Character");
		XMLRW.addElementContainer(file, fa.getStateAsArray("Character"), "Attributes");
		HashMap<String,String> att = new HashMap<String,String>();
		att.put("HP", "42");
		att.put("ATK", "12");
		att.put("DEF", "4");
		att.put("CRIT", "100");
		att.put("BLOCK", "85");
		att.put("GIF", "C:/YE/");
		String[] el = att.keySet().toArray(new String[att.keySet().size()]);
		String[] val = new String[el.length];
		for(int i=0;i<val.length;i++) {
			val[i]=att.get(el[i]);
		}
		XMLRW.addElements(file, fa.getStateAsArray("Attributes"), el, val);
		XMLRW.addElement(file, fa.getStateAsArray("Character"), "Name", "Dunemask");
		ArrayList<String> stuff = XMLRW.getElementsValues(file, fa.getStateAsArray("Attributes"));
		for(String x:stuff) {
			//System.out.println(x);
		}
		
		
	}

	/**
	 * 
	 */
	private static void character() {
		File file = new File(System.getProperty("user.home")+"/Desktop/Dunemask_Character.xml");
		XMLRW.newXMLFile(file, "Character");
		XMLRW.addElement(file, new String[] {"Character"},"Name", "Dunemask");
		XMLRW.addElementContainer(file, new String[] {"Character"},"Attributes");
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"HP",99);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"ATK",12);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"DEF",4);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"CRIT",100);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"BLOCK",87);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"GIF","C:/Ye");
		/*for(int i=0;i<archs.size();i++) {
			stats.add(XMLRW.getElementValue(file, new String[] {"Character","Attributes",archs.get(i)}));
		}
		for(String x:stats) {
			//System.out.println(x);
		}*/
		/*ArrayList<String> vals = XMLRW.getElementsValues(file, new String[] {"Character","Attributes"}, archs);
		*/
		ArrayList<String> vals = XMLRW.getElementsValues(file, new String[] {"Character","Attributes"});
		vals.add(0,XMLRW.getElementValue(file, new String[] {"Character","Name"}));
		for(String x:vals) {
			//System.out.println(x);
		}
		
		
		
	}

	/**
	 * 
	 */
	private static void test() {
		File xmlDoc = new File(System.getProperty("user.home")+"/Desktop/Test.xml");
		XMLRW.newXMLFile(xmlDoc, "Cookie");
		XMLRW.addElementContainer(xmlDoc, new String[] {"Cookie"}, "Type");
		XMLRW.addElementContainer(xmlDoc, new String[] {"Cookie","Type"}, "Potato");
		XMLRW.addElementContainer(xmlDoc, new String[] {"Cookie","Type"},"Ninja");
		XMLRW.addElement(xmlDoc, new String[] {"Cookie","Type","Potato"}, "Orange", "Pink");
		XMLRW.addElement(xmlDoc, new String[] {"Cookie","Type","Potato"}, "Vanilla","Flavorful");
		XMLRW.addElement(xmlDoc, new String[] {"Cookie","Type","Ninja"}, "Skilled","Null");
		
	}

}
