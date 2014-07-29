package com.kiyoos.concurrent.blockingQ;

import java.util.LinkedList;
import java.util.List;

public class MyBlockingQueue<E extends Runnable> {

	private final int limit;
	private final List<E> queue;

	public MyBlockingQueue(int limit) {
		this.limit = limit;
		this.queue = new LinkedList<E>();
	}

	public synchronized void enque(E e) {

		while (queue.size() >= limit) {
			try {

				//System.out.println(" Producer is going to wait.");
				wait();
				//System.out.println("Producer waited.");

			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		notifyAll();
		queue.add(e);

	}

	public synchronized E deque() {

		while (queue.size() <= 0) {
			try {
				//System.out.println("  ---  Consumer " + Thread.currentThread() +" is going to wait.");
				wait();

				//System.out.println("  --- Consumer"+ Thread.currentThread() +" waited.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();

		return queue.remove(0);

	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

}