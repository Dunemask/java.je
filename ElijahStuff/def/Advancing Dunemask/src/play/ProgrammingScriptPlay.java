/**
 * 
 */
package play;

import java.io.File;

/**
 * @author dunemask
 *
 */
public class ProgrammingScriptPlay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*CompressedScriptProcessor sp = new CompressedScriptProcessor();
		String line = "Top:{\r\n	SubElm:{	Value:\"Value1!\"} AltElm:{ Value:\"v2\"}}AltTop:{New:\"aww:(\"}AlTop:{New:\"aww:)\"}";
		//System.out.println("IN:"+line);
		//String line =	"Top:{Value:\"Value1!\" SecVal:\"antval\" 				ThirdVal:\"233\"}";
		sp.process(line);
		//sp.addElement("Top/SubElm");
		sp.addRune("Top/Alpha/");
		sp.addRune("Top/Alpha/Beta");
		//sp.printAll();
		((CompressedRuneElement)sp.getRune("Top/Alpha/Beta")).getValues().put("Sneaky!", "SuperSneaky As you can tell!");
		System.out.println(((CompressedRuneElement)sp.getRune("Top/Alpha/Beta")).getValues());
		sp.assemble();
		System.out.println(sp.fullLine);
		var nsp = new CompressedScriptProcessor(sp.fullLine);
		nsp.assemble();
		System.out.println(nsp.fullLine);*/
		CompressedRunemap rm = new CompressedRunemap(new File(System.getProperty("user.home")+"/Desktop/Test.crm"));
		//CompressedRunemap crm = new CompressedRunemap(new File(System.getProperty("user.home")+"/Desktop/Alt.crm"));
		rm.addRune("Country/");
		rm.addRune("Country/UnitedStates/");
		rm.addRune("Country/UnitedStates/Utah");
		rm.addValue("Country/UnitedStates/Utah", "ID", 48);
		rm.addValue("Country/UnitedStates/Utah","Capital","Salt Lake City");
		rm.addValue("Country/UnitedStates/Utah", "StateBird", "Turkey");
		rm.compressedWrite();
		System.out.println(rm.getSp().fullLine);
		var crm = new CompressedRunemap();
		crm.setSp(new CompressedScriptProcessor(rm.getSp().fullLine));
		System.out.println(crm.getValue("Country/UnitedStates/Utah", "StateBird"));
		var ncrm = CompressedRunemap.parseCompressedRunemap(rm.getFile());
		System.out.println("*******");
		System.out.println("*******");
		

	}

}
