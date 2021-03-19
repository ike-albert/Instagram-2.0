package com.example.instagram20;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {


    //Initialises Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();
        //Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zXnDUHPVORPkfGg4iZwU2H0y31v7Q1y92C6mluFc")
                .clientKey("xe7L5Y46Fjjoc6N1US8jGGHH6xnHeKCybGQx44ej")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
