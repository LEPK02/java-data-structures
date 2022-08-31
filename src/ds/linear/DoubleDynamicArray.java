package ds.linear;

public class DoubleDynamicArray {
	public Double[] arr;

    public DoubleDynamicArray () {
        this.arr = arr;
    }

    public void create (Double value, Integer index) {
        if (this.arr == null || this.arr.length == 0) {
            this.arr = new Double[]{value};
            return;
        }
        if (index < 0 || index > this.arr.length) {
            return;
        } 

        Double[] newArr = new Double[this.arr.length+1];
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

    public Double[] read () {
    	if (this.arr == null) {
    		return new Double[]{};
    	}
        return this.arr;        
    }

    public void update (Double value, Integer index) {
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
            this.arr = new Double[]{};
            return;
        }

        Double[] newArr = new Double[this.arr.length-1];
        for (int i=0; i<index; i++) {
            newArr[i] = this.arr[i];
        }
        for (int i=index+1; i<this.arr.length; i++) {
            newArr[i-1] = this.arr[i];
        }
        this.arr = newArr;
    }
}
