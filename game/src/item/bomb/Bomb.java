package item.bomb;

import item.GameItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Bomb extends GameItem {

    private Timer timer;
    private int state = 0;



    public Bomb() throws IOException {
        super(ImageIO.read(new File("src\\assets\\icons\\bombfirststate.png")));

        this.setFinishTime(System.currentTimeMillis() + 3000);

        timer = new Timer(500, e -> {
            state++; // Increment the state to transition through the bomb's lifecycle
            switch (state) {
                case 1:
                    // Transition to the second state
                    changeImage("src/assets/icons/bombsecondstate.png");
                    break;
                case 2:
                    // Transition to the third state
                    changeImage("src/assets/icons/bombthirdstate.png");
                    break;
                case 3:
                    // Trigger the blast
                    changeImage("src/assets/icons/blast.png");
                    // After the blast, perform any necessary cleanup
                    break;
                case 4:
                    // Final state - stop the timer and potentially remove the bomb from the game
                    timer.stop();
                    break;
            }
        });
        // Ensure the timer only runs once
        timer.setRepeats(true);
        // Start the timer
        timer.start();
    }

    private void changeImage(String imagePath) {
        try {
            this.setImage(ImageIO.read(new File(imagePath)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            // Consider more robust error handling or fallback behavior
        }
    }
}
