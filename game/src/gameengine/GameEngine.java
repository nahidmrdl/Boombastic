package gameengine;
import gui.GameInitialScreenGUI;

public class GameEngine {
    public void startGame() {
        System.out.println("Game started");
        GameInitialScreenGUI initGUI = new GameInitialScreenGUI();
        System.out.println("Round Count: " + initGUI.getRoundCount());
    }
}
