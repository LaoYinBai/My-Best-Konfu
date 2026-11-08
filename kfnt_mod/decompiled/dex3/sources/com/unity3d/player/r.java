package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class r extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    private static boolean a;
    private final Context b;
    private final SurfaceView c;
    private final SurfaceHolder d;
    private final String e;
    private final int f;
    private final int g;
    private final boolean h;
    private final long i;
    private final long j;
    private final FrameLayout k;
    private final Display l;
    private int m;
    private int n;
    private int o;
    private int p;
    private MediaPlayer q;
    private MediaController r;
    private boolean s;
    private boolean t;
    private int u;
    private boolean v;
    private boolean w;
    private a x;
    private b y;
    private volatile int z;

    public interface a {
        void a(int i);
    }

    public class b implements Runnable {
        private r b;
        private boolean c = false;

        public b(r rVar) {
            this.b = rVar;
        }

        public final void a() {
            this.c = true;
        }

        @Override // java.lang.Runnable
        public final void run() throws InterruptedException {
            try {
                Thread.sleep(AppLinkMonitor.DELAY_TIME);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (this.c) {
                return;
            }
            if (r.a) {
                r.b("Stopping the video player due to timeout.");
            }
            this.b.CancelOnPrepare();
        }
    }

    protected r(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, a aVar) {
        super(context);
        this.s = false;
        this.t = false;
        this.u = 0;
        this.v = false;
        this.w = false;
        this.z = 0;
        this.x = aVar;
        this.b = context;
        this.k = this;
        this.c = new SurfaceView(context);
        this.d = this.c.getHolder();
        this.d.addCallback(this);
        this.k.setBackgroundColor(i);
        this.k.addView(this.c);
        this.l = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        this.e = str;
        this.f = i2;
        this.g = i3;
        this.h = z;
        this.i = j;
        this.j = j2;
        if (a) {
            b("fileName: " + this.e);
        }
        if (a) {
            b("backgroundColor: " + i);
        }
        if (a) {
            b("controlMode: " + this.f);
        }
        if (a) {
            b("scalingMode: " + this.g);
        }
        if (a) {
            b("isURL: " + this.h);
        }
        if (a) {
            b("videoOffset: " + this.i);
        }
        if (a) {
            b("videoLength: " + this.j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void a(int i) {
        this.z = i;
        if (this.x != null) {
            this.x.a(this.z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    private void c() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        FileInputStream fileInputStream;
        if (this.q != null) {
            this.q.setDisplay(this.d);
            if (this.v) {
                return;
            }
            if (a) {
                b("Resuming playback");
            }
            this.q.start();
            return;
        }
        a(0);
        doCleanUp();
        try {
            this.q = new MediaPlayer();
            if (this.h) {
                this.q.setDataSource(this.b, Uri.parse(this.e));
            } else {
                if (this.j != 0) {
                    fileInputStream = new FileInputStream(this.e);
                    this.q.setDataSource(fileInputStream.getFD(), this.i, this.j);
                } else {
                    try {
                        AssetFileDescriptor assetFileDescriptorOpenFd = getResources().getAssets().openFd(this.e);
                        this.q.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                        assetFileDescriptorOpenFd.close();
                    } catch (IOException unused) {
                        fileInputStream = new FileInputStream(this.e);
                        this.q.setDataSource(fileInputStream.getFD());
                    }
                }
                fileInputStream.close();
            }
            this.q.setDisplay(this.d);
            this.q.setScreenOnWhilePlaying(true);
            this.q.setOnBufferingUpdateListener(this);
            this.q.setOnCompletionListener(this);
            this.q.setOnPreparedListener(this);
            this.q.setOnVideoSizeChangedListener(this);
            this.q.setAudioStreamType(3);
            this.q.prepareAsync();
            this.y = new b(this);
            new Thread(this.y).start();
        } catch (Exception e) {
            if (a) {
                b("error: " + e.getMessage() + e);
            }
            a(2);
        }
    }

    private void d() throws IllegalStateException {
        if (isPlaying()) {
            return;
        }
        a(1);
        if (a) {
            b("startVideoPlayback");
        }
        updateVideoLayout();
        if (this.v) {
            return;
        }
        start();
    }

    public final void CancelOnPrepare() {
        a(2);
    }

    final boolean a() {
        return this.v;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean canSeekBackward() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean canSeekForward() {
        return true;
    }

    protected final void destroyPlayer() throws IllegalStateException {
        if (a) {
            b("destroyPlayer");
        }
        if (!this.v) {
            pause();
        }
        doCleanUp();
    }

    protected final void doCleanUp() {
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        if (this.q != null) {
            this.q.release();
            this.q = null;
        }
        this.o = 0;
        this.p = 0;
        this.t = false;
        this.s = false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final int getBufferPercentage() {
        if (this.h) {
            return this.u;
        }
        return 100;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final int getCurrentPosition() {
        if (this.q == null) {
            return 0;
        }
        return this.q.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final int getDuration() {
        if (this.q == null) {
            return 0;
        }
        return this.q.getDuration();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final boolean isPlaying() {
        boolean z = this.t && this.s;
        return this.q == null ? !z : this.q.isPlaying() || !z;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (a) {
            b("onBufferingUpdate percent:" + i);
        }
        this.u = i;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) throws IllegalStateException {
        if (a) {
            b("onCompletion called");
        }
        destroyPlayer();
        a(3);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) throws IllegalStateException {
        if (i != 4 && (this.f != 2 || i == 0 || keyEvent.isSystem())) {
            return this.r != null ? this.r.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        }
        destroyPlayer();
        a(3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) throws IllegalStateException {
        if (a) {
            b("onPrepared called");
        }
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        if (this.f == 0 || this.f == 1) {
            this.r = new MediaController(this.b);
            this.r.setMediaPlayer(this);
            this.r.setAnchorView(this);
            this.r.setEnabled(true);
            if (this.b instanceof Activity) {
                this.r.setSystemUiVisibility(((Activity) this.b).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.r.show();
        }
        this.t = true;
        if (this.t && this.s) {
            d();
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) throws IllegalStateException {
        int action = motionEvent.getAction() & 255;
        if (this.f != 2 || action != 0) {
            return this.r != null ? this.r.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        }
        destroyPlayer();
        a(3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) throws IllegalStateException {
        if (a) {
            b("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.s = true;
            this.o = i;
            this.p = i2;
            if (this.t && this.s) {
                d();
                return;
            }
            return;
        }
        if (a) {
            b("invalid video width(" + i + ") or height(" + i2 + com.umeng.message.proguard.l.t);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final void pause() throws IllegalStateException {
        if (this.q == null) {
            return;
        }
        if (this.w) {
            this.q.pause();
        }
        this.v = true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final void seekTo(int i) throws IllegalStateException {
        if (this.q == null) {
            return;
        }
        this.q.seekTo(i);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public final void start() throws IllegalStateException {
        if (a) {
            b("Start");
        }
        if (this.q == null) {
            return;
        }
        if (this.w) {
            this.q.start();
        }
        this.v = false;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (a) {
            b("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.m == i2 && this.n == i3) {
            return;
        }
        this.m = i2;
        this.n = i3;
        if (this.w) {
            updateVideoLayout();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        if (a) {
            b("surfaceCreated called");
        }
        this.w = true;
        c();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (a) {
            b("surfaceDestroyed called");
        }
        this.w = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void updateVideoLayout() {
        /*
            r6 = this;
            boolean r0 = com.unity3d.player.r.a
            if (r0 == 0) goto L9
            java.lang.String r0 = "updateVideoLayout"
            b(r0)
        L9:
            android.media.MediaPlayer r0 = r6.q
            if (r0 != 0) goto Le
            return
        Le:
            int r0 = r6.m
            if (r0 == 0) goto L16
            int r0 = r6.n
            if (r0 != 0) goto L34
        L16:
            android.content.Context r0 = r6.b
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.util.DisplayMetrics r1 = new android.util.DisplayMetrics
            r1.<init>()
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getMetrics(r1)
            int r0 = r1.widthPixels
            r6.m = r0
            int r0 = r1.heightPixels
            r6.n = r0
        L34:
            int r0 = r6.m
            int r1 = r6.n
            boolean r2 = r6.s
            if (r2 == 0) goto L73
            int r2 = r6.o
            float r2 = (float) r2
            int r3 = r6.p
            float r3 = (float) r3
            float r2 = r2 / r3
            int r3 = r6.m
            float r3 = (float) r3
            int r4 = r6.n
            float r4 = (float) r4
            float r3 = r3 / r4
            int r4 = r6.g
            r5 = 1
            if (r4 != r5) goto L60
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 > 0) goto L59
        L53:
            int r1 = r6.m
            float r1 = (float) r1
            float r1 = r1 / r2
            int r1 = (int) r1
            goto L7c
        L59:
            int r0 = r6.n
            float r0 = (float) r0
            float r0 = r0 * r2
            int r0 = (int) r0
            goto L7c
        L60:
            int r4 = r6.g
            r5 = 2
            if (r4 != r5) goto L6a
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 < 0) goto L59
            goto L53
        L6a:
            int r2 = r6.g
            if (r2 != 0) goto L7c
            int r0 = r6.o
            int r1 = r6.p
            goto L7c
        L73:
            boolean r2 = com.unity3d.player.r.a
            if (r2 == 0) goto L7c
            java.lang.String r2 = "updateVideoLayout: Video size is not known yet"
            b(r2)
        L7c:
            int r2 = r6.m
            if (r2 != r0) goto L84
            int r2 = r6.n
            if (r2 == r1) goto Laf
        L84:
            boolean r2 = com.unity3d.player.r.a
            if (r2 == 0) goto La1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "frameWidth = "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = "; frameHeight = "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            b(r2)
        La1:
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 17
            r2.<init>(r0, r1, r3)
            android.widget.FrameLayout r0 = r6.k
            android.view.SurfaceView r1 = r6.c
            r0.updateViewLayout(r1, r2)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.r.updateVideoLayout():void");
    }
}
