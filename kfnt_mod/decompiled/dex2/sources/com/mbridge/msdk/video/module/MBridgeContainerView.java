package com.mbridge.msdk.video.module;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.i;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.video.js.e;
import com.mbridge.msdk.video.js.factory.b;
import com.mbridge.msdk.video.js.g;
import com.mbridge.msdk.video.module.a.a.f;
import com.mbridge.msdk.video.module.a.a.h;
import com.mbridge.msdk.video.module.a.a.k;
import com.mbridge.msdk.video.module.b.a;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeContainerView extends MBridgeBaseView implements e, g {
    private int A;
    private boolean B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private String H;
    private b I;
    private boolean J;
    private boolean K;
    private MBridgePlayableView i;
    private MBridgeClickCTAView j;
    private MBridgeClickMiniCardView k;
    private MBridgeNativeEndCardView l;
    private MBridgeH5EndCardView m;
    private MBridgeVastEndCardView n;
    private MBridgeLandingPageView o;
    private MBridgeAlertWebview p;
    private String q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public void setJSFactory(b bVar) {
        this.I = bVar;
    }

    public int getVideoSkipTime() {
        return this.A;
    }

    public void setVideoSkipTime(int i) {
        this.A = i;
    }

    public MBridgeContainerView(Context context) {
        super(context);
        this.s = 1;
        this.t = 1;
        this.u = 1;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = true;
        this.z = false;
        this.B = false;
        this.J = false;
        this.K = false;
    }

    public MBridgeContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = 1;
        this.t = 1;
        this.u = 1;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = true;
        this.z = false;
        this.B = false;
        this.J = false;
        this.K = false;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void init(Context context) {
        setVisibility(0);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        for (MBridgeBaseView mBridgeBaseView : new MBridgeBaseView[]{this.i, this.j, this.k, this.l, this.m, this.n, this.o}) {
            if (mBridgeBaseView != null && (mBridgeBaseView instanceof MBridgeClickMiniCardView)) {
                mBridgeBaseView.onSelfConfigurationChanged(configuration);
            } else if (mBridgeBaseView != null && mBridgeBaseView.getVisibility() == 0 && mBridgeBaseView.getParent() != null && !isLast()) {
                mBridgeBaseView.onSelfConfigurationChanged(configuration);
            }
        }
    }

    public void preLoadData(final b bVar) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        this.I = bVar;
        if (this.b != null) {
            if (this.b.getPlayable_ads_without_video() != 2) {
                b(this.r);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.mbridge.msdk.video.module.MBridgeContainerView.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        MBridgeContainerView.this.a(bVar, Integer.valueOf(MBridgeContainerView.this.b.getVideo_end_type()));
                    }
                }, getVideoSkipTime());
            } else {
                if (this.i == null) {
                    this.i = new MBridgePlayableView(this.a);
                }
                this.i.setCloseDelayShowTime(this.t);
                this.i.setPlayCloseBtnTm(this.u);
                this.i.setCampaign(this.b);
                this.i.setNotifyListener(new h(this.e) { // from class: com.mbridge.msdk.video.module.MBridgeContainerView.2
                    @Override // com.mbridge.msdk.video.module.a.a.h, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
                    public final void a(int i, Object obj) {
                        super.a(i, obj);
                        if (i == 100) {
                            MBridgeContainerView.this.webviewshow();
                            MBridgeContainerView.this.onConfigurationChanged(MBridgeContainerView.this.getResources().getConfiguration());
                            q qVar = new q();
                            qVar.k(MBridgeContainerView.this.b.getRequestId());
                            qVar.l(MBridgeContainerView.this.b.getRequestIdNotice());
                            qVar.n(MBridgeContainerView.this.b.getId());
                            qVar.a(MBridgeContainerView.this.b.isMraid() ? q.a : q.b);
                            com.mbridge.msdk.foundation.same.report.b.a(qVar, MBridgeContainerView.this.a, MBridgeContainerView.this.q);
                        }
                    }
                });
                this.i.preLoadData(bVar);
            }
            g();
        }
    }

    public void showPlayableView() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.b != null) {
            removeAllViews();
            setMatchParent();
            if (this.i == null) {
                preLoadData(this.I);
            }
            addView(this.i);
            if (this.i != null) {
                this.i.setUnitId(this.q);
                if (this.b != null && this.b.isMraid() && this.b.getPlayable_ads_without_video() == 2) {
                    this.i.setCloseVisible(0);
                }
                this.i.setNotchValue(this.H, this.C, this.D, this.E, this.F);
            }
            this.K = true;
            bringToFront();
        }
    }

    @Override // com.mbridge.msdk.video.js.e
    public boolean showAlertWebView() {
        if (this.p == null || !this.p.isLoadSuccess()) {
            return false;
        }
        setMatchParent();
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (!(viewGroup.indexOfChild(this) == viewGroup.getChildCount() - 1) && !this.K) {
            removeAllViews();
            bringToFront();
            this.J = true;
        }
        if (this.k != null && this.k.getParent() != null) {
            return false;
        }
        if (this.p == null) {
            g();
        }
        if (this.p != null && this.p.getParent() != null) {
            removeView(this.p);
        }
        addView(this.p);
        setBackgroundColor(0);
        this.p.webviewshow();
        return true;
    }

    @Override // com.mbridge.msdk.video.js.e
    public void showVideoClickView(int i) {
        if (this.b != null) {
            if (i == -1) {
                if (isLast() || endCardShowing()) {
                    return;
                }
                h();
                return;
            }
            switch (i) {
                case 1:
                    if (!this.v) {
                        if (this.m != null && this.m.getParent() != null) {
                            removeView(this.m);
                        }
                        if (this.k != null && this.k.getParent() != null) {
                            removeView(this.k);
                        }
                        if (this.j == null || this.j.getParent() == null) {
                            try {
                                if (this.b != null && this.b.getPlayable_ads_without_video() == 1) {
                                    this.K = true;
                                    if (this.j == null) {
                                        b(-1);
                                    }
                                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                                    layoutParams.addRule(12, -1);
                                    addView(this.j, 0, layoutParams);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (isLast()) {
                            bringToFront();
                            break;
                        }
                    }
                    break;
                case 2:
                    if (this.j != null && this.j.getParent() != null) {
                        removeView(this.j);
                    }
                    if (this.p == null || this.p.getParent() == null) {
                        if (this.k == null || this.k.getParent() == null) {
                            try {
                                if (this.b != null && this.b.getPlayable_ads_without_video() == 1) {
                                    setMatchParent();
                                    f();
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (miniCardLoaded()) {
                            if (this.m != null && this.m.getParent() != null) {
                                removeView(this.m);
                            }
                            this.e.a(112, "");
                            if (this.b != null && !this.b.isHasReportAdTrackPause()) {
                                this.b.setHasReportAdTrackPause(true);
                                a.f(this.a, this.b);
                            }
                            if (this.x) {
                                this.e.a(115, "");
                            } else {
                                this.K = true;
                                bringToFront();
                                webviewshow();
                                onConfigurationChanged(getResources().getConfiguration());
                            }
                            this.w = true;
                            break;
                        } else {
                            h();
                            break;
                        }
                    }
                    break;
            }
        }
    }

    @Override // com.mbridge.msdk.video.js.e
    public void showEndcard(int i) {
        if (this.b != null) {
            if (i == 1) {
                this.e.a(104, "");
            } else if (i != 100) {
                switch (i) {
                    case 3:
                        removeAllViews();
                        setMatchParent();
                        if (this.n == null) {
                            a(this.I, 3);
                        }
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                        layoutParams.addRule(13, -1);
                        addView(this.n, layoutParams);
                        this.n.notifyShowListener();
                        this.K = true;
                        bringToFront();
                        break;
                    case 4:
                        this.e.a(113, "");
                        removeAllViews();
                        setMatchParent();
                        if (this.o == null) {
                            a(this.I, 4);
                        }
                        this.o.preLoadData(this.I);
                        addView(this.o);
                        this.K = true;
                        bringToFront();
                        break;
                    case 5:
                        this.e.a(106, "");
                        break;
                    default:
                        removeAllViews();
                        setMatchParent();
                        this.K = true;
                        bringToFront();
                        a();
                        this.e.a(117, "");
                        break;
                }
            } else {
                a(this.i);
                setMatchParent();
                e();
                s.a(MBridgeBaseView.TAG, "showEndcard addNativeEndcard");
            }
        }
        this.v = true;
    }

    @Override // com.mbridge.msdk.video.js.e
    public boolean endCardShowing() {
        return this.v;
    }

    @Override // com.mbridge.msdk.video.js.e
    public boolean miniCardShowing() {
        return this.w;
    }

    public boolean miniCardLoaded() {
        return this.k != null && this.k.isLoadSuccess();
    }

    @Override // com.mbridge.msdk.video.js.g
    public void toggleCloseBtn(int i) {
        if (this.i != null) {
            this.i.toggleCloseBtn(i);
        }
        if (this.m != null) {
            this.m.toggleCloseBtn(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.g
    public void notifyCloseBtn(int i) {
        if (this.i != null) {
            this.i.notifyCloseBtn(i);
        }
        if (this.m != null) {
            this.m.notifyCloseBtn(i);
        }
    }

    public void triggerCloseBtn(String str) {
        if (this.b != null) {
            this.e.a(122, "");
            this.e.a(104, "");
        }
    }

    @Override // com.mbridge.msdk.video.js.e
    public void readyStatus(int i) {
        if (this.m != null) {
            this.m.readyStatus(i);
        }
    }

    public void handlerPlayableException(String str) {
        if (this.m != null) {
            this.m.handlerPlayableException(str);
            if (!this.B) {
                return;
            }
        }
        a();
    }

    @Override // com.mbridge.msdk.video.js.e
    public void showMiniCard(int i, int i2, int i3, int i4, int i5) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.k != null) {
            this.k.setMiniCardLocation(i, i2, i3, i4);
            this.k.setRadius(i5);
            this.k.setCloseVisible(8);
            this.k.setClickable(false);
            removeAllViews();
            setMatchParent();
            this.K = true;
            bringToFront();
            f();
            if (this.z) {
                return;
            }
            this.z = true;
            this.e.a(109, "");
            this.e.a(117, "");
        }
    }

    @Override // com.mbridge.msdk.video.js.e
    public void resizeMiniCard(int i, int i2, int i3) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.k != null) {
            this.k.resizeMiniCard(i, i2);
            this.k.setRadius(i3);
            removeAllViews();
            setMatchParent();
            this.K = true;
            bringToFront();
            f();
        }
    }

    @Override // com.mbridge.msdk.video.js.e
    public void configurationChanged(int i, int i2, int i3) {
        if (this.k == null || this.k.getVisibility() != 0) {
            return;
        }
        this.k.resizeMiniCard(i, i2);
    }

    public void webviewshow() {
        for (MBridgeH5EndCardView mBridgeH5EndCardView : new MBridgeH5EndCardView[]{this.i, this.k, this.m, this.p}) {
            if (mBridgeH5EndCardView != null && mBridgeH5EndCardView.getVisibility() == 0 && mBridgeH5EndCardView.getParent() != null && !isLast()) {
                mBridgeH5EndCardView.webviewshow();
            }
        }
    }

    public void install(CampaignEx campaignEx) {
        this.e.a(105, campaignEx);
    }

    public void orientation(Configuration configuration) throws JSONException {
        for (MBridgeH5EndCardView mBridgeH5EndCardView : new MBridgeH5EndCardView[]{this.i, this.k, this.m, this.p}) {
            if (mBridgeH5EndCardView != null && mBridgeH5EndCardView.getVisibility() == 0) {
                mBridgeH5EndCardView.orientation(getResources().getConfiguration());
            }
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void setNotifyListener(com.mbridge.msdk.video.module.a.a aVar) {
        super.setNotifyListener(aVar);
        for (MBridgeBaseView mBridgeBaseView : new MBridgeBaseView[]{this.i, this.j, this.k, this.l, this.m, this.n, this.o}) {
            if (mBridgeBaseView != null) {
                if (mBridgeBaseView instanceof MBridgeClickMiniCardView) {
                    mBridgeBaseView.setNotifyListener(new f(this.k, aVar));
                } else {
                    mBridgeBaseView.setNotifyListener(new h(aVar));
                }
            }
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void defaultShow() {
        super.defaultShow();
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (view != null) {
            a(view);
            super.addView(view);
        } else {
            s.d(MBridgeBaseView.TAG, "view is null");
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (view != null) {
            a(view);
            super.addView(view, layoutParams);
        } else {
            s.d(MBridgeBaseView.TAG, "view is null");
        }
    }

    public boolean canBackPress() {
        if (this.l != null) {
            return false;
        }
        if (this.m != null) {
            return this.m.canBackPress();
        }
        if (this.o != null) {
            return this.o.canBackPress();
        }
        if (this.i != null) {
            return this.i.canBackPress();
        }
        return false;
    }

    public boolean endcardIsPlayable() {
        return this.m != null && this.m.isPlayable();
    }

    public void setShowingTransparent(boolean z) {
        this.x = z;
    }

    public boolean getShowingTransparent() {
        return this.x;
    }

    public String getUnitID() {
        return this.q;
    }

    public void setUnitID(String str) {
        this.q = str;
    }

    public void setVideoInteractiveType(int i) {
        this.r = i;
    }

    public void setEndscreenType(int i) {
        this.s = i;
    }

    public int getVideoInteractiveType() {
        return this.r;
    }

    public void setCloseDelayTime(int i) {
        this.t = i;
    }

    public void setPlayCloseBtnTm(int i) {
        this.u = i;
    }

    private void a(View view) {
        if (view != null) {
            try {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(view);
                }
            } catch (Throwable th) {
                s.b(MBridgeBaseView.TAG, th.getMessage(), th);
            }
        }
    }

    private void a() {
        if (this.s == 2 && !this.B) {
            b();
        } else {
            e();
        }
    }

    private void b() {
        if (this.m == null) {
            a(this.I, 2);
        }
        if (this.m != null && this.m.isLoadSuccess()) {
            this.B = true;
            addView(this.m);
            webviewshow();
            onConfigurationChanged(getResources().getConfiguration());
            this.m.excuteTask();
            this.m.setNotchValue(this.H, this.C, this.D, this.E, this.F);
            q qVar = new q();
            qVar.k(this.b.getRequestId());
            qVar.l(this.b.getRequestIdNotice());
            qVar.n(this.b.getId());
            qVar.a(this.b.isMraid() ? q.a : q.b);
            com.mbridge.msdk.foundation.same.report.b.a(qVar, this.a, this.q);
        } else {
            e();
            if (this.m != null) {
                this.m.reportRenderResult("timeout", 3);
                this.m.setError(true);
            }
        }
        if (this.m != null) {
            this.m.setUnitId(this.q);
        }
    }

    private void e() {
        this.s = 1;
        if (this.l == null) {
            a(this.I, 2);
        }
        addView(this.l);
        onConfigurationChanged(getResources().getConfiguration());
        this.l.notifyShowListener();
        this.K = true;
        bringToFront();
    }

    private void f() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.k == null) {
            b(-2);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        if (this.x && this.y) {
            this.y = false;
            layoutParams.width = 1;
            layoutParams.height = 1;
        }
        addView(this.k, layoutParams);
    }

    private void g() {
        if (this.p == null) {
            this.p = new MBridgeAlertWebview(this.a);
            this.p.setUnitId(this.q);
            this.p.setCampaign(this.b);
        }
        this.p.preLoadData(this.I);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(b bVar, Integer num) {
        this.I = bVar;
        if (this.b != null) {
            if (num == null) {
                num = Integer.valueOf(this.b.getVideo_end_type());
            }
            if (!isLast()) {
                h();
            }
            int iIntValue = num.intValue();
            if (iIntValue != 1) {
                switch (iIntValue) {
                    case 3:
                        if (this.n == null) {
                            this.n = new MBridgeVastEndCardView(this.a);
                        }
                        this.n.setCampaign(this.b);
                        this.n.setNotifyListener(new k(this.e));
                        this.n.preLoadData(bVar);
                        break;
                    case 4:
                        if (this.o == null) {
                            this.o = new MBridgeLandingPageView(this.a);
                        }
                        this.o.setCampaign(this.b);
                        this.o.setNotifyListener(new h(this.e));
                        break;
                    case 5:
                        break;
                    default:
                        if (this.s == 2) {
                            if (this.m == null) {
                                this.m = new MBridgeH5EndCardView(this.a);
                            }
                            this.m.setCampaign(this.b);
                            this.m.setCloseDelayShowTime(this.t);
                            this.m.setNotifyListener(new h(this.e));
                            this.m.setUnitId(this.q);
                            this.m.setNotchValue(this.H, this.C, this.D, this.E, this.F);
                            this.m.preLoadData(bVar);
                            s.a(MBridgeBaseView.TAG, "preload H5Endcard");
                            if (!this.x) {
                                s.a(MBridgeBaseView.TAG, "showTransparent = " + this.x + " addview");
                                addView(this.m);
                                break;
                            }
                        } else {
                            if (this.l == null) {
                                this.l = new MBridgeNativeEndCardView(this.a);
                            }
                            this.l.setCampaign(this.b);
                            this.l.setUnitId(this.q);
                            this.l.setCloseBtnDelay(this.t);
                            this.l.setNotifyListener(new h(this.e));
                            this.l.preLoadData(bVar);
                            this.l.setNotchPadding(this.C, this.D, this.E, this.F);
                            break;
                        }
                        break;
                }
            }
        }
    }

    public boolean isLast() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        return viewGroup != null && viewGroup.indexOfChild(this) == 0;
    }

    private void h() {
        this.w = false;
        this.K = false;
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            int i = 0;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof MBridgeContainerView) {
                    i++;
                } else {
                    viewGroup.bringChildToFront(childAt);
                }
            }
        }
    }

    private void b(int i) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        switch (i) {
            case -3:
                break;
            case -2:
                if (this.b != null && this.b.getVideo_end_type() == 2) {
                    if (this.k == null) {
                        this.k = new MBridgeClickMiniCardView(this.a);
                    }
                    this.k.setCampaign(this.b);
                    this.k.setNotifyListener(new f(this.k, this.e));
                    this.k.preLoadData(this.I);
                    setMatchParent();
                    f();
                    h();
                    break;
                }
                break;
            default:
                if (this.j == null) {
                    this.j = new MBridgeClickCTAView(this.a);
                }
                this.j.setCampaign(this.b);
                this.j.setUnitId(this.q);
                this.j.setNotifyListener(new h(this.e));
                this.j.preLoadData(this.I);
                break;
        }
    }

    public void setMBridgeClickMiniCardViewTransparent() {
        if (this.k != null) {
            this.k.setMBridgeClickMiniCardViewTransparent();
            this.k.setMBridgeClickMiniCardViewClickable(false);
        }
    }

    public void onPlayableBackPress() {
        if (this.i != null) {
            this.i.onBackPress();
        }
    }

    public void onMiniEndcardBackPress() {
        if (this.w) {
            this.e.a(107, "");
        }
    }

    public void onEndcardBackPress() {
        if (this.l != null || this.n != null) {
            this.e.a(104, "");
        } else if (this.o != null) {
            this.e.a(103, "");
        } else if (this.m != null) {
            this.m.onBackPress();
        }
    }

    public void release() {
        if (this.m != null) {
            this.m.release();
            this.m = null;
        }
        if (this.i != null) {
            this.i.release();
        }
        if (this.o != null) {
            this.o.release();
        }
        if (this.e != null) {
            this.e = null;
        }
    }

    public MBridgeH5EndCardView getH5EndCardView() {
        if (this.m == null) {
            return this.i;
        }
        return this.m;
    }

    @Override // com.mbridge.msdk.video.js.e
    public void hideAlertWebview() {
        if (isLast()) {
            return;
        }
        if (this.J && !this.K) {
            h();
            this.J = false;
        }
        if (this.p == null || this.p.getParent() == null) {
            return;
        }
        removeView(this.p);
        if (this.j == null || this.j.getParent() == null) {
            return;
        }
        setWrapContent();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).addRule(12, -1);
        }
    }

    @Override // com.mbridge.msdk.video.js.e
    public void ivRewardAdsWithoutVideo(String str) {
        this.e.a(103, str);
    }

    public void setNotchPadding(int i, int i2, int i3, int i4, int i5) {
        s.d(MBridgeBaseView.TAG, "NOTCH ContainerView " + String.format("%1s-%2s-%3s-%4s-%5s", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i)));
        this.G = i;
        this.C = i2;
        this.D = i3;
        this.E = i4;
        this.F = i5;
        this.H = i.a(i, i2, i3, i4, i5);
        if (this.l != null) {
            this.l.setNotchPadding(i2, i3, i4, i5);
        }
        if (this.m != null && this.m.l != null) {
            this.m.setNotchValue(this.H, i2, i3, i4, i5);
            com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.m.l, "oncutoutfetched", Base64.encodeToString(this.H.getBytes(), 0));
        }
        if (this.i == null || this.i.l == null) {
            return;
        }
        this.i.setNotchValue(this.H, i2, i3, i4, i5);
        com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.i.l, "oncutoutfetched", Base64.encodeToString(this.H.getBytes(), 0));
    }
}
