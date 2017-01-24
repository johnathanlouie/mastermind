/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

/**
 *
 * @author user
 */
public class HumanPlayer2 extends Player2 {

	private final UserInterface ui;

	public HumanPlayer2(UserInterface ui) {
		this.ui = ui;
	}

	@Override
	public Code guess() {
		return ui.inputGuess();
	}

}
