package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import com.sigmob.sdk.common.Constants;
import com.xiaomi.push.ci;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class cf extends ci.e {
    private String a;

    public cf(String str, ContentValues contentValues, String str2) {
        super(str, contentValues);
        this.a = "MessageInsertJob";
        this.a = str2;
    }

    public static cf a(Context context, String str, hn hnVar) {
        byte[] bArrA = it.a(hnVar);
        if (bArrA == null || bArrA.length <= 0) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 0);
        contentValues.put("messageId", "");
        contentValues.put("messageItemId", hnVar.d());
        contentValues.put("messageItem", bArrA);
        contentValues.put(Constants.APPID, bw.a(context).b());
        contentValues.put("packageName", bw.a(context).m146a());
        contentValues.put("createTimeStamp", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("uploadTimestamp", (Integer) 0);
        return new cf(str, contentValues, "a job build to insert message to db");
    }
}
