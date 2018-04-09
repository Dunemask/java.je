package jtunes;

import java.io.File;

public class JSong {

	private File file;
	private String title;

	public JSong(File file,String title) {
		this.setFile(file);
		this.setTitle(title);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
