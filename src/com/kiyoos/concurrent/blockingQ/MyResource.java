package com.kiyoos.concurrent.blockingQ;

public class MyResource implements Runnable {

	private static Integer counter = 0;

	private Integer id = 0;

	public MyResource(Integer id) {
		this.id = id;
	}

	public void run() {
		System.out.println("Thread [" + Thread.currentThread() + " ]  -- " + this + " working");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Res[" + id + "]";
	}

	public static class Factory {
		public static MyResource get() {

			return new MyResource(++counter);

		}
	}

}
