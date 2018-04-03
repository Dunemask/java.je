package dunemask.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
/**Does Some Object Editing
 * <p>Already In List: {@link dunemask.util.ObjectUtil#alreadyInList(Object[], Object)}</p>
 * <p>Read Object: {@link dunemask.util.ObjectUtil#readObject(File)}</p>
 * <p>Write Object: {@link dunemask.util.ObjectUtil#writeObject(File, Object)}</p>
 * <p>Write Object with Custom Extension: {@link dunemask.util.ObjectUtil#writeObject(File, Object, String)}</p>
 **/
public class ObjectUtil{
	
	/***Version*/
    final static double version = 4.5;
	/**
	 * Writes an object to specified file (make sure it has extension)
	 * 
	 * @param file
	 *            File where object is stored
	 * @param ob
	 *            Object that will be stored
	 */
	public static void writeObject(File file, Object ob) {
		
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(ob);
			os.flush();
			os.close();
			String parentFolder = file.getParentFile().getAbsolutePath() + "\\";
			File objIndex = new File(parentFolder + "objIndx.index");
			objIndex.createNewFile();
			// If Not Indexed index
			if (!FileUtil.alreadyInFile(objIndex, file.getName())) {
				FileWriter fr = new FileWriter(objIndex, true);
				fr.write(file.getName());
				fr.write(System.lineSeparator());
				fr.close();
			}

		} catch (IOException e1) {

			e1.printStackTrace();
		}

	}


	/**
	 * Reads object From specified file, (Needs to be casted to desired variable)
	 * 
	 * @param file
	 *            File to be read
	 * @return return object from file
	 */
	public static Object readObject(File file) {
		Object ob = null;
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new FileInputStream(file));
			ob = is.readObject();
			is.close();
		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		return ob;
	}
	
	/**
	 * Scans the file to see if the text is already in the list
	 * 
	 * @param array
	 *            Obj array to be searched
	 * @param ob
	 *            to look for
	 * @return Whether it's in the list or not.
	 */
	public static boolean alreadyInList(Object[] array, Object ob) {
		if (array != null) {
			boolean isInList = false;
			for (int i = 1; i < array.length; i++) {
				if (array[i]==ob && array[i] != null) {
					isInList = true;
				}

			}
			return isInList;
		} else {
			return false;
		}
	}
	/**This is not tested yet
	 * 	 @param ob to look for
	 *           @param array List of Objects
	 *           
	 *           @return the list of objects already edited
	 * 
	 * @deprecated
	 * */
	public static Object[] createObjectNotInLIst(Object[] array, Object ob) {
		Random r = new Random();
		Object myObject[] = null;
		boolean objectInList = true;
		do{
			int randInt = r.nextInt(array.length);
			if(array[randInt]==null) {
				array[randInt] = ob;
			}
			
			
		}while(objectInList);
		
		
		
		return myObject;
	}
}
