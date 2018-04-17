package minerender;

import javax.swing.JFrame;

public abstract class Test {

	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.setVisible(true);
		f.setSize(500,500);
		f.setTitle("TEST");
		f.setLocationRelativeTo(null);
		f.setEnabled(true);
		//f.setLayout(null);
		FilePanel fp = new FilePanel();
		f.getContentPane().add(fp);
		//VoxelCt vct = new VoxelCt();
		//System.out.print("SDFJIOJWEIOFEWFHEFUIHIUHEFUIHUIEOFH");
		//f=vct;
		//f.add(c);
		
		f.repaint();
		f.revalidate();
			
		/*VoxEn e = new VoxEn(new Vector3(0,3,5),20,20,20,1);
		File f = new File(System.getProperty("user.home")+"/Desktop/tmp.xml");
		XMLMap test = new XMLMap(f,"FIOJ");
		test.writeContainer("FIOJ/Cookie/");
		test.writeElement("FIOJ/Cookie/FirstElement", "Hello World");
		//test.pullValue(url)
		XMLMap second = XMLMap.ParseDXMLMap(f);
		FileControl.SaveFileAsXML(e, "DOl");
		VoxEn der =FileControl.LoadFileXML("DOl");
		
		der.campos.print();*/
	}

}
