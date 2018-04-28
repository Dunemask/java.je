package vbox;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dunemask.dm.CMD;
import dunemask.dunemasking.Capture;

public class VBoxInit {

	public static void main(String[] args) {
		
		String num = JOptionPane.showInputDialog("Which Drive Number:");
		if(num==null) {
			System.exit(0);
		}
		Capture.startConsole();
		num = num.replace(" ", "");
		ArrayList<String> com = new ArrayList<String>();
		com.add("echo Cookie!");
		com.add(CMD.delFile("C:\\Users\\dunemask\\VirtualBox VMs\\Removeable Kali\\Removeable Kali.vmdk\""));
		String command = "VBoxManage internalcommands createrawvmdk -filename \"C:\\Users\\dunemask\\VirtualBox VMs\\Removeable Kali\\Removeable Kali.vmdk\" -rawdisk \\\\.\\PhysicalDrive"+num;
		com.add("cd C:\\Program Files\\Oracle\\VirtualBox\\");
		com.add(command);
		com.add("pause");
		//CMD.openElevatedCmd(com);
		Capture.closeConsole();
		
		
		
		
		
	}

}
