package io.jlouie.mastermind;

import java.util.Arrays;

public class Code {

	private final int[] e;
	private final int hash;
	private int color;
	private int position;

	public Code(int x, int color, int digit) {
		this.hash = x;
		this.e = toBaseX(x);
	}

	public Code(int[] p) {
		this.e = p;
		this.hash = toBase10(p);
	}

	private int toBase10(int[] p) {
		int q = 0;
		for (int i = 0; i < p.length; i++) {
			if (p[i] != 0) {
				q += p[i] * Math.pow(10, i);
			}
		}
		return q;
	}

	private int[] toBaseX(int x) {
		int[] output = new int[position];
		for (int i = 0; i < position; i++) {
			int digit = x % color;
			x /= color;
			output[i] = digit;
		}
		return output;
	}

	public Response response(Code other) {
		int[] a = Arrays.copyOf(e, e.length);
		int[] b = Arrays.copyOf(other.e, other.e.length);
		int[] res = new int[3];
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				str.append('B');
				res[0]++;
				a[i] = -1;
				b[i] = -2;
			}
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i] == b[j]) {
					str.append('W');
					res[1]++;
					b[j] = -2;
					break;
				}
			}
		}
		int filler = e.length - str.length();
		for (int i = 0; i < filler; i++) {
			str.append('X');
			res[2]++;
		}
//		return str.toString();
		return new Response(res);
	}

	@Override
	public int hashCode() {
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
		final Code other = (Code) obj;
		return this.hash == other.hash;
	}

	@Override
	public String toString() {
		return Arrays.toString(e);
	}

}
