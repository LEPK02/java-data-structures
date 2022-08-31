package ds.linear;

import java.util.ArrayList;

public class IntQueue {
    public QueueNode head;
    public QueueNode tail;
    
    public IntQueue () {
        this.head = null;
        this.tail = null;
    }

    public void create (Integer value) {
        QueueNode newNode = new QueueNode(null, value);
        if (this.head == null && this.tail == null) {
        	this.head = newNode;
        	this.tail = newNode;
        } else {
			this.tail.setPrev(newNode);
        	this.tail = newNode;
        }
    }

    public ArrayList<Integer> read () {
    	if (this.head == null && this.tail == null) {
        	return new ArrayList<Integer>();
    	} else {
    		QueueNode current = this.head;
    		ArrayList<Integer> arr = new ArrayList<Integer>();
	    	while (true) {
	    		arr.add(current.value);
	    		if (current == this.tail) {
	    			break;
	    		}
	    		current = current.prev;
	    	}
	    	return arr;
	    }
    }

    public Integer getLength () {
    	if (this.head == null && this.tail == null) {
        	return 0;
    	} else if (this.head == this.tail) {
    		return 1;
    	} else {
    		QueueNode current = this.head;
	    	Integer count = 1;
	    	while (current != this.tail) {
	    		count++;
	    		current = current.prev;
	    	}
	    	return count;
    	}
    }

    public void delete () {
    	if (this.head == null && this.tail == null) {
        	return;
        } else if (this.head == this.tail) {
        	this.head = null;
        	this.tail = null;
        } else {
	        QueueNode current = this.head.prev;
	    	this.head.setPrev(null);
	    	this.head = current;
	    }
    }

    class QueueNode {
	    public QueueNode prev;
	    public Integer value;

	    public QueueNode (QueueNode prev, Integer value) {
	        this.value = value;
	        this.prev = prev;
	    }

	    public void setPrev (QueueNode prev) {
	        this.prev = prev;
	    }

	    public void setValue (Integer value) {
	        this.value = value;
	    }
	}
}