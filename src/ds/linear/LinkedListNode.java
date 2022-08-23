package ds.linear;

import ds.linear.*;

public class LinkedListNode {
    LinkedListNode next;
    LinkedListNode prev;
    int value;
    int count;

    public LinkedListNode (LinkedListNode next, LinkedListNode prev, int value, int count) {
        this.value = value;
        this.next = next;
        this.prev = prev;
        this.count = count;
    }

    public void setNext (LinkedListNode next) {
        this.next = next;
    }

    public void setPrev (LinkedListNode prev) {
        this.prev = prev;
    }

    public void setValue (int value) {
        this.value = value;
    }

    public void editCount (int count) {
        this.count += count;
    }

    public void setCount (int count) {
        this.count = count;
    }
}