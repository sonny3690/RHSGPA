package com.app.sonny.rhsgpa;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;


public class MainActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

    public static final String CONTEXT = "com.app.Sonny";

    private final static int numOfClasses = Values.numOfClasses; //number of total classes the person is taking
    private final static int numOfFragments = Values.numOfFragment;
    public static int numOfActivatedClasses = 0;
    private final String saveTag = "com.app.Sonny.save";
    private final String readTag = "com.app.Sonny.read";
    private final String debugTag = "com.app.Sonny.debug";
    private SeekBar mainSeekBar[] = new SeekBar[numOfClasses];
    private TextView selectGradeText[] = new TextView[numOfClasses];
    private TextView wGPAField, uGPAField;
    private Button addClassButton;
    private TextView[] vClassNameInput = new TextView[Values.numOfClasses];
    private TextView[] vCredits = new TextView[Values.numOfClasses];
    private TextView[] vClassLevel = new TextView[Values.numOfClasses];
    private ImageButton[] editImageButton = new ImageButton[Values.numOfClasses];
    private ImageButton[] trashImageButton = new ImageButton[Values.numOfClasses];
    private CardView vCardView[] = new CardView[Values.numOfClasses];
    private String grade[] = new String[numOfClasses];
    private int gradeCode[][] = new int[numOfFragments][numOfClasses];
    private double credits[][] = new double[numOfFragments][numOfClasses];
    private int buttonToggle[][] = new int[numOfFragments][numOfClasses];
    private boolean vClassVisibility[] = new boolean[numOfClasses];
    private LinearLayout mainAppLayout;
    private SharedPreferences reader;

    private String classContent[][]; //saved class information; recalled when readFile () is activated





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        Log.w(CONTEXT, "activity created");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("RHS GPA");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e62117")));


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            QuarterFragment fragment = new QuarterFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }


    }


    public String[] removeArrayElement(String[] a, int del) {
        LinkedList<String> linkedList = new LinkedList<String>(Arrays.asList(a));
        linkedList.remove(del);
        linkedList.add("");
        String[] b = new String[numOfClasses];

        for (int i = 0; i < numOfClasses; i++) {
            b[i] = linkedList.get(i);
        }


        return b;
    }

    public void reset() {
        SharedPreferences.Editor edit = reader.edit();
        edit.clear();
        edit.apply();
        //RESETS THE SHARED PREFERENCES

        for (int i = 0; i < numOfClasses; i++) {
            vCredits[i].setText("");
            vClassNameInput[i].setText("");
            selectGradeText[i].setText("");
            vCardView[i].setVisibility(View.GONE);
        }
        vClassLevel = null;
        buttonToggle = null;
        credits = null;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // saveFile();
        //Log.w(saveTag, "FILE SAVED??? (onPause)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //readFile();
        //Log.w(readTag, "onResume ()");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}


