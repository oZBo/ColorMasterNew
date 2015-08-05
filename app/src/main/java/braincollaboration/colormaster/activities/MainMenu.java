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
import braincollaboration.colormaster.utils.PreferenceUtil;
import braincollaboration.colormaster.utils.SoundManager;
import cat.ppicas.customtypeface.CustomTypeface;
import cat.ppicas.customtypeface.CustomTypefaceFactory;

/**
 * Main menu activity
 */
public class MainMenu extends Activity implements View.OnClickListener {

    private Button btnLevelModeNormal, btnLevelModeMirrored;
    private ImageButton btnGameDifficulty, btnHelp, btnMarkapp, btnPlay, btnLedaerboard, btnSounds;
    private GameMode gameMode = GameMode.NORMAL;
    private SoundManager soundManager;
//    private EasyRatingDialog easyRatingDialog; //TODO add appRaterDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new CustomTypefaceFactory(this, CustomTypeface.getInstance()));
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        soundManager = SoundManager.getInstance(this);
        setContentView(R.layout.main_menu);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkGameMode();
        checkSoundState();
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

    //Checking state of the sound, on or off
    private void checkSoundState() {
        if (PreferenceUtil.getBoolean(this, getString(R.string.pref_key_is_sound_on), true)) {
            btnSounds.setImageResource(R.drawable.selector_sound_on);
        } else {
            btnSounds.setImageResource(R.drawable.selector_sound_off);
        }
    }

    @Override
    public void onClick(View v) {
        Intent nextActivity = null;
        switch (v.getId()) {
            case R.id.level_chooser_btn_help:
                soundManager.play(R.raw.menu_click);
                nextActivity = new Intent(this, Tutorial.class);
                nextActivity.putExtra(getString(R.string.pref_key_can_start_game_after_tutorial), false);
                startActivity(nextActivity);
                break;
            case R.id.level_chooser_btn_markapp:
                soundManager.play(R.raw.menu_click);
                //TODO before publishing app uncomment "go to the market" functionality
//                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                } catch (android.content.ActivityNotFoundException anfe) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//                }
                break;
            case R.id.level_chooser_btn_play:
                soundManager.play(R.raw.menu_click);
                nextActivity = new Intent(this, Tutorial.class);
                nextActivity.putExtra(getString(R.string.pref_key_game_mode), gameMode);
                startActivity(nextActivity);
                break;
            case R.id.level_chooser_btn_leaderboard:
                soundManager.play(R.raw.menu_click);
                //TODO add internet connection checker and leaderboard from Google play services
                break;
            case R.id.level_chooser_btn_sounds:
                if (PreferenceUtil.getBoolean(this, getString(R.string.pref_key_is_sound_on), true)) {
                    PreferenceUtil.putBoolean(this, getString(R.string.pref_key_is_sound_on), false);
                    btnSounds.setImageResource(R.drawable.selector_sound_off);
                } else {
                    PreferenceUtil.putBoolean(this, getString(R.string.pref_key_is_sound_on), true);
                    btnSounds.setImageResource(R.drawable.selector_sound_on);
                    soundManager.play(R.raw.menu_click);
                }
                break;
            case R.id.btn_level_mode_normal:
                soundManager.play(R.raw.menu_click);
                btnLevelModeNormal.setBackgroundResource(R.drawable.main_menu_game_mode_pressed);
                btnLevelModeMirrored.setBackgroundResource(R.drawable.main_menu_game_mode_normal);
                gameMode = GameMode.NORMAL;
                break;
            case R.id.btn_level_mode_mirrored:
                soundManager.play(R.raw.menu_click);
                btnLevelModeNormal.setBackgroundResource(R.drawable.main_menu_game_mode_normal);
                btnLevelModeMirrored.setBackgroundResource(R.drawable.main_menu_game_mode_pressed);
                gameMode = GameMode.MIRRORED;
                break;
        }

    }
}
