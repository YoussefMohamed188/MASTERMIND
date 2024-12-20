
package mastermind;
public class Feedback {
    public static int[] evaluateGuess(String[] code, String[] guess) {
        int correctPosition = 0;
        int correctColor = 0;

        boolean[] codeUsed = new boolean[code.length];
        boolean[] guessUsed = new boolean[guess.length];

        // Check for correct position
        for (int i = 0; i < code.length; i++) {
            if (code[i].equalsIgnoreCase(guess[i])) {
                correctPosition++;
                codeUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        // Check for correct colors in the wrong position
        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < guess.length; j++) {
                if (!codeUsed[i] && !guessUsed[j] && code[i].equalsIgnoreCase(guess[j])) {
                    correctColor++;
                    codeUsed[i] = true;
                    guessUsed[j] = true;
                    break;
                }
            }
        }

        return new int[]{correctPosition, correctColor};
    }
}
