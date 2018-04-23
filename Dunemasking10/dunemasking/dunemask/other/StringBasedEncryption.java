/**
 * 
 */
package dunemask.other;

import java.util.ArrayList;
import java.util.Random;

import dunemask.objects.Expression;
import dunemask.util.MathUtil;

/**
 * @author Elijah
 *
 */
public class StringBasedEncryption {
	public static final int defKey[] =new int[] {7};
	public static Expression defKeyCode = new Expression("((7*key)+2)",new String[] {"key"} ,defKey) ;
	/***Version*/
    final static double version = 4.5;
	
	/**Decrypt a String based on mathematical re-arranging
	 * @param keyCode expression for Solving
	 * @param code Text to be decrypted
	 * @param key to decryption
	 * @return the Decrypted String using key provided
	 */
	public static String decrypt(Expression keyCode,String code,int key[]) {
		
		if(keyCode.getVari().length!=key.length) {
		throw new RuntimeException("INVALID KEY!");
		//Count Off
		}
		for(int i = 0;i<key.length;i++) {
			if(keyCode.getVari()[i]!=key[i]) {
				throw new RuntimeException("INVALID KEY!");
			}
		}
		
		char[] stuff = new char[code.length()];
		for(int i=0;i<code.length();i++) {
			char character = code.charAt(i);
			int ascii =(int)character;
			ascii=ascii+keyCode.solve();
			char newChar = (char)ascii;
			stuff[i]=newChar;
		}
		return String.valueOf(stuff);
	}
	/** Encrypt a String based on mathematical re-arranging
	 * @param keyCode expression for Solving
	 * @param code Text to be encrypted
	 * @param key to for encoding
	 * @return the Encrypted String using key provided
	 */
	public static String encrypt(Expression keyCode,String code,int key[]) {
		if(keyCode.getVari()!=key) {
		throw new RuntimeException("INVALID KEY!");
		/*String exp = keyCode.getExpression();
		String vn[] = keyCode.getVarNames();
		keyCode = new Expression(exp,vn,key);*/
		}
		
		char[] stuff = new char[code.length()];
		for(int i=0;i<code.length();i++) {
			
			char character = code.charAt(i);	
			int ascii = (int) character;
			ascii=ascii- keyCode.solve();
			//System.out.println(keyCode.solve());
			char newChar = (char)ascii;
			stuff[i]=newChar;		
			//System.out.println(character+"->"+newChar);
		}
		return String.valueOf(stuff);	
	}
	
	/**Randomly creates keys and expressions 
	 * (To get keys call expression.getVari() )
	 * @param keyNumber number of keys in expression
	 * @return Returns randomly generated expression
	 * */
	
	public static Expression RandomEncryptionKeyCode(int keyNumber) {

		//List of operators (/ not included because it gives decimals)
		String operators = "*+^-%";
		//Array List of Integers {The keys}
		ArrayList<Integer> keys = new ArrayList<>();
		//Array List of names {Variable names for expressions}
		ArrayList<String> names = new ArrayList<>();
		for(int i=0;i<keyNumber;i++) {
			keys.add(new Random().nextInt(20));		
			names.add("key["+i+"]");
	
		}
		//If It's odd we need to add a +0 at the end, why? "We're counting by two's"
		if(MathUtil.isOdd(keyNumber)) {
			names.add("key["+(keyNumber+1)+"]");
			keys.add(0);
		}
		//Actuall array
		String[] name = names.toArray(new String[names.size()]);
		//Actual array
		Integer[] key = keys.toArray(new Integer[keys.size()]);
		//The expression is blank
		String expression = "";
		//For the number of keys we have count by twos
		for(int i=0;i<key.length;i+=2) {
			//Get Rnadom Operator
			String operator = String.valueOf(operators.charAt(new Random().nextInt(operators.length())));	
			//If It's odd and it's the last number we neeed to add 0 to the end so it has to be +0;
			if(i==key.length-2&&MathUtil.isOdd(keyNumber)) {
				operator = "+";
			}
			//Expression adds vari1 and performs operation operator to vari2
			expression+= name[i]+operator+name[i+1];
			//Get New Operator and add
			operator = String.valueOf(operators.charAt(new Random().nextInt(operators.length())));	
			//Add random operator to end
			expression+= operator;
		}
		//Change it to int array instead of integer array
		int[] tk = new int[key.length];
		for(int i=0;i<key.length;i++) {
			tk[i] = key[i];
		}
		//Last Character will have an additional thingy, so we'll just remove it :)
		expression = expression.substring(0,expression.length()-1);
		
		expression.replace("%0", "%1");
		expression.replace("/0", "%1");
		return new Expression(expression,name,tk);
		
	}
	
	
	
	
	/**Decrypt a string that's been put through layers of DM encryption
	 * @param text Text that's been Encoded
	 * @param layers Layers that the text has been through
	 * @param exp Expressions that the code has been through
	 * @param keys Key array that solves the expression
	 * @return decrypted Text
	 * 
	 * */
	public static String layeredDecryption(String text,int layers, Expression[] exp,int[][] keys) {	
	layers++;
		for(int c=layers-1;c>=0;c--) {		
			text=  decrypt(exp[c],text, keys[c]);	
		}	
		return text;	
	}
	
	/**Encrypt a String through layers of DM encryption
	 * @param text Text that will be encrypted
	 * @param layers Layers that the text  will go through
	 * @param exp Expressions that the code has been through
	 * @param keys Key array that solves the expression
	 * @return decrypted Text
	 * 
	 * */
	public static String layeredEncryption(String text,int layers, Expression[] exp,int[][] keys) {	
		layers++;
		for(int c=0;c<layers;c++) {		
			text=  encrypt(exp[c],text, keys[c]);	
		
		}	
		return text;	
	}
	
	
}
