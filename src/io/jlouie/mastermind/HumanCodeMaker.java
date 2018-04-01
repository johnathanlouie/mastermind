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

    private CodeWord answer;

    public HumanCodeMaker() {
    }

    @Override
    public void setAnswer() {
        String input;
        do {
            input = System2.readPassword("Enter secret code: ");
        } while (!CodeWord.isValid(input));
        answer = new CodeWord(input);
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
