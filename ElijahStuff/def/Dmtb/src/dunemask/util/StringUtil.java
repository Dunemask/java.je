package dunemask.util;

import java.util.Random;

/**<p>Tests if String Is Already In The List: {@link dunemask.util.StringUtil#alreadyInList(String[], String)}</p>
 * <p>Tests if String Contains Char Sequence Ignoring Cases: {@link dunemask.util.StringUtil#containsIgnoreCase(String, String)}</p>
 *  <p>Tests if the Text passes Criteria Set: {@link dunemask.util.StringUtil#filteredItem(String, String[], String[])}</p>
 *  <p>Tests if the Text passes Criteria Set Ignoring Cases: {@link dunemask.util.StringUtil#filteredItemIgnoreCase(String, String[], String[])}</p>
 *   <p>Generate Character from {@link dunemask.util.StringUtil#alphabet}
 *   Random CharGenny Method: {@link dunemask.util.StringUtil#randomCharacterGenerator(String[])} </p> 
 * 
 * */
public class StringUtil {

    final static double version = 4.5;
    
    /** Tab WhiteSpace
     * 
     * */
    public static final String tab = "	";
    
    
    
	/**
	 * The Alphabet To Be Searched for Random Char Gen.
	 */
	public static final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ?/., !#$%^&*()_-+=~`{[}]|?><:;\\";
	/**Compares to see if string one contains string two
	 * @param one, String One
	 * @param two, String two
	 * @return If String one contains string two ignoring case sensitive
	 * **/
	public static boolean containsIgnoreCase(String one,String two){
		one = one.toLowerCase();
		two = two.toLowerCase();
		if(one.contains(two)){
			return true;
		}else{
		return false;
		}
	}
	/** @param string String
	 * @param toReplace String wanted replaced
	 * @param replacement What will be replaced
	 * @param return Replaced String or the string if not changed
	 * 
	 * */
	public static String replaceLast(String string, String toReplace, String replacement) {
	    int pos = string.lastIndexOf(toReplace);
	    if (pos > -1) {
	        return string.substring(0, pos)
	             + replacement
	             + string.substring(pos + toReplace.length(), string.length());
	    } else {
	        return string;
	    }
	}
	/**Returns true if it met the Criterea (Case Sensitive)
	 * @param text Text being run through excpetions
	 * @param exceptions List of things that the text cannot contain
	 * @param requirements List of things that the text Must Contain
	 * @return Returns true if it made it through the filter false otherwise
	 * */
	public static boolean filteredItem(String text,String[] exceptions, String[] requirements) {

		
		boolean filtered = true;
		for(String x:exceptions) {
			
			if(text.contains(x)) {
				filtered = false;
			}else {
				//System.out.println(text);
			}
			
		}
		
		for(String r: requirements){
			
			if(!text.contains(r)){
				filtered=false;
			}
		}
		
		
		return filtered;
	}
	/**Returns true if it met the Criterea (Not Case Sensitive)
	 * @param text Text being run through excpetions
	 * @param exceptions List of things that the text cannot contain
	 * @param requirements List of things that the text Must Contain
	 * @return Returns true if it made it through the filter false otherwise
	 * */
	public static boolean filteredItemIgnoreCase(String text,String[] exceptions, String[] requirements) {
		boolean filtered = true;
		for(String x:exceptions) {
			
			if(StringUtil.containsIgnoreCase(text, x)) {
				filtered = false;
			}			
		}
		
		for(String r: requirements){
			
			if(!StringUtil.containsIgnoreCase(text, r)){
				filtered=false;
			}
		}
		
		
		return filtered;
		
		
		
	}
	
	/**
	 * Gets a random character that isn't in a list already New Character from:
	 * 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ?/.,
	 * 
	 * @param chosen
	 *            The String array that can't have the specific character
	 * @return Returns random character that is not in the list
	 */
	public static char randomCharacterGenerator(String[] chosen) {

		Random rnd = new Random();
		char c;
		String chara;

		c = alphabet.charAt(rnd.nextInt(alphabet.length()));
		chara = String.valueOf(c);
		if (!StringUtil.alreadyInList(chosen, chara)) {
			return c;
		} else {
			return "@".charAt(0);
		}

	}

	/**
	 * Scans the file to see if the text is already in the list (Case Sensitive)
	 * 
	 * @param sArray
	 *            String array to be searched
	 * @param text
	 *            to look for
	 * @return Whether it's in the list or not.
	 */
	public static boolean alreadyInList(String[] sArray, String text) {
		if (sArray != null) {
			boolean isInList = false;
			for (int i = 1; i < sArray.length; i++) {
				if(sArray[i]!=null) {
					if (sArray[i].equals(text) && sArray[i] != null) {
						isInList = true;
						i=sArray.length;
					}//End Test text
				}//End if not Null

			}
			return isInList;
		} else {
			return false;
		}
	}


}
