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
	public void checks_forward_command_in_all_directions(){
		chandrayaan3Application = new Chandrayaan3Application();

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f'};

		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,1,0});

		starting_position = new int[]{0,0,0};
		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,-1,0});

		starting_position = new int[]{0,0,0};
		initial_direction = 'E';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{1,0,0});

		starting_position = new int[]{0,0,0};
		initial_direction = 'W';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{-1,0,0});

		/* initial_direction cannot be 'U' and 'D', as we don't if it rotates left we don't know in which direction to rotate,
		so it returns the starting position as is */

		/* So, here I checked the forward command in 'U' and 'D', using below approach first forward in 'N' direction and changed
		direction to 'U' or 'D' and then forward */
		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','u','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,1,1});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','d','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,1,-1});

	}

	@Test
	public void checks_backward_command_in_all_directions(){
		chandrayaan3Application = new Chandrayaan3Application();

		starting_position = new int[]{0,0,0};
		commands = new char[]{'b'};

		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,-1,0});

		starting_position = new int[]{0,0,0};
		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,1,0});

		starting_position = new int[]{0,0,0};
		initial_direction = 'E';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{-1,0,0});

		starting_position = new int[]{0,0,0};
		initial_direction = 'W';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{1,0,0});

		/* initial_direction cannot be 'U' and 'D', as we don't if it rotates left we don't know in which direction to rotate,
		so it returns the starting position as is */

		/* So, here I checked the backward command in 'U' and 'D', using below approach first backward in 'N' direction and changed
		direction to 'U' or 'D' and then backward */
		starting_position = new int[]{0,0,0};
		commands = new char[]{'b','u','b'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,-1,-1});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'b','d','b'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,-1,1});

	}

	@Test
	public void checks_spacecraft_rotates_left_up_and_down(){
		chandrayaan3Application = new Chandrayaan3Application();

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','l','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {-1,1,0});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','l','f'};
		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {1,-1,0});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','l','f'};
		initial_direction = 'E';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {1,1,0});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','l','f'};
		initial_direction = 'W';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {-1,-1,0});

		/* here in 'U' and 'D' direction we encounter problem of remembering the 'N' 'S' 'E' 'W', because if we give command 'l' from 'U' it should
		be 'S', so we have to remember the last direction 'N' 'S' 'E' 'W' for manipulating initial direction */

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','u','f','l','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {-1,1,1});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','d','f','l','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {-1,1,-1});
	}

	@Test
	public void checks_spacecraft_rotates_right_up_and_down(){
		chandrayaan3Application = new Chandrayaan3Application();

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','r','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {1,1,0});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','r','f'};
		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {-1,-1,0});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','r','f'};
		initial_direction = 'E';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {1,-1,0});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','r','f'};
		initial_direction = 'W';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {-1,1,0});

		/* here in 'U' and 'D' direction we encounter problem of remembering the 'N' 'S' 'E' 'W', because if we give command 'l' from 'U' it should
		be 'S', so we have to remember the last direction 'N' 'S' 'E' 'W' for manipulating initial direction */

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','u','f','r','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {1,1,1});

		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','d','f','r','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[] {1,1,-1});
	}

	@Test
	public void checks_incubyte_example_commands_and_some_random_commands(){
		chandrayaan3Application = new Chandrayaan3Application();

		//test case 1
		starting_position = new int[]{0,0,0};
		commands = new char[]{'f', 'r', 'u', 'b', 'l'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,1,-1});

		//test case 2
		starting_position = new int[]{0,0,0};
		commands = new char[]{'f','f','f','f'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,4,0});

		//test case 3
		starting_position = new int[]{0,0,0};
		commands = new char[]{'f', 'r', 'f', 'l', 'b', 'r','u', 'b', 'l', 'u', 'd'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{1,0,-1});

		//test case 4
		starting_position = new int[]{0,0,0};
		commands = new char[]{'f', 'r', 'f', 'l', 'b', 'r','u', 'b', 'l', 'u', 'd'};
		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{-1,0,-1});

		//test case 5
		starting_position = new int[]{3,-2,1};
		commands = new char[]{'b', 'l', 'u', 'r', 'd', 'f'};
		initial_direction = 'E';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{2,-2,0});

		//test case 6
		starting_position = new int[]{1, -1, 0};
		commands = new char[]{'f', 'r', 'f', 'r', 'f', 'l', 'f', 'l'};
		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{-1,-1,0});
	}

}
