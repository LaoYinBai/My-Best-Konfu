package com.sigmob.sdk.nativead;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.ClickCommon;
import com.sigmob.sdk.base.models.SigImage;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.base.views.e;
import com.sigmob.sdk.common.utils.g;
import com.sigmob.sdk.nativead.SigEmptyView;
import com.sigmob.sdk.nativead.c;
import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class q implements View.OnClickListener, View.OnTouchListener, SigEmptyView.a, c.a, r {
    private static Map<Integer, Integer> d = new HashMap();
    private boolean A;
    private List<View> B;
    private long C;
    protected s a;
    protected BaseAdUnit b;
    protected g c;
    private Context e;
    private SigNativeAdVideo f;
    private SigEmptyView g;
    private List<View> h;
    private List<View> i;
    private c j;
    private SigAppInfoView k;
    private MotionEvent l;
    private b m;
    private n n;
    private boolean o;
    private ViewGroup p;
    private ViewGroup.LayoutParams q;
    private Bitmap r;
    private com.sigmob.sdk.base.views.e s;
    private boolean t;
    private k u;
    private boolean v;
    private boolean w;
    private boolean x = true;
    private boolean y = false;
    private boolean z;

    private void E() {
        try {
            if (this.s == null && F() != null) {
                this.s = new com.sigmob.sdk.base.views.e(F().getContext(), this.b);
                this.s.a(new e.b() { // from class: com.sigmob.sdk.nativead.q.5
                    @Override // com.sigmob.sdk.base.views.e.b
                    public void a() {
                        if (q.this.s != null) {
                            q.this.s.dismiss();
                            q.this.s.c();
                            q.this.s = null;
                            q.this.t = false;
                        }
                        q.this.b.getClickCommon().click_scene = "preview";
                        if (q.this.f != null) {
                            q.this.q().c();
                        }
                        q.this.j().a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_CLOSE, 0);
                    }

                    @Override // com.sigmob.sdk.base.views.e.b
                    public void a(String str, String str2) {
                        q.this.b.getClickCommon().click_area = ClickCommon.CLICK_AREA_BTN;
                        q.this.b.getClickCommon().click_scene = "appinfo";
                        q.this.b.getClickCommon().is_final_click = true;
                        q.this.u().a(q.this.b, q.this.e, str, com.sigmob.sdk.base.a.PREVIEW, str2, true);
                    }

                    @Override // com.sigmob.sdk.base.views.e.b
                    public void b() {
                        if (q.this.f != null) {
                            q.this.q().a();
                        }
                        q.this.j().a(com.sigmob.sdk.base.common.a.AD_FOUR_ELEMENTS_SHOW, 0);
                    }
                });
            }
            if (this.s == null || !this.s.a() || this.t) {
                return;
            }
            this.b.getClickCommon().click_area = "appinfo";
            this.s.show();
            this.t = true;
        } catch (Exception e) {
            SigmobLog.e("openFourElements fail:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View F() {
        View view;
        if (this.g == null || (view = (View) this.g.getParent()) == null) {
            return null;
        }
        return view;
    }

    private boolean G() {
        return (TextUtils.isEmpty(this.b.getHtmlData()) && TextUtils.isEmpty(this.b.getHtmlUrl())) ? false : true;
    }

    private View a(List<View> list, MotionEvent motionEvent) {
        if (list == null) {
            return null;
        }
        for (View view : list) {
            if (com.sigmob.sdk.common.utils.r.a(view, motionEvent)) {
                return view;
            }
        }
        return null;
    }

    private SigNativeAdVideo a(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof SigNativeAdVideo) {
                return (SigNativeAdVideo) childAt;
            }
        }
        return null;
    }

    private void a(int i, MotionEvent motionEvent) throws UnsupportedEncodingException {
        boolean z;
        com.sigmob.sdk.base.common.d.b(this.b);
        com.sigmob.sdk.common.utils.q qVarA = com.sigmob.sdk.common.utils.q.a(F(), motionEvent);
        com.sigmob.sdk.common.utils.q qVarA2 = com.sigmob.sdk.common.utils.q.a(F(), this.l);
        this.b.getClickCommon().down = qVarA2;
        this.b.getClickCommon().up = qVarA2;
        this.b.getClickCommon().click_scene = "preview";
        switch (i) {
            case 1:
                this.b.getClickCommon().click_area = "appinfo";
                this.b.getClickCommon().is_final_click = false;
                j().a(com.sigmob.sdk.base.common.a.AD_CLICK, 0);
                E();
                return;
            case 2:
                boolean zG = G();
                this.b.getClickCommon().click_area = ClickCommon.CLICK_AREA_MATERIAL;
                if (!zG) {
                    z = true;
                    break;
                } else {
                    this.b.getClickCommon().is_final_click = false;
                    j().a(com.sigmob.sdk.base.common.a.AD_CLICK, 0);
                    v();
                    return;
                }
            case 3:
                z = false;
                break;
            default:
                return;
        }
        if (!z) {
            this.b.getClickCommon().click_area = ClickCommon.CLICK_AREA_BTN;
        }
        if (this.a.g() == 1 || this.b.getMaterial().interaction_type.intValue() != com.sigmob.sdk.videoAd.f.DownloadType.a()) {
            this.b.getClickCommon().is_final_click = true;
            u().a(this.e, qVarA2, qVarA, com.sigmob.sdk.base.a.PREVIEW, this.b);
        } else {
            this.b.getClickCommon().is_final_click = false;
            j().a(com.sigmob.sdk.base.common.a.AD_CLICK, 0);
            E();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(View view, int i, int i2) {
        LinearLayout.LayoutParams layoutParams;
        if (view.getParent() instanceof FrameLayout) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams2.width = i;
            layoutParams2.height = i2;
            layoutParams = layoutParams2;
        } else if (view.getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams3.width = i;
            layoutParams3.height = i2;
            layoutParams = layoutParams3;
        } else {
            if (!(view.getParent() instanceof LinearLayout)) {
                return;
            }
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams4.width = i;
            layoutParams4.height = i2;
            layoutParams = layoutParams4;
        }
        view.setLayoutParams(layoutParams);
        view.requestLayout();
    }

    private SigEmptyView b(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof SigEmptyView) {
                return (SigEmptyView) childAt;
            }
        }
        return null;
    }

    public void A() {
        if (this.p != null && this.f != null) {
            ViewParent parent = this.f.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.f);
            }
            this.p.removeAllViews();
            if (this.q != null) {
                this.p.addView(this.f, this.q);
            } else {
                this.p.addView(this.f);
            }
            if (!this.i.contains(this.f)) {
                this.i.add(this.f);
            }
        }
        this.p = null;
        this.q = null;
    }

    public void B() {
        i iVarQ;
        if (this.o || (iVarQ = q()) == null) {
            return;
        }
        iVarQ.b();
    }

    public void C() {
        i iVarQ;
        if (this.o || (iVarQ = q()) == null) {
            return;
        }
        iVarQ.d();
    }

    public k a(Activity activity) {
        if (this.u == null) {
            this.u = new a(activity, this.b);
        }
        return this.u;
    }

    @Override // com.sigmob.sdk.base.common.k.c
    public void a() {
        if (u() != null) {
            u().a(this.e, this.b);
        }
    }

    public void a(Context context, BaseAdUnit baseAdUnit, s sVar) {
        this.e = context;
        this.b = baseAdUnit;
        this.a = sVar;
        com.sigmob.sdk.common.utils.g.a(this.e).a(k().getAd_source_logo(), new g.a() { // from class: com.sigmob.sdk.nativead.q.1
            @Override // com.sigmob.sdk.common.utils.g.a
            public void a() {
            }

            @Override // com.sigmob.sdk.common.utils.g.a
            public void a(Bitmap bitmap) {
                q.this.r = bitmap;
            }
        });
    }

    public void a(final ViewGroup viewGroup, final g gVar) {
        final SigNativeAdVideo sigNativeAdVideoA = a(viewGroup);
        if (sigNativeAdVideoA != null) {
            this.f = sigNativeAdVideoA;
        } else {
            com.sigmob.sdk.common.utils.r.a(this.f);
            this.f = new SigNativeAdVideo(this.e);
        }
        viewGroup.post(new Runnable() { // from class: com.sigmob.sdk.nativead.q.2
            @Override // java.lang.Runnable
            public void run() {
                int width = viewGroup.getWidth();
                int height = viewGroup.getHeight();
                int iO = q.this.o();
                DisplayMetrics displayMetricsAi = com.sigmob.sdk.common.a.n().ai();
                int iMax = Math.max(displayMetricsAi.widthPixels, displayMetricsAi.heightPixels) / 2;
                int iR = q.this.r();
                Integer num = (Integer) q.d.get(Integer.valueOf(viewGroup.hashCode()));
                if (num != null) {
                    height = num.intValue();
                } else {
                    q.d.put(Integer.valueOf(viewGroup.hashCode()), Integer.valueOf(viewGroup.getHeight()));
                }
                if (height == 0) {
                    q.a(viewGroup, width, (int) (width / (iO / Math.min(iR, iMax))));
                }
                if (sigNativeAdVideoA == null) {
                    com.sigmob.sdk.common.utils.r.a(q.this.f);
                    viewGroup.addView(q.this.f);
                }
            }
        });
        if (!this.i.contains(this.f)) {
            this.i.add(this.f);
        }
        if (!this.b.equals(this.f.getAdUnit())) {
            this.f.a(this);
        }
        final i sigVideoAdController = this.f.getSigVideoAdController();
        if (sigVideoAdController != null) {
            sigVideoAdController.a(new h() { // from class: com.sigmob.sdk.nativead.q.3
                @Override // com.sigmob.sdk.nativead.h
                public void a() {
                    q.this.j().a(com.sigmob.sdk.base.common.a.AD_NATIVE_VIDEO_RESTART, 0);
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onProgressUpdate(long j, long j2) {
                    if (gVar != null) {
                        gVar.onProgressUpdate(j, j2);
                    }
                    q.this.b.getVideoCommon().end_time = sigVideoAdController.h();
                    b bVarU = q.this.u();
                    if (bVarU != null) {
                        for (com.sigmob.sdk.base.common.e eVar : bVarU.a(j, j2)) {
                            q.this.j().a(eVar.o(), sigVideoAdController.h());
                            eVar.k();
                        }
                    }
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdComplete() {
                    if (gVar != null) {
                        gVar.onVideoAdComplete();
                    }
                    q.this.b.getVideoCommon().end_time = sigVideoAdController.e();
                    q.this.b.getVideoCommon().is_last = 1;
                    q.this.j().a(com.sigmob.sdk.base.common.a.AD_COMPLETE, sigVideoAdController.h());
                    q.this.j().a(com.sigmob.sdk.base.common.a.AD_VIDEO_LINK, 0);
                    q.this.y = false;
                    q.this.w = false;
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdPaused() {
                    if (gVar != null) {
                        gVar.onVideoAdPaused();
                    }
                    q.this.y = true;
                    q.this.j().a(com.sigmob.sdk.base.common.a.AD_NATIVE_VIDEO_PAUSE, 0);
                    q.this.j().a(com.sigmob.sdk.base.common.a.AD_VIDEO_LINK, 0);
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdResume() {
                    if (gVar != null) {
                        gVar.onVideoAdResume();
                    }
                    q.this.y = false;
                    q.this.b.getVideoCommon().type = 2;
                    q.this.b.getVideoCommon().is_first = 0;
                    q.this.b.getVideoCommon().begin_time = sigVideoAdController.h();
                    q.this.w = false;
                    q.this.j().a(com.sigmob.sdk.base.common.a.AD_NATIVE_VIDEO_START, sigVideoAdController.h());
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdStartPlay() {
                    View viewF = q.this.F();
                    if (viewF != null) {
                        q.this.b.setAdSize(viewF.getWidth(), viewF.getHeight());
                    }
                    q.this.b.getVideoCommon().video_time = sigVideoAdController.e();
                    q.this.b.getVideoCommon().is_first = 1;
                    q.this.b.getVideoCommon().is_last = 0;
                    q.this.b.getVideoCommon().end_time = 0;
                    q.this.b.getVideoCommon().is_auto_play = q.this.u().j() ? 1 : 2;
                    if (q.this.o) {
                        q.this.b.getVideoCommon().scene = 3;
                    } else {
                        q.this.b.getVideoCommon().scene = 1;
                    }
                    if (q.this.x) {
                        q.this.b.getVideoCommon().type = 1;
                        q.this.x = false;
                    } else {
                        q.this.b.getVideoCommon().type = 3;
                    }
                    if (gVar != null) {
                        gVar.onVideoAdStartPlay();
                    }
                    q.this.w = false;
                    q.this.y = false;
                    q.this.j().a(com.sigmob.sdk.base.common.a.AD_NATIVE_VIDEO_START, 0);
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoError(int i, int i2) {
                    q.this.w = false;
                    q.this.y = false;
                    if (gVar != null) {
                        gVar.onVideoError(i, i2);
                    }
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoLoad() {
                    if (gVar != null) {
                        gVar.onVideoLoad();
                    }
                }
            });
        }
    }

    public void a(ViewGroup viewGroup, List<View> list, n nVar) {
        SigEmptyView sigEmptyView;
        ViewGroup.LayoutParams layoutParams;
        if (viewGroup == null) {
            if (nVar != null) {
                nVar.onAdError(600502, "ad container is empty", l());
                return;
            }
            return;
        }
        u().a(new com.sigmob.sdk.base.common.s() { // from class: com.sigmob.sdk.nativead.q.4
            @Override // com.sigmob.sdk.base.common.s
            public void a() {
                if (q.this.n != null) {
                    q.this.n.onAdClicked(q.this.a);
                }
            }
        });
        this.h = list;
        if (this.i == null) {
            this.i = new ArrayList();
        }
        u().a(viewGroup.getContext(), this.b, (e.b) null);
        viewGroup.setDescendantFocusability(393216);
        this.n = nVar;
        n().a(this.e, (Map<String, Object>) null, this.b);
        if (this.g == null) {
            this.g = b(viewGroup);
            if (this.g == null) {
                this.g = new SigEmptyView(this.e);
                sigEmptyView = this.g;
                layoutParams = new ViewGroup.LayoutParams(0, 0);
            }
            this.g.a(u().h(), u().i(), this);
        }
        com.sigmob.sdk.common.utils.r.a(this.g);
        sigEmptyView = this.g;
        layoutParams = new ViewGroup.LayoutParams(0, 0);
        viewGroup.addView(sigEmptyView, layoutParams);
        this.g.a(u().h(), u().i(), this);
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void a(BaseAdUnit baseAdUnit) {
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void a(BaseAdUnit baseAdUnit, String str) {
    }

    public void a(List<ImageView> list, int i) {
        List<SigImage> listF = l().f();
        if (list == null || listF == null) {
            return;
        }
        int iMin = Math.min(list.size(), listF.size());
        for (int i2 = 0; i2 < iMin; i2++) {
            SigImage sigImage = listF.get(i2);
            ImageView imageView = list.get(i2);
            if (!this.i.contains(imageView)) {
                this.i.add(imageView);
            }
            if (this.B == null) {
                this.B = new ArrayList();
            } else {
                this.B.clear();
            }
            this.B.add(imageView);
            com.sigmob.sdk.base.common.d.a(this.e).a(sigImage.imageUrl).a(i).b(i).a(imageView);
        }
    }

    public void a(List<View> list, View.OnTouchListener onTouchListener) {
        if (list != null) {
            for (View view : list) {
                view.setOnClickListener(null);
                view.setOnTouchListener(null);
                view.setOnTouchListener(onTouchListener);
            }
        }
    }

    @Override // com.sigmob.sdk.nativead.SigEmptyView.a
    public void a(boolean z) {
        if (z == this.v) {
            return;
        }
        if (!this.o) {
            com.sigmob.sdk.base.common.d.f().e(this.b);
            if (z) {
                if (this.n != null) {
                    this.n.onAdShow(l());
                }
                j().a(com.sigmob.sdk.base.common.a.AD_NATIVE_SHOW, 0);
            }
        }
        this.v = z;
    }

    @Override // com.sigmob.sdk.base.common.k.c
    public void b() {
        if (u() != null) {
            u().b(this.e, this.b);
        }
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void b(BaseAdUnit baseAdUnit) {
    }

    @Override // com.sigmob.sdk.nativead.c.a
    public void c() {
        SigmobLog.d("----------onAdDetailShow----------");
        if (u() != null) {
            u().c(this.e, this.b);
        }
        this.o = true;
        if (this.n != null) {
            this.n.onAdDetailShow(this.a);
        }
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void c(BaseAdUnit baseAdUnit) {
        if (this.f != null) {
            ViewGroup.LayoutParams layoutParams = ((View) this.f.getParent()).getLayoutParams();
            SigmobLog.d("visibilityStatusChange() called with: isVisible = [" + layoutParams.width + Constants.COLON_SEPARATOR + layoutParams.height + "]");
        }
    }

    @Override // com.sigmob.sdk.nativead.c.a
    public void d() {
        if (u() != null) {
            u().e(this.e, this.b);
        }
        if (this.n != null) {
            this.n.onAdClicked(this.a);
        }
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void d(BaseAdUnit baseAdUnit) {
    }

    @Override // com.sigmob.sdk.nativead.c.a
    public void e() {
        if (this.n != null) {
            this.n.onAdDetailDismiss(this.a);
        }
        this.o = false;
        if (u() != null) {
            u().d(this.e, this.b);
        }
        A();
        if (this.k != null) {
            this.k.setVisibility(0);
        }
        this.j.c(this.b);
    }

    @Override // com.sigmob.sdk.base.common.k.b
    public void e(BaseAdUnit baseAdUnit) {
    }

    @Override // com.sigmob.sdk.nativead.SigEmptyView.a
    public void f() {
        if (this.z) {
            q().a();
            SigmobLog.d("-----------onAdViewPauseImpression---------");
            this.z = false;
        }
    }

    @Override // com.sigmob.sdk.nativead.SigEmptyView.a
    public void g() {
        if (this.f == null || this.f.getParent() == null || this.o || this.z || this.w || !u().j()) {
            return;
        }
        this.z = true;
        this.v = false;
        q().c();
        SigmobLog.d("------------onAdViewStartImpression------------");
    }

    @Override // com.sigmob.sdk.nativead.SigEmptyView.a
    public void h() {
        if (this.o || this.e == null || !this.A) {
            return;
        }
        if (this.i != null) {
            this.i.clear();
        }
        this.A = false;
        SigmobLog.d("------------onAdViewRemoved----------" + hashCode());
        i iVarQ = q();
        if (iVarQ != null) {
            iVarQ.a();
        }
        this.z = false;
        j().a(com.sigmob.sdk.base.common.a.AD_HIDE, 0);
        if (this.x || this.y) {
            return;
        }
        j().a(com.sigmob.sdk.base.common.a.AD_VIDEO_LINK, 0);
    }

    @Override // com.sigmob.sdk.nativead.SigEmptyView.a
    public void i() {
        if (this.A || this.o) {
            return;
        }
        this.A = true;
        SigmobLog.d("----------onAdViewShow------------" + hashCode());
        this.w = false;
        this.z = false;
        View viewF = F();
        if (viewF != null) {
            viewF.setOnTouchListener(this);
        }
        if (this.f != null && !this.i.contains(this.f)) {
            this.i.add(this.f);
        } else if (this.B != null) {
            if (this.i.isEmpty()) {
                this.i.addAll(this.B);
            } else {
                for (View view : this.B) {
                    if (!this.i.contains(view)) {
                        this.i.add(view);
                    }
                }
            }
        }
        a(this.h, this);
        j().a(com.sigmob.sdk.base.common.a.AD_START, 0);
    }

    public com.sigmob.sdk.base.common.v j() {
        com.sigmob.sdk.base.common.v sessionManager = this.b.getSessionManager();
        if (sessionManager != null || this.e == null) {
            return sessionManager;
        }
        e eVar = new e(this.e.getApplicationContext());
        eVar.a(this.e, this.b);
        return eVar;
    }

    @Override // com.sigmob.sdk.nativead.r
    public BaseAdUnit k() {
        return this.b;
    }

    @Override // com.sigmob.sdk.nativead.r
    public s l() {
        return this.a;
    }

    @Override // com.sigmob.sdk.nativead.r
    public SigAppInfoView m() {
        if (this.k == null && this.b.getadPrivacy() != null) {
            this.k = new SigAppInfoView(this.e);
            this.k.setOnTouchListener(this);
            this.k.a(this.b.getAppVersion(), this.b.getCompanyName());
        }
        return this.k;
    }

    public c n() {
        if (this.j == null) {
            this.j = new c(this);
        }
        return this.j;
    }

    public int o() {
        int width;
        View viewF = F();
        if (this.f == null || viewF == null || (width = viewF.getWidth()) <= 0) {
            return 1280;
        }
        return width;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) throws UnsupportedEncodingException {
        boolean z = false;
        if (motionEvent == null) {
            return false;
        }
        if ((motionEvent.getAction() != 0 || this.C <= 0 || System.currentTimeMillis() - this.C >= 500) && this.n != null) {
            if (motionEvent.getAction() != 1) {
                this.C = System.currentTimeMillis();
                this.l = motionEvent;
                return true;
            }
            this.b.getClickCommon().click_scene = "preview";
            if (view == this.k) {
                a(1, motionEvent);
                return true;
            }
            if (a(this.i, motionEvent) != null) {
                if (this.f != null && this.f.a(motionEvent)) {
                    z = true;
                }
                a(z ? 3 : 2, motionEvent);
                return true;
            }
            if (a(this.h, motionEvent) != null) {
                this.b.getClickCommon().click_scene = "preview";
                this.b.getClickCommon().click_area = ClickCommon.CLICK_AREA_BTN;
                a(3, motionEvent);
                return true;
            }
        }
        return false;
    }

    public View p() {
        return this.f;
    }

    public i q() {
        if (p() != null) {
            return this.f.getSigVideoAdController();
        }
        return null;
    }

    public int r() {
        View viewF = F();
        if (p() == null || viewF == null || viewF.getWidth() <= 0) {
            return 720;
        }
        return (int) (viewF.getWidth() / this.b.getAdPercent());
    }

    public void s() {
        a(this.i, (View.OnTouchListener) null);
        a(this.h, (View.OnTouchListener) null);
        this.i = null;
        this.h = null;
        this.n = null;
        this.c = null;
    }

    public void t() {
        s();
        if (this.f != null) {
            com.sigmob.sdk.common.utils.r.a(this.f);
            this.f.c();
            this.f = null;
        }
        if (this.b != null && this.b.getSessionManager() != null) {
            j().a(com.sigmob.sdk.base.common.a.AD_HIDE, 0);
            j().a();
        }
        u().c();
        if (this.s != null) {
            this.s.dismiss();
            this.s.c();
            this.s = null;
        }
        if (this.u != null && (this.u instanceof a)) {
            ((a) this.u).b();
            this.u = null;
        }
        if (this.g != null) {
            com.sigmob.sdk.common.utils.r.a(this.g);
            this.g = null;
        }
        this.j = null;
        this.e = null;
    }

    @Override // com.sigmob.sdk.nativead.r
    public b u() {
        if (this.m == null) {
            this.m = (b) this.b.getAdConfig();
        }
        return this.m;
    }

    public void v() throws UnsupportedEncodingException {
        Bundle bundle = new Bundle();
        if (this.f != null && u().d()) {
            ViewAttr viewAttr = new ViewAttr();
            int[] iArr = new int[2];
            this.f.getLocationOnScreen(iArr);
            viewAttr.a(iArr[0]);
            viewAttr.b(iArr[1]);
            viewAttr.c(this.f.getMeasuredWidth());
            viewAttr.d(this.f.getMeasuredHeight());
            bundle.putParcelable("attr", viewAttr);
            this.p = (ViewGroup) this.f.getParent();
            this.q = this.f.getLayoutParams();
            o.a(this.f);
            SigMacroCommon macroCommon = this.b.getMacroCommon();
            if (macroCommon != null) {
                macroCommon.addMarcoKey(SigMacroCommon._SCENE_, "3");
            }
            this.o = true;
        }
        if (this.k != null) {
            this.k.setVisibility(4);
        }
        n().a(this.e, this.b, bundle);
    }

    public String w() {
        return k().getCTAText();
    }

    public Bitmap x() {
        return this.r;
    }

    public void y() {
        i iVarQ;
        if (this.o || (iVarQ = q()) == null) {
            return;
        }
        iVarQ.c();
    }

    public void z() {
        i iVarQ;
        if (this.o || (iVarQ = q()) == null) {
            return;
        }
        this.w = true;
        iVarQ.a();
    }
}
