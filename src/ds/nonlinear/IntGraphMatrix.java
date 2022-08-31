package ds.nonlinear;

import java.util.Arrays;

public class IntGraphMatrix {
	public Integer[][] matrix;
	public Integer[] vertices;

	public IntGraphMatrix () {
		this.matrix = matrix;
		this.vertices = vertices;
	}

	public void createVertex (Integer value) {
		if (this.matrix == null) {
			this.matrix =  new Integer[][]{{0}};
			this.vertices = new Integer[]{value};
		} else if (this.containsVertex(value)) {
			return;
		} else {
			Integer newLength = this.vertices.length + 1;
			Integer[][] newMatrix =  new Integer[newLength][newLength];
			for (int i=0; i<newLength; i++) {
				Arrays.fill(newMatrix[i], 0);
			}
			Integer[] newVertex = new Integer[newLength];

			for (Integer i=0; i<this.vertices.length; i++) {
				for (Integer j=0; j<this.vertices.length; j++) {
					newMatrix[i][j] = this.matrix[i][j];
				}
				newVertex[i] = this.vertices[i];
			}
			newVertex[this.vertices.length] = value;

			this.matrix = newMatrix;
			this.vertices = newVertex;
		}
	}

	public Integer[][] readMatrix () {
		if (this.matrix == null) {
			return new Integer[][]{{}};
		}
		return this.matrix;
	}
	public Integer[] readVertices () {
		if (this.matrix == null) {
			return new Integer[]{};
		}
		return this.vertices;
	}

	public void updateVertex (Integer oldValue, Integer newValue) {
		if (this.vertices == null || this.vertices.length == 0) {
			return;
		}
		Integer idx = null;
		for (int i=0; i<this.vertices.length; i++) {
			if (this.vertices[i] == oldValue) {
				idx = i;
			}
			if (this.vertices[i] == newValue) {
				return;
			}
		}
		if (idx != null) {
			this.vertices[idx] = newValue;
		}
	}

	public void deleteVertex (Integer value) {
		if (this.vertices == null || this.vertices.length == 0) {
			return;
		}
		Integer deleteValueIdx = findIdx(value);
		if (deleteValueIdx == -1) {
			return;
		} else if (this.vertices.length == 1 && deleteValueIdx == this.vertices[0]) {
			this.matrix = null;
			this.vertices = null;
			return;
		}
		Integer[][] newMatrix = new Integer[this.vertices.length-1][this.vertices.length-1];
		for (int i=0; i<this.vertices.length-1; i++) {
			Arrays.fill(newMatrix[i], 0);
		}
		Integer[] newVertex = new Integer[this.vertices.length-1];

		if (deleteValueIdx == 0) {
			for (Integer i=1; i<this.vertices.length; i++) {
				for (Integer j=1; j<this.vertices.length; j++) {
					newMatrix[i-1][j-1] = this.matrix[i][j];
				}
				newVertex[i-1] = this.vertices[i];
			} 
		} else if (deleteValueIdx == this.vertices.length-1) {
			for (Integer i=0; i<this.vertices.length-1; i++) {
				for (Integer j=0; j<this.vertices.length-1; j++) {
					newMatrix[i][j] = this.matrix[i][j];
				}
				newVertex[i] = this.vertices[i];
			} 
		} else {
			for (Integer i=0; i<deleteValueIdx; i++) {
				for (Integer j=0; j<deleteValueIdx; j++) {
					newMatrix[i][j] = this.matrix[i][j];
				}
				newVertex[i] = this.vertices[i];
			}
			for (Integer i=deleteValueIdx+1; i<this.vertices.length; i++) {
				for (Integer j=deleteValueIdx+1; j<this.vertices.length; j++) {
					newMatrix[i-1][j-1] = this.matrix[i][j];
				}
				newVertex[i-1] = this.vertices[i];
			}
		}
		this.matrix = newMatrix;
		this.vertices = newVertex;
	}

	public void editEdge (Integer vertice1, Integer vertice2, Integer weight, String direction) {
		if (this.vertices == null || this.vertices.length == 0) {
			return;
		}

        Integer idx1 = findIdx(vertice1);
        Integer idx2 = findIdx(vertice2);
        if (idx1 == -1 || idx2 == -1) {
        	return;
        }

        if (vertice1 == vertice2) {
        	this.matrix[idx1][idx1] = weight;
        	return;
        }

        switch (direction) {
        	case "A => B":
        		this.matrix[idx1][idx2] = weight;
        		break;
        	case "B => A":
        		this.matrix[idx2][idx1] = weight;
        		break;
        	case "A <=> B":
        		this.matrix[idx1][idx2] = weight;
        		this.matrix[idx2][idx1] = weight;
        		break;
        	default:
        		break;
        }
	}

	public Integer findIdx (Integer value) {
        if (this.vertices == null) {
            return -1;
        }
        for (int i=0; i<this.vertices.length; i++) {
        	if (this.vertices[i] == value) {
                return i;
            }
        }
        return -1;
    }

	public boolean containsVertex (Integer value) {
		for (Integer i=0; i<this.vertices.length; i++) {
			if (this.vertices[i] == value) {
				return true;
			}
		}
		return false;
	}
}