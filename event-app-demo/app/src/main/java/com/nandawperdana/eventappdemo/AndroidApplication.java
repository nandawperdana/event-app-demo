package com.nandawperdana.eventappdemo;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by nandawperdana.
 */
public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Realm
        Realm.init(this);
    }
}
