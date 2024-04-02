package item.bomb;

import cell.Cell;
import cell.box.BoxCell;
import cell.normalCell.NormalCell;
import item.GameItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

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

                    // change image in range

                    Cell[][] gameMap =  this.getCell().getMap().getMap();

                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (i == 2 && j == 2) {
                                continue;
                            }
                            if (i == 2 || j == 2) {
                                if (gameMap[this.getCell().getX() + i - 2][this.getCell().getY() + j - 2] != null) {
                                    try {
                                        if(gameMap[this.getCell().getX() + i - 2][this.getCell().getY() + j - 2] instanceof BoxCell) {
                                            gameMap[this.getCell().getX() + i - 2][this.getCell().getY() + j - 2] = new NormalCell(this.getCell().getX() + i - 2, this.getCell().getY() + j - 2, ".");
                                            gameMap[this.getCell().getX() + i - 2][this.getCell().getY() + j - 2].setMap(this.getCell().getMap());
                                        }
                                        gameMap[this.getCell().getX() + i - 2][this.getCell().getY() + j - 2].setForegroundImage(ImageIO.read(new File("src\\assets\\icons\\blast.png")));
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            }
                        }
                    }

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
