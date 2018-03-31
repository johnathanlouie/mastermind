/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

/**
 *
 * @author user
 */
public class HumanCodeBreaker implements CodeBreaker {

    public HumanCodeBreaker() {
    }

    @Override
    public Code guess() {
        String login;
        do {
            login = System.console().readLine("Enter your guess: ");
        } while (login.length() != Main.getCodeLength());
        return new Code(login.toCharArray());
    }

}
