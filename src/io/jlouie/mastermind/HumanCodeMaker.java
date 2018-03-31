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
public class HumanCodeMaker implements CodeMaker {

    private Code answer;

    public HumanCodeMaker() {
    }

    @Override
    public void setAnswer() {
        char[] login;
        do {
            login = System.console().readPassword("Enter secret code: ");
        } while (login.length != Main.getCodeLength());
        answer = new Code(login);
    }

    @Override
    public Code getAnswer() {
        return answer;
    }

    @Override
    public Key verify(Code guess) {
        return answer.getKey(guess);
    }

}
