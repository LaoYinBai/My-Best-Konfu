package com.tachikoma.core.component.text;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.tachikoma.core.utility.ScheduleHandler;
import com.tachikoma.core.utility.SizeUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MarqueeTextView extends TextView {
    private static final int MARQUEE_DP_PER_SECOND = 12;
    private static final int MARQUEE_INTERVAL = 48;
    private static final int PADDING = SizeUtil.dp2Px(20);
    private float mBaseLine;
    private boolean mIsStoped;
    private ScheduleHandler mProgressUpdateHandler;
    private float mScroll;
    private final float mSpeed;
    private String mText;
    private float mTextStartOffset;
    private float mTextWidth;
    private int mWidth;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsStoped = true;
        this.mSpeed = ((context.getResources().getDisplayMetrics().density * 12.0f) * 48.0f) / 1000.0f;
    }

    private float getScrollInitialValue() {
        return -this.mTextStartOffset;
    }

    private void initProgressUpdateHandler() {
        if (this.mProgressUpdateHandler != null) {
            return;
        }
        this.mProgressUpdateHandler = new ScheduleHandler(48L, new Runnable() { // from class: com.tachikoma.core.component.text.MarqueeTextView.1
            @Override // java.lang.Runnable
            public void run() {
                if (MarqueeTextView.this.mIsStoped) {
                    return;
                }
                MarqueeTextView.this.mScroll += MarqueeTextView.this.mSpeed;
                if (MarqueeTextView.this.mScroll > MarqueeTextView.this.mTextWidth + MarqueeTextView.PADDING) {
                    MarqueeTextView.this.mScroll -= MarqueeTextView.this.mTextWidth + MarqueeTextView.PADDING;
                }
                MarqueeTextView.this.postInvalidate();
            }
        });
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mWidth = getWidth();
        if (TextUtils.isEmpty(this.mText) || this.mTextWidth <= 0.0f || this.mWidth <= 0) {
            return;
        }
        float f = -this.mScroll;
        while (f < this.mWidth) {
            canvas.drawText(this.mText, f, this.mBaseLine, getPaint());
            f += this.mTextWidth + PADDING;
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mBaseLine = (int) ((getHeight() / 2) - ((getPaint().descent() + getPaint().ascent()) / 2.0f));
    }

    public void pauseMarquee() {
        if (this.mProgressUpdateHandler != null) {
            this.mProgressUpdateHandler.stop();
            this.mIsStoped = true;
        }
    }

    public void reset() {
        this.mText = null;
        stopMarquee();
    }

    public void setText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.mText == null || !this.mText.equals(str)) {
            this.mText = str;
            this.mTextWidth = getPaint().measureText(this.mText);
        }
        initProgressUpdateHandler();
        postInvalidate();
        startMarquee();
    }

    public void setText(String str, float f) {
        this.mTextStartOffset = f;
        this.mScroll = -f;
        setText(str);
    }

    public void startMarquee() {
        if (this.mProgressUpdateHandler == null || this.mProgressUpdateHandler.isRunning()) {
            return;
        }
        this.mProgressUpdateHandler.start();
        this.mIsStoped = false;
    }

    public void stopMarquee() {
        pauseMarquee();
        if (this.mScroll != getScrollInitialValue()) {
            this.mScroll = getScrollInitialValue();
            postInvalidate();
        }
    }
}
