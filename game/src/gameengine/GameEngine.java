package gameengine;
import gui.GameInitialScreenGUI;

public class GameEngine {
    public void startGame(GameInitialScreenGUI initGUI) {
        System.out.println("Game started");
        System.out.println("Round Count: " + initGUI.getRoundCount());
        System.out.println("Map index: " + initGUI.getMapIndex());
    }
}
