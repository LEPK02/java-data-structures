package ds.nonlinear;

import java.util.Arrays;
import java.util.Scanner;

public class GraphMatrix {
	int[][] matrix;
	int[] vertices;
	Scanner scanner = new Scanner(System.in);

	public GraphMatrix () {
		this.matrix = matrix;
		this.vertices = vertices;
	}

	public void createVertex () {
		System.out.println("Enter the number of elements to add:");
        int numElem = scanner.nextInt();
        while (numElem < 1) {
        	System.out.println("Please enter a value larger than 0:");
        	numElem = scanner.nextInt();
        }

		if (this.matrix == null) {
			this.matrix =  new int[numElem][numElem];
			this.vertices = new int[numElem];
			for (int i=0; i<numElem; i++) {
				for (int j=0; j<numElem; j++) {
					this.matrix[i][j] = 0;
				}
				System.out.println("Enter a value:");
        		int newValue = scanner.nextInt();
        		while (this.containsVertex(newValue)) {
        			System.out.println("Vertex already exists; try another value:");
        			newValue = scanner.nextInt();
	        	}
	        	this.vertices[i] = newValue;
			}
		} else {
			int newLength = numElem + this.vertices.length;
			int[][] newMatrix =  new int[newLength][newLength];
			int[] newVertex = new int[newLength];
			int newValue;

			for (int i=0; i<this.vertices.length; i++) {
				for (int j=0; j<this.vertices.length; j++) {
					newMatrix[i][j] = this.matrix[i][j];
				}
				newVertex[i] = this.vertices[i];
			}

			for (int i=this.vertices.length; i<newLength; i++) {
				for (int j=this.vertices.length; j<newLength; j++) {
					newMatrix[i][j] = 0;
				}
				System.out.println("Enter a value:");
        		newValue = scanner.nextInt();
        		while (this.containsVertex(newValue)) {
        			System.out.println("Vertex already exists; try another value:");
        			newValue = scanner.nextInt();
	        	}
	        	newVertex[i] = newValue;
			}

			this.matrix = newMatrix;
			this.vertices = newVertex;
		}
	}

	public void deleteVertex () {
		System.out.println("Enter the number of elements to delete:");
        int numElem = scanner.nextInt();
        while (numElem < 1) {
        	System.out.println("Please enter a value larger than 0:");
        	numElem = scanner.nextInt();
        }

		int[][] newMatrix;
		int[] newVertex;

        for (int k=0; k<numElem; k++) {
			if (this.vertices.length == 0 || this.vertices == null) {
				System.out.println("Graph is empty; no vertices to delete");
			} else {
				System.out.println("Value of vertex to delete:");
		        int deleteValueIdx = Arrays.binarySearch(this.vertices, scanner.nextInt());
		        while (deleteValueIdx < 0) {
		        	System.out.println("Vertex not found; try another value:");
		        	deleteValueIdx = Arrays.binarySearch(this.vertices, scanner.nextInt());
		        }

		        newMatrix =  new int[this.vertices.length-1][this.vertices.length-1];
				newVertex = new int[this.vertices.length-1];

				if (deleteValueIdx == 0) {
					for (int i=1; i<this.vertices.length; i++) {
						for (int j=1; j<this.vertices.length; j++) {
							newMatrix[i-1][j-1] = this.matrix[i][j];
						}
						newVertex[i-1] = this.vertices[i];
					} 
				} else if (deleteValueIdx == this.vertices.length-1) {
					for (int i=0; i<this.vertices.length-1; i++) {
						for (int j=0; j<this.vertices.length-1; j++) {
							newMatrix[i][j] = this.matrix[i][j];
						}
						newVertex[i] = this.vertices[i];
					} 
				} else {
					for (int i=0; i<deleteValueIdx; i++) {
						for (int j=0; j<deleteValueIdx; j++) {
							newMatrix[i][j] = this.matrix[i][j];
						}
						newVertex[i] = this.vertices[i];
					}
					for (int i=deleteValueIdx+1; i<this.vertices.length; i++) {
						for (int j=deleteValueIdx+1; j<this.vertices.length; j++) {
							newMatrix[i-1][j-1] = this.matrix[i][j];
						}
						newVertex[i-1] = this.vertices[i];
					}
				}
				this.matrix = newMatrix;
				this.vertices = newVertex;
			}
		}
	}

	public void editEdge () {
		if (this.vertices.length == 0 || this.vertices == null) {
			System.out.println("Graph is empty; no edges to edit");
		}

		System.out.println("Value of first vertex:");
		this.printVertices();
        int newValue1 = scanner.nextInt();
        while (!this.containsVertex(newValue1)) {
        	System.out.println("Value not found, try again:");
        	this.printVertices();
        	newValue1 = scanner.nextInt();
        }

        System.out.println("Value of second vertex:");
		this.printVertices();
        int newValue2 = scanner.nextInt();
        while (!this.containsVertex(newValue2)) {
        	System.out.println("Value not found, try again:");
        	this.printVertices();
        	newValue2 = scanner.nextInt();
        }

        int direction;
        if (newValue1 != newValue2) {
	        System.out.println(String.format("Direction:\n1. %1$d --> %2$d\n2. %1$d <-- %2$d\n3. %1$d <--> %2$d", newValue1, newValue2));
	        direction = scanner.nextInt();
	        while (direction < 1 || direction > 3) {
	        	System.out.println(String.format("Enter a valid option (1, 2 or 3):\n1. %1$d --> %2$d\n2. %1$d <-- %2$d\n3. %1$d <--> %2$d", newValue1, newValue2));
	        	this.printVertices();
	        	direction = scanner.nextInt();
	        }
        } else {
        	direction = 4;
        }

        int idx1 = Arrays.binarySearch(this.vertices, newValue1);
        int idx2 = Arrays.binarySearch(this.vertices, newValue2);
		System.out.println("Enter a weight (set to 0 to remove edge):");
        switch (direction) {
        	case 1:
        		this.matrix[idx1][idx2] = scanner.nextInt();
        		break;
        	case 2:
        		this.matrix[idx2][idx1] = scanner.nextInt();
        		break;
        	case 3:
        		int newValue = scanner.nextInt();
        		this.matrix[idx1][idx2] = newValue;
        		this.matrix[idx2][idx1] = newValue;
        		break;
        	case 4:
        		this.matrix[idx1][idx1] = scanner.nextInt();
        		break;
        	default:
        		break;
        }
	}

	public void printMatrix () {
		System.out.printf("%-8s", "");
		for (int i=0; i<this.vertices.length; i++) {
		    System.out.printf("%-8d", this.vertices[i]);
		}
		System.out.println();
		for (int i=0; i<this.vertices.length; i++) {
		    System.out.printf("%-8d", this.vertices[i]);
		    for (int j=0; j<this.vertices.length; j++) {
		        System.out.printf("%-8d", this.matrix[i][j]);
		    }
		    System.out.println();
		}
	}

	public void printVertices () {
		System.out.print("Vertices: ");
        System.out.println(Arrays.toString(this.vertices));
	}

	public boolean containsVertex (int value) {
		for (int i=0; i<this.vertices.length; i++) {
			if (this.vertices[i] == value) {
				return true;
			}
		}
		return false;
	}

    public void exit () {
        scanner.close();
    }
}
