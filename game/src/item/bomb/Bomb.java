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
