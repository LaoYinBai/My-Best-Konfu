package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import com.kwad.sdk.collector.AppStatusRules;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DisplayResourceTypeDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class k extends a<com.mbridge.msdk.foundation.entity.f> {
    private static final String a = "com.mbridge.msdk.foundation.db.k";
    private static k b;

    private k(h hVar) {
        super(hVar);
    }

    public static k a(h hVar) {
        if (b == null) {
            synchronized (k.class) {
                if (b == null) {
                    b = new k(hVar);
                }
            }
        }
        return b;
    }

    public final synchronized void a(com.mbridge.msdk.foundation.entity.f fVar) {
        ContentValues contentValues;
        try {
            contentValues = new ContentValues();
            contentValues.put("campaign_id", Long.valueOf(fVar.a));
            contentValues.put("click_time", Long.valueOf(fVar.b));
            contentValues.put("is_click", Integer.valueOf(fVar.d));
            contentValues.put("resource_type", Integer.valueOf(fVar.c));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().insert("display_resource_type", null, contentValues);
    }

    public final String a() throws JSONException {
        boolean z;
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<com.mbridge.msdk.foundation.entity.f> listA = a(jCurrentTimeMillis);
        if (listA == null) {
            return "";
        }
        long j = AppStatusRules.DEFAULT_START_TIME;
        String[] strArr = {"1", "2", PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START, PushConstants.PUSH_TYPE_WITHDRAW_NOTIFICATION, CampaignEx.CLICKMODE_ON, "6", "7"};
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList(7);
        for (int i = 0; i < 7; i++) {
            arrayList.add(new JSONArray());
        }
        try {
            for (com.mbridge.msdk.foundation.entity.f fVar : listA) {
                JSONArray jSONArray = (JSONArray) arrayList.get((int) ((jCurrentTimeMillis - fVar.b) / j));
                if (jSONArray.length() == 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(String.valueOf(fVar.c), fVar.d);
                    jSONArray.put(jSONObject2);
                } else {
                    int i2 = 0;
                    while (true) {
                        z = true;
                        if (i2 >= jSONArray.length()) {
                            break;
                        }
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                        String strValueOf = String.valueOf(fVar.c);
                        if (jSONObject3.has(strValueOf)) {
                            if (jSONObject3.getInt(strValueOf) == 0 && fVar.d == 1) {
                                jSONObject3.put(strValueOf, fVar.d);
                            }
                            z = false;
                        } else {
                            i2++;
                        }
                    }
                    if (z) {
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put(String.valueOf(fVar.c), fVar.d);
                        jSONArray.put(jSONObject4);
                    }
                }
                j = AppStatusRules.DEFAULT_START_TIME;
            }
            for (int i3 = 0; i3 < 7; i3++) {
                jSONObject.put(strArr[i3], arrayList.get(i3));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007a A[Catch: all -> 0x0094, PHI: r10 r11
  0x007a: PHI (r10v10 android.database.Cursor) = (r10v9 android.database.Cursor), (r10v11 android.database.Cursor) binds: [B:30:0x0088, B:22:0x0078] A[DONT_GENERATE, DONT_INLINE]
  0x007a: PHI (r11v7 java.util.ArrayList) = (r11v5 java.util.ArrayList), (r11v13 java.util.ArrayList) binds: [B:30:0x0088, B:22:0x0078] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0005, B:23:0x007a, B:36:0x0090, B:37:0x0093), top: B:43:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0090 A[Catch: all -> 0x0094, TRY_ENTER, TryCatch #1 {, blocks: (B:4:0x0005, B:23:0x007a, B:36:0x0090, B:37:0x0093), top: B:43:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized java.util.List<com.mbridge.msdk.foundation.entity.f> a(long r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = 604800000(0x240c8400, double:2.988109026E-315)
            long r10 = r10 - r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L94
            r0.<init>()     // Catch: java.lang.Throwable -> L94
            java.lang.String r1 = "select * from display_resource_type where click_time >= "
            r0.append(r1)     // Catch: java.lang.Throwable -> L94
            r0.append(r10)     // Catch: java.lang.Throwable -> L94
            java.lang.String r10 = r0.toString()     // Catch: java.lang.Throwable -> L94
            android.database.sqlite.SQLiteDatabase r11 = r9.getReadableDatabase()     // Catch: java.lang.Throwable -> L94
            r0 = 0
            if (r11 != 0) goto L1f
            monitor-exit(r9)
            return r0
        L1f:
            android.database.sqlite.SQLiteDatabase r11 = r9.getReadableDatabase()     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            android.database.Cursor r10 = r11.rawQuery(r10, r0)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            if (r10 == 0) goto L77
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Exception -> L72 java.lang.Throwable -> L8d
            if (r11 == 0) goto L77
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch: java.lang.Exception -> L72 java.lang.Throwable -> L8d
            int r1 = r10.getCount()     // Catch: java.lang.Exception -> L72 java.lang.Throwable -> L8d
            r11.<init>(r1)     // Catch: java.lang.Exception -> L72 java.lang.Throwable -> L8d
        L38:
            java.lang.String r0 = "click_time"
            int r0 = r10.getColumnIndex(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            long r4 = r10.getLong(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r0 = "campaign_id"
            int r0 = r10.getColumnIndex(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            long r2 = r10.getLong(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r0 = "resource_type"
            int r0 = r10.getColumnIndex(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            int r6 = r10.getInt(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r0 = "is_click"
            int r0 = r10.getColumnIndex(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            int r7 = r10.getInt(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            com.mbridge.msdk.foundation.entity.f r0 = new com.mbridge.msdk.foundation.entity.f     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            r1 = r0
            r1.<init>(r2, r4, r6, r7)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            r11.add(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            boolean r0 = r10.moveToNext()     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            if (r0 != 0) goto L38
            goto L78
        L70:
            r0 = move-exception
            goto L85
        L72:
            r11 = move-exception
            r8 = r0
            r0 = r11
            r11 = r8
            goto L85
        L77:
            r11 = r0
        L78:
            if (r10 == 0) goto L8b
        L7a:
            r10.close()     // Catch: java.lang.Throwable -> L94
            goto L8b
        L7e:
            r11 = move-exception
            r10 = r0
            goto L8e
        L81:
            r10 = move-exception
            r11 = r0
            r0 = r10
            r10 = r11
        L85:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L8d
            if (r10 == 0) goto L8b
            goto L7a
        L8b:
            monitor-exit(r9)
            return r11
        L8d:
            r11 = move-exception
        L8e:
            if (r10 == 0) goto L93
            r10.close()     // Catch: java.lang.Throwable -> L94
        L93:
            throw r11     // Catch: java.lang.Throwable -> L94
        L94:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.k.a(long):java.util.List");
    }
}
