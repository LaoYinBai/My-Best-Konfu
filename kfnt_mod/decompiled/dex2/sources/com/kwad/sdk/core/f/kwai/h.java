package com.kwad.sdk.core.f.kwai;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.kwad.sdk.utils.ae;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private Context a;

    public h(Context context) {
        this.a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v7, types: [android.database.Cursor] */
    public String a() throws Throwable {
        String str;
        Exception e;
        ?? Query;
        Cursor cursor;
        String str2 = "";
        String str3 = null;
        String str4 = null;
        ?? r1 = 0;
        try {
            try {
                Query = this.a.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), null, null, null, null);
                if (Query != null) {
                    try {
                        try {
                            ?? MoveToNext = Query.moveToNext();
                            str4 = MoveToNext;
                            if (MoveToNext != 0) {
                                String string = Query.getString(Query.getColumnIndex("value"));
                                try {
                                    com.kwad.sdk.core.d.a.c("VivoDeviceIDHelper", "getOAID oaid:" + string);
                                    str2 = string;
                                    str4 = string;
                                } catch (Exception e2) {
                                    e = e2;
                                    cursor = Query;
                                    str = string;
                                    r1 = cursor;
                                    com.kwad.sdk.core.d.a.c("VivoDeviceIDHelper", "getOAID fail");
                                    com.kwad.sdk.core.d.a.b(e);
                                    ae.a((Cursor) r1);
                                    str2 = str;
                                    str3 = r1;
                                    return str2;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            ae.a((Cursor) Query);
                            throw th;
                        }
                    } catch (Exception e3) {
                        cursor = Query;
                        str = "";
                        e = e3;
                    }
                }
                ae.a((Cursor) Query);
                str3 = str4;
            } catch (Throwable th2) {
                th = th2;
                Query = str3;
            }
        } catch (Exception e4) {
            str = "";
            e = e4;
        }
        return str2;
    }
}
