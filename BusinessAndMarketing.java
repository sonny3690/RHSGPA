package com.app.sonny.rhsgpa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Caesar1Password-abc on 7/23/2015.
 */

public class BusinessAndMarketing extends AppCompatActivity implements View.OnClickListener {

    private static final String CLASSTAG = "CLASSSELECTION";
    public final String[] fileName = {"Business and Marketing", "English and Theater", "Family and Consumer Science", "Mathematics & Computer Science", "Science", "Social Studies & Social Sciences",
            "Visual & Performing Arts", "Wellness", "World Languages"};
    private final String TAG = "Business and Marketing";
    private Button[] selectButton;
    private int buttonCount = 0;
    private int numOfClasses;
    private InputStream inputStream;
    private BufferedReader reader;
    private LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f);
    private LinearLayout linearLayout;
    private String buttonInfo[][];
    //private ScrollView scrollView;
    private String buttonTitles[];
    private String TITLE = "";
    private int classIndex, fragmentNumber;
    private int classNumber; //indicates which class the user is on


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_list);
        //scrollView =  (ScrollView)findViewById(R.id.classListView);


        //GET THE INTENT
        Intent intent = getIntent();
        String classInfo = intent.getStringExtra(CourseMenu.CONTEXT);

        String split[] = intent.getStringExtra(Values.CLASSINDEXTAG).split(":");
        fragmentNumber = Integer.parseInt(split[0]);
        classNumber = Integer.parseInt(split[1]);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (classInfo != null) {
            classIndex = getClassIndex(classInfo);
            getSupportActionBar().setTitle(fileName[classIndex]);
        }

        //ARRAY INITIALIZERS
        selectButton = new Button[numOfClasses];
        buttonInfo = new String[numOfClasses][4];
        buttonTitles = new String[numOfClasses];
        linearLayout = (LinearLayout) findViewById(R.id.classLayout);

        //SET ATTRIBUTES
        for (int i = 0; i < numOfClasses; i++) {
            selectButton[i] = new Button(this);
            selectButton[i].setLayoutParams(lp);
            selectButton[i].setBackgroundColor(Color.rgb(160, 255, 179));
            selectButton[i].setGravity(Gravity.CENTER_HORIZONTAL);
            selectButton[i].setOnClickListener(this);
            linearLayout.addView(selectButton[i]);

        }

        try {
            //reads the file and the information is presented in a button
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getClassIndex(String classInfo) {
        Log.w("TEMP", classInfo);

        int temp = Integer.parseInt(classInfo);

        switch (temp) {
            case R.id.businessCourses:
                numOfClasses = 9;
                return 0;
            case R.id.englishCourses:
                numOfClasses = 41;
                return 1;
            case R.id.familyCourses:
                numOfClasses = 5;
                return 2;
            case R.id.mathCourses:
                numOfClasses = 25;
                return 3;
            case R.id.scienceCourses:
                numOfClasses = 24;
                return 4;
            case R.id.socialStudiesCourses:
                numOfClasses = 33;
                return 5;
            case R.id.artCourses:
                numOfClasses = 51;
                return 6;
            case R.id.wellnessCourses:
                numOfClasses = 4;
                return 7;
            case R.id.languageCourses:
                numOfClasses = 30;
                return 8;
            default:
                Log.w(Values.ERRORTAG, "GETCLASSINDEX ERROR");
                return -1;

        }


    }


    public void readFile() throws IOException {
        inputStream = getAssets().open(fileName[classIndex] + ".txt");
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        while ((line = reader.readLine()) != null) {
            buttonTitles[buttonCount] = line;
            initializeButton(buttonCount, line);
            buttonCount++;
            Log.w(TAG, line + ": " + String.valueOf(buttonCount));
        }

        reader.close();


    }

    public void initializeButton(int buttonCount, String buttonTitle) {
        //selectButton[buttonCount].setText(buttonTitle);
        String split[] = buttonTitle.split("_");

        selectButton[buttonCount].setText(split[0]);

       /* for (int i = 0; i<4; i++)
            buttonInfo[buttonCount][i] = split [i];*/
       // Log.w(TAG, "button #" + buttonCount + " was created");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //scrollView.removeAllViews();
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < numOfClasses; i++) {
            if (v == selectButton[i]) {
                Intent intent = new Intent(this, EditClass.class);
                intent.putExtra(Values.FRAGMENTCODE[fragmentNumber][classNumber], buttonTitles[i]);
                //intent.putExtra(Values.CLASSINDEXTAG, String.valueOf(fragmentNumber) + ":" + classNumber);
                //Log.w(TAG, "SELECTED: " + String.valueOf(i));
                Log.w(TAG,"CLASSNUMBER:"  +  String.valueOf(classNumber));
                startActivity(intent);

            } else {

            }

        }

    }
}


