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
		String line = "Top:{\r\n	SubElm:{	Value:\"Value1!\"} AltElm:{ Value:\"v2\"}}}AltTop:{New:\"aww:(\"}AlTop:{New:\"aww:)\"}";
		//String line =	"Top:{Value:\"Value1!\" SecVal:\"antval\" 				ThirdVal:\"233\"}";
		sp.process(line);
		//sp.addElement("Top/SubElm");
		sp.addRune("Top/Top/");
		sp.addRune("Top/Top/Top");
		//sp.printAll();
		((CompressedRuneElement)sp.getRune("Top/Top/Top")).getValues().put("Pink", "Green");
		System.out.println(((CompressedRuneElement)sp.getRune("Top/Top/Top")).getValues());

	}

}
