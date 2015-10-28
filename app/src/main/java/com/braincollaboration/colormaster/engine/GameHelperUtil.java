package com.braincollaboration.colormaster.engine;

import android.content.Context;
import android.content.Intent;

import com.braincollaboration.colormaster.R;
import com.braincollaboration.colormaster.utils.PreferenceUtil;

import java.util.Random;

public class GameHelperUtil {

    /**
     * @param score
     * @return time in millis for level, according to game score.
     */
    public static int getTimeForLevel(int score) {

        if (score <= 5 && score >= 0) {
            return 8000;
        } else if (score <= 10 && score > 5) {
            return 7500;
        } else if (score <= 15 && score > 10) {
            return 7000;
        } else if (score <= 20 && score > 15) {
            return 6000;
        } else if (score <= 25 && score > 20) {
            return 5000;
        } else if (score <= 30 && score > 25) {
            return 4000;
        } else if (score <= 35 && score > 30) {
            return 4400;
        } else if (score <= 40 && score > 35) {
            return 4000;
        } else if (score <= 45 && score > 40) {
            return 3000;
        } else if (score <= 50 && score > 45) {
            return 2500;
        } else if (score <= 55 && score > 50) {
            return 2000;
        } else if (score <= 60 && score > 55) {
            return 2000;
        } else if (score <= 65 && score > 60) {
            return 2000;
        } else if (score <= 70 && score > 65) {
            return 1500;
        }
        return 1300;
    }

    private static Random rnd = new Random();

    public static boolean getRandomBoolean() {
        return rnd.nextBoolean();
    }

    public static void saveBestScore(Context context, GameMode gameMode, int score){
        switch(gameMode){
            case NORMAL:
                PreferenceUtil.putInt(context, context.getString(R.string.pref_key_game_mode_normal), score);
                break;
            case MIRRORED:
                PreferenceUtil.putInt(context, context.getString(R.string.pref_key_game_mode_mirror), score);
                break;
        }
    }

    public static int loadBestScore(Context context, GameMode gameMode){
        switch(gameMode){
            case NORMAL:
                return PreferenceUtil.getInt(context, context.getString(R.string.pref_key_game_mode_normal), 0);
            case MIRRORED:
                return PreferenceUtil.getInt(context, context.getString(R.string.pref_key_game_mode_mirror), 0);
            default:
                return 0;
        }
    }

    public static void shareScore(Context context, String body){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(sharingIntent, null));
    }
}
