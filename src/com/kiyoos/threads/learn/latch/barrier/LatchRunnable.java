package com.kiyoos.threads.learn.latch.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Observer that participating threads 
 * @author _root_nishi
 *
 */
public class LatchRunnable {

	public static void main(String[] args) throws InterruptedException {
		Worker[] workers = new Worker[3];
		CountDownLatch latch = new CountDownLatch(workers.length - 1);

		Worker firstWorker = new Worker("first", latch);
		Worker secondWorker = new Worker("second", latch);
		Worker thirdWorker = new Worker("third", latch);

		ExecutorService pool = Executors.newFixedThreadPool(2);
		pool.submit(firstWorker);
		pool.submit(secondWorker);
		pool.submit(thirdWorker);

		latch.await();

		System.out.println(Thread.currentThread().getName() + " is done.");

	}

	static class Worker implements Runnable {

		private CountDownLatch latch;
		private String name;

		public Worker(String name, CountDownLatch latch) {
			this.latch = latch;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				doSomeWork();
				latch.countDown();
				doSomeMoreWork();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void doSomeMoreWork() throws InterruptedException {
			Thread.sleep(1000);
			System.out.println(name + " is doing More work in thread: "
					+ Thread.currentThread().getName());

		}

		private void doSomeWork() throws InterruptedException {
			System.out.println(name + " is doing Some workin thread: "
					+ Thread.currentThread().getName());

		}

	}

}
