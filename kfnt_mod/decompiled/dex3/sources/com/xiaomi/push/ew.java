package com.xiaomi.push;

import android.app.Notification;
import android.app.PendingIntent;
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
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ew extends eu {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    protected Bitmap f333a;

    /* renamed from: a, reason: collision with other field name */
    private RemoteViews f334a;

    /* renamed from: a, reason: collision with other field name */
    protected CharSequence f335a;

    /* renamed from: a, reason: collision with other field name */
    private String f336a;

    /* renamed from: a, reason: collision with other field name */
    protected Map<String, String> f337a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f338a;
    protected CharSequence b;

    /* renamed from: b, reason: collision with other field name */
    private boolean f339b;

    public ew(Context context, int i, String str) {
        super(context);
        this.f336a = str;
        this.a = i;
        m297c();
    }

    public ew(Context context, String str) {
        super(context);
        this.f336a = str;
        m297c();
    }

    private Bitmap a() {
        return com.xiaomi.push.service.ac.a(g.m344a(a(), this.f336a));
    }

    private String c() {
        this.f339b = e();
        return this.f339b ? b() : mo301a();
    }

    /* renamed from: c, reason: collision with other method in class */
    private void m297c() {
        int iA = a(a().getResources(), c(), "layout", a().getPackageName());
        if (iA == 0) {
            com.xiaomi.channel.commonutils.logger.b.m22a("create RemoteViews failed, no such layout resource was found");
        } else {
            this.f334a = new RemoteViews(a().getPackageName(), iA);
            this.f338a = mo296a();
        }
    }

    /* renamed from: c, reason: collision with other method in class */
    private boolean m298c() {
        return this.f337a != null && Boolean.parseBoolean(this.f337a.get("custom_builder_set_title"));
    }

    private void d() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.setContentTitle(this.f335a);
            super.setContentText(this.b);
        }
    }

    /* renamed from: d, reason: collision with other method in class */
    private boolean m299d() {
        return (TextUtils.isEmpty(b()) || TextUtils.isEmpty(this.f336a)) ? false : true;
    }

    private boolean e() {
        return m299d() && f();
    }

    private boolean f() {
        List<StatusBarNotification> listM601b;
        if (Build.VERSION.SDK_INT >= 20 && (listM601b = com.xiaomi.push.service.ao.a(a(), this.f336a).m601b()) != null && !listM601b.isEmpty()) {
            for (StatusBarNotification statusBarNotification : listM601b) {
                if (statusBarNotification.getId() == this.a) {
                    if (statusBarNotification.getNotification() == null) {
                        return false;
                    }
                    return !r0.extras.getBoolean("mipush.customCopyLayout", true);
                }
            }
        }
        return false;
    }

    protected int a(float f) {
        return (int) ((f * a().getResources().getDisplayMetrics().density) + 0.5f);
    }

    protected Bitmap a(Bitmap bitmap, float f) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(new RectF(rect), f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    /* renamed from: a, reason: collision with other method in class */
    protected final RemoteViews m300a() {
        return this.f334a;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ew addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ew addAction(Notification.Action action) {
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ew setLargeIcon(Bitmap bitmap) {
        this.f333a = bitmap;
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ew setContentTitle(CharSequence charSequence) {
        this.f335a = charSequence;
        return this;
    }

    public ew a(Map<String, String> map) {
        this.f337a = map;
        return this;
    }

    /* renamed from: a, reason: collision with other method in class */
    protected abstract String mo301a();

    @Override // com.xiaomi.push.eu
    /* renamed from: a */
    protected void mo295a() {
        super.mo295a();
        Bundle bundle = new Bundle();
        if (m299d()) {
            bundle.putBoolean("mipush.customCopyLayout", this.f339b);
        } else {
            bundle.putBoolean("mipush.customCopyLayout", false);
        }
        bundle.putBoolean("miui.customHeight", false);
        addExtras(bundle);
        if (m298c() || !com.xiaomi.push.service.ap.m602a(a().getContentResolver())) {
            d();
        }
    }

    protected void a(int i) {
        Bitmap bitmapA = a();
        if (bitmapA != null) {
            m300a().setImageViewBitmap(i, bitmapA);
            return;
        }
        int iB = g.b(a(), this.f336a);
        if (iB != 0) {
            m300a().setImageViewResource(i, iB);
        }
    }

    /* renamed from: a */
    protected abstract boolean mo296a();

    /* renamed from: a, reason: collision with other method in class */
    protected final boolean m302a(int i) {
        return ((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d) < 192.0d;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ew setContentText(CharSequence charSequence) {
        this.b = charSequence;
        return this;
    }

    protected abstract String b();

    /* renamed from: b, reason: collision with other method in class */
    protected final void m303b() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.setContentTitle(this.f335a);
            super.setContentText(this.b);
            if (this.f333a != null) {
                super.setLargeIcon(this.f333a);
            }
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    protected final boolean m304b() {
        return this.f338a;
    }
}
