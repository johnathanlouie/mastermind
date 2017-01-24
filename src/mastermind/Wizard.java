/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author user
 */
public class Wizard {

	private static final Random RANDOM = new Random();
	private final Sage sage;

	private final List<Code> guessHistory;
	private final List<Response> responseHistory;
	private final List<List<Code>> possibleHistory;
	private final List<Code> impossible;
	private List<Code> possible;

	public Wizard(Sage sage) {
		this.sage = sage;
		guessHistory = new ArrayList<>();
		responseHistory = new ArrayList<>();
		possibleHistory = new ArrayList<>();
		impossible = new ArrayList<>();
		possible = sage.getCodes();
		possibleHistory.add(possible);
		responseHistory.add(null);
		guessHistory.add(null);
	}

	public List<Code> getPossible() {
		return possible;
	}

	public void handleResponse(Code guess, Response response) {
		guessHistory.add(guess);
		responseHistory.add(response);
		filter(guess, response);
		possibleHistory.add(possible);
	}

	private void filter(Code guess, Response response) {
		List<Code> newPossible = new ArrayList<>();
		for (Code i : possible) {
			if (response.equals(guess.response(i))) {
				newPossible.add(i);
			} else if (i.equals(guess)) {
			} else {
				impossible.add(i);
			}
		}
		possible = newPossible;
	}

	public Code minMax() {
		if (possible.size() == 1 || possible.size() == 2) {
			return possible.get(0);
		}
		int max = Integer.MIN_VALUE;
		Code maxCode = null;
		for (Code c : possible) {
			int elim = 0;
			for (Response r : sage.getResponses()) {
				int elimPerRes = eliminatedCount(possible, c, r);
				if (elimPerRes != possible.size()) {
					elim += elimPerRes;
				}
			}
			if (max < elim) {
				max = elim;
				maxCode = c;
			}
		}
		for (Code c : impossible) {
			int elim = 0;
			for (Response r : sage.getResponses()) {
				int elimPerRes = eliminatedCount(possible, c, r);
				if (elimPerRes != possible.size()) {
					elim += elimPerRes;
				}
			}
			if (max < elim) {
				max = elim;
				maxCode = c;
			}
		}
		return maxCode;
	}

	public Code memoMiniMax() {
		GameNodeKey key = new GameNodeKey(possible, impossible);
		Code best = sage.getMiniMax(key);
		if (best == null) {
			best = minMax();
			sage.setMiniMax(key, best);
		}
		return best;
	}

	private int eliminatedCount(List<Code> list, Code guess, Response response) {
		int removed = 0;
		for (Code i : list) {
//			if (!response.equals(i.response(guess))) {
			if (!response.equals(sage.getResponse(i, guess))) {
				removed++;
			}
		}
		return removed;
	}

	public int getRemovedCount(int turn) {
		if (turn == 0) {
			return 0;
		}
		return possibleHistory.get(turn - 1).size() - possibleHistory.get(turn).size();
	}

	public int getNumPossible(int turn) {
		return possibleHistory.get(turn).size();
	}

	public Code getRandomPossible() {
		List<Code> l = possible;
		int i = RANDOM.nextInt(l.size());
		return l.get(i);
	}

	public List<Code> getRandomUnused() {
		return impossible;
	}

}
