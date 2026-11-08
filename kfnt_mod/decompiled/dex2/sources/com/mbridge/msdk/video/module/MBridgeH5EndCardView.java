package com.mbridge.msdk.video.module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.video.js.g;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeH5EndCardView extends MBridgeBaseView implements com.mbridge.msdk.mbjscommon.mraid.b, g {
    private long A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private String J;
    private com.mbridge.msdk.video.js.factory.b K;
    private boolean L;
    private boolean M;
    protected View i;
    protected RelativeLayout j;
    protected ImageView k;
    protected WindVaneWebView l;
    protected Handler m;
    protected String n;
    protected boolean o;
    protected boolean p;
    protected String q;
    Handler r;
    boolean s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private int z;

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void expand(String str, boolean z) {
    }

    public void install(CampaignEx campaignEx) {
    }

    public void readyStatus(int i) {
    }

    public MBridgeH5EndCardView(Context context) {
        super(context);
        this.t = false;
        this.m = new Handler();
        this.o = false;
        this.p = false;
        this.u = false;
        this.v = 1;
        this.w = 1;
        this.x = false;
        this.y = false;
        this.z = 1;
        this.A = 0L;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = "";
        this.r = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.video.module.MBridgeH5EndCardView.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 100) {
                    return;
                }
                if (MBridgeH5EndCardView.this.B) {
                    MBridgeH5EndCardView.this.e.a(122, "");
                }
                MBridgeH5EndCardView.this.e.a(103, "");
            }
        };
        this.L = false;
        this.M = false;
        this.s = false;
    }

    public MBridgeH5EndCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = false;
        this.m = new Handler();
        this.o = false;
        this.p = false;
        this.u = false;
        this.v = 1;
        this.w = 1;
        this.x = false;
        this.y = false;
        this.z = 1;
        this.A = 0L;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = "";
        this.r = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.video.module.MBridgeH5EndCardView.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 100) {
                    return;
                }
                if (MBridgeH5EndCardView.this.B) {
                    MBridgeH5EndCardView.this.e.a(122, "");
                }
                MBridgeH5EndCardView.this.e.a(103, "");
            }
        };
        this.L = false;
        this.M = false;
        this.s = false;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void onSelfConfigurationChanged(Configuration configuration) throws JSONException {
        super.onSelfConfigurationChanged(configuration);
        orientation(configuration);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void init(Context context) {
        int iFindLayout = findLayout("mbridge_reward_endcard_h5");
        if (iFindLayout >= 0) {
            this.i = this.c.inflate(iFindLayout, (ViewGroup) null);
            View view = this.i;
            this.k = (ImageView) view.findViewById(findID("mbridge_windwv_close"));
            this.j = (RelativeLayout) view.findViewById(findID("mbridge_windwv_content_rl"));
            this.l = new WindVaneWebView(getContext());
            this.l.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.j.addView(this.l);
            this.f = isNotNULL(this.k, this.l);
            addView(this.i, b());
            c();
            e();
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    protected final void c() {
        super.c();
        if (this.f) {
            this.k.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.module.MBridgeH5EndCardView.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MBridgeH5EndCardView.this.onCloseViewClick();
                }
            });
        }
    }

    public void onCloseViewClick() {
        try {
            if (this.l != null) {
                h.a().a((WebView) this.l, "onSystemDestory", "");
                new Thread(new a(this)).start();
            } else {
                this.e.a(103, "");
                this.e.a(119, "webview is null when closing webview");
            }
        } catch (Exception e2) {
            this.e.a(103, "");
            this.e.a(119, "close webview exception" + e2.getMessage());
            s.a(MBridgeBaseView.TAG, e2.getMessage());
        }
    }

    private class a implements Runnable {
        private MBridgeH5EndCardView b;

        public a(MBridgeH5EndCardView mBridgeH5EndCardView) {
            this.b = mBridgeH5EndCardView;
        }

        @Override // java.lang.Runnable
        public final void run() throws InterruptedException {
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.b == null || this.b.r == null) {
                return;
            }
            this.b.r.sendEmptyMessage(100);
        }
    }

    public void setError(boolean z) {
        this.p = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x011e A[Catch: Throwable -> 0x0143, TryCatch #1 {Throwable -> 0x0143, blocks: (B:15:0x006d, B:17:0x008f, B:19:0x0095, B:22:0x009f, B:24:0x00a5, B:26:0x00b6, B:28:0x00be, B:30:0x00c1, B:32:0x00c5, B:34:0x00cd, B:36:0x00d5, B:38:0x00dd, B:40:0x00e6, B:48:0x011e, B:49:0x0138, B:41:0x010a, B:43:0x010f, B:45:0x0115), top: B:63:0x006d }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0138 A[Catch: Throwable -> 0x0143, TRY_LEAVE, TryCatch #1 {Throwable -> 0x0143, blocks: (B:15:0x006d, B:17:0x008f, B:19:0x0095, B:22:0x009f, B:24:0x00a5, B:26:0x00b6, B:28:0x00be, B:30:0x00c1, B:32:0x00c5, B:34:0x00cd, B:36:0x00d5, B:38:0x00dd, B:40:0x00e6, B:48:0x011e, B:49:0x0138, B:41:0x010a, B:43:0x010f, B:45:0x0115), top: B:63:0x006d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void preLoadData(com.mbridge.msdk.video.js.factory.b r8) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.video.module.MBridgeH5EndCardView.preLoadData(com.mbridge.msdk.video.js.factory.b):void");
    }

    public void reportRenderResult(String str, int i) {
        if (this.b == null || this.p) {
            return;
        }
        q qVar = new q();
        qVar.k(this.b.getRequestId());
        qVar.l(this.b.getRequestIdNotice());
        qVar.n(this.b.getId());
        qVar.c(i);
        qVar.q(String.valueOf(System.currentTimeMillis() - this.A));
        qVar.p(str);
        if (this.b.getAdType() == 287) {
            qVar.h(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
        } else if (this.b.getAdType() == 94) {
            qVar.h("1");
        } else if (this.b.getAdType() == 42) {
            qVar.h("2");
        }
        if (this.b.isMraid()) {
            qVar.a(q.a);
        } else {
            qVar.f(this.b.getendcard_url());
            String str2 = "2";
            if (z.b(this.b.getendcard_url()) && this.b.getendcard_url().contains(".zip")) {
                str2 = "1";
            }
            qVar.g(str2);
            qVar.a(q.b);
        }
        com.mbridge.msdk.foundation.same.report.b.b(qVar, this.q);
    }

    public void startCounterEndCardShowTimer() {
        try {
            String str = this.b.getendcard_url();
            int iA = 15;
            if (z.b(str) && str.contains("wfl=1")) {
                String[] strArrSplit = str.split(ContainerUtils.FIELD_DELIMITER);
                if (strArrSplit != null && strArrSplit.length > 0) {
                    for (String str2 : strArrSplit) {
                        if (z.b(str2) && str2.contains("timeout") && str2.split(ContainerUtils.KEY_VALUE_DELIMITER) != null && str2.split(ContainerUtils.KEY_VALUE_DELIMITER).length > 0) {
                            iA = w.a((Object) str2.split(ContainerUtils.KEY_VALUE_DELIMITER)[1]);
                            s.b(MBridgeBaseView.TAG, "从url获取的wfl timeout :" + iA);
                        }
                    }
                }
                executeEndCardShow(iA);
            }
        } catch (Throwable th) {
            s.a(MBridgeBaseView.TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void defaultShow() {
        super.defaultShow();
    }

    @Override // com.mbridge.msdk.video.js.g
    public void notifyCloseBtn(int i) {
        switch (i) {
            case 0:
                this.D = true;
                break;
            case 1:
                this.E = true;
                break;
        }
    }

    @Override // com.mbridge.msdk.video.js.g
    public void toggleCloseBtn(int i) {
        int visibility = this.k.getVisibility();
        switch (i) {
            case 1:
                this.C = true;
                visibility = 0;
                break;
            case 2:
                this.C = false;
                visibility = 8;
                if (this.s) {
                    if (!this.M && !this.D) {
                        this.M = true;
                        if (this.w == 0) {
                            this.G = true;
                            break;
                        } else {
                            this.G = false;
                            if (this.w > -1) {
                                this.m.postDelayed(new e(this), this.w * 1000);
                                break;
                            }
                        }
                    }
                } else if (!this.L && !this.D) {
                    this.L = true;
                    if (this.v == 0) {
                        this.F = true;
                        break;
                    } else {
                        this.F = false;
                        if (this.v > -1) {
                            this.m.postDelayed(new d(this), this.v * 1000);
                            break;
                        }
                    }
                }
                break;
        }
        setCloseVisible(visibility);
    }

    private class d implements Runnable {
        private MBridgeH5EndCardView b;

        public d(MBridgeH5EndCardView mBridgeH5EndCardView) {
            this.b = mBridgeH5EndCardView;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b != null) {
                this.b.F = true;
            }
        }
    }

    private class e implements Runnable {
        private MBridgeH5EndCardView b;

        public e(MBridgeH5EndCardView mBridgeH5EndCardView) {
            this.b = mBridgeH5EndCardView;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b != null) {
                this.b.G = true;
            }
        }
    }

    public void handlerPlayableException(String str) {
        s.d("========", "===========handlerPlayableException");
        if (this.p) {
            return;
        }
        this.p = true;
        this.o = false;
        if (this.b != null) {
            q qVar = new q();
            qVar.k(this.b.getRequestId());
            qVar.l(this.b.getRequestIdNotice());
            qVar.n(this.b.getId());
            qVar.p(str);
            com.mbridge.msdk.foundation.same.report.b.d(qVar, this.a.getApplicationContext(), this.q);
        }
    }

    public void webviewshow() {
        if (this.l != null) {
            this.l.post(new Runnable() { // from class: com.mbridge.msdk.video.module.MBridgeH5EndCardView.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        s.a(MBridgeBaseView.TAG, "webviewshow");
                        String string = "";
                        try {
                            int[] iArr = new int[2];
                            MBridgeH5EndCardView.this.l.getLocationOnScreen(iArr);
                            s.d(MBridgeBaseView.TAG, "coordinate:" + iArr[0] + "--" + iArr[1]);
                            JSONObject jSONObject = new JSONObject();
                            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                            if (contextJ != null) {
                                jSONObject.put("startX", w.a(contextJ, iArr[0]));
                                jSONObject.put("startY", w.a(contextJ, iArr[1]));
                                jSONObject.put(com.mbridge.msdk.foundation.same.a.r, w.d(contextJ));
                            }
                            string = jSONObject.toString();
                        } catch (Throwable th) {
                            s.b(MBridgeBaseView.TAG, th.getMessage(), th);
                        }
                        h.a().a((WebView) MBridgeH5EndCardView.this.l, "webviewshow", Base64.encodeToString(string.toString().getBytes(), 2));
                        MBridgeH5EndCardView.this.e.a(109, "");
                        MBridgeH5EndCardView.d(MBridgeH5EndCardView.this);
                        MBridgeH5EndCardView.this.startCounterEndCardShowTimer();
                        h.a().a((WebView) MBridgeH5EndCardView.this.l, "oncutoutfetched", Base64.encodeToString(MBridgeH5EndCardView.this.J.getBytes(), 0));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public void orientation(Configuration configuration) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            if (configuration.orientation == 2) {
                jSONObject.put("orientation", "landscape");
            } else {
                jSONObject.put("orientation", "portrait");
            }
            h.a().a((WebView) this.l, "orientation", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean canBackPress() {
        return this.k != null && this.k.getVisibility() == 0;
    }

    public boolean isLoadSuccess() {
        return this.o;
    }

    public void setUnitId(String str) {
        this.q = str;
    }

    public void setCloseDelayShowTime(int i) {
        this.v = i;
    }

    public void setPlayCloseBtnTm(int i) {
        this.w = i;
    }

    public void setHtmlSource(String str) {
        this.n = str;
    }

    public void setCloseVisible(int i) {
        if (this.f) {
            this.k.setVisibility(i);
        }
    }

    public void setCloseVisibleForMraid(int i) {
        if (this.f) {
            this.H = true;
            if (i == 4) {
                this.k.setImageDrawable(new ColorDrawable(16711680));
            } else {
                this.k.setImageResource(findDrawable("mbridge_reward_close"));
            }
            this.k.setVisibility(0);
        }
    }

    protected RelativeLayout.LayoutParams b() {
        return new RelativeLayout.LayoutParams(-1, -1);
    }

    protected void e() {
        if (this.f) {
            setMatchParent();
        }
    }

    protected String a() {
        String endScreenUrl;
        if (this.b != null) {
            this.B = true;
            if (this.b.isMraid()) {
                this.u = false;
                String mraid = this.b.getMraid();
                if (!TextUtils.isEmpty(mraid)) {
                    File file = new File(mraid);
                    try {
                        if (file.exists() && file.isFile() && file.canRead()) {
                            s.b(MBridgeBaseView.TAG, "Mraid file " + mraid);
                            endScreenUrl = "file:////" + mraid;
                        } else {
                            s.b(MBridgeBaseView.TAG, "Mraid file not found. Will use endcard url.");
                            endScreenUrl = this.b.getEndScreenUrl();
                        }
                        return endScreenUrl;
                    } catch (Throwable th) {
                        if (!MBridgeConstans.DEBUG) {
                            return mraid;
                        }
                        th.printStackTrace();
                        return mraid;
                    }
                }
                String endScreenUrl2 = this.b.getEndScreenUrl();
                s.d(MBridgeBaseView.TAG, "getURL playable=false endscreenurl兜底:" + endScreenUrl2);
                return endScreenUrl2;
            }
            String str = this.b.getendcard_url();
            if (!z.a(str)) {
                this.u = true;
                String h5ResAddress = H5DownLoadManager.getInstance().getH5ResAddress(str);
                if (TextUtils.isEmpty(h5ResAddress)) {
                    try {
                        String path = Uri.parse(str).getPath();
                        if (!TextUtils.isEmpty(path) && path.toLowerCase().endsWith(".zip")) {
                            String endScreenUrl3 = this.b.getEndScreenUrl();
                            if (TextUtils.isEmpty(endScreenUrl3)) {
                                return null;
                            }
                            this.u = false;
                            excuteTask();
                            return endScreenUrl3;
                        }
                    } catch (Throwable th2) {
                        s.d(MBridgeBaseView.TAG, th2.getMessage());
                    }
                    s.b(MBridgeBaseView.TAG, "getURL playable=true endcard本地资源地址为空拿服务端地址:" + str);
                    return str + "&native_adtype=" + this.b.getAdType();
                }
                s.b(MBridgeBaseView.TAG, "getURL playable=true 资源不为空endcard地址:" + h5ResAddress);
                return h5ResAddress + "&native_adtype=" + this.b.getAdType();
            }
            this.u = false;
            String endScreenUrl4 = this.b.getEndScreenUrl();
            s.d(MBridgeBaseView.TAG, "getURL playable=false endscreenurl兜底:" + endScreenUrl4);
            return endScreenUrl4;
        }
        this.B = false;
        s.d(MBridgeBaseView.TAG, "getURL playable=false url为空");
        return null;
    }

    private class f implements Runnable {
        private MBridgeH5EndCardView b;

        public f(MBridgeH5EndCardView mBridgeH5EndCardView) {
            this.b = mBridgeH5EndCardView;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b != null) {
                if (!this.b.H) {
                    MBridgeH5EndCardView.this.setCloseVisible(0);
                }
                this.b.C = true;
            }
        }
    }

    public void excuteTask() {
        if (this.u || this.v <= -1) {
            return;
        }
        this.m.postDelayed(new f(this), this.v * 1000);
    }

    private class b implements Runnable {
        private MBridgeH5EndCardView b;

        public b(MBridgeH5EndCardView mBridgeH5EndCardView) {
            this.b = mBridgeH5EndCardView;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.b == null || this.b.y) {
                return;
            }
            this.b.y = true;
            this.b.o = false;
            MBridgeH5EndCardView.this.reportRenderResult("timeout", 5);
            this.b.e.a(127, "");
            s.a(MBridgeBaseView.TAG, "notify TYPE_NOTIFY_SHOW_NATIVE_ENDCARD");
        }
    }

    public void executeEndCardShow(int i) {
        this.m.postDelayed(new b(this), i * 1000);
    }

    public void excuteEndCardShowTask(int i) {
        this.m.postDelayed(new c(this, i), i * 1000);
    }

    private static class c implements Runnable {
        private MBridgeH5EndCardView a;
        private int b;

        public c(MBridgeH5EndCardView mBridgeH5EndCardView, int i) {
            this.a = mBridgeH5EndCardView;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.a == null || this.a.b == null) {
                return;
            }
            try {
                if (!this.a.x) {
                    this.a.x = true;
                    String str = "2";
                    if (z.b(this.a.b.getendcard_url()) && this.a.b.getendcard_url().contains(".zip")) {
                        str = "1";
                    }
                    q qVar = new q("2000043", 12, (this.b * 1000) + "", this.a.b.getendcard_url(), this.a.b.getId(), this.a.q, "ready timeout", str);
                    try {
                        if (this.a.b.getAdType() == 287) {
                            qVar.h(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                        } else if (this.a.b.getAdType() == 94) {
                            qVar.h("1");
                        } else if (this.a.b.getAdType() == 42) {
                            qVar.h("2");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    qVar.k(this.a.b.getRequestId());
                    qVar.l(this.a.b.getRequestIdNotice());
                    com.mbridge.msdk.foundation.same.report.b.a(qVar, this.a.q);
                    this.a.isLoadSuccess();
                    return;
                }
                s.b(MBridgeBaseView.TAG, "insertEndCardReadyState hasInsertLoadEndCardReport true return");
            } catch (Throwable th) {
                s.b(MBridgeBaseView.TAG, th.getMessage(), th);
            }
        }
    }

    public boolean isPlayable() {
        return this.u;
    }

    public void onBackPress() {
        if (this.C || ((this.D && this.E) || (!(this.D || !this.F || this.s) || (!this.D && this.G && this.s)))) {
            onCloseViewClick();
        }
    }

    public void setLoadPlayable(boolean z) {
        this.s = z;
    }

    public void release() {
        if (this.m != null) {
            this.m.removeCallbacksAndMessages(null);
            this.m = null;
        }
        if (this.r != null) {
            this.r.removeCallbacksAndMessages(null);
            this.r = null;
        }
        this.j.removeAllViews();
        this.l.release();
        this.l = null;
    }

    public void volumeChange(double d2) {
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(this.l, d2);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.b == null || !this.b.isMraid()) {
            return;
        }
        if (z) {
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(this.l, "true");
        } else {
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(this.l, "false");
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0 || this.I) {
            return;
        }
        this.I = true;
        setFocusableInTouchMode(true);
        requestFocus();
        requestFocusFromTouch();
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void open(final String str) {
        s.d("EndCard_MRAID", "open : " + str);
        try {
            if (this.b != null && this.b.needShowIDialog() && com.mbridge.msdk.click.c.a(this.b)) {
                com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.video.module.MBridgeH5EndCardView.5
                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void b() {
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void a() {
                        MBridgeH5EndCardView.this.a(str);
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void c() {
                        a();
                    }
                };
                if (getContext() == null || this.b == null || new com.mbridge.msdk.click.b(getContext(), this.q).b(this.b)) {
                    return;
                }
                com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.q, false);
                com.mbridge.msdk.mbjscommon.confirmation.e.a().a(com.mbridge.msdk.videocommon.d.c.a, this.b, getContext(), this.q, aVar);
                return;
            }
            a(str);
        } catch (Exception e2) {
            s.d(MBridgeBaseView.TAG, e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            String clickURL = this.b.getClickURL();
            if (!TextUtils.isEmpty(str)) {
                this.b.setClickURL(str);
                b(str);
            }
            new com.mbridge.msdk.click.b(getContext(), this.q).c(this.b);
            this.b.setClickURL(clickURL);
            this.e.a(126, "");
        } catch (Exception e2) {
            s.d(MBridgeBaseView.TAG, e2.getMessage());
        }
    }

    private void b(String str) {
        CampaignEx mraidCampaign = getMraidCampaign();
        if (mraidCampaign != null) {
            new com.mbridge.msdk.foundation.same.report.c(getContext()).b(mraidCampaign.getRequestId(), mraidCampaign.getRequestIdNotice(), mraidCampaign.getId(), this.q, str, this.b.isBidCampaign());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void close() {
        s.d("EndCard_MRAID", CampaignEx.JSON_NATIVE_VIDEO_CLOSE);
        try {
            onCloseViewClick();
        } catch (Exception e2) {
            s.d(MBridgeBaseView.TAG, e2.getMessage());
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void unload() {
        s.d("EndCard_MRAID", "unload");
        close();
    }

    public CampaignEx getMraidCampaign() {
        return this.b;
    }

    @Override // com.mbridge.msdk.mbjscommon.mraid.b
    public void useCustomClose(boolean z) {
        s.d("EndCard_MRAID", "useCustomClose : " + z);
        try {
            setCloseVisibleForMraid(z ? 4 : 0);
        } catch (Exception e2) {
            s.d(MBridgeBaseView.TAG, e2.getMessage());
        }
    }

    public void setNotchValue(String str, int i, int i2, int i3, int i4) {
        this.J = str;
        s.d(MBridgeBaseView.TAG, "NOTCH H5ENDCARD " + String.format("%1s-%2s-%3s-%4s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
        int iB = w.b(getContext(), 20.0f);
        int i5 = i3 + iB;
        layoutParams.setMargins(i + iB, i5, i2 + iB, i4 + iB);
        s.d(MBridgeBaseView.TAG, "NOTCH H5ENDCARD " + i5);
        this.k.setLayoutParams(layoutParams);
    }

    static /* synthetic */ void a(MBridgeH5EndCardView mBridgeH5EndCardView, long j, boolean z) {
        String str;
        int i;
        int i2;
        try {
            if (mBridgeH5EndCardView.x) {
                s.b(MBridgeBaseView.TAG, "insertEndCardReadyState hasInsertLoadEndCardReport true return");
                return;
            }
            mBridgeH5EndCardView.x = true;
            String str2 = "2";
            if (z.b(mBridgeH5EndCardView.b.getendcard_url()) && mBridgeH5EndCardView.b.getendcard_url().contains(".zip")) {
                str2 = "1";
            }
            String str3 = str2;
            if (z) {
                str = "ready timeout";
                i = 2;
                i2 = 12;
            } else if (mBridgeH5EndCardView.z == 2) {
                str = "ready no";
                i = 3;
                i2 = 11;
            } else {
                str = "ready yes";
                i = 1;
                i2 = 10;
            }
            int i3 = i;
            String str4 = str;
            q qVar = new q("2000043", i2, j + "", mBridgeH5EndCardView.b.getendcard_url(), mBridgeH5EndCardView.b.getId(), mBridgeH5EndCardView.q, str, str3);
            try {
                if (mBridgeH5EndCardView.b.getAdType() == 287) {
                    qVar.h(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                } else if (mBridgeH5EndCardView.b.getAdType() == 94) {
                    qVar.h("1");
                } else if (mBridgeH5EndCardView.b.getAdType() == 42) {
                    qVar.h("2");
                }
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
            qVar.k(mBridgeH5EndCardView.b.getRequestId());
            qVar.l(mBridgeH5EndCardView.b.getRequestIdNotice());
            com.mbridge.msdk.foundation.same.report.b.a(qVar, mBridgeH5EndCardView.q);
            if (!mBridgeH5EndCardView.isLoadSuccess() && i3 == 1) {
                qVar.c(i3);
                qVar.q(String.valueOf(j));
                qVar.n(mBridgeH5EndCardView.b.getId());
                qVar.p(str4);
                if (mBridgeH5EndCardView.b.isMraid()) {
                    qVar.a(q.a);
                } else {
                    qVar.f(mBridgeH5EndCardView.b.getendcard_url());
                    qVar.g(str3);
                    qVar.a(q.b);
                }
                com.mbridge.msdk.foundation.same.report.b.b(qVar, mBridgeH5EndCardView.q);
            }
            s.b(MBridgeBaseView.TAG, "insertEndCardReadyState result:" + i2 + " endCardLoadTime:" + j + " endcardurl:" + mBridgeH5EndCardView.b.getendcard_url() + "  id:" + mBridgeH5EndCardView.b.getId() + "  unitid:" + mBridgeH5EndCardView.q + "  reason:" + str4 + "  type:" + str3);
        } catch (Throwable th) {
            s.b(MBridgeBaseView.TAG, th.getMessage(), th);
        }
    }

    static /* synthetic */ void d(MBridgeH5EndCardView mBridgeH5EndCardView) throws JSONException {
        if (mBridgeH5EndCardView.b == null || !mBridgeH5EndCardView.b.isMraid()) {
            return;
        }
        String str = "undefined";
        switch (mBridgeH5EndCardView.getResources().getConfiguration().orientation) {
            case 0:
                str = "undefined";
                break;
            case 1:
                str = "portrait";
                break;
            case 2:
                str = "landscape";
                break;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("orientation", str);
            jSONObject.put("locked", "true");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        HashMap map = new HashMap();
        map.put("placementType", "Interstitial");
        map.put("state", "default");
        map.put("viewable", "true");
        map.put("currentAppOrientation", jSONObject);
        if (mBridgeH5EndCardView.getContext() instanceof Activity) {
            float fW = o.w(mBridgeH5EndCardView.getContext());
            float fX = o.x(mBridgeH5EndCardView.getContext());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) mBridgeH5EndCardView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f2 = displayMetrics.widthPixels;
            float f3 = displayMetrics.heightPixels;
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(mBridgeH5EndCardView.l, fW, fX);
            com.mbridge.msdk.mbjscommon.mraid.a.a().c(mBridgeH5EndCardView.l, f2, f3);
        }
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBridgeH5EndCardView.l, mBridgeH5EndCardView.l.getLeft(), mBridgeH5EndCardView.l.getTop(), mBridgeH5EndCardView.l.getWidth(), mBridgeH5EndCardView.l.getHeight());
        com.mbridge.msdk.mbjscommon.mraid.a.a().b(mBridgeH5EndCardView.l, mBridgeH5EndCardView.l.getLeft(), mBridgeH5EndCardView.l.getTop(), mBridgeH5EndCardView.l.getWidth(), mBridgeH5EndCardView.l.getHeight());
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBridgeH5EndCardView.l, map);
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBridgeH5EndCardView.l, com.mbridge.msdk.mbjscommon.mraid.d.a);
        com.mbridge.msdk.mbjscommon.mraid.a.a().a(mBridgeH5EndCardView.l);
    }
}
