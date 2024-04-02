package levels;


import cell.Cell;
import cell.box.BoxCell;
import cell.normalCell.NormalCell;
import cell.wall.WallCell;
import entity.Entity;
import entity.player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
//                    if(mapRows[i].charAt(j) == 'S') {
//                        System.out.println("S");
//                        map[i][j] = new Cell(i, j, String.valueOf(mapRows[i].charAt(j)));
//                    }
                     //else {map[i][j] = new Cell(i,j, String.valueOf(mapRows[i].charAt(j)));
//
//
                    if(mapRows[i].charAt(j) == 'X') {
                        map[i][j] = new BoxCell(i,j, String.valueOf(mapRows[i].charAt(j)));
                    }
                    if(mapRows[i].charAt(j) == '.') {
                        map[i][j] = new NormalCell(i,j, String.valueOf(mapRows[i].charAt(j)));
                    }
                    if(mapRows[i].charAt(j) == '#') {
                        map[i][j] = new WallCell(i,j, String.valueOf(mapRows[i].charAt(j)));
                    }

                    if(mapRows[i].charAt(j) == 'S') {
                        map[i][j] = new NormalCell(i,j, String.valueOf(mapRows[i].charAt(j)));
                        if(map[i][j] instanceof NormalCell) {
                            ((NormalCell) map[i][j]).setStartingPoint(true);
                            System.out.println("Starting point at " + i + " " + j);
                        }
                    }


                } else {
                    map[i][j] = new Cell(i,j, " ");
                }
            }
        }

        return map;
    }
}
