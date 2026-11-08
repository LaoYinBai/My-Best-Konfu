package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h implements e {
    h() {
    }

    @Override // com.umeng.commonsdk.proguard.e
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        String[] strArr = (String[]) null;
        String str = (String) null;
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), strArr, str, strArr, str);
        if (cursorQuery != null) {
            string = cursorQuery.moveToNext() ? cursorQuery.getString(cursorQuery.getColumnIndex("value")) : null;
            cursorQuery.close();
        }
        return string;
    }
}
