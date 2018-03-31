package io.jlouie.mastermind;

import java.util.Arrays;

public class Code {

    private final int[] pegs;
    private final int codePoint;

    public Code(int codePoint) {
        if (codePoint >= Main.getCodePointRange() || codePoint < 0) {
            throw new RuntimeException();
        }
        this.codePoint = codePoint;
        this.pegs = toPegs(codePoint);
    }

    public Code(int[] pegs) {
        if (pegs.length != Main.getCodeLength()) {
            throw new RuntimeException();
        }
        for (int i : pegs) {
            if (i < 0 || i >= Main.getCodeRadix()) {
                throw new RuntimeException();
            }
        }
        this.pegs = pegs;
        this.codePoint = toCodePoint(pegs);
    }

    public Code(char[] pegs) {
        this.pegs = new int[pegs.length];
        for (int i = 0; i < pegs.length; i++) {
            this.pegs[i] = Integer.parseInt(Character.toString(pegs[i]));
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
        int[] output = new int[Main.getCodeLength()];
        for (int i = 0; i < Main.getCodeLength(); i++) {
            output[i] = codePoint % Main.getCodeRadix();
            codePoint /= Main.getCodeRadix();
        }
        return output;
    }

    public Key getKey(Code other) {
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
        none = Main.getCodeLength() - black - white;
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
        final Code other = (Code) obj;
        return this.codePoint == other.codePoint;
    }

    @Override
    public String toString() {
        return Arrays.toString(pegs);
    }

}
