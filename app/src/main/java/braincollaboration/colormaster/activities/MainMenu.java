package braincollaboration.colormaster.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import braincollaboration.colormaster.R;
import braincollaboration.colormaster.engine.GameMode;
import braincollaboration.colormaster.utils.Toaster;

/**
 * Main menu activity
 */
public class MainMenu extends Activity implements View.OnClickListener {

    private ImageButton btnGameDifficulty, btnHelp, btnMarkapp, btnPlay, btnLedaerboard, btnSounds;
    private Toaster toaster;
    private GameMode gameMode = GameMode.NORMAL;
    private boolean isSoundsOn = true;
//    private SoundManager soundManager; //TODO add soundManager

//    private EasyRatingDialog easyRatingDialog; //TODO add appRaterDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        toaster = Toaster.init(this);
//        soundManager = SoundManager.getInstance(this); //TODO SoundManager usage
        btnGameDifficulty = (ImageButton) findViewById(R.id.level_chooser_btn_difficalty);
        btnGameDifficulty.setOnClickListener(this);
        btnHelp = (ImageButton) findViewById(R.id.level_chooser_btn_help);
        btnHelp.setOnClickListener(this);
        btnMarkapp = (ImageButton) findViewById(R.id.level_chooser_btn_markapp);
        btnMarkapp.setOnClickListener(this);
        btnPlay = (ImageButton) findViewById(R.id.level_chooser_btn_play);
        btnPlay.setOnClickListener(this);
        btnLedaerboard = (ImageButton) findViewById(R.id.level_chooser_btn_leaderboard);
        btnLedaerboard.setOnClickListener(this);
        btnSounds = (ImageButton) findViewById(R.id.level_chooser_btn_sounds);
        btnSounds.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent nextActivity = null;
        switch (v.getId()) {
            case R.id.level_chooser_btn_difficalty:
                switch (gameMode) {
                    case NORMAL:
                        btnGameDifficulty.setImageResource(R.drawable.game_mode_mirror);
                        gameMode = GameMode.MIRRORED;
                        toaster.toast(getString(R.string.mode_mirror));
                        break;
                    case MIRRORED:
                        btnGameDifficulty.setImageResource(R.drawable.game_mode_normal);
                        gameMode = GameMode.NORMAL;
                        toaster.toast(getString(R.string.mode_normal));
                        break;
                }
                break;
            case R.id.level_chooser_btn_help:
                nextActivity = new Intent(this, Tutorial.class);
                nextActivity.putExtra(getString(R.string.pref_key_can_start_game_after_tutorial), false);
                startActivity(nextActivity);
                break;
            case R.id.level_chooser_btn_markapp:
                //TODO before publishing app uncomment "go to the market" functionality
//                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                } catch (android.content.ActivityNotFoundException anfe) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//                }
                break;
            case R.id.level_chooser_btn_play:
                nextActivity = new Intent(this, Tutorial.class);
                nextActivity.putExtra(getString(R.string.pref_key_game_mode), gameMode);
                startActivity(nextActivity);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.level_chooser_btn_leaderboard:
                //TODO add internet connection checker and leaderboard from Google play services
                break;
            case R.id.level_chooser_btn_sounds:
                //TODO add sound, vibration turn on/off functionality
                if (isSoundsOn) {
                    isSoundsOn = false;
                    btnSounds.setImageResource(R.drawable.selector_sound_off);
                } else {
                    isSoundsOn = true;
                    btnSounds.setImageResource(R.drawable.selector_sound_on);
                }
                break;
        }

    }
}
