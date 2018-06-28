package play;

import java.io.File;

public class RSITest {

	public static void main(String[] args) {
		RuneScroll rs = new RuneScroll(new File("tmp.lolz"));
		rs.addContainer("top");
		rs.addContainer("top2");
		rs.addObject("top/subtop");
		rs.addObject("top2/obj");
		rs.addElement("top2/obj", "Block", "Stone");
		/*var tmp = rs.getRm().getIndex();
		var keys = new ArrayList<String>(tmp.keySet());
		for(int i=0;i<tmp.keySet().size();i++) {
			System.out.println(tmp.get(keys.get(i)).getName());
		}*/
		RSI.scrollToMap(rs);

	}

}
