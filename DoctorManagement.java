import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorManagement {
    private JFrame frame;
    private JTextField nameField, specializationField, contactField;

    public DoctorManagement() {
        frame = new JFrame("Doctor Management");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        // Create input fields for doctor details
        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Specialization:"));
        specializationField = new JTextField();
        frame.add(specializationField);

        frame.add(new JLabel("Contact Number:"));
        contactField = new JTextField();
        frame.add(contactField);

        // Button to add doctor
        JButton addButton = new JButton("Add Doctor");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDoctor(); // Calls the method to add a doctor
            }
        });
        frame.add(addButton);

        frame.setVisible(true); // Display the frame
    }

    private void addDoctor() {
        String name = nameField.getText();
        String specialization = specializationField.getText();
        String contact = contactField.getText();

        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        String sql = "INSERT INTO doctors (name, specialization, contact_number, availability) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, specialization);
            pstmt.setString(3, contact);
            pstmt.setBoolean(4, true); // Default availability is true
            pstmt.executeUpdate(); // Execute the insert command
            JOptionPane.showMessageDialog(frame, "Doctor added successfully!"); // Success message
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding doctor."); // Error message
        }
    }

    public static void main(String[] args) {
        new DoctorManagement(); // Run the Doctor Management module
    }
}
