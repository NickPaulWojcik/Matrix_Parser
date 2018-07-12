package nicholas.nicholas.wojcik;

import java.io.IOException;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws IOException {
		
		//Result flags
		boolean isReflexive;
		boolean isSymmetric;
		boolean isAntiSymmetric;
		boolean isTransitive;
		boolean isER;
		
		//Objects/fields for main loop
		MatrixParser matrix = new MatrixParser();
		Scanner keyboard = new Scanner(System.in);
		String choice = "0";
		boolean run = true;
		
		//Main loop, only exited if the user selects option 4 during the main menu prompt
		while(run) {
			//Prompt user with introduction screen
			ConsolePrompt.introductionPrompt();
			
			//Input validation
			keyboard.nextLine();
			choice = keyboard.nextLine();
			while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
				System.out.println("INVALID INPUT1");
				choice = keyboard.nextLine();
			}
			
			//Prompt user with confirmation of choice
			ConsolePrompt.introductionConfirmation(Integer.parseInt(choice));
			
			//Break while-loop and finish program if "4. Quit" is selected
			if(choice.equals("4"))
				break;
			
			//Decision chain to call appropriate calculation methods
			if(choice.equals("1")) {
				int dim = 0;
				
				//Input validation for positive dimension
				do{
					ConsolePrompt.dimensionLengthPrompt();
					while(!keyboard.hasNextInt()) {
						keyboard.next();
						System.out.println("INVALID INPUT2");
					}
					dim = keyboard.nextInt();
				}while(dim <= 0);
				System.out.println(dim + " Accepted");
				System.out.println("__________________________________________________");
				
				//Populate the matrix
				matrix.populateMatrix(dim);
				System.out.println("__________________________________________________");
				
				//Set result flags using MatrixParser's methods
				isReflexive = matrix.checkReflexive();
				isSymmetric = matrix.checkSymmetry();
				isAntiSymmetric = matrix.checkAntiSymmetry();
				isTransitive = matrix.checkTransitivity();
				if(isReflexive && isSymmetric && isTransitive)
					isER = true;
				else
					isER = false;
				
				//Output results
				System.out.println("Given Matrix:");
				matrix.printMatrix();
				System.out.println("Reflexive: " + isReflexive);
				System.out.println("Symmetric: " + isSymmetric);
				System.out.println("Anti-Symmetric: " + isAntiSymmetric);
				System.out.println("Transitive: " + isTransitive);
				System.out.println("Equivalence Relation: " + isER);
				
				//Convert and output transitive closure if not currently transitive
				if(!isTransitive) {
					matrix.convertToTransitiveClosure();
					System.out.println("Transitive Closure:");
					matrix.printMatrix();
				}
				
				if(isER) {
					//Output equivalence class
					System.out.print("Equivalence Class: ");
					matrix.calculateEquivalenceClass();
					System.out.println();
					System.out.println();
					System.out.println();
				}
				
			//If user selected to input from file
			}else if(choice.equals("2")) {
				try {				
						String fileName;
						fileName = keyboard.nextLine();
						
						//Calls the matrix object to upload and print out the analy
						matrix.analyzeFile(fileName);
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			//If user selected the help option from the main menu
			}else if(choice.equals("3")) {
				
				//Prints help information
				ConsolePrompt.helpPrompt();
			}
		}
		//Closes open streams and finishes applications runtime
		keyboard.close();
		System.out.println("Application Finished.");
	}

}
