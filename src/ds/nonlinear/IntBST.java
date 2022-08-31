package ds.nonlinear;

import java.util.ArrayList;

public class IntBST {
	public BSTNode root;
    public BSTNode current;
    
    public IntBST () {
        this.root = null;
        this.current = null;
    }

    public void create (Integer value, Integer count) {
        if (this.root == null) {
            this.root = new BSTNode(null, null, value, count);
        } else {
            this.current = this.root;
            BSTNode newNode = new BSTNode(null, null, value, count);
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
                    break;
                }
            }
        }
    }

    public ArrayList<ArrayList<Integer>> read () {
    	ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
    	read(this.root, arr);
    	return arr;
    }
    public ArrayList<ArrayList<Integer>> read (BSTNode currentNode, ArrayList<ArrayList<Integer>> arr) {
        if (this.root == null) {
        } else if (currentNode != null) {
	        this.read(currentNode.left, arr);
	        ArrayList<Integer> tempArr = new ArrayList<Integer>();
	        tempArr.add(currentNode.value);
	        tempArr.add(currentNode.count);
	        arr.add(tempArr);
	        this.read(currentNode.right, arr);
	    }
	    return arr;
    }

   	public void update (Integer value, Integer count) {
   		if (this.root == null) {
    		return;
    	} else {
   			update(this.root, value, count);
    	}
   	}
   	public void update (BSTNode currentNode, Integer value, Integer count) {
    	if (currentNode != null) {
            if (value < currentNode.value) {
                update(currentNode.left, value, count);
            } else if (value > currentNode.value) {
                update(currentNode.right, value, count);
            } else {
                currentNode.setCount(count);
            }
        }
    }

    public void delete (Integer value) {
   		delete(this.root, null, value);
   	}
    public void delete (BSTNode currentNode, BSTNode parentNode, Integer value) {
        if (currentNode != null) {
            if (value < currentNode.value) {
                delete(currentNode.left, currentNode, value);
            } else if (value > currentNode.value) {
                delete(currentNode.right, currentNode, value);
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
                    Integer successorVal = successor.value;
                    Integer successorCount = successor.count;
                    delete(currentNode, parentNode, successorVal);
                    currentNode.setValue(successorVal);
                    currentNode.setCount(successorCount);
                }
            }
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

    public class BSTNode {
	    BSTNode left, right;
	    Integer value, count;

	    BSTNode (BSTNode left, BSTNode right, Integer value, Integer count) {
	        this.value = value;
	        this.left = left;
	        this.right = right;
	        this.count = count;
	    }

	    public void setLeft (BSTNode left) {
	        this.left = left;
	    }

	    public void setRight (BSTNode right) {
	        this.right = right;
	    }

	    public void setValue (Integer value) {
	        this.value = value;
	    }

	    public void setCount (Integer count) {
	        this.count = count;
	    }
	}
}
