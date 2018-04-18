package mc;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
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
			Minecraft.vx.printText(fullCommand);
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
		if(command.equalsIgnoreCase("devmode")) {
			if(com[1].equalsIgnoreCase("on")) {
				Minecraft.vx.setDeveloperMode(true);
			}else if(com[1].equalsIgnoreCase("off")) {
				Minecraft.vx.setDeveloperMode(false);
			}
		}
		
		if(command.equalsIgnoreCase("set")) {
			set(com);
			return;
		}
		if(command.equalsIgnoreCase("Help")) {
			try {
			help(com[1]);
			}catch(java.lang.ArrayIndexOutOfBoundsException exc) {
				Minecraft.vx.printText("Help Is Used to demonstrate commands");
			}
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
				Minecraft.vx.printText("Invalid Argument");
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
				try {
				//System.out.println(SoundHandler.loadSong(com[2])+":"+com[2]);
				Sound wanted = SoundHandler.loadSong(com[2]);
				PlaySound.playSound(wanted);
				}catch(Exception e) {
					Minecraft.vx.printText("Could Not Play|Find "+com[2]);
				}
			}
			if(com[1].equalsIgnoreCase("off")) {
				SoundEngine.run=false;
			}
			if(com[1].equalsIgnoreCase("on")) {
				SoundEngine.run=true;
			}
			return;
		}
		if(command.equalsIgnoreCase("give")) {
			int block = Minecraft.vx.getVen().getBlockIdByName(com[1]);
			Minecraft.vx.getVen().inventory[block-1]+=Integer.parseInt(com[2]);
			
			
			//inventory[Voxels[(int)v3.x][(int)v3.y][(int)v3.z]-1]++;
			
		}
		
		
		if(command.equalsIgnoreCase("save")) {
			CountDownLatch latch = new CountDownLatch(1);
			new Thread( ()->{
			FileControl.SaveFileAsXML(Minecraft.vx.getVen(), Minecraft.vx.getVen().getName());
			latch.countDown();
			}).start();
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Minecraft.vx.printText("Saved");
			return;
		}
		if(command.equalsIgnoreCase("saveas")) {
			CountDownLatch latch = new CountDownLatch(1);
			new Thread( ()->{
			FileControl.SaveFileAsXML(Minecraft.vx.getVen(), com[1]);
			latch.countDown();
			}).start();
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Minecraft.vx.printText("Saved as "+com[1]);
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
			return;
		}
		
		if(command.equalsIgnoreCase("fill")||command.equalsIgnoreCase("fill-replace")) {
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
			if(com[4].equalsIgnoreCase("~")) {
				com[4] = ""+(int)Minecraft.vx.getVen().campos.x;
			}else if(com[4].contains("~")){
				com[4] = com[4].replace("~", "");
				com[4] = ""+(Integer.parseInt(com[4])+(int)Minecraft.vx.getVen().campos.x);
			}
			
			if(com[5].equalsIgnoreCase("~")) {
				com[5] = ""+(int)Minecraft.vx.getVen().campos.z;
			}else if(com[5].contains("~")){
				com[5] = com[5].replace("~", "");
				com[5] = ""+(Integer.parseInt(com[5])+(int)Minecraft.vx.getVen().campos.z);
			}
			if(com[6].equalsIgnoreCase("~")) {
				com[6] = ""+(int)Minecraft.vx.getVen().campos.y;
			}else if(com[6].contains("~")){
				com[6] = com[6].replace("~", "");
				com[6] = ""+(Integer.parseInt(com[6])+(int)Minecraft.vx.getVen().campos.y);
			}
			int x =Integer.parseInt(com[1]);
			int y = Integer.parseInt(com[3]);
			int z = Integer.parseInt(com[2]);
			int x2 =Integer.parseInt(com[4]);
			int y2 = Integer.parseInt(com[6]);
			int z2 = Integer.parseInt(com[5]);
			String block = com[7];
			int wanted =0;
			ArrayList<Block> names = Minecraft.vx.getVen().blks;
			for(int i=0;i<names.size();i++) {
				if(block.equalsIgnoreCase(names.get(i).getName())) {
					wanted = i+1;
					i=names.size();
				}
			}
			if(command.equalsIgnoreCase("fill")) {
				fill(x,y,z,x2,y2,z2,wanted);
			}else {
				String rep = com[8];
				int replace = 0;
				for(int i=0;i<names.size();i++) {
					if(rep.equalsIgnoreCase(names.get(i).getName())) {
						replace = i+1;
						i=names.size();
					}
				}
				replaceFill(x,y,z,x2,y2,z2,wanted,replace);
				
			}
			return;
		}
		
	}


	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param block
	 */
	private static void fill(int x, int y, int z, int x2, int y2, int z2,int block) {
		for(int i=Math.min(x, x2);i<=Math.max(x, x2);i++) {
			for(int c=Math.min(y, y2);c<=Math.max(y, y2);c++) {
				for(int b=Math.min(z, z2);b<=Math.max(z, z2);b++) {
					Vector3 temp = new Vector3(i,c,b);
					Minecraft.vx.getVen().setBlock(block,temp);
					
				}
				
				
			}
			
			
		}
		
		
		
	}
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param block
	 */
	private static void replaceFill(int x, int y, int z, int x2, int y2, int z2,int block,int repl) {
		for(int i=Math.min(x, x2);i<=Math.max(x, x2);i++) {
			for(int c=Math.min(y, y2);c<=Math.max(y, y2);c++) {
				for(int b=Math.min(z, z2);b<=Math.max(z, z2);b++) {
					Vector3 temp = new Vector3(i,c,b);
					if(Minecraft.vx.getVen().getBlock(temp)==repl) {
					Minecraft.vx.getVen().setBlock(block,temp);
					}
					
				}
				
				
			}
			
			
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
