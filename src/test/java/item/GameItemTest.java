package item;

import cell.Cell;
import entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameItemTest {

    @Mock
    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsBlown() {
        Image image = mock(Image.class);
        GameItem gameItem = new MockGameItem(image);

        assertFalse(gameItem.isBlown());
    }

    @Test
    void testSetBlown() {
        Image image = mock(Image.class);
        GameItem gameItem = new MockGameItem(image);

        gameItem.setBlown(true);

        assertTrue(gameItem.isBlown());
    }

    @Test
    void testSetAndGetImage() {
        Image image = mock(Image.class);
        GameItem gameItem = new MockGameItem(image);

        gameItem.setImage(image);

        assertEquals(image, gameItem.getImage());
    }

    @Test
    void testSetAndGetFinishTime() {
        Image image = mock(Image.class);
        GameItem gameItem = new MockGameItem(image);
        long finishTime = System.currentTimeMillis() + 1000;

        gameItem.setFinishTime(finishTime);

        assertEquals(finishTime, gameItem.getFinishTime());
    }

    @Test
    void testSetAndGetCell() {
        Image image = mock(Image.class);
        GameItem gameItem = new MockGameItem(image);
        Cell cell = mock(Cell.class);

        gameItem.setCell(cell);

        assertEquals(cell, gameItem.getCell());
    }

    @Test
    void testSetAndGetOwner() {
        Image image = mock(Image.class);
        GameItem gameItem = new MockGameItem(image);

        gameItem.setOwner(player);

        assertEquals(player, gameItem.getOwner());
    }

    // MockGameItem class to facilitate testing
    private static class MockGameItem extends GameItem {
        public MockGameItem(Image image) {
            super(image);
        }
    }
}
