package io.jlouie.mastermind;

/**
 *
 * @author user
 */
public class PossibleOnly implements GuessStyle {

	private final Wizard wiz;

	public PossibleOnly(Wizard wiz) {
		this.wiz = wiz;
	}

	@Override
	public Code guess() {
		return wiz.getRandomPossible();
	}

}
