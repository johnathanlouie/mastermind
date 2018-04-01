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
public class Key {

    private final int black;
    private final int white;
    private final int none;

    public Key(int black, int white, int none) {
        this.black = black;
        this.white = white;
        this.none = none;
    }

    public boolean isCorrect() {
        return white == 0 && none == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.black;
        hash = 13 * hash + this.white;
        hash = 13 * hash + this.none;
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
        final Key other = (Key) obj;
        if (this.black != other.black) {
            return false;
        }
        if (this.white != other.white) {
            return false;
        }
        if (this.none != other.none) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "black=" + black + ", white=" + white + ", none=" + none;
    }

    public int getBlack() {
        return black;
    }

    public int getWhite() {
        return white;
    }

    public int getNone() {
        return none;
    }

}
