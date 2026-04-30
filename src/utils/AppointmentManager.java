package utils;

import models.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentManager {

    private static final List<Appointment> appointments = new ArrayList<>();

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public static List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public static boolean updateAppointment(String patientId, Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPatientId().equals(patientId)) {
                appointments.set(i, updatedAppointment);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteAppointment(String patientId) {
        return appointments.removeIf(apt -> apt.getPatientId().equals(patientId));
    }

    public static List<Appointment> searchAppointments(String searchTerm) {
        return appointments.stream()
                .filter(apt -> apt.getPatientName().toLowerCase().contains(searchTerm.toLowerCase())
                        || apt.getPatientId().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<Appointment> getUpcomingAppointments() {
        LocalDateTime weekFromNow = LocalDateTime.now().plusDays(7);

        return appointments.stream()
                .filter(apt -> !apt.getAppointmentDateTime().isBefore(LocalDateTime.now()))
                .filter(apt -> apt.getAppointmentDateTime().isBefore(weekFromNow))
                .sorted((a1, a2) -> a1.getAppointmentDateTime().compareTo(a2.getAppointmentDateTime()))
                .collect(Collectors.toList());
    }

    public static String suggestTimeSlot(String priority) {
        switch (priority.toLowerCase()) {
            case "high":
                return "Next available slot (within 24 hours)";
            case "medium":
                return "Within 3 days";
            default:
                return "Within 7 days";
        }
    }

    public static void loadSampleData() {
        appointments.clear();

        appointments.add(new Appointment("John Smith", "P001",
                LocalDateTime.now().plusDays(1).withHour(10).withMinute(0),
                "Routine", "Good"));

        appointments.add(new Appointment("Jane Doe", "P002",
                LocalDateTime.now().plusDays(2).withHour(14).withMinute(30),
                "Emergency", "Good"));

        appointments.add(new Appointment("Bob Wilson", "P003",
                LocalDateTime.now().plusDays(3).withHour(9).withMinute(15),
                "Check-up", "Missed"));
    }
}