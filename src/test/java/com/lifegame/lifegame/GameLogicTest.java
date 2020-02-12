package com.lifegame.lifegame;

import com.lifegame.lifegame.services.ApplyAllRulesTask;
import com.lifegame.lifegame.services.GameLogic;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class GameLogicTest {
    @Test
    void underPopulationTest(){
        byte map[][] = { { 0, 0, 0, 0 },
                         { 0, 0, 1, 0 },
                         { 0, 0, 0, 0 },
                         { 0, 0, 0, 0 },
                         { 0, 0, 0, 0 } };
        byte mapResult[][] = {  { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 } };
        GameLogic gl = new GameLogic();
        byte[][] mapFromDoStep = gl.doStep(map);
        printMap(mapFromDoStep);
        assertThat(mapFromDoStep).isEqualTo(mapResult);
    }

    @Test
    void underPopulationTest2(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 0, 1 },
                        { 0, 1, 0, 1 } };
        byte mapResult[][] = {  { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 1, 1, 0 },
                                { 1, 1, 0, 0 },
                                { 1, 1, 0, 0 } };
        GameLogic gl = new GameLogic();
        byte[][] mapFromDoStep = gl.doStep(map);
        printMap(mapFromDoStep);
        assertThat(mapFromDoStep).isEqualTo(mapResult);
    }

    @Test
    void overPopulationTest(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 1, 1 },
                        { 0, 1, 0, 1 } };
        byte mapResult[][] = {  { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 1 },
                                { 1, 1, 0, 1 },
                                { 1, 1, 0, 1 } };
        GameLogic gl = new GameLogic();
        byte[][] mapFromDoStep = gl.doStep(map);
        printMap(mapFromDoStep);
        assertThat(mapFromDoStep).isEqualTo(mapResult);
    }

    @Test
    void productionTest(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 1, 1 },
                        { 0, 0, 0, 0 } };
        byte mapResult[][] = {  { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 1, 1, 0 },
                                { 0, 1, 1, 0 },
                                { 0, 1, 1, 0 } };
        GameLogic gl = new GameLogic();
        byte[][] mapFromDoStep = gl.doStep(map);
        printMap(mapFromDoStep);
        assertThat(mapFromDoStep).isEqualTo(mapResult);
    }

    @Test
    void checkNeighboursZero(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 0, 1 },
                        { 0, 1, 0, 1 } };
        ApplyAllRulesTask applyAllRulesTask = new ApplyAllRulesTask(null, null, 0);
        assertThat(applyAllRulesTask.checkNumberOfNeighbours(map,0,0)).isEqualTo(0);
    }

    @Test
    void checkNeighboursZero2(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 0, 1 },
                        { 0, 1, 0, 1 } };
        ApplyAllRulesTask applyAllRulesTask = new ApplyAllRulesTask(null, null, 0);
        assertThat(applyAllRulesTask.checkNumberOfNeighbours(map,2,1)).isEqualTo(0);
    }

    @Test
    void checkNeighboursNonZero(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 0, 1 },
                        { 0, 1, 0, 1 } };
        ApplyAllRulesTask applyAllRulesTask = new ApplyAllRulesTask(null, null, 0);
        assertThat(applyAllRulesTask.checkNumberOfNeighbours(map,2,2)).isEqualTo(3);
    }


    @Test
    void checkNeighboursNonZero2(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 1, 1, 1 },
                        { 0, 1, 0, 1 },
                        { 0, 1, 1, 1 } };
        ApplyAllRulesTask applyAllRulesTask = new ApplyAllRulesTask(null, null, 0);
        assertThat(applyAllRulesTask.checkNumberOfNeighbours(map,2,3)).isEqualTo(8);
    }

    @Test
    void checkNeighboursNonZero3(){
        byte map[][] = {{ 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 0, 0 },
                        { 0, 1, 0, 0 } };
        ApplyAllRulesTask applyAllRulesTask = new ApplyAllRulesTask(null, null, 0);
        assertThat(applyAllRulesTask.checkNumberOfNeighbours(map,1,3)).isEqualTo(2);
    }

    private void printMap(byte[][] map){
        for(int x = 0; x < map.length; x++ ){
            for(int y =0; y < map[0].length; y++){
                System.out.print(" " + map[x][y]);
            }
            System.out.println();
        }
    }
}
