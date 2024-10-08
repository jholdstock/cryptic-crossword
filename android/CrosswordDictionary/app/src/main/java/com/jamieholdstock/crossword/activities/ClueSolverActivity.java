package com.jamieholdstock.crossword.activities;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jamieholdstock.crossword.L;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.SolverSearchResults;
import com.jamieholdstock.crossword.views.ClueView;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ClueSolverActivity extends SearchActivityBase {

    @Override
    protected String[] getIntro() {
        return new String[]{
                "How to use the clue solver",
                "Type a full clue into the box above and attempt to look it up in a database of previously solved clues.",
                "Clues can be reworded almost endlessly and new clues are being written every day, so don't expect this to work all the time. For brand new crosswords this may not work at all. "
        };
    }

    @Override
    protected String getSearchHint() {
        return "Clue...";
    }

    @Override
    protected int getBackgroundColor() {
        return R.color.Nephritis;
    }

    @Override
    protected void onSearchButtonPressed(View v) {
        String searchTerm = searchBox.getText().toString();

        if (searchTerm.trim().equals("")) {
            return;
        }

        ClueSolverAsync clueSolverAsync = new ClueSolverAsync();
        clueSolverAsync.execute(searchTerm);
    }

    private void displaySearchResults(ArrayList<SolvedClue> solvedClues) {
        if (solvedClues.size() == 0) {
            displayError("No results found", "Check spelling and wording carefully");
            return;
        }

        Collections.reverse(solvedClues);

        for (SolvedClue clue: solvedClues) {
            ClueView v = new ClueView(getApplicationContext(), true);

            v.setSolution(clue.getSolution());
            v.setClue(clue.getClue());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 15, 0, 15);
            resultsPanel.addView(v, 0, params);
        }
    }

    private class ClueSolverAsync extends AsyncTask<String,Void,String> {
        SolverSearchResults answers;
        boolean error = false;

        private String baseUrl = "https://www.wordplays.com/";
        private String solverUrl = baseUrl + "crossword-solver/";

        // private String solverUrl = "http://10.0.2.2:8090/crossword-solver/";

        @Override
        protected void onPreExecute() {
            animator.start();
            resultsPanel.removeAllViews();
            searchButton.setClickable(false);
        }
        @Override
        protected String doInBackground(String... input) {
            String query = null;
            try {
                query = URLEncoder.encode(input[0], "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.exit(1);
            }

            L.l("Connecting to " + baseUrl);
            org.jsoup.Connection conn = Jsoup.connect(baseUrl);
            try {
                conn.execute();
            } catch (Exception e) {
                // The initial connection is expected to fail with 401 response. Ignore.
            }

            Map<String, String> cookies = null;
            try {
                cookies = conn.newRequest().execute().cookies();
            } catch (Exception e) {
                error = true;
                L.l("Failed to get cookies");

                e.printStackTrace();
                return null;
            }


            // Make the actual request.
            try {
                String html = Jsoup.connect(solverUrl + query).cookies(cookies).timeout(5000).execute().body();
                answers = new SolverSearchResults(html);
            } catch (Exception e) {
                error = true;
                L.l("Failed to get solutions");
                e.printStackTrace();
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            animator.stop();
            searchButton.setClickable(true);

            if (error) {
                displayError("Error!", "Check internet connection and try again.");
            } else {
                displaySearchResults(answers.getResults());
            }
        }
    }
}
