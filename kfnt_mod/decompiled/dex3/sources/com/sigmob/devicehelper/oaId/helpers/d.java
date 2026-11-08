package com.sigmob.devicehelper.oaId.helpers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper;
import com.sigmob.logger.SigmobLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private Context a;

    public d(Context context) {
        this.a = context;
    }

    private String a(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return null;
        }
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("value");
        if (columnIndex > 0) {
            return cursor.getString(columnIndex);
        }
        return null;
    }

    private boolean a() {
        try {
            PackageManager packageManager = this.a.getPackageManager();
            if (packageManager != null) {
                return packageManager.resolveContentProvider("com.meizu.flyme.openidsdk", 0) != null;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void a(DevicesIDsHelper.AppIdsUpdater appIdsUpdater) {
        if (a()) {
            try {
                Cursor cursorQuery = this.a.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"oaid"}, null);
                String strA = a(cursorQuery);
                if (appIdsUpdater != null) {
                    appIdsUpdater.OnIdsAvalid(strA);
                }
                cursorQuery.close();
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
    }
}
