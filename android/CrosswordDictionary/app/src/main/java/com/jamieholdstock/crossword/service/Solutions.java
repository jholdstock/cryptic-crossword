package com.jamieholdstock.crossword.service;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Solutions implements Parcelable {

    private final String rawHtml;
    private Document doc;

    public Solutions(String rawHtml) {
        this.rawHtml = rawHtml;
        this.doc = Jsoup.parse(rawHtml);
    }

    public String getSolution() {
        return "Solution 1234";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(rawHtml);
    }

    public static final Creator<Solutions> CREATOR = new Creator<Solutions>() {
        public Solutions createFromParcel(Parcel in) {
            return new Solutions(in);
        }
        public Solutions[] newArray(int size) {
            return new Solutions[size];
        }
    };

    private Solutions(Parcel in) {
        this(in.readString());
    }
}
