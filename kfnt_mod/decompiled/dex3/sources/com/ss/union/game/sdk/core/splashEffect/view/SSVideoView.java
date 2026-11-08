package com.ss.union.game.sdk.core.splashEffect.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.VideoView;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SSVideoView extends VideoView {
    public SSVideoView(Context context) {
        this(context, null);
    }

    public SSVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SSVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (Build.VERSION.SDK_INT > 23) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
        }
    }
}
