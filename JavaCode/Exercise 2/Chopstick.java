public class Chopstick {
	private int ID;
	private boolean free;
// hint: use a local variable to indicate whether the chopstick is free 
//                        (lying on the table), e.g.  private boolean free;

	Chopstick(int ID) {
		this.ID = ID;
		free = true;
	}
	
	synchronized void take() {
		if (free){
			free = false;
		}else{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	synchronized void release() {
		if (!free){
			notify();
			free = true;
		}
	}
	    
	public int getID() {
	    return(ID);
	}
}
