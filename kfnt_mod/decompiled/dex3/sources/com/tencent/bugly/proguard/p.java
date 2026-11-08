package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class p {
    private static p a;
    private static q b;
    private static boolean c;

    private p(Context context, List<com.tencent.bugly.a> list) {
        b = new q(context, list);
    }

    public static synchronized p a(Context context, List<com.tencent.bugly.a> list) {
        if (a == null) {
            a = new p(context, list);
        }
        return a;
    }

    public static synchronized p a() {
        return a;
    }

    public final long a(String str, ContentValues contentValues, o oVar, boolean z) {
        return a(str, contentValues, (o) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, o oVar, boolean z) {
        return a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    public final int a(String str, String str2, String[] strArr, o oVar, boolean z) {
        return a(str, str2, (String[]) null, (o) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized long a(String str, ContentValues contentValues, o oVar) {
        long j;
        j = 0;
        try {
            try {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null && contentValues != null) {
                        long jReplace = writableDatabase.replace(str, "_id", contentValues);
                        if (jReplace >= 0) {
                            x.c("[Database] insert %s success.", str);
                        } else {
                            x.d("[Database] replace %s error.", str);
                        }
                        j = jReplace;
                    }
                } catch (Throwable th) {
                    if (oVar != null) {
                        Long.valueOf(0L);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                if (!x.a(th2)) {
                    th2.printStackTrace();
                }
                if (oVar != null) {
                }
            }
            if (oVar != null) {
                Long.valueOf(j);
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, o oVar) {
        Cursor cursorQuery;
        cursorQuery = null;
        try {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    cursorQuery = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        } finally {
        }
        return cursorQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int a(String str, String str2, String[] strArr, o oVar) {
        int iDelete;
        try {
            try {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    iDelete = writableDatabase != null ? writableDatabase.delete(str, str2, strArr) : 0;
                } catch (Throwable th) {
                    if (oVar != null) {
                        Integer.valueOf(0);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                if (!x.a(th2)) {
                    th2.printStackTrace();
                }
                if (oVar != null) {
                }
            }
            if (oVar != null) {
                Integer.valueOf(iDelete);
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return iDelete;
    }

    public final boolean a(int i, String str, byte[] bArr, o oVar, boolean z) {
        if (!z) {
            a aVar = new a(4, null);
            aVar.a(i, str, bArr);
            w.a().a(aVar);
            return true;
        }
        return a(i, str, bArr, (o) null);
    }

    public final Map<String, byte[]> a(int i, o oVar, boolean z) {
        return a(i, (o) null);
    }

    public final boolean a(int i, String str, o oVar, boolean z) {
        return a(555, str, (o) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, o oVar) {
        try {
            try {
                r rVar = new r();
                rVar.a = i;
                rVar.f = str;
                rVar.e = System.currentTimeMillis();
                rVar.g = bArr;
                boolean zB = b(rVar);
                if (oVar == null) {
                    return zB;
                }
                Boolean.valueOf(zB);
                return zB;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                if (oVar != null) {
                    Boolean.valueOf(false);
                }
                return false;
            }
        } catch (Throwable th2) {
            if (oVar != null) {
                Boolean.valueOf(false);
            }
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, byte[]> a(int i, o oVar) {
        HashMap map = null;
        try {
            List<r> listC = c(i);
            if (listC == null) {
                return null;
            }
            HashMap map2 = new HashMap();
            try {
                for (r rVar : listC) {
                    byte[] bArr = rVar.g;
                    if (bArr != null) {
                        map2.put(rVar.f, bArr);
                    }
                }
                return map2;
            } catch (Throwable th) {
                th = th;
                map = map2;
                if (x.a(th)) {
                    return map;
                }
                th.printStackTrace();
                return map;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final synchronized boolean a(r rVar) {
        ContentValues contentValuesC;
        try {
            if (rVar == null) {
                return false;
            }
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase == null || (contentValuesC = c(rVar)) == null) {
                    return false;
                }
                long jReplace = writableDatabase.replace("t_lr", "_id", contentValuesC);
                if (jReplace < 0) {
                    return false;
                }
                x.c("[Database] insert %s success.", "t_lr");
                rVar.a = jReplace;
                return true;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    private synchronized boolean b(r rVar) {
        ContentValues contentValuesD;
        try {
            if (rVar == null) {
                return false;
            }
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase == null || (contentValuesD = d(rVar)) == null) {
                    return false;
                }
                long jReplace = writableDatabase.replace("t_pf", "_id", contentValuesD);
                if (jReplace < 0) {
                    return false;
                }
                x.c("[Database] insert %s success.", "t_pf");
                rVar.a = jReplace;
                return true;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad A[Catch: all -> 0x00b6, TRY_LEAVE, TryCatch #1 {all -> 0x00b6, blocks: (B:19:0x003c, B:20:0x0046, B:22:0x004d, B:24:0x0053, B:26:0x0057, B:28:0x006f, B:29:0x0077, B:31:0x0081, B:37:0x00a7, B:39:0x00ad), top: B:53:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b2 A[Catch: all -> 0x00bf, TRY_ENTER, TryCatch #5 {, blocks: (B:3:0x0001, B:16:0x0037, B:33:0x00a1, B:41:0x00b2, B:44:0x00b9, B:45:0x00bc), top: B:56:0x0001 }] */
    /* JADX WARN: Type inference failed for: r11v0, types: [int] */
    /* JADX WARN: Type inference failed for: r11v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.tencent.bugly.proguard.r> a(int r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            com.tencent.bugly.proguard.q r0 = com.tencent.bugly.proguard.p.b     // Catch: java.lang.Throwable -> Lbf
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch: java.lang.Throwable -> Lbf
            r9 = 0
            if (r0 == 0) goto Lbd
            if (r11 < 0) goto L26
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1c java.lang.Throwable -> L21
            java.lang.String r2 = "_tp = "
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L1c java.lang.Throwable -> L21
            r1.append(r11)     // Catch: java.lang.Throwable -> L1c java.lang.Throwable -> L21
            java.lang.String r11 = r1.toString()     // Catch: java.lang.Throwable -> L1c java.lang.Throwable -> L21
            r4 = r11
            goto L27
        L1c:
            r11 = move-exception
            r0 = r11
            r11 = r9
            goto Lb7
        L21:
            r11 = move-exception
            r0 = r11
            r11 = r9
            goto La7
        L26:
            r4 = r9
        L27:
            java.lang.String r2 = "t_lr"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L1c java.lang.Throwable -> L21
            if (r11 != 0) goto L3c
            if (r11 == 0) goto L3a
            r11.close()     // Catch: java.lang.Throwable -> Lbf
        L3a:
            monitor-exit(r10)
            return r9
        L3c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            r1.<init>()     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            r2.<init>()     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
        L46:
            boolean r3 = r11.moveToNext()     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            r4 = 0
            if (r3 == 0) goto L77
            com.tencent.bugly.proguard.r r3 = a(r11)     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            if (r3 == 0) goto L57
            r2.add(r3)     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            goto L46
        L57:
            java.lang.String r3 = "_id"
            int r3 = r11.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L6f java.lang.Throwable -> Lb6
            long r5 = r11.getLong(r3)     // Catch: java.lang.Throwable -> L6f java.lang.Throwable -> Lb6
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch: java.lang.Throwable -> L6f java.lang.Throwable -> Lb6
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch: java.lang.Throwable -> L6f java.lang.Throwable -> Lb6
            r1.append(r5)     // Catch: java.lang.Throwable -> L6f java.lang.Throwable -> Lb6
            goto L46
        L6f:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            com.tencent.bugly.proguard.x.d(r3, r4)     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            goto L46
        L77:
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            int r3 = r1.length()     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            if (r3 <= 0) goto L9f
            r3 = 4
            java.lang.String r1 = r1.substring(r3)     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            java.lang.String r3 = "t_lr"
            int r0 = r0.delete(r3, r1, r9)     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            java.lang.String r1 = "[Database] deleted %s illegal data %d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            java.lang.String r5 = "t_lr"
            r3[r4] = r5     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            r4 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            r3[r4] = r0     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch: java.lang.Throwable -> La6 java.lang.Throwable -> Lb6
        L9f:
            if (r11 == 0) goto La4
            r11.close()     // Catch: java.lang.Throwable -> Lbf
        La4:
            monitor-exit(r10)
            return r2
        La6:
            r0 = move-exception
        La7:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)     // Catch: java.lang.Throwable -> Lb6
            if (r1 != 0) goto Lb0
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lb6
        Lb0:
            if (r11 == 0) goto Lbd
            r11.close()     // Catch: java.lang.Throwable -> Lbf
            goto Lbd
        Lb6:
            r0 = move-exception
        Lb7:
            if (r11 == 0) goto Lbc
            r11.close()     // Catch: java.lang.Throwable -> Lbf
        Lbc:
            throw r0     // Catch: java.lang.Throwable -> Lbf
        Lbd:
            monitor-exit(r10)
            return r9
        Lbf:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(int):java.util.List");
    }

    public final synchronized void a(List<r> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    for (r rVar : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(rVar.a);
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        string = string.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string, null)));
                    } catch (Throwable th) {
                        if (x.a(th)) {
                            return;
                        }
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public final synchronized void b(int i) {
        String str;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    try {
                        str = "_tp = " + i;
                    } catch (Throwable th) {
                        if (x.a(th)) {
                            return;
                        }
                        th.printStackTrace();
                        return;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            } else {
                str = null;
            }
            x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
        }
    }

    private static ContentValues c(r rVar) {
        if (rVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put("_id", Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(rVar.b));
            contentValues.put("_pc", rVar.c);
            contentValues.put("_th", rVar.d);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static r a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            rVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            rVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00bb A[Catch: all -> 0x00bf, TRY_ENTER, TryCatch #5 {all -> 0x00bf, blocks: (B:9:0x0029, B:26:0x009c, B:39:0x00b3, B:44:0x00bb, B:47:0x00c1), top: B:55:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized java.util.List<com.tencent.bugly.proguard.r> c(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            com.tencent.bugly.proguard.q r1 = com.tencent.bugly.proguard.p.b     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La6
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La6
            if (r1 == 0) goto Lb6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La6
            java.lang.String r3 = "_id = "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La6
            r2.append(r12)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La6
            java.lang.String r10 = r2.toString()     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La6
            java.lang.String r3 = "t_pf"
            r4 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r1
            r5 = r10
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La6
            if (r2 != 0) goto L2e
            if (r2 == 0) goto L2c
            r2.close()     // Catch: java.lang.Throwable -> Lbf
        L2c:
            monitor-exit(r11)
            return r0
        L2e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            r3.<init>()     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            r4.<init>()     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
        L38:
            boolean r5 = r2.moveToNext()     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            r6 = 0
            if (r5 == 0) goto L69
            com.tencent.bugly.proguard.r r5 = b(r2)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            if (r5 == 0) goto L49
            r4.add(r5)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            goto L38
        L49:
            java.lang.String r5 = "_tp"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> Lb8
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> Lb8
            java.lang.String r7 = " or _tp"
            r3.append(r7)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> Lb8
            java.lang.String r7 = " = "
            r3.append(r7)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> Lb8
            r3.append(r5)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> Lb8
            goto L38
        L61:
            java.lang.String r5 = "[Database] unknown id."
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            goto L38
        L69:
            int r5 = r3.length()     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            if (r5 <= 0) goto L9a
            java.lang.String r5 = " and _id"
            r3.append(r5)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            java.lang.String r5 = " = "
            r3.append(r5)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            r3.append(r12)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            r12 = 4
            java.lang.String r12 = r10.substring(r12)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            java.lang.String r3 = "t_pf"
            int r12 = r1.delete(r3, r12, r0)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            java.lang.String r1 = "[Database] deleted %s illegal data %d."
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            java.lang.String r5 = "t_pf"
            r3[r6] = r5     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            r5 = 1
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            r3[r5] = r12     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> Lb8
        L9a:
            if (r2 == 0) goto L9f
            r2.close()     // Catch: java.lang.Throwable -> Lbf
        L9f:
            monitor-exit(r11)
            return r4
        La1:
            r12 = move-exception
            goto La8
        La3:
            r12 = move-exception
            r2 = r0
            goto Lb9
        La6:
            r12 = move-exception
            r2 = r0
        La8:
            boolean r1 = com.tencent.bugly.proguard.x.a(r12)     // Catch: java.lang.Throwable -> Lb8
            if (r1 != 0) goto Lb1
            r12.printStackTrace()     // Catch: java.lang.Throwable -> Lb8
        Lb1:
            if (r2 == 0) goto Lb6
            r2.close()     // Catch: java.lang.Throwable -> Lbf
        Lb6:
            monitor-exit(r11)
            return r0
        Lb8:
            r12 = move-exception
        Lb9:
            if (r2 == 0) goto Lc1
            r2.close()     // Catch: java.lang.Throwable -> Lbf
            goto Lc1
        Lbf:
            r12 = move-exception
            goto Lc2
        Lc1:
            throw r12     // Catch: java.lang.Throwable -> Lbf
        Lc2:
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.c(int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean a(int i, String str, o oVar) {
        boolean z;
        String str2;
        z = false;
        try {
            try {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null) {
                        if (z.a(str)) {
                            str2 = "_id = " + i;
                        } else {
                            str2 = "_id = " + i + " and _tp = \"" + str + "\"";
                        }
                        int iDelete = writableDatabase.delete("t_pf", str2, null);
                        x.c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(iDelete));
                        if (iDelete > 0) {
                            z = true;
                        }
                    }
                } catch (Throwable th) {
                    if (oVar != null) {
                        Boolean.valueOf(false);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                if (!x.a(th2)) {
                    th2.printStackTrace();
                }
                if (oVar != null) {
                }
            }
            if (oVar != null) {
                Boolean.valueOf(z);
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return z;
    }

    private static ContentValues d(r rVar) {
        if (rVar == null || z.a(rVar.f)) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put("_id", Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", rVar.f);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static r b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* compiled from: BUGLY */
    class a extends Thread {
        private int a;
        private o b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;

        public a(int i, o oVar) {
            this.a = i;
            this.b = oVar;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.e = z;
            this.c = str;
            this.f = strArr;
            this.g = str2;
            this.h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        public final void a(int i, String str, byte[] bArr) {
            this.o = i;
            this.p = str;
            this.q = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.a) {
                case 1:
                    p.this.a(this.c, this.d, this.b);
                    break;
                case 2:
                    p.this.a(this.c, this.m, this.n, this.b);
                    break;
                case 3:
                    p.this.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    break;
                case 4:
                    p.this.a(this.o, this.p, this.q, this.b);
                    break;
                case 5:
                    p.this.a(this.o, this.b);
                    break;
                case 6:
                    p.this.a(this.o, this.p, this.b);
                    break;
            }
        }
    }
}
