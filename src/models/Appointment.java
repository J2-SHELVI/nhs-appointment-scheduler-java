package models;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Appointment implements Serializable {

    private String patientName;
    private String patientId;
    private LocalDateTime appointmentDateTime;
    private String appointmentType;
    private String priorityLevel;
    private String attendanceHistory;

    // Constructor
    public Appointment(String patientName, String patientId,
                       LocalDateTime appointmentDateTime,
                       String appointmentType, String attendanceHistory) {
        this.patientName = patientName;
        this.patientId = patientId;
        this.appointmentDateTime = appointmentDateTime;
        this.appointmentType = appointmentType;
        this.attendanceHistory = attendanceHistory;
        this.priorityLevel = calculatePriority();
    }

    // Getters and Setters
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getAppointmentDate() {
        return appointmentDateTime.toLocalDate().toString();
    }

    public String getAppointmentTime() {
        return appointmentDateTime.toLocalTime().toString().substring(0, 5);
    }

    public String getAppointmentType() { return appointmentType; }
    public void setAppointmentType(String appointmentType) { this.appointmentType = appointmentType; }

    public String getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(String priorityLevel) { this.priorityLevel = priorityLevel; }

    public String getAttendanceHistory() { return attendanceHistory; }
    public void setAttendanceHistory(String attendanceHistory) {
        this.attendanceHistory = attendanceHistory;
        this.priorityLevel = calculatePriority();
    }

    // Priority Logic
    private String calculatePriority() {
        if (appointmentType.equalsIgnoreCase("Emergency")) {
            return "High";
        } else if (attendanceHistory.equalsIgnoreCase("Missed")) {
            return "Medium";
        } else if (appointmentType.equalsIgnoreCase("Routine")) {
            return "Low";
        } else {
            return "Medium";
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s %s [Priority: %s]",
                patientName, patientId,
                getAppointmentDate(), getAppointmentTime(),
                priorityLevel);
    }
}
