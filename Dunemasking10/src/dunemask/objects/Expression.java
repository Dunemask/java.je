/**
 * 
 */
package dunemask.objects;

import java.io.Serializable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**Create an expression and call the Solve Function to solve it
 * 
 *  * @author Elijah
 *
 */
public class Expression implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8079254251686484945L;
	/***Version*/
    final static double version = 4.5;
	private String expression;
	private String intExpression;
	private int[] vari;
	public static String operators = "*%/+-";
	private String[] varNames;
	private int answer;

	/**Create an expression and call the Solve Function to solve it
	 * @param expression Mathematical Expression
	 * @param varNames Names of all Variables
	 * @param vari Ints that coreespond to the varNames;
	 */
	public Expression(String expression,String[]varNames,int[] vari) {
		this.expression = expression;
		if(varNames.length!=vari.length) {
			throw new RuntimeException("TOO MANY VARIABLES OR NAMES!");
		}
		
		for(int i=0;i<varNames.length;i++) {
			//System.out.println(varNames[i]);
			expression = expression.replace(varNames[i], String.valueOf(vari[i]));
		}
		this.intExpression = expression;
		this.vari = vari;
		this.varNames= varNames;
		this.setAnswer(Integer.MAX_VALUE);
		
		
	}

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @return the vari
	 */
	public int[] getVari() {
		return vari;
	}
	
	public int solve() {
		int x = 0;
		if(this.answer==Integer.MAX_VALUE) {
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    
	    try {
	    //	System.err.println("Expression: "+ this.getIntExpression());
	   String solved =  String.valueOf(engine.eval(this.getIntExpression()));
	  // System.out.println(solved);
	   if(!solved.equalsIgnoreCase("NaN")) {
	   x = Integer.parseInt(solved);
	   }else {
		   System.err.println("Equation could not be solved Answer being set to -3");
		   System.err.println("Equation: "+this.getIntExpression());
		   x = -3;
	   }
	   
	  // System.out.println(solved+"=="+x);

		
	  
		 this.setAnswer(x);
		} catch (ScriptException e ) {
			
			e.printStackTrace();
		}
	    }else {
	    	x = getAnswer();
	    }
	    
	    
		return x;
		
	}

	/**
	 * @return the intExpression
	 */
	public String getIntExpression() {
		return intExpression;
	}


	/**
	 * @return the varNames
	 */
	public String[] getVarNames() {
		return varNames;
	}

	/**
	 * @return the answer
	 */
	public int getAnswer() {
		if(this.answer==Integer.MAX_VALUE) {
			try {
				throw new Throwable("EXPRESSION NOT SOLVED");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	/**@param vari th variables to set
	 * */
	public void setVari(int[] vari) {
		this.vari = vari;
	}
	

	

}
