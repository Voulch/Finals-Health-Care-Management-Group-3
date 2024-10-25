package Healthcare;

public class MedicalLog {
    private final String logId;
    private final Patient patient;
    private final Doctor doctor;
    private Nurse nurse;

    public MedicalLog(Appointment appointment) {
        this.logId = appointment.getappointmentID();
        this.patient = appointment.getPatient();
        this.doctor = appointment.getDoctor();
        this.nurse = appointment.getNurse();
    }

    public void addStaff(Nurse nurse) {
        this.nurse = nurse;
    }
    
    public void displayLog() {
        System.out.println("\n============= Medical Log =============");
        System.out.println("\nLog ID: " + logId);
        System.out.println("Patient ID: " + patient.getId());
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Nurse: " + nurse.getName());
        System.out.println("\n=======================================");
    }
}