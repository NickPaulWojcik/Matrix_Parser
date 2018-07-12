package nicholas.nicholas.wojcik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixParser {
	//matrix to manipulate
	int matrix[][];
	//Array used for checking for double outputs
	ArrayList<Integer> alreadyReported = new ArrayList<Integer>();
	//Flags
	boolean isReflexive;
	boolean isSymmetric;
	boolean isAntiSymmetric;
	boolean isTransitive;
	boolean isER;
	
	//Populates the matrix digit by digit row by row, includes input validation for boolean matrix
	public void populateMatrix(int dim) {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		matrix = new int[dim][dim];
		
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim; j++) {
				System.out.println("Enter r" + i +" c" + j + "(0s and 1s only):");
				
				//Input validation, only 1s and 0s
				do{
					while(!keyboard.hasNextInt()) {
						keyboard.next();
						System.out.println("INVALID INPUT4");
					}
					matrix[i][j] = keyboard.nextInt();
				}while(matrix[i][j] != 0 && matrix[i][j] != 1);
			}
		}
	}
	
	//Prints the current matrix stored in the object, if there is one
	public void printMatrix() {
		if(matrix != null) {
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix.length; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
	
	//Returns boolean based on REFLEXITIVITY
	public boolean checkReflexive() {
		boolean check = true;
		
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[i][i] != 1) {
				check = false;
			}
		}
		return check;
	}
	
	//Returns boolean based on SYMMETRY
	public boolean checkSymmetry() {
		int n = 1;
		boolean check = true;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = n; j < matrix.length; j++) {
				if(matrix[i][j] == 1 && matrix[j][i] == 0) {
					check = false;
				}
				if(matrix[i][j] == 0 && matrix[j][i] == 1) {
					check = false;
				}
			}
			n++;
		}
		return check;
	}
	
	//Returns boolean based on ANTISYMMETRY
	public boolean checkAntiSymmetry() {
		int n = 1;
		boolean check = true;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = n; j < matrix.length; j++) {
				if(matrix[i][j] == 1 && matrix[j][i] != 0) {
					check = false;
				}
			}
			n++;
		}
		return check;
	}
	
	//Returns boolean based on TRANSITIVITY
	public boolean checkTransitivity() {
		boolean check = true;
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[i][j] == 1) {
					for(int n = 0; n < matrix.length; n++) {
						if(matrix[j][n] == 1) {
							if(matrix[i][n] != 1) {
								check = false;
							}
						}
					}
				}
			}
		}
		return check;
	}
	
	//Converts the matrix to its transitive closure, same as checkTransitivity
	//but instead of setting a flag to false, it changes the current position to 1
	public void convertToTransitiveClosure() {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[i][j] == 1) {
					for(int n = 0; n < matrix.length; n++) {
						if(matrix[j][n] == 1) {
							if(matrix[i][n] != 1) {
								matrix[i][n] = 1;
							}
						}
					}
				}
			}
		}
	}
	
	//Calculates the equivalence class for the matrix
	public void calculateEquivalenceClass() {
		boolean first = true;
		System.out.print("{");
		int[] primary = new int[matrix.length];
		int[] secondary = new int[matrix.length];
		int count = 0;
		
		//Print columns that are lonely classes
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				primary[j] = matrix[j][i];
				if(primary[j] == 1) {
					count++;
				}
			}
			if(count == 1) {
				int addit = i + 1;
				System.out.print("{" + addit + "} ");
			}
			count = 0;
		}

		//While-loop to parse columns
		int cursorCol = 0;
		while(cursorCol < matrix.length) {
				
			//While-loop to parse rows into primary
			int cursorRow = 0;
			if(!first) {
				System.out.print("}");
				first = false;
			}
			while(cursorRow < matrix.length) {
				primary[cursorRow] = matrix[cursorRow][cursorCol];
				if(primary[cursorRow] == 1) {
					count++;
				}
				cursorRow++;
			}

			//If there are more than 1 1s in column
			if(count > 1) {
				int addi = cursorCol+1;
				if(!alreadyReported.contains(addi)) {
					System.out.print("{" + addi + " ");  //***************************************************************************
				}
				int tempCol = cursorCol + 1;
				int tempRow = 0;
				
				//While-loop to populated secondary array
				while(tempCol < matrix.length) {
					while(tempRow < matrix.length) {
						secondary[tempRow] = matrix[tempRow][tempCol];
						tempRow++;
					}
					
					//Compares primary and secondary array to see if they belong in the same class
					if(Arrays.equals(primary, secondary)) {
						int ad = tempCol+1;
						if(!alreadyReported.contains(ad)) {
							System.out.print(ad + " ");
						}
						alreadyReported.add(ad);
					}		
					tempCol++;
					tempRow = 0;
				}
			}
			count = 0;
			cursorCol++;
		}	
		System.out.print("}}");
		alreadyReported.clear();
	}
	
	//Used when the user provides a file with an 8x8 boolean matrix in it
	public void analyzeFile(String fileName) throws IOException {
		try {
			FileReader reader = new FileReader(fileName);
			@SuppressWarnings("resource")
			BufferedReader buffr = new BufferedReader(reader);
			matrix = new int[8][8];
			String line;
			int i = 0;
			while((line = buffr.readLine()) != null && i < 8) {
				int j = 0;
				String delim = "[ ]+";
				String tokens[] = line.split(delim);
				for(String a : tokens) {
					matrix[i][j] = Integer.parseInt(a);
					j++;
				}
				i++;
			}
			
			// !Repetitive Code!  Below is also used in Runner.java (needs to be put in its own methods)
			
			//Set result flags using MatrixParser's methods
			isReflexive = this.checkReflexive();
			isSymmetric = this.checkSymmetry();
			isAntiSymmetric = this.checkAntiSymmetry();
			isTransitive = this.checkTransitivity();
			if(isReflexive && isSymmetric && isTransitive)
				isER = true;
			else
				isER = false;
			
			//Output results
			System.out.println("Given Matrix:");
			this.printMatrix();
			System.out.println();
			System.out.println("Reflexive: " + isReflexive);
			System.out.println("Symmetric: " + isSymmetric);
			System.out.println("Anti-Symmetric: " + isAntiSymmetric);
			System.out.println("Transitive: " + isTransitive);
			System.out.println("Equivalence Relation: " + isER);
			
			//Convert and output transitive closure if not currently transitive
			if(!isTransitive) {
				this.convertToTransitiveClosure();
				System.out.println("Transitive Closure:");
				this.printMatrix();
			}
			
			if(isER) {
				//Output equivalence class
				System.out.print("Equivalence Class: ");
				this.calculateEquivalenceClass();
				System.out.println();
				System.out.println();
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
