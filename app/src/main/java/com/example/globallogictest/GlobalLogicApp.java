package com.example.globallogictest;

import android.app.Application;
import android.content.Context;

public class GlobalLogicApp extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }

    public static Context getMainContext(){
        return application.getApplicationContext();
    }

    public static Application getApplication(){
        return application;
    }
}
