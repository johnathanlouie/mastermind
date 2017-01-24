/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public class Sage {

	private final int initialSize;
	private final List<Code> codes;
	private final List<Response> responses;
	private final Response[][] responseTable;
	private final HashMap<GameNodeKey, Code> elimTable;
	private final int color;
	private final int position;

	public Sage(int color, int pos) {
		this.color = color;
		this.position = pos;
		initialSize = (int) Math.pow(color, pos);
		codes = genPerm();
		responses = new ArrayList<>();
		responses(0, new int[3], pos, responses);
		responseTable = new Response[initialSize][initialSize];
		elimTable = new HashMap<>();
	}

	public Response getResponse(Code c1, Code c2) {
		int x = c1.hashCode();
		int y = c2.hashCode();
		Response r = responseTable[x][y];
		if (r == null) {
			r = c1.response(c2);
			responseTable[x][y] = r;
			responseTable[y][x] = r;
		}
		return r;
	}

	private List<Code> genPerm() {
		List<Code> list = new ArrayList<>(initialSize);
		for (int i = 0; i < initialSize; i++) {
			list.add(new Code(i, color, position));
		}
		return list;
	}

	private static void responses(int index, int[] x, int remain, List<Response> list) {
		if (remain == 0) {
			list.add(new Response(Arrays.copyOf(x, x.length)));
			return;
		}
		if (index == 3) {
			return;
		}
		for (int i = 0; i <= remain; i++) {
			int[] y = Arrays.copyOf(x, x.length);
			y[index] = i;
			responses(index + 1, y, remain - i, list);
		}
	}

	public List<Code> getCodes() {
		return codes;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public Code getMiniMax(GameNodeKey key) {
		return elimTable.get(key);
	}

	public void setMiniMax(GameNodeKey key, Code value) {
		elimTable.putIfAbsent(key, value);
	}

}
