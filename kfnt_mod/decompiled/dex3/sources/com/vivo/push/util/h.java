package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class h implements BaseNotifyLayoutAdapter {
    private Resources a;
    private String b;

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final void init(Context context) {
        this.b = context.getPackageName();
        this.a = context.getResources();
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getNotificationLayout() {
        return this.a.getIdentifier("push_notify", "layout", this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getSuitIconId() {
        Resources resources;
        String str;
        if (i.g) {
            resources = this.a;
            str = "notify_icon_rom30";
        } else if (i.f) {
            resources = this.a;
            str = "notify_icon_rom20";
        } else {
            resources = this.a;
            str = "notify_icon";
        }
        return resources.getIdentifier(str, AdDownloadModel.JsonKey.ID, this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyLayoutAdapter
    public final int getTitleColor() {
        int iIntValue;
        try {
            iIntValue = ((Integer) y.a("com.android.internal.R$color", "vivo_notification_title_text_color")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            iIntValue = 0;
        }
        if (iIntValue > 0) {
            return this.a.getColor(iIntValue);
        }
        if (i.g) {
            return -1;
        }
        if (!i.f) {
            return RoundedDrawable.DEFAULT_BORDER_COLOR;
        }
        if (i.g) {
            return Color.parseColor("#ff999999");
        }
        return -1;
    }
}
