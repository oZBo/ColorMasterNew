package braincollaboration.colormaster.activities;

import android.content.Intent;
import android.graphics.Color;
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
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_1_title), getString(R.string.tutorial_1_description), android.R.drawable.ic_media_play, Color.parseColor("#00BCD4")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_2_title), getString(R.string.tutorial_2_description), android.R.drawable.presence_away, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.tutorial_3_title), getString(R.string.tutorial_3_description), android.R.drawable.ic_delete, Color.parseColor("#5C6BC0")));

        // OPTIONAL METHODS
        // Override bar/separator color
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

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
