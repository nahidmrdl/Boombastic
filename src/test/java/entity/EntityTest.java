package entity;

import map.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EntityTest {
    private EntityTest1 entity;
    private GameMap gameMap;

    @BeforeEach
    void setUp() {
        gameMap = mock(GameMap.class); // create a mock GameMap object
        entity = new EntityTest1(3, 5, gameMap);
    }

    @Test
    void getCellPositioned() {
        assertEquals(entity.getCellPositioned(), gameMap.getCell(3, 5));
    }

    @Test
    void getImage() {
        // since the image is not initialized in the test, we should expect null
        assertEquals(null, entity.getImage());
    }

    @Test
    void getBaseImage() {
        // since the baseImage is not initialized in the test, we should expect null
        assertEquals(null, entity.getBaseImage());
    }
}


class EntityTest1 extends Entity {
    public EntityTest1(int x, int y, GameMap gameMap) {
        super(x, y, gameMap);
    }
}
