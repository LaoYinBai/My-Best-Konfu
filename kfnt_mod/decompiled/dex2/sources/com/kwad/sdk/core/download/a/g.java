package com.kwad.sdk.core.download.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.kwad.sdk.utils.ae;
import com.kwad.sdk.utils.an;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    public static String a(Context context) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        String str;
        Throwable th2;
        String str2;
        if (!an.b()) {
            return "";
        }
        Cursor cursor = null;
        String strA = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(Uri.parse("content://com.xiaomi.market.provider.DirectMailProvider"), null, null, null, null);
                try {
                    strA = a(cursorQuery);
                } catch (Throwable th3) {
                    cursor = cursorQuery;
                    str2 = "";
                    th2 = th3;
                    try {
                        th2.printStackTrace();
                        try {
                            ae.a(cursor);
                            return str2;
                        } catch (Exception e) {
                            strA = str2;
                            e = e;
                            com.kwad.sdk.core.d.a.c("MiMarketHelper", "getDetailStyle service not found;");
                            com.kwad.sdk.core.d.a.a(e);
                            return strA;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        cursorQuery = cursor;
                        ae.a(cursorQuery);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                com.kwad.sdk.core.d.a.c("MiMarketHelper", "getDetailStyle service not found;");
                com.kwad.sdk.core.d.a.a(e);
                return strA;
            }
            try {
                com.kwad.sdk.core.d.a.c("MiMarketHelper", "detailStyle" + strA);
                ae.a(cursorQuery);
                return strA;
            } catch (Throwable th5) {
                th = th5;
                ae.a(cursorQuery);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            cursorQuery = null;
        }
    }

    private static String a(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            com.kwad.sdk.core.d.a.c("MiMarketHelper", "cursor is null");
            return "";
        }
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("support");
        if (columnIndex > 0 && cursor.getInt(columnIndex) == 0) {
            return "";
        }
        int columnIndex2 = cursor.getColumnIndex("detailStyle");
        String string = columnIndex2 > 0 ? cursor.getString(columnIndex2) : "";
        return string != null ? string.contains("2") ? "2" : string.contains(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START) ? PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START : string.contains("1") ? "1" : "" : "";
    }
}
