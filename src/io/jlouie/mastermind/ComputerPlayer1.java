/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

import java.util.Random;

/**
 *
 * @author user
 */
public class ComputerPlayer1 extends Player1 {

	private static final Random RANDOM = new Random();
	private final Code answer;

	public ComputerPlayer1() {
		answer = inputAnswer();
	}

	@Override
	public Code getAnswer() {
		return answer;
	}

	@Override
	public Response respond(Code guess) {
		return answer.response(guess);
	}

	private Code inputAnswer() {
		int[] ans = new int[Main.POS_NUM];
		for (int i = 0; i < Main.POS_NUM; i++) {
			ans[i] = RANDOM.nextInt(Main.COLOR_NUM);
		}
		return new Code(ans);
	}

}
