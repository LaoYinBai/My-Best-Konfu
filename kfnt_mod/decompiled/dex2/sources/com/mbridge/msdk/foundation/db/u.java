package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.tools.z;
import java.net.URLEncoder;

/* compiled from: VideoReportDataDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class u extends a<com.mbridge.msdk.foundation.entity.q> {
    private static final String a = "com.mbridge.msdk.foundation.db.u";
    private static u b;

    private u(h hVar) {
        super(hVar);
    }

    public static u a(h hVar) {
        if (b == null) {
            synchronized (u.class) {
                if (b == null) {
                    b = new u(hVar);
                }
            }
        }
        return b;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0332 A[Catch: all -> 0x0341, TRY_ENTER, TryCatch #1 {, blocks: (B:4:0x0003, B:100:0x0332, B:104:0x033a, B:105:0x033d, B:82:0x030c, B:92:0x031b), top: B:114:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x033a A[Catch: all -> 0x0341, TryCatch #1 {, blocks: (B:4:0x0003, B:100:0x0332, B:104:0x033a, B:105:0x033d, B:82:0x030c, B:92:0x031b), top: B:114:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.mbridge.msdk.foundation.entity.q> a(java.lang.String r33) {
        /*
            Method dump skipped, instructions count: 836
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.u.a(java.lang.String):java.util.List");
    }

    public final synchronized int a() {
        int i;
        Cursor cursor = null;
        i = 0;
        try {
            try {
                try {
                    Cursor cursorRawQuery = getReadableDatabase().rawQuery("select count(*) from reward_report", null);
                    if (cursorRawQuery != null) {
                        try {
                            if (cursorRawQuery.moveToFirst()) {
                                i = cursorRawQuery.getInt(0);
                            }
                        } catch (Exception e) {
                            e = e;
                            cursor = cursorRawQuery;
                            e.printStackTrace();
                            if (cursor != null) {
                                cursor.close();
                            }
                            return i;
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorRawQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return i;
    }

    public final synchronized long a(com.mbridge.msdk.foundation.entity.q qVar) {
        if (qVar != null) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DomainCampaignEx.LOOPBACK_KEY, qVar.n());
                contentValues.put("networkType", Integer.valueOf(qVar.u()));
                contentValues.put("network_str", qVar.v());
                contentValues.put("isCompleteView", Integer.valueOf(qVar.w()));
                contentValues.put("watchedMillis", Integer.valueOf(qVar.o()));
                contentValues.put("videoLength", Integer.valueOf(qVar.p()));
                if (!TextUtils.isEmpty(qVar.q())) {
                    contentValues.put("offerUrl", qVar.q());
                }
                if (!TextUtils.isEmpty(qVar.r())) {
                    contentValues.put("reason", URLEncoder.encode(qVar.r(), "utf-8"));
                }
                contentValues.put("result", Integer.valueOf(qVar.x()));
                contentValues.put("duration", qVar.s());
                contentValues.put("videoSize", Long.valueOf(qVar.t()));
                contentValues.put(Message.TYPE, qVar.g());
                String strF = qVar.f();
                if (!TextUtils.isEmpty(strF)) {
                    contentValues.put(CampaignEx.ENDCARD_URL, strF);
                }
                String strE = qVar.e();
                if (!TextUtils.isEmpty(strE)) {
                    contentValues.put(CampaignEx.JSON_KEY_VIDEO_URL, strE);
                }
                String strJ = qVar.j();
                if (!TextUtils.isEmpty(strJ)) {
                    contentValues.put("rid", strJ);
                }
                String strK = qVar.k();
                if (!TextUtils.isEmpty(strK)) {
                    contentValues.put("rid_n", strK);
                }
                String strI = qVar.i();
                if (!TextUtils.isEmpty(strI)) {
                    contentValues.put("template_url", strI);
                }
                String strD = qVar.d();
                if (!TextUtils.isEmpty(strD)) {
                    contentValues.put(CampaignEx.JSON_KEY_IMAGE_URL, strD);
                }
                String strH = qVar.h();
                if (!TextUtils.isEmpty(strH)) {
                    contentValues.put("ad_type", URLEncoder.encode(strH, "utf-8"));
                }
                contentValues.put("unitId", qVar.l());
                contentValues.put("campaignId", qVar.m());
                if ("2000039".equals(qVar.n())) {
                    String strF2 = com.mbridge.msdk.foundation.entity.q.f(qVar);
                    if (z.b(strF2)) {
                        contentValues.put("h5_click_data", strF2);
                    }
                }
                String strA = qVar.a();
                if (!TextUtils.isEmpty(strA)) {
                    contentValues.put("resource_type", URLEncoder.encode(strA, "utf-8"));
                }
                String strB = qVar.b();
                if (!TextUtils.isEmpty(strB)) {
                    contentValues.put("device_id", URLEncoder.encode(strB, "utf-8"));
                }
                String strC = qVar.c();
                if (!TextUtils.isEmpty(strC)) {
                    contentValues.put("creative", URLEncoder.encode(strC, "utf-8"));
                }
                return getWritableDatabase().insert("reward_report", null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
                com.mbridge.msdk.foundation.tools.s.d(a, e.getMessage());
            }
        }
        return -1L;
    }
}
