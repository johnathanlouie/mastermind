package io.jlouie.mastermind;

import java.io.Console;

public class Main {

    private static GameSettings settings = new GameSettings();
    private static Wizard wizard;

    public static int getCodeLength() {
        return settings.getCodeLength();
    }

    public static int getCodeRadix() {
        return settings.getCodeLength();
    }

    public static int getCodePointRange() {
        return settings.getCodePointRange();
    }

    public static Wizard getWizard() {
        return wizard;
    }

    public static void main(String[] args) {
        System.out.println("========Settings========");
        int numGames = Integer.parseInt(System.console().readLine("Number of games: "));
        settings.setCodeRadix(Integer.parseInt(System.console().readLine("Number of colors: ")));
        settings.setCodeLength(Integer.parseInt(System.console().readLine("Number of positions: ")));
        boolean isHumanCodeMaker = Boolean.parseBoolean(System.console().readLine("Human code maker (true/false): "));
        boolean isHumanCodeBreaker = Boolean.parseBoolean(System.console().readLine("Human code breaker (true/false): "));
        boolean isHintsOn = Boolean.parseBoolean(System.console().readLine("Turn hints on (true/false): "));
        boolean isGuessRaterOn = Boolean.parseBoolean(System.console().readLine("Turn on guess rater (true/false): "));

        Statistics stats = new Statistics();
        do {
            wizard = new Wizard();
            CodeMaker p1 = player1 ? new HumanCodeMaker(cli) : new ComputerCodeMaker();
            CodeBreaker p2 = player2 ? new HumanCodeBreaker(cli) : new ComputerPlayer2(new DonaldKnuth(wiz));
            cli.directions();
            Code guess;
            Key response = null;
            int turn = 0;
            gui.clear();
            while (!win(response)) {
                cli.turn(++turn);
                if (viewPossible) {
                    gui.unhidePossible();
                    gui.write(wiz.getPossible());
                }
                guess = p2.guess();
//				cli.guess(guess);
                response = p1.verify(guess);
//				cli.getKey(getKey);
                gui.appendSummary(guess.toString(), response);
                if (guessRater || !player2 || viewPossible) {
                    wiz.handleResponse(guess, response);
                }
                if (guessRater) {
                    cli.guessRater(wiz.getRemovedCount(turn), wiz.getNumPossible(turn - 1), wiz.getNumPossible(turn));
                }
            }
            cli.answer(p1.answer());
            cli.win();
            stats.addStats(turn);
            cli.stats(stats.getMax(), stats.getMin(), stats.getAvg());
            if (stats.getGames() == game_num) {
                cli.tryAgain();
            }
        } while ();
        System.exit(0);
    }

    private static boolean win(Key response) {
        if (response == null) {
            return false;
        }
//		if (getKey.length() == codeLength) {
//			for (int i = 0; i < getKey.length(); i++) {
//				if (getKey.charAt(i) != 'B') {
//					return false;
//				}
//			}
//			return true;
//		}
        return response.isCorrect();
    }

}
