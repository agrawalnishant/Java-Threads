package com.kiyoos.concurrent.threadpool;

import com.kiyoos.concurrent.blockingQ.MyBlockingQueue;
import com.kiyoos.concurrent.blockingQ.MyResource;

public class WorkerThread extends Thread {

	private final MyBlockingQueue<Runnable> taskQueue;

	private final String name;

	private boolean isStopped;

	public WorkerThread(final MyBlockingQueue<Runnable> taskQueue, final String name) {
		this.taskQueue = taskQueue;
		this.name = name;

	}

	public void run() {

		while (!isStopped) {
			Runnable task = taskQueue.poll();
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
