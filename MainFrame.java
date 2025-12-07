package coziahr;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Simple Swing GUI with a button to open a file using JFileChooser,
 * and a text area to display results.
 */
public class MainFrame extends JFrame {
    private final JButton chooseButton = new JButton("Select input file...");
    private final JTextArea outputArea = new JTextArea(20, 60);

    public MainFrame() {
        super("Mean & Standard Deviation Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(chooseButton);
        add(top, BorderLayout.NORTH);

        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        chooseButton.addActionListener(this::onChooseFile);

        pack();
        setLocationRelativeTo(null); // center
    }

    private void onChooseFile(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose input text file (one number per line)");
        chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt", "csv", "dat"));
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            processFile(f);
        }
    }

    private void processFile(File file) {
        outputArea.setText("");
        try {
            FileParser.ParseResult pr = FileParser.parseFile(file);
            outputArea.append("File: " + file.getAbsolutePath() + "\n");
            outputArea.append("Valid numeric lines: " + pr.validLines + "\n");
            outputArea.append("Empty lines: " + pr.emptyLinesCount + "\n");
            outputArea.append("Invalid lines: " + pr.invalidLinesCount + "\n");
            if (!pr.invalidLineContents.isEmpty()) {
                outputArea.append("Invalid line details:\n");
                for (String s : pr.invalidLineContents) outputArea.append("  " + s + "\n");
            }

            if (pr.list.isEmpty()) {
                outputArea.append("\nNo valid numeric input found. Cannot compute statistics.\n");
                return;
            }

            StatsCalculator.StatsResult stats = StatsCalculator.computeFromLinkedList(pr.list);
            outputArea.append("\nResults:\n");
            outputArea.append(String.format("Count (n) = %d\n", stats.count));
            outputArea.append(String.format("Mean = %.6f\n", stats.mean));
            if (stats.count > 1) {
                outputArea.append(String.format("Sample standard deviation (n-1) = %.6f\n", stats.sampleStdDev));
            } else {
                outputArea.append("Sample standard deviation is undefined for n < 2\n");
            }

        } catch (Exception ex) {
            outputArea.append("Error reading file: " + ex.getMessage() + "\n");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame f = new MainFrame();
            f.setVisible(true);
        });
    }
}