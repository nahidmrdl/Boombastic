package cell.box;

import entity.player.Player;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BoxCellTest {
    private BoxCell boxCell;
    private GameMap gameMap;

    @BeforeEach
    void setUp() {
        //mock gamemap
        gameMap = mock(GameMap.class);
        boxCell = new BoxCell(0, 0, gameMap);
    }

    @Test
    void testConstructor() {
        assertNotNull(boxCell);
        assertNull(boxCell.getOwner());
    }

    @Test
    void testSetOwnerAndGetOwner() {
        Player player = new Player(0, 0, gameMap, "Player", 1, null, null);
        boxCell.setOwner(player);
        assertEquals(player, boxCell.getOwner());
    }
}
