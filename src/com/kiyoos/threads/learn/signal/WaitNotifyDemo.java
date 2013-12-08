package com.kiyoos.threads.learn.signal;

public class WaitNotifyDemo implements Runnable {

	private MyResource resource;

	public WaitNotifyDemo(MyResource resource) {
		this.resource = resource;
	}

	public static void main(String[] args) {
		MyResource resource = new MyResource();
		Thread p1 = new Thread(new WaitNotifyDemo(resource), "1");
		Thread p2 = new Thread(new WaitNotifyDemo(resource), "2");
		Thread p3 = new Thread(new WaitNotifyDemo(resource), "3");
		/*Thread p4 = new Thread(new WaitNotifyDemo(resource), "4");
		Thread p5 = new Thread(new WaitNotifyDemo(resource), "5");
		Thread p6 = new Thread(new WaitNotifyDemo(resource), "6");*/

		p1.start();
		p2.start();
		p3.start();
		/*p4.start();
		p5.start();
		p6.start();*/
		
		try {
			p1.join();
			p2.join();p3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All r done.");

	}

	private void doWait() {
		synchronized (resource) {
			try {
				resource.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void doNotify() {
		synchronized (resource) {
			resource.notifyAll();
		}
	}

	private void doSomeWork() {
		for (int count = 0; count < 2; count++) {
			resource.work();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void doSomeMoreWork() {
		for (int count = 0; count < 3; count++) {
			resource.work();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void run() {

		synchronized (resource) {

			try {
				doSomeWork();
				resource.notifyAll();
				System.out.println(Thread.currentThread().getName() + "  wait.");
				resource.wait();
				System.out.println(Thread.currentThread().getName() + " again.");
				doSomeMoreWork();
				resource.notifyAll();
				
				System.out.println(Thread.currentThread().getName() + "  done.");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
