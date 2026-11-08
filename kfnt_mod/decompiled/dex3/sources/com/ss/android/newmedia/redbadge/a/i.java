package com.ss.android.newmedia.redbadge.a;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i implements com.ss.android.newmedia.redbadge.a {
    @Override // com.ss.android.newmedia.redbadge.a
    public void a(Context context, ComponentName componentName, int i) throws com.ss.android.newmedia.redbadge.d {
        if (context == null || componentName == null) {
            return;
        }
        if (i < 0) {
            i = 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", componentName.getPackageName() + "/" + componentName.getClassName());
        contentValues.put(com.ss.union.game.sdk.redemptionCode.a.q, Integer.valueOf(i));
        try {
            context.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), contentValues);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> a() {
        return Arrays.asList("com.teslacoilsw.launcher");
    }
}
