package com.ss.union.game.sdk.core.vapp.buoy;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.union.game.sdk.common.ui.floatview.BaseFloatView;
import com.ss.union.game.sdk.common.ui.floatview.FloatIntent;
import com.ss.union.game.sdk.common.ui.floatview.IConfigurationChangedListener;
import com.ss.union.game.sdk.common.ui.floatview.IFloatViewState;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.browser.BrowserFragment;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.core.vapp.buoy.sector.SectorFrameLayout;
import com.ss.union.game.sdk.core.vapp.c;
import com.ss.union.game.sdk.core.vapp.callback.LGAdVolumeRewardCallBack;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends BaseFloatView implements IConfigurationChangedListener {
    private static final int a = 56;
    private static final int b = 58;
    private Configuration c;
    private b<d> d;
    private LGAdVolumeRewardCallBack n;
    private SectorFrameLayout e = null;
    private ImageView f = null;
    private View g = null;
    private ImageView h = null;
    private ImageView i = null;
    private boolean j = false;
    private Runnable k = new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.1
        @Override // java.lang.Runnable
        public void run() {
            d.this.hideHalf(0);
            d.this.k = null;
        }
    };
    private a l = a.NORMAL;
    private boolean m = false;
    private boolean o = true;
    private int p = 0;
    private View q = null;
    private Runnable r = new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.5
        @Override // java.lang.Runnable
        public void run() {
            d.this.A();
            d.this.m = true;
            d.this.o = false;
            d.this.v();
            d.this.m();
            d.this.o();
            d.this.e.a();
        }
    };

    enum a {
        NORMAL,
        AD_EXPAND_MODE,
        AD_COLLAPSE_MODE
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void attachIntent(FloatIntent floatIntent) {
        super.attachIntent(floatIntent);
        this.c = floatIntent.activity.getResources().getConfiguration();
        com.ss.union.game.sdk.core.vapp.buoy.a.a().a(this);
        this.d = new c(this.mAct);
        this.mRoot.registerUIChangedListener(this);
        g();
    }

    private void g() {
        this.j = ResourceUtils.getBoolean("lg_dan_juan_float_ball_auto_orientation_rotate", false);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void onWindowLayoutParamsCreated() {
        super.onWindowLayoutParamsCreated();
        h();
        n();
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected IFloatViewState createFloatViewStateManager() {
        return new e();
    }

    private boolean a(int i) {
        return i > 0 && i < this.mScreenHeight;
    }

    private void h() {
        int y;
        int x = -1;
        if (this.mFloatViewState != null) {
            x = this.mFloatViewState.getX();
            y = this.mFloatViewState.getY();
        } else {
            y = -1;
        }
        VLog.d(getTag(), "restoreXYState mSaveLastX: " + x + ",mSaveLastY: " + y);
        if (a(y)) {
            this.wlp.x = x;
            this.wlp.y = y;
            if (this.wlp.x > (this.mScreenWidth / 2) - (this.mRoot.getMeasuredWidth() / 2)) {
                this.wlp.x = a();
            } else {
                this.wlp.x = b();
            }
            this.wlp.x = Math.min(a(), this.wlp.x);
            this.wlp.x = Math.max(b(), this.wlp.x);
            return;
        }
        if (this.mFloatIntent != null) {
            boolean z = this.mFloatIntent.x > 0 && this.mFloatIntent.x < getScreenWidth(this.mAct);
            boolean z2 = this.mFloatIntent.y > 0 && this.mFloatIntent.y < getScreenHeight(this.mAct);
            if (z && z2) {
                this.wlp.x = this.mFloatIntent.x;
                this.wlp.y = this.mFloatIntent.y;
            }
        }
    }

    protected int a() {
        return (this.mScreenWidth - this.mRoot.getMeasuredWidth()) - dp2px(8);
    }

    protected int b() {
        return dp2px(8);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void setContent() {
        this.mRoot.addView(LayoutInflater.from(this.mAct).inflate(ResourceUtils.getLayoutIdByName("lg_v_floatball_layout"), (ViewGroup) this.mRoot, false));
        c();
        d();
    }

    protected void c() {
        this.e = (SectorFrameLayout) this.mRoot.findViewById(ResourceUtils.getIdByName("lg_v_sector_layout"));
        this.f = (ImageView) this.mRoot.findViewById(ResourceUtils.getIdByName("lg_iv_ad_volume_state"));
        this.e.setMainView(this.mRoot.findViewById(ResourceUtils.getIdByName("lg_v_sector_main_menu")));
        this.i = (ImageView) this.mRoot.findViewById(ResourceUtils.getIdByName("lg_iv_ball"));
        this.g = this.mRoot.findViewById(ResourceUtils.getIdByName("lg_iv_ad_volume_use"));
        this.h = (ImageView) this.mRoot.findViewById(ResourceUtils.getIdByName("lg_iv_ad_volume_vapp"));
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                d.this.wlp.alpha = 1.0f;
                if (d.this.l()) {
                    d.this.m();
                    d.this.hideHalfAfterDelayTime();
                } else {
                    com.ss.union.game.sdk.core.vapp.d.b();
                    d.this.i();
                }
            }
        });
        a(this.i);
    }

    protected void d() {
        this.p = x();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        try {
            BrowserFragment.show(GameOptionConfig.GameOption.VApp.floatBallClickUrl(), "", true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void onViewCreated() {
        super.onViewCreated();
        this.mUiHandler.postDelayed(this.k, getDefaultHideDelayTime());
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView, com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onDown(int i, int i2) {
        VLog.d(getTag(), "onDown   x: " + this.wlp.x + ",y: " + this.wlp.y);
        if (this.k != null) {
            this.mUiHandler.removeCallbacks(this.k);
        }
        this.wlp.alpha = 1.0f;
        m();
        super.onDown(i, i2);
        this.d.a(this);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView, com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onMove(int i, int i2, int i3, int i4) {
        VLog.d(getTag(), "onMove   x: " + this.wlp.x + ",y: " + this.wlp.y);
        super.onMove(i, i2, i3, i4);
        WindowManager.LayoutParams layoutParams = this.wlp;
        layoutParams.flags = layoutParams.flags & (-513);
        this.d.b(this);
    }

    private int j() {
        if (y()) {
            return this.mScreenWidth - dp2px(106);
        }
        return (this.mScreenWidth - this.mRoot.getWidth()) - dp2px(8);
    }

    private int k() {
        if (y()) {
            return -dp2px(48);
        }
        return dp2px(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        return this.wlp.x < k() || this.wlp.x > j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        boolean z = true;
        if (this.wlp.x < k()) {
            this.wlp.x = k();
        } else if (this.wlp.x > j()) {
            this.wlp.x = j();
        } else {
            z = false;
        }
        if (z) {
            updateView();
        }
    }

    private void n() {
        this.wlp.flags &= -1025;
        this.wlp.flags |= ApkDownloadComplianceInterface.INSTALL_BITS;
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView, com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onUp(int i, int i2) {
        super.onUp(i, i2);
        VLog.d(getTag(), "onUp   x: " + this.wlp.x + ",y: " + this.wlp.y);
        WindowManager.LayoutParams layoutParams = this.wlp;
        layoutParams.flags = layoutParams.flags | 512;
        n();
        if (this.wlp.x > this.mScreenWidth / 2) {
            animateX(j()).addListener(new Animator.AnimatorListener() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.7
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    d.this.v();
                    d.this.hideHalfAfterDelayTime();
                }
            });
        } else {
            animateX(k()).addListener(new Animator.AnimatorListener() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.8
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    d.this.v();
                    d.this.hideHalfAfterDelayTime();
                }
            });
        }
        this.d.c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (p()) {
            this.e.setDirectionRight(true);
        } else {
            this.e.setDirectionRight(false);
        }
    }

    private boolean p() {
        return this.wlp.x > this.mScreenWidth / 2;
    }

    private void q() {
        this.e.a();
    }

    private void r() {
        this.e.setVisibility(0);
        this.i.setVisibility(8);
        this.e.a();
    }

    private void a(ImageView imageView) {
        String strFloatBallIcon = GameOptionConfig.GameOption.VApp.floatBallIcon();
        if (TextUtils.isEmpty(strFloatBallIcon)) {
            return;
        }
        Glide.with(this.mAct).load(strFloatBallIcon).placeholder(ResourceUtils.getDrawableIdByName("lg_v_float_ball_bg")).error(ResourceUtils.getDrawableIdByName("lg_v_float_ball_bg")).centerInside().into(imageView);
    }

    private int s() {
        return this.mScreenWidth - dp2px(103);
    }

    private int t() {
        return this.mScreenWidth - dp2px(103);
    }

    private boolean u() {
        return this.p <= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        View viewFindViewById = this.e.findViewById(ResourceUtils.getIdByName("lg_red_dot_left"));
        View viewFindViewById2 = this.e.findViewById(ResourceUtils.getIdByName("lg_red_dot_right"));
        if (!this.o) {
            viewFindViewById.setVisibility(8);
            viewFindViewById2.setVisibility(8);
        } else if (p()) {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(8);
        } else {
            viewFindViewById.setVisibility(8);
            viewFindViewById2.setVisibility(0);
        }
    }

    private void w() {
        this.p++;
        com.ss.union.game.sdk.core.vapp.c.a(this.p);
    }

    private int x() {
        return com.ss.union.game.sdk.core.vapp.c.c();
    }

    public void e() {
        this.wlp.alpha = 1.0f;
        w();
        o();
        releaseAnimator();
        this.mUiHandler.removeCallbacksAndMessages(null);
        this.l = a.AD_EXPAND_MODE;
        this.i.setVisibility(8);
        this.e.setVisibility(0);
        this.f.setImageResource(ResourceUtils.getDrawableIdByName("lg_v_float_ad_mode_volume_open"));
        if (u()) {
            this.l = a.AD_EXPAND_MODE;
            this.o = false;
            this.e.a();
        } else {
            this.l = a.AD_COLLAPSE_MODE;
            detachView();
            this.e.setBackground(null);
            this.o = true;
        }
        v();
        this.e.a(new SectorFrameLayout.a() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.9
            private boolean b = true;
            private boolean c = true;

            @Override // com.ss.union.game.sdk.core.vapp.buoy.sector.SectorFrameLayout.a
            public void a() {
                if (this.c) {
                    d.this.z();
                    d.this.l = a.AD_EXPAND_MODE;
                    d.this.mRoot.setSlideEnabled(false);
                    this.c = false;
                    this.b = true;
                    d.this.f.setImageResource(ResourceUtils.getDrawableIdByName("lg_v_float_ad_mode_volume_close"));
                    d.this.e.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_v_float_ad_mode_bg"));
                    d.this.releaseAnimator();
                    d.this.mUiHandler.removeCallbacksAndMessages(d.this.r);
                    d.this.mUiHandler.postDelayed(d.this.r, 3000L);
                }
            }

            @Override // com.ss.union.game.sdk.core.vapp.buoy.sector.SectorFrameLayout.a
            public void b() {
                if (this.b) {
                    d.this.l = a.AD_COLLAPSE_MODE;
                    d.this.mRoot.setSlideEnabled(true);
                    this.c = true;
                    this.b = false;
                    d.this.f.setImageResource(ResourceUtils.getDrawableIdByName("lg_v_float_ad_mode_volume_open"));
                    d.this.e.setBackground(null);
                    if (d.this.m) {
                        d.this.hideHalf(0);
                    } else {
                        d.this.hideHalfAfterDelayTime();
                    }
                    d.this.m = false;
                }
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                if (d.this.y()) {
                    d.this.wlp.alpha = 1.0f;
                    if (d.this.l()) {
                        d.this.m();
                        d.this.hideHalfAfterDelayTime();
                        return;
                    }
                    d.this.A();
                    d.this.o = false;
                    d.this.v();
                    d.this.m = false;
                    d.this.mUiHandler.removeCallbacksAndMessages(null);
                    d.this.m();
                    d.this.o();
                    d.this.e.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_v_float_ad_mode_bg"));
                    d.this.e.a();
                    if (d.this.l == a.AD_EXPAND_MODE) {
                        com.ss.union.game.sdk.core.vapp.d.e();
                    }
                }
            }
        });
        this.wlp.x = this.wlp.x > this.mScreenWidth / 2 ? j() : k();
        this.wlp.y -= dp2px(58);
        if (this.mRoot.isAttachedToWindow()) {
            updateView();
        } else {
            this.mUiHandler.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.11
                @Override // java.lang.Runnable
                public void run() {
                    d.this.attachView();
                    if (d.this.l == a.AD_EXPAND_MODE) {
                        d.this.mUiHandler.postDelayed(d.this.r, 3000L);
                    } else {
                        d.this.mUiHandler.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.11.1
                            @Override // java.lang.Runnable
                            public void run() {
                                d.this.o = false;
                                d.this.v();
                                d.this.hideHalf(0);
                            }
                        }, 3000L);
                    }
                }
            }, 100L);
        }
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                if (d.this.n == null) {
                    VLog.d(d.this.getTag(), "该广告卷已经消耗了，不做处理");
                } else {
                    com.ss.union.game.sdk.core.vapp.d.d();
                    com.ss.union.game.sdk.core.vapp.c.a(new c.a() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.12.1
                        @Override // com.ss.union.game.sdk.core.vapp.c.a
                        public void a(String str) {
                            if (d.this.n != null) {
                                VLog.d(d.this.getTag(), "回调cp广告卷消耗成功，cp需要在此回调发放奖励");
                                d.this.n.onReward();
                                d.this.n = null;
                            }
                        }

                        @Override // com.ss.union.game.sdk.core.vapp.c.a
                        public void a(int i, String str) {
                            VLog.e(d.this.getTag(), "消耗广告卷失败:err=" + i + ",msg=" + str);
                            ToastUtils toastUtils = ToastUtils.getInstance();
                            StringBuilder sb = new StringBuilder();
                            sb.append("消耗广告卷失败：");
                            sb.append(str);
                            toastUtils.toast(sb.toString());
                        }
                    });
                }
            }
        });
        a(this.h);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                com.ss.union.game.sdk.core.vapp.d.c();
                d.this.i();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView, com.ss.union.game.sdk.common.ui.floatview.IFloatView
    public void onDestroyed() {
        this.mRoot.unRegisterUIChangedListener(this);
        super.onDestroyed();
        com.ss.union.game.sdk.core.vapp.buoy.a.a().b();
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y() {
        return this.l == a.AD_COLLAPSE_MODE || this.l == a.AD_EXPAND_MODE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        try {
            A();
            View viewInflate = LayoutInflater.from(this.mAct).inflate(ResourceUtils.getLayoutIdByName("lg_v_floatball_ad_use_tips"), (ViewGroup) null);
            this.q = viewInflate;
            View viewFindViewById = viewInflate.findViewById(ResourceUtils.getIdByName("lg_v_use_ad_tips_ll"));
            if (p()) {
                viewFindViewById.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_v_ad_use_tips_bg_right"));
                viewFindViewById.setPadding(0, 0, 0, 0);
            }
            int iDp2px = dp2px(180);
            int iDp2px2 = dp2px(48);
            int[] iArr = new int[2];
            this.g.getLocationOnScreen(iArr);
            int width = iArr[0];
            int i = iArr[1];
            if (p()) {
                width = (width + this.g.getWidth()) - iDp2px;
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.x = width;
            layoutParams.y = (i - iDp2px2) - dp2px(5);
            layoutParams.gravity = 8388659;
            layoutParams.width = iDp2px;
            layoutParams.height = iDp2px2;
            layoutParams.format = 1;
            layoutParams.type = 2;
            layoutParams.flags = 520;
            layoutParams.flags |= ApkDownloadComplianceInterface.INSTALL_BITS;
            WindowManager windowManager = (WindowManager) this.mAct.getSystemService("window");
            if (windowManager != null) {
                windowManager.addView(viewInflate, layoutParams);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        try {
            WindowManager windowManager = (WindowManager) this.mAct.getSystemService("window");
            if (windowManager == null || this.q == null) {
                return;
            }
            windowManager.removeView(this.q);
            this.q = null;
        } catch (Throwable unused) {
        }
    }

    public void a(Context context, LGAdVolumeRewardCallBack lGAdVolumeRewardCallBack) {
        VLog.d(getTag(), "tryShowAdVolumeInRewardVideoScene ");
        if (y()) {
            VLog.d(getTag(), "已经是广告模式了");
        } else {
            this.n = lGAdVolumeRewardCallBack;
            e();
        }
    }

    private void B() {
        this.mRoot.setSlideEnabled(true);
        this.e.b();
    }

    public void f() {
        if (y()) {
            A();
            releaseAnimator();
            B();
            this.mUiHandler.removeCallbacksAndMessages(null);
            this.l = a.NORMAL;
            this.e.setVisibility(8);
            this.i.setVisibility(8);
            this.wlp.x += dp2px(56);
            this.wlp.y += dp2px(58);
            detachView();
            this.mUiHandler.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.3
                @Override // java.lang.Runnable
                public void run() {
                    d.this.i.setVisibility(0);
                    d.this.attachView();
                    d.this.mUiHandler.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            d.this.hideHalf(0);
                        }
                    }, 3000L);
                }
            }, 100L);
            this.n = null;
        }
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IConfigurationChangedListener
    public void onConfigurationChanged(Configuration configuration) {
        if (!this.j) {
            VLog.d("悬浮球不会自动旋转，返回");
            return;
        }
        VLog.d("悬浮球开始自动旋转");
        this.mUiHandler.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.buoy.d.4
            @Override // java.lang.Runnable
            public void run() {
                d.this.mScreenWidth = BaseFloatView.getScreenWidth(d.this.mAct);
                d.this.mScreenHeight = BaseFloatView.getScreenHeight(d.this.mAct);
                if (d.this.y()) {
                    d.this.wlp.x = d.this.mScreenWidth - d.this.dp2px(106);
                } else {
                    d.this.wlp.x = (d.this.mScreenWidth - d.this.mRoot.getMeasuredWidth()) - d.this.dp2px(8);
                }
                d.this.wlp.y = (d.this.mScreenHeight / 3) - (d.this.mRoot.getMeasuredHeight() / 2);
                d.this.wlp.flags |= 512;
                d.this.updateView();
                d.this.hideHalfAfterDelayTime();
            }
        }, 200L);
        if (this.c.orientation != configuration.orientation) {
            this.c = configuration;
        }
    }
}
