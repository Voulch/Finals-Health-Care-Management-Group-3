package Healthcare;

import java.util.LinkedList;
import java.util.Scanner;

public class Controller implements ISchedule, IUpdate, ICancel, ISearch, IFilter, IDisplay {
    Manager manager;
    Validation validation;
    Scanner scan;
    
    public Controller(Manager manager, Validation validation, Scanner scan) {
        this.manager = manager;
        this.validation = validation;
        this.scan = scan;
    }

    @Override
    public void schedule() {
        System.out.println("\n========= Available Schedules =========\n");
        LinkedList<String> availableTimes = manager.getAvailableTime();
        for (int i = 0; i < availableTimes.size(); i++) {
            System.out.println((i + 1) + ". " + availableTimes.get(i));
        }
        System.out.println("\n=======================================");
        System.out.print("\nChoose a Schedule: ");
        int timeChoice = validation.valInt(scan, 1, availableTimes.size());
        String time = availableTimes.get(timeChoice - 1);

        System.out.print("\nEnter an appointment ID: ");
        String appointmentID = validation.valScan(scan);

        if (manager.appointmentExists(appointmentID)) {
            System.out.println("\nAppointment ID already exists. Please choose a different ID.");
            return;
        }

        System.out.println("\n======== Enter Patient details ========\n");
        String name = validation.valString("Name: ");
        String patientId = validation.valString("Patient ID: ");
        String dateOfBirth = validation.valString("Date of Birth: ");
        String phoneNumber = validation.valString("Phone Number: ");
        System.out.println("\n=======================================");
        Patient patient = new Patient(patientId, name, dateOfBirth, phoneNumber);

        LinkedList<Doctor> doctors = manager.getDoctors();
        System.out.println("\n========== Available Doctors ==========\n");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName() + " - " + doctors.get(i).getSpecialty());
        }
        System.out.print("\nWhich Doctor do you need?: ");
        int doctorChoice = validation.valInt(scan, 1, doctors.size());
        Doctor doctor = doctors.get(doctorChoice - 1);
        System.out.println("\n=======================================");

        LinkedList<Nurse> nurses = manager.getNurses();
        System.out.println("\n========== Available Nurses ===========\n");
        for (int i = 0; i < nurses.size(); i++) {
            System.out.println((i + 1) + ". " + nurses.get(i).getName() + " - " + nurses.get(i).getDepartment());
        }
        System.out.print("\nWhich Nurse do you need?: ");
        int nurseChoice = validation.valInt(scan, 1, nurses.size());
        Nurse nurse = nurses.get(nurseChoice - 1);
        System.out.println("\n=======================================");

        Appointment appointment = new Appointment(appointmentID, time, patient, doctor, nurse);
        String result = manager.addAppointment(appointment);
        System.out.println(result);

        MedicalLog medicalLog = new MedicalLog(appointment);
        manager.addMedicalLog(medicalLog); 
    }

    @Override
    public void update() {
        System.out.println("\n======== Update an Appointment ========");
        System.out.print("\nEnter an appointment ID: ");
        String appointmentID = validation.valScan(scan);
        System.out.println("\n=======================================");

        if (!manager.appointmentExists(appointmentID)) {
            System.out.println("\nERROR! Appointment ID does not exist.");
            return;
        }

        System.out.println("\n======== Available Schedules ========\n");
        LinkedList<String> availableTimes = manager.getAvailableTime();
        for (int i = 0; i < availableTimes.size(); i++) {
            System.out.println((i + 1) + ". " + availableTimes.get(i));
        }
        System.out.println("\n=======================================");

        System.out.print("\nChoose a new Schedule: ");
        int timeChoice = validation.valInt(scan, 1, availableTimes.size());
        String newTime = availableTimes.get(timeChoice - 1);
        String result = manager.updateAppointment(appointmentID, newTime);
        System.out.println(result);
    }

    @Override
    public void cancel() {
        System.out.println("\n======== Cancel an Appointment ========");
        System.out.print("\nEnter appointment ID: ");
        String appointmentID = validation.valScan(scan);
        System.out.println("\n=======================================");

        if (!manager.appointmentExists(appointmentID)) {
            System.out.println("\nERROR! Appointment ID does not exist.");
            return;
        }

        String result = manager.cancelAppointment(appointmentID);
        System.out.println(result);
    }

    @Override
    public void search() {
        System.out.println("\n======== Find your Appointment ========");
        System.out.print("\nEnter appointment ID: ");
        String searchValue = validation.valScan(scan);
        LinkedList<Appointment> searchResults = manager.searchAppointment(searchValue);
        System.out.println("\n=======================================");

        if (searchResults.isEmpty()) {
            System.out.println("\nERROR! No appointments found.");
        } else {
            for (Appointment appointment : searchResults) {
                System.out.println(appointment.viewAppointment());
            }
        }
    }

    @Override
    public void filter() {
        System.out.println("\n========= Filter Appointments =========");
        System.out.println("\nChoose an Appointment Status:");
        System.out.println("1. Scheduled");
        System.out.println("2. Cancelled");
        System.out.println("\n=======================================");
        System.out.print("Enter your choice [1 or 2]: ");
        int choice = validation.valInt(scan, 1, 2);
        String status = (choice == 1) ? "Scheduled" : "Cancelled";
        LinkedList<Appointment> filteredAppointments = manager.filterAppointment(status);

        if (filteredAppointments.isEmpty()) {
            System.out.println("\nERROR! No appointments found with the specified status.");
        } else {
            for (Appointment appointment : filteredAppointments) {
                System.out.println(appointment.viewAppointment());
            }
        }
    }

    @Override
    public void display() {
        LinkedList<MedicalLog> medicalLogs = manager.getMedicalLogs();
        if (medicalLogs != null) {
            System.out.println("\n================= Logs ================");
            System.out.println("\nNumber of medical logs: " + medicalLogs.size());
            for (MedicalLog log : medicalLogs) {
                if (log != null) {
                    log.displayLog();
                } else {
                    System.out.println("ERROR! There is no Medical Logs Created.");
                }
            }
        } else {
            System.out.println("ERROR! There is no Medical Logs Created.");
        }
    }
}