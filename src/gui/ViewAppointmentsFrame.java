package gui;

import models.Appointment;
import utils.AppointmentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewAppointmentsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public ViewAppointmentsFrame(MainMenuFrame parent) {
        setTitle("View Appointments");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        JButton refreshButton = new JButton("Refresh");

        searchButton.addActionListener(e -> searchAppointments());
        refreshButton.addActionListener(e -> loadAppointments());

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        String[] columns = {"Patient", "ID", "Date", "Time", "Type", "Priority", "Attendance"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        add(panel);

        loadAppointments();
    }

    private void loadAppointments() {
        populateTable(AppointmentManager.getAllAppointments());
    }

    private void searchAppointments() {
        populateTable(AppointmentManager.searchAppointments(searchField.getText()));
    }

    private void populateTable(List<Appointment> appointments) {
        tableModel.setRowCount(0);

        for (Appointment appointment : appointments) {
            tableModel.addRow(new Object[]{
                    appointment.getPatientName(),
                    appointment.getPatientId(),
                    appointment.getAppointmentDate(),
                    appointment.getAppointmentTime(),
                    appointment.getAppointmentType(),
                    appointment.getPriorityLevel(),
                    appointment.getAttendanceHistory()
            });
        }
    }

    public void showUpcoming() {
        populateTable(AppointmentManager.getUpcomingAppointments());
    }
}
