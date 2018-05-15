/**
 * 
 */
package play;

/**
 * @author dunemask
 *
 */
public class ProgrammingScriptPlay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CompressedScriptProcessor sp = new CompressedScriptProcessor();
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
		System.out.println(nsp.fullLine);
		
		

	}

}
