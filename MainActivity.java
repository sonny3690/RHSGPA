package com.app.sonny.rhsgpa;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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

import com.app.sonny.rhsgpa.slidingtab.SlidingTabLayout;

import java.util.Arrays;
import java.util.LinkedList;


public class MainActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

    public static final String CONTEXT = "com.app.Sonny";

    private final static int numOfClasses = 7; //number of total classes the person is taking
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
    private int gradeCode[] = new int[numOfClasses];
    private double credits[] = new double[numOfClasses];
    private int buttonToggle[] = new int[numOfClasses];
    private boolean vClassVisibility[] = new boolean[numOfClasses];
    private LinearLayout mainAppLayout;
    private SharedPreferences reader;

    private String classContent[][]; //saved class information; recalled when readFile () is activated

    ViewPager pager;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Quarter 1",  "Quarter 2", "Quarter 3"};
    int Numboftabs = Titles.length;




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

    public void init() {


        mainAppLayout = (LinearLayout) findViewById(R.id.overAllLayout);
        mainAppLayout.requestFocus(); //removes focus from all elements

        reader = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //mainSeekBar[0] = (SeekBar) findViewById(R.id.mainSeekBar0);
        //selectGradeText[0] = (TextView) findViewById(R.id.selectGradeText0);

        wGPAField = (TextView) findViewById(R.id.wGPA);
        //wGPAField = (TextView) findViewById(Integer.parseInt("R.id.wGPA"));
        uGPAField = (TextView) findViewById(R.id.uGPA);

        credits = new double[numOfClasses];
        classContent = new String[numOfClasses][4]; //1 for class name, 2 for gradecode, 3 for credits, 4 for duration

//        addClassButton = (Button) findViewById(R.id.addClassButton);
       /* addClassButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CourseMenu.class);
                intent.putExtra(Values.CLASSINDEXTAG, String.valueOf(numOfActivatedClasses));
                startActivity(intent);


            }
        });*/


    }




    public void readFile() {
        String generalTemp = reader.getString(Values.GENERALSAVEDINFO, null);

        try {
            numOfActivatedClasses = Integer.parseInt(generalTemp);
            Log.w(CONTEXT, generalTemp);
        } catch (Exception e) {
            numOfActivatedClasses = 0;
        }


        for (int i = 0; i < numOfActivatedClasses; i++) {
            vCardView[i].setVisibility(View.VISIBLE);
        }

        String separated[] = new String[4];
        String[] fragTemp = new String[numOfClasses];

        for (int i = 0; i < numOfClasses; i++) {
            fragTemp[i] = reader.getString(Values.FRAGMENTCODE[i], null); //EACH  CLASS INFORMATION}
        }

        for (int i = 0; i < numOfClasses; i++) {
            try {
                separated = fragTemp[i].split("_");
            } catch (Exception e) {
                return;
            }
            if (separated[0] == null) {
                return;
            }


            int index = i;

            vClassNameInput[index].setText(separated[0]);
            //mainSeekBar[index].setProgress(Integer.parseInt(separated[1]));
            try {
                gradeCode[index] = Integer.parseInt(separated[1]);
                mainSeekBar[index].setProgress(Integer.parseInt(separated[1]));
            } catch (NumberFormatException e) {

            }
            vCredits[index].setText("Credits: " + separated[2]);
            credits[index] = Double.parseDouble(separated[2]);
            //Log.w(readTag, "separated[2]: "  +separated[2]);
            switch (Integer.parseInt(separated[3])) {
                case 0:
                    vClassLevel[index].setText("College Prep");
                    break;
                case 1:
                    vClassLevel[index].setText("Honors");
                    break;
                case 2:
                    vClassLevel[index].setText("AP");
                    break;
            }

            buttonToggle[index] = Integer.parseInt(separated[3]);

            //buttonToggle[index] = Integer.parseInt(separated[1]);
        }

        try {
            String s[] = new String[4];
            // s = fragTemp.split("_");
            mainSeekBar[0].setProgress(Integer.parseInt(s[1]) - 1);


        } catch (Exception e) {
            Log.w("MainActivity", "gradCode exception thrown?");
        }


    }

    public void saveFile() {

        SharedPreferences.Editor editor = reader.edit();
        editor.putString(Values.GENERALSAVEDINFO, String.valueOf(numOfActivatedClasses));

        for (int i = 0; i < Values.numOfClasses; i++) {

            String className = vClassNameInput[i].getText().toString();
            gradeCode[i] = mainSeekBar[i].getProgress();

            String[] temp = new String[3];

            temp = vCredits[i].getText().toString().split(":");

            if (temp[0].equals("Credits")) {
                credits[i] = Double.parseDouble(temp[1]);
            } else credits[i] = 0;

            switch (vClassLevel[i].getText().toString()) {
                case "College Prep":
                    buttonToggle[i] = 0;
                    break;
                case "Honors":
                    buttonToggle[i] = 1;
                    break;
                case "AP":
                    buttonToggle[i] = 2;
                    break;
            }

            String saveInfo = className + "_" + gradeCode[i] + "_" + credits[i] + "_" + buttonToggle[i];
            editor.putString(Values.FRAGMENTCODE[i], saveInfo);

            Log.w(saveTag, "SAVED:" + Values.FRAGMENTCODE[i] + ", " + saveInfo);
            //editor.commit();

        }

        editor.apply();
    }

    public void removeCardElements(int i) {
        String creditsTemp[] = new String[numOfClasses];
        String classNameTemp[] = new String[numOfClasses];
        String classLevelTemp[] = new String[numOfClasses];
        String gradeCodeTemp[] = new String[numOfClasses];

        for (int j = 0; j < numOfClasses; j++) {
            creditsTemp[j] = vCredits[j].getText().toString();
            classNameTemp[j] = vClassNameInput[j].getText().toString();
            classLevelTemp[j] = vClassLevel[j].getText().toString();
            gradeCodeTemp[j] = String.valueOf(mainSeekBar[j].getProgress());
        }
        //Log.w(CONTEXT, "Num of Activated Classes: \t"  + String.valueOf(numOfActivatedClasses));
        creditsTemp = removeArrayElement(creditsTemp, i);

        for (int j = 0; j < numOfClasses; j++) {
            Log.w(CONTEXT, "CREDITS TEMP #" + j + ":\t" + creditsTemp[j]);
        }

        classNameTemp = removeArrayElement(classNameTemp, i);
        classLevelTemp = removeArrayElement(classLevelTemp, i); //SHIFTS ALL THE VALUES
        gradeCodeTemp = removeArrayElement(gradeCodeTemp, i);

        for (int j = 0; j < numOfActivatedClasses; j++) {
            vCredits[j].setText(creditsTemp[j]);
            vClassNameInput[j].setText(classNameTemp[j]);
            vClassLevel[j].setText(classLevelTemp[j]);
            mainSeekBar[j].setProgress(Integer.parseInt(gradeCodeTemp[j])); //may throw numberformaterror?
        }//SHOULD ALL SET THE VARIABLES

        vCardView[numOfActivatedClasses - 1].setVisibility(View.GONE); //removes the last element
        numOfActivatedClasses--;

        saveFile();

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


