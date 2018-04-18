package mc;
/**
 * 
 */

import java.util.ArrayList;

import dunemask.objects.DMediaPlayer;
import dunemask.util.StringUtil;
import minerender.VoxelCt;
import mplayer.PlaySound;

/**
 * @author Dunemask
 *
 */
public class MineCommands {
	public static ArrayList<String> commands = new ArrayList<String>();
	public static int latestCommand =-1;
	
	
	public static String getPrevCommand() {
		int orig =latestCommand;
		latestCommand--;
		try {
			lc = commands.get(latestCommand-1);
			return commands.get(latestCommand-1);
		}catch(java.lang.IndexOutOfBoundsException exc) {
			return lc;
		}
		
	}
	static String lc;
	public static String getNextCommand() {
		int orig =latestCommand;
		latestCommand++;
		try {
			lc = commands.get(latestCommand-1);
			return commands.get(latestCommand-1);
		}catch(java.lang.IndexOutOfBoundsException exc) {
		return lc;
	}
	}
	
	public static String getCommand(int get) {
		return commands.get(get);
	}
	
	public static String getLastCommand() {
		return commands.get(commands.size()-1);
	}
	
	public static void HandleCommand(String fullCommand) {
		commands.add(fullCommand);
		String[] com = fullCommand.split("\\s+");
		String command = com[0];
		if(!command.startsWith("/")) {
			System.out.println(fullCommand);
		}else {
			command = command.substring(1, command.length());
		}
		if(command.equalsIgnoreCase("set")) {
			set(com);
		}
		
		
		if(command.equalsIgnoreCase("gamemode")) {
			if(com[1].equalsIgnoreCase("c")) {
				Minecraft.vx.changeMode(VoxelCt.Creative);
			}else if(com[1].equalsIgnoreCase("s")){
				Minecraft.vx.changeMode(VoxelCt.Survival);
			}else {
				System.out.println("Invalid Argument");
			}
		}
		
		
	}

	/**
	 * @param com
	 */
	private static void set(String[] com) {
		String change = com[1];
		String val = com[2];
		if(StringUtil.containsIgnoreCase(change, "vol")) {
			PlaySound.vol=((double)(Integer.valueOf(val)))/100;
			DMediaPlayer.getMediaPlayer().setVolume(PlaySound.vol);
			//System.out.println(com+":"+PlaySound.vol+"FROM:"+(double)(Integer.valueOf(val))+"/100"+"RESULTS:"+((double)(Integer.valueOf(val)))/100);
		}
		if(StringUtil.containsIgnoreCase(change, "rend")) {
			Minecraft.renderVal = Integer.parseInt(val);
		}
	
		
		
	}
	

}
