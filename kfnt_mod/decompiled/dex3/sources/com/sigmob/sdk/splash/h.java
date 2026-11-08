package com.sigmob.sdk.splash;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.base.views.AdVideoPlayer;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h extends e {
    private final AdVideoPlayer b;
    private final BaseAdUnit c;

    public h(Context context, BaseAdUnit baseAdUnit) {
        super(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.b = new AdVideoPlayer(context);
        setBackgroundColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.c = baseAdUnit;
        addView(this.b, layoutParams);
    }

    @Override // com.sigmob.sdk.splash.e
    public void a() {
        super.setVisibility(0);
        SigmobLog.i("video showAd");
        this.b.setVisibility(0);
        this.b.d();
    }

    @Override // com.sigmob.sdk.splash.e
    public boolean a(BaseAdUnit baseAdUnit) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        SigmobLog.i("video start");
        String splashFilePath = baseAdUnit.getSplashFilePath();
        this.b.a(0, 0);
        this.b.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.sigmob.sdk.splash.h.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) throws IllegalStateException {
                h.this.a = mediaPlayer.getDuration() / 1000;
                mediaPlayer.setVolume(0.0f, 0.0f);
                mediaPlayer.start();
                BaseBroadcastReceiver.a(h.this.getContext(), h.this.c.getUuid(), IntentActions.ACTION_INTERSTITIAL_SHOW);
                SigmobLog.i("video onPrepared");
            }
        });
        this.b.setVideoPath(splashFilePath);
        return true;
    }

    @Override // com.sigmob.sdk.splash.e
    public void b() throws IllegalStateException {
        this.b.a();
    }

    @Override // com.sigmob.sdk.splash.e
    public void c() throws IllegalStateException {
        this.b.b();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i == 8) {
            try {
                if (this.b != null) {
                    SigmobLog.i("video GONE");
                    this.b.c();
                    removeAllViews();
                }
            } catch (Throwable th) {
                SigmobLog.e("set splash ad video content error: " + th.getMessage());
            }
        }
        super.setVisibility(i);
    }
}
