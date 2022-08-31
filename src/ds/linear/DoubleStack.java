package ds.linear;

import java.util.ArrayList;

public class DoubleStack {
    public StackNode top;
    
    public DoubleStack () {
        this.top = null;
    }

    public void create (Double value) {
    	if (this.top == null) {
    		this.top = new StackNode(null, value);	
    	} else {
	        StackNode newNode = new StackNode(this.top, value);
	        this.top = newNode;
	    }
    }

    public ArrayList<Double> read () {
    	if (this.top == null) {
    		return new ArrayList<Double>();
    	} else {
    		ArrayList<Double> arr = new ArrayList<Double>();
    		StackNode current = this.top;
	    	while (true) {
	    		arr.add(current.value);
	    		if (current.prev == null) {
	    			break;
	    		}
	    		current = current.prev;
	    	}
	    	return arr;
    	}
    }

    public Integer getLength () {
    	if (this.top == null) {
    		return 0;
    	}
    	StackNode current = this.top;
    	Integer count = 1;
    	while (current.prev != null) {
    		count++;
    		current = current.prev;
    	}
    	return count;
    }

    public void delete () {
    	if (this.top == null) {
    		return;
    	} else if (this.top.prev == null) {
    		this.top = null;
    	} else {
    		StackNode current = this.top.prev;
    		this.top.setPrev(null);
    		this.top = current;
    	}
    }

    class StackNode {
	    StackNode prev;
	    Double value;

	    public StackNode (StackNode prev, Double value) {
	        this.value = value;
	        this.prev = prev;
	    }

	    public void setPrev (StackNode prev) {
	        this.prev = prev;
	    }

	    public void setValue (Double value) {
	        this.value = value;
	    }
	}
}