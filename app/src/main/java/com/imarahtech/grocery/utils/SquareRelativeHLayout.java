package com.imarahtech.grocery.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/** A RelativeLayout that will always be square -- same width and height,
 * where the height is based off the width. */
public class SquareRelativeHLayout extends RelativeLayout {

    public SquareRelativeHLayout(Context context) {
        super(context);
    }

    public SquareRelativeHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeHLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareRelativeHLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Set a square layout.
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }

}