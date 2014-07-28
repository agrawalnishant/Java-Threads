package com.kiyoos.threads.learn.signal.two;

public class Consumer implements Runnable {

	private MyBlockingQueue<MyResource> queue;

	public Consumer(MyBlockingQueue<MyResource> queue) {
		this.queue = queue;
	}

	public void run() {

		int response = 0;
		while (response != -1) {
			response = queue.deque().getId();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("  ------  Consuming Resource # " + response);
		}

	}

}
