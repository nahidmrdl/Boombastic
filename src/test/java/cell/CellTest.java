package cell;

import entity.Entity;
import item.GameItem;
import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CellTest {
    private Cell cell;
    private GameMap gameMap;
    private Image image;
    private GameItem gameItem;

    @BeforeEach
    void setUp() {
        gameMap = mock(GameMap.class);
        cell = new Cell(1, 1, gameMap);
        image = mock(Image.class);
        gameItem = mock(GameItem.class);
    }

    @Test
    void testAddItem() {
        cell.addItem(gameItem);
        assertEquals(1, cell.getItems().size());
        assertEquals(gameItem, cell.getItems().get(0));
    }

    @Test
    void testSetAndGetMap() {
        GameMap newMap = mock(GameMap.class);
        cell.setMap(newMap);
        assertEquals(newMap, cell.getMap());
    }

    @Test
    void testSetAndGetForegroundImage() {
        cell.setForegroundImage(image);
        assertEquals(image, cell.getForegroundImage());
    }

    @Test
    void testGetX() {
        assertEquals(1, cell.getX());
    }

    @Test
    void testGetY() {
        assertEquals(1, cell.getY());
    }

    @Test
    void testAddAndGetVisitor() {
        Entity visitor = mock(Entity.class);
        cell.addVisitor(visitor);
        cell.addVisitor(visitor);
        cell.addVisitor(visitor);
        assertEquals(3, cell.getVisitors().size());
        assertEquals(visitor, cell.getVisitors().get(0));
    }



}
