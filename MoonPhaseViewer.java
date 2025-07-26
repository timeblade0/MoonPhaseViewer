package MoonPhaseViewer;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MoonPhaseViewer extends JFrame implements ActionListener {
    // Array to store image paths and moon phase names
    private final String[] moonPhaseImages = {
        "images/new_moon.png",
        "images/waxing_crescent.png",
        "images/first_quarter.png",
        "images/waxing_gibbous.png",
        "images/full_moon.png",
        "images/waning_gibbous.png",
        "images/third_quarter.png",
        "images/waning_crescent.png"
    };
    // Array to store Moon Phase Nameshase Names
    private final String[] moonPhaseNames = {
        "New Moon",
        "Waxing Crescent",
        "First Quarter",
        "Waxing Gibbous",
        "Full Moon",
        "Waning Gibbous",
        "Third Quarter",
        "Waning Crescent"
    };

    // initialize variables for buttons
    private final JLabel imageLabel;
    private final JLabel phaseLabel;
    private final JButton leftButton;
    private final JButton rightButton;
    private final JButton resetButton;
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

        // add components to frame. center aligned image and name
        phaseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
        add(phaseLabel, BorderLayout.NORTH);

        //add buttons to bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leftButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(rightButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load initial image and set phase label
        updateImageAndText();

        // action listeners - event handles for when buttons are pressed
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        resetButton.addActionListener(this);

        //clean exit when exit button is pressed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    //step event
    private void updateImageAndText() {
        try {
            Image img = ImageIO.read(new File(moonPhaseImages[currentPhase]));
            imageLabel.setIcon(new ImageIcon(img));
            phaseLabel.setText(moonPhaseNames[currentPhase]);
        }
        //error catching
        catch (IOException e) {}
    }

    //event for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // left button clicked
        if (e.getSource() == leftButton) {
            currentPhase = (currentPhase - 1 + moonPhaseImages.length) % moonPhaseImages.length;
        } 
        // right button clicked
        else if (e.getSource() == rightButton) {
            currentPhase = (currentPhase + 1) % moonPhaseImages.length;
        }
        // reset button clicked
        if (e.getSource() == resetButton) {
            currentPhase = 0;
            updateImageAndText();
        }
        updateImageAndText();
    }

    //main event
    public static void main(String[] args) {
        new MoonPhaseViewer();  //single function that runs the whole application
    }
}
