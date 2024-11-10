import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentManagement {
    private JFrame frame;
    private JTextField patientIdField, doctorIdField, appointmentDateField, statusField;

    public AppointmentManagement() {
        frame = new JFrame("Appointment Management");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        // Create input fields for appointment details
        frame.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        frame.add(patientIdField);

        frame.add(new JLabel("Doctor ID:"));
        doctorIdField = new JTextField();
        frame.add(doctorIdField);

        frame.add(new JLabel("Appointment Date (YYYY-MM-DD):"));
        appointmentDateField = new JTextField();
        frame.add(appointmentDateField);

        frame.add(new JLabel("Status:"));
        statusField = new JTextField();
        frame.add(statusField);

        // Button to add appointment
        JButton addButton = new JButton("Add Appointment");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppointment(); // Calls the method to add an appointment
            }
        });
        frame.add(addButton);

        frame.setVisible(true); // Display the frame
    }

    private void addAppointment() {
        int patientId = Integer.parseInt(patientIdField.getText());
        int doctorId = Integer.parseInt(doctorIdField.getText());
        String appointmentDate = appointmentDateField.getText();
        String status = statusField.getText();

        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setString(3, appointmentDate);
            pstmt.setString(4, status);
            pstmt.executeUpdate(); // Execute the insert command
            JOptionPane.showMessageDialog(frame, "Appointment added successfully!"); // Success message
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding appointment."); // Error message
        }
    }

    public static void main(String[] args) {
        new AppointmentManagement(); // Run the Appointment Management module
    }
}
