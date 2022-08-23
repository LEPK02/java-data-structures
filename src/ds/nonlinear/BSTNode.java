package ds.nonlinear;

public class BSTNode {
    BSTNode left, right;
    int value, count;

    public BSTNode (BSTNode left, BSTNode right, int value, int count) {
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
