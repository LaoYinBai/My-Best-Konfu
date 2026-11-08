package com.kwad.sdk.core.f.kwai;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.heytap.mcssdk.mode.CommandMessage;
import com.kwad.sdk.utils.ae;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private Context a;

    public d(Context context) {
        this.a = context;
    }

    private String a(Cursor cursor) {
        String string;
        string = "";
        if (cursor != null && !cursor.isClosed()) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("value");
            string = columnIndex > 0 ? cursor.getString(columnIndex) : "";
            int columnIndex2 = cursor.getColumnIndex(CommandMessage.CODE);
            if (columnIndex2 > 0) {
                cursor.getInt(columnIndex2);
            }
            int columnIndex3 = cursor.getColumnIndex("expired");
            if (columnIndex3 > 0) {
                cursor.getLong(columnIndex3);
            }
        }
        return string;
    }

    public String a() throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        String str;
        Throwable th2;
        Cursor cursor;
        String strA;
        Cursor cursor2 = null;
        String str2 = null;
        try {
            try {
                cursorQuery = this.a.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"oaid"}, null);
                try {
                    strA = a(cursorQuery);
                } catch (Throwable th3) {
                    cursor = cursorQuery;
                    str = "";
                    th2 = th3;
                }
                try {
                    com.kwad.sdk.core.d.a.c("MeizuDeviceIDHelper", "getOAID oaid:" + strA);
                    ae.a(cursorQuery);
                    return strA;
                } catch (Throwable th4) {
                    th = th4;
                    ae.a(cursorQuery);
                    throw th;
                }
            } catch (Exception e) {
                e = e;
                com.kwad.sdk.core.d.a.c("MeizuDeviceIDHelper", "getOAID service not found;");
                com.kwad.sdk.core.d.a.a(e);
                return str2;
            }
        } catch (Throwable th5) {
            th = th5;
            cursorQuery = null;
        }
    }
}
