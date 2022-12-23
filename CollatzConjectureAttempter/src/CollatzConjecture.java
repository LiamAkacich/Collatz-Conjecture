import java.util.ArrayList;
import java.util.Scanner;

public class CollatzConjecture {

	private Scanner console;

	public CollatzConjecture() {
		this.console = new Scanner(System.in);
		heading("Collatz Conjecture Attempter");
		menu();
	}

	// Displays a menu for the user to select a process from.
	public void menu() {
		heading("Menu");

		System.out.printf("%4s %s\n", "1.", "New Attempt");
		System.out.printf("%4s %s\n", "2.", "View Previous Attempts");
		System.out.printf("%4s %s\n", "3.", "Exit");
		System.out.printf("\n%s ", "Please enter a selection:");

		int choice = Integer.parseInt(this.console.nextLine());

		// The value entered for choice determines the method that is called.
		switch (choice) {
		case 1:
			newAttempt();
			menu();
			break;
		case 2:
			viewAttempts();
			menu();
			break;
		case 3:
			// exit();
			break;
		default:
			System.out.println("Error - Not a valid input\n");
			menu();
			break;
		}
	}
	
	public void newAttempt() {
		heading("New Collatz Conjecture Test");
		
		System.out.print("Please enter a number to test: ");

		// TODO handle numberFormatException
		long attemptNumber = Long.parseLong(this.console.nextLine());
		
		Attempt attempt = processAttempt(attemptNumber);
		
		System.out.println("Number Tested: " + attempt.getAttemptNumber());
		System.out.println("Stopping Time: " + attempt.getStoppingTime());
		System.out.println("Highest Reached: " + attempt.getHighestReached());
		
		if (!DBUtil.attemptExists(attemptNumber)) {
			DBUtil.addAttempt(attempt);
		}
		
	}
	
	private Attempt processAttempt(long attemptNumber) {
		Attempt currentAttempt; 
		
		long number = attemptNumber;
		long stoppingTime = 0;
		long highestReached = 0;
		
		
		do {
			if (isEven(number)) {
				number = number/2;
			} else {
				number = 3 * number + 1;
			}
			
			stoppingTime++;
			
			if (number > highestReached) {
				highestReached = number;
			}
			
		} while (number != 1);
		
		currentAttempt = new Attempt(attemptNumber, stoppingTime, highestReached);

		return currentAttempt;
	}
	
	private void viewAttempts() {
		ArrayList<Attempt> attempts = DBUtil.getAttempts();
		
		heading("All Tested Numbers");
		
		System.out.printf("%-15s %-17s %-20s\n", "Number", "Stopping Time", "Highest Reached");
		System.out.printf("%-15s %-17s %-20s\n", "------", "-------------", "---------------");
		
		for (Attempt attempt : attempts) {
			System.out.printf("%-15d %-22d %-20d\n", attempt.getAttemptNumber(), attempt.getStoppingTime(), attempt.getHighestReached());
		}
	}
	
	private boolean isEven(long input) {
		boolean result = false;
		 if (input % 2 == 0) {
			 result = true;
		 }
		 
		 return result;
	}

	// Helper method used to display consistently formatted headings
	private void heading(String title) {
		String accent = "*****";

		System.out.printf("\n%s ", accent);
		System.out.printf("%s", title);
		System.out.printf(" %s\n\n", accent);
	}

}
