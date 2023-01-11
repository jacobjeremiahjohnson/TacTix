import javax.imageio.ImageIO;
import javax.swing.*; // Frame, Panel, UI Elements
import java.awt.*; // Graphics and Graphics 2D
import java.awt.event.*; // Action Event Listener
import java.io.File;
import java.io.IOException;
import java.util.*; // ArrayList

public class drawBoard extends JPanel{ // Creating GUI

    public static ArrayList<Object> pressHistory = new ArrayList<Object>();
    public static ArrayList<Object> deleteHistory = new ArrayList<Object>();

    protected void paintComponent(Graphics g) { // Draw static UI elements
        Graphics2D d = (Graphics2D) g;
        d.setStroke(new BasicStroke(3));
        d.drawRect(50, 50, 450, 450); // Draw outside border and big lines
        d.drawLine(200, 50, 200, 500);
        d.drawLine(350, 50, 350, 500);
        d.drawLine(50, 200, 500, 200);
        d.drawLine(50, 350, 500, 350);
        d.setStroke(new BasicStroke(1));
        for(int i = 0; i < 9; i++){
            d.drawLine(50 + 50*i, 50, 50 + 50*i, 500); // Draw small lines
            d.drawLine(50, 50 + 50*i, 500, 50 + 50*i);
        }
    }

    public static void createAndShowGUI() throws IOException { // Draw interactive elements
        drawBoard mainPanel = new drawBoard(); // Create instance of drawBoard

        JFrame f = new JFrame("Tic Tax"); // Create instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close window on X
        f.getContentPane().add(mainPanel);
        //f.add(mainPanel);
        f.setSize(565, 600);
        f.setVisible(true);
        f.setResizable(false);
        f.setLayout(null); // JFrame formatting

        JToggleButton[] buttonArray = new JToggleButton[81]; // Store all squares as buttons
        for (int i = 1; i < 82; i++) { // Loops 81 times, every 9 loops shifts down one row
            buttonArray[i - 1] = new JToggleButton();

            if (81.0 / i < 1.125) { // Row 9
                buttonArray[i - 1].setBounds(51 + 50 * (i - 73), 451, 48, 48);
            } else if (81.0 / i < 1.2857142857142858) { // Row 8
                buttonArray[i - 1].setBounds(51 + 50 * (i - 64), 401, 48, 48);
            } else if (81.0 / i < 1.5) { // Row 7
                buttonArray[i - 1].setBounds(51 + 50 * (i - 55), 351, 48, 48);
            } else if (81.0 / i < 1.8) { // Row 6
                buttonArray[i - 1].setBounds(51 + 50 * (i - 46), 301, 48, 48);
            } else if (81.0 / i < 2.25) { // Row 5
                buttonArray[i - 1].setBounds(51 + 50 * (i - 37), 251, 48, 48);
            } else if (81.0 / i < 3) {
                buttonArray[i - 1].setBounds(51 + 50 * (i - 28), 201, 48, 48);
            } else if (81.0 / i < 4.5) {
                buttonArray[i - 1].setBounds(51 + 50 * (i - 19), 151, 48, 48);
            } else if (81.0 / i < 9) {
                buttonArray[i - 1].setBounds(51 + 50 * (i - 10), 101, 48, 48);
            } else {
                buttonArray[i - 1].setBounds(51 + 50 * (i - 1), 51, 48, 48);
            }
            buttonArray[i - 1].setMargin(new Insets(0, 0, 0, 0));
            buttonArray[i - 1].setFocusPainted(false);
            buttonArray[i - 1].setBorder(null);
            buttonArray[i - 1].setName(String.valueOf(i));

            Image icon = ImageIO.read(new File("src/defaultButton.png"));
            buttonArray[i - 1].setIcon(new ImageIcon(icon));

            buttonArray[i - 1].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { // Runs on click
                    Object source = e.getSource();
                    JToggleButton buttonPressed = (JToggleButton) source; // Cast Source to JToggleButton object
                    pressHistory.add(e.getSource()); // Add button to pressHistory for drawing functions

                    try {
                        gameLoop.onTurn(buttonPressed); // Run gameLoop function
                    } catch (IOException ex) {
                        ex.printStackTrace(); // If image sources are not found
                    }
                }
            });

            f.add(buttonArray[i - 1]); // Probably not useful anymore

        } // Create 81 instances of JToggleButton at correct coordinates

        JButton backwardArrow = new JButton();
        backwardArrow.setBounds(225, 10, 30, 30); // More like setBounds2 agagagag
        backwardArrow.setMargin(new Insets(0, 0, 0, 0));
        backwardArrow.setBorder(null);
        backwardArrow.setName("backwardArrow");
        backwardArrow.setFocusPainted(false);
        Image bAIcon = ImageIO.read(new File("src/backwardArrow.png"));
        backwardArrow.setIcon(new ImageIcon(bAIcon));
        f.add(backwardArrow);

        backwardArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    gameLoop.onBack();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton forwardArrow = new JButton();
        forwardArrow.setBounds(295, 10, 30, 30); // More like setBounds2 agagagag
        forwardArrow.setMargin(new Insets(0, 0, 0, 0));
        forwardArrow.setBorder(null);
        forwardArrow.setName("forwardArrow");
        forwardArrow.setFocusPainted(false);
        Image fAIcon = ImageIO.read(new File("src/forwardArrow.png"));
        forwardArrow.setIcon(new ImageIcon(fAIcon));
        f.add(forwardArrow);

        forwardArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    gameLoop.onForward();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton restartGame = new JButton();
        restartGame.setBounds(250, 505, 50, 50);
        restartGame.setMargin(new Insets(0, 0, 0, 0));
        restartGame.setBorder(null);
        restartGame.setName("playAgain");
        restartGame.setFocusPainted(false);
        Image rGIcon = ImageIO.read(new File("src/playAgain.png"));
        restartGame.setIcon(new ImageIcon(rGIcon));
        f.add(restartGame);

        restartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    gameLoop.onRestart();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void draw() { // Draw all elements
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) { // In case images aren't found
                    e.printStackTrace();
                }
            }
        });
    }
}
