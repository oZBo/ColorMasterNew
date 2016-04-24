package com.braincollaboration.colormaster;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Temporary class for debug mode. Removed for production
 */
public class LocaleCustom {

    //TODO Temporary class for debug mode. Removed for production
    public static void setLocale(Context ctx){
        if(BuildConfig.DEBUG) {
            Resources res = ctx.getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("ru".toLowerCase());
            res.updateConfiguration(conf, dm);
        }
    }
}
