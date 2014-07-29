package com.kiyoos.concurrent.blockingQ;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyBlockingQueue<E> implements Queue<E> {

	private final int limit;
	private final List<E> queue;

	public MyBlockingQueue(int limit) {
		this.limit = limit;
		this.queue = new LinkedList<E>();
	}

	@Override
	public synchronized boolean offer(E e) {

		while (queue.size() >= limit) {
			try {

				wait();

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		notifyAll();
		return queue.add(e);
	}
	
	
	
	

	@Override
	public synchronized E poll() {
		while (queue.size() <= 0) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();

		return queue.remove(0);
	}

	
	
	
	
	
	
	
	
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean add(E e) {
		return false;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

}
