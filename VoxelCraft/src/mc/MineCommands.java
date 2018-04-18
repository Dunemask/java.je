package mc;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dunemask.objects.DMediaPlayer;
import dunemask.util.StringUtil;
import minerender.Block;
import minerender.FileControl;
import minerender.Vector3;
import minerender.VoxelCt;
import mplayer.PlaySound;
import mplayer.Sound;
import mplayer.SoundEngine;
import mplayer.SoundHandler;

/**
 * @author Dunemask
 *
 */
public class MineCommands {
	public static ArrayList<String> commands = new ArrayList<String>();
	public static int latestCommand =-1;
	
	public static String getCommand(int get) {
		return commands.get(get);
	}
	
	
	public static void HandleCommand(String fullCommand) {
		commands.add(fullCommand);
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(fullCommand);
		while (m.find())
		    list.add(m.group(1));
		
		String[] com = list.toArray(new String[list.size()]);
		
		String command = com[0];
		if(!command.startsWith("/")) {
			System.out.println(fullCommand);
			return;
		}else {
			command = command.substring(1, command.length());
		}
		try {
			trycommand(com,command);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @param com
	 * @param command
	 */
	private static void trycommand(String[] com, String command) {
		if(command.equalsIgnoreCase("set")) {
			set(com);
			return;
		}
		if(command.equalsIgnoreCase("Help")) {
			help(com[1]);
			return;
		}
		if(command.equalsIgnoreCase("setblock")) {
			int x = Integer.parseInt(com[1]);
			int y = Integer.parseInt(com[2]);
			int z = Integer.parseInt(com[3]);
			String block = com[4];
			int wanted = 0;
			ArrayList<Block> names = Minecraft.vx.getVen().blks;
			for(int i=0;i<names.size();i++) {
				if(block.equalsIgnoreCase(names.get(i).getName())) {
					wanted = i+1;
					i=names.size();
				}
			}
			
			Minecraft.vx.getVen().setBlock(wanted, new Vector3(x,z,y));
			return;
		}
		
		if(command.equalsIgnoreCase("gamemode")) {
			if(com[1].equalsIgnoreCase("c")) {
				Minecraft.vx.changeMode(VoxelCt.Creative);
			}else if(com[1].equalsIgnoreCase("s")){
				Minecraft.vx.changeMode(VoxelCt.Survival);
			}else {
				System.out.println("Invalid Argument");
			}
			return;
		}
		
		if(command.equalsIgnoreCase("soundengine")) {
			if(com[1].equalsIgnoreCase("stopall")) {
				SoundEngine.stop(SoundEngine.allEngines);
				DMediaPlayer.getMediaPlayer().stop();
			}
			if(com[1].equalsIgnoreCase("play")) {
				if(com[2].startsWith("\"")&&com[2].endsWith("\"")) {
					com[2] = com[2].substring(1, com[2].length()-1);
				}
				System.out.println(SoundHandler.loadSong(com[2])+":"+com[2]);
				Sound wanted = SoundHandler.loadSong(com[2]);
				PlaySound.playSound(wanted);
			}
			return;
		}
		if(command.equalsIgnoreCase("save")) {
			FileControl.SaveFileAsXML(Minecraft.vx.getVen(), Minecraft.vx.getVen().getName());
			return;
		}
		if(command.equalsIgnoreCase("saveas")) {
			FileControl.SaveFileAsXML(Minecraft.vx.getVen(), com[1]);
			return;
		}
		if(command.equalsIgnoreCase("tp")||command.equalsIgnoreCase("teleport")) {
			if(com[1].equalsIgnoreCase("~")) {
				com[1] = ""+(int)Minecraft.vx.getVen().campos.x;
			}else if(com[1].contains("~")){
				com[1] = com[1].replace("~", "");
				com[1] = ""+(Integer.parseInt(com[1])+(int)Minecraft.vx.getVen().campos.x);
			}
			
			if(com[2].equalsIgnoreCase("~")) {
				com[2] = ""+(int)Minecraft.vx.getVen().campos.z;
			}else if(com[2].contains("~")){
				com[2] = com[2].replace("~", "");
				com[2] = ""+(Integer.parseInt(com[2])+(int)Minecraft.vx.getVen().campos.z);
			}
			if(com[3].equalsIgnoreCase("~")) {
				com[3] = ""+(int)Minecraft.vx.getVen().campos.y;
			}else if(com[3].contains("~")){
				com[3] = com[3].replace("~", "");
				com[3] = ""+(Integer.parseInt(com[3])+(int)Minecraft.vx.getVen().campos.y);
			}
			
			int x =Integer.parseInt(com[1]);
			int y = Integer.parseInt(com[3]);
			int z = Integer.parseInt(com[2]);
			
			
			Minecraft.vx.getVen().campos.x = x;
			Minecraft.vx.getVen().campos.y = y;
			Minecraft.vx.getVen().campos.z = z;
		}
		
	}


	/**
	 * @param com
	 */
	private static void help(String com) {
		
		
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
