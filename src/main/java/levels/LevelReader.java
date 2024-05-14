package levels;


import cell.Cell;
import cell.box.BoxCell;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Reads a level from a file and creates a 2D array of cells.
 */
public class LevelReader {

    /**
     * Reads a level from a file and creates a 2D array of cells.
     * @param filePath path to the file
     * @return 2D array of cells
     * @throws IOException if the file is not found
     */
    public static Cell[][] readLevelFromFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        StringBuilder mapBuilder = new StringBuilder();
        int rowCount = 0;
        int maxColCount = 0;

        while ((line = bufferedReader.readLine()) != null) {
            mapBuilder.append(line).append("\n");
            maxColCount = Math.max(maxColCount, line.length());
            rowCount++;
        }

        bufferedReader.close();

        String[] mapRows = mapBuilder.toString().split("\n");
        Cell[][] map = new Cell[rowCount][maxColCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < maxColCount; j++) {
                if (j < mapRows[i].length()) {
                    if(mapRows[i].charAt(j) == 'X') {
                        map[i][j] = new BoxCell(i,j, null);
                    }
                    if(mapRows[i].charAt(j) == '.') {
                        map[i][j] = new NormalCell(i,j, null);
                    }
                    if(mapRows[i].charAt(j) == '#') {
                        map[i][j] = new WallCell(i,j, null);
                    }

                    if(mapRows[i].charAt(j) == 'S') {
                        map[i][j] = new NormalCell(i,j, null);
                        if(map[i][j] instanceof NormalCell) {
                            ((NormalCell) map[i][j]).setStartingPoint(true);
                        }
                    }


                } else {
                    map[i][j] = new Cell(i,j, null);
                }
            }
        }

        return map;
    }
}
