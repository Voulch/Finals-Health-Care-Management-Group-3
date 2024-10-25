package Healthcare;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Validation validation = new Validation(scanner);
        
        LinkedList<Doctor> doctors = Doctor.Doctors();
        LinkedList<Nurse> nurses = Nurse.Nurses();

        Manager manager = new Manager(doctors, nurses);
        Controller controller = new Controller(manager, validation, scanner);

        manageAppointments(scanner, controller, validation);
    }
    
    private static void manageAppointments(Scanner scanner, Controller controller, Validation validation) {
        while (true) {
            System.out.println("\n=======================================");
            System.out.println("||       WELFARE RECORDS SYSTEM      ||");
            System.out.println("=======================================");
            System.out.println("\n========= Manage Appointments =========\n");
            System.out.println("1. Schedule an Appointment");
            System.out.println("2. Update an Appointment");
            System.out.println("3. Cancel an Appointment");
            System.out.println("4. Search an Appointment");
            System.out.println("5. Filter an Appointment");
            System.out.println("6. View Medical Logs");
            System.out.println("7. Exit");
            System.out.println("\n=======================================");
            System.out.print("\nChoose an option: ");
            int choice = validation.valInt(scanner, 1, 7);

            switch (choice) {
                case 1 -> controller.schedule();
                case 2 -> controller.update();
                case 3 -> controller.cancel();
                case 4 -> controller.search();        
                case 5 -> controller.filter();
                case 6 -> controller.display();
                case 7 -> {
                    System.out.println("Exiting....");
                    System.exit(0);
                }
                default -> System.out.println("\nERROR! Invalid option. Please try again.");
            }
        }
    }
}