package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.heytap.mcssdk.mode.Message;
import com.kwad.sdk.collector.AppStatusRules;

/* compiled from: FqInfoDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l extends a<com.mbridge.msdk.foundation.entity.h> {
    private static l a;

    private l(h hVar) {
        super(hVar);
    }

    public static l a(h hVar) {
        if (a == null) {
            synchronized (l.class) {
                if (a == null) {
                    a = new l(hVar);
                }
            }
        }
        return a;
    }

    public final synchronized void a(com.mbridge.msdk.foundation.entity.h hVar) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getWritableDatabase() == null) {
            return;
        }
        if (hVar != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", hVar.a());
            contentValues.put("time", Long.valueOf(hVar.d()));
            contentValues.put("unitId", hVar.b());
            contentValues.put(Message.TYPE, Integer.valueOf(hVar.c()));
            if (a(hVar.b(), hVar.a())) {
                getWritableDatabase().update("fq_info", contentValues, "id = " + hVar.a() + " AND unitId = " + hVar.b(), null);
            } else {
                getWritableDatabase().insert("fq_info", null, contentValues);
            }
        }
    }

    private synchronized boolean a(String str, String str2) {
        try {
            Cursor cursorRawQuery = getReadableDatabase().rawQuery("select id from fq_info where unitId='" + str + "' and id='" + str2 + "'", null);
            if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                cursorRawQuery.close();
                return true;
            }
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final synchronized void a(String str) {
        try {
            String str2 = "time<" + (System.currentTimeMillis() - AppStatusRules.DEFAULT_START_TIME) + " and unitId=? and " + Message.TYPE + " <> " + com.mbridge.msdk.foundation.same.a.D;
            String[] strArr = {str};
            if (getWritableDatabase() != null) {
                getWritableDatabase().delete("fq_info", str2, strArr);
            }
        } catch (Exception unused) {
        }
    }
}
