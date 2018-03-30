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
public class DonaldKnuth implements GuessStyle {

	private final Wizard wiz;

	public DonaldKnuth(Wizard wiz) {
		this.wiz = wiz;
	}

	@Override
	public Code guess() {
		return wiz.memoMiniMax();
	}

}
