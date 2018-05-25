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

	}

}
