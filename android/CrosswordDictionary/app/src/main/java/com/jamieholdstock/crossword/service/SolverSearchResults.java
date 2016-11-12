package com.jamieholdstock.crossword.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.jamieholdstock.crossword.L;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Iterator;

public class SolverSearchResults implements Parcelable {

    private final String rawHtml;
    private Document document;

    public SolverSearchResults(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    private Document getDoc() {
        if (document == null) {
            document = Jsoup.parse(rawHtml);
        }
        return document;
    }

    public ArrayList<String> getSolutions() {
        Element table = getDoc().select("tbody[class=wp-widget-content]").first();

        Iterator<Element> ite = table.select("td a").iterator();

        ArrayList<String> solutions = new ArrayList<>();
        while(ite.hasNext()) {
            String value = ite.next().text();
            solutions.add(value);
            L.l("Solution: " + value);
        }

        return solutions;
    }

    public ArrayList<String> getClues() {
        Element table = getDoc().select("tbody[class=wp-widget-content]").first();

        Iterator<Element> ite = table.select("td[class=clue]").iterator();

        ArrayList<String> clues = new ArrayList<>();
        while(ite.hasNext()) {
            String value = ite.next().text();
            clues.add(value);
            L.l("Clue: " + value);
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
