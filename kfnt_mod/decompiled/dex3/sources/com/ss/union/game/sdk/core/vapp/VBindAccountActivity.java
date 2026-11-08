package com.ss.union.game.sdk.core.vapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.util.AppUtils;
import com.ss.union.game.sdk.common.util.BitmapUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.browser.JSInterface;
import com.ss.union.game.sdk.core.vapp.b.a;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VBindAccountActivity extends a {
    private static final int a = 0;
    private static final int b = -10001;
    private static final int c = -10002;
    private static final int d = -10003;
    private static final int e = -10004;
    private static final int f = -10005;
    private static final String g = "key_v_token";
    private static final String h = "key_v_nick_name";
    private static final String i = "key_v_need_back";
    private static final String j = "key_v_is_from_v";
    private AppLogIdUpdateListener A;
    private com.ss.union.game.sdk.core.vapp.b.a.b B = new com.ss.union.game.sdk.core.vapp.b.a.b() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.1
        @Override // com.ss.union.game.sdk.core.vapp.b.a.a
        public void a(com.ss.union.game.sdk.core.vapp.a.a aVar) {
            VBindAccountActivity.this.a(0);
            c.d();
            boolean unused = VBindAccountActivity.this.n;
            ToastUtils.getInstance().toastRes("lg_v_bind_success");
        }

        @Override // com.ss.union.game.sdk.core.vapp.b.a.a
        public void a(int i2, String str, @Nullable JSONObject jSONObject) throws Resources.NotFoundException {
            if (a.C0093a.a(i2)) {
                VBindAccountActivity.this.g();
                VBindAccountActivity.this.a(i2, str, jSONObject);
                return;
            }
            ToastUtils.getInstance().toast("绑定失败:" + str);
        }
    };
    private String k;
    private String l;
    private boolean m;
    private boolean n;
    private ImageView o;
    private TextView p;
    private TextView q;
    private ImageView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private View w;
    private View x;
    private TextView y;
    private TextView z;

    public void onBackPressed() {
    }

    @Override // com.ss.union.game.sdk.core.vapp.a
    public /* bridge */ /* synthetic */ void finish() {
        super.finish();
    }

    @Override // com.ss.union.game.sdk.core.vapp.a
    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public static void a(Context context, String str, String str2, boolean z, boolean z2) {
        Intent intent = new Intent(context, (Class<?>) VBindAccountActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BACK_CLEAR_DATA);
        intent.putExtra(g, str);
        intent.putExtra(h, str2);
        intent.putExtra(i, z);
        intent.putExtra(j, z2);
        context.startActivity(intent);
    }

    @Override // com.ss.union.game.sdk.core.vapp.a
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(getIntent());
        a();
        b();
        c();
        d();
    }

    private void a() {
        setContentView(ResourceUtils.getLayoutIdByName("lg_v_activity_bind_layout"));
        this.o = (ImageView) findViewById(ResourceUtils.getIdByName("lg_iv_game"));
        this.p = (TextView) findViewById(ResourceUtils.getIdByName("lg_tv_game_name"));
        this.q = (TextView) findViewById(ResourceUtils.getIdByName("lg_tv_game_user_name"));
        this.r = (ImageView) findViewById(ResourceUtils.getIdByName("lg_iv_vapp"));
        this.s = (TextView) findViewById(ResourceUtils.getIdByName("lg_tv_vapp_name"));
        this.t = (TextView) findViewById(ResourceUtils.getIdByName("lg_tv_vapp_user_name"));
        this.u = (TextView) findViewById(ResourceUtils.getIdByName("lg_btn_v_bind_later"));
        this.v = (TextView) findViewById(ResourceUtils.getIdByName("lg_btn_v_bind_now"));
        this.w = findViewById(ResourceUtils.getIdByName("lg_v_show_bind_layout"));
        this.x = findViewById(ResourceUtils.getIdByName("lg_v_has_bind_layout"));
        this.y = (TextView) findViewById(ResourceUtils.getIdByName("lg_tv_v_has_bind_desc"));
        this.z = (TextView) findViewById(ResourceUtils.getIdByName("lg_tv_v_has_bind_i_know"));
        b.a(this.u, 1.4f);
        b.a(this.v, 1.4f);
        b.a(this.z, 1.4f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        Drawable appIcon = AppUtils.getAppIcon(this, getPackageName());
        String appName = AppUtils.getAppName(this, getPackageName());
        Drawable appIcon2 = AppUtils.getAppIcon(this, JSInterface.a.c);
        String appName2 = AppUtils.getAppName(this, JSInterface.a.c);
        int iDip2Px = UIUtils.dip2Px(52.0f);
        int iDip2Px2 = UIUtils.dip2Px(16.0f);
        if (appIcon != null) {
            try {
                this.o.setImageDrawable(BitmapUtils.createRoundRadiusDrawable(this, BitmapUtils.drawableToBitmap(appIcon), iDip2Px, iDip2Px, iDip2Px2));
            } catch (Throwable unused) {
                this.o.setImageDrawable(appIcon);
            }
        }
        if (appIcon2 != null) {
            try {
                this.r.setImageDrawable(BitmapUtils.createRoundRadiusDrawable(this, BitmapUtils.drawableToBitmap(appIcon2), iDip2Px, iDip2Px, iDip2Px2));
            } catch (Throwable unused2) {
                this.r.setImageDrawable(appIcon2);
            }
        }
        this.p.setText(appName);
        this.s.setText(appName2);
        if (!TextUtils.isEmpty(this.l)) {
            this.t.setText(this.l);
        }
        if (TextUtils.isEmpty(LGAccountDataUtil.getCurrentUserOpenId())) {
            this.q.setText("游客");
        } else {
            j();
        }
    }

    private void c() {
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VBindAccountActivity.this.e();
                VBindAccountActivity.this.h();
            }
        });
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VBindAccountActivity.this.f();
                VBindAccountActivity.this.i();
            }
        });
    }

    private void d() {
        if (this.n) {
            d.g();
        } else {
            d.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.n) {
            d.i();
        } else {
            d.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.n) {
            d.k();
        } else {
            d.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        d.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        a(b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
        if (currentUserLocal != null) {
            com.ss.union.game.sdk.core.vapp.b.a.b(this.k, currentUserLocal.open_id, currentUserLocal.token, this.B);
        } else {
            this.A = new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.4
                @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
                public void onIdChanged(String str, String str2) {
                    com.ss.union.game.sdk.core.vapp.b.a.a(VBindAccountActivity.this.k, str, str2, VBindAccountActivity.this.B);
                }
            };
            AppLogManager.getInstance().registerIdUpdateListener(this.A);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        a(i2, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str) {
        Intent intent = new Intent();
        intent.putExtra("status", i2);
        intent.putExtra("msg", str);
        setResult(-1, intent);
        finish();
        if (this.n) {
            VLog.e("V的绑定页面结束，准备销毁进程");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    VLog.e("V的绑定页面结束，开始销毁进程");
                    Process.killProcess(Process.myPid());
                }
            }, 500L);
        }
    }

    private void j() {
        User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
        if (currentUserLocal == null) {
            VLog.d("fail to bindGameUserName,user null");
            return;
        }
        String str = currentUserLocal.login_type;
        if (TextUtils.equals(LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName(), str)) {
            this.q.setText(ResourceUtils.getString("lg_v_visitor") + currentUserLocal.open_id);
            return;
        }
        if (TextUtils.equals(LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), str)) {
            this.q.setText(ResourceUtils.getString("lg_v_phone_user") + a(currentUserLocal.mobile));
            return;
        }
        if (TextUtils.equals(LGAccountConstant.LoginType.LOGIN_TYPE_DY.getName(), str)) {
            this.q.setText(ResourceUtils.getString("lg_v_douyin") + currentUserLocal.nick_name);
            return;
        }
        if (TextUtils.equals(LGAccountConstant.LoginType.LOGIN_TYPE_TT.getName(), str)) {
            this.q.setText(ResourceUtils.getString("lg_v_toutiao") + currentUserLocal.nick_name);
        }
    }

    private String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.length() > 3 ? str.substring(str.length() - 3) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i2, final String str, @Nullable JSONObject jSONObject) throws Resources.NotFoundException {
        a(this.w, this.x);
        try {
            this.y.setText(com.ss.union.game.sdk.core.vapp.a.a.a(jSONObject).b);
        } catch (Exception unused) {
        }
        this.z.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.C0093a.b(i2)) {
                    VBindAccountActivity.this.a(VBindAccountActivity.d, str);
                } else {
                    VBindAccountActivity.this.a(VBindAccountActivity.c, str);
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void a(final View view, View view2) throws Resources.NotFoundException {
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, ResourceUtils.getAnimIdByName("lg_anim_slide_out_left"));
        animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.7
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(8);
            }
        });
        view2.setVisibility(0);
        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this, ResourceUtils.getAnimIdByName("lg_animator_slide_in_right"));
        animationLoadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.ss.union.game.sdk.core.vapp.VBindAccountActivity.8
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation(animationLoadAnimation);
        view2.startAnimation(animationLoadAnimation2);
    }

    protected void a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            a(e);
            return;
        }
        this.k = extras.getString(g, "");
        this.l = extras.getString(h, "");
        this.m = extras.getBoolean(g, false);
        this.n = extras.getBoolean(j, false);
    }

    @Override // com.ss.union.game.sdk.core.vapp.a
    protected void onDestroy() {
        super.onDestroy();
        AppLogManager.getInstance().unRegisterIdUpdateListener(this.A);
    }
}
