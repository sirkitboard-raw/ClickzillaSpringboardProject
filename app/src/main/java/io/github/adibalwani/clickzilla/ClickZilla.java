package io.github.adibalwani.clickzilla;

import android.app.Application;
import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by Aditya on 8/31/2015.
*/
public class ClickZilla extends Application {
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, getString(R.string.ApplicationID), getString(R.string.ClientKey));
    }
}
