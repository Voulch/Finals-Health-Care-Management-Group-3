package Healthcare;
import java.util.LinkedList;

public class Patient extends User {
    private final LinkedList<MedicalLog> medicalLogs;

    public Patient(String id, String name, String dateOfBirth, String contact) {
        super(id, name, dateOfBirth, contact);
        this.medicalLogs = new LinkedList<>();
    }

    public Patient(LinkedList<MedicalLog> medicalLogs, int total, String id, String name, String dateOfBirth, String contact) {
        super(id, name, dateOfBirth, contact);
        this.medicalLogs = medicalLogs;
    }

    public void addLog(MedicalLog log) {
        this.medicalLogs.add(log);
    }   

}