package com.braincollaboration.colormaster.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by eandreychenko on 29.07.2015.
 */
public class VibratorManager {

    private static VibratorManager me;
    private Context context;

    Vibrator v = null;

    private Vibrator getVibrator(){
        if(v == null){
            v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
        return v;
    }

    public static VibratorManager getManager(Context context) {
        if(me == null){
            me = new VibratorManager();
        }
        me.setContext(context);
        return me;
    }

    private void setContext(Context context){
        this.context = context;
    }

    public void vibrate(long pattern){
        me.getVibrator().vibrate(pattern);
    }
}