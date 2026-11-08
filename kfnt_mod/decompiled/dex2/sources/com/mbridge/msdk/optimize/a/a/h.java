package com.mbridge.msdk.optimize.a.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* compiled from: VivoDeviceHelper.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class h {
    private Context b;
    private boolean c = false;
    String a = null;

    public h(Context context) {
        this.b = context;
    }

    public final String a() {
        try {
            Cursor cursorQuery = this.b.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), null, null, null, null);
            if (cursorQuery != null) {
                string = cursorQuery.moveToNext() ? cursorQuery.getString(cursorQuery.getColumnIndex("value")) : null;
                cursorQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return string;
    }
}
