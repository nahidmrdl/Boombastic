package levels;


import cell.Cell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelReader {
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
                    if(mapRows[i].charAt(j) == 'S'){
                        System.out.println("S");
                        map[i][j] = new Cell(i,j, String.valueOf(mapRows[i].charAt(j)));
                    }
                    map[i][j] = new Cell(i,j, String.valueOf(mapRows[i].charAt(j)));

                } else {
                    map[i][j] = new Cell(i,j, " ");
                }
            }
        }

        return map;
    }
}
