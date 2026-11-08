package com.ss.union.game.sdk.common.audio.core;

import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.ss.union.game.sdk.common.audio.inter.IAudioControl;
import com.ss.union.game.sdk.common.audio.inter.IAudioPlayListener;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a implements IAudioControl {
    private static final int b = -1;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 3;
    private static final int g = 4;
    private static final int h = 5;
    private static final int i = 6;
    private static final String j = "assets://";
    private AudioFocusRequest C;
    AudioManager a;
    private MediaPlayer k;
    private String o;
    private IAudioPlayListener q;
    private Handler u;
    private int l = 0;
    private int m = 0;
    private boolean n = false;
    private int p = 0;
    private boolean r = false;
    private float s = 1.0f;
    private float t = 1.0f;
    private Runnable v = new Runnable() { // from class: com.ss.union.game.sdk.common.audio.core.a.1
        @Override // java.lang.Runnable
        public void run() {
            a.this.d();
        }
    };
    private MediaPlayer.OnPreparedListener w = new MediaPlayer.OnPreparedListener() { // from class: com.ss.union.game.sdk.common.audio.core.a.2
        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) throws IllegalStateException {
            if (mediaPlayer == a.this.k) {
                a.this.l = 2;
                if (a.this.m != 6) {
                    if (a.this.m != 3) {
                        if (a.this.m != 4) {
                            if (a.this.m == 0) {
                                a.this.stop();
                                return;
                            }
                            return;
                        }
                        a.this.pause();
                        return;
                    }
                    a.this.play();
                    return;
                }
                a.this.playOrPause();
                return;
            }
            try {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private MediaPlayer.OnCompletionListener x = new MediaPlayer.OnCompletionListener() { // from class: com.ss.union.game.sdk.common.audio.core.a.3
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            a.this.l = 5;
            a.this.m = 5;
            a.this.a(a.this.getDuration(), a.this.getDuration(), a.this.getBufferPercentage());
            a.this.m();
            a.this.e();
        }
    };
    private MediaPlayer.OnBufferingUpdateListener y = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.ss.union.game.sdk.common.audio.core.a.4
        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
            a.this.p = i2;
            a.this.a(i2);
        }
    };
    private MediaPlayer.OnErrorListener z = new MediaPlayer.OnErrorListener() { // from class: com.ss.union.game.sdk.common.audio.core.a.5
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            a.this.l = -1;
            a.this.m = -1;
            a.this.a(i2, ((i3 == -1004 || i3 == -110) ? "网络出错" : i2 == 200 ? "抱歉，该音频不适合在此设备上播放。" : "无法播放此音频。") + l.s + i2 + Constants.ACCEPT_TIME_SEPARATOR_SP + i3 + l.t);
            a.this.e();
            return true;
        }
    };
    private MediaPlayer.OnSeekCompleteListener A = new MediaPlayer.OnSeekCompleteListener() { // from class: com.ss.union.game.sdk.common.audio.core.a.6
        @Override // android.media.MediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            a.this.r();
        }
    };
    private MediaPlayer.OnInfoListener B = new MediaPlayer.OnInfoListener() { // from class: com.ss.union.game.sdk.common.audio.core.a.7
        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
            switch (i2) {
                case 701:
                    a.this.o();
                    break;
                case 702:
                    a.this.p();
                    break;
            }
            return false;
        }
    };
    private AudioManager.OnAudioFocusChangeListener D = new AudioManager.OnAudioFocusChangeListener() { // from class: com.ss.union.game.sdk.common.audio.core.a.8
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i2) throws IllegalStateException {
            if (i2 == 1) {
                if (a.this.n) {
                    a.this.setVolume(1.0f, 1.0f);
                    a.this.play();
                    return;
                }
                return;
            }
            switch (i2) {
                case -3:
                    if (a.this.isPlaying()) {
                        a.this.n = true;
                        a.this.pause();
                        break;
                    }
                    break;
                case -2:
                    if (a.this.isPlaying()) {
                        a.this.n = true;
                        a.this.pause();
                        break;
                    }
                    break;
                case -1:
                    if (a.this.isPlaying()) {
                        a.this.n = true;
                        a.this.pause();
                        break;
                    }
                    break;
            }
        }
    };

    private a() {
        c();
        g();
    }

    public static a a() {
        return new a();
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void playAsset(String str) {
        play(j + str);
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void play(String str) {
        stop();
        this.o = str;
        this.m = 3;
        f();
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void playOrPause() throws IllegalStateException {
        if (b()) {
            if (!isPlaying()) {
                play();
                return;
            } else {
                pause();
                return;
            }
        }
        this.m = 6;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void play() throws IllegalStateException {
        if (b()) {
            if (!isPlaying()) {
                this.k.start();
            }
            this.l = 3;
            k();
            d();
            this.k.setVolume(this.s, this.t);
            return;
        }
        this.m = 3;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void pause() throws IllegalStateException {
        if (b()) {
            if (isPlaying()) {
                this.k.pause();
            }
            n();
            e();
            this.l = 4;
            return;
        }
        this.m = 4;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void stop() {
        if (b()) {
            try {
                if (this.k != null) {
                    this.k.stop();
                    this.k.reset();
                    this.k.release();
                }
            } catch (Throwable th) {
                this.l = 0;
                this.m = 0;
                this.o = null;
                this.k = null;
                this.p = 0;
                e();
                throw th;
            }
            this.l = 0;
            this.m = 0;
            this.o = null;
            this.k = null;
            this.p = 0;
            e();
            l();
            return;
        }
        this.m = 0;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public int getDuration() {
        if (b()) {
            return this.k.getDuration();
        }
        return 0;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public int getCurrentPosition() {
        if (b()) {
            return this.k.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void seekTo(int i2) throws IllegalStateException {
        if (b()) {
            q();
            this.k.seekTo(i2);
        }
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public boolean isPlaying() {
        return b() && this.k.isPlaying();
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public int getBufferPercentage() {
        return this.p;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public void release() {
        stop();
        this.q = null;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public String getPlayUrl() {
        return this.o;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public IAudioControl setLooping(boolean z) {
        this.r = z;
        if (this.k != null) {
            this.k.setLooping(z);
        }
        return this;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public IAudioControl setVolume(float f2, float f3) {
        this.s = f2;
        this.t = f3;
        if (this.k != null) {
            this.k.setVolume(f2, f3);
        }
        return this;
    }

    @Override // com.ss.union.game.sdk.common.audio.inter.IAudioControl
    public IAudioControl registerPlayListener(IAudioPlayListener iAudioPlayListener) {
        this.q = iAudioPlayListener;
        return this;
    }

    private boolean b() {
        return (this.k == null || this.l == -1 || this.l == 0 || this.l == 1) ? false : true;
    }

    private void c() {
        this.u = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        e();
        if (this.l == 3) {
            int currentPosition = getCurrentPosition();
            if (currentPosition % 1000 < 100) {
                a((currentPosition / 1000) * 1000, (getDuration() / 1000) * 1000, getBufferPercentage());
            }
            this.u.postDelayed(this.v, 1000 - r1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.u.removeCallbacksAndMessages(null);
    }

    private void f() {
        try {
            this.l = 1;
            j();
            this.k = new MediaPlayer();
            if (this.o.startsWith(j)) {
                AssetFileDescriptor assetFileDescriptorOpenFd = GlobalApplicationUtils.getContext().getAssets().openFd(this.o.replace(j, ""));
                this.k.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            } else {
                this.k.setDataSource(this.o);
            }
            this.k.setAudioStreamType(3);
            this.k.setOnPreparedListener(this.w);
            this.k.setOnSeekCompleteListener(this.A);
            this.k.setOnCompletionListener(this.x);
            this.k.setOnBufferingUpdateListener(this.y);
            this.k.setOnErrorListener(this.z);
            this.k.setOnInfoListener(this.B);
            this.k.setLooping(this.r);
            h();
            this.k.prepareAsync();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void g() {
        this.a = (AudioManager) GlobalApplicationUtils.getContext().getSystemService("audio");
    }

    private void h() {
        if (this.a == null) {
            g();
        }
        if (this.a != null) {
            if (Build.VERSION.SDK_INT < 26) {
                this.a.requestAudioFocus(this.D, 3, 1);
                return;
            }
            if (this.C == null) {
                this.C = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build()).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(this.D).build();
            }
            this.a.requestAudioFocus(this.C);
        }
    }

    private void i() {
        if (this.a != null) {
            if (Build.VERSION.SDK_INT < 26) {
                this.a.abandonAudioFocus(this.D);
            } else if (this.C != null) {
                this.a.abandonAudioFocusRequest(this.C);
            }
        }
    }

    private void j() {
        if (this.q != null) {
            this.q.onPrepare();
        }
    }

    private void k() {
        if (this.q != null) {
            this.q.onPlay();
        }
    }

    private void l() {
        if (this.q != null) {
            this.q.onStop();
        }
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.q != null) {
            this.q.onPlayCompletion();
        }
        i();
    }

    private void n() {
        if (this.q != null) {
            this.q.onPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        if (this.q != null) {
            this.q.onBufferingUpdate(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.q != null) {
            this.q.onBlockStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.q != null) {
            this.q.onBlockEnd();
        }
    }

    private void q() {
        if (this.q != null) {
            this.q.onSeekStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.q != null) {
            this.q.onSeekEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3, int i4) {
        if (this.q != null) {
            this.q.onPlayProgress(i2, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str) {
        if (this.q != null) {
            this.q.onError(i2, str);
        }
        i();
    }
}
