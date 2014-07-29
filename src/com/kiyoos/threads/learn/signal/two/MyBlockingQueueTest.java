package com.kiyoos.threads.learn.signal.two;

public class MyBlockingQueueTest {

	private static MyBlockingQueue<MyResource> blockingQueue;

	private static Producer producer;
	private static Consumer consumer1;
	private static Consumer consumer2;

	public static void main(String[] args) {

		blockingQueue = new MyBlockingQueue<MyResource>(5);
		producer = new Producer(blockingQueue);
		consumer1 = new Consumer(blockingQueue);
		consumer2 = new Consumer(blockingQueue);

		new Thread(consumer1).start();
		new Thread(consumer2).start();
		new Thread(producer).start();

	}

}
