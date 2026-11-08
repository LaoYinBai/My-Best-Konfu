package com.mbridge.msdk.video.module;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.report.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener;
import com.mbridge.msdk.playercommon.PlayerView;
import com.mbridge.msdk.video.js.i;
import com.mbridge.msdk.video.widget.SoundImageView;
import com.mbridge.msdk.videocommon.d.c;
import com.mbridge.msdk.widget.dialog.MBAlertDialog;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeVideoView extends MBridgeBaseView implements i {
    private static boolean O = false;
    public static final String TAG = "MBridgeVideoView";
    private static int i;
    private static int j;
    private static int k;
    private static int l;
    private static int m;
    private String A;
    private double B;
    private double C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    private int M;
    private boolean N;
    private int P;
    private String Q;
    private int R;
    private int S;
    private int T;
    private boolean U;
    private boolean V;
    private boolean W;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private AlphaAnimation ae;
    private b af;
    private boolean ag;
    private PlayerView n;
    private SoundImageView o;
    private TextView p;
    private View q;
    private FrameLayout r;
    private boolean s;
    private boolean t;
    private String u;
    private int v;
    private int w;
    private int x;
    private MBAlertDialog y;
    private com.mbridge.msdk.widget.dialog.a z;

    public boolean isShowingAlertView() {
        return this.s;
    }

    public boolean isInstallDialogShowing() {
        return this.t;
    }

    public void setUnitId(String str) {
        this.A = str;
        if (this.af != null) {
            this.af.a(str);
        }
    }

    public String getUnitId() {
        return this.A;
    }

    public boolean isMiniCardShowing() {
        return this.G;
    }

    public boolean isShowingTransparent() {
        return this.L;
    }

    public void setShowingTransparent(boolean z) {
        this.L = z;
    }

    public MBridgeVideoView(Context context) {
        super(context);
        this.s = false;
        this.t = false;
        this.A = "";
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = false;
        this.N = false;
        this.P = 2;
        this.U = false;
        this.V = false;
        this.W = false;
        this.aa = true;
        this.ab = false;
        this.ac = false;
        this.ad = false;
        this.af = new b(this);
        this.ag = false;
    }

    public MBridgeVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = false;
        this.t = false;
        this.A = "";
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = false;
        this.N = false;
        this.P = 2;
        this.U = false;
        this.V = false;
        this.W = false;
        this.aa = true;
        this.ab = false;
        this.ac = false;
        this.ad = false;
        this.af = new b(this);
        this.ag = false;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void init(Context context) {
        int iFindLayout = findLayout("mbridge_reward_videoview_item");
        if (iFindLayout > 0) {
            this.c.inflate(iFindLayout, this);
            this.f = b();
            if (!this.f) {
                s.d(TAG, "MBridgeVideoView init fail");
            }
            c();
            this.ae = new AlphaAnimation(0.0f, 100.0f);
            this.ae.setDuration(200L);
        }
        O = false;
    }

    public void setIsIV(boolean z) {
        this.N = z;
        if (this.af != null) {
            this.af.a(z);
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void setCampaign(CampaignEx campaignEx) {
        int iQ;
        super.setCampaign(campaignEx);
        if (this.af != null) {
            this.af.a(campaignEx);
            b bVar = this.af;
            if (campaignEx != null && campaignEx.getReady_rate() != -1) {
                iQ = campaignEx.getReady_rate();
            } else {
                iQ = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.A, false).q();
            }
            bVar.a(iQ, com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.A, false).r());
        }
    }

    public void setSoundState(int i2) {
        this.P = i2;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    protected final void c() {
        super.c();
        if (this.f) {
            this.n.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.module.MBridgeVideoView.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MBridgeVideoView.this.e.a(1, "");
                }
            });
            this.o.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.module.MBridgeVideoView.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i2 = 2;
                    if (MBridgeVideoView.this.n.isSilent()) {
                        i2 = 1;
                    }
                    MBridgeVideoView.this.e.a(5, i2);
                }
            });
            this.q.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.module.MBridgeVideoView.3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (MBridgeVideoView.this.N) {
                        MBridgeVideoView.this.ad = true;
                        if (MBridgeVideoView.this.aa) {
                            MBridgeVideoView.this.a();
                            return;
                        } else {
                            MBridgeVideoView.this.e.a(123, "");
                            return;
                        }
                    }
                    MBridgeVideoView.this.a();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            if (this.N && (this.R == com.mbridge.msdk.foundation.same.a.v || this.R == com.mbridge.msdk.foundation.same.a.w)) {
                if (this.U) {
                    if (this.R == com.mbridge.msdk.foundation.same.a.w) {
                        this.e.a(2, b(this.ab));
                        return;
                    }
                    return;
                }
                if (this.R == com.mbridge.msdk.foundation.same.a.w && this.ad) {
                    this.e.a(2, b(this.ab));
                    return;
                }
                if (this.aa) {
                    int curPosition = this.n.getCurPosition() / 1000;
                    int videoLength = (int) ((curPosition / (this.n.getDuration() == 0 ? this.b.getVideoLength() : this.n.getDuration())) * 100.0f);
                    if (this.R == com.mbridge.msdk.foundation.same.a.v) {
                        e();
                        if (this.S == com.mbridge.msdk.foundation.same.a.x && videoLength >= this.T) {
                            this.e.a(2, b(this.ab));
                            return;
                        } else {
                            if (this.S == com.mbridge.msdk.foundation.same.a.y && curPosition >= this.T) {
                                this.e.a(2, b(this.ab));
                                return;
                            }
                            this.e.a(8, "");
                        }
                    }
                    if (this.R == com.mbridge.msdk.foundation.same.a.w) {
                        if (this.S == com.mbridge.msdk.foundation.same.a.x && videoLength >= this.T) {
                            e();
                            this.e.a(8, "");
                            return;
                        } else {
                            if (this.S != com.mbridge.msdk.foundation.same.a.y || curPosition < this.T) {
                                return;
                            }
                            e();
                            this.e.a(8, "");
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            if (this.v > -1 && this.x == 1 && !this.L) {
                e();
                this.e.a(8, "");
            } else {
                this.e.a(2, "");
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void hideAlertView(int i2) {
        if (this.s) {
            this.s = false;
            this.U = true;
            setShowingAlertViewCover(this.s);
            com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.A, false);
            d.a(this.a, this.b, c.a, this.A, 1, i2, 1);
            if (i2 == 0) {
                f();
                if (this.N) {
                    if (this.R == com.mbridge.msdk.foundation.same.a.w || this.R == com.mbridge.msdk.foundation.same.a.v) {
                        this.V = true;
                        this.e.a(124, "");
                        this.ac = true;
                        gonePlayingCloseView();
                        return;
                    }
                    return;
                }
                return;
            }
            this.W = true;
            if (this.N && this.R == com.mbridge.msdk.foundation.same.a.w) {
                f();
            } else if (this.N && this.R == com.mbridge.msdk.foundation.same.a.v) {
                this.e.a(2, b(this.ab));
            } else {
                this.e.a(2, "");
            }
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void alertWebViewShowed() {
        this.s = true;
        setShowingAlertViewCover(this.s);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void dismissAllAlert() {
        if (this.y != null) {
            this.y.dismiss();
        }
        this.e.a(125, "");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void showAlertView() {
        if (this.G) {
            return;
        }
        if (this.z == null) {
            this.z = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.video.module.MBridgeVideoView.4
                @Override // com.mbridge.msdk.widget.dialog.a
                public final void c() {
                    a();
                }

                @Override // com.mbridge.msdk.widget.dialog.a
                public final void a() {
                    MBridgeVideoView.this.s = false;
                    MBridgeVideoView.this.setShowingAlertViewCover(MBridgeVideoView.this.s);
                    if (MBridgeVideoView.this.N && (MBridgeVideoView.this.R == com.mbridge.msdk.foundation.same.a.w || MBridgeVideoView.this.R == com.mbridge.msdk.foundation.same.a.v)) {
                        MBridgeVideoView.this.V = true;
                        MBridgeVideoView.this.e.a(124, "");
                        MBridgeVideoView.this.ac = true;
                        MBridgeVideoView.this.gonePlayingCloseView();
                    }
                    MBridgeVideoView.this.f();
                    d.a(MBridgeVideoView.this.a, MBridgeVideoView.this.b, MBridgeVideoView.this.Q, MBridgeVideoView.this.A, 1, 0, 1);
                }

                @Override // com.mbridge.msdk.widget.dialog.a
                public final void b() {
                    MBridgeVideoView.this.s = false;
                    MBridgeVideoView.this.W = true;
                    MBridgeVideoView.this.setShowingAlertViewCover(MBridgeVideoView.this.s);
                    d.a(MBridgeVideoView.this.a, MBridgeVideoView.this.b, MBridgeVideoView.this.Q, MBridgeVideoView.this.A, 1, 1, 1);
                    if (!MBridgeVideoView.this.N || MBridgeVideoView.this.R != com.mbridge.msdk.foundation.same.a.v) {
                        if (MBridgeVideoView.this.N && MBridgeVideoView.this.R == com.mbridge.msdk.foundation.same.a.w) {
                            MBridgeVideoView.this.f();
                            return;
                        } else {
                            MBridgeVideoView.this.e.a(2, "");
                            return;
                        }
                    }
                    MBridgeVideoView.this.e.a(2, MBridgeVideoView.this.b(MBridgeVideoView.this.ab));
                }
            };
        }
        if (this.y == null) {
            this.y = new MBAlertDialog(getContext(), this.z);
        }
        if (this.N) {
            this.y.makeIVAlertView(this.R, this.A);
        } else {
            this.y.makeRVAlertView(this.A);
        }
        if (this.n == null || this.n.isComplete()) {
            return;
        }
        this.y.show();
        this.U = true;
        this.s = true;
        setShowingAlertViewCover(this.s);
        com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.A, false);
        this.Q = c.a;
        d.a(this.a, this.b, this.Q, this.A, 1, 1);
    }

    public void preLoadData(com.mbridge.msdk.video.js.factory.b bVar) {
        if (this.f && !TextUtils.isEmpty(this.u) && this.b != null) {
            if (this.b != null && z.b(this.b.getVideoResolution())) {
                String videoResolution = this.b.getVideoResolution();
                s.b(TAG, "MBridgeBaseView videoResolution:" + videoResolution);
                String[] strArrSplit = videoResolution.split("x");
                if (strArrSplit.length == 2) {
                    if (w.b(strArrSplit[0]) > 0.0d) {
                        this.B = w.b(strArrSplit[0]);
                    }
                    if (w.b(strArrSplit[1]) > 0.0d) {
                        this.C = w.b(strArrSplit[1]);
                    }
                    s.b(TAG, "MBridgeBaseView mVideoW:" + this.B + "  mVideoH:" + this.C);
                }
                if (this.B <= 0.0d) {
                    this.B = 1280.0d;
                }
                if (this.C <= 0.0d) {
                    this.C = 720.0d;
                }
            }
            this.n.initBufferIngParam(this.w);
            this.n.initVFPData(this.u, this.b.getVideoUrlEncode(), this.af);
            soundOperate(this.P, -1, null);
        }
        O = false;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void defaultShow() {
        super.defaultShow();
        this.D = true;
        showVideoLocation(0, 0, w.h(this.a), w.g(this.a), 0, 0, 0, 0, 0);
        videoOperate(1);
        if (this.v == 0) {
            closeVideoOperate(-1, 2);
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void showVideoLocation(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        s.b(TAG, "showVideoLocation marginTop:" + i2 + " marginLeft:" + i3 + " width:" + i4 + "  height:" + i5 + " radius:" + i6 + " borderTop:" + i7 + " borderLeft:" + i8 + " borderWidth:" + i9 + " borderHeight:" + i10);
        if (this.f) {
            this.r.setPadding(0, 0, 0, 0);
            if (this.r.getVisibility() != 0) {
                this.r.setVisibility(0);
            }
            setVisibility(0);
            if ((i4 > 0 && i5 > 0 && w.h(this.a) >= i4 && w.g(this.a) >= i5) && !this.D) {
                j = i7;
                k = i8;
                l = i9 + 4;
                m = i10 + 4;
                float f = i4 / i5;
                float f2 = 0.0f;
                try {
                    f2 = (float) (this.B / this.C);
                } catch (Throwable th) {
                    s.b(TAG, th.getMessage(), th);
                }
                if (i6 > 0) {
                    i = i6;
                    if (i6 > 0) {
                        GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setCornerRadius(w.b(getContext(), i6));
                        gradientDrawable.setColor(-1);
                        gradientDrawable.setStroke(1, 0);
                        if (Build.VERSION.SDK_INT >= 16) {
                            setBackground(gradientDrawable);
                            this.n.setBackground(gradientDrawable);
                        } else {
                            setBackgroundDrawable(gradientDrawable);
                            this.n.setBackgroundDrawable(gradientDrawable);
                        }
                        if (Build.VERSION.SDK_INT >= 21) {
                            setClipToOutline(true);
                            this.n.setClipToOutline(true);
                        }
                    }
                }
                if (Math.abs(f - f2) <= 0.1f || this.M == 1) {
                    g();
                    if (this.L) {
                        setLayoutCenter(i4, i5);
                        if (O) {
                            this.e.a(114, "");
                            return;
                        } else {
                            this.e.a(116, "");
                            return;
                        }
                    }
                    setLayoutParam(i3, i2, i4, i5);
                    return;
                }
                g();
                videoOperate(1);
                return;
            }
            g();
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void soundOperate(int i2, int i3) {
        soundOperate(i2, i3, "2");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void soundOperate(int i2, int i3, String str) {
        if (this.f) {
            this.P = i2;
            if (i2 == 1) {
                this.o.setSoundStatus(false);
                this.n.closeSound();
            } else if (i2 == 2) {
                this.o.setSoundStatus(true);
                this.n.openSound();
            }
            if (i3 == 1) {
                this.o.setVisibility(8);
            } else if (i3 == 2) {
                this.o.setVisibility(0);
            }
        }
        if (str == null || !str.equals("2")) {
            return;
        }
        this.e.a(7, Integer.valueOf(i2));
    }

    @Override // com.mbridge.msdk.video.js.i
    public void videoOperate(int i2) {
        s.a(TAG, "VideoView videoOperate:" + i2);
        if (this.f) {
            if (i2 == 1) {
                if (getVisibility() == 0 && isfront()) {
                    s.a(TAG, "VideoView videoOperate:play");
                    if (this.s || this.G || this.t) {
                        return;
                    }
                    f();
                    return;
                }
                return;
            }
            if (i2 == 2) {
                if (getVisibility() == 0) {
                    s.a(TAG, "VideoView videoOperate:pause");
                    e();
                    return;
                }
                return;
            }
            if (i2 == 3) {
                if (this.F) {
                    return;
                }
                this.n.release();
                this.F = true;
                return;
            }
            if (i2 == 5) {
                this.t = true;
                if (this.F) {
                    return;
                }
                e();
                return;
            }
            if (i2 == 4) {
                this.t = false;
                if (this.F || isMiniCardShowing()) {
                    return;
                }
                f();
            }
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void closeVideoOperate(int i2, int i3) {
        if (i2 == 1) {
            this.ad = true;
            if (getVisibility() == 0) {
                a();
            }
        }
        if (i3 == 1) {
            gonePlayingCloseView();
            return;
        }
        if (i3 == 2) {
            if ((this.ac && getVisibility() == 0) || !this.f || this.q.getVisibility() == 0) {
                return;
            }
            this.q.setVisibility(0);
            this.H = true;
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void progressOperate(int i2, int i3) {
        if (this.f) {
            s.b(TAG, "progressOperate progress:" + i2);
            int videoLength = this.b != null ? this.b.getVideoLength() : 0;
            if (i2 > 0 && i2 <= videoLength && this.n != null) {
                s.b(TAG, "progressOperate progress:" + i2);
                this.n.seekTo(i2 * 1000);
            }
            if (i3 == 1) {
                this.p.setVisibility(8);
            } else if (i3 == 2) {
                this.p.setVisibility(0);
            }
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public String getCurrentProgress() {
        try {
            int iA = this.af.a();
            int videoLength = this.b != null ? this.b.getVideoLength() : 0;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("progress", a(iA, videoLength));
            jSONObject.put("time", iA);
            jSONObject.put("duration", videoLength + "");
            return jSONObject.toString();
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
            return "{}";
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setScaleFitXY(int i2) {
        this.M = i2;
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setVisible(int i2) {
        setVisibility(i2);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setInstallDialogState(boolean z) {
        this.t = z;
        this.n.setIsCovered(z);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setCover(boolean z) {
        if (this.f) {
            this.n.setIsCovered(z);
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setMiniEndCardState(boolean z) {
        this.G = z;
    }

    public void setShowingAlertViewCover(boolean z) {
        this.n.setIsCovered(z);
    }

    @Override // com.mbridge.msdk.video.js.i
    public boolean isH5Canvas() {
        return getLayoutParams().height < w.g(this.a.getApplicationContext());
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewHeight() {
        return m;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewWidth() {
        return l;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewLeft() {
        return k;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewTop() {
        return j;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewRadius() {
        return i;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.r.setVisibility(4);
        if (this.f && this.D) {
            g();
        }
    }

    private String a(int i2, int i3) {
        if (i3 != 0) {
            try {
                return w.a(Double.valueOf(i2 / i3)) + "";
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return i3 + "";
    }

    public int getCloseAlert() {
        return this.x;
    }

    public void setCloseAlert(int i2) {
        this.x = i2;
    }

    public int getVideoSkipTime() {
        return this.v;
    }

    public void setVideoSkipTime(int i2) {
        this.v = i2;
    }

    public void setPlayURL(String str) {
        this.u = str;
    }

    public void setBufferTimeout(int i2) {
        this.w = i2;
    }

    private boolean b() {
        try {
            this.n = (PlayerView) findViewById(findID("mbridge_vfpv"));
            this.o = (SoundImageView) findViewById(findID("mbridge_sound_switch"));
            this.p = (TextView) findViewById(findID("mbridge_tv_count"));
            this.q = findViewById(findID("mbridge_rl_playing_close"));
            this.q.setVisibility(4);
            this.r = (FrameLayout) findViewById(findID("mbridge_top_control"));
            return isNotNULL(this.n, this.o, this.p, this.q);
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
            return false;
        }
    }

    public boolean isfront() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup == null) {
            return false;
        }
        int iIndexOfChild = viewGroup.indexOfChild(this);
        int childCount = viewGroup.getChildCount();
        int i2 = iIndexOfChild + 1;
        boolean z = false;
        while (i2 <= childCount - 1) {
            if (viewGroup.getChildAt(i2).getVisibility() == 0 && this.G) {
                return false;
            }
            i2++;
            z = true;
        }
        return z;
    }

    private void e() {
        try {
            if (this.n != null) {
                this.n.setIsCovered(this.t || this.s);
                this.n.onPause();
            }
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (!this.E) {
                boolean zPlayVideo = this.n.playVideo();
                if (this.b != null && this.b.getPlayable_ads_without_video() != 2 && !zPlayVideo) {
                    s.d("MediaPlayer", "播放失败");
                    if (this.af != null) {
                        this.af.onPlayError("play video failed");
                    }
                }
                this.E = true;
                return;
            }
            if (this.t || this.s) {
                return;
            }
            this.n.setIsCovered(false);
            this.n.onResume();
        } catch (Exception e) {
            s.b(TAG, e.getMessage(), e);
        }
    }

    public void gonePlayingCloseView() {
        if (this.f && this.q.getVisibility() != 8) {
            this.q.setVisibility(8);
            this.H = false;
        }
        if (this.ag || this.K || this.I) {
            return;
        }
        this.ag = true;
        if (this.v < 0) {
            return;
        }
        if (this.v == 0) {
            this.K = true;
        } else {
            new Handler().postDelayed(new Runnable() { // from class: com.mbridge.msdk.video.module.MBridgeVideoView.5
                @Override // java.lang.Runnable
                public final void run() {
                    MBridgeVideoView.this.K = true;
                }
            }, this.v * 1000);
        }
    }

    private void g() {
        float fH = w.h(this.a);
        float fG = w.g(this.a);
        if (this.B <= 0.0d || this.C <= 0.0d || fH <= 0.0f || fG <= 0.0f) {
            h();
            return;
        }
        double d = this.B / this.C;
        double d2 = fH / fG;
        s.b(TAG, "videoWHDivide:" + d + "  screenWHDivide:" + d2);
        double dA = w.a(Double.valueOf(d));
        double dA2 = w.a(Double.valueOf(d2));
        s.b(TAG, "videoWHDivideFinal:" + dA + "  screenWHDivideFinal:" + dA2);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.n.getLayoutParams();
        if (dA > dA2) {
            double d3 = (fH * this.C) / this.B;
            layoutParams.width = -1;
            layoutParams.height = (int) d3;
            layoutParams.gravity = 17;
        } else if (dA < dA2) {
            layoutParams.width = (int) (fG * d);
            layoutParams.height = -1;
            layoutParams.gravity = 17;
        } else {
            layoutParams.width = -1;
            layoutParams.height = -1;
        }
        this.n.setLayoutParams(layoutParams);
        setMatchParent();
    }

    private void h() {
        try {
            setLayoutParam(0, 0, -1, -1);
            if (isLandscape() || !this.f) {
                return;
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.n.getLayoutParams();
            int iH = w.h(this.a);
            layoutParams.width = -1;
            layoutParams.height = (iH * 9) / 16;
            layoutParams.gravity = 17;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static final class b extends DefaultVideoPlayerStatusListener {
        private MBridgeVideoView a;
        private int b;
        private int c;
        private boolean d;
        private boolean i;
        private String j;
        private CampaignEx k;
        private int l;
        private int m;
        private a e = new a();
        private boolean f = false;
        private boolean g = false;
        private boolean h = false;
        private boolean n = false;

        public final void a(CampaignEx campaignEx) {
            this.k = campaignEx;
        }

        public final void a(boolean z) {
            this.i = z;
        }

        public final void a(String str) {
            this.j = str;
        }

        public final int a() {
            return this.b;
        }

        public final void a(int i, int i2) {
            this.l = i;
            this.m = i2;
        }

        public b(MBridgeVideoView mBridgeVideoView) {
            this.a = mBridgeVideoView;
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayStarted(int i) {
            super.onPlayStarted(i);
            if (!this.d) {
                this.a.e.a(10, this.e);
                this.d = true;
            }
            boolean unused = MBridgeVideoView.O = false;
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayCompleted() throws JSONException {
            super.onPlayCompleted();
            this.a.ab = true;
            this.a.p.setText(String.valueOf(PushConstants.PUSH_TYPE_NOTIFY));
            this.a.n.setClickable(false);
            String strB = this.a.b(true);
            this.a.e.a(121, "");
            this.a.e.a(11, strB);
            this.b = this.c;
            boolean unused = MBridgeVideoView.O = true;
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayError(String str) {
            s.d("DefaultVideoPlayerStatusListener", "errorStr" + str);
            super.onPlayError(str);
            this.a.e.a(12, str);
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlayProgress(int i, int i2) {
            int i3;
            String str;
            super.onPlayProgress(i, i2);
            if (this.a.f) {
                int i4 = i2 - i;
                if (i4 <= 0) {
                    i4 = 0;
                }
                this.a.p.setText(String.valueOf(i4));
            }
            this.c = i2;
            this.e.a = i;
            this.e.b = i2;
            this.e.c = this.a.ac;
            this.b = i;
            this.a.e.a(15, this.e);
            if (this.a.N && !this.a.U && this.a.R == com.mbridge.msdk.foundation.same.a.w) {
                this.a.a();
            }
            if (this.l == 100 || this.n || this.l == 0) {
                return;
            }
            if (this.m > this.l) {
                this.m = this.l / 2;
            }
            if (this.m < 0 || i < (i3 = (i2 * this.m) / 100)) {
                return;
            }
            if (this.k.getAdType() == 94 || this.k.getAdType() == 287) {
                str = this.k.getRequestId() + this.k.getId() + this.k.getVideoUrlEncode();
            } else {
                str = this.k.getId() + this.k.getVideoUrlEncode() + this.k.getBidToken();
            }
            com.mbridge.msdk.videocommon.download.a aVarA = com.mbridge.msdk.videocommon.download.b.getInstance().a(this.j, str);
            if (aVarA != null) {
                aVarA.j();
                this.n = true;
                s.d("DefaultVideoPlayerStatusListener", "CDRate is : " + i3 + " and start download !");
            }
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onBufferingStart(String str) {
            try {
                super.onBufferingStart(str);
                this.a.e.a(13, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onBufferingEnd() {
            try {
                super.onBufferingEnd();
                this.a.e.a(14, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.mbridge.msdk.playercommon.DefaultVideoPlayerStatusListener, com.mbridge.msdk.playercommon.VideoPlayerStatusListener
        public final void onPlaySetDataSourceError(String str) {
            super.onPlaySetDataSourceError(str);
        }

        public final void b() {
            this.a = null;
            boolean unused = MBridgeVideoView.O = false;
        }
    }

    public static class a {
        public int a;
        public int b;
        public boolean c;

        public final String toString() {
            return "ProgressData{curPlayPosition=" + this.a + ", allDuration=" + this.b + '}';
        }
    }

    public void onBackPress() {
        if (this.G || this.s || this.V) {
            return;
        }
        if (this.H) {
            a();
            return;
        }
        if (this.I && this.J) {
            a();
        } else {
            if (this.I || !this.K) {
                return;
            }
            a();
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void notifyCloseBtn(int i2) {
        if (i2 == 0) {
            this.I = true;
            this.K = false;
        } else if (i2 == 1) {
            this.J = true;
        }
    }

    @Override // com.mbridge.msdk.video.js.i
    public void showIVRewardAlertView(String str) {
        this.e.a(8, "");
    }

    public void notifyVideoClose() {
        this.e.a(2, "");
    }

    public int getMute() {
        return this.P;
    }

    public void setIVRewardEnable(int i2, int i3, int i4) {
        this.R = i2;
        this.S = i3;
        this.T = i4;
    }

    public void setDialogRole(int i2) {
        this.aa = i2 == 1;
        s.d(TAG, i2 + " " + this.aa);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(boolean z) throws JSONException {
        if (!this.N) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (!this.U) {
                jSONObject.put("Alert_window_status", com.mbridge.msdk.foundation.same.a.z);
            }
            if (this.W) {
                jSONObject.put("Alert_window_status", com.mbridge.msdk.foundation.same.a.B);
            }
            if (this.V) {
                jSONObject.put("Alert_window_status", com.mbridge.msdk.foundation.same.a.A);
            }
            jSONObject.put("complete_info", z ? 1 : 2);
            return jSONObject.toString();
        } catch (Exception unused) {
            s.d(TAG, "getIVRewardStatusString ERROR");
            return "";
        }
    }

    public void setNotchPadding(final int i2, final int i3, final int i4, final int i5) {
        s.d(TAG, "NOTCH VideoView " + String.format("%1s-%2s-%3s-%4s", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        if (Math.max(Math.max(layoutParams.leftMargin, layoutParams.rightMargin), Math.max(layoutParams.topMargin, layoutParams.bottomMargin)) > Math.max(Math.max(i2, i3), Math.max(i4, i5))) {
            return;
        }
        this.r.postDelayed(new Runnable() { // from class: com.mbridge.msdk.video.module.MBridgeVideoView.6
            @Override // java.lang.Runnable
            public final void run() {
                MBridgeVideoView.this.r.setPadding(i2, i4, i3, i5);
                MBridgeVideoView.this.r.startAnimation(MBridgeVideoView.this.ae);
                MBridgeVideoView.this.r.setVisibility(0);
            }
        }, 200L);
    }

    public void releasePlayer() {
        try {
            if (this.n != null && !this.F) {
                this.n.release();
            }
            if (this.af != null) {
                this.af.b();
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }
}
