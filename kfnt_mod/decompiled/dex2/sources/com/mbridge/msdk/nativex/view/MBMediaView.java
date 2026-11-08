package com.mbridge.msdk.nativex.view;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.download.download.HTMLResourceManager;
import com.mbridge.msdk.foundation.download.download.ResourceManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.foundation.webview.BrowserView;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.nativex.view.MediaViewPlayerView;
import com.mbridge.msdk.nativex.view.mbfullview.BaseView;
import com.mbridge.msdk.nativex.view.mbfullview.MBridgeFullView;
import com.mbridge.msdk.nativex.view.mbfullview.MBridgeTopFullView;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.NativeListener;
import com.mbridge.msdk.out.OnMBMediaViewListener;
import com.mbridge.msdk.out.OnMBMediaViewListenerPlus;
import com.mbridge.msdk.playercommon.VideoPlayerStatusListener;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.video.js.bridge.IRewardBridge;
import com.mbridge.msdk.videocommon.view.MyImageView;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBMediaView extends LinearLayout implements VideoPlayerStatusListener, IRewardBridge {
    public static final int OPEN_FULLSCREEN_ON_VIDEO_TYPE_1_LANDING_PAGE = 1;
    public static final int OPEN_FULLSCREEN_ON_VIDEO_TYPE_2_NORMAL_FULLSCREEN = 2;
    public static final int OPEN_FULLSCREEN_ON_VIDEO_TYPE_3_NORMAL_FULLSCREEN_ENDCARD = 3;
    public static final int OPEN_FULLSCREEN_ON_VIDEO_TYPE_4_NORMAL_FULLSCREEN_LP = 4;
    public static final int OPEN_FULLSCREEN_ON_VIDEO_TYPE_6_SMALLVIDEO = 6;
    public static final String TAG = "MBMediaView";
    public static final int WHAT_VIEW_FULL_SCREEN = 2;
    public static final int WHAT_VIEW_SMALL_SCREEN = 1;
    private static int o = 2;
    private static int p = 1;
    private ProgressBar A;
    private View B;
    private BaseView C;
    private RelativeLayout D;
    private RelativeLayout E;
    private TextView F;
    private ProgressBar G;
    private RelativeLayout H;
    private int I;
    private Handler J;
    private CampaignEx K;
    private int L;
    private int M;
    private double N;
    private double O;
    private int P;
    private int Q;
    private e R;
    private SensorManager S;
    private Sensor T;
    private com.mbridge.msdk.videocommon.download.a U;
    private a V;
    private OnMBMediaViewListener W;
    private boolean a;
    private OnMBMediaViewListenerPlus aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private RelativeLayout ae;
    private ImageView af;
    private int ag;
    private boolean ah;
    private Context ai;
    private boolean aj;
    private Runnable ak;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private volatile boolean i;
    private volatile boolean j;
    private boolean k;
    private boolean l;
    private ArrayList<String> m;
    private ArrayList<String> n;
    private boolean q;
    private boolean r;
    private int s;
    private MediaViewPlayerView t;
    private RelativeLayout u;
    private RelativeLayout v;
    private RelativeLayout w;
    private WindVaneWebViewForNV x;
    private WindVaneWebViewForNV y;
    private MyImageView z;

    private enum a {
        BIG_IMAGE,
        VIDEO,
        GIF
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void handlerPlayableException(Object obj, String str) {
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void notifyCloseBtn(Object obj, String str) {
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayCompleted() {
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayProgressMS(int i, int i2) {
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayStarted(int i) {
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void setOrientation(Object obj, String str) {
    }

    public MBMediaView(Context context) {
        super(context);
        this.a = true;
        this.b = true;
        this.c = true;
        this.d = true;
        this.e = true;
        this.f = false;
        this.g = true;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = new ArrayList<>();
        this.n = new ArrayList<>();
        this.q = false;
        this.r = false;
        this.s = 0;
        this.U = null;
        this.V = null;
        this.ac = false;
        this.ad = false;
        this.ah = false;
        this.aj = false;
        this.ak = new Runnable() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.9
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (!MBMediaView.this.h) {
                        if (!MBMediaView.this.a((View) MBMediaView.this)) {
                            MBMediaView.Q(MBMediaView.this);
                        } else {
                            MBMediaView.R(MBMediaView.this);
                        }
                    }
                    MBMediaView.this.J.postDelayed(this, 300L);
                } catch (Exception e2) {
                    s.d(MBMediaView.TAG, e2.getMessage());
                }
            }
        };
        a(context);
    }

    public MBMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = true;
        this.b = true;
        this.c = true;
        this.d = true;
        this.e = true;
        this.f = false;
        this.g = true;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = new ArrayList<>();
        this.n = new ArrayList<>();
        this.q = false;
        this.r = false;
        this.s = 0;
        this.U = null;
        this.V = null;
        this.ac = false;
        this.ad = false;
        this.ah = false;
        this.aj = false;
        this.ak = new Runnable() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.9
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (!MBMediaView.this.h) {
                        if (!MBMediaView.this.a((View) MBMediaView.this)) {
                            MBMediaView.Q(MBMediaView.this);
                        } else {
                            MBMediaView.R(MBMediaView.this);
                        }
                    }
                    MBMediaView.this.J.postDelayed(this, 300L);
                } catch (Exception e2) {
                    s.d(MBMediaView.TAG, e2.getMessage());
                }
            }
        };
        a(context);
    }

    private void a(Context context) {
        try {
            c();
            b();
            this.ai = context;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private void a() {
        if (this.U != null) {
            this.U.b((com.mbridge.msdk.videocommon.listener.a) null);
        }
    }

    private void b() {
        s.d(TAG, "initView");
        int iA = m.a(getContext(), "mbridge_nativex_mbmediaview", TtmlNode.TAG_LAYOUT);
        if (iA == -1) {
            s.d(TAG, "can not find mediaview resource");
            return;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(iA, (ViewGroup) null);
        this.v = (RelativeLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_rl_mediaview_root", "id"));
        this.u = (RelativeLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_ll_playerview_container", "id"));
        this.z = (MyImageView) viewInflate.findViewById(m.a(getContext(), "mbridge_my_big_img", "id"));
        this.A = (ProgressBar) viewInflate.findViewById(m.a(getContext(), "mbridge_native_pb", "id"));
        this.w = (RelativeLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_nativex_webview_layout", "id"));
        this.x = (WindVaneWebViewForNV) viewInflate.findViewById(m.a(getContext(), "mbridge_nativex_webview_layout_webview", "id"));
        this.v.setClickable(true);
        addView(viewInflate, -1, -1);
    }

    private void c() {
        this.J = new Handler() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                if (message != null) {
                    try {
                        switch (message.what) {
                            case 1:
                                MBMediaView.this.r();
                                break;
                            case 3:
                                Object obj = message.obj;
                                if (obj != null && (obj instanceof View)) {
                                    if (MBMediaView.this.a((View) obj)) {
                                        MBMediaView.b(MBMediaView.this);
                                        break;
                                    }
                                }
                                break;
                        }
                    } catch (Exception e2) {
                        s.d(MBMediaView.TAG, e2.getMessage());
                    }
                }
            }
        };
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("onAttachedToWindow start:");
            sb.append(this.K == null ? "" : this.K.getAppName());
            s.b(TAG, sb.toString());
            if (this.c) {
                try {
                    this.S = (SensorManager) getContext().getSystemService("sensor");
                    this.T = this.S.getDefaultSensor(1);
                    this.R = new e();
                    this.S.registerListener(this.R, this.T, 2);
                    s.b(TAG, "register sensorlistener");
                } catch (Throwable th) {
                    s.d(TAG, th.getMessage());
                }
            }
            if (Build.VERSION.SDK_INT >= 11) {
                this.g = isHardwareAccelerated();
            }
            this.ag = getOrientation();
            d();
            this.J.postDelayed(this.ak, 3000L);
            s.b(TAG, "onAttachedToWindow setDisplay finalmCurDisplayMode:" + this.V);
        } catch (Throwable th2) {
            s.d(TAG, th2.getMessage());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            this.J.removeCallbacks(this.ak);
            if (this.J != null) {
                this.J.removeCallbacksAndMessages(null);
            }
            try {
                if (this.S != null && this.R != null) {
                    this.S.unregisterListener(this.R);
                    s.b(TAG, "unRegister sensorlistener");
                }
            } catch (Throwable th) {
                s.d(TAG, th.getMessage());
            }
            a();
        } catch (Throwable th2) {
            s.d(TAG, th2.getMessage());
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        try {
            s.d(TAG, "hasWindowFocus:" + z);
            this.e = z;
            if (this.V == a.VIDEO && this.t != null) {
                this.t.setIsFrontDesk(z);
            }
            try {
                if (!this.h) {
                    s.b(TAG, "initFullPlayerOnWindowFocus 在半屏 return");
                } else if (this.t != null) {
                    if (this.e) {
                        if (this.t.isPlaying()) {
                            s.b(TAG, "fullscreen windowfocuse true isPlaying do nothing return");
                        } else if (this.t != null && !this.t.isComplete() && !this.t.getIsActiviePause()) {
                            s.d(TAG, "fullscreen windowfocuse true startOrPlayVideo");
                            this.t.onClickPlayButton();
                        }
                    } else {
                        s.b(TAG, "fullscreen windowfocuse false pasue======");
                        this.t.pause();
                    }
                } else {
                    s.d(TAG, "fullscreen playerview is null return");
                }
            } catch (Throwable th) {
                s.d(TAG, th.getMessage());
            }
            requestLayout();
            s.b(TAG, "onWindowFocusChanged reqeusetlaytout");
        } catch (Throwable th2) {
            s.d(TAG, th2.getMessage());
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        try {
            this.L = getHeight();
            this.M = getWidth();
            if (this.M == 0) {
                this.M = getMeasuredWidth();
                s.c(TAG, "onMeasure 宽度为0 调用getMeasuredWidth mDevWidth:" + this.M);
            }
            if (this.L == 0) {
                this.L = getMeasuredHeight();
                s.c(TAG, "onMeasure 高度为0 调用getMeasuredHeight mDevWidth:" + this.L);
            }
            s.b(TAG, "onMeasure pre mDevWidth " + this.M + " mDevHeight:" + this.L + " mCurDisplayMode:" + this.V + " mCurIsLandScape:" + this.i);
            if (this.M == 0 && this.L == 0) {
                this.M = (int) B();
                s.d(TAG, "onMeasure 宽度和高度都为0 宽度取屏幕宽度mDevWidth:" + this.M);
            }
            if (this.V == a.VIDEO && !this.h) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (this.L == 0 || ((layoutParams != null && layoutParams.height == -2) || (layoutParams != null && layoutParams.height == -1))) {
                    this.L = (int) ((this.M * this.O) / this.N);
                    s.b(TAG, "onMeasure mDevHeight为0并且设置WRAP_CONTENT 拿视频宽高算高度mDevHeight:" + this.L);
                }
                s.b(TAG, "onMeasure after mDevWidth " + this.M + " * mDevHeight *****" + this.L);
                h();
                return;
            }
            if (this.V == a.BIG_IMAGE && !this.h) {
                f();
            } else {
                if (this.V != a.GIF || this.h) {
                    return;
                }
                g();
            }
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    public void setNativeAd(Campaign campaign) {
        String str;
        try {
            if (campaign == null) {
                s.d(TAG, "setNativeAd campaign is null return");
                return;
            }
            if (this.K != null && this.K == campaign) {
                s.b(TAG, "setNativeAd has init return");
                return;
            }
            this.e = true;
            this.g = true;
            this.h = false;
            this.i = false;
            this.j = false;
            this.r = false;
            z();
            this.K = (CampaignEx) campaign;
            if (this.K.getMediaViewHolder() == null) {
                CampaignEx.b bVar = new CampaignEx.b();
                bVar.l = this.K.getAdvImpList();
                this.K.setMediaViewHolder(bVar);
                s.b(TAG, "setNativeAd mediaViewHolder appname:" + this.K.getAppName());
            }
            if (this.K.getAdType() == 94 || this.K.getAdType() == 287) {
                str = this.K.getRequestId() + this.K.getId() + this.K.getVideoUrlEncode();
            } else {
                str = this.K.getId() + this.K.getVideoUrlEncode() + this.K.getBidToken();
            }
            this.U = com.mbridge.msdk.videocommon.download.b.getInstance().a(p(), str);
            s.d(TAG, "setNativeAd cid" + this.K.getId() + " appname:" + this.K.getAppName());
            if (this.q) {
                s.b(TAG, "setNativeAd setDisplay appname:" + this.K.getAppName());
                d();
            }
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    public void setOnMediaViewListener(OnMBMediaViewListener onMBMediaViewListener) {
        this.W = onMBMediaViewListener;
    }

    public void setOnMediaViewListener(OnMBMediaViewListenerPlus onMBMediaViewListenerPlus) {
        this.aa = onMBMediaViewListenerPlus;
    }

    public void setIsAllowFullScreen(boolean z) {
        this.d = z;
    }

    public void setAllowVideoRefresh(boolean z) {
        this.a = z;
    }

    public void setAllowLoopPlay(boolean z) {
        this.b = z;
    }

    public void setAllowScreenChange(boolean z) {
        this.c = z;
    }

    public void destory() {
        try {
            if (this.t != null) {
                this.t.release();
            }
            a();
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private void d() {
        try {
            this.V = a(true);
            s.d(TAG, "setDisplay mCurDisplayMode:" + this.V);
            changeNoticeURL();
            if (this.V == a.BIG_IMAGE) {
                k();
                n();
            } else if (this.V == a.VIDEO) {
                e();
            } else if (this.V == a.GIF) {
                o();
                m();
            }
            this.q = true;
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        A();
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.V != a.BIG_IMAGE || this.M == 0 || this.Q == 0 || this.P == 0) {
                return;
            }
            int i = (this.M * this.Q) / this.P;
            if (this.z == null || i == 0) {
                return;
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.z.getLayoutParams();
            layoutParams.width = this.M;
            layoutParams.height = i;
            this.z.setLayoutParams(layoutParams);
            s.d(TAG, "initBitImageViewWHByDevWh onMeasure mdevWidth:" + this.M + " mDevHeight:" + this.L + " finalHeigt:" + i);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            if (this.V == a.GIF) {
                if (this.M != 0 && this.Q != 0 && this.P != 0) {
                    int i = (this.M * this.Q) / this.P;
                    if (this.w != null && i != 0) {
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
                        layoutParams.width = this.M;
                        layoutParams.height = i;
                        layoutParams.addRule(13);
                        this.w.setLayoutParams(layoutParams);
                        s.d(TAG, "initGifImageViewWHByDevWh onMeasure mdevWidth:" + this.M + " mDevHeight:" + this.L + " finalHeigt:" + i + this.K.getAppName());
                    }
                } else if (this.M != 0 && this.w != null) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
                    layoutParams2.width = this.M;
                    layoutParams2.height = (this.M * 627) / 1200;
                    layoutParams2.addRule(13);
                    this.w.setLayoutParams(layoutParams2);
                    s.d(TAG, "initGifImageViewWHByDevWh onMeasure mdevWidth:" + this.M + " mDevHeight:" + this.L + this.K.getAppName());
                }
            }
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        try {
            if (this.V != a.VIDEO || this.u == null) {
                return;
            }
            int iB = (int) B();
            int iJ = w.j(getContext());
            if (!this.h) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.u.getLayoutParams();
                layoutParams.width = this.M;
                layoutParams.height = this.L;
                layoutParams.addRule(13);
                this.u.setLayoutParams(layoutParams);
            } else {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.D.getLayoutParams();
                layoutParams2.width = iB;
                layoutParams2.height = iJ;
                layoutParams2.addRule(13);
                this.D.setLayoutParams(layoutParams2);
            }
            r();
            if (!this.h) {
                a(this.t, this.M, this.L);
            } else {
                a(this.t, iB, iJ);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    private void i() {
        try {
            this.t.showSoundIndicator(this.l);
            this.t.showProgressView(this.k);
            if (this.W != null) {
                this.W.onExitFullscreen();
            }
            if (this.aa != null) {
                this.aa.onExitFullscreen();
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    private void j() {
        try {
            if (this.W != null) {
                this.W.onVideoAdClicked(this.K);
            }
            if (this.aa != null) {
                this.aa.onVideoAdClicked(this.K);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    protected final void a(String str) {
        try {
            if (TextUtils.isEmpty(str) || this.m.contains(str)) {
                return;
            }
            this.m.add(str);
            if (this.W != null) {
                this.W.onVideoStart();
            }
            if (this.aa != null) {
                this.aa.onVideoStart();
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    protected final void b(String str) {
        try {
            if (TextUtils.isEmpty(str) || this.n.contains(str)) {
                return;
            }
            this.n.add(str);
            if (this.aa != null) {
                this.aa.onVideoComplete();
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    private void k() {
        try {
            w.a((ImageView) this.z);
            this.z.setVisibility(0);
            this.u.setVisibility(8);
            this.w.setVisibility(8);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private void l() {
        try {
            this.u.setVisibility(0);
            this.z.setVisibility(8);
            this.w.setVisibility(8);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            this.u.setVisibility(8);
            this.z.setVisibility(8);
            this.w.setVisibility(0);
            this.x.setVisibility(0);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private void n() {
        try {
            if (this.K == null) {
                return;
            }
            String imageUrl = this.K.getImageUrl();
            if (z.a(imageUrl) || getContext() == null) {
                return;
            }
            s.b(TAG, "fillBigimage startOrPlayVideo");
            com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(imageUrl, new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.12
                @Override // com.mbridge.msdk.foundation.same.c.c
                public final void onFailedLoad(String str, String str2) {
                    s.c(MBMediaView.TAG, "load image fail in mbmediaview");
                }

                @Override // com.mbridge.msdk.foundation.same.c.c
                public final void onSuccessLoad(Bitmap bitmap, String str) {
                    s.b(MBMediaView.TAG, "fillBigimage onSuccessLoad mCurDisplayMode:" + MBMediaView.this.V);
                    if (MBMediaView.this.z == null || MBMediaView.this.V != a.BIG_IMAGE) {
                        return;
                    }
                    if (bitmap != null) {
                        s.b(MBMediaView.TAG, "setimgeBitmap=======");
                        MBMediaView.this.P = bitmap.getWidth();
                        MBMediaView.this.Q = bitmap.getHeight();
                        MBMediaView.this.z.setImageUrl(str);
                        MBMediaView.this.z.setImageBitmap(bitmap);
                    }
                    MBMediaView.this.z.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.12.1
                        @Override // com.mbridge.msdk.widget.a
                        protected final void a(View view) {
                            if (MBMediaView.this.ai != null) {
                                MBMediaView.this.b(MBMediaView.this.ai);
                            } else {
                                MBMediaView.this.b(view.getContext());
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private void o() {
        try {
            if (this.K == null) {
                return;
            }
            String gifUrl = this.K.getGifUrl();
            if (z.a(gifUrl) || getContext() == null) {
                return;
            }
            s.b(TAG, "fillGifimage");
            this.x.loadDataWithBaseURL(null, "<!DOCTYPE html><html lang=\"en\"><head>  <meta charset=\"UTF-8\">  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">  <title>Document</title>  <style>  *{    margin: 0;    padding: 0;  }  html, body{    width: 100%;    height: 100%;  }  body{    background-image: url('gifUrl');    background-position: center;    background-size: contain;    background-repeat: no-repeat;  }  </style></head><body></body></html>".replace("gifUrl", gifUrl), "text/html", "utf-8", null);
            this.x.setInterceptTouch(true);
            this.w.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.13
                @Override // com.mbridge.msdk.widget.a
                protected final void a(View view) {
                    if (MBMediaView.this.ai != null) {
                        MBMediaView.this.b(MBMediaView.this.ai);
                    } else {
                        MBMediaView.this.b(view.getContext());
                    }
                    s.b(MBMediaView.TAG, "CLICK WEBVIEW LAYOUT ");
                }
            });
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        final com.mbridge.msdk.click.b bVar = new com.mbridge.msdk.click.b(getContext(), p());
        try {
            if (this.K != null && this.K.needShowIDialog()) {
                com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.14
                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void b() {
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void a() {
                        MBMediaView.this.a(bVar);
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void c() {
                        a();
                    }
                };
                if (com.mbridge.msdk.click.c.a(this.K) && this.K.needShowIDialog()) {
                    if (!bVar.b(this.K)) {
                        com.mbridge.msdk.mbjscommon.confirmation.e.a().a("", this.K, context, p(), aVar);
                        return;
                    } else {
                        j();
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            s.a(TAG, th.getMessage());
        }
        a(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.mbridge.msdk.click.b bVar) {
        try {
            j();
            if (this.K != null && !z.a(p())) {
                F();
                bVar.a(new NativeListener.NativeTrackingListener() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.15
                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDismissLoading(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDownloadFinish(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDownloadProgress(int i) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onDownloadStart(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final boolean onInterceptDefaultLoadingDialog() {
                        return true;
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onShowLoading(Campaign campaign) {
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onStartRedirection(Campaign campaign, String str) {
                        try {
                            MBMediaView.m(MBMediaView.this);
                            MBMediaView.n(MBMediaView.this);
                            MBMediaView.a(MBMediaView.this, campaign, str);
                            s.d(MBMediaView.TAG, "=====showloading");
                        } catch (Exception e2) {
                            s.d(MBMediaView.TAG, e2.getMessage());
                        }
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onRedirectionFailed(Campaign campaign, String str) {
                        try {
                            MBMediaView.o(MBMediaView.this);
                            MBMediaView.p(MBMediaView.this);
                            MBMediaView.b(MBMediaView.this, campaign, str);
                            s.d(MBMediaView.TAG, "=====hideloading");
                        } catch (Exception e2) {
                            s.d(MBMediaView.TAG, e2.getMessage());
                        }
                    }

                    @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
                    public final void onFinishRedirection(Campaign campaign, String str) {
                        try {
                            MBMediaView.o(MBMediaView.this);
                            MBMediaView.p(MBMediaView.this);
                            MBMediaView.c(MBMediaView.this, campaign, str);
                            s.d(MBMediaView.TAG, "=====hideloading");
                        } catch (Exception e2) {
                            s.d(MBMediaView.TAG, e2.getMessage());
                        }
                    }
                });
                bVar.c(this.K);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String p() {
        try {
            if (this.K == null || !z.b(this.K.getCampaignUnitId())) {
                return null;
            }
            return this.K.getCampaignUnitId();
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
            return null;
        }
    }

    private a a(boolean z) {
        a aVar = null;
        try {
            if (this.K == null) {
                return null;
            }
            s.b(TAG, "initCurDisplayMode appname:" + this.K.getAppName());
            if (z.a(this.K.getVideoUrlEncode())) {
                if (z.b(this.K.getImageUrl())) {
                    aVar = a.BIG_IMAGE;
                    s.b(TAG, "没有视频 只有大图 显示大图");
                }
                if (!z.b(this.K.getGifUrl())) {
                    return aVar;
                }
                s.b(TAG, "没有视频 有gif图 显示gif图");
                if (z.a(this.K.getImageUrl())) {
                    s.b(TAG, "没有视频 没有大图 有gif图 显示gif图");
                    aVar = a.GIF;
                }
                this.x.setWebViewClient(new g(this));
                o();
                return aVar;
            }
            if (Build.VERSION.SDK_INT >= 14 && this.g && this.U != null) {
                if (z.b(this.K.getVideoUrlEncode()) && z.a(this.K.getImageUrl())) {
                    a aVar2 = a.VIDEO;
                    s.b(TAG, "只有视频 没有大图 显示视频");
                    return aVar2;
                }
                if (!z.b(this.K.getVideoUrlEncode()) || !z.b(this.K.getImageUrl())) {
                    return null;
                }
                s.b(TAG, "有视频 又有大图 进入判断逻辑");
                int iT = t();
                s.b(TAG, "readyRate:" + iT);
                if (com.mbridge.msdk.videocommon.download.d.a(this.U, iT)) {
                    a aVar3 = a.VIDEO;
                    s.b(TAG, "满足readyrate 显示视频");
                    return aVar3;
                }
                a aVar4 = a.BIG_IMAGE;
                s.b(TAG, "没有满足readyrate 暂时显示大图 判断是否监听下载");
                if (!z) {
                    return aVar4;
                }
                if (this.a) {
                    s.b(TAG, "可以监听下载 下载满足readyrate之后 显示大图");
                    this.U.b(new f(this));
                    return aVar4;
                }
                s.b(TAG, "开发者禁止监听下载 一直显示大图");
                return aVar4;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("版本小于4.0或者没有开启硬件加速 显示大图 isHard:");
            sb.append(this.g);
            sb.append(" downloadtask:");
            sb.append(this.U != null);
            s.d(TAG, sb.toString());
            return a.BIG_IMAGE;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            s.b(TAG, "默认显示大图");
            return a.BIG_IMAGE;
        }
    }

    private void q() {
        try {
            if (this.K == null) {
                s.b(TAG, "campaign is null addPlayerView return");
            }
            s.b(TAG, "specSize addPlayerView");
            if (this.t != null && this.t.getParent() != null) {
                ((ViewGroup) this.t.getParent()).removeView(this.t);
            }
            l();
            this.t = new MediaViewPlayerView(getContext());
            this.t.showProgressView(this.k);
            this.t.showSoundIndicator(this.l);
            if (this.f) {
                this.t.openSound();
            } else {
                this.t.closeSound();
            }
            this.t.setAllowLoopPlay(this.b);
            this.t.initPlayerViewData(s(), this.K, v(), this, this.U, p());
            this.t.setOnMediaViewPlayerViewListener(new c(this));
            this.u.addView(this.t, -1, -1);
            try {
                if (this.t == null) {
                    s.b(TAG, "setPlayerViewListener playerview is null return");
                } else {
                    this.t.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.11
                        @Override // com.mbridge.msdk.widget.a
                        protected final void a(View view) {
                            try {
                                if (!MBMediaView.this.h) {
                                    MBMediaView.d(MBMediaView.this);
                                }
                                MBMediaView.this.t.showSoundIndicator(true);
                                MBMediaView.this.t.showProgressView(true);
                                if (!MBMediaView.this.d || MBMediaView.this.h || (MBMediaView.this.B != null && MBMediaView.this.B.getParent() != null)) {
                                    if (!MBMediaView.this.h) {
                                        if (MBMediaView.this.ai != null) {
                                            MBMediaView.this.b(MBMediaView.this.ai);
                                        } else {
                                            MBMediaView.this.b(view.getContext());
                                        }
                                        s.d(MBMediaView.TAG, "不允许全屏 跳gp");
                                        return;
                                    }
                                    s.b(MBMediaView.TAG, "fullScreenShowUI");
                                    MBMediaView.i(MBMediaView.this);
                                    return;
                                }
                                if (!MBMediaView.this.t.halfLoadingViewisVisible() && MBMediaView.this.t.isPlaying()) {
                                    MBMediaView.h(MBMediaView.this);
                                    return;
                                }
                                s.b(MBMediaView.TAG, "is loading or no playing return;");
                            } catch (Throwable th) {
                                s.b(MBMediaView.TAG, th.getMessage(), th);
                            }
                        }
                    });
                }
            } catch (Throwable th) {
                s.d(TAG, th.getMessage());
            }
        } catch (Throwable th2) {
            s.d(TAG, th2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        try {
            if (getVisibility() != 0) {
                return;
            }
            if (a((View) this)) {
                com.mbridge.msdk.b.d dVarU = u();
                int iD = dVarU != null ? dVarU.d() : 0;
                Message messageObtainMessage = this.J.obtainMessage();
                messageObtainMessage.what = 3;
                messageObtainMessage.obj = this;
                this.J.sendMessageDelayed(messageObtainMessage, iD * 1000);
            }
            if (this.V == a.VIDEO) {
                if (this.t == null) {
                    s.b(TAG, "setPlayerByVisibilityChange : player is null and addPlayerView");
                    q();
                } else if (this.K != this.t.getCampaign()) {
                    this.t.release();
                    q();
                    if (getParent() != null) {
                        ((View) getParent()).invalidate();
                    }
                    requestLayout();
                    s.b(TAG, "setPlayerByVisibilityChange : addplayerview andr requestLayout");
                }
            }
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private String s() {
        try {
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
        if (this.K == null) {
            return null;
        }
        s.b(TAG, "getPlayUrl curDisplay:" + this.V);
        if (this.U != null) {
            int iF = this.U.f();
            s.b(TAG, "downloadState:" + iF);
            if (iF == 5) {
                String strD = this.U.d();
                if (new File(strD).exists() && this.U.e() == w.a(new File(strD))) {
                    s.b(TAG, "本地已下载完 拿本地播放地址：" + strD + " state：" + iF);
                    return strD;
                }
            }
        }
        String videoUrlEncode = this.K.getVideoUrlEncode();
        if (z.b(videoUrlEncode)) {
            s.b(TAG, "本地尚未下载完 拿网络地址：" + videoUrlEncode);
            return videoUrlEncode;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(View view) {
        if (view != null) {
            try {
            } catch (Throwable th) {
                s.d(TAG, th.getMessage());
            }
            if (view.getVisibility() == 0) {
                Rect rect = new Rect();
                if (!view.getLocalVisibleRect(rect)) {
                    return false;
                }
                long jHeight = rect.height() * rect.width();
                long height = view.getHeight() * view.getWidth();
                long j = (long) (height * 0.5f);
                if (height > 0 && w.l(getContext()) && this.e && jHeight >= j) {
                    if (isShown()) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private int t() {
        if (u() != null) {
            return u().g();
        }
        return 100;
    }

    private com.mbridge.msdk.b.d u() {
        try {
            if (this.K != null && !z.a(this.K.getCampaignUnitId())) {
                String campaignUnitId = this.K.getCampaignUnitId();
                String strK = com.mbridge.msdk.foundation.controller.a.f().k();
                if (!z.a(campaignUnitId) && !z.a(strK)) {
                    com.mbridge.msdk.b.d dVarF = com.mbridge.msdk.b.b.a().f(strK, campaignUnitId);
                    return dVarF != null ? dVarF : com.mbridge.msdk.b.d.d(campaignUnitId);
                }
                return com.mbridge.msdk.b.d.d(campaignUnitId);
            }
            return null;
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
            return null;
        }
    }

    private boolean v() {
        com.mbridge.msdk.b.d dVarU;
        try {
            dVarU = u();
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
        if (dVarU == null) {
            return false;
        }
        int iK = dVarU.k();
        s.b(TAG, "========autoPlayType：" + iK);
        if (iK == 1) {
            if (w.b(getContext())) {
                s.b(TAG, "========wifi下自动播放");
                return true;
            }
            s.b(TAG, "========wifi下自动播放 但目前不是wifi环境 现在为点击播放");
            return false;
        }
        if (iK == 2) {
            s.b(TAG, "========点击播放");
            return false;
        }
        if (iK == 3) {
            s.b(TAG, "========有网自动播放");
            return w.c(getContext());
        }
        if (w.b(getContext())) {
            s.b(TAG, "========else wifi下自动播放");
            return true;
        }
        s.b(TAG, "========else wifi下自动播放 但目前不是wifi环境 现在为点击播放");
        return false;
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayError(String str) {
        try {
            s.b(CampaignEx.JSON_NATIVE_VIDEO_ERROR, str);
            if (this.K != null && this.K.getMediaViewHolder() != null && !this.K.getMediaViewHolder().d && !TextUtils.isEmpty(this.K.getCampaignUnitId()) && this.K.getNativeVideoTracking() != null && this.K.getNativeVideoTracking().m() != null) {
                this.K.getMediaViewHolder().d = true;
                com.mbridge.msdk.click.b.a(getContext(), this.K, this.K.getCampaignUnitId(), this.K.getNativeVideoTracking().m(), false, false);
            }
            try {
                u uVarA = u.a(i.a(getContext()));
                q qVar = null;
                if (!TextUtils.isEmpty(this.K.getNoticeUrl())) {
                    int iC = o.C(getContext());
                    qVar = new q("2000021", iC, this.K.getNoticeUrl(), str, o.a(getContext(), iC));
                } else if (!TextUtils.isEmpty(this.K.getClickURL())) {
                    int iC2 = o.C(getContext());
                    qVar = new q("2000021", iC2, this.K.getClickURL(), str, o.a(getContext(), iC2));
                }
                if (qVar != null) {
                    qVar.n(this.K.getId());
                    qVar.e(this.K.getVideoUrlEncode());
                    qVar.p(str);
                    qVar.k(this.K.getRequestId());
                    qVar.l(this.K.getRequestIdNotice());
                    qVar.m(p());
                    uVarA.a(qVar);
                }
            } catch (Exception e2) {
                s.d(TAG, e2.getMessage());
            }
            com.mbridge.msdk.mbnative.c.b.a(this.K.getCampaignUnitId(), this.K);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onBufferingStart(String str) {
        s.b("bufferMsg", str);
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onBufferingEnd() {
        s.b("bufferend", "bufferend");
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlaySetDataSourceError(String str) {
        s.b("errorstr", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View w() {
        try {
            this.ae = new RelativeLayout(getContext());
            this.ae.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.af = new ImageView(getContext());
            this.af.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 30.0f), w.b(getContext(), 30.0f));
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            layoutParams.topMargin = w.b(getContext(), 8.0f);
            layoutParams.rightMargin = w.b(getContext(), 8.0f);
            this.af.setLayoutParams(layoutParams);
            this.af.setBackgroundResource(m.a(getContext(), "mbridge_nativex_close", "drawable"));
            this.af.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.17
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MBMediaView.this.exitFullScreen();
                }
            });
            this.y.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.ae.addView(this.y);
            this.ae.addView(this.af);
            return this.ae;
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
            return null;
        }
    }

    public void exitFullScreen() {
        try {
            x();
            s.b(TAG, "=========webview close mAllowLoopPlay:" + this.b);
            if (this.b) {
                s.b(TAG, "播放结束 调用onClickPlayButton");
                this.t.onClickPlayButton();
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    public void showEndCardWebViewCloseBtn() {
        if (this.af == null || this.af.getVisibility() == 0) {
            return;
        }
        this.af.setVisibility(0);
    }

    public void hideEndCardWebViewCloseBtn() {
        if (this.af == null || this.af.getVisibility() != 0) {
            return;
        }
        this.af.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        try {
            FrameLayout frameLayout = (FrameLayout) getRootView().findViewById(R.id.content);
            FrameLayout frameLayout2 = (FrameLayout) getRootView().findViewById(100);
            RelativeLayout relativeLayout = (RelativeLayout) getRootView().findViewById(101);
            RelativeLayout relativeLayout2 = (RelativeLayout) getRootView().findViewById(103);
            if (relativeLayout2 == null && relativeLayout != null) {
                relativeLayout2 = (RelativeLayout) relativeLayout.findViewById(103);
            }
            RelativeLayout relativeLayout3 = (RelativeLayout) getRootView().findViewById(m.a(getContext(), "mbridge_full_rl_playcontainer", "id"));
            if (relativeLayout3 == null && relativeLayout2 != null) {
                relativeLayout3 = (RelativeLayout) relativeLayout2.findViewById(m.a(getContext(), "mbridge_full_rl_playcontainer", "id"));
            }
            RelativeLayout relativeLayout4 = (RelativeLayout) getRootView().findViewById(m.a(getContext(), "mbridge_full_player_parent", "id"));
            if (relativeLayout4 == null && relativeLayout3 != null) {
                relativeLayout4 = (RelativeLayout) relativeLayout3.findViewById(m.a(getContext(), "mbridge_full_player_parent", "id"));
            }
            ProgressBar progressBar = (ProgressBar) getRootView().findViewById(m.a(getContext(), "mbridge_full_pb_loading", "id"));
            RelativeLayout relativeLayout5 = (RelativeLayout) getRootView().findViewById(m.a(getContext(), "mbridge_full_rl_install", "id"));
            LinearLayout linearLayout = (LinearLayout) getRootView().findViewById(m.a(getContext(), "mbridge_full_ll_pro_dur", "id"));
            ViewGroup viewGroup = frameLayout2 != null ? (ViewGroup) frameLayout2.getParent() : null;
            if (relativeLayout2 != null) {
                relativeLayout2.removeView(linearLayout);
            } else if (linearLayout != null && linearLayout.getParent() != null) {
                ((ViewGroup) linearLayout.getParent()).removeView(linearLayout);
            }
            if (this.ae != null) {
                if (relativeLayout2 != null) {
                    relativeLayout2.removeView(this.ae);
                } else if (this.ae.getParent() != null) {
                    ((ViewGroup) this.ae.getParent()).removeView(this.ae);
                }
                this.y.setBackListener(null);
                this.y.setObject(null);
                this.y = null;
                this.ae = null;
            }
            if (relativeLayout2 != null) {
                relativeLayout2.removeView(relativeLayout5);
            } else if (relativeLayout5 != null && relativeLayout5.getParent() != null) {
                ((ViewGroup) relativeLayout5.getParent()).removeView(relativeLayout5);
            }
            if (relativeLayout2 != null) {
                relativeLayout2.removeView(progressBar);
            } else if (progressBar != null && progressBar.getParent() != null) {
                ((ViewGroup) progressBar.getParent()).removeView(progressBar);
            }
            if (relativeLayout4 != null) {
                relativeLayout4.removeView(this.t);
            }
            if (relativeLayout3 != null) {
                relativeLayout3.removeView(relativeLayout4);
            } else if (relativeLayout4 != null && relativeLayout4.getParent() != null) {
                ((ViewGroup) relativeLayout4.getParent()).removeView(relativeLayout4);
            }
            if (relativeLayout2 != null) {
                relativeLayout2.removeView(relativeLayout3);
            } else if (relativeLayout3 != null && relativeLayout3.getParent() != null) {
                ((ViewGroup) relativeLayout3.getParent()).removeView(relativeLayout3);
            } else if (this.C != null) {
                this.C.removeView(this.D);
            }
            if (relativeLayout != null) {
                relativeLayout.removeView(relativeLayout2);
            } else if (relativeLayout2 != null && relativeLayout2.getParent() != null) {
                ((ViewGroup) relativeLayout2.getParent()).removeView(relativeLayout2);
            } else if (this.B != null) {
                ((ViewGroup) this.B).removeView(this.C);
                ((ViewGroup) this.B.getParent()).removeView(this.B);
            }
            if (frameLayout != null) {
                if (relativeLayout != null) {
                    frameLayout.removeView(relativeLayout);
                } else {
                    frameLayout.removeView(this.B);
                    if (this.B.getParent() != null) {
                        ((ViewGroup) this.B.getParent()).removeView(this.B);
                        this.B.setVisibility(8);
                    }
                }
            }
            setVisibility(0);
            requestLayout();
            if (viewGroup != null) {
                if (this.t.getParent() != null && this.t.getParent() != viewGroup) {
                    ((ViewGroup) this.t.getParent()).removeView(this.t);
                }
                viewGroup.addView(this.t, this.I);
                viewGroup.removeView(frameLayout2);
                viewGroup.invalidate();
            }
            i();
            this.h = false;
            if (this.t != null) {
                this.t.setExitFullScreen();
                if (this.f) {
                    this.t.openSound();
                } else {
                    this.t.closeSound();
                }
                this.t.gonePauseView();
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    private boolean y() {
        try {
            this.D = this.C.getMBridgeFullPlayContainer();
            this.H = this.C.getMBridgeFullPlayerParent();
            this.E = this.C.getMBridgeFullClose();
            this.F = this.C.getMBridgeFullTvInstall();
            this.G = this.C.getMBridgeFullPb();
            return true;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            return false;
        }
    }

    private void z() {
        try {
            this.i = w.i(getContext()) >= w.j(getContext());
            this.j = this.i;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private void A() {
        try {
            if (this.K != null && !z.a(this.K.getVideoResolution())) {
                String videoResolution = this.K.getVideoResolution();
                s.d(TAG, "videoResolution:" + videoResolution);
                String[] strArrSplit = videoResolution.split("x");
                if (strArrSplit == null || strArrSplit.length != 2) {
                    return;
                }
                String str = strArrSplit[0];
                String str2 = strArrSplit[1];
                double dB = w.b(str);
                double dB2 = w.b(str2);
                if (dB <= 0.0d || dB2 <= 0.0d) {
                    return;
                }
                this.N = dB;
                this.O = dB2;
                return;
            }
            s.b(TAG, "campaign is null initVideoWH return");
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float B() {
        try {
            float fI = w.i(getContext());
            return this.i ? fI + w.k(getContext()) : fI;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            return 0.0f;
        }
    }

    private float C() {
        try {
            float fJ = w.j(getContext());
            return !this.i ? fJ + w.k(getContext()) : fJ;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            return 0.0f;
        }
    }

    private boolean c(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    private void a(View view, float f2, float f3) {
        try {
            if (view == null) {
                s.b(TAG, "setPlayViewParamsByVidoWH view is null");
                return;
            }
            double d2 = 0.0d;
            if (this.N > 0.0d && this.O > 0.0d) {
                double d3 = this.N / this.O;
                if (f2 > 0.0f && f3 > 0.0f) {
                    d2 = f2 / f3;
                }
                double dA = w.a(Double.valueOf(d3));
                double dA2 = w.a(Double.valueOf(d2));
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.u.getLayoutParams();
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                boolean z = c(getContext()) && this.i;
                int i = -1;
                if (dA > dA2) {
                    double d4 = (f2 * this.O) / this.N;
                    s.b(TAG, " setPlayView 宽铺满 playerViewHeight:" + d4 + " onMeasure mDevWidth " + this.M + " * mDevHeight *****" + this.L);
                    layoutParams2.width = -1;
                    if (!z) {
                        i = (int) d4;
                    }
                    layoutParams2.height = i;
                    layoutParams2.addRule(13);
                    layoutParams.width = this.M;
                    layoutParams.height = (int) d4;
                    layoutParams.addRule(13);
                } else if (dA < dA2) {
                    double d5 = f3 * d3;
                    layoutParams2.width = z ? -1 : (int) d5;
                    layoutParams2.height = -1;
                    layoutParams2.addRule(13);
                    layoutParams.width = (int) d5;
                    layoutParams.height = this.L;
                    layoutParams.addRule(13);
                    s.b(TAG, "setPlayView 高铺满 playerViewWidth:" + d5 + " mDevWidth " + this.M + " * mDevHeight *****" + this.L);
                } else {
                    layoutParams2.width = -1;
                    layoutParams2.height = -1;
                    layoutParams.width = this.M;
                    layoutParams.height = this.L;
                    layoutParams.addRule(13);
                    s.b(TAG, "setPlayView 铺满父布局  videoWHDivide_final：" + dA + "  screenWHDivide_final：" + dA2);
                }
                if (!this.h) {
                    this.u.setLayoutParams(layoutParams);
                }
                view.setLayoutParams(layoutParams2);
                return;
            }
            b(view);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    private void b(View view) {
        try {
            if (view == null) {
                s.b(TAG, "setPlayViewParamsDefault view is null");
                return;
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = -1;
            if (this.i) {
                layoutParams.height = -1;
            } else {
                layoutParams.height = (((int) B()) * 9) / 16;
                layoutParams.addRule(13);
            }
            view.setLayoutParams(layoutParams);
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        try {
            if (this.E == null && this.af == null) {
                return;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 30.0f), w.b(getContext(), 30.0f));
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            layoutParams.topMargin = w.b(getContext(), 8.0f);
            layoutParams.rightMargin = w.b(getContext(), 8.0f);
            if (this.E != null) {
                this.E.setLayoutParams(layoutParams);
            }
            if (this.af != null) {
                this.af.setLayoutParams(layoutParams);
            }
            updateViewManger(false);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        try {
            if (this.E == null && this.af == null) {
                return;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 30.0f), w.b(getContext(), 30.0f));
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            if (this.ab == 0 && w.a(getContext())) {
                layoutParams.rightMargin = w.k(getContext()) + w.b(getContext(), 8.0f);
            } else {
                layoutParams.rightMargin = w.b(getContext(), 8.0f);
            }
            layoutParams.topMargin = w.b(getContext(), 8.0f);
            if (this.E != null) {
                this.E.setLayoutParams(layoutParams);
            }
            if (this.af != null) {
                this.af.setLayoutParams(layoutParams);
            }
            updateViewManger(true);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    public void updateViewManger(boolean z) {
        com.mbridge.msdk.nativex.view.mbfullview.a aVarA = com.mbridge.msdk.nativex.view.mbfullview.a.a(getContext());
        if (aVarA != null) {
            try {
                this.C.getmAnimationPlayer().clearAnimation();
                aVarA.a(z, !this.t.isComplete(), this.C);
                aVarA.a(z, this.C, this.ab);
            } catch (NullPointerException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (!(this.C instanceof MBridgeTopFullView) || aVarA == null) {
            return;
        }
        boolean z2 = !z;
        BaseView baseView = this.C;
        if (baseView instanceof MBridgeTopFullView) {
            MBridgeTopFullView mBridgeTopFullView = (MBridgeTopFullView) baseView;
            int i = z2 ? 0 : 8;
            mBridgeTopFullView.getMBridgeFullViewDisplayIcon().setVisibility(i);
            mBridgeTopFullView.getMBridgeFullViewDisplayTitle().setVisibility(i);
            mBridgeTopFullView.getMBridgeFullViewDisplayDscription().setVisibility(i);
            mBridgeTopFullView.getStarLevelLayoutView().setVisibility(i);
        }
    }

    private void F() {
        if (this.K.isReportClick()) {
            return;
        }
        this.K.setReportClick(true);
        if (this.K == null || this.K.getNativeVideoTracking() == null || this.K.getNativeVideoTracking().j() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(getContext(), this.K, this.K.getCampaignUnitId(), this.K.getNativeVideoTracking().j(), false, false);
    }

    private void a(int i, int i2) {
        CampaignEx.b mediaViewHolder;
        try {
            if (this.K == null || (mediaViewHolder = this.K.getMediaViewHolder()) == null || mediaViewHolder.i || i2 == 0) {
                return;
            }
            List<Map<Integer, String>> listG = this.K.getNativeVideoTracking().g();
            int i3 = ((i + 1) * 100) / i2;
            if (listG != null) {
                int i4 = 0;
                while (i4 < listG.size()) {
                    Map<Integer, String> map = listG.get(i4);
                    if (map != null && map.size() > 0) {
                        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Integer, String> next = it.next();
                            int iIntValue = next.getKey().intValue();
                            String value = next.getValue();
                            if (iIntValue <= i3 && !TextUtils.isEmpty(value)) {
                                com.mbridge.msdk.click.b.a(getContext(), this.K, this.K.getCampaignUnitId(), new String[]{value}, false, true);
                                it.remove();
                                listG.remove(i4);
                                i4--;
                            }
                        }
                    }
                    i4++;
                }
                if (listG.size() <= 0) {
                    mediaViewHolder.i = true;
                }
            }
        } catch (Throwable unused) {
            s.d(TAG, "reportPlayPercentageData error");
        }
    }

    private void G() {
        int i;
        try {
            if (this.K == null || this.K.getNativeVideoTracking() == null) {
                return;
            }
            String[] strArrF = this.K.getNativeVideoTracking().f();
            if (this.i) {
                i = o;
            } else {
                i = p;
            }
            for (String str : strArrF) {
                if (!TextUtils.isEmpty(str)) {
                    com.mbridge.msdk.click.b.a(getContext(), this.K, this.K.getCampaignUnitId(), str + "&orienation=" + i, false, false);
                }
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    public void changeNoticeURL() {
        if (this.K != null) {
            String noticeUrl = this.K.getNoticeUrl();
            if (TextUtils.isEmpty(noticeUrl)) {
                return;
            }
            if (noticeUrl.contains("is_video")) {
                if (this.V == a.VIDEO) {
                    if (noticeUrl.contains("is_video=2")) {
                        noticeUrl = noticeUrl.replace("is_video=2", "is_video=1");
                    }
                } else if (this.V == a.BIG_IMAGE && noticeUrl.contains("is_video=1")) {
                    noticeUrl = noticeUrl.replace("is_video=1", "is_video=2");
                }
            } else {
                String str = "";
                if (this.V == a.VIDEO) {
                    str = "1";
                } else if (this.V == a.BIG_IMAGE) {
                    str = "2";
                }
                StringBuilder sb = new StringBuilder(noticeUrl);
                if (noticeUrl.contains("?")) {
                    sb.append("&is_video=");
                    sb.append(str);
                } else {
                    sb.append("?is_video=");
                    sb.append(str);
                }
                noticeUrl = sb.toString();
            }
            this.K.setNoticeUrl(noticeUrl);
        }
    }

    public String getAddNVT2ToNoticeURL() {
        if (this.K == null) {
            return null;
        }
        String noticeUrl = this.K.getNoticeUrl();
        if (TextUtils.isEmpty(noticeUrl) || noticeUrl.contains(CampaignEx.JSON_KEY_NV_T2)) {
            return noticeUrl;
        }
        return noticeUrl + "&nv_t2=" + this.K.getNvT2();
    }

    public boolean canShowVideo() {
        return a(false) == a.VIDEO;
    }

    private class e implements SensorEventListener {
        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        private e() {
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
            int iRound;
            if (MBMediaView.this.ah) {
                return;
            }
            try {
                float[] fArr = sensorEvent.values;
                float f = -fArr[0];
                float f2 = -fArr[1];
                float f3 = -fArr[2];
                if (((f * f) + (f2 * f2)) * 4.0f >= f3 * f3) {
                    iRound = 90 - Math.round(((float) Math.atan2(-f2, f)) * 57.29578f);
                    while (iRound >= 360) {
                        iRound -= 360;
                    }
                    while (iRound < 0) {
                        iRound += 360;
                    }
                } else {
                    iRound = -1;
                }
                float fB = MBMediaView.this.B();
                int iJ = w.j(MBMediaView.this.getContext());
                if ((iRound <= 45 || iRound >= 135) && (iRound <= 225 || iRound >= 315)) {
                    if (((iRound <= 135 || iRound >= 225) && ((iRound <= 315 || iRound >= 360) && ((iRound < 0 || iRound > 45) && iRound != -1))) || fB > iJ || !MBMediaView.this.j) {
                        return;
                    }
                    s.b(MBMediaView.TAG, "onSensorChanged: to protrait: |||||||||||||||||");
                    MBMediaView.this.i = false;
                    MBMediaView.this.j = false;
                    MBMediaView.this.J.postDelayed(new Runnable() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.e.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                s.b(MBMediaView.TAG, "onSensorChanged: is portrait: |||||||||||||||||");
                                com.mbridge.msdk.nativex.view.mbfullview.a.a(MBMediaView.this.getContext()).a(MBMediaView.this.C, MBMediaView.this.i);
                                MBMediaView.this.D();
                                MBMediaView.this.h();
                                MBMediaView.this.f();
                                MBMediaView.this.g();
                                if (MBMediaView.this.y != null) {
                                    s.a(MBMediaView.TAG, "=====orientation|||||");
                                    MBMediaView.this.y.orientation(MBMediaView.this.i);
                                }
                            } catch (Exception e) {
                                s.d(MBMediaView.TAG, e.getMessage());
                            }
                        }
                    }, 200L);
                    return;
                }
                if (fB < iJ || MBMediaView.this.j) {
                    return;
                }
                s.b(MBMediaView.TAG, "onSensorChanged: to LandScape: --------------");
                MBMediaView.this.i = true;
                MBMediaView.this.j = true;
                MBMediaView.this.J.postDelayed(new Runnable() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.e.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            s.b(MBMediaView.TAG, "onSensorChanged: is LandScape: --------------");
                            com.mbridge.msdk.nativex.view.mbfullview.a.a(MBMediaView.this.getContext()).a(MBMediaView.this.C, MBMediaView.this.i);
                            MBMediaView.this.E();
                            MBMediaView.this.h();
                            MBMediaView.this.f();
                            MBMediaView.this.g();
                            if (MBMediaView.this.y != null) {
                                s.a(MBMediaView.TAG, "=====orientation----");
                                MBMediaView.this.y.orientation(MBMediaView.this.i);
                            }
                        } catch (Exception e) {
                            s.d(MBMediaView.TAG, e.getMessage());
                        }
                    }
                }, 200L);
            } catch (Throwable th) {
                s.b(MBMediaView.TAG, th.getMessage(), th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindVaneWebViewForNV H() {
        try {
            s.d(TAG, "getEndCardWebview hadStarLoad:" + this.ad + "-endCardWebview:" + this.y);
            if (this.y != null && this.ad) {
                return this.y;
            }
            if (this.ad) {
                return null;
            }
            b(true);
            return null;
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
            return null;
        }
    }

    private void b(boolean z) {
        try {
            int nvT2 = this.K.getNvT2();
            if (this.y == null) {
                this.y = new WindVaneWebViewForNV(getContext());
                this.y.setObject(this);
                this.y.setBackListener(new com.mbridge.msdk.nativex.listener.b() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.6
                    @Override // com.mbridge.msdk.nativex.listener.b
                    public final void a() {
                        MBMediaView.this.exitFullScreen();
                    }
                });
                this.y.setWebViewListener(new com.mbridge.msdk.mbjscommon.c.b() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.7
                    @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
                    public final void a(WebView webView, String str) {
                        super.a(webView, str);
                        MBMediaView.this.ac = true;
                    }

                    @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
                    public final void a(WebView webView, int i, String str, String str2) {
                        super.a(webView, i, str, str2);
                        MBMediaView.this.ac = false;
                    }
                });
            }
            if (nvT2 != 3) {
                if (nvT2 == 4) {
                    this.ad = true;
                    if (this.K != null) {
                        BrowserView.MBDownloadListener mBDownloadListener = new BrowserView.MBDownloadListener(this.K);
                        mBDownloadListener.setTitle(this.K.getAppName());
                        this.y.setDownloadListener(mBDownloadListener);
                        this.y.setFilter(new d());
                        return;
                    }
                    return;
                }
                return;
            }
            String str = this.K.getendcard_url();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.K.getMediaViewHolder();
            if (str.contains(".zip") && str.contains(ResourceManager.KEY_MD5FILENAME)) {
                String h5ResAddress = H5DownLoadManager.getInstance().getH5ResAddress(str);
                if (z.b(h5ResAddress)) {
                    this.ad = true;
                    this.y.loadUrl(h5ResAddress);
                    return;
                }
                return;
            }
            String htmlContentFromUrl = HTMLResourceManager.getInstance().getHtmlContentFromUrl(str);
            if (z.b(htmlContentFromUrl)) {
                s.a(TAG, "load html...");
                this.ad = true;
                this.y.loadDataWithBaseURL(str, htmlContentFromUrl, "text/html", C.UTF8_NAME, null);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    private static final class g extends WebViewClient {
        WeakReference<MBMediaView> a;

        public g(MBMediaView mBMediaView) {
            this.a = new WeakReference<>(mBMediaView);
        }

        @Override // android.webkit.WebViewClient
        public final void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            try {
                MBMediaView mBMediaView = this.a.get();
                if (mBMediaView == null || mBMediaView.V == null || mBMediaView.V != a.BIG_IMAGE) {
                    return;
                }
                mBMediaView.m();
                mBMediaView.V = a.GIF;
                mBMediaView.changeNoticeURL();
            } catch (Exception e) {
                s.d(MBMediaView.TAG, e.getMessage());
            }
        }
    }

    private static final class f implements com.mbridge.msdk.videocommon.listener.a {
        WeakReference<MBMediaView> a;

        @Override // com.mbridge.msdk.videocommon.listener.a
        public final void a(String str, String str2) {
        }

        public f(MBMediaView mBMediaView) {
            this.a = new WeakReference<>(mBMediaView);
        }

        @Override // com.mbridge.msdk.videocommon.listener.a
        public final void a(String str) {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                mBMediaView.J.post(new b(mBMediaView));
            }
        }
    }

    private static final class b implements Runnable {
        WeakReference<MBMediaView> a;

        public b(MBMediaView mBMediaView) {
            this.a = new WeakReference<>(mBMediaView);
        }

        @Override // java.lang.Runnable
        public final void run() {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                try {
                    if (mBMediaView.V == null || mBMediaView.V != a.BIG_IMAGE) {
                        return;
                    }
                    mBMediaView.e();
                    mBMediaView.V = a.VIDEO;
                    mBMediaView.changeNoticeURL();
                } catch (Throwable th) {
                    s.b(MBMediaView.TAG, th.getMessage(), th);
                }
            }
        }
    }

    private static final class c implements com.mbridge.msdk.nativex.listener.a {
        WeakReference<MBMediaView> a;

        public c(MBMediaView mBMediaView) {
            this.a = new WeakReference<>(mBMediaView);
        }

        @Override // com.mbridge.msdk.nativex.listener.a
        public final void a() {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                MBMediaView.L(mBMediaView);
            }
        }

        @Override // com.mbridge.msdk.nativex.listener.a
        public final void b() {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                MBMediaView.M(mBMediaView);
            }
        }

        @Override // com.mbridge.msdk.nativex.listener.a
        public final void c() {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                MBMediaView.N(mBMediaView);
            }
        }

        @Override // com.mbridge.msdk.nativex.listener.a
        public final void d() {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                MBMediaView.O(mBMediaView);
            }
        }

        @Override // com.mbridge.msdk.nativex.listener.a
        public final void a(String str) {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                mBMediaView.a(str);
            }
        }

        @Override // com.mbridge.msdk.nativex.listener.a
        public final void b(String str) {
            MBMediaView mBMediaView = this.a.get();
            if (mBMediaView != null) {
                mBMediaView.b(str);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public BaseView.a handleViewStyleResult(Context context) {
        int nvT2 = this.K.getNvT2();
        if (nvT2 != 6) {
            switch (nvT2) {
                case 1:
                    b(context);
                    return null;
                case 2:
                case 3:
                case 4:
                    return BaseView.a.FULL_MIDDLE_VIEW;
                default:
                    return null;
            }
        }
        return BaseView.a.FULL_TOP_VIEW;
    }

    private static final class d implements com.mbridge.msdk.mbjscommon.base.a {
        private d() {
        }

        @Override // com.mbridge.msdk.mbjscommon.base.a
        public final boolean a(String str) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (v.a.a(str)) {
                        v.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, (NativeListener.NativeTrackingListener) null);
                        return true;
                    }
                    if (URLUtil.isNetworkUrl(str)) {
                        return false;
                    }
                    v.b(com.mbridge.msdk.foundation.controller.a.f().j(), str, null);
                    return true;
                }
            } catch (Exception e) {
                s.d(MBMediaView.TAG, e.getMessage());
            }
            return false;
        }
    }

    public void setProgressVisibility(boolean z) {
        this.k = z;
        if (this.t != null) {
            this.t.showProgressView(this.k);
        }
    }

    public void setSoundIndicatorVisibility(boolean z) {
        this.l = z;
        if (this.t != null) {
            this.t.showSoundIndicator(this.l);
        }
    }

    public void setVideoSoundOnOff(boolean z) {
        this.f = z;
        if (this.t != null) {
            if (this.f) {
                this.t.openSound();
            } else {
                this.t.closeSound();
            }
        }
    }

    public BaseView getFullScreenViewByStyle(Context context, BaseView.a aVar) {
        BaseView mBridgeFullView;
        switch (aVar) {
            case FULL_MIDDLE_VIEW:
                mBridgeFullView = new MBridgeFullView(context);
                break;
            case FULL_TOP_VIEW:
                mBridgeFullView = new MBridgeTopFullView(context);
                break;
            default:
                mBridgeFullView = null;
                break;
        }
        mBridgeFullView.setStytle(aVar);
        return mBridgeFullView;
    }

    public void setFullScreenViewBackgroundColor(int i) {
        this.s = i;
    }

    public void setFollowActivityOrientation(boolean z) {
        this.ah = z;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        s.d(TAG, "onConfigurationChange " + configuration.orientation);
        if (!this.ah || this.ag == configuration.orientation) {
            return;
        }
        this.ag = configuration.orientation;
        this.i = this.ag == 0;
        this.j = this.ag == 0;
        this.J.postDelayed(new Runnable() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.8
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    com.mbridge.msdk.nativex.view.mbfullview.a.a(MBMediaView.this.getContext()).a(MBMediaView.this.C, MBMediaView.this.i);
                    if (MBMediaView.this.ag == 0) {
                        MBMediaView.this.E();
                    } else {
                        MBMediaView.this.D();
                    }
                    MBMediaView.this.h();
                    MBMediaView.this.f();
                    MBMediaView.this.g();
                    if (MBMediaView.this.y != null) {
                        MBMediaView.this.y.orientation(MBMediaView.this.i);
                    }
                } catch (Exception e2) {
                    s.d(MBMediaView.TAG, e2.getMessage());
                }
            }
        }, 200L);
    }

    public boolean ismCurIsFullScreen() {
        return this.h;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        s.d(TAG, "onVisibilityChanged invoked");
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void getEndScreenInfo(Object obj, String str) {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.K);
            String strA = a(arrayList, p(), "MAL_15.7.57,3.0.1");
            String strEncodeToString = !TextUtils.isEmpty(strA) ? Base64.encodeToString(strA.getBytes(), 2) : "";
            s.d(TAG, "====getEndScreenInfo-mCampaign.name:" + this.K.getAppName());
            h.a().a(obj, strEncodeToString);
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void install(Object obj, String str) {
        WindVaneWebView windVaneWebView;
        try {
            if (this.ai != null) {
                b(this.ai);
            } else {
                if (!(obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) || (windVaneWebView = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a) == null) {
                    return;
                }
                b(windVaneWebView.getContext());
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void openURL(Object obj, String str) throws JSONException {
        WindVaneWebView windVaneWebView;
        s.d(TAG, "openURL:" + str);
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "params is null");
            return;
        }
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (contextJ == null) {
            try {
                if ((obj instanceof com.mbridge.msdk.mbjscommon.windvane.a) && (windVaneWebView = ((com.mbridge.msdk.mbjscommon.windvane.a) obj).a) != null) {
                    contextJ = windVaneWebView.getContext();
                }
            } catch (Exception e2) {
                s.d(TAG, e2.getMessage());
            }
        }
        if (contextJ == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url");
            int iOptInt = jSONObject.optInt(com.heytap.mcssdk.mode.Message.TYPE);
            if (iOptInt == 1) {
                com.mbridge.msdk.click.c.a(contextJ, strOptString);
            } else if (iOptInt == 2) {
                com.mbridge.msdk.click.c.b(contextJ, strOptString);
            }
        } catch (JSONException e3) {
            s.d(TAG, e3.getMessage());
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void cai(Object obj, String str) throws JSONException {
        s.a(TAG, "cai:" + str);
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "params is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String strOptString = new JSONObject(str).optString("packageName");
            if (TextUtils.isEmpty(strOptString)) {
                com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "packageName is empty");
            }
            int i = w.c(com.mbridge.msdk.foundation.controller.a.f().j(), strOptString) ? 1 : 2;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, com.mbridge.msdk.mbjscommon.bridge.b.b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("result", i);
                jSONObject.put("data", jSONObject2);
                h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (Exception e2) {
                com.mbridge.msdk.mbjscommon.bridge.b.a(obj, e2.getMessage());
                s.a(TAG, e2.getMessage());
            }
        } catch (JSONException e3) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "exception: " + e3.getLocalizedMessage());
            s.b(TAG, "cai", e3);
        } catch (Throwable th) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, "exception: " + th.getLocalizedMessage());
            s.b(TAG, "cai", th);
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void gial(Object obj, String str) throws JSONException {
        s.a(TAG, "gial:" + str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, com.mbridge.msdk.mbjscommon.bridge.b.b);
            JSONObject jSONObject2 = new JSONObject();
            List<String> list = com.mbridge.msdk.foundation.controller.a.b;
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    jSONArray.put(list.get(i));
                }
            }
            jSONObject2.put("packageNameList", jSONArray);
            jSONObject.put("data", jSONObject2);
            h.a().a(obj, Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, e2.getMessage());
            s.a(TAG, e2.getMessage());
        } catch (Throwable th) {
            com.mbridge.msdk.mbjscommon.bridge.b.a(obj, th.getMessage());
            s.a(TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void toggleCloseBtn(Object obj, String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                try {
                    new JSONObject(str).optInt("state", 1);
                } catch (Exception unused) {
                }
            }
            s.d(TAG, "SHOW CLOSE BTN ");
            showEndCardWebViewCloseBtn();
            h.a().a(obj, a(0));
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
            h.a().b(obj, a(1));
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void triggerCloseBtn(Object obj, String str) {
        try {
            exitFullScreen();
            h.a().a(obj, a(0));
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
            h.a().b(obj, a(1));
        }
    }

    private String a(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CommandMessage.CODE, i);
            String string = jSONObject.toString();
            return !TextUtils.isEmpty(string) ? Base64.encodeToString(string.getBytes(), 2) : "";
        } catch (Throwable unused) {
            s.d(TAG, "code to string is error");
            return "";
        }
    }

    private String a(List<CampaignEx> list, String str, String str2) throws JSONException {
        if (list == null) {
            return null;
        }
        try {
            if (list.size() <= 0) {
                return null;
            }
            JSONArray camplistToJson = CampaignEx.parseCamplistToJson(list);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("campaignList", camplistToJson);
            jSONObject.put(MBridgeConstans.PROPERTIES_UNIT_ID, str);
            jSONObject.put("sdk_info", str2);
            return jSONObject.toString();
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
            return null;
        }
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayProgress(int i, int i2) {
        int i3;
        String str;
        CampaignEx.b mediaViewHolder;
        try {
            if (this.K != null && (mediaViewHolder = this.K.getMediaViewHolder()) != null && !mediaViewHolder.h && mediaViewHolder.l != null && mediaViewHolder.l.size() > 0) {
                Map<Integer, String> map = mediaViewHolder.l;
                s.b(TAG, "reportAdvImp pre advImpMap.size:" + map.size());
                Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, String> next = it.next();
                    Integer key = next.getKey();
                    String value = next.getValue();
                    if (i >= key.intValue() && !TextUtils.isEmpty(value)) {
                        com.mbridge.msdk.click.b.a(getContext(), this.K, this.K.getCampaignUnitId(), value, false, false);
                        it.remove();
                        s.b(TAG, "reportAdvImp remove value:" + value);
                    }
                }
                s.b(TAG, "reportAdvImp advImpMap after size:" + map.size());
                if (map.size() <= 0) {
                    mediaViewHolder.h = true;
                }
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
        a(i, i2);
        if (t() == 100 || this.aj) {
            return;
        }
        int iH = u() != null ? u().h() : 0;
        int iT = t();
        if (iT == 0) {
            return;
        }
        if (iH > iT) {
            iH = iT / 2;
        }
        if (iH < 0 || i < (i3 = (i2 * iH) / 100)) {
            return;
        }
        if (this.K.getAdType() == 94 || this.K.getAdType() == 287) {
            str = this.K.getRequestId() + this.K.getId() + this.K.getVideoUrlEncode();
        } else {
            str = this.K.getId() + this.K.getVideoUrlEncode() + this.K.getBidToken();
        }
        com.mbridge.msdk.videocommon.download.a aVarA = com.mbridge.msdk.videocommon.download.b.getInstance().a(p(), str);
        if (aVarA != null) {
            aVarA.j();
            this.aj = true;
            s.d(TAG, "CDRate is : " + i3 + " and start download !");
        }
    }

    static /* synthetic */ void b(MBMediaView mBMediaView) {
        if (mBMediaView.K == null || mBMediaView.K.getMediaViewHolder() == null) {
            return;
        }
        com.mbridge.msdk.foundation.controller.a.f().a(mBMediaView.getContext());
        com.mbridge.msdk.mbnative.e.b.a(mBMediaView.K, mBMediaView.getContext(), mBMediaView.p(), null);
        CampaignEx.b mediaViewHolder = mBMediaView.K.getMediaViewHolder();
        if (!mediaViewHolder.a && mBMediaView.V == a.VIDEO && z.b(mBMediaView.K.getImpressionURL())) {
            mediaViewHolder.a = true;
            String impressionURL = mBMediaView.K.getImpressionURL();
            if (!impressionURL.contains("is_video=1")) {
                StringBuilder sb = new StringBuilder(impressionURL);
                if (impressionURL.contains("?")) {
                    sb.append("&is_video=1");
                } else {
                    sb.append("?is_video=1");
                }
                impressionURL = sb.toString();
            }
            String str = impressionURL;
            s.b(TAG, "change impressionurl:" + str);
            com.mbridge.msdk.click.b.a(mBMediaView.getContext(), mBMediaView.K, mBMediaView.K.getCampaignUnitId(), str, false, true);
        }
    }

    static /* synthetic */ void d(MBMediaView mBMediaView) {
        CampaignEx.b mediaViewHolder;
        if (mBMediaView.K == null || (mediaViewHolder = mBMediaView.K.getMediaViewHolder()) == null || mediaViewHolder.g || mBMediaView.K.getNativeVideoTracking() == null || mBMediaView.K.getNativeVideoTracking().q() == null) {
            return;
        }
        mediaViewHolder.g = true;
        com.mbridge.msdk.click.b.a(mBMediaView.getContext(), mBMediaView.K, mBMediaView.K.getCampaignUnitId(), mBMediaView.K.getNativeVideoTracking().q(), false, false);
    }

    static /* synthetic */ void h(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.getRootView() != null && (mBMediaView.getRootView() instanceof ViewGroup)) {
                BaseView.a aVarHandleViewStyleResult = mBMediaView.handleViewStyleResult(mBMediaView.getContext());
                if (aVarHandleViewStyleResult == null) {
                    return;
                }
                mBMediaView.C = mBMediaView.getFullScreenViewByStyle(mBMediaView.getContext(), aVarHandleViewStyleResult);
                if (mBMediaView.C == null) {
                    s.b(TAG, "mFullScreenViewUI is null");
                    return;
                }
                if (!mBMediaView.y()) {
                    s.d(TAG, "fullViewFailed return");
                    return;
                }
                com.mbridge.msdk.nativex.view.mbfullview.a.a(mBMediaView.getContext()).a(mBMediaView.C.style, mBMediaView.K, mBMediaView.C);
                mBMediaView.h = true;
                mBMediaView.r = false;
                if (mBMediaView.t != null) {
                    mBMediaView.t.setEnterFullScreen();
                    mBMediaView.t.setIsActivePause(false);
                }
                try {
                    if (mBMediaView.W != null) {
                        mBMediaView.W.onEnterFullscreen();
                    }
                    if (mBMediaView.aa != null) {
                        mBMediaView.aa.onEnterFullscreen();
                    }
                } catch (Exception e2) {
                    s.d(TAG, e2.getMessage());
                }
                FrameLayout frameLayout = (FrameLayout) mBMediaView.getRootView().findViewById(R.id.content);
                RelativeLayout relativeLayout = new RelativeLayout(mBMediaView.getContext());
                mBMediaView.B = relativeLayout;
                mBMediaView.B.setClickable(true);
                ViewGroup viewGroup = (ViewGroup) mBMediaView.t.getParent();
                int childCount = viewGroup.getChildCount();
                int i = 0;
                while (i < childCount && viewGroup.getChildAt(i) != mBMediaView.t) {
                    i++;
                }
                mBMediaView.I = i;
                FrameLayout frameLayout2 = new FrameLayout(mBMediaView.getContext());
                frameLayout2.setId(100);
                viewGroup.addView(frameLayout2, i, new ViewGroup.LayoutParams(mBMediaView.getWidth(), mBMediaView.getHeight()));
                viewGroup.removeView(mBMediaView.t);
                ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                relativeLayout.setId(101);
                new RelativeLayout.LayoutParams(-1, -1);
                mBMediaView.H.addView(mBMediaView.t, new RelativeLayout.LayoutParams(-1, -1));
                frameLayout.addView(relativeLayout, layoutParams);
                int i2 = -16777216;
                switch (mBMediaView.C.style) {
                    case FULL_TOP_VIEW:
                        i2 = -1;
                        break;
                }
                if (mBMediaView.s != 0) {
                    relativeLayout.setBackgroundColor(mBMediaView.s);
                } else {
                    relativeLayout.setBackgroundColor(i2);
                }
                mBMediaView.C.setId(103);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                int[] iArr = new int[2];
                frameLayout.getLocationInWindow(iArr);
                mBMediaView.ab = iArr[1];
                s.b(TAG, "mFullViewStartY:" + mBMediaView.ab);
                if (mBMediaView.ab == 0) {
                    s.b(TAG, "addfullview 增加状态栏高度 沉浸式时contentview的高度和屏幕高度一样");
                    if (Build.VERSION.SDK_INT >= 23) {
                        WindowInsets rootWindowInsets = mBMediaView.getRootWindowInsets();
                        if (rootWindowInsets != null) {
                            layoutParams2.setMargins(0, rootWindowInsets.getStableInsetTop(), 0, 0);
                        }
                    } else {
                        layoutParams2.setMargins(0, w.e(mBMediaView.getContext()), 0, 0);
                    }
                }
                relativeLayout.addView(mBMediaView.C, layoutParams2);
                try {
                    mBMediaView.z();
                    mBMediaView.a(mBMediaView.D, mBMediaView.B(), mBMediaView.C());
                    com.mbridge.msdk.nativex.view.mbfullview.a.a(mBMediaView.getContext()).a(mBMediaView.C, mBMediaView.i);
                    if (mBMediaView.i) {
                        mBMediaView.E();
                    } else {
                        mBMediaView.D();
                    }
                    mBMediaView.J.postDelayed(new Runnable() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.5
                        @Override // java.lang.Runnable
                        public final void run() {
                            MBMediaView.this.r = true;
                            if (MBMediaView.this.h) {
                                TextView unused = MBMediaView.this.F;
                            }
                        }
                    }, 3000L);
                } catch (Throwable th) {
                    s.d(TAG, th.getMessage());
                }
                try {
                    mBMediaView.B.setFocusableInTouchMode(true);
                    mBMediaView.B.requestFocus();
                    mBMediaView.B.setOnKeyListener(new View.OnKeyListener() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.18
                        @Override // android.view.View.OnKeyListener
                        public final boolean onKey(View view, int i3, KeyEvent keyEvent) {
                            try {
                                if (keyEvent.getKeyCode() != 4) {
                                    return false;
                                }
                                MBMediaView.this.x();
                                return true;
                            } catch (Throwable th2) {
                                s.d(MBMediaView.TAG, th2.getMessage());
                                return false;
                            }
                        }
                    });
                    mBMediaView.B.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            MBMediaView.i(MBMediaView.this);
                        }
                    });
                    mBMediaView.E.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            MBMediaView.this.x();
                        }
                    });
                    mBMediaView.F.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.nativex.view.MBMediaView.4
                        @Override // com.mbridge.msdk.widget.a
                        protected final void a(View view) {
                            try {
                                s.b(MBMediaView.TAG, "点击安装 click");
                                MBMediaView.this.b(view.getContext());
                            } catch (Exception e3) {
                                s.d(MBMediaView.TAG, e3.getMessage());
                            }
                        }
                    });
                } catch (Exception e3) {
                    s.d(TAG, e3.getMessage());
                }
                if (mBMediaView.t != null) {
                    mBMediaView.t.openSound();
                }
                mBMediaView.t.setMediaViewPlayListener(new MediaViewPlayerView.a(mBMediaView.t) { // from class: com.mbridge.msdk.nativex.view.MBMediaView.16
                    @Override // com.mbridge.msdk.nativex.view.MediaViewPlayerView.a
                    public final void a() throws JSONException {
                        int nvT2 = MBMediaView.this.K.getNvT2();
                        if (MBMediaView.this.h && (nvT2 == 3 || nvT2 == 4)) {
                            WindVaneWebViewForNV windVaneWebViewForNVH = MBMediaView.this.H();
                            if (windVaneWebViewForNVH != null) {
                                View viewW = MBMediaView.this.w();
                                if (viewW != null) {
                                    if (nvT2 == 3 && MBMediaView.this.ac) {
                                        com.mbridge.msdk.nativex.view.mbfullview.a.a(MBMediaView.this.getContext()).a(viewW, MBMediaView.this.C);
                                        Context context = MBMediaView.this.getContext();
                                        CampaignEx campaignEx = MBMediaView.this.K;
                                        String campaignUnitId = MBMediaView.this.K.getCampaignUnitId();
                                        if (campaignEx != null) {
                                            try {
                                                if (campaignEx.getNativeVideoTracking() != null && campaignEx.getNativeVideoTracking().o() != null) {
                                                    for (String str : campaignEx.getNativeVideoTracking().o()) {
                                                        if (!TextUtils.isEmpty(str)) {
                                                            com.mbridge.msdk.click.b.a(context, campaignEx, campaignUnitId, str, false, true);
                                                        }
                                                    }
                                                }
                                            } catch (Exception e4) {
                                                e4.printStackTrace();
                                            }
                                        }
                                    } else if (nvT2 == 4) {
                                        com.mbridge.msdk.nativex.view.mbfullview.a.a(MBMediaView.this.getContext()).a(viewW, MBMediaView.this.C);
                                        String clickURL = MBMediaView.this.K.getClickURL();
                                        if (!TextUtils.isEmpty(clickURL)) {
                                            com.mbridge.msdk.click.b.a(MBMediaView.this.getContext(), MBMediaView.this.K, MBMediaView.this.p(), MBMediaView.this.getAddNVT2ToNoticeURL(), true, false);
                                            MBMediaView.this.y.loadUrl(clickURL);
                                        }
                                    } else {
                                        super.a();
                                        return;
                                    }
                                    windVaneWebViewForNVH.webViewShow(MBMediaView.this.K, MBMediaView.this.p());
                                    windVaneWebViewForNVH.orientation(MBMediaView.this.i);
                                    return;
                                }
                                super.a();
                                return;
                            }
                            super.a();
                            return;
                        }
                        super.a();
                    }
                });
                mBMediaView.b(false);
                if (mBMediaView.K != null && mBMediaView.K.getMediaViewHolder() != null && !mBMediaView.K.getMediaViewHolder().k && !TextUtils.isEmpty(mBMediaView.K.getCampaignUnitId()) && mBMediaView.K.getNativeVideoTracking() != null && mBMediaView.K.getNativeVideoTracking().f() != null) {
                    mBMediaView.K.getMediaViewHolder().k = true;
                    mBMediaView.G();
                }
                s.b(TAG, "mediaview add to full screen");
                return;
            }
            s.b(TAG, "rootView is null");
        } catch (Exception e4) {
            s.d(TAG, e4.getMessage());
        }
    }

    static /* synthetic */ void i(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.t == null) {
                s.b(TAG, "playerview is null return");
            } else {
                mBMediaView.t.onClickPlayerView();
            }
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    static /* synthetic */ void m(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.A != null) {
                mBMediaView.A.setVisibility(0);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    static /* synthetic */ void n(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.G != null) {
                mBMediaView.G.setVisibility(0);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    static /* synthetic */ void a(MBMediaView mBMediaView, Campaign campaign, String str) {
        try {
            if (mBMediaView.W != null) {
                mBMediaView.W.onStartRedirection(campaign, str);
            }
            if (mBMediaView.aa != null) {
                mBMediaView.aa.onStartRedirection(campaign, str);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    static /* synthetic */ void o(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.A != null) {
                mBMediaView.A.setVisibility(8);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    static /* synthetic */ void p(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.G != null) {
                mBMediaView.G.setVisibility(8);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    static /* synthetic */ void b(MBMediaView mBMediaView, Campaign campaign, String str) {
        try {
            if (mBMediaView.W != null) {
                mBMediaView.W.onRedirectionFailed(campaign, str);
            }
            if (mBMediaView.aa != null) {
                mBMediaView.aa.onRedirectionFailed(campaign, str);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    static /* synthetic */ void c(MBMediaView mBMediaView, Campaign campaign, String str) {
        try {
            if (mBMediaView.W != null) {
                mBMediaView.W.onFinishRedirection(campaign, str);
            }
            if (mBMediaView.aa != null) {
                mBMediaView.aa.onFinishRedirection(campaign, str);
            }
        } catch (Exception e2) {
            s.d(TAG, e2.getMessage());
        }
    }

    static /* synthetic */ void L(MBMediaView mBMediaView) {
        CampaignEx.b mediaViewHolder;
        if (mBMediaView.K == null || (mediaViewHolder = mBMediaView.K.getMediaViewHolder()) == null || mediaViewHolder.b || mBMediaView.K.getNativeVideoTracking() == null || mBMediaView.K.getNativeVideoTracking().h() == null) {
            return;
        }
        mediaViewHolder.b = true;
        com.mbridge.msdk.click.b.a(mBMediaView.getContext(), mBMediaView.K, mBMediaView.K.getCampaignUnitId(), mBMediaView.K.getNativeVideoTracking().h(), false, false);
    }

    static /* synthetic */ void M(MBMediaView mBMediaView) {
        CampaignEx.b mediaViewHolder;
        if (mBMediaView.K == null || (mediaViewHolder = mBMediaView.K.getMediaViewHolder()) == null || mediaViewHolder.c || mBMediaView.K.getNativeVideoTracking() == null || mBMediaView.K.getNativeVideoTracking().i() == null) {
            return;
        }
        mediaViewHolder.c = true;
        com.mbridge.msdk.click.b.a(mBMediaView.getContext(), mBMediaView.K, mBMediaView.K.getCampaignUnitId(), mBMediaView.K.getNativeVideoTracking().i(), false, false);
    }

    static /* synthetic */ void N(MBMediaView mBMediaView) {
        CampaignEx.b mediaViewHolder;
        if (mBMediaView.K == null || (mediaViewHolder = mBMediaView.K.getMediaViewHolder()) == null || mediaViewHolder.e || mBMediaView.K.getNativeVideoTracking() == null || mBMediaView.K.getNativeVideoTracking().k() == null) {
            return;
        }
        mediaViewHolder.e = true;
        com.mbridge.msdk.click.b.a(mBMediaView.getContext(), mBMediaView.K, mBMediaView.K.getCampaignUnitId(), mBMediaView.K.getNativeVideoTracking().k(), false, false);
    }

    static /* synthetic */ void O(MBMediaView mBMediaView) {
        CampaignEx.b mediaViewHolder;
        if (mBMediaView.K == null || (mediaViewHolder = mBMediaView.K.getMediaViewHolder()) == null || mediaViewHolder.f || mBMediaView.K.getNativeVideoTracking() == null || mBMediaView.K.getNativeVideoTracking().l() == null) {
            return;
        }
        mediaViewHolder.f = true;
        com.mbridge.msdk.click.b.a(mBMediaView.getContext(), mBMediaView.K, mBMediaView.K.getCampaignUnitId(), mBMediaView.K.getNativeVideoTracking().l(), false, false);
    }

    static /* synthetic */ void Q(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.t != null && mBMediaView.t.hasPrepare() && mBMediaView.t.isPlaying()) {
                s.b(TAG, "isPlaying pasue======");
                try {
                    if (mBMediaView.t != null) {
                        mBMediaView.t.pause();
                    }
                } catch (Throwable th) {
                    s.d(TAG, th.getMessage());
                }
            }
        } catch (Throwable th2) {
            s.d(TAG, th2.getMessage());
        }
    }

    static /* synthetic */ void R(MBMediaView mBMediaView) {
        try {
            if (mBMediaView.t == null || !mBMediaView.t.hasPrepare() || mBMediaView.t.isPlaying() || mBMediaView.t.isComplete() || !mBMediaView.t.hasPrepare()) {
                return;
            }
            mBMediaView.t.startOrPlayVideo();
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }
}
