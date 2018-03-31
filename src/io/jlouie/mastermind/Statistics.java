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
public class Statistics {

    private int max = 0;
    private int min = Integer.MAX_VALUE;
    private int games = 0;
    private int tries = 0;

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getGames() {
        return games;
    }

    public int getTries() {
        return tries;
    }

    public double getAvgTurns() {
        return (double) tries / games;
    }

    public void addStats(int tries) {
        games++;
        this.tries += tries;
        min = Integer.min(min, tries);
        max = Integer.max(max, tries);
    }

}
