package com.braincollaboration.colormaster.engine;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.braincollaboration.colormaster.R;

import java.util.Random;

class ColorHelper {

    private static Random random = new Random();

    public static String getColorName(Context mContext, int colorNumber) {
        switch (colorNumber) {
            case 0:
                return mContext.getResources().getString(R.string.color_red);
            case 1:
                return mContext.getResources().getString(R.string.color_pink);
            case 2:
                return mContext.getResources().getString(R.string.color_purple);
            case 3:
                return mContext.getResources().getString(R.string.color_indigo);
            case 4:
                return mContext.getResources().getString(R.string.color_blue);
            case 5:
                return mContext.getResources().getString(R.string.color_turquoise);
            case 6:
                return mContext.getResources().getString(R.string.color_green);
            case 7:
                return mContext.getResources().getString(R.string.color_yellow);
            case 8:
                return mContext.getResources().getString(R.string.color_orange);
            case 9:
                return mContext.getResources().getString(R.string.color_brown);
            default:
                return "WTF???";
        }
    }

    public static int getColorValue(Context mContext, int colorNumber) {
        switch (colorNumber) {
            case 0:
                return mContext.getResources().getColor(R.color.red);
            case 1:
                return mContext.getResources().getColor(R.color.pink);
            case 2:
                return mContext.getResources().getColor(R.color.purple);
            case 3:
                return mContext.getResources().getColor(R.color.indigo);
            case 4:
                return mContext.getResources().getColor(R.color.blue);
            case 5:
                return mContext.getResources().getColor(R.color.turquoise);
            case 6:
                return mContext.getResources().getColor(R.color.green);
            case 7:
                return mContext.getResources().getColor(R.color.yellow);
            case 8:
                return mContext.getResources().getColor(R.color.orange);
            case 9:
                return mContext.getResources().getColor(R.color.brown);
            default:
                return 505;
        }
    }
        //TODO add dependency between color and background
    public static Drawable getColorImageBackground(Context mContext, int colorNumber) {
        int randomSeedBackground = random.nextInt(5);
            switch (colorNumber){
                case 0:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.red_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.red_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.red_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.red_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.red_5_tiled);
                    }
                case 1:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.pink_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.pink_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.pink_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.pink_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.pink_5_tiled);
                    }
                case 2:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.purple_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.purple_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.purple_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.purple_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.purple_5_tiled);
                    }
                case 3:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.indigo_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.indigo_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.indigo_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.indigo_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.indigo_5_tiled);
                    }
                case 4:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.blue_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.blue_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.blue_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.blue_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.blue_5_tiled);
                    }
                case 5:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.turquoise_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.turquoise_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.turquoise_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.turquoise_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.turquoise_5_tiled);
                    }
                case 6:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.green_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.green_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.green_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.green_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.green_5_tiled);
                    }
                case 7:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.yellow_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.yellow_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.yellow_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.yellow_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.yellow_5_tiled);
                    }
                case 8:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.orange_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.orange_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.orange_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.orange_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.orange_5_tiled);
                    }
                case 9:
                    switch(randomSeedBackground){
                        case 0:
                            return mContext.getResources().getDrawable(R.drawable.brown_1_tiled);
                        case 1:
                            return mContext.getResources().getDrawable(R.drawable.brown_2_tiled);
                        case 2:
                            return mContext.getResources().getDrawable(R.drawable.brown_3_tiled);
                        case 3:
                            return mContext.getResources().getDrawable(R.drawable.brown_4_tiled);
                        case 4:
                            return mContext.getResources().getDrawable(R.drawable.brown_5_tiled);
                    }
                default:
                    return null;
            }
    }
}

