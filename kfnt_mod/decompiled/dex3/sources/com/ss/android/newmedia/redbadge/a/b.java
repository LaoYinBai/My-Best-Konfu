package com.ss.android.newmedia.redbadge.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements com.ss.android.newmedia.redbadge.a {
    @Override // com.ss.android.newmedia.redbadge.a
    public void a(Context context, ComponentName componentName, int i) throws com.ss.android.newmedia.redbadge.d {
        if (context == null || componentName == null) {
            return;
        }
        if (i < 0) {
            i = 0;
        }
        Intent intent = new Intent("com.anddoes.launcher.COUNTER_CHANGED");
        intent.putExtra(com.umeng.message.common.a.u, componentName.getPackageName());
        intent.putExtra(com.ss.union.game.sdk.redemptionCode.a.q, i);
        intent.putExtra("badgecount", i);
        intent.putExtra("class", componentName.getClassName());
        context.sendBroadcast(intent);
        if (com.ss.android.newmedia.redbadge.c.a.a(context, intent)) {
            try {
                context.sendBroadcast(intent);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
            }
        }
        throw new com.ss.android.newmedia.redbadge.d("unable to resolve intent: " + intent.toString());
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> a() {
        return Arrays.asList("com.anddoes.launcher");
    }
}
