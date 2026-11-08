package com.unity3d.player;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.sigmob.sdk.common.Constants;
import com.tachikoma.core.utility.UriUtil;
import com.umeng.message.util.HttpRequest;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class m extends RelativeLayout {
    UnityPlayer a;
    l b;
    Context c;
    int d;
    VideoView e;
    ImageView f;
    ImageView g;
    TextView h;
    Timer i;
    int j;
    int k;
    boolean l;
    TimerTask m;

    class a extends AsyncTask {
        private String[] b;

        a() {
        }

        private static void a(String str) throws Throwable {
            HttpURLConnection httpURLConnection;
            HttpURLConnection httpURLConnection2 = null;
            try {
                httpURLConnection = (HttpURLConnection) new URL("https://splash.unity.cn/api/diagnosis").openConnection();
                try {
                    httpURLConnection.setConnectTimeout(30000);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", HttpRequest.CONTENT_TYPE_JSON);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", "tracking");
                    jSONObject.put("reason", str);
                    jSONObject.put("platform", "android");
                    byte[] bytes = jSONObject.toString().getBytes();
                    outputStream.write(bytes, 0, bytes.length);
                    outputStream.flush();
                    outputStream.close();
                    httpURLConnection.getResponseCode();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Exception unused) {
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:20:0x007f A[PHI: r2
  0x007f: PHI (r2v4 java.net.HttpURLConnection) = (r2v2 java.net.HttpURLConnection), (r2v7 java.net.HttpURLConnection) binds: [B:28:0x00a5, B:19:0x007d] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Void doInBackground(java.lang.String... r9) throws java.lang.Throwable {
            /*
                r8 = this;
                r8.b = r9
                java.lang.String[] r9 = r8.b
                r0 = 0
                if (r9 != 0) goto L8
                return r0
            L8:
                r9 = 0
            L9:
                java.lang.String[] r1 = r8.b
                int r1 = r1.length
                if (r9 >= r1) goto Lb3
                java.lang.String[] r1 = r8.b
                r1 = r1[r9]
                if (r1 == 0) goto Laf
                java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L88
                java.lang.String r3 = com.unity3d.player.m.a(r1)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L88
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L88
                java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L88
                java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L88
                r3 = 30000(0x7530, float:4.2039E-41)
                r2.setConnectTimeout(r3)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.lang.String r3 = "GET"
                r2.setRequestMethod(r3)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                int r3 = r2.getResponseCode()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r4 = 400(0x190, float:5.6E-43)
                if (r3 < r4) goto L7d
                java.io.InputStream r4 = r2.getErrorStream()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r6.<init>(r4)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r5.<init>(r6)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.lang.String r4 = ""
            L45:
                java.lang.String r6 = r5.readLine()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                if (r6 == 0) goto L5b
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r7.<init>()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r7.append(r4)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r7.append(r6)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.lang.String r4 = r7.toString()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                goto L45
            L5b:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r6.<init>()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r6.append(r1)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.lang.String r7 = " - "
                r6.append(r7)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r6.append(r3)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.lang.String r3 = " - "
                r6.append(r3)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r6.append(r4)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                java.lang.String r3 = r6.toString()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                a(r3)     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
                r5.close()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> La8
            L7d:
                if (r2 == 0) goto Laf
            L7f:
                r2.disconnect()
                goto Laf
            L83:
                r3 = move-exception
                goto L8a
            L85:
                r9 = move-exception
                r2 = r0
                goto La9
            L88:
                r3 = move-exception
                r2 = r0
            L8a:
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8
                r4.<init>()     // Catch: java.lang.Throwable -> La8
                r4.append(r1)     // Catch: java.lang.Throwable -> La8
                java.lang.String r1 = " - "
                r4.append(r1)     // Catch: java.lang.Throwable -> La8
                java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> La8
                r4.append(r1)     // Catch: java.lang.Throwable -> La8
                java.lang.String r1 = r4.toString()     // Catch: java.lang.Throwable -> La8
                a(r1)     // Catch: java.lang.Throwable -> La8
                if (r2 == 0) goto Laf
                goto L7f
            La8:
                r9 = move-exception
            La9:
                if (r2 == 0) goto Lae
                r2.disconnect()
            Lae:
                throw r9
            Laf:
                int r9 = r9 + 1
                goto L9
            Lb3:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.m.a.doInBackground(java.lang.String[]):java.lang.Void");
        }
    }

    public m(Context context, UnityPlayer unityPlayer, l lVar) throws SecurityException, IllegalArgumentException {
        super(context);
        this.d = 5;
        this.j = 0;
        this.k = this.d;
        this.m = new TimerTask() { // from class: com.unity3d.player.m.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public final void run() {
                m.this.a.a(new Runnable() { // from class: com.unity3d.player.m.4.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (m.this.l) {
                            m mVar = m.this;
                            mVar.k--;
                            m.this.h.setText("Skip " + m.this.k);
                            if (m.this.k <= 0) {
                                m.this.i();
                            }
                        }
                    }
                });
            }
        };
        this.c = context;
        this.a = unityPlayer;
        this.b = lVar;
        d();
        e();
        g();
    }

    private static String a(String str, String str2) {
        if (str == null || str.indexOf(63) == -1) {
            return null;
        }
        for (String str3 : str.split("\\?")[1].split("&")) {
            String[] strArrSplit = str3.split("=");
            if (str2.equals(strArrSplit[0])) {
                return strArrSplit[1];
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (!str.startsWith("deeplinker")) {
            if (e(str)) {
                c(str);
                return;
            }
            return;
        }
        String strF = f(a(str, "primaryUrl"));
        String strF2 = f(a(str, "fallbackUrl"));
        if (d(strF) && strF != null && e(strF)) {
            if (this.b.h() != null) {
                new a().execute(this.b.h());
            }
            c(strF);
        } else {
            if (strF2 == null || !e(strF2)) {
                return;
            }
            if (this.b.i() != null) {
                new a().execute(this.b.i());
            }
            c(strF2);
        }
    }

    private void c(String str) {
        if (str == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.c.startActivity(intent);
        if (this.b.e() != null) {
            new a().execute(this.b.e());
        }
    }

    private void d() throws SecurityException, IllegalArgumentException {
        if (!"VIDEO".equals(this.b.j())) {
            String strB = this.b.b();
            if (this.b.b() == null || this.b.b() == "" || this.f != null) {
                return;
            }
            if (strB.startsWith(UriUtil.FILE_PREFIX)) {
                strB = strB.substring(7);
            }
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(strB);
            if (bitmapDecodeFile == null) {
                return;
            }
            this.f = new ImageView(this.c);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(11);
            layoutParams.addRule(9);
            this.f.setLayoutParams(layoutParams);
            this.f.setImageBitmap(bitmapDecodeFile);
            this.f.setScaleType(ImageView.ScaleType.CENTER_CROP);
            addView(this.f);
            return;
        }
        if (this.b.k() == null || this.b.k() == "" || this.e != null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(11);
        layoutParams2.addRule(9);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.c, Uri.parse(this.b.k()));
        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(0L, 2);
        ImageView imageView = new ImageView(this.c);
        imageView.setLayoutParams(layoutParams2);
        imageView.setImageBitmap(frameAtTime);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(imageView);
        this.e = new VideoView(this.c);
        this.e.setLayoutParams(layoutParams2);
        this.e.setVideoPath(this.b.k());
        addView(this.e);
    }

    private boolean d(String str) {
        if (str == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        return this.c.getPackageManager().resolveActivity(intent, 0) != null;
    }

    private void e() {
        if (this.a.getShowSplashSlogan().booleanValue()) {
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("unity_splash_slogan", "drawable", this.c.getPackageName()), new BitmapFactory.Options());
            this.g = new ImageView(this.c);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.a.getShowSplashSloganHeight());
            layoutParams.addRule(11);
            layoutParams.addRule(9);
            layoutParams.addRule(12);
            this.g.setLayoutParams(layoutParams);
            this.g.setImageBitmap(bitmapDecodeResource);
            this.g.setScaleType(ImageView.ScaleType.CENTER);
            this.g.setBackgroundColor(-1);
            addView(this.g);
        }
    }

    private static boolean e(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith(Constants.HTTP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Uri.encode(URLDecoder.decode(str, "UTF-8"), "@#&=*+-_.,:!?()/~'%");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    private void f() {
        this.h = new TextView(this.c);
        String str = "Skip " + this.d;
        if (this.e != null) {
            str = "Skip";
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        layoutParams.rightMargin = 48;
        layoutParams.topMargin = 72;
        this.h.setLayoutParams(layoutParams);
        this.h.setText(str);
        this.h.setTextSize(15.0f);
        this.h.setTextColor(-1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-7829368);
        gradientDrawable.setCornerRadius(12.0f);
        gradientDrawable.setStroke(3, -1);
        this.h.setBackground(gradientDrawable);
        this.h.setPadding(20, 5, 20, 5);
        this.h.setAlpha(0.8f);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.unity3d.player.m.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                m.this.h();
            }
        });
        addView(this.h);
    }

    private void g() {
        TextView textView = new TextView(this.c);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(10);
        layoutParams.leftMargin = 32;
        layoutParams.topMargin = 64;
        textView.setLayoutParams(layoutParams);
        textView.setText("ADS");
        textView.setTextSize(8.0f);
        textView.setTextColor(-7829368);
        textView.setAlpha(0.8f);
        textView.setPadding(10, 5, 10, 5);
        addView(textView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.e != null) {
            this.e.stopPlayback();
        }
        if (this.i != null) {
            this.i.cancel();
        }
        this.a.NotifySplashAdsFinished();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.b.l() != null) {
            new a().execute(this.b.l());
        }
        h();
    }

    public final void a() {
        if (this.b.f() > 0) {
            this.d = this.b.f();
            this.k = this.d;
        }
        f();
        if (this.e != null) {
            this.e.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.unity3d.player.m.2
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    m.this.i();
                }
            });
            this.e.start();
        } else {
            this.i = new Timer();
            this.l = true;
            this.i.schedule(this.m, 1000L, 1000L);
        }
        if (this.b.d() != null) {
            new a().execute(this.b.d());
        }
        setOnTouchListener(new View.OnTouchListener() { // from class: com.unity3d.player.m.3
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                m.this.b(m.this.b.c());
                return true;
            }
        });
    }

    public final void b() {
        if (this.e != null) {
            this.e.pause();
            this.j = this.e.getCurrentPosition();
        }
        if (this.i != null) {
            this.l = false;
        }
    }

    public final void c() {
        if (this.e != null) {
            this.e.start();
            this.e.seekTo(this.j);
        }
        if (this.i != null) {
            this.l = true;
        }
    }
}
