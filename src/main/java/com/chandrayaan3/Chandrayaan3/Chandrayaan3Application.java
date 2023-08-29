package com.chandrayaan3.Chandrayaan3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chandrayaan3Application {

	public static void main(String[] args) {
		SpringApplication.run(Chandrayaan3Application.class, args);
	}


	public int[] final_position(int[] startingPosition, char[] commands, char initialDirection) {
		if(commands.length < 0){
			return startingPosition;
		}

        return startingPosition;
    }
}
