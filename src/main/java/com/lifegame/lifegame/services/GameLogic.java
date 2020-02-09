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
               applyRuleUnderPopulation(resultMap,x ,y , numOfNeighbours);
               applyRuleOverPopulation(resultMap, x, y, numOfNeighbours);
               applyRuleProduction(resultMap, x, y, numOfNeighbours);
           }
        }
        map = resultMap;
        return resultMap;
    }

    private void applyRuleUnderPopulation(byte[][] resultMap, int x, int y, int numOfNei) {
        if(numOfNei < 2 && resultMap[x][y] != 0) {
            resultMap[x][y] = 0;
        }
    }

    private void applyRuleOverPopulation(byte[][] resultMap, int x, int y, int numOfNei) {
        if(numOfNei > 3 && resultMap[x][y] != 0) {
            resultMap[x][y] = 0;
        }
    }

    private void applyRuleProduction(byte[][] resultMap, int x, int y, int numOfNei) {
        if(numOfNei == 3 && resultMap[x][y] != 1) {
            resultMap[x][y] = 1;
        }
    }

    public int checkNumberOfNeighbours(byte[][] map, int y, int x) {
        int neighbours = 0;
        if(x-1 >= 0 && map[x-1][y] == 1){
            neighbours++;
        }
        if(x+1 < map.length && map[x+1][y] == 1){
            neighbours++;
        }
        if(y-1 >= 0 && map[x][y-1] == 1){
            neighbours++;
        }
        if(y+1 < map[0].length && map[x][y+1] == 1){
            neighbours++;
        }

        //corners
        if((x-1 >= 0 && y-1 >= 0) && map[x-1][y-1] == 1){
            neighbours++;
        }
        if((x+1 < map.length && y+1 < map[0].length) && map[x+1][y+1] == 1){
            neighbours++;
        }
        if((y-1 >= 0 && x+1 < map.length) && map[x+1][y-1] == 1){
            neighbours++;
        }
        if((x-1 >= 0 && y+1 < map[0].length) && map[x-1][y+1] == 1){
            neighbours++;
        }
        return neighbours;
    }
}
