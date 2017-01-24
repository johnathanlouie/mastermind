/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.Arrays;

/**
 *
 * @author user
 */
public class Response {

	private final int[] x;

	public Response(int[] x) {
		this.x = x;
	}

	public boolean win() {
		return x[0] == Main.POS_NUM;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Arrays.hashCode(this.x);
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
		final Response other = (Response) obj;
		if (!Arrays.equals(this.x, other.x)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(x);
	}

}
