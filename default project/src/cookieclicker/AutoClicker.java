/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: cookieclicker.AutoClicker.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package cookieclicker;

/**
 * @author karib
 *
 */
public class AutoClicker extends Thread {
	
	
	public AutoClicker(){
		super(new Runnable() {

			@Override
			public void run() {
				while(true) {
				CookieClicker.cookies++;
				CookieClicker.cframe.repaint();
				CookieClicker.cframe.revalidate();
			//	System.out.println("Click");
				CookieClicker.cookiCounter.setText("Cookies:"+CookieClicker.cookies);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
				
			}
			
		});
		
	}

}