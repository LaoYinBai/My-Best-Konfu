package com.xiaomi.push;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class et extends ew {
    private int a;
    private Bitmap b;
    private Bitmap c;

    public et(Context context, String str) {
        super(context, str);
        this.a = DownloadExpSwitchCode.BACK_PARTIAL;
    }

    @Override // com.xiaomi.push.ew
    /* renamed from: a */
    public et setLargeIcon(Bitmap bitmap) {
        if (b() && bitmap != null) {
            if (bitmap.getWidth() != 984 || 184 > bitmap.getHeight() || bitmap.getHeight() > 1678) {
                com.xiaomi.channel.commonutils.logger.b.m22a("colorful notification banner image resolution error, must belong to [984*184, 984*1678]");
            } else {
                this.b = bitmap;
            }
        }
        return this;
    }

    public et a(String str) {
        if (b() && !TextUtils.isEmpty(str)) {
            try {
                this.a = Color.parseColor(str);
            } catch (Exception unused) {
                com.xiaomi.channel.commonutils.logger.b.m22a("parse banner notification image text color error");
            }
        }
        return this;
    }

    @Override // com.xiaomi.push.ew, android.app.Notification.Builder
    /* renamed from: a */
    public ew setLargeIcon(Bitmap bitmap) {
        return this;
    }

    @Override // com.xiaomi.push.ew
    /* renamed from: a */
    protected String mo301a() {
        return "notification_banner";
    }

    @Override // com.xiaomi.push.ew, com.xiaomi.push.eu
    /* renamed from: a, reason: collision with other method in class */
    public void mo295a() {
        RemoteViews remoteViewsA;
        Bitmap bitmapA;
        if (!b() || this.b == null) {
            b();
            return;
        }
        super.mo295a();
        Resources resources = mo301a().getResources();
        String packageName = mo301a().getPackageName();
        int iA = a(resources, "bg", AdDownloadModel.JsonKey.ID, packageName);
        if (l.a(mo301a()) >= 10) {
            remoteViewsA = mo301a();
            bitmapA = a(this.b, 30.0f);
        } else {
            remoteViewsA = mo301a();
            bitmapA = this.b;
        }
        remoteViewsA.setImageViewBitmap(iA, bitmapA);
        int iA2 = a(resources, "icon", AdDownloadModel.JsonKey.ID, packageName);
        if (this.c != null) {
            mo301a().setImageViewBitmap(iA2, this.c);
        } else {
            a(iA2);
        }
        int iA3 = a(resources, DBDefinition.TITLE, AdDownloadModel.JsonKey.ID, packageName);
        mo301a().setTextViewText(iA3, this.f335a);
        if (this.f337a != null && this.a == 16777216) {
            a(this.f337a.get("notification_image_text_color"));
        }
        mo301a().setTextColor(iA3, (this.a == 16777216 || !a(this.a)) ? -1 : RoundedDrawable.DEFAULT_BORDER_COLOR);
        setCustomContentView(mo301a());
        Bundle bundle = new Bundle();
        bundle.putBoolean("miui.customHeight", true);
        addExtras(bundle);
    }

    @Override // com.xiaomi.push.ew
    /* renamed from: a, reason: collision with other method in class */
    protected boolean mo296a() {
        if (!l.m548a(mo301a())) {
            return false;
        }
        Resources resources = mo301a().getResources();
        String packageName = mo301a().getPackageName();
        return (a(mo301a().getResources(), "bg", AdDownloadModel.JsonKey.ID, mo301a().getPackageName()) == 0 || a(resources, "icon", AdDownloadModel.JsonKey.ID, packageName) == 0 || a(resources, DBDefinition.TITLE, AdDownloadModel.JsonKey.ID, packageName) == 0 || l.a(mo301a()) < 9) ? false : true;
    }

    public et b(Bitmap bitmap) {
        if (b() && bitmap != null) {
            this.c = bitmap;
        }
        return this;
    }

    @Override // com.xiaomi.push.ew
    protected String b() {
        return null;
    }
}
