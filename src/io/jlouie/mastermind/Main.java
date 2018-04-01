package io.jlouie.mastermind;

public class Main {

    public static void main(String[] args) {
        System.out.println("========Settings========");
        int numGames = promptInt("Number of games: ", 1);
        CodeWord.setCodeRadix(promptInt("Number of colors: ", 6));
        CodeWord.setCodeLength(promptInt("Number of positions: ", 4));
        boolean isHumanCodeMaker = promptBool("Human code maker (true/false): ", false);
        boolean isHumanCodeBreaker = promptBool("Human code breaker (true/false): ", false);
        System.out.println();
        System.out.println("========Directions========");
        System.out.println("Enter a " + CodeWord.getCodeLength() + " digit number with each digit ranging from 0 to " + ((CodeWord.getCodeRadix()) - 1) + ".");
        Statistics stats = new Statistics();
        do {
            CodeMaker maker = isHumanCodeMaker ? new HumanCodeMaker() : new ComputerCodeMaker();
            CodeBreaker breaker = isHumanCodeBreaker ? new HumanCodeBreaker() : new DonaldKnuth();
            System.out.println();
            System.out.println("========Game " + (stats.getGames() + 1) + "========");
            maker.setAnswer();
            System.out.println();
            int turn = 0;
            Key response = null;
            do {
                System.out.println();
                System.out.println("========Turn " + ++turn + "========");
                CodeWord guess = breaker.guess();
                response = maker.verify(guess);
                System.out.println("Response: " + response);
                breaker.receiveKey(response);
            } while (!response.isCorrect());
            stats.addStats(turn);
            System.out.println();
            System.out.println("========Winner========");
            System.out.println("Answer: " + maker.getAnswer());
            System.out.println("You won!");
        } while (stats.getGames() < numGames || promptBool("Try again? (true/false): ", false));
        System.out.println();
        System.out.println("========Statistics========");
        System.out.println("Most tries needed: " + stats.getMax());
        System.out.println("Fewest tries needed: " + stats.getMin());
        System.out.println("Average number of tries: " + stats.getAvgTurns());
    }

    private static boolean isPositiveInteger(String str) {
        char[] ch = str.toCharArray();
        for (char i : ch) {
            if (!Character.isDigit(i)) {
                return false;
            }
        }
        return Integer.parseInt(str) > 0;
    }

    private static boolean isBoolean(String str) {
        return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false");
    }

    private static int promptInt(String msg, int def) {
        String input;
        do {
            input = System2.readLine(msg);
            if (input.isEmpty()) {
                return def;
            }
        } while (!isPositiveInteger(input));
        return Integer.parseInt(input);
    }

    private static boolean promptBool(String msg, boolean def) {
        String input;
        do {
            input = System2.readLine(msg);
            if (input.isEmpty()) {
                return def;
            }
        } while (!isBoolean(input));
        return Boolean.parseBoolean(input);
    }

}
