package com.kiyoos.threads.learn.safetyexample;

public class ThreadSafeClass {

	private Integer count = 0;

	/**
	 * @return the count
	 */
	public synchronized Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public synchronized void setCount(Integer count) {
		System.out.println(Thread.currentThread() + ": " + count);
		this.count = count;
	}

}
