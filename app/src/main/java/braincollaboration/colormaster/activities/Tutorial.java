package braincollaboration.colormaster.activities;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import braincollaboration.colormaster.R;
import braincollaboration.colormaster.utils.PreferenceUtil;

/**
 * Game tutorial activity
 */
public class Tutorial extends AppIntro {

    private Intent gameLevel;
    private boolean canGoToGameActivity;

    @Override
    public void init(Bundle bundle) {

        canGoToGameActivity = getIntent().getBooleanExtra(getString(R.string.pref_key_can_start_game_after_tutorial), true);
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_1_title), getString(R.string.tutorial_1_description), R.drawable.dummy_screenshot_1, getResources().getColor(R.color.tutorial_1_bg)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_2_title), getString(R.string.tutorial_2_description), R.drawable.dummy_screenshot_1, getResources().getColor(R.color.tutorial_2_bg)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_3_title), getString(R.string.tutorial_3_description), R.drawable.dummy_screenshot_1, getResources().getColor(R.color.tutorial_3_bg)));

        // OPTIONAL METHODS
        // Override bar/separator color
        setBarColor(getResources().getColor(R.color.tutorial_bar_bg));
        setSeparatorColor(getResources().getColor(R.color.tutorial_separator));

        // Hide Skip/Done button|
        showSkipButton(false);
        showDoneButton(true);

        gameLevel = new Intent(this, GameLevel.class);
        gameLevel.putExtra(getString(R.string.pref_key_game_mode), getIntent().getSerializableExtra(getString(R.string.pref_key_game_mode)));

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
