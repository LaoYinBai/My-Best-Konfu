package com.sigmob.sdk.videoAd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.d;
import com.sigmob.sdk.base.common.i;
import com.sigmob.sdk.base.common.j;
import com.sigmob.sdk.base.common.l;
import com.sigmob.sdk.base.common.p;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.base.views.AdLogoView;
import com.sigmob.sdk.base.views.AlertDialogWidget;
import com.sigmob.sdk.base.views.CompanionAdsWidget;
import com.sigmob.sdk.base.views.CreativeWebView;
import com.sigmob.sdk.base.views.SkipButtonWidget;
import com.sigmob.sdk.base.views.VideoButtonWidget;
import com.sigmob.sdk.base.views.VideoProgressBarWidget;
import com.sigmob.sdk.base.views.e;
import com.sigmob.sdk.base.views.g;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.videoAd.g;
import com.sigmob.volley.toolbox.k;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.ss.union.game.sdk.core.base.event.INetUploadStrategy;
import com.tachikoma.core.component.input.ReturnKeyType;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h extends b implements d.a, g.a {
    private static int h;
    private boolean A;
    private com.sigmob.sdk.videoAd.a B;
    private int C;
    private BaseVideoView D;
    private BaseAdUnit E;
    private View F;
    private p G;
    private VideoProgressBarWidget H;
    private VideoButtonWidget I;
    private SkipButtonWidget J;
    private g K;
    private c L;
    private int M;
    private int N;
    private boolean O;
    private boolean P;
    private int Q;
    private boolean R;
    private RelativeLayout S;
    private boolean T;
    private VideoButtonWidget U;
    private boolean V;
    private boolean W;
    private k.c X;
    private CompanionAdsWidget Y;
    private boolean Z;
    private boolean aa;
    private boolean ab;
    private int ac;
    private com.sigmob.sdk.base.views.g ad;
    private boolean ae;
    private com.sigmob.sdk.base.views.e af;
    private boolean ag;
    String g;
    private AdLogoView i;
    private ImageView j;
    private RelativeLayout k;
    private int l;
    private boolean m;
    private int n;
    private String o;
    private List<String> p;
    private List<String> q;
    private long r;
    private String s;
    private String t;
    private boolean u;
    private Handler v;
    private boolean w;
    private AlertDialogWidget x;
    private String y;
    private int z;

    static class a {
        a() {
        }

        @JavascriptInterface
        public int getInteractionType() {
            return h.h;
        }

        @JavascriptInterface
        public String getSdkVersion() {
            return Constants.SDK_VERSION;
        }
    }

    public h(final Activity activity, BaseAdUnit baseAdUnit, Bundle bundle, Bundle bundle2, String str, com.sigmob.sdk.base.common.h hVar) {
        int i;
        super(activity, str, hVar);
        this.l = 0;
        this.m = false;
        this.n = 0;
        this.o = "undone";
        this.p = new ArrayList();
        this.q = new ArrayList();
        this.s = "none";
        this.t = null;
        this.v = new Handler();
        this.w = true;
        this.E = null;
        this.M = 0;
        this.R = false;
        this.T = false;
        this.V = false;
        this.W = false;
        this.Z = false;
        this.ae = false;
        this.ag = false;
        this.t = bundle.getString("ad_scene");
        this.E = baseAdUnit;
        this.B = (com.sigmob.sdk.videoAd.a) this.E.getAdConfig();
        this.B.a(k(), this.E, (e.b) null);
        this.y = this.E.getAdslot_id();
        h = this.E.getMaterial().interaction_type.intValue();
        switch (this.E.getAd().display_orientation.intValue()) {
            case 1:
                i = 7;
                break;
            case 2:
                i = 6;
                break;
            default:
                i = bundle.getInt(Constants.REQUESTED_ORIENTATION, 3);
                break;
        }
        this.C = i;
        h().a(this.C);
        k().requestWindowFeature(1);
        k().getWindow().addFlags(com.ss.union.game.sdk.feedback.picture.select.d.d);
        b(bundle);
        this.E.getMacroCommon().addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, "1");
        this.w = this.E.getMaterial().creative_type.intValue() != j.CreativeTypeVideo_transparent_html.a();
        if (bundle2 != null) {
            this.N = bundle2.getInt("current_position", -1);
            this.O = bundle2.getBoolean("video_finished", false);
            this.R = bundle2.getBoolean("companionAd_visable", false);
        }
        if (this.B.a() == null) {
            throw new IllegalStateException("BaseVideoConfig does not have a video disk path");
        }
        int iB = com.sigmob.sdk.base.b.b();
        if (iB != 0) {
            k().setTheme(iB);
        }
        j().setBackgroundColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.k = new RelativeLayout(activity);
        this.k.setBackgroundColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.D = a(activity, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        this.k.addView(this.D, layoutParams);
        j().addView(this.k, new RelativeLayout.LayoutParams(-1, -1));
        if (this.m) {
            a(activity);
            x();
        }
        l();
        this.k.setClickable(true);
        this.k.setOnTouchListener(new View.OnTouchListener() { // from class: com.sigmob.sdk.videoAd.h.1
            MotionEvent a = null;

            /* JADX WARN: Removed duplicated region for block: B:50:0x01f2  */
            @Override // android.view.View.OnTouchListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public boolean onTouch(android.view.View r10, android.view.MotionEvent r11) throws java.io.UnsupportedEncodingException {
                /*
                    Method dump skipped, instructions count: 505
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.videoAd.h.AnonymousClass1.onTouch(android.view.View, android.view.MotionEvent):boolean");
            }
        });
    }

    private void A() {
        try {
            if (this.O) {
                return;
            }
            H();
            this.D.pause();
            SigmobLog.i("videoView.pause()");
            this.N = r();
            m().a(com.sigmob.sdk.base.common.a.AD_PAUSE, r());
            this.B.b(i(), this.N);
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float B() {
        return r() / q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        try {
            this.M = this.B.i(q());
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    private void D() {
        t.a(PointCategory.PLAY_LOADING, (String) null, this.E, new t.a() { // from class: com.sigmob.sdk.videoAd.h.19
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    HashMap map = new HashMap();
                    map.put("duration_seq", h.this.a((List<String>) h.this.p));
                    map.put("video_time_seq", h.this.a((List<String>) h.this.q));
                    map.put("skip_state", h.this.s);
                    map.put("video_duration", String.format("%.2f", Float.valueOf(h.this.q() / 1000.0f)));
                    map.put("endcard_loading_state", h.this.o);
                    ((PointEntitySigmob) obj).setOptions(map);
                }
            }
        });
    }

    private void E() {
        int iK = this.B.k();
        int iQ = q() - 2;
        if (iK == 99999999) {
            iK = 0;
        } else if (iK == -99999999) {
            a(iQ);
            return;
        } else if (iK <= 0) {
            iK += iQ;
        }
        a(iK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        s();
        if (!this.w && this.j != null) {
            ViewParent parent = this.j.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.j);
            }
            j().addView(this.j, 0);
        }
        com.sigmob.sdk.base.common.d.f().b(this);
        if (this.I == null) {
            e(i(), 0);
        } else {
            this.I.setVisibility(0);
        }
        if (this.i != null) {
            this.i.setVisibility(8);
        }
        D();
        y();
        if (this.F == null) {
            SigmobLog.e("endcard can't show " + this.E.getEndCardIndexPath());
            HashMap map = new HashMap();
            map.put("error", "endcard can't show");
            a(IntentActions.ACTION_REWARDED_VIDEO_PLAYFAIL, map);
            this.f.a();
        }
        if (this.F != null) {
            this.F.setVisibility(0);
            this.F.bringToFront();
        }
        if (this.k != null) {
            this.k.setVisibility(4);
            a(IntentActions.ACTION_REWARDED_VIDEO_CLOSE);
        }
        if (this.J != null) {
            this.J.setVisibility(8);
        }
        if (this.U != null) {
            this.U.setVisibility(8);
        }
        if (this.Z) {
            this.H.setVisibility(8);
        }
        if (this.Y != null) {
            this.Y.setVisibility(8);
        }
        this.R = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        if (this.L != null) {
            this.L.a(50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        if (this.L != null) {
            this.L.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sigmob.sdk.videoAd.h.10
            @Override // java.lang.Runnable
            public void run() {
                h.this.a(IntentActions.ACTION_INTERSTITIAL_CLICK);
            }
        });
    }

    private View a(Context context, g gVar, int i) {
        m.a.a(context);
        if (gVar == null) {
            View view = new View(context);
            view.setVisibility(4);
            return view;
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(17);
        j().addView(relativeLayout, 0, new RelativeLayout.LayoutParams(-1, -1));
        CreativeWebView creativeWebViewA = a(context, gVar);
        creativeWebViewA.setVisibility(i);
        relativeLayout.addView(creativeWebViewA, new LinearLayout.LayoutParams(-1, -1));
        return creativeWebViewA;
    }

    private CreativeWebView a(final Context context, final g gVar) {
        m.a.a(context);
        m.a.a(gVar);
        m.a.a(gVar.a());
        final CreativeWebView creativeWebViewA = CreativeWebView.a(context, this.E.getAd_source_logo(), false, this.E.getInvisibleAdLabel());
        creativeWebViewA.addJavascriptInterface(new a(), "sigVersion");
        creativeWebViewA.setWebViewClickListener(new CreativeWebView.a() { // from class: com.sigmob.sdk.videoAd.h.7
            @Override // com.sigmob.sdk.base.views.CreativeWebView.a
            public void a(MotionEvent motionEvent, MotionEvent motionEvent2) throws UnsupportedEncodingException {
                h hVar;
                String str;
                Object[] objArr;
                if (h.this.I.getVisibility() != 0 || h.this.T) {
                    SigmobLog.w("ignore invalid click");
                    return;
                }
                t.a(h.this.E, motionEvent2, PointCategory.ENDCARDCLICK, true);
                if (motionEvent == null) {
                    hVar = h.this;
                    str = "%d,%d,%d,%d";
                    objArr = new Object[]{Integer.valueOf((int) motionEvent2.getRawX()), Integer.valueOf((int) motionEvent2.getRawY()), Integer.valueOf((int) motionEvent2.getRawX()), Integer.valueOf((int) motionEvent2.getRawY())};
                } else {
                    hVar = h.this;
                    str = "%d,%d,%d,%d";
                    objArr = new Object[]{Integer.valueOf((int) motionEvent.getRawX()), Integer.valueOf((int) motionEvent.getRawY()), Integer.valueOf((int) motionEvent2.getRawX()), Integer.valueOf((int) motionEvent2.getRawY())};
                }
                hVar.g = String.format(str, objArr);
                SigMacroCommon macroCommon = h.this.E.getMacroCommon();
                if (macroCommon instanceof SigMacroCommon) {
                    SigMacroCommon sigMacroCommon = macroCommon;
                    sigMacroCommon.addMarcoKey(SigMacroCommon._DOWNX_, String.valueOf((int) motionEvent.getRawX()));
                    sigMacroCommon.addMarcoKey(SigMacroCommon._DOWNY_, String.valueOf((int) motionEvent.getRawY()));
                    sigMacroCommon.addMarcoKey(SigMacroCommon._UPX_, String.valueOf((int) motionEvent2.getRawX()));
                    sigMacroCommon.addMarcoKey(SigMacroCommon._UPY_, String.valueOf((int) motionEvent2.getRawY()));
                }
                h.this.u = true;
                if (h.this.E.getMaterial().click_type.intValue() != i.FullScreen.a() || TextUtils.isEmpty(h.this.E.getMaterial().landing_page) || h.this.E.getMaterial().creative_type.intValue() == j.CreativeTypeVideo_EndCardURL.a()) {
                    h.this.aa = true;
                } else {
                    gVar.a(context, (String) null, com.sigmob.sdk.base.a.ENDCARD, h.this.a(), h.this.g);
                }
            }
        });
        creativeWebViewA.setDownloadListener(new DownloadListener() { // from class: com.sigmob.sdk.videoAd.h.8
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (h.this.u) {
                    gVar.c(h.this.E);
                    if (TextUtils.isEmpty(h.this.E.getMaterial().landing_page) || h.this.E.getMaterial().interaction_type.intValue() != 2) {
                        l.a(creativeWebViewA.getContext(), str, h.this.E);
                        t.a(PointCategory.COMPANION, "click", h.this.E, Constants.FAIL, str, h.this.g, h.this.Q);
                        h.this.I();
                    } else {
                        gVar.a(context, (String) null, com.sigmob.sdk.base.a.ENDCARD, h.this.a(), h.this.g);
                    }
                    SigmobLog.d("onDownloadStart() called with: url = [" + str + "], userAgent = [" + str2 + "], contentDisposition = [" + str3 + "], mimetype = [" + str4 + "], contentLength = [" + j + "]");
                }
            }
        });
        creativeWebViewA.setWebViewClient(new WebViewClient() { // from class: com.sigmob.sdk.videoAd.h.9
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                SigmobLog.d("onPageFinished: ");
                h.this.o = ReturnKeyType.DONE;
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                SigmobLog.d("onPageStarted: ");
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                if (str2.startsWith("http://localhost")) {
                    return;
                }
                h.this.o = "error";
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (Build.VERSION.SDK_INT >= 21) {
                    String host = webResourceRequest.getUrl().getHost();
                    if (TextUtils.isEmpty(host) || !host.equals("localhost")) {
                        return;
                    }
                    h.this.o = "error";
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Context context2;
                Uri uri;
                try {
                    SigmobLog.d("load url " + str);
                    context2 = webView.getContext();
                    uri = Uri.parse(str);
                    if ("sigmobAd".equalsIgnoreCase(uri.getScheme())) {
                        String host = uri.getHost();
                        if (!TextUtils.isEmpty(host)) {
                            if (!"track".equals(host) && !"active".equals(host)) {
                                if ("openFourElements".equals(host)) {
                                    h.this.b(2);
                                    return true;
                                }
                            }
                            String queryParameter = uri.getQueryParameter("data");
                            String queryParameter2 = uri.getQueryParameter(INetUploadStrategy.KEY_EVENT);
                            if (!TextUtils.isEmpty(queryParameter2) && !TextUtils.isEmpty(queryParameter)) {
                                final String str2 = new String(Base64.decode(queryParameter, 0), "utf-8");
                                t.a(host, queryParameter2, h.this.E, new t.a() { // from class: com.sigmob.sdk.videoAd.h.9.1
                                    @Override // com.sigmob.sdk.base.common.t.a
                                    public void a(Object obj) {
                                        if (obj instanceof PointEntitySigmob) {
                                            ((PointEntitySigmob) obj).setUrl(str2);
                                        }
                                    }
                                });
                            }
                            return true;
                        }
                    }
                } catch (Throwable unused) {
                    h.this.c_();
                    SigmobLog.e("webview");
                }
                if (h.this.T) {
                    return true;
                }
                if (h.this.E.getMaterial().disable_auto_deeplink.booleanValue() && !h.this.u) {
                    return TextUtils.isEmpty(str) || !str.startsWith(Constants.HTTP);
                }
                if (h.this.E.getMaterial().click_type.intValue() == i.Button.a() || TextUtils.isEmpty(h.this.E.getMaterial().landing_page) || h.this.E.getMaterial().creative_type.intValue() == j.CreativeTypeVideo_EndCardURL.a()) {
                    if (h.this.I.getVisibility() != 0 || TextUtils.isEmpty(uri.getScheme()) || uri.getScheme().equals(Constants.HTTP) || uri.getScheme().equals(Constants.HTTPS)) {
                        if (!TextUtils.isEmpty(uri.getScheme()) && (uri.getScheme().equals(Constants.HTTP) || uri.getScheme().equals(Constants.HTTPS))) {
                            if (h.this.aa) {
                                gVar.c(h.this.E);
                                t.a(PointCategory.ENDCARD, "click", h.this.E, Constants.FAIL, str, h.this.g, h.this.q());
                                h.this.I();
                                h.this.aa = false;
                            }
                            webView.loadUrl(str);
                        }
                    } else if (!uri.getScheme().equalsIgnoreCase("sigmobAd") || TextUtils.isEmpty(uri.getHost()) || !uri.getHost().equalsIgnoreCase("download") || TextUtils.isEmpty(h.this.E.getMaterial().landing_page)) {
                        if (TextUtils.isEmpty(gVar.b())) {
                            gVar.a(str);
                        }
                        gVar.a(context2, (String) null, com.sigmob.sdk.base.a.ENDCARD, h.this.a(), h.this.g);
                    } else {
                        gVar.a(context2, str, com.sigmob.sdk.base.a.ENDCARD, h.this.a(), h.this.g);
                    }
                } else if (h.this.E.getMaterial().click_type.intValue() != i.FullScreen.a() || TextUtils.isEmpty(h.this.E.getMaterial().landing_page)) {
                    return true;
                }
                return true;
            }
        });
        gVar.a().a(creativeWebViewA);
        return creativeWebViewA;
    }

    private BaseVideoView a(Context context, int i) {
        if (this.B.a() == null) {
            throw new IllegalStateException("BaseVideoConfig does not have a video disk path");
        }
        final BaseVideoView baseVideoView = new BaseVideoView(context);
        baseVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.sigmob.sdk.videoAd.h.16
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) throws IllegalStateException, UnsupportedEncodingException {
                h.this.Q = mediaPlayer.getDuration();
                if (h.this.K != null) {
                    h.this.K.a(h.this.Q);
                }
                mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() { // from class: com.sigmob.sdk.videoAd.h.16.1
                    @Override // android.media.MediaPlayer.OnSeekCompleteListener
                    public void onSeekComplete(MediaPlayer mediaPlayer2) throws IllegalStateException {
                        if (h.this.O) {
                            return;
                        }
                        if ((h.this.x == null || h.this.x.getVisibility() != 0) && !h.this.ag) {
                            h.this.y();
                            mediaPlayer2.start();
                            SigmobLog.i("mediaplayer omPrepared start");
                        }
                    }
                });
                if (h.this.ac > 0) {
                    SigmobLog.i("seek to " + (h.this.ac / 1000));
                    mediaPlayer.seekTo(h.this.ac + (-500));
                } else {
                    SigmobLog.i("seek to 0");
                    mediaPlayer.seekTo(0);
                }
                if (Build.VERSION.SDK_INT >= 3) {
                    mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.sigmob.sdk.videoAd.h.16.2
                        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                        public void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i2, int i3) {
                            baseVideoView.a(mediaPlayer2.getVideoWidth(), mediaPlayer2.getVideoHeight());
                            baseVideoView.requestLayout();
                        }
                    });
                }
                try {
                    if (Build.VERSION.SDK_INT >= 16) {
                        String strConcat = "";
                        for (MediaPlayer.TrackInfo trackInfo : mediaPlayer.getTrackInfo()) {
                            strConcat = strConcat.concat(trackInfo.toString());
                        }
                    }
                } catch (Throwable unused) {
                }
                if (h.this.V) {
                    h.this.U.a(com.sigmob.sdk.base.views.d.SOUND_MUTE.a());
                    h.this.D.setVolume(0.0f);
                }
                h.this.m().a(h.this.Q, h.this.B.d());
                if (!h.this.O && (h.this.N == 0 || h.this.N == -1)) {
                    SigMacroCommon macroCommon = h.this.E.getMacroCommon();
                    if (macroCommon instanceof SigMacroCommon) {
                        SigMacroCommon sigMacroCommon = macroCommon;
                        sigMacroCommon.addMarcoKey(SigMacroCommon._COMPLETED_, Constants.FAIL);
                        sigMacroCommon.addMarcoKey(SigMacroCommon._VIDEOTIME_, String.valueOf(h.this.Q / 1000));
                    }
                }
                h.this.C();
                if (h.this.Z) {
                    h.this.H.a(h.this.q(), 0);
                }
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.sigmob.sdk.videoAd.h.16.3
                    @Override // android.media.MediaPlayer.OnInfoListener
                    public boolean onInfo(MediaPlayer mediaPlayer2, int i2, int i3) throws IllegalStateException {
                        String str;
                        if (h.this.m && mediaPlayer2.isPlaying()) {
                            if (i2 == 3) {
                                mediaPlayer2.start();
                                str = "mediaplayer onInfo start";
                            } else if (i2 == 804 && !h.this.ab) {
                                h.this.x();
                                h.this.ab = true;
                                h.this.ac = mediaPlayer2.getCurrentPosition();
                                h.this.H();
                                baseVideoView.pause();
                                str = "videoView.pause()";
                            }
                            SigmobLog.i(str);
                        }
                        return true;
                    }
                });
            }
        });
        try {
            if (!this.w) {
                baseVideoView.a.setDataSource(this.B.a());
                this.j = b(context);
            }
        } catch (Throwable unused) {
        }
        baseVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.sigmob.sdk.videoAd.h.17
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (h.this.m && (h.this.ab || h.this.B() < 0.5f)) {
                    h.this.x();
                } else {
                    h.this.s = "none";
                    h.this.a(true);
                }
            }
        });
        baseVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.sigmob.sdk.videoAd.h.18
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                SigmobLog.e(String.format("what %d, extra %d", Integer.valueOf(i2), Integer.valueOf(i3)));
                if (h.this.m) {
                    h.this.x();
                    h.this.ab = true;
                    h.this.ac = mediaPlayer.getCurrentPosition();
                    h.this.H();
                    baseVideoView.pause();
                    SigmobLog.i("videoView.pause()");
                    return true;
                }
                h.this.P = true;
                h.this.m().a(com.sigmob.sdk.base.common.a.AD_ERROR, h.this.r());
                h.this.H();
                h.this.F();
                String str = "";
                try {
                    if (Build.VERSION.SDK_INT >= 16) {
                        MediaPlayer.TrackInfo[] trackInfo = mediaPlayer.getTrackInfo();
                        int length = trackInfo.length;
                        String str2 = "";
                        int i4 = 0;
                        while (i4 < length) {
                            try {
                                String strConcat = str2.concat(trackInfo[i4].toString());
                                i4++;
                                str2 = strConcat;
                            } catch (Throwable unused2) {
                            }
                        }
                        str = str2;
                    }
                } catch (Throwable unused3) {
                }
                h.this.B.a(h.this.i(), str + String.format("what %d, extra %d", Integer.valueOf(i2), Integer.valueOf(i3)), h.this.E, PointCategory.VIDEO, h.this.r());
                HashMap map = new HashMap();
                map.put("error", "ad_unit_key Serializable is null");
                h.this.a(IntentActions.ACTION_REWARDED_VIDEO_PLAYFAIL, map);
                h.this.h().a();
                return false;
            }
        });
        if (this.E.isVideoExist()) {
            baseVideoView.setVideoURI(Uri.fromFile(new File(this.B.a())));
        } else {
            this.m = true;
            com.sigmob.sdk.base.common.d.f().a(this.E, null);
            com.sigmob.sdk.base.common.d.f().a(this);
        }
        baseVideoView.setVisibility(i);
        return baseVideoView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append('[');
        do {
            if (i != 0) {
                sb.append(',');
            }
            sb.append(list.get(i));
            i++;
        } while (i < list.size());
        sb.append(']');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        try {
            SigmobLog.d("get video image begin" + System.currentTimeMillis());
            Bitmap frameAtTime = this.D.a.getFrameAtTime((long) (i * 1000), 2);
            SigmobLog.d("get video image end" + System.currentTimeMillis());
            this.j.setScaleType(ImageView.ScaleType.FIT_XY);
            this.j.setImageBitmap(frameAtTime);
        } catch (RuntimeException e) {
            SigmobLog.e(e.getMessage());
        }
    }

    private void a(int i, RelativeLayout.LayoutParams layoutParams) {
        int iB = com.sigmob.sdk.common.utils.c.b(10.0f, this.b);
        layoutParams.addRule(10);
        if (this.U != null) {
            layoutParams.addRule(i, this.U.getId());
            layoutParams.setMargins(0, iB * 2, 0, 0);
        } else {
            layoutParams.addRule(i, this.U.getId());
            layoutParams.setMargins(iB, iB * 2, iB, iB);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        if (this.J == null || this.J.a()) {
            return;
        }
        this.J.b();
        m().a(z, i);
        m().a(com.sigmob.sdk.base.common.a.AD_SHOW_SKIP, i);
    }

    private ImageView b(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        return imageView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        if (this.af == null) {
            this.af = new com.sigmob.sdk.base.views.e(k(), this.E);
            this.af.a(new e.b() { // from class: com.sigmob.sdk.videoAd.h.6
                @Override // com.sigmob.sdk.base.views.e.b
                public void a() {
                    if (h.this.af != null) {
                        h.this.af.dismiss();
                        h.this.af.c();
                        h.this.af = null;
                        h.this.ag = false;
                    }
                    if (h.this.D != null && i == 1) {
                        h.this.D.start();
                    }
                    h.this.m().a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_CLOSE, h.this.r());
                }

                @Override // com.sigmob.sdk.base.views.e.b
                public void a(String str, String str2) {
                    if (h.this.K == null) {
                        h.this.s();
                    }
                    if (h.this.K != null) {
                        String landing_page = h.this.a().getLanding_page();
                        h.this.B.a(h.this.a(), (Context) h.this.k(), TextUtils.isEmpty(landing_page) ? str : landing_page, com.sigmob.sdk.base.a.ENDCARD, str2, true);
                    }
                }

                @Override // com.sigmob.sdk.base.views.e.b
                public void b() {
                    if (h.this.D != null && h.this.D.isPlaying() && i == 1) {
                        h.this.D.pause();
                    }
                    h.this.m().a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_SHOW, h.this.r());
                }
            });
        }
        if (this.af == null || !this.af.a() || this.ag) {
            return;
        }
        this.af.show();
        this.ag = true;
    }

    private void b(int i, RelativeLayout.LayoutParams layoutParams) {
        int i2;
        int iB = com.sigmob.sdk.common.utils.c.b(10.0f, this.b);
        switch (i) {
            case 1:
            case 2:
                layoutParams.addRule(10);
                i2 = 9;
                break;
            case 3:
            case 4:
                layoutParams.addRule(10);
                i2 = 11;
                break;
            default:
                return;
        }
        layoutParams.addRule(i2);
        layoutParams.setMargins(iB, iB * 2, iB, iB);
    }

    private void b(Context context, int i) {
        this.H = new VideoProgressBarWidget(context);
        this.H.setAnchorId(this.D.getId());
        this.H.setVisibility(i);
        j().addView(this.H);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, int i) {
        if (this.x != null) {
            return;
        }
        this.x = new AlertDialogWidget(context, this.B.l());
        this.x.setVisibility(i);
        j().addView(this.x);
        this.x.setDialogListener(new AlertDialogWidget.a() { // from class: com.sigmob.sdk.videoAd.h.20
            @Override // com.sigmob.sdk.base.views.AlertDialogWidget.a
            public void a() {
                if (!h.this.O) {
                    h.this.m().a(com.sigmob.sdk.base.common.a.AD_SKIP, h.this.r());
                    h.this.B.b(h.this.b, h.this.r(), h.this.q(), h.this.E);
                }
                h.this.x.setVisibility(8);
                if (h.this.Y != null && h.this.Y.getFourElementsLayout() != null) {
                    h.this.Y.getFourElementsLayout().setClickable(true);
                }
                if (h.this.B.e()) {
                    h.this.h().a();
                    return;
                }
                if (!h.this.w) {
                    h.this.a(h.this.r());
                }
                h.this.D.stopPlayback();
                h.this.H();
                h.this.F();
                h.this.O = true;
                if (h.this.K != null) {
                    h.this.K.a(h.this.i(), h.this.r(), h.this.a(), h.this.y);
                }
            }

            @Override // com.sigmob.sdk.base.views.AlertDialogWidget.a
            public void b() {
                h.this.D.start();
                h.this.x.setVisibility(8);
                if (h.this.Y == null || h.this.Y.getFourElementsLayout() == null) {
                    return;
                }
                h.this.Y.getFourElementsLayout().setClickable(true);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b7 A[Catch: Throwable -> 0x00f3, TRY_LEAVE, TryCatch #1 {Throwable -> 0x00f3, blocks: (B:29:0x00b3, B:31:0x00b7), top: B:74:0x00b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fd A[Catch: Throwable -> 0x0108, TRY_LEAVE, TryCatch #5 {Throwable -> 0x0108, blocks: (B:40:0x00f9, B:42:0x00fd), top: B:82:0x00f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(final android.content.Context r24, int r25) {
        /*
            Method dump skipped, instructions count: 525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.videoAd.h.d(android.content.Context, int):void");
    }

    private void e(Context context, int i) {
        if (this.I != null) {
            return;
        }
        this.I = new VideoButtonWidget(context, this.B.f());
        this.I.setVisibility(i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.sigmob.sdk.common.utils.c.b(30.0f, context), com.sigmob.sdk.common.utils.c.b(30.0f, context));
        b(this.B.f(), layoutParams);
        j().addView(this.I, layoutParams);
        if (!TextUtils.isEmpty(this.E.getCloseCardHtmlData()) && !this.K.c()) {
            this.ad = new com.sigmob.sdk.base.views.g(k(), this.t, this.E, this.B);
            this.ad.a(new g.a() { // from class: com.sigmob.sdk.videoAd.h.2
                @Override // com.sigmob.sdk.base.views.g.a
                public void a() {
                    if (h.this.ad != null) {
                        h.this.ad.dismiss();
                        h.this.ad.c();
                    }
                    h.this.T = true;
                    h.this.B.b(h.this.i(), h.this.q(), h.this.E);
                    h.this.h().a();
                }
            });
        }
        this.I.setOnTouchListener(new View.OnTouchListener() { // from class: com.sigmob.sdk.videoAd.h.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    if (h.this.K.c() || h.this.ad == null || h.this.ad.a() || h.this.ae) {
                        h.this.T = true;
                        h.this.B.b(h.this.i(), h.this.q(), h.this.E);
                        h.this.h().a();
                    } else {
                        h.this.ad.show();
                        h.this.ae = true;
                    }
                }
                return true;
            }
        });
        this.I.a(this.E);
        String strB = this.B.b();
        if (strB != null) {
            this.I.a(strB);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f(final android.content.Context r4, int r5) {
        /*
            r3 = this;
            com.sigmob.sdk.base.views.SkipButtonWidget r0 = new com.sigmob.sdk.base.views.SkipButtonWidget
            r0.<init>(r4)
            r3.J = r0
            com.sigmob.sdk.base.views.SkipButtonWidget r0 = r3.J
            int r1 = com.sigmob.sdk.common.a.r()
            r0.setId(r1)
            com.sigmob.sdk.base.views.SkipButtonWidget r0 = r3.J
            r0.setVisibility(r5)
            android.widget.RelativeLayout$LayoutParams r5 = new android.widget.RelativeLayout$LayoutParams
            r0 = -2
            r5.<init>(r0, r0)
            com.sigmob.sdk.videoAd.a r0 = r3.B
            int r0 = r0.h()
            r1 = 0
            r2 = 2
            if (r0 > r2) goto L3c
            com.sigmob.sdk.videoAd.a r0 = r3.B
            int r0 = r0.g()
            if (r0 > r2) goto L32
            r0 = 1
            r3.a(r0, r5)
            goto L47
        L32:
            com.sigmob.sdk.videoAd.a r0 = r3.B
            int r0 = r0.g()
            r3.b(r0, r5)
            goto L47
        L3c:
            com.sigmob.sdk.videoAd.a r0 = r3.B
            int r0 = r0.g()
            if (r0 <= r2) goto L32
            r3.a(r1, r5)
        L47:
            android.view.ViewGroup r0 = r3.j()
            com.sigmob.sdk.base.views.SkipButtonWidget r2 = r3.J
            r0.addView(r2, r5)
            com.sigmob.sdk.base.views.SkipButtonWidget r5 = r3.J
            com.sigmob.sdk.videoAd.h$4 r0 = new com.sigmob.sdk.videoAd.h$4
            r0.<init>()
            r5.setOnClickListener(r0)
            com.sigmob.sdk.videoAd.a r4 = r3.B
            int r4 = r4.m()
            if (r4 != 0) goto L6a
            com.sigmob.sdk.videoAd.a r4 = r3.B
            int r4 = r4.n()
            if (r4 < 0) goto L72
        L6a:
            com.sigmob.sdk.videoAd.a r4 = r3.B
            int r4 = r4.n()
            if (r4 != 0) goto L75
        L72:
            r3.a(r1, r1)
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.videoAd.h.f(android.content.Context, int):void");
    }

    private void g(Context context, int i) {
        this.U = new VideoButtonWidget(context, this.B.h());
        this.U.setId(com.sigmob.sdk.common.a.r());
        this.U.setVisibility(i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.sigmob.sdk.common.utils.c.b(30.0f, context), com.sigmob.sdk.common.utils.c.b(30.0f, context));
        b(this.B.h(), layoutParams);
        j().addView(this.U, layoutParams);
        this.U.setOnTouchListener(new View.OnTouchListener() { // from class: com.sigmob.sdk.videoAd.h.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BaseVideoView baseVideoView;
                float f;
                if (motionEvent.getAction() == 1) {
                    if (h.this.V) {
                        h.this.m().a(com.sigmob.sdk.base.common.a.AD_UNMUTE, h.this.r());
                        h.this.U.a(com.sigmob.sdk.base.views.d.SOUND_ON.a());
                        baseVideoView = h.this.D;
                        f = 1.0f;
                    } else {
                        h.this.m().a(com.sigmob.sdk.base.common.a.AD_MUTE, h.this.r());
                        h.this.U.a(com.sigmob.sdk.base.views.d.SOUND_MUTE.a());
                        baseVideoView = h.this.D;
                        f = 0.0f;
                    }
                    baseVideoView.setVolume(f);
                    h.this.V = !h.this.V;
                }
                return true;
            }
        });
        if (this.B.j() == 0) {
            this.U.a(com.sigmob.sdk.base.views.d.SOUND_ON.a());
            return;
        }
        this.U.a(com.sigmob.sdk.base.views.d.SOUND_MUTE.a());
        this.D.setVolume(0.0f);
        this.V = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (this.S == null || this.S.getVisibility() == 0) {
            return;
        }
        this.r = System.currentTimeMillis();
        this.S.setVisibility(0);
        this.s = "loading";
        int i = this.n + 1;
        this.n = i;
        if (i > 2) {
            a(r(), true);
        } else {
            this.v.postDelayed(new Runnable() { // from class: com.sigmob.sdk.videoAd.h.12
                @Override // java.lang.Runnable
                public void run() {
                    h.this.v.removeCallbacksAndMessages(null);
                    h.this.a(h.this.r(), true);
                }
            }, AppLinkMonitor.DELAY_TIME);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"DefaultLocale"})
    public void y() {
        if (this.S == null || this.S.getVisibility() != 0) {
            return;
        }
        this.p.add(String.format("%d", Long.valueOf(System.currentTimeMillis() - this.r)));
        this.q.add(String.format("%.2f", Float.valueOf(r() / 1000.0f)));
        this.s = PointCategory.PLAY;
        this.v.removeCallbacksAndMessages(null);
        this.S.setVisibility(4);
    }

    private void z() {
        try {
            if (this.O) {
                return;
            }
            G();
            if (this.N > 0) {
                m().a(com.sigmob.sdk.base.common.a.AD_PLAYING, this.N);
                SigmobLog.d("video seek to " + this.N);
                this.D.seekTo(this.N);
            } else {
                m().a(com.sigmob.sdk.base.common.a.AD_PLAY_LOAD, r());
            }
            if ((this.x == null || this.x.getVisibility() != 0) && !this.ag) {
                this.D.start();
            }
            if (this.N != -1) {
                this.B.a(i(), this.N);
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    @Override // com.sigmob.sdk.base.common.d.a
    public BaseAdUnit a() {
        return this.E;
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(int i, int i2, Intent intent) {
        if (i2 == -1) {
            h().a();
        }
    }

    public void a(Context context) {
        this.S = new RelativeLayout(context);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(com.sigmob.sdk.base.views.d.LOADING.a());
        imageView.setId(com.sigmob.sdk.common.a.r());
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, BaseConstants.Time.HOUR, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(8000000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setRepeatMode(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        imageView.setAnimation(rotateAnimation);
        this.S.addView(imageView, layoutParams);
        this.S.setVisibility(4);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        j().addView(this.S, layoutParams2);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Configuration configuration) {
        if (this.B != null && this.K == null) {
            this.K = com.sigmob.sdk.videoAd.a.d(this.E);
            this.K.a(this.B);
        }
        if (m() != null) {
            m().a(com.sigmob.sdk.base.common.a.AD_ROTATION, r());
        }
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Bundle bundle) {
        try {
            bundle.putBoolean("video_finished", this.O);
            bundle.putInt("current_position", this.N);
            bundle.putBoolean("companionAd_visable", this.R);
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    void a(com.sigmob.sdk.base.common.a aVar) {
        if (com.sigmob.sdk.base.common.a.AD_START.equals(aVar)) {
            a(IntentActions.ACTION_REWARDED_VIDEO_PLAY);
        }
        if (m() != null) {
            m().a(aVar, r());
        }
    }

    @Override // com.sigmob.sdk.base.common.d.a
    public void a(BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getUuid().equals(this.E.getUuid())) {
            SigmobLog.d("cache_video_ready() called");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sigmob.sdk.videoAd.h.11
                @Override // java.lang.Runnable
                public void run() {
                    BaseVideoView baseVideoView;
                    Uri uriFromFile;
                    try {
                        if (new File(h.this.E.getVideoPath()).exists()) {
                            baseVideoView = h.this.D;
                            uriFromFile = Uri.fromFile(new File(h.this.E.getVideoPath()));
                        } else {
                            baseVideoView = h.this.D;
                            uriFromFile = Uri.fromFile(new File(h.this.E.getVideoTmpPath()));
                        }
                        baseVideoView.setVideoURI(uriFromFile);
                    } catch (Throwable th) {
                        SigmobLog.e("cache_video_ready error", th);
                    }
                }
            });
        }
    }

    public void a(boolean z) {
        if (!this.W) {
            b(z);
        }
        if (!this.W && this.B.e()) {
            h().a();
            return;
        }
        if (!this.w) {
            if (z) {
                E();
            } else {
                a(r());
            }
        }
        this.D.stopPlayback();
        H();
        if (z) {
            this.E.getMacroCommon().addMarcoKey(SigMacroCommon._PLAYLASTFRAME_, "1");
            this.B.a(this.b, r(), q(), this.E);
            if (!this.P) {
                m().a(com.sigmob.sdk.base.common.a.AD_COMPLETE, r());
            }
        }
        F();
        this.O = true;
        if (this.K != null) {
            this.K.a(i(), r(), a(), this.y);
        }
    }

    @Override // com.sigmob.sdk.videoAd.b, com.sigmob.sdk.base.common.g
    public void b() {
        super.b();
        MaterialMeta material = this.E.getMaterial();
        this.l = com.sigmob.sdk.common.a.n().ad();
        Activity activityK = k();
        if (this.R) {
            F();
            return;
        }
        if (this.B == null) {
            throw new IllegalStateException("BaseVideoConfig does not have a video disk path");
        }
        if (this.Z) {
            b(activityK, 4);
        }
        if (material.has_companion_endcard != null && material.has_companion_endcard.booleanValue() && material.companion != null) {
            d(activityK, 4);
            this.z = material.companion.show_delay_secs.intValue();
        }
        g(activityK, 0);
        f(activityK, 0);
        this.L = new c(this, this.B, new Handler(Looper.getMainLooper()));
    }

    @Override // com.sigmob.sdk.base.common.d.a
    public void b(BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getUuid().equals(this.E.getUuid())) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sigmob.sdk.videoAd.h.13
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        h.this.ac = h.this.r();
                        SigmobLog.d("cache_video_Success() called " + h.this.ac);
                        h.this.m = false;
                        h.this.ab = false;
                        h.this.D.pause();
                        h.this.H();
                        h.this.D.setVideoURI(Uri.fromFile(new File(h.this.E.getVideoPath())));
                        h.this.G();
                    } catch (Throwable th) {
                        SigmobLog.e("cache_video_ready error", th);
                    }
                }
            });
        }
    }

    void b(boolean z) {
        if (this.E.getAd_type() == 4) {
            return;
        }
        this.W = true;
        int iQ = z ? q() : r();
        a(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
        m().a(com.sigmob.sdk.base.common.a.AD_FINISH, iQ);
        this.B.c(i().getApplicationContext(), iQ, q(), a());
    }

    @Override // com.sigmob.sdk.videoAd.g.a
    public void b_() {
        I();
    }

    @Override // com.sigmob.sdk.base.common.g
    public void c() {
        if (this.T) {
            a(IntentActions.ACTION_INTERSTITIAL_DISMISS);
        } else {
            if (this.R) {
                return;
            }
            A();
        }
    }

    @Override // com.sigmob.sdk.base.common.d.a
    public void c(BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getUuid().equals(this.E.getUuid())) {
            com.sigmob.sdk.base.common.d.f().a(baseAdUnit, null);
            H();
            SigmobLog.d("cache_video_update() called");
        }
    }

    @Override // com.sigmob.sdk.videoAd.g.a
    public void c_() {
        SigmobLog.e("onHandleClickFailed");
    }

    @Override // com.sigmob.sdk.base.common.g
    public void d() {
        if (this.R) {
            return;
        }
        z();
    }

    @Override // com.sigmob.sdk.base.common.d.a
    public void d(BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getUuid().equals(this.E.getUuid())) {
            SigmobLog.d("cache_video_update() called");
            if (this.ab) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sigmob.sdk.videoAd.h.14
                    @Override // java.lang.Runnable
                    public void run() {
                        h.this.ab = false;
                        h.this.D.setVideoURI(Uri.fromFile(new File(h.this.E.getVideoTmpPath())));
                        h.this.G();
                    }
                });
            }
        }
    }

    @Override // com.sigmob.sdk.base.common.g
    public void e() {
        try {
            SigmobLog.d("VideoViewController onDestroy() called");
            H();
            com.sigmob.sdk.base.common.d.f().b(this);
            if (!this.T) {
                a(IntentActions.ACTION_INTERSTITIAL_DISMISS);
            }
            this.B.c();
            if (this.X != null) {
                this.X.a();
                this.X = null;
            }
            if (this.G != null) {
                this.G.a(com.sigmob.sdk.base.common.a.AD_VCLOSE, 0);
                this.G.a();
                this.G = null;
            }
            if (this.K != null) {
                this.K.a((g.a) null);
            }
            if (this.D.a != null) {
                this.D.a.release();
            }
            if (this.I != null) {
                this.I.setOnTouchListener(null);
            }
            if (this.J != null) {
                this.J.setOnTouchListener(null);
            }
            if (this.U != null) {
                this.U.setOnTouchListener(null);
            }
            if (this.D != null) {
                this.D.a();
            }
            if (this.F != null && (this.F instanceof CreativeWebView)) {
                ((CreativeWebView) this.F).setWebViewClickListener(null);
            }
            this.F = null;
            this.H = null;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    @Override // com.sigmob.sdk.base.common.d.a
    public void e(BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getUuid().equals(this.E.getUuid()) && baseAdUnit.isEndCardIndexExist()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sigmob.sdk.videoAd.h.15
                @Override // java.lang.Runnable
                public void run() {
                    h.this.s();
                }
            });
        }
    }

    @Override // com.sigmob.sdk.base.common.g
    public void f() {
    }

    @Override // com.sigmob.sdk.base.common.d.a
    public void f(BaseAdUnit baseAdUnit) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public boolean g() {
        return false;
    }

    public void l() {
        this.i = new AdLogoView(i(), 0);
        this.i.setId(com.sigmob.sdk.common.a.r());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, com.sigmob.sdk.common.utils.c.b(16.0f, i()));
        int iB = com.sigmob.sdk.common.utils.c.b(10.0f, this.b);
        if (this.Y != null) {
            layoutParams.addRule(8, this.Y.getId());
        } else {
            layoutParams.addRule(12);
            layoutParams.setMargins(0, 0, 0, iB);
        }
        try {
            this.i.a(this.E.getAd_source_logo());
            if (!this.E.getInvisibleAdLabel()) {
                this.i.b(com.sigmob.sdk.base.b.f());
            }
        } catch (Throwable unused) {
        }
        j().addView(this.i, layoutParams);
    }

    public p m() {
        if (this.G == null) {
            this.G = new p(this.c);
            this.G.a(this.b, a(), this.t);
        }
        return this.G;
    }

    public boolean n() {
        try {
            long jR = r();
            if (this.B.n() > -1) {
                if ((jR / 1000.0f) + 0.3f >= this.B.n()) {
                    return true;
                }
            } else if (this.M / 1000.0f < jR / 1000.0f) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return true;
        }
    }

    public boolean o() {
        if (this.Y != null && this.Y.a()) {
            return true;
        }
        try {
            boolean z = ((long) r()) / 1000 >= ((long) this.z);
            if (z) {
                SigmobLog.d("showAble CompanionAds");
            }
            return z;
        } catch (Throwable th) {
            SigmobLog.e("shouldBeShowCompanionAds", th);
            return true;
        }
    }

    public void p() {
        if (this.A || this.Y == null) {
            return;
        }
        this.Y.bringToFront();
        this.Y.setVisibility(0);
        this.A = true;
    }

    int q() {
        com.sigmob.sdk.videoAd.a aVar;
        int duration;
        if (this.D == null) {
            return 0;
        }
        if (this.Q > 0) {
            aVar = this.B;
            duration = this.Q;
        } else {
            aVar = this.B;
            duration = this.D.getDuration();
        }
        return aVar.a(duration);
    }

    int r() {
        if (this.D == null) {
            return 0;
        }
        return this.D.getCurrentPosition();
    }

    void s() {
        if (this.F == null && this.E.isEndCardIndexExist()) {
            try {
                this.K = com.sigmob.sdk.videoAd.a.d(this.E);
                this.K.a(this.B);
                this.F = a(k(), this.K, 4);
                this.K.a(this.Q);
                e(i(), 4);
                this.K.a(this);
            } catch (Throwable th) {
                this.K = null;
                SigmobLog.e(th.getMessage());
                HashMap map = new HashMap();
                map.put("error", th.getMessage());
                a(IntentActions.ACTION_REWARDED_VIDEO_PLAYFAIL, map);
                this.f.a();
            }
        }
    }

    void t() {
        if (this.E.getMaterial().disable_auto_deeplink.booleanValue()) {
            s();
        }
        a(r(), false);
        int iQ = (int) (((q() - r()) / 1000.0f) + 0.5f);
        this.J.a(iQ);
        if (this.x != null) {
            this.x.setduration(iQ);
        }
    }

    boolean u() {
        return !this.W && B() >= this.B.i();
    }

    void v() {
        int iQ = (int) (((q() - r()) / 1000.0f) + 0.5f);
        this.J.a(iQ);
        if (this.x != null) {
            this.x.setduration(iQ);
        }
        if (this.Z) {
            this.H.a(r());
        }
    }
}
