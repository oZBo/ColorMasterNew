package com.braincollaboration.colormaster.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.braincollaboration.colormaster.R;
import com.braincollaboration.colormaster.engine.GameMode;
import com.braincollaboration.colormaster.utils.PreferenceUtil;
import com.braincollaboration.colormaster.utils.SoundManager;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.example.games.basegameutils.BaseGameActivity;

import cat.ppicas.customtypeface.CustomTypeface;
import cat.ppicas.customtypeface.CustomTypefaceFactory;
import hotchemi.android.rate.AppRate;

/**
 * Main menu activity
 */
public class MainMenu extends BaseGameActivity implements View.OnClickListener {

    public static final int ACTIVITY_CODE_SHOW_LEADERBOARD = 500;
    public static final String IS_GOOGLE_GAMES_LOGED_IN = "isGoogleGamesLoggedIn";

    private Button btnLevelModeNormal, btnLevelModeMirrored;
    private ImageButton btnHelp, btnMarkapp, btnPlay, btnLedaerboard, btnSounds;
    private GameMode gameMode = GameMode.NORMAL;
    private SoundManager soundManager;
    private boolean isGooglePlayInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new CustomTypefaceFactory(this, CustomTypeface.getInstance()));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        soundManager = SoundManager.getInstance(this);
        setContentView(R.layout.main_menu);
        initViews();
        initAppRaterDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkGameMode();
        checkSoundState();
    }

    private void initViews() {
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

    private void initAppRaterDialog() {
        AppRate.with(this)
                .setInstallDays(2) // default 10, 0 means install day.
                .setLaunchTimes(5) // default 10 times.
                .setRemindInterval(2) // default 1 day.
                .setShowLaterButton(true) // default true.
                .setDebug(true) // default false.
                .setCancelable(false) // default false.
                .setTitle(R.string.rate_dialog_title)
                .setTextLater(R.string.rate_dialog_cancel)
                .setTextNever(R.string.rate_dialog_no)
                .setTextRateNow(R.string.rate_dialog_ok)
                .monitor();
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
        Intent nextActivity;
        switch (v.getId()) {
            case R.id.level_chooser_btn_help:
                soundManager.play(R.raw.menu_click);
                nextActivity = new Intent(this, Tutorial.class);
                nextActivity.putExtra(getString(R.string.pref_key_can_start_game_after_tutorial), false);
                startActivity(nextActivity);
                break;
            case R.id.level_chooser_btn_markapp:
                soundManager.play(R.raw.menu_click);
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            case R.id.level_chooser_btn_play:
                soundManager.play(R.raw.menu_click);
                if (PreferenceUtil.getBoolean(this, getString(R.string.pref_key_can_show_tutorial), true)) {
                    nextActivity = new Intent(this, Tutorial.class);
                } else {
                    nextActivity = new Intent(this, GameLevel.class);
                }
                nextActivity.putExtra(IS_GOOGLE_GAMES_LOGED_IN, isGooglePlayInit);
                nextActivity.putExtra(getString(R.string.pref_key_game_mode), gameMode);
                startActivity(nextActivity);
                break;
            case R.id.level_chooser_btn_leaderboard:
                soundManager.play(R.raw.menu_click);
                if (mHelper.isSignedIn()) {
                    startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(getApiClient()),
                            ACTIVITY_CODE_SHOW_LEADERBOARD);
                } else {
                    beginUserInitiatedSignIn();
                }
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

    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == ACTIVITY_CODE_SHOW_LEADERBOARD
                && responseCode == GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED) {
            mHelper.disconnect();
        } else {
            mHelper.onActivityResult(requestCode, responseCode, data);
        }
    }

    @Override
    public void onSignInFailed() {
        isGooglePlayInit = false;
    }

    @Override
    public void onSignInSucceeded() {
        isGooglePlayInit = true;
    }
}
