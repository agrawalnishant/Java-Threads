package com.kiyoos.concurrent.blockingQ;

import java.util.Queue;

public class Consumer<Q extends Queue> implements Runnable {

	private Queue<MyResource> queue;
	private String name;

	public Consumer(Q queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	public void run() {

		int response = 0;
		MyResource responseRes = null;
		while (response != -1) {
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			responseRes = queue.poll();
			response = responseRes.getId();
			System.out.println(" \t Consumer[" + name + "]  <<<<<<< " + response);
			responseRes.run();

		}

		// Propagate the STOP signal so that other threads can stop.
		queue.offer(responseRes);

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
