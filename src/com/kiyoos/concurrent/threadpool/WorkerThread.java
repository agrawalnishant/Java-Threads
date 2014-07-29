package com.kiyoos.concurrent.threadpool;

import com.kiyoos.concurrent.blockingQ.MyBlockingQueue;

public class WorkerThread extends Thread {

	private final MyBlockingQueue taskQueue;

	private final String name;

	private boolean isStopped;

	public WorkerThread(final MyBlockingQueue taskQueue, final String name) {
		this.taskQueue = taskQueue;
		this.name = name;

	}

	public void run() {

		while (!isStopped) {
			Runnable task = taskQueue.deque();
			task.run();
		}
	}

	public synchronized void stopIt() {
		isStopped = true;
		this.interrupt();

	}

	public boolean isStopped() {
		return isStopped;
	}

	@Override
	public String toString() {
		return "Worker[" + name + "]";
	}

}
