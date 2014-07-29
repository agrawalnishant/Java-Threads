package com.kiyoos.concurrent.threadpool;

import java.util.LinkedList;

import com.kiyoos.concurrent.blockingQ.MyBlockingQueue;

public class MyThreadPoolImpl implements MyThreadPool {

	private Integer poolSize;

	private MyBlockingQueue<Runnable> taskQueue;

	private LinkedList<WorkerThread> workerThreads;

	private boolean isStopped;

	public MyThreadPoolImpl(MyBlockingQueue taskQueue, Integer poolSize) {
		this.taskQueue = taskQueue;
		this.poolSize = poolSize;
		workerThreads = new LinkedList<WorkerThread>();

		while (poolSize-- > 0) {
			workerThreads.add(new WorkerThread(taskQueue, ("" + (poolSize + 1))));
		}

		for (WorkerThread thread : workerThreads) {
			thread.start();
		}
	}

	@Override
	public void execute(Runnable task) {
		if (isStopped == true) {
			throw new IllegalStateException("ThreadPool is stopped");
		}
		this.taskQueue.offer(task);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() {
		isStopped = true;
		for (WorkerThread thread : workerThreads) {
			thread.stop();
			thread.stopIt();
		}

	}

}
