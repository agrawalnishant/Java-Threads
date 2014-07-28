package com.kiyoos.threads.learn.locks.prodcons;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerLockConsumerLock {

	private AtomicBoolean isEmpty = new AtomicBoolean();

	private AtomicBoolean isFull = new AtomicBoolean();

	private Integer[] queue;

	private final int size;

	private ReentrantLock mainLock = new ReentrantLock();

	private Condition full = mainLock.newCondition();

	private Condition empty = mainLock.newCondition();

	private AtomicInteger count = new AtomicInteger();

	public ProducerLockConsumerLock(int sz) {
		this.size = sz;
		queue = new Integer[sz];
		isEmpty.set(true);
		isFull.set(false);
	}

	public void add(Integer value) {

		if (mainLock.tryLock()) {
			try {
				while (isFull.get() == true) {
					System.out.println("Is Full");
					full.await();
				}

				queue[count.getAndIncrement()] = value;

				if (count.get() == size) {
					System.out.println("Is FUll now. Count: " + count.get());
					isFull.compareAndSet(false, true);
				}

				isEmpty.compareAndSet(true, false);
				empty.signalAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mainLock.unlock();
			}
		}

	}

	public Integer get() {
		Integer value = null;
		if (mainLock.tryLock()) {
			try {
				while (isEmpty.get() == true) {

					System.out.println("Is Empty");
					empty.await();
					;
				}

				int pos = count.getAndDecrement();
				value = queue[pos - 1];
				queue[pos - 1] = null;

				if (pos == 0) {
					isEmpty.compareAndSet(false, true);
				}

				isFull.compareAndSet(false, true);
				full.signalAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mainLock.unlock();
			}
		}

		return value;

	}

	public static void main(String[] args) {
		final ProducerLockConsumerLock twoLockSys = new ProducerLockConsumerLock(10);
		ExecutorService producers = Executors.newFixedThreadPool(2);
		ExecutorService consumers = Executors.newFixedThreadPool(2);

		producers.execute(new Runnable() {

			@Override
			public void run() {
				for (int count = 1; count < 20; count++) {
					int num = ThreadLocalRandom.current().nextInt(4, 100);
					System.out.println("Added : " + num);
					twoLockSys.add(num);
				}
			}

		});

		consumers.execute(new Runnable() {

			@Override
			public void run() {
				for (int count = 1; count < 15; count++) {
					int num = ThreadLocalRandom.current().nextInt(4, 100);
					System.out.println("Got: " + twoLockSys.get());
				}

			}
		});

	}
}
