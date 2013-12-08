package com.kiyoos.threads.learn.safetyexample;

public class ThreadSafetyTest {

	public static void main(String[] args) throws InterruptedException {
		notThreadSafeDemo();
		System.out.println( " ---  Not ThreadsafeDemo complete. ");
		threadSafeDemo();

	}

	private static void notThreadSafeDemo() throws InterruptedException {
		final NotThreadSafeClass notThreadSafeObject = new NotThreadSafeClass();

		Thread p1 = new Thread(new Runnable() {
			public void run() {
				for (int count = 0; count < 50; count++) {
					notThreadSafeObject.setCount(notThreadSafeObject.getCount() + 1);
				}
			}
		}, "Firrrrrrrst");

		Thread p2 = new Thread(new Runnable() {
			public void run() {
				for (int count = 0; count < 50; count++) {
					notThreadSafeObject.setCount(notThreadSafeObject.getCount() + 1);
				}
			}
		}, "second");

		p1.start();
		p2.start();
		Thread.sleep(10000);
		System.out.println("Final value: " + notThreadSafeObject.getCount());

	}

	private static void threadSafeDemo() throws InterruptedException {
		final ThreadSafeClass threadSafeObject = new ThreadSafeClass();

		Thread p1 = new Thread(new Runnable() {
			public void run() {
				for (int count = 0; count < 50; count++) {
					threadSafeObject.setCount(threadSafeObject.getCount() + 1);
				}
			}
		}, "Thread Safe Firrrrrrrst");

		Thread p2 = new Thread(new Runnable() {
			public void run() {
				for (int count = 0; count < 50; count++) {
					threadSafeObject.setCount(threadSafeObject.getCount() + 1);
				}
			}
		}, "Thread Safe  second");

		p1.start();
		p2.start();
		Thread.sleep(10000);
		System.out.println("Final value: " + threadSafeObject.getCount());

	}

}
