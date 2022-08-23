package ds.linear;

import ds.linear.LinkedListNode;
import java.lang.*;
import java.util.*;

public class LinkedList {
    Scanner scanner = new Scanner(System.in);
    LinkedListNode root;
    LinkedListNode current;

    public LinkedList () {
        this.root = null;
        this.current = null;
    }

    public void createNode () {
        System.out.println("Enter the number of elements to add:");
        int numElem = scanner.nextInt();
        
        for (int i=0; i<numElem; i++) {
            System.out.println("Enter a value:");
            int value = scanner.nextInt();
            System.out.println("Enter a count:");
            int count = scanner.nextInt();
            createNode(value, count);
        }
    }
    public void createNode (int value, int count) {
        this.current = this.root;
        LinkedListNode newNode = new LinkedListNode(null, null, value, count);

        if (this.root == null) {
            this.root = newNode;
        } else if (this.root.next == null && this.root.prev == null) {
            if (this.root.value == newNode.value) {
                this.root.editCount(count);
            } else {
                this.root.setNext(newNode);
                this.root.setPrev(newNode);
                newNode.setPrev(this.root);
                newNode.setNext(this.root);
                if (newNode.value < this.root.value) {
                    this.setRoot(newNode);
                }
            }
        } else {
            while (true) {
                if (newNode.value > this.current.value) {
                    if (newNode.value < this.current.next.value) {
                        insertNext(this.current, newNode);
                        break;
                    } else if (newNode.value > this.current.next.value) {
                        if (this.current.next == this.root) {
                            insertNext(this.current, newNode);
                            break;
                        } else {
                            this.current = this.current.next;
                        }
                    } else {
                        this.current = this.current.next;
                    }
                } else if (newNode.value < this.current.value) {
                    this.insertPrev(this.current, newNode);
                    this.setRoot(newNode);
                    break;
                } else {
                    this.current.editCount(1);
                    break;
                }
            }
        }
    }

    public void readLinkedList () {
        this.current = this.root;
        if (this.current == null) {
            System.out.println("Linked list is empty");
        } else if (this.current.next == null && this.current.prev == null) {
            System.out.println(String.format("There is only one element\nValue: %d\nCount:%d", this.root.value, this.root.count));
        } else {
            System.out.println("Linked list:");
            while (this.current.next != this.root) {
                System.out.println(String.format("Value: %d, Count: %d", this.current.value, this.current.count));
                this.current = this.current.next;
            }
            System.out.println(String.format("Value: %d, Count: %d", this.current.value, this.current.count));
        }
    }

    public LinkedListNode findNode () {
        return findNode('f');
    }
    public LinkedListNode findNode (char action) {
        this.current = this.root;
        if (this.current == null) {
            System.out.println("Linked list is empty");
            return null;
        } else if (this.current.next == null && this.current.prev == null) {
            System.out.println(String.format("Only one element was found\nValue: %d\nCount: %d", this.root.value, this.root.count));
            return this.current;
        }

        switch (action) {
            case 'f':
                System.out.println("Value to find:");
                break;
            case 'u':
                System.out.println("Value to update:");
                break;
            case 'd':
                System.out.println("Value to delete:");
                break;
            default:
                break;
        }
        int searchVal = scanner.nextInt();
        System.out.println("Search in ascending (a) or desecnding (d) order?:");
        String searchDir = String.valueOf(scanner.next().charAt(0)).toLowerCase();

        while (true) {
            if (searchDir.equals("a")) {
                while (true) {
                    if (searchVal > this.current.value) {
                        if (this.current.next == this.root) {
                            System.out.println("No such node");
                            return null;
                        }
                        this.current = this.current.next;
                    } else if (searchVal < this.current.value) {
                        System.out.println("No such node");
                        return null;
                    } else {
                        System.out.println(String.format("Element found!\nValue: %d\nCount: %d", this.current.value, this.current.count));
                        return this.current;
                    }
                }
            } else if (searchDir.equals("d")) {
                this.current = this.root.prev;
                while (true) {
                    if (searchVal < this.current.value) {
                        if (this.current == this.root) {
                            System.out.println("No such node");
                            return null;
                        }
                        this.current = this.current.prev;
                    } else if (searchVal > this.current.value) {
                        System.out.println("No such node");
                        return null;
                    } else {
                        System.out.println(String.format("Element found!\nValue: %d\nCount: %d", this.current.value, this.current.count));
                        return this.current;
                    }
                }
            } else {
                System.out.println("Please enter a valid value ('a' or 'd'):");
                searchDir = String.valueOf(scanner.next().charAt(0)).toLowerCase();
            }
        }
    }

    public void updateNode () {
        this.current = this.findNode('u');
        if (this.current != null) {
            System.out.println("Enter a new value:");
            this.current.setValue(scanner.nextInt());

            System.out.println(String.format("Enter a new count (current: %d):", this.current.count));
            this.current.setCount(scanner.nextInt());

            if (this.current.next != null && this.current.prev != null) {
                if (this.current.value > this.current.prev.value && this.current.value < this.current.next.value) {
                    
                } else {
                    int savedValue = this.current.value;
                    int savedCount = this.current.count;
                    this.deleteNode(this.current);
                    System.out.println(savedCount);
                    System.out.println(savedValue);
                    this.createNode(savedValue, savedCount);
                }
            }
        } else if (this.root == null) {
            System.out.println("Empty linked list; creating node");
            createNode();
        }
    }

    public void deleteNode () {
        this.deleteNode(this.findNode('d'));
    }
    public void deleteNode (LinkedListNode currentNode) {
        if (this.root == null) {
            System.out.println("Empty linked list, no nodes to delete");
        } else if (currentNode == null) {
            System.out.println("Unable to delete");
        } else {
            LinkedListNode nextNode = currentNode.next;
            LinkedListNode prevNode = currentNode.prev;
            if (nextNode == null || prevNode == null) {
                this.root = null;
                currentNode = null;
            } else if (nextNode == this.root && this.root.next == currentNode) {
                this.root.setNext(null);
                this.root.setPrev(null);
                currentNode.setNext(null);
                currentNode.setPrev(null);
            } else {
                if (currentNode == this.root) {
                    this.setRoot(currentNode.next);
                }
                nextNode.setPrev(prevNode);
                currentNode.setNext(null);
                prevNode.setNext(nextNode);
                currentNode.setPrev(null);
            }
            nextNode = null;
            prevNode = null;
            currentNode = null;
        }
    }

    public void setRoot (LinkedListNode newRoot) {
        this.root = newRoot;
    }

    public void insertNext (LinkedListNode currentNode, LinkedListNode newNode) {
        currentNode.next.setPrev(newNode);
        newNode.setNext(currentNode.next);
        currentNode.setNext(newNode);
        newNode.setPrev(currentNode);
    }

    public void insertPrev (LinkedListNode currentNode, LinkedListNode newNode) {
        currentNode.prev.setNext(newNode);
        newNode.setPrev(currentNode.prev);
        currentNode.setPrev(newNode);
        newNode.setNext(currentNode);
    }

    // public void extractNode (LinkedListNode currentNode) {
    //     currentNode.prev.setNext(currentNode.next);
    //     currentNode.next.setPrev(currentNode.prev);
    //     currentNode.setPrev(null);
    //     currentNode.setNext(null);
    // }

    public void exit () {
        scanner.close();
    }
}