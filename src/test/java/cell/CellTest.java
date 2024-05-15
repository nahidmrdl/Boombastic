package cell;

import cell.Cell;
import map.GameMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import entity.Entity;
import item.GameItem;

class CellTest {
    private Cell cell;
    private GameMap map;
    private Entity entity;
    private GameItem item;

    @BeforeEach
    void setup() {
        map = mock(GameMap.class);
        entity = mock(Entity.class);
        item = mock(GameItem.class);
        cell = new Cell(0, 0, map);
    }

    @Test
    void addItemToCell() {
        cell.addItem(item);
        assertFalse(cell.getItems().isEmpty());
        assertEquals(item, cell.getItems().get(0));
    }

    @Test
    void addVisitorToCell() {
        cell.addVisitor(entity);
        assertFalse(cell.getVisitors().isEmpty());
        assertEquals(entity, cell.getVisitors().get(0));
    }
}