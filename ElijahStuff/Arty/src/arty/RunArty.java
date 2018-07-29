package arty;

import java.io.File;
import java.util.Calendar;

public class RunArty {

	public static void main(String[] args) {
		String source = "/arty/resources/";
		Arty art = new Arty();
		art.getPlet().addData("/Name", "YUI");
		art.getPlet().addData("/Age", Calendar.getInstance().getTime());
		art.getPlet().addData("/Source", source);
		File attr = new File("src"+source+"Attributes");
		art.getPlet().writeOut(attr);
		Scriptor.handle("print(Cookies!);");
		
		
	}

}
