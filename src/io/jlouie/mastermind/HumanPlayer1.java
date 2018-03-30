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
public class HumanPlayer1 extends Player1 {

	private final Code answer;

	public HumanPlayer1(UserInterface ui) {
		this.answer = ui.inputAnswer();
	}

	@Override
	public Code getAnswer() {
		return answer;
	}

	@Override
	public Response respond(Code guess) {
		return answer.response(guess);
	}

}
