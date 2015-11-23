package com.app.sonny.rhsgpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Caesar1Password-abc on 7/19/2015.
 */
public class CourseMenu extends AppCompatActivity implements View.OnClickListener {

    public static String CONTEXT = "courseMenu";
    private Button button[] = new Button[9];
    private int classNumber, fragmentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Choose a Category");

        button[0] = (Button) findViewById(R.id.businessCourses);
        button[1] = (Button) findViewById(R.id.englishCourses);
        button[2] = (Button) findViewById(R.id.familyCourses);
        button[3] = (Button) findViewById(R.id.mathCourses);
        button[4] = (Button) findViewById(R.id.scienceCourses);
        button[5] = (Button) findViewById(R.id.socialStudiesCourses);
        button[6] = (Button) findViewById(R.id.artCourses);
        button[7] = (Button) findViewById(R.id.wellnessCourses);
        button[8] = (Button) findViewById(R.id.languageCourses);

        button[0].setOnClickListener(this);
        button[1].setOnClickListener(this);
        button[2].setOnClickListener(this);
        button[3].setOnClickListener(this);
        button[4].setOnClickListener(this);
        button[5].setOnClickListener(this);
        button[6].setOnClickListener(this);
        button[7].setOnClickListener(this);
        button[8].setOnClickListener(this);

        Intent intent = getIntent();

        if (intent != null) {
            try {
                String temp = intent.getStringExtra(Values.CLASSINDEXTAG);
                String split[] = temp.split(":");

                fragmentNumber = Integer.parseInt(split[0]);
                classNumber = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                classNumber = 0;
                fragmentNumber = 0;
            }
            Log.w("CourseMenu.class", String.valueOf(fragmentNumber) + ", " + classNumber);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), BusinessAndMarketing.class);
        intent.putExtra(CONTEXT, String.valueOf(v.getId()));
        intent.putExtra(Values.CLASSINDEXTAG, String.valueOf(fragmentNumber) + ":" + classNumber); //sends the number of the fragment

        startActivity(intent);


        //finish();


    }


}


