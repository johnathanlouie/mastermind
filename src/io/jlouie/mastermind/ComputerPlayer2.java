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
public class ComputerPlayer2 extends Player2 {

	private GuessStyle style;

	public ComputerPlayer2(GuessStyle style) {
		this.style = style;
	}

	@Override
	public Code guess() {
		return style.guess();
	}

}
