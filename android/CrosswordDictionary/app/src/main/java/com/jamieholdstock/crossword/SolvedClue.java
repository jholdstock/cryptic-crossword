package com.jamieholdstock.crossword;

public class SolvedClue {
    private String clue;
    private String solution;

    public SolvedClue(String clue, String solution) {
        this.clue = clue;
        this.solution = solution;
    }

    public String getClue() {
        return clue;
    }

    public String getSolution() {
        return solution;
    }
}
