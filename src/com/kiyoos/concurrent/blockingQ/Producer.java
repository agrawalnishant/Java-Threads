package com.kiyoos.concurrent.blockingQ;

public class Producer implements Runnable {

	private MyBlockingQueue<MyResource> queue;

	private String name;

	public Producer(MyBlockingQueue<MyResource> queue, String name) {
		this.queue = queue;
		this.name = name;
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
			System.out.println(" Producer[" + name + "] >>>>>>> " + res);
			queue.enque(res);
		}

		queue.enque(new MyResource(-1));

	}

}
