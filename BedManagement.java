import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BedManagement {
    private JFrame frame;
    private JTextField wardField, bedNumberField, statusField;

    public BedManagement() {
        frame = new JFrame("Bed Management");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        // Create input fields for bed details
        frame.add(new JLabel("Ward:"));
        wardField = new JTextField();
        frame.add(wardField);

        frame.add(new JLabel("Bed Number:"));
        bedNumberField = new JTextField();
        frame.add(bedNumberField);

        frame.add(new JLabel("Status:"));
        statusField = new JTextField();
        frame.add(statusField);

        // Button to add bed
        JButton addButton = new JButton("Add Bed");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBed(); // Calls the method to add a bed
            }
        });
        frame.add(addButton);

        frame.setVisible(true); // Display the frame
    }

    private void addBed() {
        String ward = wardField.getText();
        int bedNumber = Integer.parseInt(bedNumberField.getText());
        String status = statusField.getText();

        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        String sql = "INSERT INTO beds (ward, bed_number, status) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ward);
            pstmt.setInt(2, bedNumber);
            pstmt.setString(3, status);
            pstmt.executeUpdate(); // Execute the insert command
            JOptionPane.showMessageDialog(frame, "Bed added successfully!"); // Success message
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding bed."); // Error message
        }
    }

    public static void main(String[] args) {
        new BedManagement(); // Run the Bed Management module
    }
}
