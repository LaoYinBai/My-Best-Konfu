package com.ss.android.newmedia.redbadge.a;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class r implements com.ss.android.newmedia.redbadge.a {
    private final Uri a = Uri.parse("content://com.android.badge/badge");

    @Override // com.ss.android.newmedia.redbadge.a
    @TargetApi(11)
    public void a(Context context, ComponentName componentName, int i) throws com.ss.android.newmedia.redbadge.d {
        if (context == null || componentName == null) {
            return;
        }
        if (i < 0) {
            i = 0;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("app_badge_count", i);
            context.getContentResolver().call(this.a, "setAppBadgeCount", (String) null, bundle);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> a() {
        return Collections.singletonList("com.zui.launcher");
    }
}
