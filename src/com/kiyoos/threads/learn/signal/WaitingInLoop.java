package com.kiyoos.threads.learn.signal;

public class WaitingInLoop implements Runnable {

	private MySignal mySignal;

	public WaitingInLoop(MySignal mySignal) {
		this.mySignal = mySignal;
	}

	public void run() {

		int counter = 0;

		synchronized (mySignal) {

			System.out.println(Thread.currentThread() + " Getting lock");
			mySignal.setFree(false);

			while (++counter < 5) {
				System.out.println(Thread.currentThread()  + " is working.");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println(Thread.currentThread().getName() + " releasing lock.");
			mySignal.setFree(true);

		}

	}

	public static void main(String[] args) {
		MySignal mySignal = new MySignal();
		Thread p1 = new Thread(new WaitingInLoop(mySignal), "P 1");
		Thread p2 = new Thread(new WaitingInLoop(mySignal), "P   2");
		Thread p3 = new Thread(new WaitingInLoop(mySignal), "P     3");
		Thread p4 = new Thread(new WaitingInLoop(mySignal), "P       4");
		
		p4.start();
		p3.start();
		p2.start();
		p1.start();
		
		
	}

}
