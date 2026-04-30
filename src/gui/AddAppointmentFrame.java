package gui;

import models.Appointment;
import utils.AppointmentManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddAppointmentFrame extends JFrame {

    private JTextField nameField;
    private JTextField idField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> historyCombo;

    public AddAppointmentFrame(MainMenuFrame parent) {
        setTitle("Add Appointment");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        idField = new JTextField();
        typeCombo = new JComboBox<>(new String[]{"Routine", "Check-up", "Emergency"});
        historyCombo = new JComboBox<>(new String[]{"Good", "Missed", "Unknown"});

        panel.add(new JLabel("Patient Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Patient ID:"));
        panel.add(idField);

        panel.add(new JLabel("Appointment Type:"));
        panel.add(typeCombo);

        panel.add(new JLabel("Attendance History:"));
        panel.add(historyCombo);

        JButton saveButton = new JButton("Save Appointment");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> saveAppointment());
        cancelButton.addActionListener(e -> dispose());

        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);
    }

    private void saveAppointment() {
        if (nameField.getText().trim().isEmpty() || idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient name and ID are required.");
            return;
        }

        Appointment appointment = new Appointment(
                nameField.getText().trim(),
                idField.getText().trim(),
                LocalDateTime.now().plusDays(1),
                (String) typeCombo.getSelectedItem(),
                (String) historyCombo.getSelectedItem()
        );

        AppointmentManager.addAppointment(appointment);

        JOptionPane.showMessageDialog(this,
                "Appointment saved successfully!\nPriority: " + appointment.getPriorityLevel());

        dispose();
    }
}