import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class gameLoop { // Functions to run the game
    public static int gameState = 1;
    public static boolean turnToggle = true;

    public static void onBack() throws IOException {
        JToggleButton button;
        try {
            button = (JToggleButton) drawBoard.pressHistory.get(drawBoard.pressHistory.size()-2);
        } catch (Exception e){
            System.out.println(e);
            return; // If at least two buttons have been pressed
        }
        // Store button to be removed
        JToggleButton removedButton = (JToggleButton) drawBoard.pressHistory.get(drawBoard.pressHistory.size()-1);
        drawBoard.deleteHistory.add(removedButton);
        // Removes button from arrayList
        drawBoard.pressHistory.remove(drawBoard.pressHistory.size()-1);

        // Removes icon and re-enables button
        Image icon = ImageIO.read(new File("src/defaultButton.png"));
        removedButton.setIcon(new ImageIcon(icon));
        removedButton.setEnabled(true);

    }

    public static void onForward() throws IOException {
        if (drawBoard.deleteHistory.size() > 0) {
            JToggleButton redrawnButton = (JToggleButton) drawBoard.deleteHistory.get(drawBoard.deleteHistory.size() - 1);
            if (turnToggle){
                Image icon = ImageIO.read(new File("src/Xtile.png"));
                redrawnButton.setIcon(new ImageIcon(icon));
            } else {
                Image icon = ImageIO.read(new File("src/Ytile.png"));
                redrawnButton.setIcon(new ImageIcon(icon));
            }
            drawBoard.deleteHistory.remove(redrawnButton);
            drawBoard.pressHistory.add(redrawnButton);
            redrawnButton.setEnabled(false);
        } else {
            return;
        }

    }

    public static void onTurn(JToggleButton e) throws IOException {

        if (drawBoard.pressHistory.size() % 2 != 0){ // If pressHistory is even, it must be player 2's turn
            turnToggle = true;
        } else {
            turnToggle = false;
        }
        Image XIcon = ImageIO.read(new File("src/Xtile.png"));
        Image YIcon = ImageIO.read(new File("src/Ytile.png"));

        if (turnToggle){ // Changes button icon based on who's turn it is
            e.setIcon(new ImageIcon(XIcon));
            e.setDisabledIcon(new ImageIcon(XIcon));
        } else {
            e.setIcon(new ImageIcon(YIcon));
            e.setDisabledIcon(new ImageIcon(YIcon));
        }

        e.setEnabled(false); // Disables button so can't be pressed again
    }

    public static void onRestart() throws IOException {

        for (Object object : drawBoard.pressHistory){
            JToggleButton button = (JToggleButton) object;

            Image icon = ImageIO.read(new File("src/defaultButton.png"));
            button.setIcon(new ImageIcon(icon));
            button.setEnabled(true);
        }
        drawBoard.pressHistory.clear();
        turnToggle = true;
    }

    public static boolean checkSmallWin(){
        boolean isWin = false;

        return isWin;
    }
}
