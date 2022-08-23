package ds.linear;

import ds.linear.QueueNode;
import java.lang.*;
import java.util.*;

public class MyQueue {
	Scanner scanner = new Scanner(System.in);
    QueueNode head;
    QueueNode tail;
    
    public MyQueue () {
        this.head = this.createNode();
        this.tail = this.head;
    }

    public void enqueue () {
        System.out.println("Enter the number of elements to add:");
        int numElem = scanner.nextInt();
        enqueue(numElem);
    }
    public void enqueue (int numElem) {
        QueueNode newNode;
        for (int i=0; i<numElem; i++) {
            newNode = this.createNode();
            this.tail.setPrev(newNode);
            this.tail = newNode;
        }
    }
    public void enqueue (QueueNode newNode) {
        this.tail.setPrev(newNode);
        this.tail = newNode;
    }

    public void dequeue () {
        int len = this.getLength();
    	System.out.println(String.format("Enter the number of elements to remove (current: %d):", len));
        int numElem = scanner.nextInt();
        while (numElem > len || numElem < 1) {
        	System.out.println(String.format("Please enter a value from 1 to %d:", len));
        	numElem = scanner.nextInt();
        }
        dequeue(numElem);
    }
    public void dequeue (int numElem) { 
        QueueNode current = this.head;
        for (int i=numElem; i>1; i--) {
        	current = current.prev;
        	this.head.setPrev(null);
        	this.head = current;
        }

    	if (current == this.tail) {
    		this.head = null;
    		this.tail = null;
            current = null;
    	} else {
    		current = current.prev;
    		this.head.setPrev(null);
    		this.head = current;
    	}
    }

    public void printQueue () {
    	QueueNode current = this.head;
    	if (current == null) {
    		System.out.println("Queue empty");
    	} else {
            System.out.println("Queue:");
	    	while (current != this.tail) {
	    		System.out.println(current.value);
	    		current = current.prev;
	    	}
	    	System.out.println(current.value);
	    }
    }

    public int getLength () {
    	QueueNode current = this.head;
    	int count = 1;
    	while (current != this.tail) {
    		count++;
    		current = current.prev;
    	}
    	return count;
    }

    public QueueNode createNode() {
        System.out.println("Enter a value:");
        int value = scanner.nextInt();
        return createNode(value);
    }
    public QueueNode createNode(int value) {
        return new QueueNode(null, value);
    }

    public void exit () {
        scanner.close();
    }
}