package com.kiyoos.threads.learn.signal;

public class MyResource {
	
	public synchronized void work() {
		System.out.println(Thread.currentThread().getName() + " is using me.");
		
	}

}
