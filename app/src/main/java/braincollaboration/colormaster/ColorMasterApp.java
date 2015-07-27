package braincollaboration.colormaster;

import android.app.Application;
import android.graphics.Typeface;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * Created by eandreychenko on 27.07.2015.
 */
public class ColorMasterApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceCollection typeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/font_3.ttf"))
                .create();
        TypefaceHelper.init(typeface);
    }

}