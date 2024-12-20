package mastermind;

import java.util.Scanner;

public class MastermindGame {
    private static final int CODE_LENGTH = 4;
    private static final String[] VALID_COLORS = {"pink", "green", "red", "gray", "purple", "white"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mastermind!");
        System.out.println("Try to guess the secret color. allowed colors: pink, green, red, gray, purple, white.");
        System.out.print("Enter the number of attempts you would like to have: ");
        int maxAttempts = getPositiveInteger(scanner);

        String[] code = CodeGenerator.generateCode(CODE_LENGTH);
        System.out.println("You have " + maxAttempts + " attempts to crack the code.");

        boolean isGuessedCorrectly = false; // Track whether the player guesses correctly

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.println("Attempt " + attempt + ": Enter your guess (e.g., pink green red gray purple white):");
            String guessInput = scanner.nextLine();
            String[] guess = parseGuess(guessInput);

            if (guess.length != CODE_LENGTH || !areValidColors(guess)) {
                System.out.println("Invalid input! Please enter exactly " + CODE_LENGTH + " valid colors.");
                attempt--; // Don't count invalid attempts
                continue;
            }

            int[] feedback = Feedback.evaluateGuess(code, guess);
            System.out.println("Correct position: " + feedback[0]);
            System.out.println("Correct color (wrong position): " + feedback[1]);

            if (feedback[0] == CODE_LENGTH) {
                System.out.println("Congratulations! You cracked the code!");
                isGuessedCorrectly = true;
                break;
            }
        }

        if (!isGuessedCorrectly) {
            System.out.println("Game Over! You ran out of attempts.");
            System.out.print("The correct code was: ");
            for (String color : code) {
                System.out.print(color + " ");
            }
            System.out.println();
        }
    }

    private static String[] parseGuess(String input) {
        return input.trim().split("\\s+"); // Split input by spaces
    }

    private static boolean areValidColors(String[] guess) {
        for (String color : guess) {
            boolean isValid = false;
            for (String validColor : VALID_COLORS) {
                if (color.equalsIgnoreCase(validColor)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                return false;
            }
        }
        return true;
    }

    private static int getPositiveInteger(Scanner scanner) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid positive number: ");
            }
        }
    }
}

