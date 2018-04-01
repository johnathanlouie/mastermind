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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Johnathan Louie
 */
public class CodeWord {

    private static final Random RANDOM = new Random();
    private static int codeRadix = 6;
    private static int codeLength = 4;

    private static int codeRange() {
        return (int) Math.pow(codeRadix, codeLength);
    }

    public static int getCodeRadix() {
        return codeRadix;
    }

    public static void setCodeRadix(int codeRadix) {
        CodeWord.codeRadix = codeRadix;
    }

    public static int getCodeLength() {
        return codeLength;
    }

    public static void setCodeLength(int codeLength) {
        CodeWord.codeLength = codeLength;
    }

    public static Set<CodeWord> getRange() {
        Set<CodeWord> universe = new HashSet<>();
        for (int i = 0; i < codeRange(); i++) {
            universe.add(new CodeWord(i));
        }
        return universe;
    }

    public static boolean isValid(String input) {
        if (input.length() != codeLength) {
            return false;
        }
        char[] ch = input.toCharArray();
        for (char i : ch) {
            if (Character.isDigit(i)) {
                if (Character.digit(i, 10) >= codeRadix) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private final int[] pegs;
    private final int codePoint;

    public CodeWord() {
        codePoint = RANDOM.nextInt(codeRange());
        pegs = toPegs(codePoint);
    }

    public CodeWord(int codePoint) {
        this.codePoint = codePoint;
        this.pegs = toPegs(codePoint);
    }

    public CodeWord(int[] pegs) {
        this.pegs = pegs;
        this.codePoint = toCodePoint(pegs);
    }

    public CodeWord(String code) {
        char[] p = code.toCharArray();
        this.pegs = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            this.pegs[i] = Character.digit(p[i], 10);
        }
        this.codePoint = toCodePoint(this.pegs);
    }

    private int toCodePoint(int[] pegs) {
        int q = 0;
        for (int i = 0; i < pegs.length; i++) {
            if (pegs[i] != 0) {
                q += pegs[i] * Math.pow(10, i);
            }
        }
        return q;
    }

    private int[] toPegs(int codePoint) {
        int[] output = new int[codeLength];
        for (int i = 0; i < codeLength; i++) {
            output[i] = codePoint % codeRadix;
            codePoint /= codeRadix;
        }
        return output;
    }

    public Key getKey(CodeWord other) {
        int[] a = Arrays.copyOf(pegs, pegs.length);
        int[] b = Arrays.copyOf(other.pegs, other.pegs.length);
        int black = 0;
        int white = 0;
        int none = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) {
                black++;
                a[i] = -1;
                b[i] = -2;
            }
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    white++;
                    b[j] = -2;
                    break;
                }
            }
        }
        none = codeLength - black - white;
        return new Key(black, white, none);
    }

    @Override
    public int hashCode() {
        return codePoint;
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
        final CodeWord other = (CodeWord) obj;
        return this.codePoint == other.codePoint;
    }

    @Override
    public String toString() {

        char[] str = new char[pegs.length];
        for (int i = 0; i < pegs.length; i++) {
            str[i] = Character.forDigit(pegs[i], 10);
        }
        return new String(str);
    }

}
