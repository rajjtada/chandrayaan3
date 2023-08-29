package com.chandrayaan3.Chandrayaan3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chandrayaan3Application {



	public static void main(String[] args) {
		SpringApplication.run(Chandrayaan3Application.class, args);
	}


	public int[] final_position(int[] startingPosition, char[] commands, char initialDirection) {
		char initialDirection_compass;
		char initialDirection_galactic = '\0';

		if(initialDirection == 'U' || initialDirection == 'D'){
			return startingPosition;
		}
		else{
			initialDirection_compass = initialDirection;
		}
		if(commands.length == 0){
			return startingPosition;
		}
		else{
			for(char c : commands){
				if(c == 'f'){
					if(initialDirection_galactic != '\0'){
						moveForward(startingPosition, initialDirection_galactic);
					}
					else{
						moveForward(startingPosition, initialDirection_compass);
					}
				}
				else if (c == 'b') {
					if(initialDirection_galactic != '\0'){
						moveBackward(startingPosition, initialDirection_galactic);
					}
					else{
						moveBackward(startingPosition, initialDirection_compass);
					}
				}
				else if (c == 'l') {
					if(initialDirection_galactic != '\0'){
						initialDirection_compass = rotateLeft(initialDirection_compass);
						initialDirection_galactic = '\0';
					}
					else{
						initialDirection_compass = rotateLeft(initialDirection_compass);
					}
				}
				else if (c == 'r') {
					if(initialDirection_galactic != '\0'){
						initialDirection_compass = rotateRight(initialDirection_compass);
						initialDirection_galactic = '\0';
					}
					else{
						initialDirection_compass = rotateRight(initialDirection_compass);
					}
				}
				else if (c == 'u') {
					initialDirection_galactic = 'U';
				}
				else if (c == 'd') {
					initialDirection_galactic = 'D';
				}
			}
		}
        return startingPosition;
    }

	private char rotateRight(char initialDirectionCompass) {
		switch (initialDirectionCompass){
			case 'N' -> {
				return 'E';
			}
			case 'S' -> {
				return 'W';
			}
			case 'E' -> {
				return 'S';
			}
			case 'W' -> {
				return 'N';
			}
		}
		return initialDirectionCompass;
	}

	private char rotateLeft(char initialDirectionCompass) {
		switch (initialDirectionCompass){
			case 'N' -> {
				return 'W';
			}
			case 'S' -> {
				return 'E';
			}
			case 'E' -> {
				return 'N';
			}
			case 'W' -> {
				return 'S';
			}
		}
		return initialDirectionCompass;
    }

	private void moveBackward(int[] startingPosition, char initialDirection) {
		switch (initialDirection) {
			case 'N' -> startingPosition[1] -= 1;
			case 'S' -> startingPosition[1] += 1;
			case 'E' -> startingPosition[0] -= 1;
			case 'W' -> startingPosition[0] += 1;
			case 'U' -> startingPosition[2] -= 1;
			case 'D' -> startingPosition[2] += 1;
		}
	}

	private static void moveForward(int[] startingPosition, char initialDirection) {
        switch (initialDirection) {
            case 'N' -> startingPosition[1] += 1;
            case 'S' -> startingPosition[1] -= 1;
            case 'E' -> startingPosition[0] += 1;
            case 'W' -> startingPosition[0] -= 1;
            case 'U' -> startingPosition[2] += 1;
            case 'D' -> startingPosition[2] -= 1;
        }

	}
}
