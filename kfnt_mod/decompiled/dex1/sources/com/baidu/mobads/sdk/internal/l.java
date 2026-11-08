package com.baidu.mobads.sdk.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.app.NotificationCompat;
import com.baidu.mobads.sdk.api.CustomNotification;
import com.baidu.mobads.sdk.api.ICommonModuleObj;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l implements ICommonModuleObj {

    /* renamed from: a, reason: collision with root package name */
    private static volatile l f3241a;

    /* renamed from: b, reason: collision with root package name */
    private CustomNotification f3242b = new CustomNotification();

    private l() {
    }

    public static l a() {
        if (f3241a == null) {
            synchronized (l.class) {
                if (f3241a == null) {
                    f3241a = new l();
                }
            }
        }
        return f3241a;
    }

    @Override // com.baidu.mobads.sdk.api.ICommonModuleObj
    public Object createModuleObj(String str, JSONObject jSONObject) {
        if (!ICommonModuleObj.KEY_NOTIFICATION.equals(str) || jSONObject == null) {
            return null;
        }
        return this.f3242b.getCustomNotification((Context) jSONObject.opt("context"), jSONObject.optString("channelId"), jSONObject.optString("ticker"), (Bitmap) jSONObject.opt("icon"), jSONObject.optString(com.alipay.sdk.widget.d.m), jSONObject.optString("content"), jSONObject.optString("status"), jSONObject.optBoolean("autoCancel"), jSONObject.optInt(NotificationCompat.CATEGORY_PROGRESS), jSONObject.optInt("smallIcon"), jSONObject.optString("action"), (PendingIntent) jSONObject.opt("pendingIntent"));
    }
}
