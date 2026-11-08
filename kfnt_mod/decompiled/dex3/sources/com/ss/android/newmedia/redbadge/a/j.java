package com.ss.android.newmedia.redbadge.a;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.tachikoma.core.component.input.InputType;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j implements com.ss.android.newmedia.redbadge.a {
    private static int a = -1;
    private int b = Target.SIZE_ORIGINAL;

    @Override // com.ss.android.newmedia.redbadge.a
    @TargetApi(11)
    public void a(Context context, ComponentName componentName, int i) throws com.ss.android.newmedia.redbadge.d {
        if (context == null || componentName == null || this.b == i) {
            return;
        }
        if (this.b <= 99 || i <= 99) {
            this.b = i;
            Intent intent = new Intent("com.oppo.unsettledevent");
            intent.putExtra("pakeageName", componentName.getPackageName());
            intent.putExtra(InputType.NUMBER, i);
            intent.putExtra("upgradeNumber", i);
            if (com.ss.android.newmedia.redbadge.c.a.a(context, intent)) {
                try {
                    context.sendBroadcast(intent);
                    if (i > 0 || Build.VERSION.SDK_INT >= 23) {
                        return;
                    }
                    Intent intent2 = (Intent) intent.clone();
                    intent2.putExtra(InputType.NUMBER, -1);
                    intent2.putExtra("upgradeNumber", -1);
                    context.sendBroadcast(intent2);
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
                }
            }
            Bundle bundle = new Bundle();
            bundle.putInt("app_badge_count", i);
            context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
            if (i > 0 || Build.VERSION.SDK_INT >= 23) {
                return;
            }
            Bundle bundle2 = (Bundle) bundle.clone();
            bundle2.putInt("app_badge_count", -1);
            context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle2);
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> a() {
        return Collections.singletonList("com.oppo.launcher");
    }
}
