package Healthcare;
import java.util.Scanner;

public class Validation {
    
    private final Scanner scanner;

    public Validation(Scanner scanner) {
        this.scanner = scanner;
    }

    public String valString(String string) {
        while (true) {
            System.out.print(string);
            String input = scanner.nextLine();
            if (input.trim().length() > 0) {
                return input;
            } else {
                System.out.println("\nERR0R! Invalid Input\n");
            }
        }
    }

    public String valScan(Scanner scanner) {
        int attempts = 0;
        while (true) {
            if (attempts > 0) {
                System.out.print("Please enter a [number/letter]: ");
            }
            String input = scanner.nextLine();
            if (input.trim().length() > 0) {
                return input;
            } else {
                System.out.println("\nERR0R! Invalid Input\n");
            }
            attempts++;
        }
    }

    public int valInt(Scanner scanner2, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner2.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.print("\nERR0R! Invalid Input, Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("\nERR0R! Invalid Input, Please enter a number between " + min + " and " + max + ": ");
            }
        }
    }
}