package com.lifegame.lifegame.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class GameLogic {
    public GameLogic() {
    }

    public byte[][] doStep(byte[][] map) {
        if(map == null){
            map = new byte[][] {{ 1, 0, 0, 0, 0, 1, 0, 0, 1, 0 },
                                { 0, 0, 1, 0, 0, 1, 0, 0, 1, 0 },
                                { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 },
                                { 1, 1, 1, 1, 0, 1, 0, 0, 1, 0 },
                                { 0, 1, 0, 1, 0, 1, 0, 0, 1, 0 },
                                { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 },
                                { 0, 0, 1, 0, 0, 1, 0, 0, 1, 0 },
                                { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 },
                                { 1, 1, 1, 1, 0, 1, 0, 0, 1, 0 },
                                { 0, 1, 0, 1, 0, 1, 0, 0, 1, 0 }};
        }
        byte[][] resultMap = Arrays.stream(map)
                .map((byte[] row) -> row.clone())
                .toArray((int length) -> new byte[length][]);

        ExecutorService pool = Executors.newFixedThreadPool(4);
        for(int x = 0; x < map.length; x++ ){
            Runnable runnable = new ApplyAllRulesTask(map, resultMap, x);
            pool.execute(runnable);
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(2, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return resultMap;
    }

    public byte[][] createRandomMap(int size) {
        Random rand = new Random();
        byte[][] map = new byte[size][size];
        for(int x = 0; x < map.length; x++ ) {
            for (int y = 0; y < map[0].length; y++) {
                map[x][y] = (byte) rand.nextInt(2);
            }
        }
        return map;
    }

    public List<List<String>> printMap(byte[][] map) {
        List<List<String>> result = new ArrayList<>();
        for(int x = 0; x < map.length; x++ ){
            List<String> inResult = new ArrayList<>();
            for(int y =0; y < map[0].length; y++){
                inResult.add(map[x][y] + "");
            }
            result.add(inResult);
        }
        return result;
    }

//    public List<String> printMap(byte[][] map) {
//        List<String> result = new ArrayList<>();
//        for(int x = 0; x < map.length; x++ ){
//            String text = "";
//            for(int y =0; y < map[0].length; y++){
//                text += " " + map[x][y];
//            }
//            result.add(text);
//        }
//        return result;
//    }
}
