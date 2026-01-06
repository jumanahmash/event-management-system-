package EventMangement;

import java.io.Serializable;

public class Node<T> implements Serializable {
    
    private T data;
    private Node<T> next;
    
    public Node(T obj) {
        this(obj, null);
    }
    
    public Node(T obj, Node<T> node) {
        data = obj;
        next = node;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}