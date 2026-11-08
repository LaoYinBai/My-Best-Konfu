package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import java.util.List;

/* compiled from: BidReplaceCampignDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends a {
    private static volatile b a;

    private b(h hVar) {
        super(hVar);
    }

    public static b a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(i.a(context));
                }
            }
        }
        return a;
    }

    public final void a(String str, List<CampaignEx> list) {
        if (TextUtils.isEmpty(str) || list == null || list.size() <= 0) {
            return;
        }
        try {
            String requestId = list.get(0).getRequestId();
            String encryptPrice = list.get(0).getEncryptPrice();
            String campaignUnitId = list.get(0).getCampaignUnitId();
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("bid_unit_id", campaignUnitId);
            contentValues.put("expect_bid_token", str);
            contentValues.put("expect_encrypt_price", "");
            contentValues.put("real_request_id", requestId);
            contentValues.put("real_encrypt_price", encryptPrice);
            contentValues.put("real_token_rule", (Integer) 1);
            contentValues.put("is_replace", (Integer) 0);
            writableDatabase.insert("bid_replace_campaign", null, contentValues);
            com.mbridge.msdk.foundation.tools.s.d("HBOPTIMIZE", "插入数据到对应关系表 期望 bidToken 值" + str + "+ 用于替换期望Offer 的RequestId " + requestId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final int a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || getWritableDatabase() == null) {
            return 0;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_replace", (Integer) 1);
            return getWritableDatabase().update("bid_replace_campaign", contentValues, "bid_unit_id = '" + str2 + "' AND expect_bid_token = '" + str + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final int a(String str) {
        try {
            String str2 = "real_request_id = '" + str + "' AND is_replace = 1";
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return 0;
            }
            return writableDatabase.delete("bid_replace_campaign", str2, null);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("BidReplaceCampaignDao", e.getLocalizedMessage());
            return 0;
        }
    }

    public final int a() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return 0;
            }
            return writableDatabase.delete("bid_replace_campaign", "is_replace = 0", null);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("BidReplaceCampaignDao", e.getLocalizedMessage());
            return 0;
        }
    }

    public final int a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || getWritableDatabase() == null) {
            return 0;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("expect_encrypt_price", str3);
            return getWritableDatabase().update("bid_replace_campaign", contentValues, "bid_unit_id = '" + str2 + "' AND expect_bid_token = '" + str + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00e6 A[PHI: r1
  0x00e6: PHI (r1v8 android.database.Cursor) = (r1v7 android.database.Cursor), (r1v12 android.database.Cursor) binds: [B:34:0x00e4, B:22:0x00c9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.mbridge.msdk.foundation.entity.d> b(java.lang.String r10, java.lang.String r11) throws java.lang.Throwable {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            if (r1 == 0) goto Lc
            return r0
        Lc:
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 == 0) goto L13
            return r0
        L13:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT * FROM bid_replace_campaign WHERE "
            r1.append(r2)
            java.lang.String r2 = "bid_unit_id"
            r1.append(r2)
            java.lang.String r2 = " = '"
            r1.append(r2)
            r1.append(r10)
            java.lang.String r10 = "' AND "
            r1.append(r10)
            java.lang.String r10 = "real_request_id"
            r1.append(r10)
            java.lang.String r10 = " = '"
            r1.append(r10)
            r1.append(r11)
            java.lang.String r10 = "'"
            r1.append(r10)
            r10 = 0
            java.lang.Object r11 = new java.lang.Object     // Catch: java.lang.Throwable -> Ld4 java.lang.Exception -> Ld8
            r11.<init>()     // Catch: java.lang.Throwable -> Ld4 java.lang.Exception -> Ld8
            monitor-enter(r11)     // Catch: java.lang.Throwable -> Ld4 java.lang.Exception -> Ld8
            android.database.sqlite.SQLiteDatabase r2 = r9.getReadableDatabase()     // Catch: java.lang.Throwable -> Ld1
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Ld1
            android.database.Cursor r1 = r2.rawQuery(r1, r10)     // Catch: java.lang.Throwable -> Ld1
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lcc
            if (r1 == 0) goto Lc9
            int r10 = r1.getCount()     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            if (r10 <= 0) goto Lc9
        L5d:
            boolean r10 = r1.moveToNext()     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            if (r10 == 0) goto Lc9
            com.mbridge.msdk.foundation.entity.d r10 = new com.mbridge.msdk.foundation.entity.d     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.<init>()     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r11 = "bid_unit_id"
            int r11 = r1.getColumnIndex(r11)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r11 = r1.getString(r11)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r2 = "expect_bid_token"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r3 = "expect_encrypt_price"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r4 = "real_request_id"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r4 = r1.getString(r4)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r5 = "real_encrypt_price"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r6 = "real_token_rule"
            int r6 = r1.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            int r6 = r1.getInt(r6)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            java.lang.String r7 = "is_replace"
            int r7 = r1.getColumnIndex(r7)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            int r7 = r1.getInt(r7)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.a(r11)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.b(r2)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.c(r3)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.d(r4)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.e(r5)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.a(r6)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r10.b(r7)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            r0.add(r10)     // Catch: java.lang.Exception -> Lc7 java.lang.Throwable -> Lea
            goto L5d
        Lc7:
            r10 = move-exception
            goto Ldb
        Lc9:
            if (r1 == 0) goto Le9
            goto Le6
        Lcc:
            r10 = move-exception
            r8 = r1
            r1 = r10
            r10 = r8
            goto Ld2
        Ld1:
            r1 = move-exception
        Ld2:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Ld1
            throw r1     // Catch: java.lang.Throwable -> Ld4 java.lang.Exception -> Ld8
        Ld4:
            r11 = move-exception
            r1 = r10
            r10 = r11
            goto Leb
        Ld8:
            r11 = move-exception
            r1 = r10
            r10 = r11
        Ldb:
            java.lang.String r11 = "BidReplaceCampaignDao"
            java.lang.String r10 = r10.getLocalizedMessage()     // Catch: java.lang.Throwable -> Lea
            com.mbridge.msdk.foundation.tools.s.d(r11, r10)     // Catch: java.lang.Throwable -> Lea
            if (r1 == 0) goto Le9
        Le6:
            r1.close()
        Le9:
            return r0
        Lea:
            r10 = move-exception
        Leb:
            if (r1 == 0) goto Lf0
            r1.close()
        Lf0:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.b.b(java.lang.String, java.lang.String):java.util.List");
    }
}
