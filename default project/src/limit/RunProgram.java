/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * File: limit.RunProgram.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 * <p>Belongs to Package {@link limit }</p>
 */
package limit;

import java.util.LinkedHashMap;

/**
 * @author Elijah
 *  <p>Belongs to Package {@link limit }</p>
 */
public class RunProgram implements ExternalRun {

	public RunProgram() {
		
	}
	


	public LinkedHashMap<String,Runnable> addAll() {
		LinkedHashMap<String,Runnable> mp = new LinkedHashMap<>();
		mp.put("hello", sayHello());
		mp.put("intro", sayWhatYouDo());
		mp.put("result", sayResult());
		mp.put("end", sayGoodbye());
		
		return mp;
		
	}


	/* (non-Javadoc)
	 * @see limit.ExternalRun#sayHello()
	 */
	@Override
	public Runnable sayHello() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello I am your Program! :D");
				
			}
			
		};
		
		
		return runnable;
	}

	/* (non-Javadoc)
	 * @see limit.ExternalRun#sayWhatYouDo()
	 */
	@Override
	public Runnable sayWhatYouDo() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("I Am going to calculate the meaning of life the universe and everything!");
				
			}
			
		};
		
		
		return runnable;
	}
	
	static boolean calc;
	
	private Runnable calculating = new Runnable() {

		@Override
		public void run() {
			System.out.print("Calculating");
			while(calc) {
				System.out.print(".");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			}

			
		}
		
	};

	
	/* (non-Javadoc)
	 * @see limit.ExternalRun#sayResult()
	 */
	@Override
	public Runnable sayResult() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				
					calc = true;
					new Thread(calculating).start();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					calc = false;
					System.out.println(".");
					System.out.println("Done");
					
				
				System.out.println("The Answer To Life The Universe and everything is 42");
				
			}
			
		};
		
		
		return runnable;
	}

	/* (non-Javadoc)
	 * @see limit.ExternalRun#sayGoodbye()
	 */
	@Override
	public Runnable sayGoodbye() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("The question isn't good enough so I shall make another one, using 42");
				
			}
			
		};
		
		
		return runnable;
	}


	

}
