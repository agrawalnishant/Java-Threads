package com.kiyoos.threads.learn.latch.barrier;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Observer that participating threads
 * 
 * @author _root_nishi
 * 
 */
public class LatchCallableBounded {

	public static final int WORKER_COUNT = 30;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		Collection<Worker> workers = new LinkedList<Worker>();
		CountDownLatch latch = new CountDownLatch(WORKER_COUNT);

		int count = WORKER_COUNT;
		while (count-- > 0) {
			workers.add(new Worker("" + (count), latch));
		}

		// Observe that if queue is bounded to half the worker count
		// (WORKER_COUNT / 2) , it will
		// lead to RejectedExectuionExecption.
		ExecutorService execPool = new ThreadPoolExecutor(WORKER_COUNT / 10, WORKER_COUNT / 10, 100, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(WORKER_COUNT));
		List<Future<Long>> results = execPool.invokeAll(workers);

		latch.await();
		System.out.println("-------- DONE ---------");

		long totalTime = 0;
		for (Future<Long> result : results) {
			totalTime += result.get();
			System.out.println("totalTime:" + totalTime);
		}

		System.out.println("Time by main: " + (System.currentTimeMillis() - start));

	}

	static class Worker implements Callable<Long> {

		private CountDownLatch latch;
		private String name;

		public Worker(String name, CountDownLatch latch) {
			this.latch = latch;
			this.name = name;
		}

		private void doSomeMoreWork() throws InterruptedException {
			Long time = new Double(500 * Math.random()).longValue();
			Thread.sleep(time);
			System.out.println(name + " is doing More work in thread: " + Thread.currentThread().getName());

		}

		private void doSomeWork() throws InterruptedException {
			System.out.println(name + " is doing Some workin thread: " + Thread.currentThread().getName());

		}

		@Override
		public Long call() throws Exception {
			long start = System.currentTimeMillis();
			try {
				doSomeWork();
				latch.countDown();
				doSomeMoreWork();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return (System.currentTimeMillis() - start);
		}

	}

}
