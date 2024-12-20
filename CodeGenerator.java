package mastermind;

import java.util.Random;

public class CodeGenerator {
    private static final String[] COLORS = {"pink", "green", "red", "gray", "purple", "white"};

    public static String[] generateCode(int length) {
        Random random = new Random();
        String[] code = new String[length];
        for (int i = 0; i < length; i++) {
            code[i] = COLORS[random.nextInt(COLORS.length)];
        }
        return code;
    }
}

