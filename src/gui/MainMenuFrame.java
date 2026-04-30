package gui;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("NHS Appointment Scheduler - Main Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Appointment Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        JButton addBtn = createStyledButton("Add Appointment");
        addBtn.addActionListener(e -> new AddAppointmentFrame(this).setVisible(true));
        gbc.gridx = 0;
        panel.add(addBtn, gbc);

        JButton viewBtn = createStyledButton("View Appointments");
        viewBtn.addActionListener(e -> new ViewAppointmentsFrame(this).setVisible(true));
        gbc.gridx = 1;
        panel.add(viewBtn, gbc);

        gbc.gridy++;

        JButton upcomingBtn = createStyledButton("Upcoming Appointments");
        upcomingBtn.addActionListener(e -> showUpcomingAppointments());
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(upcomingBtn, gbc);

        add(panel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(220, 50));
        return button;
    }

    private void showUpcomingAppointments() {
        ViewAppointmentsFrame frame = new ViewAppointmentsFrame(this);
        frame.showUpcoming();
        frame.setVisible(true);
    }
}
