package MoonPhaseViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MoonPhaseViewer extends JFrame implements ActionListener {
    // Array to store image paths and moon phase names
    private String[] moonPhaseImages = {
        "MoonPhaseViewer/images/new_moon.png",
        "MoonPhaseViewer/images/waxing_crescent.png",
        "MoonPhaseViewer/images/first_quarter.png",
        "MoonPhaseViewer/images/waxing_gibbous.png",
        "MoonPhaseViewer/images/full_moon.png",
        "MoonPhaseViewer/images/waning_gibbous.png",
        "MoonPhaseViewer/images/third_quarter.png",
        "MoonPhaseViewer/images/waning_crescent.png"
    };
    private String[] moonPhaseNames = {
        "New Moon",
        "Waxing Crescent",
        "First Quarter",
        "Waxing Gibbous",
        "Full Moon",
        "Waning Gibbous",
        "Third Quarter",
        "Waning Crescent"
    };

    private JLabel imageLabel;
    private JLabel phaseLabel;
    private JButton leftButton;
    private JButton rightButton;
    private JButton resetButton;
    private int currentPhase = 0;

    public MoonPhaseViewer() {
        // Create GUI components
        imageLabel = new JLabel();
        phaseLabel = new JLabel();
        leftButton = new JButton("<");
        rightButton = new JButton(">");
        resetButton = new JButton("Reset");
        
        // set up layout
        setLayout(new BorderLayout());

        // add components to frame
        add(imageLabel, BorderLayout.CENTER);
        add(phaseLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leftButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(rightButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load initial image and set phase label
        updateImageAndText();

        // add action listeners
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        resetButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void updateImageAndText() {
        try {
            Image img = ImageIO.read(new File(moonPhaseImages[currentPhase]));
            imageLabel.setIcon(new ImageIcon(img));
            phaseLabel.setText(moonPhaseNames[currentPhase]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leftButton) {
            currentPhase = (currentPhase - 1 + moonPhaseImages.length) % moonPhaseImages.length;
        } else if (e.getSource() == rightButton) {
            currentPhase = (currentPhase + 1) % moonPhaseImages.length;
        }
        if (e.getSource() == resetButton) {
            currentPhase = 0;
            updateImageAndText();
        }
        updateImageAndText();
    }

    public static void main(String[] args) {
        new MoonPhaseViewer();
    }
}
