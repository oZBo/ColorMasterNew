package braincollaboration.colormaster.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import braincollaboration.colormaster.R;
import braincollaboration.colormaster.engine.Color;
import braincollaboration.colormaster.engine.GameHelper;
import braincollaboration.colormaster.engine.GameMode;
import braincollaboration.colormaster.utils.SwipeDirectionCalculator;
import braincollaboration.colormaster.views.MirroredOrNormalTextView;
import cat.ppicas.customtypeface.CustomTypeface;
import cat.ppicas.customtypeface.CustomTypefaceFactory;

/**
 * Main game level. Contains NormalMode and MirroredMode
 */
public class GameLevel extends Activity implements View.OnTouchListener {

    private static final int LEFT_SIDE_ID = 100;
    private static final int RIGHT_SIDE_ID = 200;

    private static int score = 0;                   //Game score for current mode
    private float YstartPoint = 0, YEndPoint = 0;   //Values for calculating user swipe direction

    Color colorLeft, colorRight;
    GameMode gameMode;

    MirroredOrNormalTextView textViewLeftSide, textViewRightSide;
    LinearLayout layoutLeftSide, layoutRightSide;
    TextView tvGameScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory(new CustomTypefaceFactory(this, CustomTypeface.getInstance()));
        super.onCreate(savedInstanceState);
        gameMode = (GameMode) getIntent().getSerializableExtra(getString(R.string.pref_key_game_mode));
        setContentView(R.layout.game_level);

        initViews();
        startLevel(gameMode);

    }

    private void initViews() {
        tvGameScore = (TextView) findViewById(R.id.tv_score);
        layoutLeftSide = (LinearLayout) findViewById(R.id.layout_left_side);
        layoutLeftSide.setOnTouchListener(this);
        layoutRightSide = (LinearLayout) findViewById(R.id.layout_right_side);
        layoutRightSide.setOnTouchListener(this);

        textViewLeftSide = (MirroredOrNormalTextView) findViewById(R.id.textview_left_side);
        textViewRightSide = (MirroredOrNormalTextView) findViewById(R.id.textview_right_side);
    }

    private void startLevel(GameMode gameMode) {
//        hideGameOverDialog();
        generateRightColor(gameMode);
        generateLeftColor(gameMode);
//        startSideTimer(LEFT_SIDE, GameHelper.getTimeForLevel(score));     //TODO add timers to game level
//        startSideTimer(RIGHT_SIDE, GameHelper.getTimeForLevel(score));    //TODO add timers to game level
    }

    private void changeGameScore(int score) {
        tvGameScore.setText("" + score);
    }

    private void endLevel() {
//        soundManager.play(R.raw.incorrect);  //TODO make soundManager class. Add loose sound.
//        if (score > GameHelper.loadBestScore(GameLevel.this, gameMode)) {
//            GameHelper.saveBestScore(GameLevel.this, gameMode, score);
//        }
        score = 0;
        changeGameScore(score);
//        pushAccomplishments(score, false);    //TODO add google play services for game score
//        vibrator.vibrate(VIBRATOR_INTERVAL);  //TODO add vibrator manager
//        layoutGameOver.startAnimation(fadeIn);    //TODO add GameOver overlay
    }

    private void nextLevel(int side) {
//        soundManager.play(R.raw.correct); //TODO make soundManager class. Add win sound.
        switch (side) {
            case LEFT_SIDE_ID:
                score++;
                changeGameScore(score);
                generateLeftColor(gameMode);
//                startSideTimer(LEFT_SIDE, GameHelper.getTimeForLevel(score)); //TODO make timer for level
                break;
            case RIGHT_SIDE_ID:
                score++;
                changeGameScore(score);
                generateRightColor(gameMode);
//                startSideTimer(RIGHT_SIDE, GameHelper.getTimeForLevel(score)); //TODO make timer for level
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
