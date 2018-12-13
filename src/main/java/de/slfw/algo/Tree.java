package de.slfw.algo;

public class Tree<T> {

	private Node<T> root;
	
	public Tree() {
		root = null;
	}
	
	public Tree(Node<T> root) {
		this.root = root;
	}
	
	
	
	public Node<T> add(Node<T> node){
		if(root == null){
			root=node;
		}
		else
			add(root, node);
		
		return null;
	}
	
	public Node<T> add(Node<T> root ,Node<T> node){
		if(root.getValue()==node.getValue())
			return null;
		
		if(root.getValue() > node.getValue()){
			if(root.getLeft()==null){
				root.setLeft(node);
			}
			else
				add(root.getLeft(), node);
		}
		else
		{
			if(root.getRight()==null){
				root.setRight(node);
			}
			else
				add(root.getRight(),node);
		}
		return node;
	}
	

	
}
