package braincollaboration.colormaster;

import android.app.Application;
import android.graphics.Typeface;

import cat.ppicas.customtypeface.CustomTypeface;

/**
 * Created by eandreychenko on 27.07.2015.
 */
public class ColorMasterApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // Register a Typeface creating first the object, and then registering the object
        // with a name.
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font_2.ttf");
        CustomTypeface.getInstance().registerTypeface("main_font", typeface);
    }

}