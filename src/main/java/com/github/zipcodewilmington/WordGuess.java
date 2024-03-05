package com.github.zipcodewilmington;
// Jorris Ekoloko
import java.util.Random;
import java.util.Scanner;

public class WordGuess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // List of words to choose from
        String[] wordList = {"bear", "wolf", "beaver", "elk", "moose"};
        boolean playAgain = true;

        while (playAgain) {
            // Select a random word from the list
            String selectedWord = wordList[random.nextInt(wordList.length)];
            StringBuilder currentGuess = new StringBuilder("_".repeat(selectedWord.length()));
            int tries = 0;
            int triesAllowed = selectedWord.length();

            System.out.println("Let's Play Wordguess version 1.0");

            // Game loop
            while (tries < triesAllowed && !currentGuess.toString().equals(selectedWord)) {
                System.out.println("Current Guesses: ");
                // Display the current guess with spaces between characters
                for (int i = 0; i < currentGuess.length(); i++) {
                    System.out.print(currentGuess.charAt(i) + " ");
                }
                System.out.println(); // New line after displaying the current guesses

                // Display tries left with correct grammar for singular/plural
                System.out.println("You have " + (triesAllowed - tries) + (triesAllowed - tries > 1 ? " tries" : " try") + " left.");
                System.out.print("Enter a single character: ");

                String input = scanner.nextLine();
                if (input.length() == 0) continue; // Skip empty input
                char guess = input.charAt(0);

                if (guess == '-') {
                    System.out.println("Game Over. Thanks for playing!");
                    return;
                }

                boolean correctGuess = false;
                for (int i = 0; i < selectedWord.length(); i++) {
                    if (selectedWord.charAt(i) == guess) {
                        currentGuess.setCharAt(i, guess);
                        correctGuess = true;
                    }
                }

                if (!correctGuess) {
                    tries++;
                }
            }

            // Determine win or loss
            if (currentGuess.toString().equals(selectedWord)) {
                System.out.println("**** ****");
                System.out.println(currentGuess);
                System.out.println("Congratulations, You Won!");
            } else {
                System.out.println(":-( :-( :-(");
                System.out.println(currentGuess);
                System.out.println("You Lost! You ran out of guesses.");
                System.out.println("The correct word was: " + selectedWord);
            }

            // Play again prompt with validation loop
            boolean validResponse = false;
            while (!validResponse) {
                System.out.print("Would you like to play again? (yes/no): ");
                String playAgainResponse = scanner.nextLine().trim();

                if (playAgainResponse.equalsIgnoreCase("yes")) {
                    playAgain = true;
                    validResponse = true;
                } else if (playAgainResponse.equalsIgnoreCase("no")) {
                    playAgain = false;
                    validResponse = true;
                } else {
                    System.out.println("Invalid response. Please answer 'yes' or 'no'.");
                }
            }
        }

        System.out.println("Game Over. Thanks for playing!");
        scanner.close();
    }
}
