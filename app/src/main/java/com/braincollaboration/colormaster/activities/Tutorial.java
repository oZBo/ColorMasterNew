package com.braincollaboration.colormaster.activities;

import android.content.Intent;
import android.os.Bundle;

import com.braincollaboration.colormaster.LocaleCustom;
import com.braincollaboration.colormaster.R;
import com.braincollaboration.colormaster.utils.PreferenceUtil;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Game tutorial activity
 */
public class Tutorial extends AppIntro {

    private Intent gameLevel;
    private boolean canGoToGameActivity;

    @Override
    public void init(Bundle bundle) {
        LocaleCustom.setLocale(this);

        canGoToGameActivity = getIntent().getBooleanExtra(getString(R.string.pref_key_can_start_game_after_tutorial), true);
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_1_title), getString(R.string.tutorial_1_description), R.drawable.screenshot_tutorial_1, getResources().getColor(R.color.tutorial_1_bg)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_2_title), getString(R.string.tutorial_2_description), R.drawable.screenshot_tutorial_2, getResources().getColor(R.color.tutorial_2_bg)));
        addSlide(ColorLibrary.newInstance("colorLibrary"));
        // OPTIONAL METHODS
        // Override bar/separator color

        setBarColor(getResources().getColor(R.color.tutorial_bar_bg));
        setSeparatorColor(getResources().getColor(R.color.tutorial_separator));

        // Hide Skip/Done button|
        showSkipButton(false);
        showDoneButton(true);

        gameLevel = new Intent(this, GameLevel.class);
        gameLevel.putExtra(getString(R.string.pref_key_game_mode), getIntent().getSerializableExtra(getString(R.string.pref_key_game_mode)));
        if (getIntent().getBooleanExtra(MainMenu.IS_GOOGLE_GAMES_LOGED_IN, false)) {
            gameLevel.putExtra(MainMenu.IS_GOOGLE_GAMES_LOGED_IN, true);
        }

    }

    @Override
    public void onSkipPressed() {
        if (canGoToGameActivity)
            startActivity(gameLevel);
        finish();
    }

    @Override
    public void onDonePressed() {
        if (canGoToGameActivity) {
            startActivity(gameLevel);
            PreferenceUtil.putBoolean(this, getString(R.string.pref_key_can_show_tutorial), false);
        }
        finish();
    }
}
