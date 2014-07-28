package com.kiyoos.threads.learn.signal.two;

public class MyResource {

	private static Integer counter = 0;

	private Integer id = 0;

	public MyResource(Integer id) {
		this.id = id;
	}

	private void doSomething() {
		System.out.println(id + " is doing something");
	}

	static class Factory {
		public static MyResource get() {

			return new MyResource(++counter);

		}
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyResource [id=" + id + "]";
	}

}
