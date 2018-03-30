/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

import java.util.List;

/**
 *
 * @author user
 */
public class GameNodeKey {

	private final int possible;
	private final int impossible;

	public GameNodeKey(List<Code> possible, List<Code> impossible) {
		this.possible = possible.hashCode();
		this.impossible = impossible.hashCode();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + this.possible;
		hash = 23 * hash + this.impossible;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final GameNodeKey other = (GameNodeKey) obj;
		if (this.possible != other.possible) {
			return false;
		}
		if (this.impossible != other.impossible) {
			return false;
		}
		return true;
	}

}
