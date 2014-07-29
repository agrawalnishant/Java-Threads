package com.kiyoos.threads.learn.signal.two;

public class Consumer implements Runnable {

	private MyBlockingQueue<MyResource> queue;

	public Consumer(MyBlockingQueue<MyResource> queue) {
		this.queue = queue;
	}

	public void run() {

		int response = 0;
		MyResource responseRes = null;
		while (response != -1) {
			responseRes = queue.deque();
			response = responseRes.getId();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" -----" + Thread.currentThread() + " ---  Consuming Resource # " + response);
		}
		
		//Propagate the STOP signal so that other threads can stop.
		queue.enque(responseRes);

	}

}
