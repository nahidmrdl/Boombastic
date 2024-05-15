package cell.box;

import cell.box.BoxCell;
import entity.player.Player;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class BoxCellTest {
    private BoxCell cell;
    private GameMap map;
    private Player owner;

    @BeforeEach
    void setup() {
        map = mock(GameMap.class);
        owner = mock(Player.class);
        cell = new BoxCell(0, 0, map);
    }

    @Test
    void setAndGetOwner() {
        assertNull(cell.getOwner()); // Ensure no owner initially
        cell.setOwner(owner);
        assertEquals(owner, cell.getOwner());
    }
}