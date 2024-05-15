package entity.player;

import item.powerup.PowerUp;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PlayerTest {
    @Mock
    private GameMap gameMap;
    @Mock private Player player;
    @Mock private Image image;
    @Mock private HashMap<String, String> controls;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        player = new Player(0, 0, gameMap, "Player1", 1, controls, image);
    }

    @Test
    void testMoveSetsCoordinates() {
        player.setX(5);
        player.setY(5);
        assertEquals(5, player.getX());
        assertEquals(5, player.getY());
    }

    @Test
    void testAddPowerUpIncreasesCount() {
        PowerUp powerUp = mock(PowerUp.class);
        player.addPowerUp(powerUp);
        assertFalse(player.powerUpsItems.isEmpty());
        assertTrue(player.powerUpsItems.contains(powerUp));
    }

    @Test
    void testIsDead() {
        assertFalse(player.isDead());
        player.setDead(true);
        assertTrue(player.isDead());
    }
}
