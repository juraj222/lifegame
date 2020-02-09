package com.lifegame.lifegame;

import com.lifegame.lifegame.services.GameLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LifegameApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(LifegameApplication.class, args);
		byte map[][] = {{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 },
						{ 0, 1, 1, 1 },
						{ 1, 1, 1, 0 },
						{ 0, 0, 0, 0 } };
		GameLogic gl = new GameLogic(map);
		while(true){
			map = gl.doStep();
			for(int x = 0; x < map.length; x++ ){
				for(int y =0; y < map[0].length; y++){
					System.out.print(" " + map[x][y]);
				}
				System.out.println();
			}
			System.out.println("----------");
			Thread.sleep(1000);
		}
	}

}
