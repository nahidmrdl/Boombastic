package entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import map.GameMap;
import cell.Cell;

class EntityTest {
    private Entity entity;
    private GameMap gameMap;
    private final int x = 5;
    private final int y = 5;

    @BeforeEach
    void setUp() {
        gameMap = mock(GameMap.class);
        Cell cell = mock(Cell.class);
        when(gameMap.getCell(x, y)).thenReturn(cell);
        entity = new Entity(x, y, gameMap) {};  // Anonymous subclass for testing
    }

    @Test
    void testGetCellPositioned() {
        assertEquals(gameMap.getCell(x, y), entity.getCellPositioned());
    }

    @Test
    void testGetImage() {
        assertNull(entity.getImage());
    }

    @Test
    void testGetBaseImage() {
        assertNull(entity.getBaseImage());
    }
}
