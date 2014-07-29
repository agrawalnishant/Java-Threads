package com.kiyoos.concurrent.blockingQ.test;

import com.kiyoos.concurrent.blockingQ.Consumer;
import com.kiyoos.concurrent.blockingQ.MyBlockingQueue;
import com.kiyoos.concurrent.blockingQ.MyResource;
import com.kiyoos.concurrent.blockingQ.Producer;

public class MyBlockingQueueTest {

	private static MyBlockingQueue<MyResource> blockingQueue;

	private static Producer producer;
	private static Consumer consumer1;
	private static Consumer consumer2;

	public static void main(String[] args) {

		blockingQueue = new MyBlockingQueue<MyResource>(5);
		producer = new Producer(blockingQueue,"1");
		consumer1 = new Consumer(blockingQueue, "1");
		consumer2 = new Consumer(blockingQueue,"2");

		new Thread(consumer1).start();
		new Thread(consumer2).start();
		new Thread(producer).start();

	}

}
