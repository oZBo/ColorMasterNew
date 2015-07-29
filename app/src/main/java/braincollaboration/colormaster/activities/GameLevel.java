package braincollaboration.colormaster.activities;

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

import braincollaboration.colormaster.R;
import braincollaboration.colormaster.engine.Color;
import braincollaboration.colormaster.engine.GameHelper;
import braincollaboration.colormaster.engine.GameMode;
import braincollaboration.colormaster.utils.SwipeDirectionCalculator;
import braincollaboration.colormaster.utils.VibratorManager;
import braincollaboration.colormaster.views.MirroredOrNormalTextView;
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
    private Animation fadeIn, fadeOut;
    private VibratorManager vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new CustomTypefaceFactory(this, CustomTypeface.getInstance()));
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                break;
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

        fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setDuration(ANIM_DUARTION);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
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
    }

    private void startSideTimer(int sideID, final int levelTime) {

        switch (sideID) {
            case LEFT_SIDE_ID:
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
        hideGameOverDialog();
        generateRightColor(gameMode);
        generateLeftColor(gameMode);
        startSideTimer(LEFT_SIDE_ID, GameHelper.getTimeForLevel(score));
        startSideTimer(RIGHT_SIDE_ID, GameHelper.getTimeForLevel(score));
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
//        soundManager.play(R.raw.incorrect);  //TODO make soundManager class. Add loose sound.
        if (score > GameHelper.loadBestScore(GameLevel.this, gameMode)) {
            GameHelper.saveBestScore(GameLevel.this, gameMode, score);
        }
//        pushAccomplishments(score, false);    //TODO add google play services for game score
        vibrator.vibrate(VIBRATOR_INTERVAL);
        layoutGameOver.startAnimation(fadeIn);
    }

    private void nextLevel(int side) {
//        soundManager.play(R.raw.correct); //TODO make soundManager class. Add win sound.
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
//        layoutLeftSide.setBackgroundDrawable(colorLeft.getParentLayoutBgImage()); //TODO change background for textViewLayout
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
//        layoutRightSide.setBackgroundDrawable(colorRight.getParentLayoutBgImage()); //TODO change background for textViewLayout
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
                        moveToNextLevelOrEndGame(LEFT_SIDE_ID, SwipeDirectionCalculator.calculateSwipeDirection(YstartPoint, YEndPoint));
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
                        moveToNextLevelOrEndGame(RIGHT_SIDE_ID, SwipeDirectionCalculator.calculateSwipeDirection(YstartPoint, YEndPoint));
                        break;
                }
        }
        return true;
    }


    private void moveToNextLevelOrEndGame(int sideID, int userChoice) {
        switch (sideID) {
            case LEFT_SIDE_ID:
                switch (userChoice) {
                    case SwipeDirectionCalculator.USER_SWIPE_INCORRECT:
                        if (colorLeft.isColorSameAsText()) {
                            nextLevel(LEFT_SIDE_ID);
                        } else {
                            endLevel();
                        }
                        break;
                    case SwipeDirectionCalculator.USER_SWIPE_CORRECT:
                        if (colorLeft.isColorSameAsText()) {
                            endLevel();
                        } else {
                            nextLevel(LEFT_SIDE_ID);
                        }
                        break;
                }
                break;
            case RIGHT_SIDE_ID:
                switch (userChoice) {
                    case SwipeDirectionCalculator.USER_SWIPE_INCORRECT:
                        if (colorRight.isColorSameAsText()) {
                            nextLevel(RIGHT_SIDE_ID);
                        } else {
                            endLevel();
                        }
                        break;
                    case SwipeDirectionCalculator.USER_SWIPE_CORRECT:
                        if (colorRight.isColorSameAsText()) {
                            endLevel();
                        } else {
                            nextLevel(RIGHT_SIDE_ID);
                        }
                        break;
                }
                break;
        }
    }

}
