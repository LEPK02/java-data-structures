package ds;

import ds.linear.*;
import ds.nonlinear.*;
// import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
    	// Dynamic Arrays
        DynamicArr myArr = new DynamicArr();
        myArr.initArr();
        myArr.showArr();
        myArr.replaceElem();
        myArr.insertElem();
        myArr.deleteElem();
        myArr.showArr();
        myArr.exit();

        // // Linked List
        // LinkedList myLinkedList = new LinkedList();
        // myLinkedList.createNode();
        // myLinkedList.findNode();
        // myLinkedList.updateNode();
        // myLinkedList.deleteNode();
        // myLinkedList.readLinkedList();
        // myLinkedList.exit();

        // // Queue
        // MyQueue myQueue = new MyQueue();
        // myQueue.enqueue();
        // myQueue.dequeue();
        // myQueue.getLength();
        // myQueue.printQueue();
        // myQueue.exit();

        // // Stack
        // MyStack myStack = new MyStack();
        // myStack.push();
        // myStack.pop();
        // myStack.printStack();
        // myStack.exit();

        // // Binary Search Tree
        // BinarySearchTree myBST = new BinarySearchTree();
        // myBST.createNode();
        // myBST.inOrder();
        // myBST.printInOrder();
        // myBST.exit();

        // // Graph (Adjacency Matrix (Weighted, Directed))
        // GraphMatrix myMatrix = new GraphMatrix();
        // myMatrix.createVertex();
        // myMatrix.deleteVertex();
        // myMatrix.editEdge();
        // myMatrix.printMatrix();
        // myMatrix.exit();

        // Graph (Adjacency List (Weighted, Directed))
        // GraphList myGraphList = new GraphList();
        // myGraphList.createNode();
        // myGraphList.printReference();
        // myGraphList.exit();

        // TODO: Nonlinear (Graph, Hash)
    }
}
