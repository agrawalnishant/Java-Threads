package com.kiyoos.threads.learn.latch.barrier;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierBatchProducerConsumer {

	private final int QUEUE_LENGTH = 5;
	
	
	public void produceAndCOnsumeBatch() {
		
	}
	
	static class Producer implements Runnable{
		
		ArrayBlockingQueue<UnitOfWork> workQueue;
		
		public Producer(ArrayBlockingQueue<UnitOfWork> workQueue, CyclicBarrier ) {
			this.workQueue = workQueue;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	static class UnitOfWork {
		public void doWork() {
			System.out.println("Unit of work done.");
		}
	}

}
