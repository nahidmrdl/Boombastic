import gui.GameGUI;
import util.ResourceCollection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Main File
public class Main {
    public static void main(String[] args) {
        ResourceCollection.readFiles();
        System.out.print("Bombastic game\n");
        System.out.print("Welcome to the game\n");
        GameGUI game = new GameGUI();
    }
}