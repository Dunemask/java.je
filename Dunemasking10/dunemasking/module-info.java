/** Dunemasking is an opensource program that has some basic java tools
 * 
 * @author dunemask
 * 
 * */
module dunemasking{
	requires  java.base;
	requires  transitive  java.desktop;
	requires  transitive  java.scripting;
	exports dunemask.util;
	exports dunemask.objects;
	exports dunemask.util.xml;
	exports dunemask.other;
	exports dunemask.at;
	exports dunemask.crypto;
}