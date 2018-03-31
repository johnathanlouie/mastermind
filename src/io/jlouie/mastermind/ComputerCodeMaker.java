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
public class ComputerCodeMaker implements CodeMaker {

    private Code answer;

    public ComputerCodeMaker() {
    }

    @Override
    public void setAnswer() {
        answer = Main.getWizard().getRandomCode();
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
