/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jlouie.mastermind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author user
 */
public class Wizard {

    private static final Random RANDOM = new Random();

    private final List<Code> universe = new ArrayList<>();
    private List<Code> guessed = new LinkedList<>();
    private List<Code> impossible = new LinkedList<>();
    private Set<Code> possible = new HashSet<>();

    public Wizard() {
        for (int i = 0; i < Main.getCodePointRange(); i++) {
            universe.add(new Code(i));
        }
    }

    public Code getRandomCode() {
        return universe.get(RANDOM.nextInt(universe.size()));
    }

    public int handleResponse(Code guess, Key response) {
        return eliminate(guess, response, guessed, impossible, possible);
    }

    public static int eliminate(Code guess, Key response, List<Code> guessed, List<Code> impossible, Set<Code> possible) {
        int total = possible.size();
        guessed.add(guess);
        possible.remove(guess);
        Iterator<Code> iterator = possible.iterator();
        while (iterator.hasNext()) {
            Code test = iterator.next();
            if (!guess.getKey(test).equals(response)) {
                impossible.add(test);
                iterator.remove();
            }
        }
        return (total - possible.size()) / total;
    }

    public List<Code> getGuessed() {
        return new LinkedList<>(guessed);
    }

    public void setGuessed(List<Code> guessed) {
        this.guessed = guessed;
    }

    public List<Code> getImpossible() {
        return new LinkedList<>(impossible);
    }

    public void setImpossible(List<Code> impossible) {
        this.impossible = impossible;
    }

    public Set<Code> getPossible() {
        return new HashSet<>(possible);
    }

    public void setPossible(Set<Code> possible) {
        this.possible = possible;
    }

    public List<Code> getUniverse() {
        return universe;
    }

}
