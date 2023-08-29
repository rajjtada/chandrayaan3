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
	public void checking_forward_command_in_all_directions(){
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
		starting_position = new int[]{0,0,0};
		initial_direction = 'U';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,0,0});

		starting_position = new int[]{0,0,0};
		initial_direction = 'D';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,0,0});

	}

	@Test
	public void checking_backward_command_in_all_directions(){
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
		starting_position = new int[]{0,0,0};
		initial_direction = 'U';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,0,0});

		initial_direction = 'D';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(new int[]{0,0,0});

	}

	@Test
	public void check_spacecraft_rotates_left(){
		chandrayaan3Application = new Chandrayaan3Application();

		//no change in position will occur in this as it only changes the direction
		starting_position = new int[]{0,0,0};
		commands = new char[]{'l'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'E';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'W';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		/* here in 'U' and 'D' direction we encounter problem of remembering the 'N' 'S' 'E' 'W', because if we give command 'l' from 'U' it should
		be 'S', so we have to remember the last direction 'N' 'S' 'E' 'W' for manipulating initial direction */

		//if initial_direction is 'U' or 'D' it returns the same position as is and program terminates there
		initial_direction = 'U';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'D';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);
	}

	@Test
	public void check_spacecraft_rotates_right(){
		chandrayaan3Application = new Chandrayaan3Application();

		//no change in position will occur in this as it only changes the direction
		starting_position = new int[]{0,0,0};
		commands = new char[]{'l'};
		initial_direction = 'N';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'S';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'E';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'W';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		/* here in 'U' and 'D' direction we encounter problem of remembering the 'N' 'S' 'E' 'W', because if we give command 'l' from 'U' it should
		be 'S', so we have to remember the last direction 'N' 'S' 'E' 'W' for manipulating initial direction */

		//if initial_direction is 'U' or 'D' it returns the same position as is and program terminates there
		initial_direction = 'U';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);

		initial_direction = 'D';
		assertThat(chandrayaan3Application.final_position(starting_position,commands,initial_direction)).isEqualTo(starting_position);
	}

}
