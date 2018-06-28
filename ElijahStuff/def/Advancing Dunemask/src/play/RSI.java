/**
 * 
 */
package play;

import dunemask.util.xml.Runemap;

/**
 * @author Dunemask
 * Rune Scroll Interface for Ease of Using RuneScroll
 */
public class RSI {
	private static RuneScroll rs;
	private static Runemap rm;
	
	public static Runemap scrollToMap(RuneScroll ros) {
		rs = ros;
		if(rs.getRm().getFile()==null) {
			throw new RuntimeException("Rune Scroll Must Have a File To Be Converted To A RuneMap!");
		}
		
		rm = new Runemap(rs.getRm().getFile());
		var children = rs.getTop();
		System.out.println(children);
		for(int i=0;i<children.size();i++) {
			var child = children.get(i);
			boolean isCont=false;
			if(child.endsWith("/")) {
				isCont = true;
			}
			if(isCont) {
				rm.writeContainer(child);
				writeChildren(child);

			}else {
				rm.writeContainer(child);
				var val = ((CompressedRuneElement)rs.getRm().getSp().getRune(child)).getValues();
				for(int c=0;c<val.size();c++) {
					rm.writeElement(child, val);
				}
			}
			
			
		}
		
		
		
		
		return rm;
		
	} 
	
	
	private static void writeChildren(String curl) {
		
		
		
		
	
	}

	
	
	
	
	
}
