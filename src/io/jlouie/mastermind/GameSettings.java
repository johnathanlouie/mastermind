/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

/**
 *
 * @author jlouie
 */
public class GameSettings {

    private int codeRadix = 6;
    private int codeLength = 4;
    private int codePointRange = 1296;

    public GameSettings() {
    }

    public int getCodeRadix() {
        return codeRadix;
    }

    private void updateCodePointRange() {
        codePointRange = (int) Math.pow(codeRadix, codeLength);
    }

    public void setCodeRadix(int codeRadix) {
        if (codeRadix < 0) {
            throw new RuntimeException();
        }
        this.codeRadix = codeRadix;
        updateCodePointRange();
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        if (codeLength < 0) {
            throw new RuntimeException();
        }
        this.codeLength = codeLength;
        updateCodePointRange();
    }

    public int getCodePointRange() {
        return codePointRange;
    }

}
