package de.slfw.algo;

public class Node<T> {

	

	
	private long value;
	private T object;
	private Node<T> left=null;
	private Node<T> right=null;
	
	
	public Node(T object) {
		this.object = object;
		value=object.hashCode();
	}

	
	public T getObject() {
		return object;
	}

	
	public long getValue() {
		return value;
	}

	/**
	 * @return the left
	 */
	public Node<T> getLeft() {
		return left;
	}


	/**
	 * @return the right
	 */
	public Node<T> getRight() {
		return right;
	}


	/**
	 * @param left the left to set
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
	}


	/**
	 * @param right the right to set
	 */
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	
	@Override
	public String toString() {
		return "Hash:" + String.valueOf(value) + " Class:"+ object.getClass();
	}
	
	
	
}
