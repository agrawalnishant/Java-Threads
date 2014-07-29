package com.kiyoos.concurrent.blockingQ;

import java.util.Queue;

public class Producer<Q extends Queue> implements Runnable {

	private Queue<MyResource> queue;

	private String name;

	private final int limit;

	public Producer(Q queue, String name, int limit) {
		this.queue = queue;
		this.name = name;
		this.limit = limit;
	}

	public void run() {
		for (int count = 0; count < limit; count++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MyResource res = MyResource.Factory.get();
			System.out.println(" Producer[" + name + "] >>>>>>> " + res);
			queue.offer(res);
		}

		queue.offer(new MyResource(-1));

	}

}
