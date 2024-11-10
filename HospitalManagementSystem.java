import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalManagementSystem {
    private JFrame frame;

    public HospitalManagementSystem() {
        // Create the main frame
        frame = new JFrame("Hospital Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1)); // Layout for buttons

        // Create buttons for each module
        JButton patientButton = new JButton("Patient Management");
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PatientManagement(); // Open Patient Management module
            }
        });
        frame.add(patientButton);

        JButton doctorButton = new JButton("Doctor Management");
        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DoctorManagement(); // Open Doctor Management module
            }
        });
        frame.add(doctorButton);

        JButton bedButton = new JButton("Bed Management");
        bedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BedManagement(); // Open Bed Management module
            }
        });
        frame.add(bedButton);

        JButton appointmentButton = new JButton("Appointment Management");
        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AppointmentManagement(); // Open Appointment Management module
            }
        });
        frame.add(appointmentButton);

        JButton feedbackButton = new JButton("Feedback Management");
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FeedbackManagement(); // Open Feedback Management module
            }
        });
        frame.add(feedbackButton);

        frame.setVisible(true); // Display the frame
    }

    public static void main(String[] args) {
        new HospitalManagementSystem(); // Start the application
    }
}
