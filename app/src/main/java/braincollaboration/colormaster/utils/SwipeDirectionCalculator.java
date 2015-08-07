package braincollaboration.colormaster.utils;

/**
 * Class for calculating user swipe direction
 */
public class SwipeDirectionCalculator {

    private final static int MAX_CLICK_DISTANCE = 30;   //set up value to define tap or not
    final public static int USER_SWIPE_CORRECT = 1;
    final public static int USER_SWIPE_INCORRECT = -1;
    final public static int USER_TAP_INSTEAD_SWIPE = -500;


    public static int calculateSwipeDirection(float YStartPoint, float YEndPoint) {
        if (YStartPoint > YEndPoint && YStartPoint > YEndPoint + MAX_CLICK_DISTANCE) {
            return USER_SWIPE_INCORRECT;
        } else if (YStartPoint < YEndPoint && YStartPoint < YEndPoint - MAX_CLICK_DISTANCE) {
            return USER_SWIPE_CORRECT;
        }
        return USER_TAP_INSTEAD_SWIPE;
    }
}
