package braincollaboration.colormaster.engine;

import android.content.Context;

import java.util.Random;

public class Color {

    private static final int COLORS_NUMBER = 10; //Numbers of colors that used in game

    private int colorValue;
    private int layoutBackgroundColor;
    private String colorText;
    private boolean colorSameAsBackground;
    private int randomColor;
    private Context mContext;
    private Random random = new Random();

    public Color(Context mContext) {
        this.mContext = mContext;
        randomColor = random.nextInt(COLORS_NUMBER);
        colorSameAsBackground = random.nextBoolean();
        colorValue = ColorHelper.getColorValue(mContext, randomColor);
        colorText = ColorHelper.getColorName(mContext, randomColor);
        configureColors();
    }

    private void configureColors() {

        int tempLayoutColor = random.nextInt(COLORS_NUMBER);
        if (!colorSameAsBackground) {
            while (tempLayoutColor == randomColor)
                tempLayoutColor = random.nextInt(COLORS_NUMBER);
            layoutBackgroundColor = ColorHelper.getColorValue(mContext, tempLayoutColor);
        }else{
            layoutBackgroundColor = ColorHelper.getColorValue(mContext, randomColor);
        }

        int tempTextColor = random.nextInt(COLORS_NUMBER);
        while (tempTextColor == randomColor || tempTextColor == tempLayoutColor)
            tempTextColor = random.nextInt(COLORS_NUMBER);
        colorValue = ColorHelper.getColorValue(mContext, tempTextColor);
    }

    public int getColorValue() {
        return colorValue;
    }

    public String getColorText() {
        return colorText;
    }

    public int getLayoutBackgroundColor() {
        return layoutBackgroundColor;
    }

    public boolean isColorSameAsBackground() {
        return colorSameAsBackground;
    }

}
