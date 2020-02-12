package com.lifegame.lifegame.services;

public class ApplyAllRulesTask implements Runnable{
  private byte map[][];
  private volatile byte resultMap[][];
  private int x;

  public ApplyAllRulesTask(byte[][] map, byte[][] resultMap, int x) {
    this.map = map;
    this.resultMap = resultMap;
    this.x = x;
  }

  @Override
  public void run() {
    for(int y =0; y < map[0].length; y++){
      int numOfNeighbours = checkNumberOfNeighbours(map, y, x);
      applyRuleUnderPopulation(resultMap,x ,y , numOfNeighbours);
      applyRuleOverPopulation(resultMap, x, y, numOfNeighbours);
      applyRuleProduction(resultMap, x, y, numOfNeighbours);
    }
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
