package com.kiyoos.concurrent.threadpool;

public interface MyThreadPool {

	public void execute(Runnable task);

	public void stop();

}
