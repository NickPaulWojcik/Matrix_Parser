package nicholas.nicholas.wojcik;

public class ConsolePrompt {
	
	//Prompts the user with the main menu
	public static void introductionPrompt() {
		System.out.println();
		System.out.println("__________________________________________________");
		System.out.println("Make a selection");
		System.out.println("1. Enter Matrix");
		System.out.println("2. Upload 8x8 Matrix From File");
		System.out.println("3. Help");
		System.out.println("4. Quit");
		System.out.println("__________________________________________________");
	}
	
	//Confirms the choice made from the main menu
	public static void introductionConfirmation(int choice) {
		if(choice == 1)
			System.out.println("1. Enter Matrix, selected");
		else if(choice == 2)
			System.out.println("2. Upload 8x8 Matrix From File, selected");
		else if(choice == 3)
			System.out.println("3. Help, selected");
		else if(choice == 4)
			System.out.println("4. Quit, selected");
		System.out.println("__________________________________________________");
	}
	
	//Prompts the help information
	public static void helpPrompt() {
		System.out.println("");
		System.out.println("_____________________________________");
		System.out.println("");
		System.out.println("Selecting 1. Enter Matrix:");
		System.out.println("\t You will be prompted to enter a boolean matrix row by row.  Only 0s and 1s are");
		System.out.println("\t accepted inputs.  Begin entering each digit at a time.  The parser will place");
		System.out.println("\t the digits row by row from left to right, and will automatically jump to the new");
		System.out.println("\t row when the end is reached.");
		System.out.println("");
		System.out.println("Selecting 2. Upload 8x8 Matrix From File:");
		System.out.println("\t You will be prompted to enter a file name containing a 8x8 matrix.");
		System.out.println("");
		System.out.println("Selecting 3. Help:");
		System.out.println("\t You will be brought here.");
		System.out.println("");
		System.out.println("Selecting 4. Quit:");
		System.out.println("\t The program will exit the main-loop and quickly finish its execution and close");
		System.out.println("");
		System.out.println("_____________________________________");
		System.out.println("");
	}
	
	//Prompts the user to enter a dimension when inputing their own matrix
	public static void dimensionLengthPrompt() {
		System.out.println("");
		System.out.println("Enter row/col:");
	}
	
	//Prompts the user to enter a file name
	public static void filePrompt() {
		System.out.println("Enter file name:");
	}
}
