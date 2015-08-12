package com.braincollaboration.colormaster.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by oZBo on 28.01.2015.
 */
public class MirroredProgressBar extends ProgressBar {
    public MirroredProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.translate(getWidth(), 0);
        canvas.scale(-1, 1);
        super.onDraw(canvas);
    }
}
