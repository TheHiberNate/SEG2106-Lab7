public class Chopstick {
	private int ID;
	private boolean chopstickFree;
// hint: use a local variable to indicate whether the chopstick is free 
//                        (lying on the table), e.g.  private boolean free;

	Chopstick(int ID) {
		  this.ID = ID;
		  chopstickFree = true;
	}
	
	synchronized void take()  {
		if (chopstickFree) {
			chopstickFree = false;
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized void release() {
		notify();
		chopstickFree = true;
	}
	    
	public int getID() {
	    return(ID);
	}
}
