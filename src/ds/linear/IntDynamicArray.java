package ds.linear;

import java.util.Arrays;

public class IntDynamicArray {
    public Integer[] arr;

    public IntDynamicArray () {
        this.arr = arr;
    }

    public void create (Integer value, Integer index) {
        if (this.arr == null || this.arr.length == 0) {
            this.arr = new Integer[]{value};
            return;
        }
        if (index < 0 || index > this.arr.length) {
            return;
        } 

        Integer[] newArr = new Integer[this.arr.length+1];
        if (index == 0) {
            newArr[0] = value;
            for (int i=1; i<this.arr.length+1; i++) {
                newArr[i] = this.arr[i-1];
            }
        } else if (index == this.arr.length) {
            for (int i=0; i<this.arr.length; i++) {
                newArr[i] = this.arr[i];
            }
            newArr[this.arr.length] = value;
        } else {
            for (int i=0; i<index; i++) {
                newArr[i] = this.arr[i];
            }
            newArr[index] = value;
            for (int i=index; i<this.arr.length; i++) {
                newArr[i+1] = this.arr[i];
            }
        }
        this.arr = newArr;
    }

    public Integer[] read () {
    	if (this.arr == null) {
    		return new Integer[]{};
    	}
        return this.arr;        
    }

    public void update (Integer value, Integer index) {
        if (this.arr == null || this.arr.length == 0) {
            return;
        }
        if (index < 0 || index >= this.arr.length) {
            return;
        }
        this.arr[index] = value;
    }

    public void delete (Integer index) {
        if (this.arr == null || this.arr.length == 0) {
            return;
        }
        if (index < 0 || index >= this.arr.length) {
            return;
        }
        if (this.arr.length == 1 && index == 0) {
            this.arr = new Integer[]{};
            return;
        }

        Integer[] newArr = new Integer[this.arr.length-1];
        for (int i=0; i<index; i++) {
            newArr[i] = this.arr[i];
        }
        for (int i=index+1; i<this.arr.length; i++) {
            newArr[i-1] = this.arr[i];
        }
        this.arr = newArr;
    }
}