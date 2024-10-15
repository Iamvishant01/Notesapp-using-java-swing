import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SimpleNotesApp extends JFrame {
    private JTextArea textArea;
    private JButton newButton, saveButton, viewButton;

    public SimpleNotesApp() {
        // Set up the frame
        setTitle("Simple Notes App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create text area
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create buttons
        JPanel panel = new JPanel();
        newButton = new JButton("New Note");
        saveButton = new JButton("Save Note");
        viewButton = new JButton("View Notes");

        panel.add(newButton);
        panel.add(saveButton);
        panel.add(viewButton);

        add(panel, BorderLayout.SOUTH);

        // Add action listeners
        newButton.addActionListener(e -> newNote());
        saveButton.addActionListener(e -> saveNote());
        viewButton.addActionListener(e -> viewNotes());
    }

    // Clear text area for a new note
    private void newNote() {
        textArea.setText("");
    }

    // Save the current note to a file
    private void saveNote() {
        try {
            // Ask for the file name
            String fileName = JOptionPane.showInputDialog("Enter file name (with .txt):");
            if (fileName == null || fileName.isEmpty()) {
                return; // User canceled
            }

            // Write the text area content to the file
            FileWriter writer = new FileWriter(fileName);
            writer.write(textArea.getText());
            writer.close();

            JOptionPane.showMessageDialog(this, "Note saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving note.");
        }
    }

    // View saved notes in the current directory
    private void viewNotes() {
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (listOfFiles != null && listOfFiles.length > 0) {
            StringBuilder filesList = new StringBuilder("Available Notes:\n");
            for (File file : listOfFiles) {
                filesList.append(file.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(this, filesList.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No notes found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleNotesApp app = new SimpleNotesApp();
            app.setVisible(true);
        });
    }
}
