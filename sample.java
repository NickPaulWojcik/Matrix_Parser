public class sample {

	
	
	public void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	public boolean reflexive(int[][] matrix) {
		boolean check = true;
			
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[i][i] != 1) {
				check = false;
			}
		}
		return check;
	}
	
	
	
	
		
		public boolean symmetry(int[][] matrix) {
			int a = 1;
			boolean check = true;
			for(int i = 0; i < matrix.length; i++) {
				for(int j = a; j < matrix.length; j++) {
					if(matrix[i][j] == 1 && matrix[j][i] == 0) {
						check = false;
					}
					if(matrix[i][j] == 0 && matrix[j][i] == 1) {
						check = false;
					}
				}
				a++;
			}
			return check;
		}
		
		
		
		
		
		public boolean transitivity(int[][] matrix) {
			boolean isTransitive = true;
			
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix.length; j++) {
					if(matrix[i][j] == 1) {
						for(int n = 0; n < matrix.length; n++) {
							if(matrix[j][n] == 1) {
								if(matrix[i][n] != 1) {
									isTransitive = false;
								}
							}
						}
					}
				}
			}
			return isTransitive;
		}
		
		
		
		
		
		
		public void matrixToTransitiveClosure(int[][] matrix) {
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix.length; j++) {
					if(matrix[i][j] == 1) {
						for(int a = 0; a < matrix.length; a++) {
							if(matrix[j][a] == 1) {
								if(matrix[i][a] != 1) {
									matrix[i][a] = 1;
								}
							}
						}
					}
				}
			}
		}
}
