public class Philosopher extends Thread {
	private GraphicTable table;
	private Chopstick first;
	private Chopstick second;
	private int ID;
	final int timeThink_max = 5000;
	final int timeNextFork = 100;
	final int timeEat_max = 5000;
	
	Philosopher(int ID, GraphicTable table, Chopstick left, Chopstick right) {
		this.ID = ID;
		this.table = table;

		if (ID%2 == 1){
			this.first = left;
			this.second = right;
		}else{
			this.first = right;
			this.second = left;
		}
		setName("Philosopher "+ID);
	}
	
	public void run() {
		while(true){
			
			// Tell the table GUI that I am thinking
			table.isThinking(ID);
			// Print to console that I am thinking
			System.out.println(getName()+" thinks");
			
			// Let the thread sleep (in order to simulate thinking time)
			try {
				sleep((long)(Math.random()*timeThink_max));
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			
			// Done with thinking
			System.out.println(getName()+" finished thinking"); 
			 
			// and now I am hungry!
			System.out.println(getName()+" is hungry"); 
			// Tell the GUI I am hungry...
			table.isHungry(ID);
			
			// Let's try to get the left chopstick
			if (ID%2 == 1){
				System.out.println(getName()+" wants left chopstick");
			}else{
				System.out.println(getName()+" wants right chopstick");
			}
			first.take();
			
			// Tell the GUI that I took the left chopstick
			table.takeChopstick(ID, first.getID());

			if (ID%2 == 1){
				System.out.println(getName()+" got left chopstick");
			}else{
				System.out.println(getName()+" got right chopstick");
			}

			// I'll wait a bit before I try to get the next chopstick (it's philosopher's etiquette)
			try {
				sleep(timeNextFork);
			} catch(InterruptedException e) {
				System.out.println(e);
			} 
			

			// Ok, enough etiquette nonesense, now I need my right chopstick
			if (ID%2 == 1){
				System.out.println(getName()+" wants right chopstick");
			}else{
				System.out.println(getName()+" wants left chopstick");
			}
			
			this.second.take();


			// Got it!
			table.takeChopstick(ID, second.getID());
			if (ID%2 == 1){
				System.out.println(getName()+" got right chopstick");
			}else{
				System.out.println(getName()+" got left chopstick");
			}
			
			// Sweet taste of steamed rice....
			System.out.println(getName()+" eats"); 
			try {
				sleep((long)(Math.random()*timeEat_max));
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			
			// Ok, I am really full now
			System.out.println(getName()+" finished eating"); 
			
			// I just realized I did not wash these chopsticks 
			// and the philosopher on my right is coming down with a flu 
			
			// I'll release the left chopstick


			if (ID%2 == 1){
				table.releaseChopstick(ID, first.getID());
				this.first.release();
			}else{
				table.releaseChopstick(ID, second.getID());
				this.second.release();			
			}
			System.out.println(getName()+" released left chopstick");

			// I'll release the right chopstick
			table.releaseChopstick(ID, second.getID());
			second.release();

			if (ID%2 == 1){
				table.releaseChopstick(ID, second.getID());
				second.release();
			} else{
				table.releaseChopstick(ID, first.getID());
				this.first.release();
			}
			System.out.println(getName()+" released right chopstick");
		}
	}
}
