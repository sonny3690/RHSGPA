package com.app.sonny.rhsgpa;

import android.widget.TextView;

/**
 * Created by Caesar1Password-abc on 7/31/2015.
 */
public class Values {
    public static final String ERRORTAG = "com.sonny.ERROR";
    public static final String CLASSTAG = "CLASSSELECTION";
    public static final String CLASSINDEXTAG = "CLASSINDEXTAG";
    public static final String GENERALSAVEDINFO = "GENERALSAVEDINFO";
    public static final int numOfClasses = 7;
    public static final  String [] FRAGMENTCODE = new String [7];
    public static final int [] gradeCode = new int[numOfClasses];
    public static final int cpButton [] = {R.id.collegePrepButton0, R.id.collegePrepButton1};
    public static final  int honorsButton [] = {R.id.honorsButton0, R.id.honorsButton1};
    public static final int APButton [] = {R.id.APButton0, R.id.APButton1};
    public static final double []  GPA  = new double[2]; //weighted then uw
    public static final String EDITTAG = "com.sonny.EDITCLASS";

    public static TextView vClassNameInput [] = new TextView[numOfClasses];



    public Values (){
        for (int i =0; i< numOfClasses; i++){
            FRAGMENTCODE [i] = String.valueOf(i);
        }



    }

}


