package com.kiyoos.threads.learn.signal.two;

public class MyBlockingQueueTest {

	private static MyBlockingQueue<MyResource> blockingQueue;

	private static Producer producer;
	private static Consumer consumer;

	public static void main(String[] args) {

		blockingQueue = new MyBlockingQueue<MyResource>(5);
		producer = new Producer(blockingQueue);
		consumer = new Consumer(blockingQueue);

		new Thread(consumer).start();
		new Thread(producer).start();

	}

}
