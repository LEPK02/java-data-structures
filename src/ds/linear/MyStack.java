package ds.linear;

import ds.linear.StackNode;
import java.lang.*;
import java.util.*;

public class MyStack {
	Scanner scanner = new Scanner(System.in);
    StackNode top;
    
    public MyStack () {
        this.top = new StackNode(null, 0);
    }

    public void push () {
        System.out.println("Enter the number of elements to add:");
        int numElem = scanner.nextInt();

        StackNode newNode;
        for (int i=0; i<numElem; i++) {
            newNode = new StackNode(null, 0);
            newNode.setPrev(this.top);
            this.top = newNode;
        }
    }

    public void pop () {
    	int len = this.getLength();
    	System.out.println(String.format("Enter the number of elements to remove (current: %d):", len));
        int numElem = scanner.nextInt();
        while (numElem > len || numElem < 1) {
        	System.out.println(String.format("Please enter a value from 1 to %d:", len));
        	numElem = scanner.nextInt();
        }

        StackNode current = this.top;
        for (int i=numElem; i>1; i--) {
        	current = current.prev;
        	this.top.setPrev(null);
        	this.top = current;
        }

    	if (current.prev == null) {
    		this.top = null;
    		current = null;
    	} else {
    		current = current.prev;
        	this.top.setPrev(null);
        	this.top = current;
    	}
    }

    public void printStack () {
    	StackNode current = this.top;
    	if (current == null) {
    		System.out.println("Stack empty");
    	} else {
    		System.out.println("Stack:");
	    	while (current.prev != null) {
	    		System.out.println(current.value);
	    		current = current.prev;
	    	}
	    	System.out.println(current.value);
	    }
    }

    public int getLength () {
    	StackNode current = this.top;
    	int count = 1;
    	while (current.prev != null) {
    		count++;
    		current = current.prev;
    	}
    	return count;
    }

    public void exit () {
        scanner.close();
    }
}