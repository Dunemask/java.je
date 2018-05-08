package dunemask.util.internal;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

/**
 * Jar Utilities
 * <p>Extract Jar: {@link dunemask.util.internal.JarInternalUtil#extractAll(String, String, String)}</p>
 * <p>Extract Jar With Progress Bar: {@link dunemask.util.internal.JarInternalUtil#extractAllOpenDialog(String, String, String)}</p>
 * <p>Get Jar Program Path: {@link dunemask.util.internal.JarInternalUtil#getProgramPath()}</p>
 * <p>Is jar:  {@link dunemask.util.internal.JarInternalUtil#isJar(String)}</p>
 * <p>List jar Entries: {@link dunemask.util.internal.JarInternalUtil#listJarEntries(File)}</p>
 * <p>Load File From Jar: {@link dunemask.util.internal.JarInternalUtil#loadFileInJar(File, String)}</p>
 * @author Elijah
 * @deprecated Will be removed in next version
 **/
public class JarInternalUtil{
	
	/***Version*/
    final static double version = 4.7;
	private static JFrame f = new JFrame();
	private static JPanel p = new JPanel();
	
	
	/**
	 * Extracts jar into new folder named: jarName_lib Displays Small Progress Bar
	 * While Extracting
	 * 
	 * @param jarName
	 *            Name of Jar
	 * @param destination
	 *            destination
	 * @throws IOException
	 *             If the file don't exist, it's not gonna work
	 */
	public static void extractAllOpenDialog(File jar, String destination) throws IOException {
		init();
		destination = InternalFileUtil.fixSpaces(destination).replaceAll("%20", " ");
		JarFile jarfile = new JarFile(jar);
		Enumeration<JarEntry> enu = jarfile.entries();
		// File file = new File(dir+"\\"+jarName+"_lib\\");
		//System.out.println(jar.getAbsolutePath());
		while (enu.hasMoreElements()) {
			String destdir = destination;
			JarEntry je = enu.nextElement();
			//if (!je.getName().contains("org") && !je.getName().contains("META-INF")) {

				updateScreen(je.getName(), je);

				File fl = new File(destdir, je.getName());
				if (!fl.exists()) {
					fl.getParentFile().mkdirs();
					fl = new java.io.File(destdir, je.getName());
				}
				if (je.isDirectory()) {
					continue;
				}
				InputStream in = jarfile.getInputStream(je);
				BufferedInputStream is = new BufferedInputStream(in);
				OutputStream out = new FileOutputStream(fl);
				BufferedOutputStream fo = new BufferedOutputStream(out);

				// Set progress Bar
				JProgressBar pb = new JProgressBar();
				pb.setSize(80, 20);
				pb.setMinimum(0);
				pb.setValue(0);
				pb.setMaximum(is.available() / (2048 * 32));
				pb.setLocation(10, 30);
				pb.setForeground(Color.GREEN);
				byte[] buffer = new byte[2048 * 32];

				while (is.available() > 0) {
					fo.write(buffer, 0, is.read(buffer));
					// Read and write, and update the PB+ repaint
					pb.setValue(pb.getValue() + 1);

					p.add(pb);
					repaintScreen();

				}

				fo.close();
				is.close();

			//}
		}
		JOptionPane.showMessageDialog(null, "Completed Extracting!");
		f.dispose();
		jarfile.close();
	}
	
	
	
	
	/**
	 * Extracts jar into new folder named: jarName_lib Displays Small Progress Bar
	 * While Extracting
	 * 
	 * @param dir
	 *            Directory to the jar
	 * @param jarName
	 *            Name of Jar
	 * @param resourceFolderName
	 *            Name of folder that will become the resource folder
	 * @throws IOException
	 *             If the file don't exist, it's not gonna work
	 */
	public static void extractAllOpenDialog(String dir, String jarName, String resourceFolderName) throws IOException {
		
		extractAllOpenDialog(new java.io.File(InternalFileUtil.fixSpaces(dir).replaceAll("%20", " ") + jarName + ".jar"),dir+"\\"+jarName+"_lib\\");
	}

	private static void updateScreen(String name, JarEntry je) {
		p.removeAll();
		JLabel lbl = new JLabel("Exporting: " + name);
		lbl.setSize(lbl.getPreferredSize());
		int x = 10;
		int y = 10;
		lbl.setLocation(x, y);
		p.add(lbl);
		repaintScreen();

	}

	private static void repaintScreen() {
		p.repaint();
		p.revalidate();
		f.repaint();
		f.revalidate();
	}

