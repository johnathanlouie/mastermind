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

    private CodeWord answer;

    public ComputerCodeMaker() {
    }

    @Override
    public void setAnswer() {
        answer = new CodeWord();
        System.out.println("Enter secret code: ");
    }

    @Override
    public CodeWord getAnswer() {
        return answer;
    }

    @Override
    public Key verify(CodeWord guess) {
        return answer.getKey(guess);
    }

}
