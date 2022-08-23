package ds.linear;

public class QueueNode {
    QueueNode prev;
    int value;

    public QueueNode (QueueNode prev, int value) {
        this.value = value;
        this.prev = prev;
    }

    public void setPrev (QueueNode prev) {
        this.prev = prev;
    }

    public void setValue (int value) {
        this.value = value;
    }
}
