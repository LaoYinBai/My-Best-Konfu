package com.mbridge.msdk.nativex.view.mbfullview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.c.b;
import com.mbridge.msdk.foundation.same.c.c;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.nativex.view.mbfullview.BaseView;
import java.lang.ref.WeakReference;

/* compiled from: FullViewManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static volatile a b;
    private WeakReference<Context> a;

    private a(Context context) {
        this.a = new WeakReference<>(context);
    }

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    /* compiled from: FullViewManager.java */
    /* renamed from: com.mbridge.msdk.nativex.view.mbfullview.a$a, reason: collision with other inner class name */
    private class C0124a implements c {
        private WeakReference<MBridgeTopFullView> b;

        @Override // com.mbridge.msdk.foundation.same.c.c
        public final void onFailedLoad(String str, String str2) {
        }

        public C0124a(MBridgeTopFullView mBridgeTopFullView) {
            this.b = new WeakReference<>(mBridgeTopFullView);
        }

        @Override // com.mbridge.msdk.foundation.same.c.c
        public final void onSuccessLoad(Bitmap bitmap, String str) {
            if (this.b == null || this.b.get() == null) {
                return;
            }
            this.b.get().getMBridgeFullViewDisplayIcon().setImageBitmap(a.a(a.this, bitmap, 25));
        }
    }

    public final void a(BaseView.a aVar, CampaignEx campaignEx, BaseView baseView) {
        switch (aVar) {
            case FULL_TOP_VIEW:
                MBridgeTopFullView mBridgeTopFullView = (MBridgeTopFullView) baseView;
                if (mBridgeTopFullView != null) {
                    Context context = this.a.get();
                    if (context != null) {
                        b.a(context).a(campaignEx.getIconUrl(), new C0124a(mBridgeTopFullView));
                    }
                    mBridgeTopFullView.getMBridgeFullViewDisplayTitle().setText(campaignEx.getAppName());
                    mBridgeTopFullView.getMBridgeFullViewDisplayDscription().setText(campaignEx.getAppDesc());
                    mBridgeTopFullView.getMBridgeFullTvInstall().setText(campaignEx.getAdCall());
                    mBridgeTopFullView.getStarLevelLayoutView().setRating((int) campaignEx.getRating());
                    break;
                }
                break;
            case FULL_MIDDLE_VIEW:
                baseView.getMBridgeFullTvInstall().setText(campaignEx.getAdCall());
                break;
        }
    }

    public final void a(boolean z, BaseView baseView, int i) {
        RelativeLayout.LayoutParams layoutParams;
        Context context = this.a.get();
        if (context != null) {
            if (z) {
                layoutParams = new RelativeLayout.LayoutParams((int) (a(z) / 3.0f), w.b(context, 45.0f));
                layoutParams.addRule(11);
                layoutParams.addRule(12);
                layoutParams.bottomMargin = w.b(context, 10.0f);
                layoutParams.rightMargin = w.a(context) && i == 0 ? w.k(context) + w.b(context, 8.0f) : w.b(context, 8.0f);
            } else {
                layoutParams = new RelativeLayout.LayoutParams(-1, w.b(context, 45.0f));
                layoutParams.addRule(12);
            }
            baseView.getmAnimationContent().setLayoutParams(layoutParams);
        }
    }

    private float a(boolean z) {
        try {
            Context context = this.a.get();
            if (context == null) {
                return 0.0f;
            }
            float fI = w.i(context);
            return z ? fI + w.k(context) : fI;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public final void a(final boolean z, boolean z2, final BaseView baseView) {
        int color = Color.parseColor("#ff264870");
        baseView.getmAnimationPlayer().setBackgroundColor(color);
        Context context = this.a.get();
        if (!z) {
            if (baseView.style != BaseView.a.FULL_MIDDLE_VIEW) {
                if (context != null) {
                    baseView.getmAnimationContent().setBackgroundResource(m.a(context, "mbridge_nativex_cta_por_pre", "drawable"));
                    baseView.getmAnimationPlayer().setBackgroundResource(m.a(context, "mbridge_nativex_cta_por_pre", "drawable"));
                    return;
                }
                return;
            }
        } else {
            if (baseView.style == BaseView.a.FULL_TOP_VIEW && context != null) {
                baseView.getmAnimationContent().setBackgroundResource(m.a(context, "mbridge_nativex_fullview_background", "drawable"));
                baseView.getmAnimationPlayer().setBackgroundColor(color);
            }
            if (z2) {
                baseView.getmAnimationPlayer().getBackground().setAlpha(80);
            } else {
                baseView.getmAnimationPlayer().setBackgroundColor(Color.parseColor("#ff4c8fdf"));
                baseView.getmAnimationPlayer().getBackground().setAlpha(200);
            }
        }
        if (z2) {
            new Handler().postDelayed(new Runnable() { // from class: com.mbridge.msdk.nativex.view.mbfullview.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    baseView.getmAnimationPlayer().setBackgroundColor(Color.parseColor("#ff4c8fdf"));
                    baseView.getmAnimationPlayer().getBackground().setAlpha(z ? 200 : 255);
                    a.a(a.this, baseView.getmAnimationPlayer());
                }
            }, 1000L);
        }
    }

    public final void a(View view, BaseView baseView) {
        if (view == null || baseView == null) {
            return;
        }
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        baseView.i.addView(view);
    }

    public final void a(BaseView baseView, boolean z) {
        if (baseView == null || Build.VERSION.SDK_INT < 11) {
            return;
        }
        baseView.setSystemUiVisibility(z ? 0 : Message.MESSAGE_APP);
    }

    static /* synthetic */ Bitmap a(a aVar, Bitmap bitmap, int i) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        bitmap.getWidth();
        float f = i;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    static /* synthetic */ void a(a aVar, View view) {
        if (view == null) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(800L);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(2);
        alphaAnimation.setRepeatMode(1);
        view.startAnimation(alphaAnimation);
    }
}
