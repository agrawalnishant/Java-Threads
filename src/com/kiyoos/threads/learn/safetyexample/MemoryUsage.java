package com.kiyoos.threads.learn.safetyexample;

public class MemoryUsage implements Runnable {
	
	private Integer UseMemory = new Integer(100);
	private String[] useMoreMemory = {"h","e","l","l","o"};

	@Override
	public void run() {
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		for(int count = 0; count <100; count++){
			new Thread(new MemoryUsage(),"" + count).start();
		}
	}
	
	

}
