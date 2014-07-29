package com.kiyoos.concurrent.blockingQ;

public class Consumer implements Runnable {

	private MyBlockingQueue<MyResource> queue;
	private String name;

	public Consumer(MyBlockingQueue<MyResource> queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	public void run() {

		int response = 0;
		MyResource responseRes = null;
		while (response != -1) {
			responseRes = queue.deque();
			response = responseRes.getId();
			responseRes.run();
			System.out.println(" \t Consumer[" + name + "]  <<<<<<< " + response);
		}
		
		//Propagate the STOP signal so that other threads can stop.
		queue.enque(responseRes);

	}

}
