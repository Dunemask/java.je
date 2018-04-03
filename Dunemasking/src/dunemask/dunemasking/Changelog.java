/**
 * 
 */
package dunemask.dunemasking;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dunemask.util.FileUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author karib
 * <p>Main: {@link dunemask.dunemasking.Changelog#main(String[])}</p>
 * <p>Update All Versions: {@link dunemask.dunemasking.Changelog#updateAllVersions()}</p>
 * <p>Print Changelog: {@link dunemask.dunemasking.Changelog#printChangeLog()}</p>
 */
public class Changelog {
	/*@Changelog.java: Tests javadoc version (Only works in ide) and labels with package
	 				   Styling	*/
	/***Version*/
    final static double version = 4.5;
	final static String toPackages = "src/dunemask/";
	final static File changeLog = new File("src/dunemask/resources/Changelog.txt");
	final static File additionally = new File(toPackages+"resources/AdditionalNotes.txt");
	final static int directFilesSize = 50;
	final static String[] exceptions = {"package-info.java"};
	final static String[] requirements = {".java"};
	/**
	 * @param args Default paramaters
	 */
	public static void main(String[] args) {
		//Re-Writes the Version to be more fitting
		calcVersion();
		
		
		//Update All Versions
		updateAllVersions();
		//Print out Changelog Location For Verifciation
		System.out.println("ChangeLog @ "+ changeLog.getAbsolutePath());
		//Test to make sure a new JavaDoc has been made 
		
		try {
			testDocCreated();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		changeLog.delete();
		
		
		//Create a new File, Explode if it doesn't work
				try {
					changeLog.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("File Not Found @ "+ changeLog.getAbsolutePath());
				}
				File dir = new File(toPackages);
				File[] subFiles = FileUtil.getAllSubFiles(dir);
				String[] displayedFiles = new String[subFiles.length];
				ArrayList<String> display = new ArrayList<>();
				for(int i=0;i<subFiles.length;i++) {
					String x = subFiles[i].getPath();
					boolean acc = StringUtil.filteredItem(x, exceptions, requirements);
					if(acc) {
						//System.out.println(x);
						File file = new File(x);
						checkVersion(file);
						x = x.replace("src\\", "");
						x = x.replace("\\", ".");
						x = x + " Version: "+ getFileVersion(file);
						display.add(x);
					}
				}
			displayedFiles = display.toArray(new String[display.size()]);
			
			RW.write(changeLog, "Dunemasking Ver: " + Version.getVersion(), 1);
			RW.write(changeLog, displayedFiles, 2);
			writeAdditionalNotes();
			
			//Time Stamp it
			LocalDateTime localDate = LocalDateTime.now();//For reference
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String formattedString = localDate.format(formatter);
			RW.write(changeLog,"Created at: "+formattedString, FileUtil.linesInFile(changeLog)+2);
			System.out.println("(Class-Update) Previous Programs May not be compatable");
			System.out.println("(Update) New programs added, Previous are Compatable");
			System.out.println("Which?");
			Scanner in =new Scanner(System.in);
			int choice = in.nextInt();
			in.close();
			
			String text = "(Update)";
			if(choice==1) {
				text = "(Class-Update)";
			}else if(choice==2) {
				
			}else {
				text = "(Update)";
			}	
			RW.write(changeLog, text, FileUtil.lastLine(changeLog));
			printChangeLog();
				
				
				
		
	}
	/**
	 * 
	 */
	private static void calcVersion() {
		File dir = new File(toPackages);
		File[] subFiles = FileUtil.getAllSubFiles(dir);
		ArrayList<Double> versions = new ArrayList<>();
		for(int i=0;i<subFiles.length;i++) {
			String x = subFiles[i].getPath();
			boolean acc = StringUtil.filteredItem(x, exceptions, requirements);
			if(acc) {
				//System.out.println(x);
				File file = new File(x);
				double dub = getFileVersion(file);
				versions.add(dub);
			}
		}
		Double[] vers = versions.toArray(new Double[versions.size()]);
		Double sum = 0.0;

		for (Double d : vers) {
		    sum += d;
		}
		double avg= (sum/vers.length)+.01;
		avg =  Math.floor(avg * 100) / 100;
		double finalVersion = avg;
		RW.write(new File(toPackages+"resources//Version.txt"), "Version "+finalVersion, 1);
		
	}
	/**
	 * 
	 */
	private static void writeAdditionalNotes() {
		String[] notes = RW.read(additionally, 1, FileUtil.linesInFile(additionally));
		RW.write(changeLog, notes, FileUtil.lastLine(changeLog));
	}
	/**
	 * @param file
	 * @return
	 */
	private static double getFileVersion(File file) {
		int location =	FileUtil.findInDocument(file, "final static double version");
		String line = RW.read(file, location);
		line = line.replace("final static double version", "");
		line = line.replace(";", "");
		line = line.replace("=", "");
		
		
		double version = Double.parseDouble(line);
		return version;
	}
	/**
	 * @param file who's Version is checked
	 */
	private static void checkVersion(File file) {
		int location =	FileUtil.findInDocument(file, "final static double version");
		String line = RW.read(file, location);
		line = line.replace("final static double version", "");
		line = line.replace(";", "");
		line = line.replace("=", "");
	
		
		double version = Double.parseDouble(line);
		//Check COmpatable Version +.09 is leeneancy
		if(Version.getVersion()>version+.09) {
			Throwable vers = new Throwable("Version Not Up to Date - on File: "+file);
			try {
				throw vers;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		
	}
	/**Updates All Dunemasking Versions
	 * **/
	public static void updateAllVersions() {
		String dir[] = changeLog.getParentFile().getParentFile().list();
		//Write version
		for(int i =0;i<dir.length;i++) {

			if(new File(toPackages+"/"+dir[i]).isDirectory()) {
				File[] checker = new File(toPackages+"/"+dir[i]).listFiles();
				for(int a=0;a<checker.length;a++) {
					
					//If It's Not a class, if it's not package-info if it's not the Main Version file, and it's
					//Not the DM file
					if(checker[a].getAbsolutePath().contains(".java")&&
					!checker[a].getAbsolutePath().contains("package-info")
							) {
				
						
					int location =	FileUtil.findInDocument(checker[a], "final static double version");
					String line = RW.read(checker[a], location);
					line = line.replace("final static double version", "");
					line = line.replace(";", "");
					line = line.replace("=", "");
					double currentVersion = Double.parseDouble(line);
					double cv = Math.max(Version.getVersion(),currentVersion);
					String updatedVersion = "    final static double version = "+cv+";";
					RW.write(checker[a], updatedVersion, location);
					
					
					}else {
						//System.out.println(checker[a]);
					}
				
				}
			}
		}
		
	}
	
	/**Prints The ChangeLog
	 ***/
	public static void printChangeLog() {
		int lines = FileUtil.linesInFile(changeLog);
		String line[] = RW.read(changeLog, 1, lines);
		for(String x: line) {
			System.out.println(x);
		}
		
		
		
	}


	/**
	 * @throws ParseException failed Date Parsing
	 * 
	 */
	private static void testDocCreated() throws ParseException {
		//Set Doc Time
		Path file = Paths.get(toPackages+"/resources/doc/");
		BasicFileAttributes attr=null;
		//Read the attributes
		try {
			attr = Files.readAttributes(file, BasicFileAttributes.class);
		} catch (IOException e) {
			System.err.println("JAVADOCS ARE NOT CREATED!");
			e.printStackTrace();
		}
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//2017-12-12 08:34
		
			
			Date docDate = df.parse(df.format(attr.lastModifiedTime().toMillis()));
			/**
			 * 	//Time Stamp it
			LocalDateTime localDate = LocalDateTime.now();//For reference
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String formattedString = localDate.format(formatter);

			 * */
			
			
			//System.out.println(docDate);
			Date changeLogDate = null;
			if(changeLog.exists()) {
				String time =	RW.read(changeLog, FileUtil.linesInFile(changeLog)-1);
				time =time.replace("Created at: ", "");
			changeLogDate = df.parse(time);
			
				//Updated doc if changelog is before the doc date
				boolean createdDoc = changeLogDate.before(docDate);
				//System.out.println(createdDoc);	
				if(!createdDoc) {
					Throwable nd =  new Throwable("NEED TO CREATE DOC!");
					nd.printStackTrace();
					//RW.write(changeLog, nd.getMessage(), FileUtil.nextFreeLine(changeLog));
					
				}
			}	
	
	}
	
	
	
}
