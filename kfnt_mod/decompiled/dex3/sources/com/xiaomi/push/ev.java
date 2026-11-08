package com.xiaomi.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ev extends ew {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private PendingIntent f330a;
    private int b;

    /* renamed from: b, reason: collision with other field name */
    private Bitmap f331b;
    private int c;

    /* renamed from: c, reason: collision with other field name */
    private CharSequence f332c;

    public ev(Context context, int i, String str) {
        super(context, i, str);
        this.a = DownloadExpSwitchCode.BACK_PARTIAL;
        this.b = DownloadExpSwitchCode.BACK_PARTIAL;
        this.c = DownloadExpSwitchCode.BACK_PARTIAL;
    }

    private Drawable a(int i, int i2, int i3, float f) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null));
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.setIntrinsicWidth(i2);
        shapeDrawable.setIntrinsicHeight(i3);
        return shapeDrawable;
    }

    private void a(RemoteViews remoteViews, int i, int i2, int i3, boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            int iA = a(6.0f);
            remoteViews.setViewPadding(i, iA, 0, iA, 0);
        }
        int i4 = z ? -1 : RoundedDrawable.DEFAULT_BORDER_COLOR;
        remoteViews.setTextColor(i2, i4);
        remoteViews.setTextColor(i3, i4);
    }

    @Override // com.xiaomi.push.ew
    /* renamed from: a */
    public ev setLargeIcon(Bitmap bitmap) {
        if (b() && bitmap != null) {
            if (bitmap.getWidth() != 984 || bitmap.getHeight() < 177 || bitmap.getHeight() > 207) {
                com.xiaomi.channel.commonutils.logger.b.m22a("colorful notification bg image resolution error, must [984*177, 984*207]");
            } else {
                this.f331b = bitmap;
            }
        }
        return this;
    }

    public ev a(CharSequence charSequence, PendingIntent pendingIntent) {
        if (b()) {
            this.f332c = charSequence;
            this.f330a = pendingIntent;
        }
        return this;
    }

    public ev a(String str) {
        if (b() && !TextUtils.isEmpty(str)) {
            try {
                this.b = Color.parseColor(str);
            } catch (Exception unused) {
                com.xiaomi.channel.commonutils.logger.b.m22a("parse colorful notification button bg color error");
            }
        }
        return this;
    }

    @Override // com.xiaomi.push.ew
    /* renamed from: a */
    protected String mo301a() {
        return "notification_colorful";
    }

    @Override // com.xiaomi.push.ew, com.xiaomi.push.eu
    /* renamed from: a */
    public void mo295a() {
        RemoteViews remoteViewsA;
        Bitmap bitmapA;
        boolean zA;
        RemoteViews remoteViewsA2;
        RemoteViews remoteViewsA3;
        Drawable drawableA;
        if (!b()) {
            b();
            return;
        }
        super.mo295a();
        Resources resources = mo301a().getResources();
        String packageName = mo301a().getPackageName();
        int iA = a(resources, "icon", AdDownloadModel.JsonKey.ID, packageName);
        if (this.f333a == null) {
            a(iA);
        } else {
            mo301a().setImageViewBitmap(iA, this.f333a);
        }
        int iA2 = a(resources, DBDefinition.TITLE, AdDownloadModel.JsonKey.ID, packageName);
        int iA3 = a(resources, com.umeng.commonsdk.framework.c.a, AdDownloadModel.JsonKey.ID, packageName);
        mo301a().setTextViewText(iA2, this.f335a);
        mo301a().setTextViewText(iA3, this.b);
        if (!TextUtils.isEmpty(this.f332c)) {
            int iA4 = a(resources, "buttonContainer", AdDownloadModel.JsonKey.ID, packageName);
            int iA5 = a(resources, "button", AdDownloadModel.JsonKey.ID, packageName);
            int iA6 = a(resources, "buttonBg", AdDownloadModel.JsonKey.ID, packageName);
            mo301a().setViewVisibility(iA4, 0);
            mo301a().setTextViewText(iA5, this.f332c);
            mo301a().setOnClickPendingIntent(iA4, this.f330a);
            if (this.b != 16777216) {
                int iA7 = a(70.0f);
                int iA8 = a(29.0f);
                mo301a().setImageViewBitmap(iA6, com.xiaomi.push.service.ac.a(a(this.b, iA7, iA8, iA8 / 2.0f)));
                mo301a().setTextColor(iA5, a(this.b) ? -1 : RoundedDrawable.DEFAULT_BORDER_COLOR);
            }
        }
        int iA9 = a(resources, "bg", AdDownloadModel.JsonKey.ID, packageName);
        int iA10 = a(resources, "container", AdDownloadModel.JsonKey.ID, packageName);
        if (this.a != 16777216) {
            if (l.a(mo301a()) >= 10) {
                remoteViewsA3 = mo301a();
                drawableA = a(this.a, 984, 192, 30.0f);
            } else {
                remoteViewsA3 = mo301a();
                drawableA = a(this.a, 984, 192, 0.0f);
            }
            remoteViewsA3.setImageViewBitmap(iA9, com.xiaomi.push.service.ac.a(drawableA));
            remoteViewsA2 = mo301a();
            zA = a(this.a);
        } else {
            if (this.f331b == null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    mo301a().setViewVisibility(iA, 8);
                    mo301a().setViewVisibility(iA9, 8);
                    try {
                        bh.a((Object) this, "setStyle", t.a(mo301a(), "android.app.Notification$DecoratedCustomViewStyle").getConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception unused) {
                        com.xiaomi.channel.commonutils.logger.b.m22a("load class DecoratedCustomViewStyle failed");
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("miui.customHeight", true);
                addExtras(bundle);
                setCustomContentView(mo301a());
            }
            if (l.a(mo301a()) >= 10) {
                remoteViewsA = mo301a();
                bitmapA = a(this.f331b, 30.0f);
            } else {
                remoteViewsA = mo301a();
                bitmapA = this.f331b;
            }
            remoteViewsA.setImageViewBitmap(iA9, bitmapA);
            if (this.f337a != null && this.c == 16777216) {
                c(this.f337a.get("notification_image_text_color"));
            }
            zA = this.c == 16777216 || !a(this.c);
            remoteViewsA2 = mo301a();
        }
        a(remoteViewsA2, iA10, iA2, iA3, zA);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("miui.customHeight", true);
        addExtras(bundle2);
        setCustomContentView(mo301a());
    }

    @Override // com.xiaomi.push.ew
    /* renamed from: a */
    protected boolean mo296a() {
        if (!l.m548a(mo301a())) {
            return false;
        }
        Resources resources = mo301a().getResources();
        String packageName = mo301a().getPackageName();
        return (a(resources, "icon", AdDownloadModel.JsonKey.ID, packageName) == 0 || a(resources, DBDefinition.TITLE, AdDownloadModel.JsonKey.ID, packageName) == 0 || a(resources, com.umeng.commonsdk.framework.c.a, AdDownloadModel.JsonKey.ID, packageName) == 0) ? false : true;
    }

    public ev b(String str) {
        if (b() && !TextUtils.isEmpty(str)) {
            try {
                this.a = Color.parseColor(str);
            } catch (Exception unused) {
                com.xiaomi.channel.commonutils.logger.b.m22a("parse colorful notification bg color error");
            }
        }
        return this;
    }

    @Override // com.xiaomi.push.ew
    protected String b() {
        return "notification_colorful_copy";
    }

    public ev c(String str) {
        if (b() && !TextUtils.isEmpty(str)) {
            try {
                this.c = Color.parseColor(str);
            } catch (Exception unused) {
                com.xiaomi.channel.commonutils.logger.b.m22a("parse colorful notification image text color error");
            }
        }
        return this;
    }
}
