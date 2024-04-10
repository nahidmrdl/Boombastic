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
        super(ImageIO.read(new File("src/assets/icons/bombfirststate.png")));

        this.setFinishTime(System.currentTimeMillis() + 3000);

        this.invokeDetonateAnimation();
    }

    /**
     * This method is used to invoke the detonate animation of the bomb
     */
    private void invokeDetonateAnimation() {
        timer = new Timer(500, e -> {
            state++;
            switch (state) {
                case 1:
                    changeImage("src/assets/icons/bombsecondstate.png");
                    break;
                case 2:
                    changeImage("src/assets/icons/bombthirdstate.png");
                    break;
                case 3:
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
                    timer.stop();
                    break;
            }
        });

        timer.setRepeats(true);
        timer.start();
    }

    /**
     * This method is used to change the image of the bomb
     * @param imagePath
     */
    private void changeImage(String imagePath) {
        try {
            this.setImage(ImageIO.read(new File(imagePath)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
