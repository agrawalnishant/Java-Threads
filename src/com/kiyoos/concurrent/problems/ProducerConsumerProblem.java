package com.kiyoos.concurrent.problems;

import java.util.ArrayDeque;
import java.util.Queue;

import com.kiyoos.concurrent.blockingQ.Consumer;
import com.kiyoos.concurrent.blockingQ.MyResource;
import com.kiyoos.concurrent.blockingQ.Producer;

public class ProducerConsumerProblem {

	private static Producer producer;
	private static Consumer consumer1;

	private static final int PRODUCTION_LIMIT = 20;

	private static Queue<MyResource> queue;

	public static void main(String[] args) {

		/**
		 * Even though queue size id 5, producer adds all 20 elements to it.
		 */
		queue = new ArrayDeque<MyResource>(5);
		producer = new Producer(queue, "1", PRODUCTION_LIMIT);
		consumer1 = new Consumer(queue, "1");

		new Thread(producer).start();

		new Thread(consumer1, consumer1.getName()).start();

	}
}
