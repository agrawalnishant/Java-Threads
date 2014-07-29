package com.kiyoos.concurrent.threadpool.test;

import com.kiyoos.concurrent.blockingQ.MyBlockingQueue;
import com.kiyoos.concurrent.blockingQ.MyResource;
import com.kiyoos.concurrent.threadpool.MyThreadPool;
import com.kiyoos.concurrent.threadpool.MyThreadPoolImpl;

public class MyThreadPoolTest {

	// An interesting observation: decreasing queue size increases pool
	// throughput.
	private static final int QUEUE_SIZE = 5;

	private static final int THREAD_POOL_SIZE = 3;

	private static final int TASK_COUNT = 30;

	public static void main(String[] args) {

		MyBlockingQueue<Runnable> queue = new MyBlockingQueue<Runnable>(QUEUE_SIZE);

		MyThreadPool threadPool = new MyThreadPoolImpl(queue, THREAD_POOL_SIZE);

		int counter = TASK_COUNT;
		while (counter-- > 0) {
			threadPool.execute(MyResource.Factory.get());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadPool.stop();

	}

}
