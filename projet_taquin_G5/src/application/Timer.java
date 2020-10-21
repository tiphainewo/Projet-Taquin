package application;


public class Timer extends Thread{
	private int time;
	public boolean stop;
	public Timer() {
		this.time = 0;
		this.stop = false;
	}
	
	@Override
	public void run() {
		while(!stop) {
			time++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(time);
			
		}
	}

}
