package com.app.sonny.rhsgpa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.sonny.rhsgpa.slidingtab.SlidingTabLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Sonny on 11/22/2015.
 */
public class ViewModeAdapter {

    public static final String CONTEXT = "com.app.Sonny";
    private final static int numOfClasses = 7; //number of total classes the person is taking
    public static int numOfActivatedClasses = 0;
    private final String saveTag = "com.app.Sonny.save";
    private final String readTag = "com.app.Sonny.read";
    private final String debugTag = "com.app.Sonny.debug";
    View view;
    ViewPager pager;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Quarter 1", "Quarter 2", "Quarter 3"};
    int Numboftabs = Titles.length;
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
    private int fragNum;
    private String classContent[][]; //saved class information; recalled when readFile () is activated

    public ViewModeAdapter (LayoutInflater inflater, ViewGroup container, boolean bundle, int fragNum){
        view = inflater.inflate(R.layout.view_mode, container, bundle);

        this.fragNum = fragNum;
        init ();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void init (){

        // readFile();


        vClassNameInput[0] = (TextView) view.findViewById(R.id.vClassNameInput0);
        vClassNameInput[1] = (TextView) view.findViewById(R.id.vClassNameInput1);
        vClassNameInput[2] = (TextView) view.findViewById(R.id.vClassNameInput2);
        vClassNameInput[3] = (TextView) view.findViewById(R.id.vClassNameInput3);
        vClassNameInput[4] = (TextView) view.findViewById(R.id.vClassNameInput4);
        vClassNameInput[5] = (TextView) view.findViewById(R.id.vClassNameInput5);
        vClassNameInput[6] = (TextView) view.findViewById(R.id.vClassNameInput6);
        //vClassNameInput[7] = (TextView) view.findViewById(R.id.vClassNameInput7);

        vCredits[0] = (TextView) view.findViewById(R.id.vCredits0);
        vCredits[1] = (TextView) view.findViewById(R.id.vCredits1);
        vCredits[2] = (TextView) view.findViewById(R.id.vCredits2);
        vCredits[3] = (TextView) view.findViewById(R.id.vCredits3);
        vCredits[4] = (TextView) view.findViewById(R.id.vCredits4);
        vCredits[5] = (TextView) view.findViewById(R.id.vCredits5);
        vCredits[6] = (TextView) view.findViewById(R.id.vCredits6);
        //vCredits[7] = (TextView) view.findViewById(R.id.vCredits7);

        vClassLevel[0] = (TextView) view.findViewById(R.id.vClassLevel0);
        vClassLevel[1] = (TextView) view.findViewById(R.id.vClassLevel1);
        vClassLevel[2] = (TextView) view.findViewById(R.id.vClassLevel2);
        vClassLevel[3] = (TextView) view.findViewById(R.id.vClassLevel3);
        vClassLevel[4] = (TextView) view.findViewById(R.id.vClassLevel4);
        vClassLevel[5] = (TextView) view.findViewById(R.id.vClassLevel5);
        vClassLevel[6] = (TextView) view.findViewById(R.id.vClassLevel6);
        //vClassLevel[7] = (TextView) view.findViewById(R.id.vClassLevel7);

        selectGradeText[0] = (TextView) view.findViewById(R.id.selectGradeText0);
        selectGradeText[1] = (TextView) view.findViewById(R.id.selectGradeText1);
        selectGradeText[2] = (TextView) view.findViewById(R.id.selectGradeText2);
        selectGradeText[3] = (TextView) view.findViewById(R.id.selectGradeText3);
        selectGradeText[4] = (TextView) view.findViewById(R.id.selectGradeText4);
        selectGradeText[5] = (TextView) view.findViewById(R.id.selectGradeText5);
        selectGradeText[6] = (TextView) view.findViewById(R.id.selectGradeText6);
        //selectGradeText[7] = (TextView) view.findViewById(R.id.selectGradeText7);

        mainSeekBar[0] = (SeekBar) view.findViewById(R.id.mainSeekBar0);
        mainSeekBar[1] = (SeekBar) view.findViewById(R.id.mainSeekBar1);
        mainSeekBar[2] = (SeekBar) view.findViewById(R.id.mainSeekBar2);
        mainSeekBar[3] = (SeekBar) view.findViewById(R.id.mainSeekBar3);
        mainSeekBar[4] = (SeekBar) view.findViewById(R.id.mainSeekBar4);
        mainSeekBar[5] = (SeekBar) view.findViewById(R.id.mainSeekBar5);
        mainSeekBar[6] = (SeekBar) view.findViewById(R.id.mainSeekBar6);
        //mainSeekBar[7] = (SeekBar) view.findViewById(R.id.mainSeekBar7);

        editImageButton[0] = (ImageButton) view.findViewById(R.id.editImageButton0);
        editImageButton[1] = (ImageButton) view.findViewById(R.id.editImageButton1);
        editImageButton[2] = (ImageButton) view.findViewById(R.id.editImageButton2);
        editImageButton[3] = (ImageButton) view.findViewById(R.id.editImageButton3);
        editImageButton[4] = (ImageButton) view.findViewById(R.id.editImageButton4);
        editImageButton[5] = (ImageButton) view.findViewById(R.id.editImageButton5);
        editImageButton[6] = (ImageButton) view.findViewById(R.id.editImageButton6);
        //editImageButton[7] = (ImageButton) view.findViewById(R.id.editImageButton7);

        trashImageButton[0] = (ImageButton) view.findViewById(R.id.trashImageButton0);
        trashImageButton[1] = (ImageButton) view.findViewById(R.id.trashImageButton1);
        trashImageButton[2] = (ImageButton) view.findViewById(R.id.trashImageButton2);
        trashImageButton[3] = (ImageButton) view.findViewById(R.id.trashImageButton3);
        trashImageButton[4] = (ImageButton) view.findViewById(R.id.trashImageButton4);
        trashImageButton[5] = (ImageButton) view.findViewById(R.id.trashImageButton5);
        trashImageButton[6] = (ImageButton) view.findViewById(R.id.trashImageButton6);
        //trashImageButton[7] = (ImageButton) view.findViewById(R.id.trashImageButton7);

        vCardView[0] = (CardView) view.findViewById(R.id.cardView0);
        vCardView[1] = (CardView) view.findViewById(R.id.cardView1);
        vCardView[2] = (CardView) view.findViewById(R.id.cardView2);
        vCardView[3] = (CardView) view.findViewById(R.id.cardView3);
        vCardView[4] = (CardView) view.findViewById(R.id.cardView4);
        vCardView[5] = (CardView) view.findViewById(R.id.cardView5);
        vCardView[6] = (CardView) view.findViewById(R.id.cardView6);


        vClassNameInput[0].setText ("2332");

        for (int i = 0; i < numOfClasses; i++) {
            setSeekBar(mainSeekBar[i], 11, i);
            if (i > numOfActivatedClasses)
            vCardView[i].setVisibility(View.GONE);
            trashImageButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < numOfClasses; i++) {
                        if (trashImageButton[i] == view)
                            // vCardView[i].setVisibility(View.GONE);
                            removeCardElements(i);
                        Log.w("YAYA", "YAYYY");


                    }
                }
            });
        }

        reader = PreferenceManager.getDefaultSharedPreferences(view.getContext().getApplicationContext());
        //mainSeekBar[0] = (SeekBar) findViewById(R.id.mainSeekBar0);
        //selectGradeText[0] = (TextView) findViewById(R.id.selectGradeText0);

        wGPAField = (TextView) view.findViewById(R.id.wGPA);
        //wGPAField = (TextView) findViewById(Integer.parseInt("R.id.wGPA"));
        uGPAField = (TextView) view.findViewById(R.id.uGPA);

        credits = new double[numOfClasses];
        classContent = new String[numOfClasses][4]; //1 for class name, 2 for gradecode, 3 for credits, 4 for duration

        addClassButton = (Button) view.findViewById(R.id.addClassButton);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CourseMenu.class);
                intent.putExtra(Values.CLASSINDEXTAG, String.valueOf(fragNum) + ":" + numOfActivatedClasses);
                view.getContext().startActivity(intent);
                numOfActivatedClasses++;


            }
        });



    }

    public void setSeekBar(SeekBar seekbar, int max, final int i) {
        seekbar.setMax(max);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar == mainSeekBar[i]) {
                    Log.w(CONTEXT, "SeekBar #" + i + ": FOUND");
                    switch (seekBar.getProgress()) {
                        case 0:
                            grade[i] = "E";
                            gradeCode[i] = 1;
                            break;
                        case 1:
                            grade[i] = "D-";
                            gradeCode[i] = 2;
                            break;
                        case 2:
                            grade[i] = "D";
                            gradeCode[i] = 3;
                            break;
                        case 3:
                            grade[i] = "D+";
                            gradeCode[i] = 4;
                            break;
                        case 4:
                            grade[i] = "C-";
                            gradeCode[i] = 5;
                            break;
                        case 5:
                            grade[i] = "C";
                            gradeCode[i] = 6;
                            break;
                        case 6:
                            grade[i] = "C+";
                            gradeCode[i] = 7;
                            break;
                        case 7:
                            grade[i] = "B-";
                            gradeCode[i] = 8;
                            break;
                        case 8:
                            grade[i] = "B";
                            gradeCode[i] = 9;
                            break;
                        case 9:
                            grade[i] = "B+";
                            gradeCode[i] = 10;
                            break;
                        case 10:
                            grade[i] = "A-";
                            gradeCode[i] = 11;
                            break;
                        case 11:
                            grade[i] = "A";
                            gradeCode[i] = 12;
                            break;
                    }

                    selectGradeText[i].setText(grade[i]);
                }


                calculateGPA(gradeCode, credits, buttonToggle, true);
                calculateGPA(gradeCode, credits, buttonToggle, false);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calculateGPA(int gradeCode[], double credits[], int classLevel[], boolean weighted) {

        double totalCredits = 0;
        double totalValue = 0;
        int gradePoint = 0;

        for (int i = 0; i < numOfActivatedClasses; i++) {
            double arbValue = 0;
            if (classLevel[i] == 0 || !weighted) {//CP
                // Log.w(debugTag, "cp");
                switch (gradeCode[i]) {
                    case 1:
                        arbValue = 0;
                        break;
                    case 2:
                        arbValue = 0.67;
                        break;
                    case 3:
                        arbValue = 1D;
                        break;
                    case 4:
                        arbValue = 1.33;
                        break;
                    case 5:
                        arbValue = 1.67;
                        break;
                    case 6:
                        arbValue = 2D;
                        break;
                    case 7:
                        arbValue = 2.33;
                        break;
                    case 8:
                        arbValue = 2.67;
                        break;
                    case 9:
                        arbValue = 3D;
                        break;
                    case 10:
                        arbValue = 3.33;
                        break;
                    case 11:
                        arbValue = 3.67;
                        break;
                    case 12:
                        arbValue = 4D;
                        break;
                }
            } else if (classLevel[i] == 1 && weighted) { //HONORS
                //Log.w(debugTag, "honors");
                switch (gradeCode[i]) {
                    case 1:
                        arbValue = 0;
                        break;
                    case 2:
                        arbValue = 1.17;
                        break;
                    case 3:
                        arbValue = 1.5;
                        break;
                    case 4:
                        arbValue = 1.83;
                        break;
                    case 5:
                        arbValue = 2.17;
                        break;
                    case 6:
                        arbValue = 2.5;
                        break;
                    case 7:
                        arbValue = 2.83;
                        break;
                    case 8:
                        arbValue = 3.17;
                        break;
                    case 9:
                        arbValue = 3.5;
                        break;
                    case 10:
                        arbValue = 3.83;
                        break;
                    case 11:
                        arbValue = 4.17;
                        break;
                    case 12:
                        arbValue = 4.5;
                        break;
                }

            } else if (classLevel[i] == 2 && weighted) {//AP
                Log.w(debugTag, "ap");
                switch (gradeCode[i]) {
                    case 1:
                        arbValue = 0;
                        break;
                    case 2:
                        arbValue = 1.67;
                        break;
                    case 3:
                        arbValue = 2D;
                        break;
                    case 4:
                        arbValue = 2.33;
                        break;
                    case 5:
                        arbValue = 2.67;
                        break;
                    case 6:
                        arbValue = 3D;
                        break;
                    case 7:
                        arbValue = 3.33;
                        break;
                    case 8:
                        arbValue = 3.67;
                        break;
                    case 9:
                        arbValue = 4D;
                        break;
                    case 10:
                        arbValue = 4.33;
                        break;
                    case 11:
                        arbValue = 4.67;
                        break;
                    case 12:
                        arbValue = 5D;
                        break;
                }
            } else {
                //Log.w(debugTag, "MISCELLANEOUS");

            }

            totalValue += arbValue * credits[i];
            Log.w(CONTEXT, "Total Value: " + String.valueOf(totalValue));
            totalCredits += credits[i];

            double gpa = round(totalValue / totalCredits, 2); //totalValue/totalCredits;

            if (weighted) {
                Values.GPA[0] = gpa;
                wGPAField.setText(String.valueOf(gpa));
            } else {
                Values.GPA[1] = gpa;
                uGPAField.setText(String.valueOf(gpa));
            }
        }
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


        //TODO: CHECK THIS LINE
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
            editor.putString(Values.FRAGMENTCODE[fragNum], saveInfo);

            Log.w(saveTag, "SAVED:" + Values.FRAGMENTCODE[fragNum] + ", " + saveInfo);
            //editor.commit();

        }

        editor.apply();
    }


    public View getView (){
        return view;
    }


    //TODO: finish addClass () function
    //// TODO: 11/22/2015  
    //// TODO: 11/22/2015 make adding classes smoother (all in this class) 
    
     
}
