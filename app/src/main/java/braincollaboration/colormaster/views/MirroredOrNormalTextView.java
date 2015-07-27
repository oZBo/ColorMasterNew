package braincollaboration.colormaster.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by eandreychenko on 27.07.2015.
 */
public class MirroredOrNormalTextView extends TextView {

    private boolean isMirrored = false;

    public MirroredOrNormalTextView(Context context) {
        super(context);
    }

    public MirroredOrNormalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MirroredOrNormalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isMirrored) {
            canvas.translate(getWidth(), 0);
            canvas.scale(-1, 1);
        }
        super.onDraw(canvas);
    }

    public void setTextDrawingMirrored() {
        isMirrored = true;
    }

    public void setTextDrawingNormal() {
        isMirrored = false;
    }

}
