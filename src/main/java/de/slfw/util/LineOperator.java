package de.slfw.util;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 
 * <p>A class to manage lines.</p>
 *
 * The {@link LineOperator} works as an que. (First in first out) You can also peek and poll. Additional the
 * interface {@link Iterable} was implemented to iterate over the {@link LineOperator} for example with the for each.
 * @author Alex S.
 * 
 *
 * @param <E>
 */
public class LineOperator<E> implements Iterable<E> {	
	
	private ConcurrentLinkedQueue<E> que = new ConcurrentLinkedQueue<E>();

	public LineOperator() {}
	
	public LineOperator(List<E> e){
		for(E element : e){
			addLine(element);
		}
	}
	
	/**
	 * Adds a line at the last position of the line que. 
	 * @param line the line to add
	 */
	public void addLine(E line){
		que.add(line);
	}
	
	/**
	 * returns the first line in the line que but dosn't remove it. Returns <code>null</code> if there is no line in the line que. 
	 * @return a line
	 */
	public E getLinePeek(){
		return que.peek();
	}
	
	/**
	 * returns the first line in the line que and removes it. Returns <code>null</code> if there is no line in the line que. 
	 * @return a line
	 */
	public E getLinePoll(){
		return que.poll();
	}

	public Iterator<E> iterator() {
		return que.iterator();
		//return new Itr();
	}

	/*
	private class Itr implements Iterator<E>{

		int count = 0;
		
		
		public boolean hasNext() {
			return count < vec.size();
		}

		public E next() {
			E next = vec.get(count);
			count++;
			return next;
		}

		public void remove() {
			count--;
			vec.remove(count);			
		}
		
	}
	*/
}
