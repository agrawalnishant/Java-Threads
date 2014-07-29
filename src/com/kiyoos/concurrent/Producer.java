package com.kiyoos.concurrent;

public class Producer implements Runnable {

	private MyBlockingQueue<MyResource> queue;

	public Producer(MyBlockingQueue<MyResource> queue) {
		this.queue = queue;
	}

	public void run() {
		for (int count = 0; count < (queue.getLimit() * 4); count++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MyResource res = MyResource.Factory.get();
			System.out.println("Producing " + res);
			queue.enque(res);
		}

		queue.enque(new MyResource(-1));

	}

}
