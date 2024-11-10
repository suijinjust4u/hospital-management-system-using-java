import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientManagement {
    private JFrame frame;
    private JTextField nameField, ageField, genderField, contactField, addressField;

    public PatientManagement() {
        frame = new JFrame("Patient Management");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        // Create input fields for patient details
        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Age:"));
        ageField = new JTextField();
        frame.add(ageField);

        frame.add(new JLabel("Gender:"));
        genderField = new JTextField();
        frame.add(genderField);

        frame.add(new JLabel("Contact Number:"));
        contactField = new JTextField();
        frame.add(contactField);

        frame.add(new JLabel("Address:"));
        addressField = new JTextField();
        frame.add(addressField);

        // Button to add patient
        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient(); // Calls the method to add a patient
            }
        });
        frame.add(addButton);

        frame.setVisible(true); // Display the frame
    }

    private void addPatient() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderField.getText();
        String contact = contactField.getText();
        String address = addressField.getText();

        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        String sql = "INSERT INTO patients (name, age, gender, contact_number, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, contact);
            pstmt.setString(5, address);
            pstmt.executeUpdate(); // Execute the insert command
            JOptionPane.showMessageDialog(frame, "Patient added successfully!"); // Success message
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding patient."); // Error message
        }
    }

    public static void main(String[] args) {
        new PatientManagement(); // Run the Patient Management module
    }
}
