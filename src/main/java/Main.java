import gui.GameGUI;
import util.ResourceCollection;

public class Main {
    public static void main(String[] args) {
        ResourceCollection.readFiles();
        GameGUI game = new GameGUI();
    }
}