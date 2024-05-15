package item;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cell.Cell;
import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Image;

class GameItemTest {
    private GameItem gameItem;
    private Cell mockCell;
    private Player mockPlayer;
    private Image mockImage;

    @BeforeEach
    void setUp() {
        mockCell = mock(Cell.class);
        mockPlayer = mock(Player.class);
        mockImage = mock(Image.class);


        gameItem = new GameItem(mockImage) {};
    }

    @Test
    void testSetAndGetImage() {
        Image newImage = mock(Image.class);
        gameItem.setImage(newImage);
        assertEquals(newImage, gameItem.getImage(), "The image should be updated to the new image.");
    }

    @Test
    void testSetAndGetCell() {
        gameItem.setCell(mockCell);
        assertSame(mockCell, gameItem.getCell(), "The cell should be the one that was set.");
    }

    @Test
    void testSetAndGetOwner() {
        gameItem.setOwner(mockPlayer);
        assertSame(mockPlayer, gameItem.getOwner(), "The owner should be the one that was set.");
    }

    @Test
    void testSetAndGetFinishTime() {
        long finishTime = System.currentTimeMillis() + 1000;
        gameItem.setFinishTime(finishTime);
        assertEquals(finishTime, gameItem.getFinishTime(), "The finish time should be the one that was set.");
    }

    @Test
    void testBlownState() {
        assertFalse(gameItem.isBlown(), "Initially, isBlown should be false.");
        gameItem.setBlown(true);
        assertTrue(gameItem.isBlown(), "After setting to true, isBlown should return true.");
    }
}
