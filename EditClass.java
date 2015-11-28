package com.app.sonny.rhsgpa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Sonny on 8/15/2015.
 */
public class EditClass extends AppCompatActivity {

    private SeekBar creditsSeekBar;
    private TextView selectCreditsText, classNameInput;
    private Button CPButton, honorsButton, APButton, courseCatalogButton, okayButton;

    private int buttonToggle, classNumber, fragmentNumber;
    private double credits;
    private String buttonLine;

    private SharedPreferences reader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_class);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        Intent intent = getIntent();


        //editclass will never be called with empty intent

        String[] temp1 = intent.getStringExtra(Values.CLASSINDEXTAG).split(":");
        fragmentNumber = Integer.parseInt(temp1[0]);
        classNumber = Integer.parseInt(temp1[1]);
        String temp2 = intent.getStringExtra(Values.FRAGMENTCODE[fragmentNumber][classNumber]);
        String classInfo[] = temp2.split("_");

        if (classInfo != null) {

            classNameInput.setText(classInfo[0]);
            String temp = classInfo[1];
            if (temp.equals("CP"))
                classLevelDetector(0);
            else if (temp.equals("H"))
                classLevelDetector(1);
            else classLevelDetector(2);

            credits = Double.parseDouble(classInfo[2]);
            creditsSeekBar.setProgress((int) credits * 2);

        }

    }

    public void init() {
        //MAY THROW EXCEPTION
        reader = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        creditsSeekBar = (SeekBar) findViewById(R.id.creditsSeekBar0);
        selectCreditsText = (TextView) findViewById(R.id.selectCreditsText0);
        classNameInput = (TextView) findViewById(R.id.classNameInput0);

        CPButton = (Button) findViewById(R.id.collegePrepButton0);
        honorsButton = (Button) findViewById(R.id.honorsButton0);
        APButton = (Button) findViewById(R.id.APButton0);
        courseCatalogButton = (Button) findViewById(R.id.courseCatalogButton0);
        okayButton = (Button) findViewById(R.id.okayButton);

        CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
        honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
        APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));

        CPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classLevelDetector(0);

            }
        });
        honorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classLevelDetector(1);

            }
        });
        APButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classLevelDetector(2);

            }
        });
        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);

                //SAVING DONE
                SharedPreferences.Editor editor = reader.edit();
                editor.putString(Values.FRAGMENTCODE[fragmentNumber][classNumber], buttonLine);

                editor.apply();

                startActivity(intent);
            }
        });

        courseCatalogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CourseMenu.class);
                intent.putExtra(Values.CLASSINDEXTAG, classNumber);
                startActivity(intent);
            }
        });

        creditsSeekBar.setMax(12);

        creditsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                credits = (double) progress / 2;
                selectCreditsText.setText("Credits: " + credits);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void classLevelDetector(int buttonToggle) {
        switch (buttonToggle) {
            case 0:
                CPButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                this.buttonToggle = 0;
                break;
            case 1:
                CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                honorsButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                APButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                this.buttonToggle = 1;
                break;
            case 2:
                CPButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                honorsButton.setBackgroundColor(Color.parseColor("#ffbdbdbd"));
                APButton.setBackgroundColor(Color.parseColor("#ff1cbb6e"));
                this.buttonToggle = 2;
                break;

        }
    }

   /* public void saveFile() {

        SharedPreferences.Editor editor = reader.edit();
        String className = classNameInput.getText().toString();
        String saveInfo = className + "_" + "" + "_" + credits + "_" + buttonToggle;
        Log.w("EditClass", "fragment #: " + fragmentNumber + " class: " + classNumber + "\t" + saveInfo);
        editor.putString(Values.FRAGMENTCODE[fragmentNumber][classNumber], saveInfo); //edit tag needs to be an ARRAY
        editor.apply();


    }*/

    @Override
    protected void onPause() {
        super.onPause();

        Log.w("EditClass", "File Saved");


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.w("EditClass", "File Saved");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
