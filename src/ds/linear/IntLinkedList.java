package ds.linear;

import java.util.ArrayList;
import java.util.Arrays.*;

public class IntLinkedList {
    public IntLinkedListNode root;
    public IntLinkedListNode current;

    public IntLinkedList () {
        this.root = null;
        this.current = null;
    }

    public void create (Integer value, Integer count) {
        this.current = this.root;
        IntLinkedListNode newNode = new IntLinkedListNode(null, null, value, count);

        if (this.root == null) {
            this.setRoot(newNode);
        } else if (this.root.getNext() == null && this.root.getPrev() == null) {
            if (this.root.getValue() != value) {
                newNode.setNext(this.root);
                newNode.setPrev(this.root);
                this.root.setNext(newNode);
                this.root.setPrev(newNode);
                if (value < this.root.getValue()) {
                    this.setRoot(newNode);
                };
            }
        } else {
            while (true) {
                if (value > this.current.getValue()) {
                    if (value < this.current.getNext().getValue()) {
                        insertNext(this.current, newNode);
                        break;
                    } else if (value > this.current.getNext().getValue()) {
                        if (this.current.getNext() == this.root) {
                            insertNext(this.current, newNode);
                            break;
                        } else {
                            this.current = this.current.getNext();
                        }
                    } else {
                        this.current = this.current.getNext();
                    }
                } else if (value < this.current.getValue()) {
                    insertPrev(this.current, newNode);
                    setRoot(newNode);
                    break;
                } else {
                    break;
                }
            }
        }
    }

    public ArrayList<ArrayList<Integer>> read () {
    	ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        this.current = this.root;
        if (this.current == null) {
            return arr;
        } else if (this.current.getNext() == null || this.current.getPrev() == null) {
            ArrayList<Integer> tempArr = new ArrayList<Integer>();
        	tempArr.add(this.current.getValue());
        	tempArr.add(this.current.getCount());
        	arr.add(tempArr);
        	return arr;
        } else {
            while (true) {
                ArrayList<Integer> tempArr = new ArrayList<Integer>();
            	tempArr.add(this.current.getValue());
            	tempArr.add(this.current.getCount());
            	arr.add(tempArr);
                this.current = this.current.getNext();
                if (this.current == this.root) {
                    break;
                }
            }
            return arr;
        }
    }

    public void update (Integer value, Integer count) {
        this.current = this.root;
        if (this.root == null) {
            return;
        } else {
            while (true) {
                if (value > this.current.getValue()) {
                    if (this.current.getNext() == this.root) {
                        break;
                    }
                    this.current = this.current.getNext();
                } else if (value < this.current.getValue()) {
                    if (this.current == this.root) {
                        break;
                    } else {
                        if (value > this.current.getPrev().getValue()) {
                            break;
                        }
                    }
                } else {
                    this.current.setCount(count);
                    break;
                }
            }
        }
    }

    public void delete (Integer value) {
        this.current = this.root;
        if (this.root == null) {
        } else if (this.root.getNext() == null && this.root.getPrev() == null) {
            if (value.equals(this.current.getValue())) {
            	this.root = null;
            	this.current = null;
            }
        } else {
            while (true) {
                if (value > this.current.getValue()) {
                    if (this.current.getNext() == this.root) {
                        break;
                    } else {
                        this.current = this.current.getNext();
                    }
                } else if (value < this.current.getValue()) {
                    break;
                } else if (this.current.getNext() == this.root && this.root.getNext() == this.current) {
                    this.root.setNext(null);
                    this.root.setPrev(null);
                    this.current.setNext(null);
                    this.current.setPrev(null);
                    break;
                } else {
                    if (this.current == this.root) {
                        this.setRoot(this.current.getNext());
                    }
                    this.current.getNext().setPrev(this.current.getPrev());
                    this.current.getPrev().setNext(this.current.getNext());
                    this.current.setNext(null);
                    this.current.setPrev(null);
                    break;
                }
            }
        }
    }

    public void setRoot (IntLinkedListNode newRoot) {
        this.root = newRoot;
    }

    public void insertNext (IntLinkedListNode currentNode, IntLinkedListNode newNode) {
        currentNode.getNext().setPrev(newNode);
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        newNode.setPrev(currentNode);
    }

    public void insertPrev (IntLinkedListNode currentNode, IntLinkedListNode newNode) {
        currentNode.getPrev().setNext(newNode);
        newNode.setPrev(currentNode.getPrev());
        currentNode.setPrev(newNode);
        newNode.setNext(currentNode);
    }

    public class IntLinkedListNode {
        public IntLinkedListNode next;
        public IntLinkedListNode prev;
        public Integer value;
        public Integer count;

        public IntLinkedListNode (IntLinkedListNode next, IntLinkedListNode prev, Integer value, Integer count) {
            this.next = next;
            this.prev = prev;
            this.value = value;
            this.count = count;
        }

        public IntLinkedListNode getNext () {
            return this.next;
        }

        public void setNext (IntLinkedListNode next) {
            this.next = next;
        }

        public IntLinkedListNode getPrev () {
            return this.prev;
        }

        public void setPrev (IntLinkedListNode prev) {
            this.prev = prev;
        }

        public Integer getValue () {
            return this.value;
        }

        public void setValue (Integer value) {
            this.value = value;
        }

        public Integer getCount () {
            return this.count;
        }

        public void setCount (Integer count) {
            this.count = count;
        }
    }
}