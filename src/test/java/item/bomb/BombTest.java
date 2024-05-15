package item.bomb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cell.Cell;
import entity.player.Player;
import item.bomb.Bomb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ResourceCollection;

import javax.swing.Timer;
import java.awt.Image;

class BombTest {
    private Bomb bomb;
    private Cell cell;
    private Player player;

    @BeforeEach
    void setUp() {
        cell = mock(Cell.class);
        player = mock(Player.class);
        bomb = new Bomb(false);
        bomb.setCell(cell);
        bomb.setOwner(player);
    }

    @Test
    void isDetonator() {
        assertFalse(bomb.isDetonator(), "Bomb should not be initialized as a detonator");
        Bomb detonatorBomb = new Bomb(true);
        assertTrue(detonatorBomb.isDetonator(), "Bomb should be initialized as a detonator");
    }

}