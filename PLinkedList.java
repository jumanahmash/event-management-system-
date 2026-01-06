package EventMangement;

import java.io.Serializable;

public class PLinkedList<T> implements Serializable {
	
	private Node<T> head;


	public  PLinkedList() {
		head=null;

	}
	public boolean isEmpty() {
		return head == null;
		
	}

	public void insertAtFront(T item) {
		Node<T> newNode=new Node<>(item);
		newNode.setNext(head);
		if(isEmpty()) 
			head = newNode;
		else 
			head=newNode;

	}
	public T remove(T item) {
		if(isEmpty())
			return null;
		Node<T> current=head;
		Node<T> previous=null;
		while(current!=null && !current. getData().equals(item)) {
			previous=current;
		current=current.getNext();
		}
		if(current==null)
			return null;
		T remoedData=current.getData();
		if(previous== null) {
			head=head.getNext();
		}else {
			previous.setNext(current.getNext());
		}
		return remoedData;
	}
	public void print() {
		if(isEmpty()) {
			System.out.println("List is empty");
		}
		Node<T> current=head;
		while(current!=null ) {
			System.out.println(current.getData());
			current=current.getNext();
		}
		}
	//
	//public int size() {
	//int count = 0;
	//Node<T> current = head;
	//while(current != null) {
//		count ++;
//		current = current.getNext();
	//}
	//return count;
	//}
	public Node<T> getHead() {
		return head;
	}
	public void setHead(Node<T> head) {
		this.head = head;
	}


	}
