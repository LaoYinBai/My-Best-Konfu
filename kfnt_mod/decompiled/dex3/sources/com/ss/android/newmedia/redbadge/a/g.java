package com.ss.android.newmedia.redbadge.a;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g implements com.ss.android.newmedia.redbadge.a {
    @Override // com.ss.android.newmedia.redbadge.a
    public void a(Context context, ComponentName componentName, int i) throws com.ss.android.newmedia.redbadge.d {
        Bundle bundle = new Bundle();
        try {
            bundle.putString(com.umeng.message.common.a.u, context.getPackageName());
            bundle.putString("class", componentName.getClassName());
            bundle.putInt("badgenumber", i);
            if (Build.VERSION.SDK_INT >= 11) {
                context.getContentResolver().call(Uri.parse("content://com.transsion.hilauncher.unreadprovider"), "change_badge", (String) null, bundle);
                return;
            }
            throw new com.ss.android.newmedia.redbadge.d("this device is not support to show badge", new UnsupportedOperationException("this device is not support to show badge"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(e.getMessage(), e);
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> a() {
        return Collections.singletonList("com.transsion.hilauncher");
    }
}
