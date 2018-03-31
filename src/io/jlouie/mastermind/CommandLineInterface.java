/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

import java.io.Console;
import java.util.List;

/**
 *
 * @author user
 */
public class CommandLineInterface extends UserInterface {

    private final Console c = System.console();
    private static final CommandLineInterface instance = new CommandLineInterface();

    private CommandLineInterface() {
    }

    public static CommandLineInterface getInstance() {
        if (instance.c == null) {
            return null;
        }
        return instance;
    }

    @Override
    public Code inputAnswer() {
        char[] login;
        do {
            login = c.readPassword("Enter secret code: ");
        } while (login.length != Main.codeLength);
        return toCode(login);
    }

    @Override
    public Code inputGuess() {
        String login;
        do {
            login = c.readLine("Enter your guess: ");
        } while (login.length() != Main.codeLength);
        return toCode(login.toCharArray());
    }

    private Code toCode(char[] p) {
        int[] x = new int[p.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = Integer.parseInt(Character.toString(p[i]));
        }
        return new Code(x);
    }

    public void answer(Code c) {
        System.out.println("Answer: " + c);
    }

    public void guess(Code c) {
        System.out.println("Guess: " + c);
    }

    public void response(String response) {
        System.out.println("Response: " + response);
    }

    public void turn(int turn) {
        System.out.println();
        System.out.println("========Turn " + turn + "========");
    }

    public void hint(List<Code> hint) {
        System.out.println("These are all the possibilites:");
//		System.out.println(viewPossible);
        for (Code i : hint) {
            System.out.println(i);
        }
    }

    public void tryAgain() {
        boolean again = Boolean.parseBoolean(c.readLine("Try again? (true/false): "));
        if (again) {
            Main.game_num++;
        }
    }

    public void directions() {
        System.out.println();
        System.out.println("========Directions========");
        System.out.println("Enter a " + Main.codeLength + " digit number with each digit ranging from 0 to " + (Main.codeRadix - 1) + ".");
    }

    public void win() {
        System.out.println("Win!");
    }

    public void stats(int max, int min, double avg) {
        System.out.println("max turns needed: " + max);
        System.out.println("min turns needed: " + min);
        System.out.println("average turns: " + avg);
    }

    public void menu() {
        System.out.println("========Menu========");
        Main.game_num = Integer.parseInt(c.readLine("Number of games: "));
        Main.codeRadix = Integer.parseInt(c.readLine("Number of colors: "));
        Main.codeLength = Integer.parseInt(c.readLine("Number of positions: "));
        Main.player1 = Boolean.parseBoolean(c.readLine("Human code maker (true/false): "));
        Main.player2 = Boolean.parseBoolean(c.readLine("Human code breaker (true/false): "));
        Main.viewPossible = Boolean.parseBoolean(c.readLine("Turn hints on (true/false): "));
        Main.guessRater = Boolean.parseBoolean(c.readLine("Turn on guess rater (true/false): "));
    }

    public void guessRater(int eliminated, int prev, int left) {
        System.out.println("Eliminated " + eliminated + " out of " + prev + " possibilities.");
        System.out.println("Eliminated " + (eliminated * 100 / prev) + "%.");
        System.out.println(left + " possibilities left.");
    }

}
