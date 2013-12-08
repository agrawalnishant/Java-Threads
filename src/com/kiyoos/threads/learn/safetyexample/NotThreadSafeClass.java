package com.kiyoos.threads.learn.safetyexample;

public class NotThreadSafeClass {
	
	private  Integer count = 0;

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		System.out.println(Thread.currentThread() + ": " + count);
		this.count = count;
	}
	
	
	public static void main(String[] args) {
		
	}
	
	

}
