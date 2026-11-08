package com.sigmob.devicehelper.oaId.helpers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i {
    private Context b;
    private boolean c = false;
    String a = null;

    public i(Context context) {
        this.b = context;
    }

    public String a() {
        Cursor cursorQuery = this.b.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), null, null, null, null);
        if (cursorQuery != null) {
            string = cursorQuery.moveToNext() ? cursorQuery.getString(cursorQuery.getColumnIndex("value")) : null;
            cursorQuery.close();
        }
        return string;
    }
}
