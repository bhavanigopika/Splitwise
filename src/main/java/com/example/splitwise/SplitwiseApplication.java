package com.example.splitwise;

import com.example.splitwise.Command.CommandExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

	private CommandExecutor commandExecutor;

	public SplitwiseApplication(CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}
	public static void main(String[] args) {

		SpringApplication.run(SplitwiseApplication.class, args);
	}

	//implement the run method

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		//Take input continuously
		while(true){
			String input = sc.next();
			//give input to command executor
			commandExecutor.execute(input);

		}
	}
}
