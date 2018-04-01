/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

import java.util.Scanner;

/**
 *
 * @author jlouie
 */
public class System2 {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readLine(String msg) {
        System.out.print(msg);
        return SCANNER.nextLine();
    }

    public static String readPassword(String msg) {
        if (System.console() != null) {
            return new String(System.console().readPassword(msg));
        }
        return readLine(msg);
    }

}
