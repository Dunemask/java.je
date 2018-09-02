package minerender;
//Block class, it stores all of the properties of each block type...
public class Block {
	boolean tangible;
	public boolean isTangible() {
		return tangible;
	}
	public boolean isOpaque() {
		return opaque;
	}
	public String getName() {
		return name;
	}
	public int[] getImage() {
		return image;
	}
	public int getBreakTime() {
		return breaktime;
	}
	boolean opaque;
	String name;
	int[] image;
	int breaktime;
	public Block(boolean tangible, boolean opaque, int[] image, String name,int bt) {
		this.tangible = tangible;
		this.opaque = opaque;
		this.image = image;
		this.name = name;
		this.breaktime = bt;
	}

}
