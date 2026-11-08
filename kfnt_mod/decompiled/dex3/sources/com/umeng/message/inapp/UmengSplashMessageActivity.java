package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.inapp.UImageLoadTask;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.j;
import java.io.File;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengSplashMessageActivity extends Activity {
    private static final String a = "com.umeng.message.inapp.UmengSplashMessageActivity";
    private static int s = 2000;
    private static int t = 1000;
    private Activity b;
    private UImageLoadTask c;
    private ImageView d;
    private ImageView e;
    private TextView f;
    private a i;
    private a j;
    private UInAppMessage k;
    private UInAppHandler l;
    private long q;
    private long r;
    private boolean g = true;
    private boolean h = true;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private UImageLoadTask.ImageLoaderCallback u = new UImageLoadTask.ImageLoaderCallback() { // from class: com.umeng.message.inapp.UmengSplashMessageActivity.1
        @Override // com.umeng.message.inapp.UImageLoadTask.ImageLoaderCallback
        public void onLoadImage(Bitmap[] bitmapArr) {
            if (UmengSplashMessageActivity.this.e()) {
                return;
            }
            if (UmengSplashMessageActivity.this.i != null) {
                UmengSplashMessageActivity.this.i.a();
                UmengSplashMessageActivity.this.i = null;
            }
            try {
                if (bitmapArr.length == 1) {
                    UmengSplashMessageActivity.this.d.setOnClickListener(new View.OnClickListener() { // from class: com.umeng.message.inapp.UmengSplashMessageActivity.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            UmengSplashMessageActivity.this.m = true;
                            if (TextUtils.equals("none", UmengSplashMessageActivity.this.k.action_type)) {
                                return;
                            }
                            UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                            d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, 1, 0, 0, 0, (int) UmengSplashMessageActivity.this.r, 0);
                            UmengSplashMessageActivity.this.f();
                            UmengSplashMessageActivity.this.l.handleInAppMessage(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k, 16);
                            UmengSplashMessageActivity.this.finish();
                        }
                    });
                    UmengSplashMessageActivity.this.e.setVisibility(8);
                    UmengSplashMessageActivity.this.d.setImageBitmap(bitmapArr[0]);
                    UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.d);
                }
                if (bitmapArr.length == 2) {
                    UmengSplashMessageActivity.this.d.setOnClickListener(new View.OnClickListener() { // from class: com.umeng.message.inapp.UmengSplashMessageActivity.1.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            UmengSplashMessageActivity.this.n = true;
                            if (TextUtils.equals("none", UmengSplashMessageActivity.this.k.action_type)) {
                                return;
                            }
                            UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                            d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, 0, 1, UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.o), 0, (int) UmengSplashMessageActivity.this.r, 0);
                            UmengSplashMessageActivity.this.f();
                            UmengSplashMessageActivity.this.l.handleInAppMessage(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k, 16);
                            UmengSplashMessageActivity.this.finish();
                        }
                    });
                    UmengSplashMessageActivity.this.e.setOnClickListener(new View.OnClickListener() { // from class: com.umeng.message.inapp.UmengSplashMessageActivity.1.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            UmengSplashMessageActivity.this.o = true;
                            if (TextUtils.equals("none", UmengSplashMessageActivity.this.k.bottom_action_type)) {
                                return;
                            }
                            UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                            d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, 0, UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.n), 1, 0, (int) UmengSplashMessageActivity.this.r, 0);
                            UmengSplashMessageActivity.this.f();
                            UmengSplashMessageActivity.this.l.handleInAppMessage(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k, 17);
                            UmengSplashMessageActivity.this.finish();
                        }
                    });
                    UmengSplashMessageActivity.this.d.setImageBitmap(bitmapArr[0]);
                    UmengSplashMessageActivity.this.e.setImageBitmap(bitmapArr[1]);
                    UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.d);
                    UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.e);
                }
                UmengSplashMessageActivity.this.q = SystemClock.elapsedRealtime();
                if (!UmengSplashMessageActivity.this.k.display_button) {
                    UmengSplashMessageActivity.this.f.setVisibility(8);
                } else {
                    UmengSplashMessageActivity.this.f.setVisibility(0);
                    UmengSplashMessageActivity.this.f.setOnClickListener(new View.OnClickListener() { // from class: com.umeng.message.inapp.UmengSplashMessageActivity.1.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            UmengSplashMessageActivity.this.r += SystemClock.elapsedRealtime() - UmengSplashMessageActivity.this.q;
                            d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.m), UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.n), UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.o), 1, (int) UmengSplashMessageActivity.this.r, 0);
                            UmengSplashMessageActivity.this.f();
                            UmengSplashMessageActivity.this.finish();
                        }
                    });
                }
                InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k);
                InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, 1);
                InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).h();
                UmengSplashMessageActivity.this.g = false;
                UmengSplashMessageActivity.this.j = UmengSplashMessageActivity.this.new a(UmengSplashMessageActivity.this.k.display_time * 1000, UmengSplashMessageActivity.t);
                UmengSplashMessageActivity.this.j.b();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private IUmengInAppMessageCallback v = new IUmengInAppMessageCallback() { // from class: com.umeng.message.inapp.UmengSplashMessageActivity.2
        @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
        public void onCardMessage(UInAppMessage uInAppMessage) {
        }

        @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
        public void onSplashMessage(UInAppMessage uInAppMessage) {
            UInAppMessage uInAppMessage2;
            String strE = InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).e();
            if (TextUtils.isEmpty(strE)) {
                uInAppMessage2 = null;
            } else {
                try {
                    uInAppMessage2 = new UInAppMessage(new JSONObject(strE));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (uInAppMessage != null) {
                if (uInAppMessage2 != null && !uInAppMessage.msg_id.equals(uInAppMessage2.msg_id)) {
                    InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).a(new File(h.d(UmengSplashMessageActivity.this.b, uInAppMessage2.msg_id)));
                }
                UmengSplashMessageActivity.this.k = uInAppMessage;
            } else if (uInAppMessage2 == null) {
                return;
            } else {
                UmengSplashMessageActivity.this.k = uInAppMessage2;
            }
            if (UmengSplashMessageActivity.this.k.show_type == 1 && !UmengSplashMessageActivity.this.g()) {
                InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, 0);
            }
            if (InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).b(UmengSplashMessageActivity.this.k) && InAppMessageManager.getInstance(UmengSplashMessageActivity.this.b).c(UmengSplashMessageActivity.this.k)) {
                if (UmengSplashMessageActivity.this.k.msg_type == 0) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UmengSplashMessageActivity.a, 2, "SPLASH_A");
                    UmengSplashMessageActivity.this.c = new UImageLoadTask(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k);
                    UmengSplashMessageActivity.this.c.a(UmengSplashMessageActivity.this.u);
                    UmengSplashMessageActivity.this.c.execute(UmengSplashMessageActivity.this.k.image_url);
                }
                if (UmengSplashMessageActivity.this.k.msg_type == 1) {
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(UmengSplashMessageActivity.a, 2, "SPLASH_B");
                    UmengSplashMessageActivity.this.c = new UImageLoadTask(UmengSplashMessageActivity.this.b, UmengSplashMessageActivity.this.k);
                    UmengSplashMessageActivity.this.c.a(UmengSplashMessageActivity.this.u);
                    UmengSplashMessageActivity.this.c.execute(UmengSplashMessageActivity.this.k.image_url, UmengSplashMessageActivity.this.k.bottom_image_url);
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public int a(boolean z) {
        return z ? 1 : 0;
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
    }

    public boolean onCustomPretreatment() {
        return false;
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        if ((getIntent().getFlags() & DownloadExpSwitchCode.FIX_DOWNLOADER_ISDOWNLOADING_SETMULTIPROCESS_DEADLOCK) > 0) {
            finish();
            return;
        }
        if (onCustomPretreatment()) {
            return;
        }
        setRequestedOrientation(1);
        setContentView(c());
        d();
        this.l = InAppMessageManager.getInstance(this.b).getInAppHandler();
        this.i = new a(s, t);
        this.i.b();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
    }

    private View c() {
        FrameLayout frameLayout = new FrameLayout(this.b);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 13.0f);
        this.d = new ImageView(this.b);
        this.d.setLayoutParams(layoutParams);
        this.d.setScaleType(ImageView.ScaleType.FIT_XY);
        linearLayout.addView(this.d);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 0, 3.0f);
        this.e = new ImageView(this.b);
        this.e.setLayoutParams(layoutParams2);
        this.e.setScaleType(ImageView.ScaleType.FIT_XY);
        linearLayout.addView(this.e);
        frameLayout.addView(linearLayout);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 5;
        layoutParams3.rightMargin = j.a(this.b, 30.0f);
        layoutParams3.topMargin = j.a(this.b, 20.0f);
        this.f = new TextView(this.b);
        this.f.setLayoutParams(layoutParams3);
        int iA = j.a(this.b, 6.0f);
        int i = iA / 3;
        this.f.setPadding(iA, i, iA, i);
        this.f.setTextSize(14.0f);
        this.f.setBackgroundColor(Color.parseColor("#80000000"));
        this.f.setTextColor(-1);
        this.f.setVisibility(8);
        frameLayout.addView(this.f);
        return frameLayout;
    }

    private void d() {
        if (InAppMessageManager.a) {
            d.a(this).a(this.v);
        } else if (System.currentTimeMillis() - InAppMessageManager.getInstance(this.b).d() > InAppMessageManager.b) {
            d.a(this).a(this.v);
        } else {
            this.v.onSplashMessage(null);
        }
    }

    @Override // android.app.Activity
    protected final void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.i != null) {
            this.i.d();
        }
        if (this.j != null) {
            this.q = SystemClock.elapsedRealtime();
            this.j.d();
        }
    }

    @Override // android.app.Activity
    protected final void onPause() {
        super.onPause();
        if (this.i != null) {
            this.i.c();
        }
        if (this.j != null) {
            this.r += SystemClock.elapsedRealtime() - this.q;
            this.j.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean e() {
        boolean z;
        z = this.p;
        this.p = true;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void f() {
        if (this.h) {
            this.h = false;
            Intent intent = new Intent();
            intent.setClassName(this.b, InAppMessageManager.getInstance(this).a());
            intent.setFlags(536870912);
            this.b.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(InAppMessageManager.getInstance(this.b).i());
        Calendar calendar2 = Calendar.getInstance();
        return calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(500L);
        view.startAnimation(alphaAnimation);
    }

    class a extends c {
        a(long j, long j2) {
            super(j, j2);
        }

        @Override // com.umeng.message.inapp.c
        public void a(long j) {
            if (UmengSplashMessageActivity.this.g) {
                return;
            }
            UmengSplashMessageActivity.this.f.setVisibility(0);
            UmengSplashMessageActivity.this.f.setText(((int) Math.ceil((j * 1.0d) / UmengSplashMessageActivity.t)) + " " + UmengSplashMessageActivity.this.k.display_name);
        }

        @Override // com.umeng.message.inapp.c
        public void e() {
            if (UmengSplashMessageActivity.this.e() && UmengSplashMessageActivity.this.g) {
                return;
            }
            if (!UmengSplashMessageActivity.this.g) {
                d.a(UmengSplashMessageActivity.this.b).a(UmengSplashMessageActivity.this.k.msg_id, UmengSplashMessageActivity.this.k.msg_type, 1, UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.m), UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.n), UmengSplashMessageActivity.this.a(UmengSplashMessageActivity.this.o), 0, UmengSplashMessageActivity.this.k.display_time * 1000, 0);
            }
            UmengSplashMessageActivity.this.f();
            UmengSplashMessageActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        if (this.i != null) {
            this.i.a();
        }
        if (this.j != null) {
            this.j.a();
        }
        if (this.c != null) {
            this.c.a((UImageLoadTask.ImageLoaderCallback) null);
        }
        this.p = false;
        this.m = false;
        this.n = false;
        this.o = false;
        super.onDestroy();
    }
}
