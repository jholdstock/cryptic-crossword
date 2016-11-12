package com.jamieholdstock.crossword.service;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Iterator;

public class SolverSearchResults implements Parcelable {

    private final String rawHtml;
    private Document document;
    private Element table;

    public SolverSearchResults(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    private Document getDoc() {
        if (document == null) {
            document = Jsoup.parse(rawHtml);
        }
        return document;
    }

    public ArrayList<SolvedClue> getResults() {
        table = getDoc().select("tbody[class=wp-widget-content]").first();
        ArrayList<SolvedClue> solvedClues = new ArrayList<>();

        if (table == null) {
            return solvedClues;
        }

        ArrayList<String> solutions = getSolutions();
        ArrayList<String> clues = getClues();

        for(int i = 0; i < solutions.size(); i++) {
            solvedClues.add(new SolvedClue(clues.get(i), solutions.get(i)));
        }
        return solvedClues;
    }

    private ArrayList<String> getSolutions() {
        ArrayList<String> solutions = new ArrayList<>();
        Iterator<Element> ite = table.select("td a").iterator();
        while(ite.hasNext()) {
            String value = ite.next().text();
            solutions.add(value);
        }
        return solutions;
    }

    private ArrayList<String> getClues() {
        Iterator<Element> ite = table.select("td[class=clue]").iterator();
        ArrayList<String> clues = new ArrayList<>();
        while(ite.hasNext()) {
            String value = ite.next().text();
            clues.add(value);
        }
        return clues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(rawHtml);
    }

    public static final Creator<SolverSearchResults> CREATOR = new Creator<SolverSearchResults>() {
        public SolverSearchResults createFromParcel(Parcel in) {
            return new SolverSearchResults(in);
        }
        public SolverSearchResults[] newArray(int size) {
            return new SolverSearchResults[size];
        }
    };

    private SolverSearchResults(Parcel in) {
        this(in.readString());
    }
}
