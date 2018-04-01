/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author user
 */
public class DonaldKnuth implements CodeBreaker {

    private Key response = null;
    private CodeWord guess = null;

    private final List<CodeWord> impossible = new LinkedList<>();
    private final Set<CodeWord> possible;

    public DonaldKnuth() {
        possible = CodeWord.getRange();
    }

    @Override
    public void receiveKey(Key key) {
        response = key;
        Iterator<CodeWord> iterator = possible.iterator();
        while (iterator.hasNext()) {
            CodeWord i = iterator.next();
            if (!guess.getKey(i).equals(response)) {
                impossible.add(i);
                iterator.remove();
            }
        }
    }

    @Override
    public CodeWord guess() {
        int minimumEliminated = -1;
        CodeWord bestGuess = null;
        List<CodeWord> unused = new LinkedList<>(possible);
        unused.addAll(impossible);
        for (CodeWord a : unused) {
            int[][] minMaxTable = new int[CodeWord.getCodeLength() + 1][CodeWord.getCodeLength() + 1];
            for (CodeWord b : possible) {
                Key abKey = a.getKey(b);
                minMaxTable[abKey.getBlack()][abKey.getWhite()]++;
            }
            int mostHits = -1;
            for (int[] row : minMaxTable) {
                for (int i : row) {
                    mostHits = Integer.max(i, mostHits);
                }
            }
            int score = possible.size() - mostHits;
            if (score > minimumEliminated) {
                minimumEliminated = score;
                bestGuess = a;
            }
        }
        guess = bestGuess;
        System.out.println("Enter secret code: " + guess);
        return bestGuess;
    }

}
