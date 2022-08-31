package ds.linear;

import java.util.ArrayList;
import java.util.Arrays.*;

public class DoubleLinkedList {
    public DoubleLinkedListNode root;
    public DoubleLinkedListNode current;

    public DoubleLinkedList () {
        this.root = null;
        this.current = null;
    }

    public void create (Double value, Integer count) {
        this.current = this.root;
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(null, null, value, count);

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

    public ArrayList<Double> readValue () {
    	ArrayList<Double> arr = new ArrayList<Double>();
        this.current = this.root;
        if (this.current == null) {
            return arr;
        } else if (this.current.getNext() == null || this.current.getPrev() == null) {
        	arr.add(this.current.getValue());
        	return arr;
        } else {
            while (true) {
                arr.add(this.current.getValue());
                this.current = this.current.getNext();
                if (this.current == this.root) {
                    break;
                }
            }
            return arr;
        }
    }
    public ArrayList<Integer> readCount () {
    	ArrayList<Integer> arr = new ArrayList<Integer>();
        this.current = this.root;
        if (this.current == null) {
            return arr;
        } else if (this.current.getNext() == null || this.current.getPrev() == null) {
        	arr.add(this.current.getCount());
        	return arr;
        } else {
            while (true) {
                arr.add(this.current.getCount());
                this.current = this.current.getNext();
                if (this.current == this.root) {
                    break;
                }
            }
            return arr;
        }
    }

    public void update (Double value, Integer count) {
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

    public void delete (Double value) {
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

    public void setRoot (DoubleLinkedListNode newRoot) {
        this.root = newRoot;
    }

    public void insertNext (DoubleLinkedListNode currentNode, DoubleLinkedListNode newNode) {
        currentNode.getNext().setPrev(newNode);
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        newNode.setPrev(currentNode);
    }

    public void insertPrev (DoubleLinkedListNode currentNode, DoubleLinkedListNode newNode) {
        currentNode.getPrev().setNext(newNode);
        newNode.setPrev(currentNode.getPrev());
        currentNode.setPrev(newNode);
        newNode.setNext(currentNode);
    }

    public class DoubleLinkedListNode {
        public DoubleLinkedListNode next;
        public DoubleLinkedListNode prev;
        public Double value;
        public Integer count;

        public DoubleLinkedListNode (DoubleLinkedListNode next, DoubleLinkedListNode prev, Double value, Integer count) {
            this.next = next;
            this.prev = prev;
            this.value = value;
            this.count = count;
        }

        public DoubleLinkedListNode getNext () {
            return this.next;
        }

        public void setNext (DoubleLinkedListNode next) {
            this.next = next;
        }

        public DoubleLinkedListNode getPrev () {
            return this.prev;
        }

        public void setPrev (DoubleLinkedListNode prev) {
            this.prev = prev;
        }

        public Double getValue () {
            return this.value;
        }

        public void setValue (Double value) {
            this.value = value;
        }

        public Integer getCount () {
            return this.count;
        }

        public void editCount (Integer count) {
            this.count += count;
        }

        public void setCount (Integer count) {
            this.count = count;
        }
    }
}