package com.app.sonny.rhsgpa;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Caesar1Password-abc on 7/6/2015.
 */
public class GradeCard0 extends Fragment implements View.OnClickListener {

    static final String TAG = "GRADECARD1.TAG";

    private int gradeCode;
    private SeekBar mainSeekBar;
    private SeekBar creditsSeekBar;
    private TextView selectGradeText;
    private TextView selectCreditsText;
    int buttonToggle;
    private String grade;
    private int progressValue;
    private double credits;
    private TextView classNameInput;

    private Button honorsButton;
    private Button APButton;
    private Button CPButton;
    private Button courseCatalogButton;

    private final int cpbutton = R.id.collegePrepButton0;
    private final int honorsbutton = R.id.honorsButton0;
    private final int apbutton = R.id.APButton0;
    private final int coursebutton = R.id.courseCatalogButton0;

    private static final int FRAGMENTCODE = 0;
    private String className;

    public static final String CONTEXT = "com.app.Sonny";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grade_card_0, container, false);

        return view;
    }


    public void onClick(View view) {

        int buttonId = view.getId();

        //button toggle: -1 indicates resetted, 0 CP, 1 Honors, 2 AP


        switch (buttonId) {
            case cpbutton:

                if (buttonToggle == 0) {
                    CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                    buttonToggle = -1;
                    break;
                }


                CPButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                buttonToggle = 0;

                break;
            case honorsbutton:
                if (buttonToggle == 1) {
                    honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                    buttonToggle = -1;
                    break;
                }

                CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                honorsButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                buttonToggle = 1;
                break;

            case apbutton:
                if (buttonToggle == 2) {
                    APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                    buttonToggle = -1;
                    break;
                }

                CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                APButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                buttonToggle = 2;

                break;

            //SHOW COURSE CATALOG MENU


        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //saveFile();
    }

    public void saveFile() {

        className = classNameInput.getText().toString();
        String saveInfo = FRAGMENTCODE + ": " + className + ", " + String.valueOf(gradeCode) + ", " + String.valueOf(credits);

        SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString(String.valueOf(FRAGMENTCODE), saveInfo);
        Log.w(CONTEXT, "saved in shared preferences");
        //editor.putString("text", mSaved.getText().toString());
        //editor.putInt("selection-start", mSaved.getSelectionStart());
        // editor.putInt("selection-end", mSaved.getSelectionEnd());
        editor.apply();

    }

    public void readFile() {
        SharedPreferences reader = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedInfo = reader.getString(String.valueOf(FRAGMENTCODE), null);

        Log.w(CONTEXT, "READ: " + savedInfo);

    }


}

