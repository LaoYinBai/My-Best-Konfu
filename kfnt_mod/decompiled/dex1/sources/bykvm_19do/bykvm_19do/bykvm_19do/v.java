package bykvm_19do.bykvm_19do.bykvm_19do;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class v {
    private static int d;
    static final HashMap<String, u> e;

    /* renamed from: a, reason: collision with root package name */
    private final i2 f1394a;

    /* renamed from: b, reason: collision with root package name */
    private final j2 f1395b;

    /* renamed from: c, reason: collision with root package name */
    private final a f1396c;

    static class a extends SQLiteOpenHelper {
        a(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                try {
                    sQLiteDatabase.beginTransaction();
                    Iterator<u> it = v.e.values().iterator();
                    while (it.hasNext()) {
                        String strC = it.next().c();
                        if (strC != null) {
                            sQLiteDatabase.execSQL(strC);
                        }
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th) {
                    try {
                        l0.a(th);
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable th2) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Exception e) {
                            l0.a(e);
                        }
                        throw th2;
                    }
                }
            } catch (Exception e2) {
                l0.a(e2);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            onUpgrade(sQLiteDatabase, i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            l0.d("onUpgrade, " + i + ", " + i2, null);
            try {
                try {
                    sQLiteDatabase.beginTransaction();
                    Iterator<u> it = v.e.values().iterator();
                    while (it.hasNext()) {
                        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + it.next().d());
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                    l0.a(e);
                }
            } catch (Throwable th) {
                try {
                    l0.c("", th);
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th2) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e2) {
                        l0.a(e2);
                    }
                    throw th2;
                }
            }
            onCreate(sQLiteDatabase);
        }
    }

    static {
        String str = "SELECT * FROM " + c0.o + " WHERE event_name =?  AND monitor_status" + com.bytedance.bdturing.b.b.f3762b;
        String str2 = "UPDATE " + c0.o + " SET monitor_num =? WHERE event_name =? AND monitor_status =?";
        String str3 = "SELECT * FROM " + c0.o + " WHERE date<? ORDER BY local_time_ms LIMIT ?";
        String str4 = "DELETE FROM " + c0.o + " WHERE local_time_ms <= ?";
        e = new HashMap<>();
        a(new b0());
        a(new w());
        a(new y(null, false, null));
        a(new z());
        a(new d0());
        a(new a0());
        a(new x("", new JSONObject()));
        a(new c0());
    }

    public v(Application application, j2 j2Var, i2 i2Var) {
        this.f1396c = new a(application, "bd_embed_m_log.db", null, 29);
        this.f1395b = j2Var;
        this.f1394a = i2Var;
    }

    private String a(u uVar, String str, int i) {
        return "SELECT * FROM " + uVar.d() + " WHERE " + com.bytedance.b.a.a.d.p + "='" + str + "' ORDER BY local_time_ms LIMIT " + i;
    }

    private void a(a0 a0Var, HashMap<String, Integer> map, boolean z) {
    }

    private String a(u uVar, String str, long j) {
        return "DELETE FROM " + uVar.d() + " WHERE " + com.bytedance.b.a.a.d.p + "='" + str + "' AND local_time_ms<=" + j;
    }

    private String a(long j, int i) {
        return "UPDATE pack SET _fail=" + i + " WHERE local_time_ms=" + j;
    }

    private static void a(u uVar) {
        e.put(uVar.d(), uVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x01b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x008e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0070 A[Catch: all -> 0x019c, Exception -> 0x01a1, TRY_ENTER, TRY_LEAVE, TryCatch #14 {Exception -> 0x01a1, all -> 0x019c, blocks: (B:4:0x003d, B:13:0x0070, B:17:0x007c, B:21:0x0088), top: B:140:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0088 A[Catch: all -> 0x019c, Exception -> 0x01a1, TRY_ENTER, TRY_LEAVE, TryCatch #14 {Exception -> 0x01a1, all -> 0x019c, blocks: (B:4:0x003d, B:13:0x0070, B:17:0x007c, B:21:0x0088), top: B:140:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0130 A[Catch: all -> 0x017b, Exception -> 0x017e, TRY_ENTER, TryCatch #16 {Exception -> 0x017e, all -> 0x017b, blocks: (B:52:0x0116, B:65:0x013c, B:61:0x0130, B:63:0x0134), top: B:136:0x0116 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(org.json.JSONObject r25, bykvm_19do.bykvm_19do.bykvm_19do.z r26, boolean r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.v.a(org.json.JSONObject, bykvm_19do.bykvm_19do.bykvm_19do.z, boolean):boolean");
    }

    private boolean a(z zVar, boolean z) {
        return !zVar.l && z;
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0270 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x025b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:170:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b6 A[Catch: all -> 0x022f, Exception -> 0x0235, TRY_ENTER, TRY_LEAVE, TryCatch #15 {Exception -> 0x0235, all -> 0x022f, blocks: (B:6:0x0076, B:8:0x007d, B:11:0x008e, B:26:0x00ce, B:31:0x00d8, B:37:0x00e5, B:21:0x00b6, B:23:0x00c0, B:25:0x00c7), top: B:157:0x0076 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(org.json.JSONObject r47) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 634
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.v.a(org.json.JSONObject):boolean");
    }

    private int a(u[] uVarArr, int i, SQLiteDatabase sQLiteDatabase, String str, JSONArray[] jSONArrayArr) {
        int i2 = 0;
        while (i2 < i) {
            jSONArrayArr[i2] = null;
            i2++;
        }
        int length = 200;
        while (length > 0 && i2 < uVarArr.length) {
            jSONArrayArr[i2] = a(sQLiteDatabase, uVarArr[i2], str, length);
            length -= jSONArrayArr[i2].length();
            if (length > 0) {
                i2++;
            }
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0063 A[DONT_GENERATE, PHI: r4 r12
  0x0063: PHI (r4v2 long) = (r4v1 long), (r4v5 long) binds: [B:23:0x0061, B:16:0x0056] A[DONT_GENERATE, DONT_INLINE]
  0x0063: PHI (r12v3 android.database.Cursor) = (r12v2 android.database.Cursor), (r12v5 android.database.Cursor) binds: [B:23:0x0061, B:16:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONArray a(android.database.sqlite.SQLiteDatabase r9, bykvm_19do.bykvm_19do.bykvm_19do.u r10, java.lang.String r11, int r12) {
        /*
            r8 = this;
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            r1 = 0
            r3 = 0
            java.lang.String r12 = r8.a(r10, r11, r12)     // Catch: java.lang.Throwable -> L5b
            android.database.Cursor r12 = r9.rawQuery(r12, r3)     // Catch: java.lang.Throwable -> L5b
            r4 = r1
        L11:
            boolean r6 = r12.moveToNext()     // Catch: java.lang.Throwable -> L59
            if (r6 == 0) goto L4b
            r10.a(r12)     // Catch: java.lang.Throwable -> L59
            boolean r6 = bykvm_19do.bykvm_19do.bykvm_19do.l0.f1356a     // Catch: java.lang.Throwable -> L59
            if (r6 == 0) goto L3a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L59
            r6.<init>()     // Catch: java.lang.Throwable -> L59
            java.lang.String r7 = "queryEvnetInner, "
            r6.append(r7)     // Catch: java.lang.Throwable -> L59
            r6.append(r11)     // Catch: java.lang.Throwable -> L59
            java.lang.String r7 = ", "
            r6.append(r7)     // Catch: java.lang.Throwable -> L59
            r6.append(r10)     // Catch: java.lang.Throwable -> L59
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L59
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r6, r3)     // Catch: java.lang.Throwable -> L59
        L3a:
            org.json.JSONObject r6 = r10.f()     // Catch: java.lang.Throwable -> L59
            r0.put(r6)     // Catch: java.lang.Throwable -> L59
            long r6 = r10.f1385a     // Catch: java.lang.Throwable -> L59
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r6 <= 0) goto L11
            long r6 = r10.f1385a     // Catch: java.lang.Throwable -> L59
            r4 = r6
            goto L11
        L4b:
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r1 <= 0) goto L56
            java.lang.String r11 = r8.a(r10, r11, r4)     // Catch: java.lang.Throwable -> L59
            r9.execSQL(r11)     // Catch: java.lang.Throwable -> L59
        L56:
            if (r12 == 0) goto L66
            goto L63
        L59:
            r9 = move-exception
            goto L5e
        L5b:
            r9 = move-exception
            r4 = r1
            r12 = r3
        L5e:
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r9)     // Catch: java.lang.Throwable -> L8f
            if (r12 == 0) goto L66
        L63:
            r12.close()
        L66:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "queryEvent, "
            r9.append(r11)
            r9.append(r10)
            java.lang.String r10 = ", "
            r9.append(r10)
            int r10 = r0.length()
            r9.append(r10)
            java.lang.String r10 = ", "
            r9.append(r10)
            r9.append(r4)
            java.lang.String r9 = r9.toString()
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r9, r3)
            return r0
        L8f:
            r9 = move-exception
            if (r12 == 0) goto L95
            r12.close()
        L95:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.v.a(android.database.sqlite.SQLiteDatabase, bykvm_19do.bykvm_19do.bykvm_19do.u, java.lang.String, int):org.json.JSONArray");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0066 A[DONT_GENERATE, PHI: r5 r7
  0x0066: PHI (r5v2 long) = (r5v1 long), (r5v6 long) binds: [B:23:0x0064, B:16:0x005a] A[DONT_GENERATE, DONT_INLINE]
  0x0066: PHI (r7v2 android.database.Cursor) = (r7v1 android.database.Cursor), (r7v4 android.database.Cursor) binds: [B:23:0x0064, B:16:0x005a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONArray a(bykvm_19do.bykvm_19do.bykvm_19do.z r11, bykvm_19do.bykvm_19do.bykvm_19do.d0 r12, bykvm_19do.bykvm_19do.bykvm_19do.b0 r13, android.database.sqlite.SQLiteDatabase r14) {
        /*
            r10 = this;
            r0 = 1
            java.lang.String[] r1 = new java.lang.String[r0]
            java.lang.String r2 = r11.f1387c
            r3 = 0
            r1[r3] = r2
            org.json.JSONArray r2 = new org.json.JSONArray
            r2.<init>()
            r4 = 0
            r5 = 0
            java.lang.String r7 = "SELECT * FROM page WHERE session_id=? LIMIT 500"
            android.database.Cursor r7 = r14.rawQuery(r7, r1)     // Catch: java.lang.Throwable -> L5f
        L16:
            boolean r8 = r7.moveToNext()     // Catch: java.lang.Throwable -> L5d
            if (r8 == 0) goto L53
            r13.a(r7)     // Catch: java.lang.Throwable -> L5d
            boolean r3 = bykvm_19do.bykvm_19do.bykvm_19do.l0.f1356a     // Catch: java.lang.Throwable -> L5d
            if (r3 == 0) goto L3f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d
            r3.<init>()     // Catch: java.lang.Throwable -> L5d
            java.lang.String r8 = "queryPageInner, "
            r3.append(r8)     // Catch: java.lang.Throwable -> L5d
            r3.append(r1)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r8 = ", "
            r3.append(r8)     // Catch: java.lang.Throwable -> L5d
            r3.append(r13)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L5d
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r3, r4)     // Catch: java.lang.Throwable -> L5d
        L3f:
            boolean r3 = r13.j()     // Catch: java.lang.Throwable -> L5d
            if (r3 == 0) goto L4d
            org.json.JSONObject r3 = r13.f()     // Catch: java.lang.Throwable -> L5d
            r2.put(r3)     // Catch: java.lang.Throwable -> L5d
            goto L51
        L4d:
            long r8 = r13.i     // Catch: java.lang.Throwable -> L5d
            r3 = 0
            long r5 = r5 + r8
        L51:
            r3 = 1
            goto L16
        L53:
            if (r3 == 0) goto L5a
            java.lang.String r13 = "DELETE FROM page WHERE session_id=?"
            r14.execSQL(r13, r1)     // Catch: java.lang.Throwable -> L5d
        L5a:
            if (r7 == 0) goto L69
            goto L66
        L5d:
            r13 = move-exception
            goto L61
        L5f:
            r13 = move-exception
            r7 = r4
        L61:
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r13)     // Catch: java.lang.Throwable -> Lbc
            if (r7 == 0) goto L69
        L66:
            r7.close()
        L69:
            int r13 = r2.length()
            if (r13 <= 0) goto L97
            r13 = 1000(0x3e8, double:4.94E-321)
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 <= 0) goto L76
            goto L77
        L76:
            r5 = r13
        L77:
            r12.i = r5
            java.lang.String r0 = r11.f1387c
            r12.f1387c = r0
            long r0 = r11.f1385a
            r12.f1385a = r0
            long r0 = r11.f1385a
            long r0 = r0 + r5
            long r0 = r0 / r13
            r12.j = r0
            bykvm_19do.bykvm_19do.bykvm_19do.i2 r13 = r10.f1394a
            long r13 = bykvm_19do.bykvm_19do.bykvm_19do.a2.a(r13)
            r12.f1386b = r13
            java.lang.String r13 = r11.e
            r12.e = r13
            java.lang.String r11 = r11.f
            r12.f = r11
        L97:
            boolean r11 = bykvm_19do.bykvm_19do.bykvm_19do.l0.f1356a
            if (r11 == 0) goto Lbb
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r13 = "queryPage, "
            r11.append(r13)
            r11.append(r12)
            java.lang.String r12 = ", "
            r11.append(r12)
            int r12 = r2.length()
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r11, r4)
        Lbb:
            return r2
        Lbc:
            r11 = move-exception
            if (r7 == 0) goto Lc2
            r7.close()
        Lc2:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.v.a(bykvm_19do.bykvm_19do.bykvm_19do.z, bykvm_19do.bykvm_19do.bykvm_19do.d0, bykvm_19do.bykvm_19do.bykvm_19do.b0, android.database.sqlite.SQLiteDatabase):org.json.JSONArray");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003b A[DONT_GENERATE, PHI: r3
  0x003b: PHI (r3v2 android.database.Cursor) = (r3v1 android.database.Cursor), (r3v8 android.database.Cursor) binds: [B:15:0x0039, B:8:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.ArrayList<bykvm_19do.bykvm_19do.bykvm_19do.a0> a() {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.HashMap<java.lang.String, bykvm_19do.bykvm_19do.bykvm_19do.u> r1 = bykvm_19do.bykvm_19do.bykvm_19do.v.e
            java.lang.String r2 = "pack"
            java.lang.Object r1 = r1.get(r2)
            bykvm_19do.bykvm_19do.bykvm_19do.a0 r1 = (bykvm_19do.bykvm_19do.bykvm_19do.a0) r1
            r2 = 0
            bykvm_19do.bykvm_19do.bykvm_19do.v$a r3 = r5.f1396c     // Catch: java.lang.Throwable -> L34
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()     // Catch: java.lang.Throwable -> L34
            java.lang.String r4 = "SELECT * FROM pack ORDER BY local_time_ms DESC,_full DESC LIMIT 2"
            android.database.Cursor r3 = r3.rawQuery(r4, r2)     // Catch: java.lang.Throwable -> L34
        L1c:
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2f
            bykvm_19do.bykvm_19do.bykvm_19do.u r1 = r1.clone()     // Catch: java.lang.Throwable -> L32
            bykvm_19do.bykvm_19do.bykvm_19do.a0 r1 = (bykvm_19do.bykvm_19do.bykvm_19do.a0) r1     // Catch: java.lang.Throwable -> L32
            r1.a(r3)     // Catch: java.lang.Throwable -> L32
            r0.add(r1)     // Catch: java.lang.Throwable -> L32
            goto L1c
        L2f:
            if (r3 == 0) goto L3e
            goto L3b
        L32:
            r1 = move-exception
            goto L36
        L34:
            r1 = move-exception
            r3 = r2
        L36:
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r1)     // Catch: java.lang.Throwable -> L5f
            if (r3 == 0) goto L3e
        L3b:
            r3.close()
        L3e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "queryPack, "
            r1.append(r3)
            int r3 = r0.size()
            r1.append(r3)
            java.lang.String r3 = ", "
            r1.append(r3)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            bykvm_19do.bykvm_19do.bykvm_19do.l0.a(r1, r2)
            return r0
        L5f:
            r0 = move-exception
            if (r3 == 0) goto L65
            r3.close()
        L65:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_19do.v.a():java.util.ArrayList");
    }

    public void a(ArrayList<a0> arrayList, ArrayList<a0> arrayList2) {
        SQLiteDatabase writableDatabase;
        l0.a("setResult, " + arrayList + ", " + arrayList2, null);
        HashMap<String, Integer> map = new HashMap<>();
        HashMap map2 = new HashMap();
        HashMap<String, Integer> map3 = new HashMap<>();
        HashMap<String, Integer> map4 = new HashMap<>();
        HashMap<String, Integer> map5 = new HashMap<>();
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).j == 0) {
                    a(arrayList.get(i), map, true);
                } else {
                    a(arrayList.get(i), map4, false);
                }
            }
        }
        Iterator<a0> it = arrayList2.iterator();
        while (it.hasNext()) {
            try {
                a0 next = it.next();
                if (next.j == 0) {
                    HashMap<String, Integer> map6 = (HashMap) map2.get(Integer.valueOf(next.k));
                    if (map6 == null) {
                        map6 = new HashMap<>();
                        map2.put(Integer.valueOf(next.k), map6);
                    }
                    a(next, map6, false);
                } else if (r8 + 1 > 5) {
                    a(next, map3, false);
                    arrayList.add(next);
                    it.remove();
                } else {
                    a(next, map5, false);
                }
            } catch (Exception e2) {
                l0.a(e2);
                return;
            }
        }
        try {
            writableDatabase = this.f1396c.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                Iterator<a0> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    writableDatabase.execSQL("DELETE FROM pack WHERE local_time_ms=?", new String[]{String.valueOf(it2.next().f1385a)});
                }
                Iterator<a0> it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    a0 next2 = it3.next();
                    long j = next2.f1385a;
                    int i2 = next2.j + 1;
                    next2.j = i2;
                    writableDatabase.execSQL(a(j, i2));
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Throwable th) {
                th = th;
                try {
                    l0.a(th);
                    if (writableDatabase != null) {
                        writableDatabase.endTransaction();
                    }
                    return;
                } catch (Throwable th2) {
                    if (writableDatabase != null) {
                        try {
                            writableDatabase.endTransaction();
                        } catch (Exception e3) {
                            l0.a(e3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            writableDatabase = null;
        }
        if (writableDatabase != null) {
            writableDatabase.endTransaction();
        }
    }

    public void a(ArrayList<u> arrayList) {
        Throwable th;
        SQLiteDatabase writableDatabase;
        l0.a("save, " + arrayList.toString(), null);
        try {
            try {
                writableDatabase = this.f1396c.getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    Iterator<u> it = arrayList.iterator();
                    ContentValues contentValuesB = null;
                    while (it.hasNext()) {
                        u next = it.next();
                        String strD = next.d();
                        contentValuesB = next.b(contentValuesB);
                        writableDatabase.insert(strD, null, contentValuesB);
                    }
                    writableDatabase.setTransactionSuccessful();
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        d += arrayList.size();
                        l0.a(th);
                        if (writableDatabase != null) {
                            writableDatabase.endTransaction();
                        }
                        return;
                    } catch (Throwable th3) {
                        if (writableDatabase != null) {
                            try {
                                writableDatabase.endTransaction();
                            } catch (Throwable th4) {
                                l0.a(th4);
                            }
                        }
                        throw th3;
                    }
                }
            } catch (Throwable th5) {
                l0.a(th5);
                return;
            }
        } catch (Throwable th6) {
            th = th6;
            writableDatabase = null;
        }
        if (writableDatabase != null) {
            writableDatabase.endTransaction();
        }
    }
}
