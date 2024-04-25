package cell.normalCell;

import cell.Cell;
import entity.player.Player;
import item.GameItem;
import item.curse.Curse;
import item.powerup.PowerUp;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NormalCellTest {
    private NormalCell normalCell;
    private GameMap gameMap;

    @BeforeEach
    void setUp() {
        // Mock GameMap
        gameMap = mock(GameMap.class);

        // Create NormalCell
        normalCell = new NormalCell(0, 0, gameMap);
    }

    @Test
    void testConstructor() {
        assertEquals(normalCell.getX(), 0);
        assertEquals(normalCell.getY(), 0);
        assertEquals(normalCell.getMap(), gameMap);
    }



}
