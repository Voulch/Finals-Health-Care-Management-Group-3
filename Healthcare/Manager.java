package Healthcare;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Manager {
    private final Map<String, Appointment> appointments = new HashMap<>();
    private final LinkedList<MedicalLog> medicalLogs = new LinkedList<>();
    private final LinkedList<String> availableTime;
    private final LinkedList<Doctor> doctors;
    private final LinkedList<Nurse> nurses;

    public Manager(LinkedList<Doctor> doctors, LinkedList<Nurse> nurses) {
        this.doctors = doctors;
        this.nurses = nurses;
        availableTime = new LinkedList<>();
        availableTime.add("7:00 AM - 9:00 AM");
        availableTime.add("10:00 AM - 12:00 PM");
        availableTime.add("1:00 PM - 3:00 PM");
        availableTime.add("5:00 PM - 7:00 PM");
        availableTime.add("8:00 PM - 10:00 PM");
    }
  
    public LinkedList<String> getAvailableTime() {
        return availableTime;
    }

    public boolean appointmentExists(String appointmentID) {
        return appointments.containsKey(appointmentID);
    }

    public LinkedList<Doctor> getDoctors() {
        return doctors;
    }

    public LinkedList<Nurse> getNurses() {
        return nurses;
    }

    public LinkedList<MedicalLog> getMedicalLogs() {
        return medicalLogs;
    }

    public void addMedicalLog(MedicalLog log) {
        medicalLogs.add(log);
    }

    public String addAppointment(Appointment appointment) {
        if (availableTime.contains(appointment.getTime())) {
            appointments.put(appointment.getappointmentID(), appointment);
            availableTime.remove(appointment.getTime());
            return appointment.scheduledAppointment();
        } else {
            return "\nERROR! The Schedule you selected is Unavailable.";
        }
    }

    public String updateAppointment(String appointmentID, String newTime) {
        Appointment appointment = appointments.get(appointmentID);
        if (appointment != null && availableTime.contains(newTime)) {
            availableTime.add(appointment.getTime());
            availableTime.remove(newTime);
            appointment.updatedAppointment(newTime);
            return "\nUpdated: " + appointment.viewAppointment();
        } else {
            return "\nERROR! ID not found or the recently chosen Schedule is Unavailable.";
        }
    }

    public String cancelAppointment(String appointmentID) {
        Appointment appointment = appointments.get(appointmentID);
        if (appointment != null) {
            availableTime.add(appointment.getTime());
            appointment.status = "cancelled";
            return appointment.cancelledAppointment();
        } else {
            return "\nERROR! ID not found.";
        }
    }

    public LinkedList<Appointment> searchAppointment(String searchID) {
        LinkedList<Appointment> searchResults = new LinkedList<>();
        for (Appointment appointment : appointments.values()) {
            if (appointment.getappointmentID().equals(searchID)) {
                searchResults.add(appointment);
            }
        }
        return searchResults;
    }

    public LinkedList<Appointment> filterAppointment(String status) {
        LinkedList<Appointment> filteredAppointments = new LinkedList<>();
        for (Appointment appointment : appointments.values()) {
            if (appointment.getStatus().equalsIgnoreCase(status)) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }
}