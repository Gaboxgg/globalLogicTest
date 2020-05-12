package com.example.globallogictest.utils;

import  androidx.annotation.StringRes;
import android.widget.Toast;

import com.example.globallogictest.GlobalLogicApp;


public class GeneralUtils {
    public static void showToast(@StringRes int resId){
        Toast.makeText(
                GlobalLogicApp.getMainContext(),
                resId,
                Toast.LENGTH_LONG
        ).show();
    }
}
