package com.app.sonny.rhsgpa;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Caesar1Password-abc on 7/6/2015.
 */
public class GradeCard1 extends Fragment implements View.OnClickListener{

    static final String TAG = "GRADECARD1.TAG";

    private int gradeCode;
    private SeekBar mainSeekBar;
    private SeekBar creditsSeekBar;
    private TextView classNameInput;
    private TextView selectGradeText;
    private TextView selectCreditsText;
    int buttonToggle;
    private String grade;
    private int progressValue;
    private double credits;

    private Button honorsButton;
    private Button APButton;
    private Button CPButton;

    private final int cpbutton = R.id.collegePrepButton1;
    private final int honorsbutton = R.id.honorsButton1;
    private final int apbutton = R.id.APButton1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grade_card_1, container, false);

        mainSeekBar = (SeekBar)view.findViewById(R.id.mainSeekBar1);
        creditsSeekBar = (SeekBar)view.findViewById(R.id.creditsSeekBar1);
        selectGradeText = (TextView)view.findViewById(R.id.selectGradeText1);
        selectCreditsText = (TextView)view.findViewById(R.id.selectCreditsText1);
        classNameInput = (TextView)view.findViewById(R.id.classNameInput1);

        CPButton = (Button) view.findViewById(R.id.collegePrepButton1);
        honorsButton = (Button) view.findViewById(R.id.honorsButton1);
        APButton = (Button) view.findViewById(R.id.APButton1);

        CPButton.setOnClickListener(this);
        honorsButton.setOnClickListener(this);
        APButton.setOnClickListener(this);

        CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
        honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
        APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));


        mainSeekBar.setMax(11);
        creditsSeekBar.setMax(10);

        mainSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue  = progress;

                switch (seekBar.getProgress()) {
                    case 0:
                        grade= "E";
                        gradeCode=1;
                        break;
                    case 1:
                        grade = "D-";
                        gradeCode=2;
                        break;
                    case 2:
                        grade = "D";
                        gradeCode=3;
                        break;
                    case 3:
                        grade = "D+";
                        gradeCode=4;
                        break;
                    case 4:
                        grade= "C-";
                        gradeCode=5;
                        break;
                    case 5:
                        grade = "C";
                        gradeCode=6;
                        break;
                    case 6:
                        grade= "C+";
                        gradeCode=7;
                        break;
                    case 7:
                        grade= "B-";
                        gradeCode=8;
                        break;
                    case 8:
                        grade= "B";
                        gradeCode=9;
                        break;
                    case 9:
                        grade= "B+";
                        gradeCode=10;
                        break;
                    case 10:
                        grade = "A-";
                        gradeCode=11;
                        break;
                    case 11:
                        grade= "A";
                        gradeCode=12;
                        break;

                }

                //gradeShowerText.setText("Your Grade is " + grade);
                selectGradeText.setText(grade);

                // Log.w("GRADECODE", String.format("%s : %s", grade[0], gradeCode[0]));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        creditsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                credits = (double)progress/2;
                selectCreditsText.setText("Credits: " + credits);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
                if (buttonToggle== 1) {
                    honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                    buttonToggle= -1;
                    break;
                }

                CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                honorsButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                buttonToggle= 1;
                break;

            case apbutton:
                if (buttonToggle== 2) {
                    APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                    buttonToggle= -1;
                    break;
                }

                CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                APButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                buttonToggle=2;

                break;
        }
    }

}


