package com.kiyoos.threads.learn.signal;

public class BusyWaitingNoSynchronized implements Runnable {

	private MySignal mySignal;

	public BusyWaitingNoSynchronized(MySignal mySignal) {
		this.mySignal = mySignal;
	}

	public void run() {

		int counter = 0;

		while (!mySignal.isFree()) {
			System.out
					.println(Thread.currentThread().getName() + " is waiting");
		}

		mySignal.setFree(false);

		System.out.println("\t" + Thread.currentThread().getName() + " Got lock");

		while (++counter < 5) {
			System.out.println("\t\t" + Thread.currentThread().getName()
					+ " is working.");
			/*try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

		System.out.println(Thread.currentThread().getName()
				+ " releasing lock.");
		mySignal.setFree(true);

	}

	public static void main(String[] args) {
		MySignal mySignal = new MySignal();
		Thread p1 = new Thread(new BusyWaitingNoSynchronized(mySignal), "1");
		Thread p2 = new Thread(new BusyWaitingNoSynchronized(mySignal), " 2");
		Thread p3 = new Thread(new BusyWaitingNoSynchronized(mySignal),
				"  3");
		Thread p4 = new Thread(new BusyWaitingNoSynchronized(mySignal),
				"   4");

		p4.start();
		p3.start();
		p2.start();
		p1.start();

	}

}
