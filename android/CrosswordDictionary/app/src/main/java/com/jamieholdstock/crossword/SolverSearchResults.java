package com.jamieholdstock.crossword;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Iterator;

public class SolverSearchResults {

    private ArrayList<SolvedClue> solvedClues;

    public SolverSearchResults(String rawHtml) {
        Document document = Jsoup.parse(rawHtml);
        Element table = document.select("tbody[class=wp-widget-content]").first();

        solvedClues = new ArrayList<>();

        if (table == null) return;

        ArrayList<String> solutions = getSolutions(table);
        ArrayList<String> clues = getClues(table);

        for(int i = 0; i < solutions.size(); i++) {
            solvedClues.add(new SolvedClue(clues.get(i), solutions.get(i)));
        }
    }

    public ArrayList<SolvedClue> getResults() {
        return solvedClues;
    }

    private ArrayList<String> getSolutions(Element table) {
        ArrayList<String> solutions = new ArrayList<>();
        Iterator<Element> ite = table.select("tr.clue td a:not(.definition)").iterator();
        while(ite.hasNext()) {
            String value = ite.next().text();
            solutions.add(value);
        }
        return solutions;
    }

    private ArrayList<String> getClues(Element table) {
        ArrayList<String> clues = new ArrayList<>();
        Iterator<Element> ite = table.select("tr.clue td.clue").iterator();
        while(ite.hasNext()) {
            String value = ite.next().text();
            clues.add(value);
        }
        return clues;
    }
}
