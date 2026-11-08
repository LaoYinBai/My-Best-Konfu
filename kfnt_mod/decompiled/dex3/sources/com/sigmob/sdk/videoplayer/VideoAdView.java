package com.sigmob.sdk.videoplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.utils.o;
import com.sigmob.sdk.nativead.SigAdView;
import com.sigmob.sdk.nativead.h;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoAdView extends RelativeLayout implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    public static boolean a = true;
    public static int b = 6;
    public static int c = 1;
    public static boolean d = false;
    public static boolean e = true;
    public static int f;
    protected int A;
    protected a B;
    protected boolean C;
    VideoPlayerTextureView D;
    public AudioManager.OnAudioFocusChangeListener E;
    private ViewGroup F;
    private ViewGroup G;
    private ViewGroup H;
    private ViewGroup I;
    private ViewGroup J;
    private ViewGroup K;
    private ViewGroup L;
    private ViewGroup M;
    private ProgressBar N;
    private ImageView O;
    private h P;
    private boolean Q;
    private com.sigmob.sdk.videoplayer.a R;
    private ImageView S;
    private int T;
    private int U;
    private c V;
    private boolean W;
    private View aa;
    private boolean ab;
    private View ac;
    public VideoAdView g;
    public int h;
    public int i;
    public d j;
    public int k;
    public int l;
    public Class m;
    public boolean n;
    public e o;
    public int p;
    public int q;
    public int r;
    public long s;
    public ImageView t;
    public ImageView u;
    public ImageView v;
    public boolean w;
    protected long x;
    protected Timer y;
    protected int z;

    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (VideoAdView.this.h == 4 || VideoAdView.this.h == 5) {
                VideoAdView.this.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.VideoAdView.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        long currentPositionWhenPlaying = VideoAdView.this.getCurrentPositionWhenPlaying();
                        long duration = VideoAdView.this.getDuration();
                        VideoAdView.this.a((int) ((100 * currentPositionWhenPlaying) / (duration == 0 ? 1L : duration)), currentPositionWhenPlaying, duration);
                    }
                });
            }
        }
    }

    private interface b {
        void a(int i);
    }

    private class c extends BroadcastReceiver {
        b a;

        public c(b bVar) {
            this.a = bVar;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int streamVolume;
            if (!"android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction()) || intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) != 3 || this.a == null || (streamVolume = ((AudioManager) VideoAdView.this.getApplicationContext().getSystemService("audio")).getStreamVolume(3)) < 0) {
                return;
            }
            this.a.a(streamVolume);
        }
    }

    public VideoAdView(Context context) {
        super(context);
        this.h = -1;
        this.i = -1;
        this.k = 0;
        this.l = 0;
        this.n = true;
        this.p = -1;
        this.q = 0;
        this.r = -1;
        this.s = 0L;
        this.w = false;
        this.x = 0L;
        this.E = new AudioManager.OnAudioFocusChangeListener() { // from class: com.sigmob.sdk.videoplayer.VideoAdView.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                StringBuilder sb;
                String str;
                if (i != 1) {
                    switch (i) {
                        case -2:
                            sb = new StringBuilder();
                            str = "AUDIOFOCUS_LOSS_TRANSIENT [";
                            break;
                        case -1:
                            VideoAdView.this.D();
                            sb = new StringBuilder();
                            str = "AUDIOFOCUS_LOSS [";
                            break;
                        default:
                            return;
                    }
                    sb.append(str);
                    sb.append(hashCode());
                    sb.append("]");
                    SigmobLog.i(sb.toString());
                }
            }
        };
        this.Q = true;
        this.W = false;
        a(context);
    }

    public VideoAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = -1;
        this.i = -1;
        this.k = 0;
        this.l = 0;
        this.n = true;
        this.p = -1;
        this.q = 0;
        this.r = -1;
        this.s = 0L;
        this.w = false;
        this.x = 0L;
        this.E = new AudioManager.OnAudioFocusChangeListener() { // from class: com.sigmob.sdk.videoplayer.VideoAdView.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                StringBuilder sb;
                String str;
                if (i != 1) {
                    switch (i) {
                        case -2:
                            sb = new StringBuilder();
                            str = "AUDIOFOCUS_LOSS_TRANSIENT [";
                            break;
                        case -1:
                            VideoAdView.this.D();
                            sb = new StringBuilder();
                            str = "AUDIOFOCUS_LOSS [";
                            break;
                        default:
                            return;
                    }
                    sb.append(str);
                    sb.append(hashCode());
                    sb.append("]");
                    SigmobLog.i(sb.toString());
                }
            }
        };
        this.Q = true;
        this.W = false;
        a(context);
    }

    private void E() {
        try {
            if (this.o != null) {
                this.o.d();
            }
            this.o = this.m == null ? new f(this) : (e) this.m.getConstructor(VideoAdView.class).newInstance(this);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    public void A() {
    }

    public void B() {
        if (this.g == null || this.g.h == 0) {
            l();
            return;
        }
        if (this.g.h == 5) {
            if (this.P != null) {
                this.P.onVideoAdResume();
            }
            this.g.f();
            this.g.o.a();
            return;
        }
        if (this.g.h == 3) {
            if (this.P != null) {
                this.P.onVideoAdStartPlay();
            }
            this.g.o.a();
            this.g.f();
        }
    }

    public void C() {
        if (this.g != null) {
            if (this.g.h == 6 || this.g.h == 0 || this.g.h == 1 || this.g.h == 7) {
                D();
            } else if (this.g.h != 5) {
                this.g.g();
                this.g.o.c();
            }
        }
    }

    public void D() {
        SigmobLog.i("releaseAllVideos");
        if (this.g != null) {
            this.g.k();
            this.g = null;
        }
    }

    public void a() {
        D();
        if (this.V != null) {
            getContext().unregisterReceiver(this.V);
            this.V = null;
        }
    }

    public void a(int i, int i2) {
        String str;
        SigmobLog.d("onInfo what - " + i + " extra - " + i2);
        if (i == 3) {
            if (this.h == 3 || this.h == 2) {
                f();
                return;
            }
            return;
        }
        switch (i) {
            case 701:
                if (this.h == 4) {
                    this.ab = true;
                    this.o.c();
                    g();
                }
                str = "MEDIA_INFO_BUFFERING_START";
                break;
            case 702:
                if (this.ab) {
                    this.ab = false;
                    this.o.a();
                    f();
                }
                str = "MEDIA_INFO_BUFFERING_END";
                break;
            default:
                return;
        }
        SigmobLog.d(str);
    }

    public void a(int i, int i2, int i3) {
        switch (i) {
            case 0:
                b();
                break;
            case 1:
                c();
                break;
            case 2:
                a(i2, i3);
                break;
            case 4:
                f();
                break;
            case 5:
                g();
                break;
            case 6:
                i();
                break;
            case 7:
                h();
                break;
        }
    }

    public void a(int i, long j) {
        this.h = 2;
        this.s = j;
        this.j.a = i;
        this.o.a((Surface) null);
        this.o.d();
        this.o.b();
    }

    public void a(int i, long j, long j2) {
        SigmobLog.d("onProgress: progress=" + i + " position=" + j + " duration=" + j2);
        if (!this.C) {
            if (this.r != -1) {
                if (this.r > i) {
                    return;
                } else {
                    this.r = -1;
                }
            } else if (i != 0 && this.N != null) {
                this.N.setProgress(i);
            }
        }
        if (this.P != null) {
            this.P.onProgressUpdate(j, j2);
        }
    }

    public void a(Context context) {
        View.inflate(context, getLayoutId(), this);
        this.J = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_sound_rl"));
        this.G = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_start_rl"));
        this.H = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_fullscreen_rl"));
        this.I = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_replay_rl"));
        this.v = (ImageView) findViewById(o.a(getContext(), "sig_native_video_sound_btn"));
        this.t = (ImageView) findViewById(o.a(getContext(), "sig_native_video_start_btn"));
        this.u = (ImageView) findViewById(o.a(getContext(), "sig_native_video_fullscreen_btn"));
        this.F = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_surface_container"));
        this.M = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_app_container"));
        this.N = (ProgressBar) findViewById(o.a(getContext(), "sig_native_video_bottom_progress"));
        this.K = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_big_replay"));
        this.O = (ImageView) findViewById(o.a(getContext(), "sig_native_video_thumb"));
        this.S = (ImageView) findViewById(o.a(getContext(), "sig_native_video_blurImageView"));
        this.L = (ViewGroup) findViewById(o.a(getContext(), "sig_native_video_back_rl"));
        this.aa = findViewById(o.a(getContext(), "sig_native_video_layout_top"));
        this.ac = findViewById(o.a(getContext(), "sig_native_video_layout_bottom"));
        this.I.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.z = getContext().getResources().getDisplayMetrics().widthPixels;
        this.A = getContext().getResources().getDisplayMetrics().heightPixels;
        this.V = new c(new b() { // from class: com.sigmob.sdk.videoplayer.VideoAdView.2
            @Override // com.sigmob.sdk.videoplayer.VideoAdView.b
            public void a(int i) {
                ImageView imageView;
                Context context2;
                String str;
                if (i == 0 || VideoAdView.this.Q) {
                    imageView = VideoAdView.this.v;
                    context2 = VideoAdView.this.getContext();
                    str = "sig_image_video_mute";
                } else {
                    imageView = VideoAdView.this.v;
                    context2 = VideoAdView.this.getContext();
                    str = "sig_image_video_unmute";
                }
                imageView.setImageResource(o.c(context2, str));
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        context.registerReceiver(this.V, intentFilter);
        this.h = -1;
    }

    public void a(com.sigmob.sdk.videoplayer.c cVar, boolean z) {
        ViewGroup viewGroup;
        int i = 4;
        switch (cVar) {
            case START:
                viewGroup = this.G;
                if (z) {
                }
                a(viewGroup, i);
            case RETRY:
                viewGroup = this.I;
                if (z) {
                }
                a(viewGroup, i);
            case VOLUME:
                viewGroup = this.J;
                if (z) {
                }
                a(viewGroup, i);
            case FULLSCREEN:
                viewGroup = this.H;
                if (z) {
                }
                a(viewGroup, i);
            case BIGRETRY:
                viewGroup = this.K;
                if (z) {
                }
                a(viewGroup, i);
            case BACK:
                viewGroup = this.L;
                if (z) {
                }
                a(viewGroup, i);
            default:
                return;
        }
        i = 0;
        a(viewGroup, i);
    }

    public void a(d dVar, int i) {
        a(dVar, i, (Class) null);
    }

    public void a(d dVar, int i, Class cls) {
        if (System.currentTimeMillis() - this.x < 200) {
            return;
        }
        this.j = dVar;
        this.i = i;
        b();
        this.m = cls;
    }

    public void a(boolean z) {
        this.W = z;
    }

    public void b() {
        SigmobLog.d("onStateNormal stat" + this.h + " [" + hashCode() + "] ");
        this.h = 0;
        a(this.O, 0);
        a(this.G, 0);
        p();
        if (this.o != null) {
            this.o.d();
        }
    }

    public void b(int i, int i2) {
        SigmobLog.e("onError " + i + " - " + i2 + " [" + hashCode() + "] ");
        if (this.P != null) {
            this.P.onVideoError(i, i2);
        }
        if (i == 38 || i2 == -38 || i == -38 || i2 == 38 || i2 == -19) {
            return;
        }
        h();
        this.o.d();
    }

    public void c() {
        SigmobLog.d("onStatePreparing  [" + hashCode() + "] ");
        this.h = 1;
        q();
    }

    public void c(int i, int i2) {
        this.T = i2;
        this.U = i;
        SigmobLog.d("onVideoSizeChanged  [" + hashCode() + "] ");
        if (this.D != null) {
            if (this.q != 0) {
                this.D.setRotation(this.q);
            }
            this.D.a(i, i2);
        }
    }

    public boolean d() {
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService("audio");
        return audioManager != null && audioManager.getStreamVolume(3) == 0;
    }

    public void e() {
        SigmobLog.d("onPrepared  [" + hashCode() + "] ");
        this.h = 3;
        setSoundChange(this.Q);
        if (this.P != null) {
            this.P.onVideoLoad();
        }
        if (!this.w) {
            if (this.P != null) {
                this.P.onVideoAdStartPlay();
            }
            SigmobLog.d("mediaInterface start");
            f();
            this.o.a();
            this.w = false;
        } else if (this.W) {
            this.O.setImageBitmap(this.D.getBitmap());
        }
        if (this.j.a().toString().toLowerCase().contains("mp3") || this.j.a().toString().toLowerCase().contains("wma") || this.j.a().toString().toLowerCase().contains("aac") || this.j.a().toString().toLowerCase().contains("m4a") || this.j.a().toString().toLowerCase().contains("wav")) {
            f();
        }
    }

    public void f() {
        SigmobLog.d("onStatePlaying  [" + hashCode() + "] ");
        a(this.O, 4);
        a(this.G, 4);
        if (this.h == 3 && this.s != 0) {
            this.o.a(this.s);
            this.s = 0L;
        }
        this.h = 4;
        o();
    }

    public void g() {
        SigmobLog.i("onStatePause  [" + hashCode() + "] ");
        this.h = 5;
        a(this.G, 0);
        if (this.P != null) {
            this.P.onVideoAdPaused();
        }
        o();
    }

    public ViewGroup getAppContainer() {
        return this.M;
    }

    public Context getApplicationContext() {
        Context applicationContext;
        Context context = getContext();
        return (context == null || (applicationContext = context.getApplicationContext()) == null) ? context : applicationContext;
    }

    public ImageView getBlurImageView() {
        return this.S;
    }

    public View getBottomLayoutView() {
        return this.ac;
    }

    public long getCurrentPositionWhenPlaying() {
        if (this.h != 4 && this.h != 5) {
            return 0L;
        }
        try {
            return this.o.e();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public long getDuration() {
        try {
            return this.o.f();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public int getLayoutId() {
        return o.b(getContext(), "sig_video_player_layout");
    }

    public ViewGroup getSigAdView() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        while (!(viewGroup instanceof SigAdView) && (viewGroup = (ViewGroup) getParent()) != null) {
        }
        return viewGroup;
    }

    public Bitmap getTextureBitmap() {
        if (this.D != null) {
            return this.D.getBitmap();
        }
        return null;
    }

    public ImageView getThumbView() {
        return this.O;
    }

    public View getTopLayoutView() {
        return this.aa;
    }

    public int getVideoHeight() {
        return this.T;
    }

    public int getVideoSurferViewHeight() {
        if (this.D != null) {
            return this.D.getHeight();
        }
        return 0;
    }

    public int getVideoWidth() {
        return this.U;
    }

    public void h() {
        SigmobLog.d("onStateError  [" + hashCode() + "] ");
        this.h = 7;
        p();
        a(this.i == 1 ? this.G : this.I, 0);
    }

    public void i() {
        SigmobLog.i("onStateAutoComplete  [" + hashCode() + "] ");
        this.h = 6;
        p();
        if (this.N != null) {
            this.N.setProgress(100);
        }
    }

    public void j() {
        Runtime.getRuntime().gc();
        if (this.P != null) {
            this.P.onVideoAdComplete();
        }
        SigmobLog.d("onAutoCompletion  [" + hashCode() + "] ");
        p();
        A();
        y();
        z();
        i();
        Window windowC = com.sigmob.sdk.videoplayer.b.c(getContext());
        if (windowC != null) {
            windowC.clearFlags(128);
        }
        q();
        this.g = null;
    }

    public void k() {
        SigmobLog.d("reset  [" + hashCode() + "] ");
        p();
        q();
        A();
        y();
        z();
        b();
        this.F.removeAllViews();
        Window windowC = com.sigmob.sdk.videoplayer.b.c(getContext());
        if (windowC != null) {
            windowC.clearFlags(128);
        }
        if (this.o != null) {
            this.o.d();
        }
    }

    public void l() {
        SigmobLog.d("startVideo [" + hashCode() + "] ");
        setCurrentVideoAdView(this);
        try {
            E();
            a(this.I, 4);
            a(this.K, 4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        Window windowC = com.sigmob.sdk.videoplayer.b.c(getContext());
        if (windowC != null) {
            windowC.setFlags(DownloadExpSwitchCode.BACK_PARTIAL, DownloadExpSwitchCode.BACK_PARTIAL);
            windowC.addFlags(128);
        }
        m();
        c();
    }

    public void m() {
        SigmobLog.d("addTextureView [" + hashCode() + "] ");
        if (this.D != null) {
            this.F.removeView(this.D);
        }
        this.D = new VideoPlayerTextureView(getContext().getApplicationContext());
        this.D.setSurfaceTextureListener(this.o);
        this.F.addView(this.D, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    public void n() {
        Window windowC = com.sigmob.sdk.videoplayer.b.c(getContext());
        if (windowC != null) {
            ((ViewGroup) windowC.getDecorView()).removeView(this);
        }
    }

    public void o() {
        SigmobLog.d("startProgressTimer:  [" + hashCode() + "] ");
        p();
        this.y = new Timer();
        this.B = new a();
        this.y.schedule(this.B, 0L, 300L);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        onTouch(view, null);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.i == 1 || this.i == 2) {
            super.onMeasure(i, i2);
            return;
        }
        if (this.k == 0 || this.l == 0) {
            super.onMeasure(i, i2);
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        int i3 = (int) ((size * this.l) / this.k);
        setMeasuredDimension(size, i3);
        getChildAt(0).measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            getDuration();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        SigmobLog.d("bottomProgress onStartTrackingTouch [" + hashCode() + "] ");
        p();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        SigmobLog.d("bottomProgress onStopTrackingTouch [" + hashCode() + "] ");
        o();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        if (this.h == 4 || this.h == 5) {
            long progress = (seekBar.getProgress() * getDuration()) / 100;
            this.r = seekBar.getProgress();
            this.o.a(progress);
            SigmobLog.d("seekTo " + progress + " [" + hashCode() + "] ");
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() != 1) {
            return false;
        }
        if (view == this.H) {
            switch (this.i) {
                case 0:
                    r();
                    return false;
                case 1:
                    s();
                    return false;
                default:
                    return false;
            }
        }
        if (view == this.J) {
            setSoundChange(!this.Q);
            return false;
        }
        if (view == this.I || view == this.K) {
            if (this.P != null) {
                this.P.a();
            }
            l();
            return false;
        }
        if (view != this.G) {
            return false;
        }
        if (this.h != 0) {
            if (this.h == 4) {
                SigmobLog.d("pauseVideo [" + hashCode() + "] ");
                this.o.c();
                g();
            } else if (this.h == 5) {
                if (this.P != null) {
                    this.P.onVideoAdResume();
                }
                this.o.a();
                f();
            } else if (this.h != 6) {
                if (this.h == 3) {
                    this.o.a();
                    f();
                    if (this.P != null) {
                        this.P.onVideoAdStartPlay();
                    }
                }
            }
            return true;
        }
        if (!e && !this.j.a().toString().startsWith("file") && !this.j.a().toString().startsWith("/") && !com.sigmob.sdk.videoplayer.b.a(getContext())) {
            x();
            return true;
        }
        l();
        return true;
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:118)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
     */
    public void p() {
        if (this.y != null) {
            this.y.cancel();
        }
        if (this.B != null) {
            this.B.cancel();
        }
    }

    public void q() {
        if (this.N != null) {
            this.N.setProgress(0);
        }
    }

    public void r() {
        if (this.R != null) {
            this.R.e();
        }
        u();
        this.u.setImageResource(o.c(getContext(), "sig_image_video_small"));
    }

    public void s() {
        this.x = System.currentTimeMillis();
        if (this.R != null) {
            this.R.d();
        }
        t();
        this.u.setImageResource(o.c(getContext(), "sig_image_video_fullscreen"));
    }

    public void setBackClickListener(View.OnClickListener onClickListener) {
        if (this.L != null) {
            this.L.setOnClickListener(onClickListener);
        }
    }

    public void setBufferProgress(int i) {
        SigmobLog.d("setBufferProgress() called with: bufferProgress = [" + i + "]");
    }

    public void setCurrentVideoAdView(VideoAdView videoAdView) {
        this.g = videoAdView;
    }

    public void setHolderImageResource(int i) {
    }

    public void setMediaInterface(Class cls) {
        k();
        this.m = cls;
    }

    @Override // android.view.View
    public void setRotation(float f2) {
        this.D.setRotation(f2);
    }

    public void setScreen(int i) {
        switch (i) {
            case 0:
                t();
                break;
            case 1:
                u();
                break;
            case 2:
                v();
                break;
        }
    }

    public void setSoundChange(boolean z) {
        ImageView imageView;
        Context context;
        String str;
        this.Q = z;
        if (z) {
            if (this.o != null) {
                this.o.a(0.0f);
            }
            imageView = this.v;
            context = getContext();
            str = "sig_image_video_mute";
        } else {
            if (this.o != null) {
                this.o.a(1.0f);
            }
            imageView = this.v;
            context = getContext();
            str = "sig_image_video_unmute";
        }
        imageView.setImageResource(o.c(context, str));
    }

    public void setState(int i) {
        a(i, 0, 0);
    }

    public void setTextureViewRotation(int i) {
        if (this.g == null || this.g.D == null) {
            return;
        }
        this.g.D.setRotation(i);
    }

    public void setUp(String str) {
        a(new d(str, null), 0);
    }

    public void setVideoAdStatusListener(h hVar) {
        this.P = hVar;
    }

    public void setVideoAdViewListener(com.sigmob.sdk.videoplayer.a aVar) {
        this.R = aVar;
    }

    public void setVideoImageDisplayType(int i) {
        f = i;
        if (this.g == null || this.g.D == null) {
            return;
        }
        this.g.D.requestLayout();
    }

    public void t() {
        this.i = 0;
    }

    public void u() {
        this.i = 1;
    }

    public void v() {
        this.i = 2;
    }

    public void w() {
    }

    public void x() {
    }

    public void y() {
    }

    public void z() {
    }
}
