package braincollaboration.colormaster.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import braincollaboration.colormaster.R;
import braincollaboration.colormaster.engine.GameMode;
import cat.ppicas.customtypeface.CustomTypeface;
import cat.ppicas.customtypeface.CustomTypefaceFactory;

/**
 * Main menu activity
 */
public class MainMenu extends Activity implements View.OnClickListener {

    private Button btnLevelModeNormal, btnLevelModeMirrored;
    private ImageButton btnGameDifficulty, btnHelp, btnMarkapp, btnPlay, btnLedaerboard, btnSounds;
    private GameMode gameMode = GameMode.NORMAL;
    private boolean isSoundsOn = true;
//    private SoundManager soundManager; //TODO add soundManager
//    private EasyRatingDialog easyRatingDialog; //TODO add appRaterDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new CustomTypefaceFactory(this, CustomTypeface.getInstance()));
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_menu);
        initViews();
//        soundManager = SoundManager.getInstance(this); //TODO SoundManager usage

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkGameMode();
    }

    private void initViews() {
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
        btnLevelModeNormal = (Button) findViewById(R.id.btn_level_mode_normal);
        btnLevelModeNormal.setOnClickListener(this);
        btnLevelModeMirrored = (Button) findViewById(R.id.btn_level_mode_mirrored);
        btnLevelModeMirrored.setOnClickListener(this);
    }

    //Checking gameMode and changing buttons backgrounds
    private void checkGameMode() {
        switch (gameMode) {
            case NORMAL:
                btnLevelModeNormal.setBackgroundResource(R.drawable.main_menu_game_mode_pressed);
                btnLevelModeMirrored.setBackgroundResource(R.drawable.main_menu_game_mode_normal);
                break;
            case MIRRORED:
                btnLevelModeNormal.setBackgroundResource(R.drawable.main_menu_game_mode_normal);
                btnLevelModeMirrored.setBackgroundResource(R.drawable.main_menu_game_mode_pressed);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent nextActivity = null;
        switch (v.getId()) {
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
            case R.id.btn_level_mode_normal:
                btnLevelModeNormal.setBackgroundResource(R.drawable.main_menu_game_mode_pressed);
                btnLevelModeMirrored.setBackgroundResource(R.drawable.main_menu_game_mode_normal);
                gameMode = GameMode.NORMAL;
                break;
            case R.id.btn_level_mode_mirrored:
                btnLevelModeNormal.setBackgroundResource(R.drawable.main_menu_game_mode_normal);
                btnLevelModeMirrored.setBackgroundResource(R.drawable.main_menu_game_mode_pressed);
                gameMode = GameMode.MIRRORED;
                break;
        }

    }
}
