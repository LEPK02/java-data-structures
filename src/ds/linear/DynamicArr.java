package ds.linear;

import java.util.Scanner;

public class DynamicArr {
    Scanner scanner = new Scanner(System.in);
    Object[] arr;

    public DynamicArr () {
    	System.out.println("Enter the number of elements:");
		int len = scanner.nextInt();
        this.arr = new Object[len];
    }

	public void initArr () {      
        for (int i=0; i<this.arr.length; i++) {
            System.out.println(String.format("Enter element at index %d:", i));
            this.arr[i] = scanner.nextInt();
        }
	}

	public void showArr () {
		System.out.println("Array:");
		for (int i=0; i<this.arr.length; i++) {
        	System.out.println(this.arr[i]);
        }
	}

	// public void addElem () {		
	// 	System.out.println("Number of elements to add:");
 //        int numElem = scanner.nextInt();
 //        while (numElem < 1) {
 //        	System.out.println("Please enter a number larger than 1");
 //        	numElem = scanner.nextInt();
 //        }

 //        System.out.println("Add to start(s) or end(e)?:");
 //        String addDir = String.valueOf(scanner.next().charAt(0)).toLowerCase();    

 //        Object[] newArr = new Object[this.arr.length + numElem];

 //    	if (addDir.equals("e")) {
	//         for (int i=0; i<this.arr.length; i++) {
	//         	newArr[i] = this.arr[i];
	//         }
	//         for (int i=this.arr.length; i<(numElem+this.arr.length); i++) {
	//         	System.out.println(String.format("Enter element at index %d:", i));
	//         	newArr[i] = scanner.nextInt();
	//         }
 //    	} else if (addDir.equals("s")) {
 //    		for (int i=0; i<numElem; i++) {
 //        		System.out.println(String.format("Enter element at index %d:", i));
	//         	newArr[i] = scanner.nextInt();
 //        	}
 //        	for (int i=numElem; i<(numElem+this.arr.length); i++) {
	//         	newArr[i] = this.arr[i-numElem];
 //    		}
 //    	}
 //    	this.arr = newArr;
	// }

	public void replaceElem () {
		System.out.println("Index to insert element:");
		int elemIdx = scanner.nextInt();

		while (elemIdx < 0 || elemIdx >= this.arr.length) {
        	System.out.println(String.format("Please enter a number between 0 and %d:", this.arr.length));
        	elemIdx = scanner.nextInt();
        }

        System.out.println("Enter new value:");
        int newElem = scanner.nextInt();

        this.arr[elemIdx] = newElem;
	}

	public void insertElem () {
		System.out.println("Index to insert element:");
		int elemIdx = scanner.nextInt();
		while (elemIdx < 0 || elemIdx >= this.arr.length) {
        	System.out.println(String.format("Please enter a number from 0 to %d:", this.arr.length-1));
        	elemIdx = scanner.nextInt();
        }

		System.out.println("Number of elements to add:");
        int numElem = scanner.nextInt();
        while (numElem < 1) {
        	System.out.println("Please enter a number larger than 1");
        	numElem = scanner.nextInt();
        }

        Object[] arrToAdd = new Object[numElem];
		for (int i=0; i<numElem; i++) {
        	System.out.println(String.format("Enter element at index %d:", elemIdx+1+i));
    		arrToAdd[i] = scanner.nextInt();
        }

        Object[] newArr = new Object[this.arr.length + numElem];

        if (elemIdx == 0) {
		    for (int i=0; i<numElem; i++) {
		    	newArr[i] = arrToAdd[i];
		    }
		    for (int i=numElem; i<newArr.length; i++) {
		    	newArr[i] = this.arr[i-numElem];
		    }
		} else if (elemIdx == this.arr.length-1) {
		    for (int i=0; i<this.arr.length; i++) {
		    	newArr[i] = this.arr[i];
		    }
		    for (int i=this.arr.length; i<newArr.length; i++) {
		    	newArr[i] = arrToAdd[i-this.arr.length];
		    }
		} else {
			for (int i=0; i<elemIdx; i++) {
		    	newArr[i] = this.arr[i];
		    }
		    for (int i=elemIdx; i<elemIdx+numElem; i++) {
		    	newArr[i] = arrToAdd[i-elemIdx];
		    }
		    for (int i=elemIdx+numElem; i<newArr.length; i++) {
		    	newArr[i] = this.arr[i-elemIdx-numElem+1];
		    }
		}

		this.arr = newArr;
	}

	public void deleteElem () {
		System.out.println("Index to delete element:");
		int elemIdx = scanner.nextInt();
		while (elemIdx < 0 || elemIdx >= this.arr.length) {
        	System.out.println(String.format("Please enter a number from 0 to %d:", this.arr.length-1));
        	elemIdx = scanner.nextInt();
        }

        System.out.println("Number of elements to delete:");
        int numElem = scanner.nextInt();
        while (numElem > this.arr.length - elemIdx) {
        	System.out.println(String.format("Please enter a number from 0 to %d:", this.arr.length-elemIdx));
        	numElem = scanner.nextInt();
        }

        Object[] newArr = new Object[this.arr.length - numElem];

        for (int i=0; i<elemIdx; i++) {
        	newArr[i] = this.arr[i];
        }
        for (int i=elemIdx+numElem; i<this.arr.length-numElem; i++) {
        	newArr[i] = this.arr[this.arr.length-numElem];
        }

        this.arr = newArr;
	}

	public void exit () {
		scanner.close();
	}
}