package com.braincollaboration.colormaster;

import android.app.Application;
import android.graphics.Typeface;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import cat.ppicas.customtypeface.CustomTypeface;
import io.fabric.sdk.android.Fabric;

/**
 * Created by eandreychenko on 27.07.2015.
 */
public class ColorMasterApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Answers(), new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        // Register a Typeface creating first the object, and then registering the object
        // with a name.
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main_font.ttf");
        CustomTypeface.getInstance().registerTypeface("main_font", typeface);
    }

}