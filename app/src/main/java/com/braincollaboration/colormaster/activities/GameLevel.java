package com.braincollaboration.colormaster.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.braincollaboration.colormaster.R;
import com.braincollaboration.colormaster.engine.Color;
import com.braincollaboration.colormaster.engine.GameHelper;
import com.braincollaboration.colormaster.engine.GameMode;
import com.braincollaboration.colormaster.engine.SwipeDirectionListener;
import com.braincollaboration.colormaster.utils.PreferenceUtil;
import com.braincollaboration.colormaster.utils.SoundManager;
import com.braincollaboration.colormaster.utils.VibratorManager;
import com.braincollaboration.colormaster.views.MirroredOrNormalTextView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

import cat.ppicas.customtypeface.CustomTypeface;
import cat.ppicas.customtypeface.CustomTypefaceFactory;

/**
 * Main game level. Contains NormalMode and MirroredMode
 */
public class GameLevel extends Activity implements View.OnTouchListener, View.OnClickListener {

    private static final int LEFT_SIDE_ID = 100;
    private static final int RIGHT_SIDE_ID = 200;
    private final static int COUNT_DOWN_INTERVAL = 10;  //Interval to update timers. MilliSeconds
    private final static int ANIM_DUARTION = 500; //Anim duration of the game over overlay. Milliseconds
    private final static int VIBRATOR_INTERVAL = 250; //Device vibration duration. Milliseconds

    private static int score = 0;                       //Game score for current mode
    private float YstartPoint = 0, YEndPoint = 0;       //Values for calculating user swipe direction

    private Color colorLeft, colorRight;
    private GameMode gameMode;

