package com.ss.android.newmedia.redbadge.a;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class k implements com.ss.android.newmedia.redbadge.a {
    private static final String[] a = {"_id", "class"};
    private d b;

    public k() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.b = new d();
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public void a(Context context, ComponentName componentName, int i) throws Throwable {
        Cursor cursorQuery;
        if (context == null || componentName == null) {
            return;
        }
        if (i < 0) {
            i = 0;
        }
        if (this.b != null && this.b.a(context)) {
            this.b.a(context, componentName, i);
            return;
        }
        Uri uri = Uri.parse("content://com.sec.badge/apps?notify=true");
        ContentResolver contentResolver = context.getContentResolver();
        try {
            try {
                cursorQuery = contentResolver.query(uri, a, "package=?", new String[]{componentName.getPackageName()}, null);
                if (cursorQuery != null) {
                    try {
                        String className = componentName.getClassName();
                        boolean z = false;
                        while (cursorQuery.moveToNext()) {
                            contentResolver.update(uri, a(componentName, i, false), "_id=?", new String[]{String.valueOf(cursorQuery.getInt(0))});
                            if (className.equals(cursorQuery.getString(cursorQuery.getColumnIndex("class")))) {
                                z = true;
                            }
                        }
                        if (!z) {
                            contentResolver.insert(uri, a(componentName, i, true));
                        }
                    } catch (Throwable th) {
                        th = th;
                        com.ss.android.newmedia.redbadge.c.b.a(cursorQuery);
                        throw th;
                    }
                }
                com.ss.android.newmedia.redbadge.c.b.a(cursorQuery);
            } catch (Throwable th2) {
                th = th2;
                cursorQuery = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private ContentValues a(ComponentName componentName, int i, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(com.umeng.message.common.a.u, componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }
        contentValues.put("badgecount", Integer.valueOf(i));
        return contentValues;
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> a() {
        return Arrays.asList("com.sec.android.app.launcher", "com.sec.android.app.twlauncher");
    }
}
