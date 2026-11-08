package com.mbridge.msdk.optimize.a.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import com.heytap.mcssdk.mode.CommandMessage;

/* compiled from: MeizuDeviceHelper.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private Context a;

    public c(Context context) {
        this.a = context;
    }

    public final void a(com.mbridge.msdk.optimize.a.b bVar) throws PackageManager.NameNotFoundException {
        try {
            this.a.getPackageManager().getPackageInfo("com.meizu.flyme.openidsdk", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Cursor cursorQuery = this.a.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"oaid"}, null);
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.moveToFirst();
                int columnIndex = cursorQuery.getColumnIndex("value");
                string = columnIndex > 0 ? cursorQuery.getString(columnIndex) : null;
                int columnIndex2 = cursorQuery.getColumnIndex(CommandMessage.CODE);
                if (columnIndex2 > 0) {
                    cursorQuery.getInt(columnIndex2);
                }
                int columnIndex3 = cursorQuery.getColumnIndex("expired");
                if (columnIndex3 > 0) {
                    cursorQuery.getLong(columnIndex3);
                }
            }
            if (bVar != null) {
                bVar.a(string, false);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