    private ProgressBar progressBarLeft, progressBarRight;
    private CountDownTimer countDownTimerLeft, countDownTimerRight;
    private MirroredOrNormalTextView textViewLeftSide, textViewRightSide;
    private LinearLayout layoutLeftSide, layoutRightSide, layoutGameOver;
    private ImageButton btnReplay, btnHome, btnShare, btnLeaderboard;
    private TextView tvGameOverScore, tvGameOverBest;
    private TextView tvGameScore;
    private Animation fadeIn, fadeOut, fallingDownLeft, fallingDownRight;
    private VibratorManager vibrator;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new CustomTypefaceFactory(this, CustomTypeface.getInstance())); //Set custom fonts to the current activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        soundManager = SoundManager.getInstance(this);
        score = 0;
        gameMode = (GameMode) getIntent().getSerializableExtra(getString(R.string.pref_key_game_mode));
        vibrator = VibratorManager.getManager(this);
        setContentView(R.layout.game_level);

        initViews();
        initAnimations();
        startLevel(gameMode);

    }

    @Override
    public void onBackPressed() {
        if (layoutGameOver.isShown()) {
            finish();
        } else {
            layoutGameOver.startAnimation(fadeIn);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.game_over_btn_replay:
                layoutGameOver.startAnimation(fadeOut);
                break;
            case R.id.game_over_btn_home:
                onBackPressed();
                break;
            case R.id.game_over_btn_share:
                GameHelper.shareScore(this, getString(R.string.share_dialog_part_1) + score + getString(R.string.share_dialog_part_2) + getString(R.string.share_dialog_part_3));
                break;
            case R.id.game_over_leaderboard:
//                pushAccomplishments(score, true);  //TODO Google play service push score to leaderboard
//                try {
//                    startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(getApiClient()),
//                            PreferenceUtil.getInt(this, getString(R.string.pref_key_show_leaderboard_requestcode), 500));
//                } catch (Exception ex) {
//                    getApiClient().connect();
//                }
//                break;
        }
    }

    @Override
    protected void onPause() {
        cancelCountDownTimers();
        layoutGameOver.setVisibility(View.VISIBLE);
        super.onPause();
    }

    private void initViews() {
        tvGameScore = (TextView) findViewById(R.id.tv_score);
        layoutLeftSide = (LinearLayout) findViewById(R.id.layout_left_side);
        layoutLeftSide.setOnTouchListener(this);
        layoutRightSide = (LinearLayout) findViewById(R.id.layout_right_side);
        layoutRightSide.setOnTouchListener(this);
        layoutGameOver = (LinearLayout) findViewById(R.id.layout_game_over);
        layoutGameOver.setVisibility(View.GONE);
        textViewLeftSide = (MirroredOrNormalTextView) findViewById(R.id.textview_left_side);
        textViewRightSide = (MirroredOrNormalTextView) findViewById(R.id.textview_right_side);
        progressBarLeft = (ProgressBar) findViewById(R.id.progress_left);
        progressBarRight = (ProgressBar) findViewById(R.id.progress_right);
        tvGameOverScore = (TextView) findViewById(R.id.game_over_tv_score);
        tvGameOverBest = (TextView) findViewById(R.id.game_over_tv_best);
        btnReplay = (ImageButton) findViewById(R.id.game_over_btn_replay);
        btnReplay.setOnClickListener(this);
        btnHome = (ImageButton) findViewById(R.id.game_over_btn_home);
        btnHome.setOnClickListener(this);
        btnShare = (ImageButton) findViewById(R.id.game_over_btn_share);
        btnShare.setOnClickListener(this);
        btnLeaderboard = (ImageButton) findViewById(R.id.game_over_leaderboard);
        btnLeaderboard.setOnClickListener(this);
    }

    protected void initAnimations() {

        fallingDownLeft = AnimationUtils.loadAnimation(this, R.anim.falling_down);
        fallingDownRight = AnimationUtils.loadAnimation(this, R.anim.falling_down);
        fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setDuration(ANIM_DUARTION);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textViewLeftSide.setVisibility(View.INVISIBLE);
                textViewRightSide.setVisibility(View.INVISIBLE);
                layoutLeftSide.setOnTouchListener(null);
                layoutRightSide.setOnTouchListener(null);
                cancelCountDownTimers();
                showScoreDialog();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnReplay.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        fadeOut.setDuration(ANIM_DUARTION);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                score = 0;
                refreshGameScore(score);
                btnReplay.setClickable(false);
                progressBarLeft.setProgress(progressBarLeft.getMax());
                progressBarRight.setProgress(progressBarRight.getMax());
                generateLeftColor(gameMode);
                generateRightColor(gameMode);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideGameOverDialog();
                layoutLeftSide.setOnTouchListener(GameLevel.this);
                layoutRightSide.setOnTouchListener(GameLevel.this);
                startSideTimer(LEFT_SIDE_ID, GameHelper.getTimeForLevel(score));
                startSideTimer(RIGHT_SIDE_ID, GameHelper.getTimeForLevel(score));
                textViewLeftSide.setVisibility(View.VISIBLE);
                textViewRightSide.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void cancelCountDownTimers() {
        if (countDownTimerRight != null && countDownTimerLeft != null) {
            countDownTimerLeft.cancel();
            countDownTimerRight.cancel();
        }
        textViewLeftSide.clearAnimation();
        textViewRightSide.clearAnimation();
    }

    private void startSideTimer(int sideID, final int levelTime) {

        switch (sideID) {
            case LEFT_SIDE_ID:
                fallingDownLeft.setDuration(levelTime);
                textViewLeftSide.startAnimation(fallingDownLeft);
                progressBarLeft.setMax(levelTime);
                if (countDownTimerLeft != null) {
                    countDownTimerLeft.cancel();
                }
                countDownTimerLeft = new CountDownTimer(levelTime, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progressBarLeft.setProgress((int) millisUntilFinished);

                    }

                    @Override
                    public void onFinish() {
                        progressBarLeft.setProgress(0);
                        endLevel();
                    }
                };
                countDownTimerLeft.start();
                break;
            case RIGHT_SIDE_ID:
                fallingDownRight.setDuration(levelTime);
                textViewRightSide.startAnimation(fallingDownRight);
                progressBarRight.setMax(levelTime);
                if (countDownTimerRight != null) {
                    countDownTimerRight.cancel();
                }
                countDownTimerRight = new CountDownTimer(levelTime, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progressBarRight.setProgress((int) millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        progressBarRight.setProgress(0);
                        endLevel();
                    }
                };
                countDownTimerRight.start();
                break;
        }
    }

    private void startLevel(GameMode gameMode) {
        startSideTimer(LEFT_SIDE_ID, GameHelper.getTimeForLevel(score));
        startSideTimer(RIGHT_SIDE_ID, GameHelper.getTimeForLevel(score));
        hideGameOverDialog();
        generateRightColor(gameMode);
        generateLeftColor(gameMode);
    }

    private void showScoreDialog() {
        if (!layoutGameOver.isShown()) {
            layoutGameOver.setVisibility(View.VISIBLE);
            tvGameOverBest.setText("" + GameHelper.loadBestScore(this, gameMode));
            tvGameOverScore.setText("" + score);
        }
    }

    private void refreshGameScore(int score) {
        tvGameScore.setText("" + score);
    }

    private void hideGameOverDialog() {
        layoutGameOver.setVisibility(View.GONE);
    }

    private void endLevel() {
        soundManager.play(R.raw.incorrect);
        if (score > GameHelper.loadBestScore(GameLevel.this, gameMode)) {
            GameHelper.saveBestScore(GameLevel.this, gameMode, score);
        }
//        pushAccomplishments(score, false);    //TODO add google play services for game score
        vibrator.vibrate(VIBRATOR_INTERVAL);
        layoutGameOver.startAnimation(fadeIn);
    }

    private void nextLevel(int side) {
        soundManager.play(R.raw.correct);
        switch (side) {
            case LEFT_SIDE_ID:
                score++;
                refreshGameScore(score);
                generateLeftColor(gameMode);
                startSideTimer(LEFT_SIDE_ID, GameHelper.getTimeForLevel(score));
                break;
            case RIGHT_SIDE_ID:
                score++;
                refreshGameScore(score);
                generateRightColor(gameMode);
                startSideTimer(RIGHT_SIDE_ID, GameHelper.getTimeForLevel(score));
                break;
        }
    }

    private void generateLeftColor(GameMode gameMode) {
        colorLeft = new Color(this);
        switch (gameMode) {
            case NORMAL:
                textViewLeftSide.setTextDrawingNormal();
                break;
            case MIRRORED:
                if (GameHelper.getRandomBoolean())
                    textViewLeftSide.setTextDrawingMirrored();
                else
                    textViewLeftSide.setTextDrawingNormal();
                break;
        }
        textViewLeftSide.setTextColor(colorLeft.getColorValue());
        textViewLeftSide.setText(colorLeft.getColorText());
        layoutLeftSide.setBackgroundDrawable(colorLeft.getLayoutBackgroundColor());
    }

    private void generateRightColor(GameMode gameMode) {
        colorRight = new Color(this);
        switch (gameMode) {
            case NORMAL:
                textViewRightSide.setTextDrawingNormal();
                break;
            case MIRRORED:
                if (GameHelper.getRandomBoolean())
                    textViewRightSide.setTextDrawingMirrored();
                else
                    textViewRightSide.setTextDrawingNormal();
                break;
        }
        textViewRightSide.setTextColor(colorRight.getColorValue());
        textViewRightSide.setText(colorRight.getColorText());
        layoutRightSide.setBackgroundDrawable(colorRight.getLayoutBackgroundColor());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.layout_left_side:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        YstartPoint = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        YEndPoint = event.getY();
                        moveToNextLevelOrEndGame(LEFT_SIDE_ID, SwipeDirectionListener.calculateSwipeDirection(YstartPoint, YEndPoint));
                        break;
                }
                break;
            case R.id.layout_right_side:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        YstartPoint = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        YEndPoint = event.getY();
                        moveToNextLevelOrEndGame(RIGHT_SIDE_ID, SwipeDirectionListener.calculateSwipeDirection(YstartPoint, YEndPoint));
                        break;
                }
        }
        return true;
    }


    private void moveToNextLevelOrEndGame(int sideID, int userChoice) {
        switch (sideID) {
            case LEFT_SIDE_ID:
                switch (userChoice) {
                    case SwipeDirectionListener.USER_SWIPE_INCORRECT:
                        if (colorLeft.isColorSameAsBackground()) {
                            nextLevel(LEFT_SIDE_ID);
                        } else {
                            endLevel();
                        }
                        break;
                    case SwipeDirectionListener.USER_SWIPE_CORRECT:
                        if (colorLeft.isColorSameAsBackground()) {
                            endLevel();
                        } else {
                            nextLevel(LEFT_SIDE_ID);
                        }
                        break;
                    case SwipeDirectionListener.USER_TAP_INSTEAD_SWIPE:
                        return;
                }
                break;
            case RIGHT_SIDE_ID:
                switch (userChoice) {
                    case SwipeDirectionListener.USER_SWIPE_INCORRECT:
                        if (colorRight.isColorSameAsBackground()) {
                            nextLevel(RIGHT_SIDE_ID);
                        } else {
                            endLevel();
                        }
                        break;
                    case SwipeDirectionListener.USER_SWIPE_CORRECT:
                        if (colorRight.isColorSameAsBackground()) {
                            endLevel();
                        } else {
                            nextLevel(RIGHT_SIDE_ID);
                        }
                        break;
                    case SwipeDirectionListener.USER_TAP_INSTEAD_SWIPE:
                        return;
                }
                break;
        }
    }

//    @Override
//    public void onSignInFailed() {
//
//    }
//
//    @Override
//    public void onSignInSucceeded() {
//
//    }
}
