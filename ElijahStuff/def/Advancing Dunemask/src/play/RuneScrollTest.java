/**
 * 
 */
package play;

/**
 * @author Dunemask
 *
 */
public class RuneScrollTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RuneScroll rs = new RuneScroll();
		rs.addContainer("potato");
		rs.addContainer("mroo/");
		rs.addContainer("mroo/asdf/");
		rs.addContainer("mroo/asdf/Cookie");
		rs.addObject("mroo/asdf/Cookie/Pink");
		rs.addElement("mroo/asdf/Cookie/Pink","other?", 0);
		rs.addObject("pig");
		rs.addElement("pig", "color", "pink");
		
		//System.out.println(rs.getRm().getIndex());
		//rs.addElement("potato/epic/moo/alt", "fir", "val");
		//System.out.println(rs.getChildren("mroo"));
		//rs.getRm().getSp().addRune("potato/");
		//rs.getRm().getSp().addRune("mroo/");
		//rs.getRm().getSp().addRune("mroo/asdf/");
		//System.out.println(((CompressedRuneSlot)rs.getRm().getSp().getRune("mroo/")).getChildren());
		var chil = rs.getChildren("mroo/");
		System.out.println(chil);
		rs.getRm().getSp().assemble();
		System.out.println(rs.getRm().getSp().fullLine);
		var top = rs.getTop();
		System.out.println(top);
		

	}

}
