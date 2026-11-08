package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.mbridge.msdk.click.CommonJumpLoader;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.tools.x;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;

/* compiled from: CampaignClickDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends a<CommonJumpLoader.JumpLoaderResult> {
    public static final String a = "c";
    private static c b;
    private com.mbridge.msdk.b.a c;
    private int d;

    private c(h hVar) {
        super(hVar);
        this.d = 100;
    }

    public static c a(h hVar) {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c(hVar);
                }
            }
        }
        return b;
    }

    public final synchronized void a() {
        try {
            getWritableDatabase().delete("campaignclick", "(pts not " + ((Object) null) + " AND (  ( " + DomainCampaignEx.JSON_KEY_TTC_TYPE + " = 2 AND (  ( cps = 1 AND (" + System.currentTimeMillis() + " - pts) > cpti )  OR  (cps = 0 AND (" + System.currentTimeMillis() + " - pts) > cpei )  )  ) OR ( " + DomainCampaignEx.JSON_KEY_TTC_TYPE + " = 1 AND  ( " + System.currentTimeMillis() + " - pts )  > " + DomainCampaignEx.JSON_KEY_TTC_CT2 + " ) ) ) OR ( pts is " + ((Object) null) + " AND  ( " + System.currentTimeMillis() + " - ts) > cti)", null);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d(a, e.getMessage());
        }
    }

    public final synchronized long a(CampaignEx campaignEx, String str, boolean z, int i, int i2) {
        if (campaignEx == null) {
            return 0L;
        }
        try {
            String strA = x.a(campaignEx.getJumpResult());
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", campaignEx.getId());
            contentValues.put("unitid", str);
            contentValues.put("result", strA);
            contentValues.put("cpti", Integer.valueOf(campaignEx.getPreClickInterval() * 1000));
            contentValues.put("cti", Integer.valueOf(campaignEx.getClickInterval() * 1000));
            contentValues.put(CampaignEx.JSON_KEY_PACKAGE_NAME, campaignEx.getPackageName());
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (z) {
                contentValues.put("pts", Long.valueOf(System.currentTimeMillis()));
                if (i == 0 || i == 1) {
                    contentValues.put("cps", Integer.valueOf(i));
                }
                if (aVarB != null && aVarB.w() > 0) {
                    contentValues.put("n4", Integer.valueOf(aVarB.w() * 1000));
                }
                contentValues.put(DomainCampaignEx.JSON_KEY_TTC_CT2, Integer.valueOf(campaignEx.getTtc_ct2() * 1000));
                contentValues.put(DomainCampaignEx.JSON_KEY_TTC_TYPE, Integer.valueOf(i2));
            } else {
                contentValues.put("ts", Long.valueOf(System.currentTimeMillis()));
            }
            if (aVarB != null && aVarB.T() > 0) {
                contentValues.put("cpei", Integer.valueOf(aVarB.T() * 1000));
            }
            if (aVarB != null && aVarB.U() > 0) {
                contentValues.put("cpoci", Integer.valueOf(aVarB.U() * 1000));
            }
            if (c(campaignEx.getId(), str)) {
                String str2 = "id = " + campaignEx.getId() + " AND unitid = " + str;
                if (getWritableDatabase() == null) {
                    return -1L;
                }
                return getWritableDatabase().update("campaignclick", contentValues, str2, null);
            }
            return getWritableDatabase().insert("campaignclick", null, contentValues);
        } catch (Exception unused) {
            return -1L;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized String a(String str) {
        String str2;
        Cursor cursorRawQuery;
        str2 = "";
        if (this.c == null) {
            this.c = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        }
        if (this.c != null && this.c.y() > 0) {
            this.d = this.c.y();
        }
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id FROM campaignclick WHERE (ttc_type = 2 AND ( (cps = 1 AND unitid = '");
        sb.append(str);
        sb.append("' AND (");
        sb.append("cpti");
        sb.append(" + ");
        sb.append("pts");
        sb.append(") > ");
        sb.append(System.currentTimeMillis());
        sb.append(") OR  (");
        sb.append("cps");
        sb.append(" = ");
        sb.append(0);
        sb.append(" AND (");
        sb.append("pts");
        sb.append(" + ");
        sb.append("cpei");
        sb.append(") > ");
        sb.append(System.currentTimeMillis());
        sb.append(") OR (");
        sb.append("unitid");
        sb.append(" <> '");
        sb.append(str);
        sb.append("' AND (");
        sb.append("pts");
        sb.append(" + ");
        sb.append("cpoci");
        sb.append(") > ");
        sb.append(System.currentTimeMillis());
        sb.append(" AND ");
        sb.append("cps");
        sb.append(" = ");
        sb.append(1);
        sb.append(" ))  ) OR (");
        sb.append(DomainCampaignEx.JSON_KEY_TTC_TYPE);
        sb.append(" = ");
        sb.append(1);
        sb.append(" AND ( (");
        sb.append("unitid");
        sb.append(" = '");
        sb.append(str);
        sb.append("' AND (");
        sb.append(System.currentTimeMillis());
        sb.append(" - ");
        sb.append("pts");
        sb.append(" ) <= ");
        sb.append(DomainCampaignEx.JSON_KEY_TTC_CT2);
        sb.append(" ) OR (");
        sb.append("unitid");
        sb.append(" <> '");
        sb.append(str);
        sb.append("' AND (");
        sb.append(System.currentTimeMillis());
        sb.append(" - ");
        sb.append("pts");
        sb.append(") <= ");
        sb.append("n4");
        sb.append(" ) ) ) ORDER BY ");
        sb.append("pts");
        sb.append(" DESC  LIMIT ");
        sb.append(this.d);
        String string = sb.toString();
        Cursor cursor = null;
        Cursor cursor2 = null;
        try {
            try {
                cursorRawQuery = getReadableDatabase().rawQuery(string, null);
                if (cursorRawQuery != null) {
                    try {
                        if (cursorRawQuery.getCount() > 0) {
                            cursorRawQuery.moveToFirst();
                            for (int i = 0; !cursorRawQuery.isAfterLast() && i < 200; i++) {
                                hashSet.add(cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("id")) + "");
                                cursorRawQuery.moveToNext();
                            }
                        }
                    } catch (Exception unused) {
                        cursor2 = cursorRawQuery;
                        com.mbridge.msdk.foundation.tools.s.d(a, "AvoidRepetition report fail");
                        cursor = cursor2;
                        if (cursor2 != null) {
                            cursor2.close();
                            cursor = cursor2;
                        }
                        return str2;
                    } catch (Throwable th) {
                        th = th;
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        throw th;
                    }
                }
                Iterator it = hashSet.iterator();
                JSONArray jSONArray = new JSONArray();
                while (it.hasNext()) {
                    jSONArray.put(Integer.valueOf((String) it.next()));
                }
                String string2 = jSONArray.toString();
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                str2 = string2;
                cursor = jSONArray;
            } catch (Throwable th2) {
                th = th2;
                cursorRawQuery = cursor;
            }
        } catch (Exception unused2) {
        }
        return str2;
    }

    public final synchronized boolean a(String str, String str2) {
        try {
            Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT id FROM campaignclick WHERE id='" + str + "' AND unitid='" + str2 + "' AND cti + ts > " + System.currentTimeMillis(), null);
            if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                cursorRawQuery.close();
                return true;
            }
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return false;
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d(a, e.getMessage());
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004b A[Catch: all -> 0x0063, PHI: r11
  0x004b: PHI (r11v7 android.database.Cursor) = (r11v6 android.database.Cursor), (r11v9 android.database.Cursor) binds: [B:27:0x0057, B:19:0x0049] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0063, blocks: (B:14:0x0042, B:20:0x004b, B:33:0x005f, B:36:0x0065), top: B:39:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005f A[Catch: all -> 0x0063, TRY_ENTER, TryCatch #0 {all -> 0x0063, blocks: (B:14:0x0042, B:20:0x004b, B:33:0x005f, B:36:0x0065), top: B:39:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized com.mbridge.msdk.click.CommonJumpLoader.JumpLoaderResult b(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.getReadableDatabase()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L52
            java.lang.String r2 = "campaignclick"
            r3 = 0
            java.lang.String r4 = "id=? AND unitid=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L52
            r6 = 0
            r5[r6] = r11     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L52
            r11 = 1
            r5[r11] = r12     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L52
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L52
            if (r11 == 0) goto L49
            int r12 = r11.getCount()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L5c
            if (r12 <= 0) goto L49
            boolean r12 = r11.moveToFirst()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L5c
            if (r12 == 0) goto L49
            java.lang.String r12 = "result"
            int r12 = r11.getColumnIndex(r12)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L5c
            java.lang.String r12 = r11.getString(r12)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L5c
            boolean r1 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L5c
            if (r1 != 0) goto L49
            java.lang.Object r12 = com.mbridge.msdk.foundation.tools.x.a(r12)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L5c
            com.mbridge.msdk.click.CommonJumpLoader$JumpLoaderResult r12 = (com.mbridge.msdk.click.CommonJumpLoader.JumpLoaderResult) r12     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L5c
            if (r11 == 0) goto L45
            r11.close()     // Catch: java.lang.Throwable -> L63
        L45:
            monitor-exit(r10)
            return r12
        L47:
            r12 = move-exception
            goto L54
        L49:
            if (r11 == 0) goto L5a
        L4b:
            r11.close()     // Catch: java.lang.Throwable -> L63
            goto L5a
        L4f:
            r12 = move-exception
            r11 = r0
            goto L5d
        L52:
            r12 = move-exception
            r11 = r0
        L54:
            r12.printStackTrace()     // Catch: java.lang.Throwable -> L5c
            if (r11 == 0) goto L5a
            goto L4b
        L5a:
            monitor-exit(r10)
            return r0
        L5c:
            r12 = move-exception
        L5d:
            if (r11 == 0) goto L65
            r11.close()     // Catch: java.lang.Throwable -> L63
            goto L65
        L63:
            r11 = move-exception
            goto L66
        L65:
            throw r12     // Catch: java.lang.Throwable -> L63
        L66:
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.c.b(java.lang.String, java.lang.String):com.mbridge.msdk.click.CommonJumpLoader$JumpLoaderResult");
    }

    private synchronized boolean c(String str, String str2) {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT id FROM campaignclick WHERE id='" + str + "' AND unitid= '" + str2 + "'", null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
            cursorRawQuery.close();
            return true;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return false;
    }
}
