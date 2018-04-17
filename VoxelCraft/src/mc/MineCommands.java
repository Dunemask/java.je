package mc;
/**
 * 
 */

import minerender.VoxelCt;

/**
 * @author Dunemask
 *
 */
public class MineCommands {
	
	
	public static void HandleCommand(String fullCommand) {
		
		String[] com = fullCommand.split("\\s+");
		String command = com[0];
		if(!command.startsWith("/")) {
			System.out.println(fullCommand);
		}else {
			command = command.substring(1, command.length());
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
	

}
