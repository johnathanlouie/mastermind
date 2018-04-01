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

import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author Johnathan Louie
 */
public class System2 {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Console CONSOLE = System.console();

    public static String readLine(String msg) {
        if (CONSOLE != null) {
            return CONSOLE.readLine(msg);
        }
        System.out.print(msg);
        return SCANNER.nextLine();
    }

    public static String readPassword(String msg) {
        if (CONSOLE != null) {
            return new String(CONSOLE.readPassword(msg));
        }
        return readLine(msg);
    }

}
