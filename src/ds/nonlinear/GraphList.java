package ds.nonlinear;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphList {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Node> referenceArray;

	public GraphList () {
		this.referenceArray = new ArrayList<Node>(0);
	}

	public void createNode () {
		System.out.println("Enter the number of elements to add:");
        int numElem = scanner.nextInt();
        while (numElem < 1) {
        	System.out.println("Please enter a value larger than 0:");
        	numElem = scanner.nextInt();
        }
        Node newNode;
        int newValue;
        for (int i=0; i<numElem; i++) {
	        System.out.println("Enter a value:");
	        newValue = scanner.nextInt();
	        newNode = this.findNode(newValue);
	        if (newNode != null) {
	        	System.out.println(String.format("Add to count (current: %d):", newNode.count));
				newNode.editCount(scanner.nextInt());
	        } else {
	        	System.out.println("Enter a count:");
	        	int newCount = scanner.nextInt();
	        	this.referenceArray.add(new Node(newValue, newCount, new ArrayList<Edge>(0)));
	        }
    	}
	}

	public Node findNode (int searchVal) {
		for (int i=0; i<this.referenceArray.size(); i++) {
			if (this.referenceArray.get(i).value == searchVal) {
				return this.referenceArray.get(i);
			}
		}
		return null;
	}

	public Node editNode () {
        String action;
        do {
            System.out.println("Options:\n- Find (f)\n- Delete (d)\n- Update (u)");
            action = String.valueOf(scanner.next().charAt(0)).toLowerCase();
        } while (!action.equals("f") && !action.equals("d") && !action.equals("u"));
        return editNode(action);
    }
	public Node editNode (String action) {
		if (this.referenceArray.size() == 0) {
            System.out.println("Binary search tree is empty");
        } else {
            Node node;
            int searchVal;
		    switch (action) {
                case "f":
                    System.out.println("Value to find:");
                    searchVal = scanner.nextInt();
                    node = findNode(searchVal);
                    if (node == null) {
                        System.out.println("No such node");
                    } else if (node.value == searchVal) {
                        System.out.println(String.format("Value: %d, Count: %d", node.value, node.count));
                    }
                    break;
                case "d":
                // TODO
                    // System.out.println("Value to delete:");
                    // searchVal = scanner.nextInt();
                    // deleteNode();
                    // node = null;
                    break;
                case "u":
                // TODO
                    // System.out.println("Value to update:");
                    // searchVal = scanner.nextInt();
                    // deleteNode(this.root, null, searchVal);

                    // System.out.println("Enter a new value:");
                    // int value = scanner.nextInt();
                    // System.out.println("Enter a new count:");
                    // int count = scanner.nextInt();
                    // createNode(value, count);
                    node = null;
                    break;
                default:
                    node = null;
                    break;
            }       
            return node;
	    }
	}

	public Edge editEdge () {
        System.out.println("First, find a node using its value:");
        Node node = this.editNode("f");

        String action;
        do {
            System.out.println("Options:- Create(c)\n- Find (f)\n- Delete (d)\n- Update (u)");
            action = String.valueOf(scanner.next().charAt(0)).toLowerCase();
        } while (!action.equals("c") && !action.equals("f") && !action.equals("d") && !action.equals("u"));
        return editEdge(action, node);
    }
	public Edge editEdge (String action, Node node) {
		if (node.edges.size() == 0) {
            System.out.println("No edges originate from this node");
        } else {
            int searchVal;
            Node neighbour;
		    switch (action) {
		    	case "c":
		    		// TODO
		    		// System.out.println("Enter the value of the potential neighbour:");
        //             searchVal = scanner.nextInt();
        //             neighbour = this.findNode(searchVal);
        //             if (neighbour == null) {
        //                 System.out.println("No such neighbour");
		    		break;
                case "f":
                    System.out.println("Enter the value of the potential neighbour:");
                    searchVal = scanner.nextInt();
                    neighbour = this.findNode(searchVal);
                    if (neighbour == null) {
                        System.out.println("No such neighbour");
                    } else {
                    	for (int i=0; i<node.edges.size(); i++) {
	                    	if (node.edges.get(i).end == neighbour) {
	                        	System.out.println(String.format("Start: %d, End: %d, Weight: %d", node.value, neighbour.value, node.edges.get(i).weight));
	                        	return node.edges.get(i);
	                    	}
	                    }
                    }
                    break;
                case "d":
                // TODO
                    // System.out.println("Value to delete:");
                    // searchVal = scanner.nextInt();
                    // deleteNode();
                    // node = null;
                    break;
                case "u":
                // TODO
                    // System.out.println("Value to update:");
                    // searchVal = scanner.nextInt();
                    // deleteNode(this.root, null, searchVal);

                    // System.out.println("Enter a new value:");
                    // int value = scanner.nextInt();
                    // System.out.println("Enter a new count:");
                    // int count = scanner.nextInt();
                    // createNode(value, count);
                    node = null;
                    break;
                default:
                    node = null;
                    break;
            }       
            return node;
	    }
	}

	public void printReference () {
		System.out.println("REFERENCE ARRAY");
		for (int i=0; i<this.referenceArray.size(); i++) {
			System.out.println(String.format("Value: %d, Count: %d", this.referenceArray.get(i).value, this.referenceArray.get(i).count));
		}
	}

	class Node {
		int value, count;
		ArrayList<Edge> edges;

		public Node (int value, int count, ArrayList<Edge> edges) {
			this.value = value;
			this.count = count;
			this.edges = edges;
		}

		public void editCount (int count) {
	        this.count += count;
	    }

	    public void setCount (int count) {
	        this.count = count;
	    }
	}

	class Edge {
		Node start, end;
		int weight;

		public Edge (Node start, Node end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

    public void exit () {
        scanner.close();
    }
}
