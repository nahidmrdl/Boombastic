package cell.normalCell;

import cell.normalCell.NormalCell;
import entity.player.Player;
import item.curse.Curse;
import item.curse.lowspeed.LowSpeed;
import item.powerup.PowerUp;
import item.powerup.increasednumberffbombs.IncreasedNumberOfBombs;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NormalCellTest {

    @Mock
    private GameMap map;
    @Mock
    private Player player;
    private NormalCell cell;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cell = new NormalCell(0, 0, map);
        when(player.getX()).thenReturn(0);
        when(player.getY()).thenReturn(0);
        cell.addVisitor(player);
    }



    @Test
    void testCollectItemsByPlayer() {
        PowerUp powerUp = new IncreasedNumberOfBombs();
        cell.addItem(powerUp);
        cell.collectItems();

        verify(player, times(1)).addPowerUp(powerUp);
        assertTrue(cell.getItems().isEmpty()); // Items should be collected
    }

    @Test
    void testCollectCurseByPlayer() {
        Curse curse = new LowSpeed();
        cell.addItem(curse);
        cell.collectItems();

        verify(player, times(1)).addCurse(curse);
        assertTrue(cell.getItems().isEmpty()); // Items should be collected
    }

    @Test
    void testStartingPoint() {
        assertFalse(cell.isStartingPoint());
        cell.setStartingPoint(true);
        assertTrue(cell.isStartingPoint());
    }
}
