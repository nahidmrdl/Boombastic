import gui.GameGUI;
import util.ResourceCollection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Main File
public class Main {
    public static void main(String[] args) {
        ResourceCollection.readFiles();
        GameGUI game = new GameGUI();
    }
}