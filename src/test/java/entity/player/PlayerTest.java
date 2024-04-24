package entity.player;

import cell.Cell;
import item.bomb.Bomb;
import item.curse.Curse;
import item.powerup.PowerUp;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerTest {

    @Mock
    private GameMap gameMap;

    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        HashMap<String, String> controls = new HashMap<>();
        controls.put("UP", "W");
        controls.put("DOWN", "S");
        controls.put("LEFT", "A");
        controls.put("RIGHT", "D");
        controls.put("BOX", "Q");
        controls.put("BOMB", "E");

        Image image = mock(Image.class);
        player = new Player(0, 0, gameMap, "TestPlayer", 1, controls, image);
    }

    @Test
    void testGetName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    void testGetX() {
        player.setX(12);
        assertEquals(12, player.getX());
    }

    @Test
    void testGetY() {
        player.setY(10);
        assertEquals(10, player.getY());
    }

    @Test
    void testSetX() {
        player.setX(6);
        assertEquals(6, player.getX());
    }

    @Test
    void testSetY() {
        player.setY(5);
        assertEquals(5, player.getY());
    }

}