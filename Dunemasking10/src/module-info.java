module dunemasking10{
	requires  java.base;
	requires  transitive  java.desktop;
	requires  transitive  javafx.graphics;
	requires  transitive  javafx.media;
	requires  transitive  javafx.fxml;
	requires  transitive  javafx.base;
	requires  transitive  javafx.swing;
	requires  transitive  javafx.controls;
	requires  transitive  java.scripting;
	exports dunemask.util;
	exports dunemask.objects;
	exports dunemask.objects.movieplayer;
	exports dunemask.util.xml;
	exports dunemask.other;
}