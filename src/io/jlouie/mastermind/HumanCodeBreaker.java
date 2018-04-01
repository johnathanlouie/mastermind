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
    public void receiveKey(Key key) {
    }

    @Override
    public CodeWord guess() {
        String input;
        do {
            input = System2.readLine("Enter your guess: ");
        } while (!CodeWord.isValid(input));
        return new CodeWord(input);
    }

}
