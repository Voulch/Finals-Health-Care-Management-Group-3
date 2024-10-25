package Healthcare;

public class Appointment {
    private final String appointmentID;
    private String time;
    String status;
    private final Doctor doctor;
    private final Nurse nurse;
    private final Patient patient;

    public Appointment(String appointmentID, String time, Patient patient, Doctor doctor, Nurse nurse) {
        this.appointmentID = appointmentID;
        this.time = time;
        this.status = "Scheduled";
        this.patient = patient;
        this.doctor = doctor;
        this.nurse = nurse;
    } 
    
    public  String getappointmentID() {
        return appointmentID;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void updatedAppointment(String newTime) {
        this.time = newTime;
    }

    public String scheduledAppointment() {
        return "\nAppointment Scheduled: for " + patient.getName() + " on " + time;
    }

    public String cancelledAppointment() {
        this.status = "Cancelled";
        return "\nAppointment Cancelled: for " + patient.getName() + " on " + time;
    }

    public String viewAppointment() {
        return "\nAppointment ID: " + appointmentID +
               "\nPatient: " + patient.getName() +
               "\nDate and Time: " + time + 
               "\nStatus: " + status + 
               "\nDoctor: " + doctor.getName() + 
               "\nNurse: " + nurse.getName();
    }
}