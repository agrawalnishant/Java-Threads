package com.kiyoos.threads.learn.signal;

public class MySignal {

	private boolean isFree = true;

	/**
	 * @return the isFree
	 */
	public synchronized boolean isFree() {
		return isFree;
	}

	/**
	 * @param isFree
	 *            the isFree to set
	 */
	public synchronized void setFree(boolean isFree) {
		this.isFree = isFree;
	}

}
