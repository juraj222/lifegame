package com.lifegame.lifegame.services;

import java.util.Arrays;

public class GameLogic {
    byte map [][];

    public GameLogic(byte[][] map) {
        this.map = map;
    }

    public byte[][] doStep() {
        byte[][] resultMap = Arrays.stream(map)
                .map((byte[] row) -> row.clone())
                .toArray((int length) -> new byte[length][]);

        for(int x = 0; x < map.length; x++ ){
           for(int y =0; y < map[0].length; y++){
               int numOfNeighbours = checkNumberOfNeighbours(map, y, x);
               applyRuleOne(resultMap,x ,y , numOfNeighbours);
           }
        }
        return resultMap;
    }

    private void applyRuleOne(byte[][] resultMap, int x, int y, int numOfNei) {
        if(numOfNei < 2 && resultMap[x][y] != 0) {
            resultMap[x][y] = 0;
        }
    }

    public int checkNumberOfNeighbours(byte[][] map, int y, int x) {
        int neighbours = 0;
        if(x-1 > 0 && map[x-1][y] == 1){
            neighbours++;
        }
        if(x+1 < map.length && map[x+1][y] == 1){
            neighbours++;
        }
        if(y-1 > 0 && map[x][y-1] == 1){
            neighbours++;
        }
        if(y+1 < map[0].length && map[x][y+1] == 1){
            neighbours++;
        }

        //corners
        if((x-1 > 0 && y-1 > 0) && map[x-1][y-1] == 1){
            neighbours++;
        }
        if((x+1 < map.length && y+1 < map[0].length) && map[x+1][y+1] == 1){
            neighbours++;
        }
        if((y-1 > 0 && x+1 < map.length) && map[x+1][y-1] == 1){
            neighbours++;
        }
        if((x-1 > 0 && y+1 < map[0].length) && map[x-1][y+1] == 1){
            neighbours++;
        }
        return neighbours;
    }
}
