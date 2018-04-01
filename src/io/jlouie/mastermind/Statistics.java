/*
 * Copyright (C) 2018 Johnathan Louie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.jlouie.mastermind;

/**
 *
 * @author Johnathan Louie
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
