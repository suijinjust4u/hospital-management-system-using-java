import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackManagement {
    private JFrame frame;
    private JTextField patientIdField, doctorIdField, ratingField, commentsField;

    public FeedbackManagement() {
        frame = new JFrame("Feedback Management");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        // Create input fields for feedback details
        frame.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        frame.add(patientIdField);

        frame.add(new JLabel("Doctor ID:"));
        doctorIdField = new JTextField();
        frame.add(doctorIdField);

        frame.add(new JLabel("Rating (1-5):"));
        ratingField = new JTextField();
        frame.add(ratingField);

        frame.add(new JLabel("Comments:"));
        commentsField = new JTextField();
        frame.add(commentsField);

        // Button to add feedback
        JButton addButton = new JButton("Add Feedback");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFeedback(); // Calls the method to add feedback
            }
        });
        frame.add(addButton);

        frame.setVisible(true); // Display the frame
    }

    private void addFeedback() {
        int patientId = Integer.parseInt(patientIdField.getText());
        int doctorId = Integer.parseInt(doctorIdField.getText());
        int rating = Integer.parseInt(ratingField.getText());
        String comments = commentsField.getText();

        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        String sql = "INSERT INTO feedback (patient_id, doctor_id, rating, comments) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setInt(3, rating);
            pstmt.setString(4, comments);
            pstmt.executeUpdate(); // Execute the insert command
            JOptionPane.showMessageDialog(frame, "Feedback added successfully!"); // Success message
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding feedback."); // Error message
        }
    }

    public static void main(String[] args) {
        new FeedbackManagement(); // Run the Feedback Management module
    }
}
