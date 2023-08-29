package com.chandrayaan3.Chandrayaan3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Chandrayaan3ApplicationTests {
	@Autowired
    private Chandrayaan3Application chandrayaan3Application;

	int[] starting_position;
	char[] commands;
	char initial_direction;

	@Test
	public void returns_same_coordinates_for_empty_commands(){
		chandrayaan3Application = new Chandrayaan3Application();

		starting_position = new int[]{0, 1, 1};
		commands = new char[]{};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);
	}

	@Test
	public void checking_forward_command(){
		chandrayaan3Application = new Chandrayaan3Application();


	}
}
