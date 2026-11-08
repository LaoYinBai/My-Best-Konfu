package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.union.game.sdk.common.ui.floatview.BaseFloatView;
import com.ss.union.game.sdk.common.ui.floatview.IFloatViewState;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.NotchUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGScreenOrientationFrameLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends BaseFloatView implements LGScreenOrientationFrameLayout.a {
    private static final String c = "LGAutomaticDetectionFloatView";
    private static final int d = 4000;
    private static final int e = 99;
    int a;
    int b;
    private TextView f;
    private TextView g;
    private boolean h;
    private LGScreenOrientationFrameLayout l;
    private EnumC0072a i = EnumC0072a.RIGHT_NORMAL;
    private b j = new b();
    private b k = new b();
    private Handler m = new Handler(Looper.getMainLooper()) { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.a.1
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what == 99) {
                switch (AnonymousClass4.a[a.this.i.ordinal()]) {
                    case 1:
                        a.this.e();
                        break;
                    case 2:
                        a.this.g();
                        break;
                }
            }
        }
    };

    /* renamed from: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.a$a, reason: collision with other inner class name */
    enum EnumC0072a {
        LEFT_NORMAL,
        LEFT_EXPEND,
        RIGHT_NORMAL,
        RIGHT_EXPEND
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    @Nullable
    protected IFloatViewState createFloatViewStateManager() {
        return null;
    }

    static class b {
        int a;
        int b;
        int c;
        int d;

        b() {
        }
    }

    /* renamed from: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.a$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[EnumC0072a.values().length];

        static {
            try {
                a[EnumC0072a.LEFT_EXPEND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[EnumC0072a.RIGHT_EXPEND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[EnumC0072a.LEFT_NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[EnumC0072a.RIGHT_NORMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void setContent() {
        int screenWidth = getScreenWidth(this.mAct);
        int screenHeight = getScreenHeight(this.mAct);
        this.a = Math.min(screenWidth, screenHeight);
        this.b = Math.max(screenWidth, screenHeight);
        this.mRoot.addView(LayoutInflater.from(this.mAct).inflate(ResourceUtils.getLayoutIdByName("lg_automatic_detection_float_view"), (ViewGroup) this.mRoot, false));
        b();
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void initWindowLayoutParams() {
        super.initWindowLayoutParams();
        this.wlp.flags &= -1025;
        this.wlp.flags |= ApkDownloadComplianceInterface.INSTALL_BITS;
        this.j.a = NotchUtils.hasNotchScreen(this.mAct) ? UIUtils.dip2Px(44.0f) : 0;
        this.j.b = UIUtils.dip2Px(31.0f);
        this.j.c = 0;
        this.j.d = UIUtils.dip2Px(32.0f);
        this.k.a = 0;
        this.k.b = UIUtils.dip2Px(44.0f);
        this.k.c = 0;
        this.k.d = UIUtils.dip2Px(34.0f);
        this.mRoot.post(new Runnable() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.a.2
            @Override // java.lang.Runnable
            public void run() {
                a.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.mRoot.measure(0, 0);
        this.wlp.x = this.mScreenWidth - this.mRoot.getMeasuredWidth();
        this.wlp.y = ((this.mScreenHeight / 3) - UIUtils.dip2Px(44.0f)) - UIUtils.dip2Px(33.0f);
        g();
    }

    private void b() {
        this.l = (LGScreenOrientationFrameLayout) a("lg_automatic_detection_float_layout_root");
        this.f = (TextView) a("lg_detection_float_view_tv_left");
        this.g = (TextView) a("lg_detection_float_view_tv_right");
        this.l.setScreenOrientationListener(this);
        this.l.setOnClickListener(new AntiShakeClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.a.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                switch (AnonymousClass4.a[a.this.i.ordinal()]) {
                    case 1:
                    case 2:
                        a.this.k();
                        break;
                    case 3:
                        a.this.f();
                        break;
                    case 4:
                        a.this.h();
                        break;
                }
            }
        }));
    }

    private void c() {
        this.m.sendEmptyMessageDelayed(99, 4000L);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView, com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onMove(int i, int i2, int i3, int i4) {
        if (!j()) {
            this.wlp.x += i3;
        }
        this.wlp.y += i4;
        if (this.h) {
            if (!j()) {
                this.wlp.x = Math.max(this.j.a, this.wlp.x);
            }
            this.wlp.y = Math.max(this.j.b, this.wlp.y);
            this.wlp.y = Math.min((getScreenHeight(this.mAct) - this.j.d) - this.l.getMeasuredHeight(), this.wlp.y);
        } else {
            this.wlp.y = Math.max(this.k.b, this.wlp.y);
            this.wlp.y = Math.min((getScreenHeight(this.mAct) - this.k.d) - this.l.getMeasuredHeight(), this.wlp.y);
        }
        i();
        updateView();
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView, com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onUp(int i, int i2) {
        super.onUp(i, i2);
        if (this.wlp.x > this.mScreenWidth / 2) {
            if (j()) {
                h();
                return;
            } else {
                g();
                return;
            }
        }
        if (j()) {
            f();
        } else {
            e();
        }
    }

    private int d() {
        if (this.h) {
            return this.j.a;
        }
        return this.k.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.i = EnumC0072a.LEFT_NORMAL;
        this.l.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_detection_left_normal_bg"));
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.mRoot.measure(0, 0);
        animateX(d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.i = EnumC0072a.LEFT_EXPEND;
        this.l.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_detection_left_extend_bg"));
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.mRoot.measure(0, 0);
        animateX(d());
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.i = EnumC0072a.RIGHT_NORMAL;
        this.l.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_detection_right_normal_bg"));
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.mRoot.measure(0, 0);
        animateX(this.mScreenWidth - this.mRoot.getMeasuredWidth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.i = EnumC0072a.RIGHT_EXPEND;
        this.l.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_detection_right_extend_bg"));
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.mRoot.measure(0, 0);
        animateX(this.mScreenWidth - this.mRoot.getMeasuredWidth());
        c();
    }

    private void i() {
        if (!j()) {
            this.l.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_circle_99000000"));
            this.f.setVisibility(8);
            this.g.setVisibility(8);
        }
        this.m.removeMessages(99);
    }

    private boolean j() {
        return this.i == EnumC0072a.LEFT_EXPEND || this.i == EnumC0072a.RIGHT_EXPEND;
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGScreenOrientationFrameLayout.a
    public void a(Configuration configuration) {
        this.h = configuration.orientation == 2;
        if (this.h) {
            this.mScreenWidth = this.b;
            this.mScreenHeight = this.a;
        } else {
            this.mScreenWidth = this.a;
            this.mScreenHeight = this.b;
        }
        a();
    }

    private <T extends View> T a(String str) {
        return (T) this.mRoot.findViewById(ResourceUtils.getIdByName(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LGAutomaticDetectionDetailFragment.a();
    }
}
