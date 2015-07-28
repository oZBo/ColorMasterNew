package braincollaboration.colormaster.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import braincollaboration.colormaster.R;

/**
 * Game tutorial activity
 */
public class Tutorial extends AppIntro {

    Intent gameLevel;

    @Override
    public void init(Bundle bundle) {


        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance("Tutorial 1", "asdad sg wae as dcvf sav d asv", R.drawable.screenshot_1, Color.parseColor("#00BCD4")));
        addSlide(AppIntroFragment.newInstance("Tutorial 2", "asdad sg wae as dcvf sav d asv", R.drawable.screenshot_1, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("Tutorial 3", "asdad sg wae as dcvf sav d asv", R.drawable.screenshot_1, Color.parseColor("#5C6BC0")));

        // OPTIONAL METHODS
        // Override bar/separator color
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button
        showSkipButton(true);
        showDoneButton(true);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permesssion in Manifest
        setVibrate(true);
        setVibrateIntensity(30);

        gameLevel = new Intent(this, GameLevel.class);
        gameLevel.putExtra(getString(R.string.pref_key_game_mode), getIntent().getSerializableExtra(getString(R.string.pref_key_game_mode)));
    }

    @Override
    public void onSkipPressed() {
        startActivity(gameLevel);
        finish();
    }

    @Override
    public void onDonePressed() {
        startActivity(gameLevel);
        finish();
    }
}
