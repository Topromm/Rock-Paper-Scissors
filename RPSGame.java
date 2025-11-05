/*
 CMD Rock-Paper-Scissors Game

 Usage (PowerShell):
 1. Compile:
	javac "c:\\Users\\User\\Downloads\\RPS-Java\\RPS.java"
 2. Run:
	java -cp "c:\\Users\\User\\Downloads\\RPS-Java" RPSGame

 Controls:
 - Type r, p, s (or rock, paper, scissors) to play a round.
 - Type q or quit to exit.
*/

import java.util.Random;
import java.util.Scanner;

class RPSGame {
	private enum Move { ROCK, PAPER, SCISSORS }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random rng = new Random();

		int wins = 0, losses = 0, ties = 0, rounds = 0;

		System.out.println("Let's play Rock-Paper-Scissors!");
		System.out.println("Enter r/p/s (or rock/paper/scissors). Type q to quit.");

		while (true) {
			System.out.print("Your move (r/p/s or q): ");
			String line = scanner.nextLine();
			if (line == null) break;
			line = line.trim().toLowerCase();
			if (line.isEmpty()) {
				System.out.println("Please enter r, p, s, or q.");
				continue;
			}

			if (line.equals("q") || line.equals("quit") || line.equals("exit")) {
				break;
			}

			Move player = parseMove(line);
			if (player == null) {
				System.out.println("Invalid input. Use r/p/s or rock/paper/scissors.");
				continue;
			}

			Move computer = Move.values()[rng.nextInt(3)];
			System.out.println("Computer played: " + computer);

			int outcome = compare(player, computer); // 1 win, 0 tie, -1 loss
			rounds++;
			if (outcome > 0) {
				wins++;
				System.out.println("You win this round!");
			} else if (outcome < 0) {
				losses++;
				System.out.println("You lose this round.");
			} else {
				ties++;
				System.out.println("This round is a tie.");
			}

			System.out.printf("Score — Played: %d  Wins: %d  Losses: %d  Ties: %d%n",
							  rounds, wins, losses, ties);
		}

		System.out.println();
		System.out.println("Thanks for playing!");
		System.out.printf("Final score — Played: %d  Wins: %d  Losses: %d  Ties: %d%n",
						  rounds, wins, losses, ties);

		scanner.close();
	}

	private static Move parseMove(String s) {
		switch (s) {
			case "r": case "rock": return Move.ROCK;
			case "p": case "paper": return Move.PAPER;
			case "s": case "scissors": case "scissor": return Move.SCISSORS;
			default: return null;
		}
	}

	// returns 1 if a beats b, 0 if tie, -1 if a loses to b
	private static int compare(Move a, Move b) {
		if (a == b) return 0;
		if ((a == Move.ROCK && b == Move.SCISSORS)
			|| (a == Move.PAPER && b == Move.ROCK)
			|| (a == Move.SCISSORS && b == Move.PAPER)) {
			return 1;
		}
		return -1;
	}
}

