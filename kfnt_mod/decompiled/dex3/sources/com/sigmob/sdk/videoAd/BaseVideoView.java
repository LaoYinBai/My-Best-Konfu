package com.sigmob.sdk.videoAd;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.VideoView;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.common.utils.r;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BaseVideoView extends VideoView {
    protected final MediaMetadataRetriever a;
    private int b;
    private int c;

    public BaseVideoView(Context context) {
        super(context);
        m.a.a(context, "context cannot be null");
        this.a = new MediaMetadataRetriever();
    }

    private void b() {
        try {
            setOnClickListener(null);
            setOnCompletionListener(null);
            setOnErrorListener(null);
            setOnPreparedListener(null);
            Field declaredField = View.class.getDeclaredField("mContext");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                declaredField.set(this, null);
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    public void a() {
        SigmobLog.d("BaseVideoView onDestroy() called");
        suspend();
        stopPlayback();
        r.a(this);
        b();
        super.destroyDrawingCache();
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(0, i);
        int defaultSize2 = getDefaultSize(0, i2);
        if (defaultSize2 > defaultSize) {
            if (this.c <= this.b) {
                defaultSize2 = (int) (defaultSize * (this.c / this.b));
            }
        } else if (this.c > this.b) {
            defaultSize = (int) (defaultSize2 * (this.b / this.c));
        }
        if (this.c != this.b) {
            setMeasuredDimension(defaultSize, defaultSize2);
        } else if (this.c == -1) {
            setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        } else {
            super.onMeasure(i, i2);
        }
    }

    public void setVolume(float f) {
        try {
            Field declaredField = Class.forName("android.widget.VideoView").getDeclaredField("mMediaPlayer");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                ((MediaPlayer) declaredField.get(this)).setVolume(f, f);
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }
}
