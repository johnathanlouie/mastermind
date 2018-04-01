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
public class HumanCodeMaker implements CodeMaker {

    private CodeWord answer;

    public HumanCodeMaker() {
    }

    @Override
    public void setAnswer() {
        String input;
        do {
            input = System2.readPassword("Enter secret code: ");
        } while (!CodeWord.isValid(input));
        answer = new CodeWord(input);
    }

    @Override
    public CodeWord getAnswer() {
        return answer;
    }

    @Override
    public Key verify(CodeWord guess) {
        return answer.getKey(guess);
    }

}
