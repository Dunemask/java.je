/**
 * 
 */
package randomcontest;

/**
 * @author Dunemask
 *
 */
public class Connection {

	private float val;
	private Onode onode;
	private Inode inode;

	/**
	 * 
	 */
	public Connection(Inode inode,Onode onode,float con) {
		this.setInode(inode);
		this.setOnode(onode);
		this.setVal(con);
	}

	/**
	 * @return the inode
	 */
	public Inode getInode() {
		return inode;
	}

	/**
	 * @param inode the inode to set
	 */
	public void setInode(Inode inode) {
		this.inode = inode;
	}

	/**
	 * @return the onode
	 */
	public Onode getOnode() {
		return onode;
	}

	/**
	 * @param onode the onode to set
	 */
	public void setOnode(Onode onode) {
		this.onode = onode;
	}

	/**
	 * @return the val
	 */
	public float getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(float val) {
		this.val = val;
	}

	@Override
	public String toString() {
		String ret = "";
		ret = "("+this.inode+","+this.onode+","+this.val+")";
		return ret;
	}
}
