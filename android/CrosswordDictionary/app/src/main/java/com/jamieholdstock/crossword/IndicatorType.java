package com.jamieholdstock.crossword;

import com.jamieholdstock.crossword.activities.tutorial.devices.AcrosticActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.AnagramActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.DeletionActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.HiddenWordActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.HomophoneActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.ReversalActivity;

public enum IndicatorType {

    Acrostic("Acrostic", AcrosticActivity.class),
    Anagram("Anagram", AnagramActivity.class),
    DeletionEnd("Deletion (end)", DeletionActivity.class),
    DeletionMiddle("Deletion (middle)", DeletionActivity.class),
    Deletion("Deletion", DeletionActivity.class),
    DeletionStart("Deletion (start)", DeletionActivity.class),
    DeletionStartEnd("Deletion (start/end)", DeletionActivity.class),
    HiddenWord("Hidden Word", HiddenWordActivity.class),
    Homophone("Homophone", HomophoneActivity.class),
    Reversal("Reversal", ReversalActivity.class),
    ReversalAcross("Reversal (across)", ReversalActivity.class),
    ReversalDown("Reveral (down)", ReversalActivity.class);

    private String displayName;
    private Class activityToLaunch;

    IndicatorType(String displayName, Class activityToLaunch) {
        this.displayName = displayName;
        this.activityToLaunch = activityToLaunch;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColumnName() {
        return this.name();
    }

    public Class getActivityToLaunch() {
        return activityToLaunch;
    }
}
