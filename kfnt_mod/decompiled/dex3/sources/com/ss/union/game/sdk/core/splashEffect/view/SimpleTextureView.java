package com.ss.union.game.sdk.core.splashEffect.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SimpleTextureView extends TextureView {
    private MediaPlayer a;

    public SimpleTextureView(Context context) {
        this(context, null);
    }

    public SimpleTextureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SimpleTextureView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a(MediaPlayer mediaPlayer) {
        this.a = mediaPlayer;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        try {
            int videoWidth = this.a.getVideoWidth();
            int videoHeight = this.a.getVideoHeight();
            if (videoHeight != 0 && videoWidth != 0) {
                int defaultSize = View.getDefaultSize(videoWidth, i);
                int defaultSize2 = View.getDefaultSize(videoHeight, i2);
                View.MeasureSpec.getMode(i);
                int size = View.MeasureSpec.getSize(i);
                View.MeasureSpec.getMode(i2);
                int size2 = View.MeasureSpec.getSize(i2);
                int i3 = videoWidth * defaultSize2;
                int i4 = defaultSize * videoHeight;
                if (i3 < i4) {
                    com.ss.union.game.sdk.core.splashEffect.a.a.b("video too wide, correcting");
                    defaultSize = i3 / videoHeight;
                } else if (i3 > i4) {
                    com.ss.union.game.sdk.core.splashEffect.a.a.b("video too tall, correcting");
                    defaultSize2 = i4 / videoWidth;
                }
                com.ss.union.game.sdk.core.splashEffect.a.a.b("原始视频布局大小: " + size + Constants.ACCEPT_TIME_SEPARATOR_SP + size2);
                com.ss.union.game.sdk.core.splashEffect.a.a.b("转换后视频布局大小: " + defaultSize + Constants.ACCEPT_TIME_SEPARATOR_SP + defaultSize2);
                setMeasuredDimension(defaultSize, defaultSize2);
                return;
            }
            setMeasuredDimension(1, 1);
            postOnAnimation(new Runnable() { // from class: com.ss.union.game.sdk.core.splashEffect.view.SimpleTextureView.1
                @Override // java.lang.Runnable
                public void run() {
                    SimpleTextureView.this.requestLayout();
                }
            });
        } catch (Exception e) {
            com.ss.union.game.sdk.core.splashEffect.a.a.b("SimpleTextureView测量异常: " + Log.getStackTraceString(e));
            super.onMeasure(i, i2);
        }
    }
}