	/**
	 * Finds out if jarName_lib already exists
	 * 
	 * @param jarName
	 *            Name of Jar
	 * @param jarDir
	 *            Directory where jar is located
	 * @return whether the jarname_lib already exists
	 */
	public static boolean alreadyExisits(String jarName, File jarDir) {
		if (new File(jarDir.getAbsolutePath() + "\\" + jarName + "_lib\\").exists()) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * Creates small frame (Should be used for JProgressBar)
	 */
	public static void init() {

		f.setSize(300, 100);
		f.setLocationRelativeTo(null);
		p.setLayout(null);
		f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		f.setVisible(true);
		f.add(p);

	}
	
	/**
	 * Extracts jar into new folder named: jarName_lib
	 * 
	 * @param dir
	 *            Directory to the jar
	 * @param jarName
	 *            Name of Jar
	 * @param resourceFolderName
	 *            name of resource folder
	 * @throws IOException
	 *             If the jarFile don't exists it's gonna blow up
	 */
	public static void extractAll(String dir, String jarName, String resourceFolderName) throws IOException {
		dir = InternalFileUtil.fixSpaces(dir).replaceAll("%20", " ");
		jarName = InternalFileUtil.fixSpaces(jarName).replaceAll("%20", " ");
		resourceFolderName = InternalFileUtil.fixSpaces(resourceFolderName).replaceAll("%20", " ");
		extractAll(new java.io.File(InternalFileUtil.fixSpaces(dir).replaceAll("%20", " ") + jarName + ".jar"),dir+"\\"+jarName+"_lib\\");
	}
	
	
	/**
	 * 
	 * @param jar Jar File
	 * @param destination String path to export location
	 * @throws IOException
	 *             If the jarFile don't exists it's gonna blow up
	 */
	public static void extractAll(File jar,String destination) throws IOException {
			destination = InternalFileUtil.fixSpaces(destination).replaceAll("%20", " ");
			JarFile jarfile = new JarFile(jar);
		Enumeration<JarEntry> enu = jarfile.entries();
		// File file = new File(dir+"\\"+jarName+"_lib\\");
		while (enu.hasMoreElements()) {
			String destdir = destination;
			JarEntry je = enu.nextElement();
			//if (!je.getName().contains("org") && !je.getName().contains("META-INF")) {

				File fl = new File(destdir, je.getName());
				if (!fl.exists()) {
					fl.getParentFile().mkdirs();
					fl = new java.io.File(destdir, je.getName());
				}
				if (je.isDirectory()) {
					continue;
				}
				InputStream in = jarfile.getInputStream(je);
				BufferedInputStream is = new BufferedInputStream(in);
				OutputStream out = new FileOutputStream(fl);
				BufferedOutputStream fo = new BufferedOutputStream(out);

				byte[] buffer = new byte[2048 * 32];

				while (is.available() > 0) {
					fo.write(buffer, 0, is.read(buffer));
					// Read and write, and update the PB+ repaint

				}

				fo.close();
				is.close();

			//}
		}
		jarfile.close();
	}

	/**
	 * @return Returns the jardir with \\ at end
	 */
	public static String getProgramPath() {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		return jarDir + "\\";
	}

	/**
	 * Is Jar Returns wether it's a jar or not given a jar name
	 * 
	 * @param jarName
	 *            name of jar
	 * @return jar or not jar
	 **/
	public static boolean isJar(String jarName) {
		boolean isJar = false;
		if (jarName.contains(".jar")) {
			jarName = InternalFileUtil.removeExtension(jarName);
		}

		if (new File(InternalFileUtil.fixSpaces(JarInternalUtil.getProgramPath()).replaceAll("%20", " ") + jarName + ".jar").exists()) {
			
			isJar = true;
		}
		return isJar;

	}
	
	public static String findEntry(File jarFile,String res) {
		String url=null;
		String[] je = listJarEntries(jarFile);
		for(int i=0;i<je.length;i++) {
			if(je[i].contains(res)) {
				url= je[i];
				i=je.length;
			}
		}
		if(url==null) {
			throw new RuntimeException("Resource Couldn't Be Found");
		}
		
		return url;
		
	}
	
	
	/**Get the names of all entries in the jar
	 * @param jarFile Jar whose entries will be listed
	 * @return String of Jar Entry Names
	 * 
	 **/
	public static String[] listJarEntries(File jarFile) {
		JarFile jar = null;
		try {
			jar = new JarFile(jarFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<String> entries = new ArrayList<>();
		
		Enumeration<JarEntry> enu = jar.entries();
		while (enu.hasMoreElements()) {
			JarEntry je = enu.nextElement();
			entries.add(je.getName());
		}	
		
		return entries.toArray(new String[entries.size()]);
		
	}

	/**Load File In Jar
	 * <p>If Returning same results, It's the fact that it's also in your ide, or jar, IT CANNOT BE!</p>
	 * @param jar Top jar
	 * @param res Relative Path to File in jar
	 * @return Returns the file
	 * */
	public static File loadFileInJar(File jar,String res)  {
		
		//Get File
		URL[] urls = null;
		try {
			urls = new URL[]{jar.toURI().toURL()};
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		URLClassLoader urlClassLoader = new URLClassLoader(urls);
		URL resource = urlClassLoader.findResource(res);
		
		
		
		//URL x = URLClassLoader.newInstance(urls).findResource(res.toString());
		
		//Write tmpFile
		File file=null;
		if (resource.toString().startsWith("jar:")||resource.toString().startsWith("rsrc:")) {
			try {
				
				InputStream input = URLClassLoader.newInstance(urls).getResourceAsStream(res);
				file = File.createTempFile("tempfile", ".tmp");
				OutputStream out = new FileOutputStream(file);
				int read;
				byte[] bytes = new byte[1024*1024];

				while ((read = input.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				//file.deleteOnExit();
				input.close();
				out.close();
				urlClassLoader.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}else {
			try {
				urlClassLoader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("Uh Boom?");	
		}
		
		
		
		return file;
	}

	
	
	
	
		
		
}
		
