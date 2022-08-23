package ds.nonlinear;

import ds.nonlinear.BSTNode;
import ds.linear.MyQueue;

import java.util.Scanner;
import java.util.Arrays;

public class BinarySearchTree {
	Scanner scanner = new Scanner(System.in);
	BSTNode root;
    BSTNode current;
    
    public BinarySearchTree () {
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
        BSTNode newNode = new BSTNode(null, null, value, count);

        if (this.root == null) {
            this.root = newNode;
        } else {
            this.current = this.root;
            while (true) {
                if (newNode.value < this.current.value) {
                    if (this.current.left == null) {
                        this.current.setLeft(newNode);
                        break;
                    } else {
                        this.current = this.current.left;
                    }
                } else if (newNode.value > this.current.value) {
                    if (this.current.right == null) {
                        this.current.setRight(newNode);
                        break;
                    } else {
                        this.current = this.current.right;
                    } 
                } else {
                    System.out.println("Node already exists; adding to count");
                    this.current.editCount(newNode.count);
                    break;
                }
            }
        }
    }

    public void printInOrder () {
        if (this.root == null) {
            System.out.println("Binary search tree is empty");
        } else {
            printInOrder(this.root);
        }
    }
    public void printInOrder (BSTNode currentNode) {
        if (currentNode != null) {
            this.printInOrder(currentNode.left);
            System.out.println(String.format("Value: %d, Count: %d", currentNode.value, currentNode.count));
            this.printInOrder(currentNode.right);
        }
    }

    public BSTNode inOrder () {
        String action;
        do {
            System.out.println("Options:\n- Find (f)\n- Delete (d)\n- Update (u)");
            action = String.valueOf(scanner.next().charAt(0)).toLowerCase();
        } while (!action.equals("f") && !action.equals("d") && !action.equals("u"));
        return inOrder(action);
    }
    public BSTNode inOrder (String action) {
        if (this.root == null) {
            System.out.println("Binary search tree is empty");
            return null;
        } else {
            BSTNode node;
            int searchVal;
            switch (action) {
                case "f":
                    System.out.println("Value to find:");
                    searchVal = scanner.nextInt();
                    node = findNode(this.root, searchVal);
                    if (node == null) {
                        System.out.println("No such node");
                    } else if (node.value == searchVal) {
                        System.out.println(String.format("Value: %d, Count: %d", node.value, node.count));
                    }
                    break;
                case "d":
                    System.out.println("Value to delete:");
                    searchVal = scanner.nextInt();
                    deleteNode(this.root, null, searchVal);
                    node = null;
                    break;
                case "u":
                    System.out.println("Value to update:");
                    searchVal = scanner.nextInt();
                    deleteNode(this.root, null, searchVal);

                    System.out.println("Enter a new value:");
                    int value = scanner.nextInt();
                    System.out.println("Enter a new count:");
                    int count = scanner.nextInt();
                    createNode(value, count);
                    node = null;
                    break;
                default:
                    node = null;
                    break;
            }       
            return node;
        }
    }

    public BSTNode findNode (BSTNode currentNode, int searchVal) {
        if (currentNode != null) {
            if (searchVal < currentNode.value) {
                return findNode(currentNode.left, searchVal);
            } else if (searchVal > currentNode.value) {
                return findNode(currentNode.right, searchVal);
            } else {
                return currentNode;
            }
        }
        return null;
    }

    public int[] deleteNode (BSTNode currentNode, BSTNode parentNode, int searchVal) {
        if (currentNode != null) {
            if (searchVal < currentNode.value) {
                return deleteNode(currentNode.left, currentNode, searchVal);
            } else if (searchVal > currentNode.value) {
                return deleteNode(currentNode.right, currentNode, searchVal);
            } else {
                if (currentNode.left == null && currentNode.right == null) {
                    if (currentNode == this.root) {
                        this.root = null;
                    } else {
                        if (parentNode.left == currentNode) {
                            parentNode.setLeft(null);
                        } else {
                            parentNode.setRight(null);
                        }
                    }
                } else if (currentNode.left != null && currentNode.right == null) {
                    if (currentNode == this.root) {
                        this.root = currentNode.left;
                    } else {
                        if (parentNode.left == currentNode) {
                            parentNode.setLeft(currentNode.left);
                        } else {
                            parentNode.setRight(currentNode.left);
                        }
                    }
                    currentNode.setLeft(null);
                } else if (currentNode.left == null && currentNode.right != null) {
                    if (currentNode == this.root) {
                        this.root = currentNode.right;
                    } else {
                        if (parentNode.left == currentNode) {
                            parentNode.setLeft(currentNode.right);
                        } else {
                            parentNode.setRight(currentNode.right);
                        }
                    }
                    currentNode.setRight(null);
                } else {
                    BSTNode successor = this.inOrderSuccessor(currentNode);
                    int successorVal = successor.value;
                    int successorCount = successor.count;
                    deleteNode(currentNode, parentNode, successorVal);
                    currentNode.setValue(successorVal);
                    currentNode.setCount(successorCount);
                }
                int[] currentNodeData = {currentNode.value, currentNode.count};
                return currentNodeData;
            }
        } else {
            System.out.println("Node not found");
            return null;
        }
    }

    public BSTNode inOrderSuccessor (BSTNode node) {
        // Smallest key greater than input node
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            return null;
        }
    }

    public void exit () {
        scanner.close();
    }
}
