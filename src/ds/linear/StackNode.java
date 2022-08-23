package ds.linear;

import java.lang.*;
import java.util.*;

public class StackNode {
    Scanner scanner = new Scanner(System.in);
    StackNode prev;
    int value;

    public StackNode (StackNode prev, int value) {
        System.out.println("Enter a value:");
        value = scanner.nextInt();
        this.value = value;
        this.prev = prev;
    }

    public void setPrev (StackNode prev) {
        this.prev = prev;
    }

    public void setValue (int value) {
        this.value = value;
    }
}
