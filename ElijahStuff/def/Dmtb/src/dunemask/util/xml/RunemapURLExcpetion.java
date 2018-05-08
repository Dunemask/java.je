package dunemask.util.xml;

public class RunemapURLExcpetion extends DMXMLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2161060316917457128L;

	public RunemapURLExcpetion() {
		// TODO Auto-generated constructor stub
	}

	public RunemapURLExcpetion(String arg0) {
		super("ONE OR MORE PATHS IS INVALID!"+"\r\n"+arg0);

	}

	public RunemapURLExcpetion(Throwable arg0) {
		super(arg0);

	}

	public RunemapURLExcpetion(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public RunemapURLExcpetion(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
