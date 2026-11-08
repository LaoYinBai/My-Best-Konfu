package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.util.CloseHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class SamsungHomeBadger implements Badger {
    private static final String[] CONTENT_PROJECTION = {"_id", "class"};
    private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";
    private DefaultBadger defaultBadger;

    public SamsungHomeBadger() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.defaultBadger = new DefaultBadger();
        }
    }

    @Override // me.leolin.shortcutbadger.Badger
    public void executeBadge(Context context, ComponentName componentName, int i) throws Throwable {
        Cursor cursorQuery;
        if (this.defaultBadger != null && this.defaultBadger.isSupported(context)) {
            this.defaultBadger.executeBadge(context, componentName, i);
            return;
        }
        Uri uri = Uri.parse(CONTENT_URI);
        ContentResolver contentResolver = context.getContentResolver();
        try {
            cursorQuery = contentResolver.query(uri, CONTENT_PROJECTION, "package=?", new String[]{componentName.getPackageName()}, null);
            if (cursorQuery != null) {
                try {
                    String className = componentName.getClassName();
                    boolean z = false;
                    while (cursorQuery.moveToNext()) {
                        contentResolver.update(uri, getContentValues(componentName, i, false), "_id=?", new String[]{String.valueOf(cursorQuery.getInt(0))});
                        if (className.equals(cursorQuery.getString(cursorQuery.getColumnIndex("class")))) {
                            z = true;
                        }
                    }
                    if (!z) {
                        contentResolver.insert(uri, getContentValues(componentName, i, true));
                    }
                } catch (Throwable th) {
                    th = th;
                    CloseHelper.close(cursorQuery);
                    throw th;
                }
            }
            CloseHelper.close(cursorQuery);
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
    }

    private ContentValues getContentValues(ComponentName componentName, int i, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("package", componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }
        contentValues.put("badgecount", Integer.valueOf(i));
        return contentValues;
    }

    @Override // me.leolin.shortcutbadger.Badger
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.sec.android.app.launcher", "com.sec.android.app.twlauncher");
    }
}
