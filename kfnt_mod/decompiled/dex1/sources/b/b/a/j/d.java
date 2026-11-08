package b.b.a.j;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.k.o;
import b.b.a.k.t;
import b.b.a.k.v;
import b.b.a.k.x;
import com.bytedance.sdk.account.platform.api.IAppAuthService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    public static final HashMap<String, b.b.a.j.b> f1115a = new HashMap<>();

    /* renamed from: b, reason: collision with root package name */
    public static final b.b.a.j.b[] f1116b;

    /* renamed from: c, reason: collision with root package name */
    public static final b[] f1117c;
    public final b.b.a.d.b d;
    public final a e;
    public String f;

    public static class a extends SQLiteOpenHelper {
        public a(@Nullable Context context, @Nullable String str, @Nullable SQLiteDatabase.CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.beginTransaction();
                Iterator<b.b.a.j.b> it = d.f1115a.values().iterator();
                while (it.hasNext()) {
                    String strC = it.next().c();
                    if (strC != null) {
                        sQLiteDatabase.execSQL(strC);
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Throwable th) {
                try {
                    v.a(th);
                } finally {
                    x.a(sQLiteDatabase);
                }
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            onUpgrade(sQLiteDatabase, i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            v.a("onUpgrade, " + i + ", " + i2, (Throwable) null);
            try {
                sQLiteDatabase.beginTransaction();
                Iterator<b.b.a.j.b> it = d.f1115a.values().iterator();
                while (it.hasNext()) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + it.next().d());
                }
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                try {
                    x.a(sQLiteDatabase);
                    onCreate(sQLiteDatabase);
                } catch (Throwable th) {
                }
            }
            x.a(sQLiteDatabase);
            onCreate(sQLiteDatabase);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public String f1118a;

        /* renamed from: b, reason: collision with root package name */
        public int f1119b;

        /* renamed from: c, reason: collision with root package name */
        public int f1120c;

        public final void a(b.b.a.j.b bVar) {
            String strH = bVar.h();
            if (strH == null || strH.length() <= this.f1119b) {
                return;
            }
            this.f1118a = bVar.g();
            this.f1119b = strH.length();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.f1120c);
            sb.append("-");
            sb.append(this.f1118a);
            sb.append("-");
            sb.append(this.f1119b);
            return sb.toString();
        }
    }

    static {
        f1115a.put("page", new j());
        f1115a.put("launch", new h());
        f1115a.put("terminate", new m());
        f1115a.put("pack", new i());
        f1116b = new b.b.a.j.b[]{new e(), new g(null, false, null), new f("", new JSONObject())};
        for (b.b.a.j.b bVar : f1116b) {
            a(bVar);
        }
        f1115a.put(IAppAuthService.Scope.PROFILE, new k(null, null));
        f1117c = new b[]{new b(), new b(), new b()};
    }

    public d(b.b.a.d.b bVar, String str) {
        this.e = new a(bVar.f1054c, str, null, 39);
        this.d = bVar;
    }

    public final String a(String str, String str2, boolean z, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(str);
        sb.append(" WHERE ");
        sb.append(com.bytedance.b.a.a.d.p);
        sb.append(z ? "='" : "!='");
        sb.append(str2);
        sb.append("' AND ");
        sb.append("_id");
        sb.append("<=");
        sb.append(j);
        return sb.toString();
    }

    public static void a(b.b.a.j.b bVar) {
        f1115a.put(bVar.d(), bVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00d5 A[PHI: r10 r15
  0x00d5: PHI (r10v2 android.database.Cursor) = (r10v1 android.database.Cursor), (r10v5 android.database.Cursor), (r10v5 android.database.Cursor) binds: [B:45:0x00d3, B:77:0x00d5, B:78:0x00d5] A[DONT_GENERATE, DONT_INLINE]
  0x00d5: PHI (r15v3 long) = (r15v1 long), (r15v5 long), (r15v5 long) binds: [B:45:0x00d3, B:77:0x00d5, B:78:0x00d5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ed A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(int r19, android.database.sqlite.SQLiteDatabase r20, java.lang.String r21, boolean r22, org.json.JSONArray[] r23, long[] r24) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.j.d.a(int, android.database.sqlite.SQLiteDatabase, java.lang.String, boolean, org.json.JSONArray[], long[]):int");
    }

    public final boolean a(String str) {
        StringBuilder sbA = b.a.a.a.a.a("needLaunch, ");
        sbA.append(this.f);
        sbA.append(", ");
        sbA.append(str);
        v.a(sbA.toString());
        if (TextUtils.equals(str, this.f)) {
            return false;
        }
        this.f = str;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.util.ArrayList<b.b.a.j.i> a(org.json.JSONObject r33) {
        /*
            Method dump skipped, instructions count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.j.d.a(org.json.JSONObject):java.util.ArrayList");
    }

    public final JSONObject a(h hVar, JSONObject jSONObject) throws JSONException {
        if (TextUtils.equals(hVar.m, this.d.g.c()) && hVar.l == this.d.g.b()) {
            return jSONObject;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            x.a(jSONObject2, jSONObject);
            jSONObject2.put("app_version", hVar.m);
            jSONObject2.put("version_code", hVar.l);
            return jSONObject2;
        } catch (JSONException e) {
            v.a(e);
            return jSONObject;
        }
    }

    public final void a(JSONObject jSONObject, h hVar, i iVar, SQLiteDatabase sQLiteDatabase, JSONArray[] jSONArrayArr, long[] jArr, ArrayList<i> arrayList, HashMap<String, JSONObject> map) {
        long[] jArr2;
        SQLiteDatabase sQLiteDatabase2;
        i iVar2;
        SQLiteDatabase sQLiteDatabase3;
        i iVar3;
        StringBuilder sbA = b.a.a.a.a.a("packCurrentData, ");
        sbA.append(hVar.e);
        v.a(sbA.toString());
        boolean zA = a(hVar.e);
        int iA = a(0, sQLiteDatabase, hVar.e, true, jSONArrayArr, jArr);
        JSONArray jSONArrayA = a(hVar, map);
        if (zA || a(jArr) || jSONArrayA != null) {
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            iVar2 = iVar;
            iVar.a(jSONObject, zA ? hVar : null, null, null, jSONArrayArr, jArr, jSONArrayA);
            if (jSONArrayA == null && iA >= f1116b.length) {
                i iVar4 = (i) iVar.clone();
                iVar4.i();
                arrayList.add(iVar4);
            } else {
                a(iVar2, true, sQLiteDatabase2, true);
            }
        } else {
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            iVar2 = iVar;
        }
        int iA2 = iA;
        while (iA2 < f1116b.length) {
            i iVar5 = iVar2;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            iA2 = a(iA2, sQLiteDatabase, hVar.e, true, jSONArrayArr, jArr);
            if (a(jArr2)) {
                sQLiteDatabase3 = sQLiteDatabase4;
                iVar3 = iVar5;
                iVar.a(jSONObject, a(hVar.e) ? hVar : null, null, null, jSONArrayArr, jArr, null);
                a(iVar3, true, sQLiteDatabase3, true);
            } else {
                sQLiteDatabase3 = sQLiteDatabase4;
                iVar3 = iVar5;
            }
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase3;
            iVar2 = iVar3;
        }
    }

    public final void a(JSONObject jSONObject, h hVar, i iVar, j jVar, m mVar, SQLiteDatabase sQLiteDatabase, JSONArray[] jSONArrayArr, long[] jArr, HashMap<String, JSONObject> map) {
        SQLiteDatabase sQLiteDatabase2;
        i iVar2;
        boolean z;
        SQLiteDatabase sQLiteDatabase3;
        StringBuilder sbA = b.a.a.a.a.a("packHistoryData, ");
        sbA.append(hVar.e);
        v.a(sbA.toString());
        JSONArray jSONArrayA = a(hVar, true, mVar, jVar, sQLiteDatabase);
        hVar.n = jSONArrayA.length() == 0;
        int iA = a(0, sQLiteDatabase, hVar.e, true, jSONArrayArr, jArr);
        JSONArray jSONArrayA2 = a(hVar, map);
        if (hVar.n) {
            z = true;
            sQLiteDatabase2 = sQLiteDatabase;
            iVar2 = iVar;
            iVar.a(jSONObject, a(hVar.e) ? hVar : null, null, null, jSONArrayArr, jArr, jSONArrayA2);
        } else {
            sQLiteDatabase2 = sQLiteDatabase;
            iVar2 = iVar;
            z = true;
            iVar.a(jSONObject, null, mVar, jSONArrayA, jSONArrayArr, jArr, jSONArrayA2);
        }
        a(iVar2, z, sQLiteDatabase2, z);
        int iA2 = iA;
        while (iA2 < f1116b.length) {
            i iVar3 = iVar2;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            iA2 = a(iA2, sQLiteDatabase, hVar.e, true, jSONArrayArr, jArr);
            if (a(jArr)) {
                sQLiteDatabase3 = sQLiteDatabase4;
                iVar2 = iVar3;
                iVar.a(jSONObject, null, null, null, jSONArrayArr, jArr, null);
                a(iVar2, true, sQLiteDatabase3, true);
            } else {
                sQLiteDatabase3 = sQLiteDatabase4;
                iVar2 = iVar3;
            }
            sQLiteDatabase2 = sQLiteDatabase3;
        }
    }

    public final void a(JSONObject jSONObject, h hVar, m mVar, j jVar, i iVar, SQLiteDatabase sQLiteDatabase, String str, JSONArray[] jSONArrayArr, long[] jArr) {
        long[] jArr2;
        SQLiteDatabase sQLiteDatabase2;
        i iVar2;
        SQLiteDatabase sQLiteDatabase3;
        i iVar3;
        v.a("packLostData, " + str);
        hVar.e = str;
        iVar.e = str;
        JSONArray jSONArrayA = a(hVar, false, mVar, jVar, sQLiteDatabase);
        int iA = a(0, sQLiteDatabase, str, false, jSONArrayArr, jArr);
        hVar.n = jSONArrayA.length() == 0;
        if (a(jArr) || !hVar.n) {
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            iVar2 = iVar;
            iVar.a(jSONObject, null, !hVar.n ? mVar : null, hVar.n ? null : jSONArrayA, jSONArrayArr, jArr, null);
            a(iVar2, false, sQLiteDatabase2, true);
        } else {
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            iVar2 = iVar;
        }
        int iA2 = iA;
        while (iA2 < f1116b.length) {
            i iVar4 = iVar2;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            iA2 = a(iA2, sQLiteDatabase, str, false, jSONArrayArr, jArr);
            if (a(jArr2)) {
                sQLiteDatabase3 = sQLiteDatabase4;
                iVar3 = iVar4;
                iVar.a(jSONObject, null, null, null, jSONArrayArr, jArr, null);
                a(iVar3, false, sQLiteDatabase3, true);
            } else {
                sQLiteDatabase3 = sQLiteDatabase4;
                iVar3 = iVar4;
            }
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase3;
            iVar2 = iVar3;
        }
    }

    public final boolean a(long[] jArr) {
        return jArr[0] > 0 || jArr[1] > 0 || jArr[2] > 0;
    }

    public final void a(SQLiteDatabase sQLiteDatabase, HashMap<String, JSONObject> map) {
        Cursor cursorRawQuery;
        h hVar = (h) f1115a.get("launch");
        try {
            try {
                cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM launch ORDER BY _id LIMIT 5", null);
                while (cursorRawQuery.moveToNext()) {
                    try {
                        hVar.a(cursorRawQuery);
                        JSONObject jSONObject = new JSONObject();
                        try {
                            t.a().a(hVar.f1109b, hVar.e, jSONObject);
                        } catch (Throwable th) {
                            v.a(th);
                        }
                        map.put(hVar.e, jSONObject);
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            v.a(th);
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            return;
                        } catch (Throwable th3) {
                            if (cursorRawQuery != null) {
                                try {
                                    cursorRawQuery.close();
                                } catch (Throwable th4) {
                                    v.a(th4);
                                }
                            }
                            throw th3;
                        }
                    }
                }
                cursorRawQuery.close();
            } catch (Throwable th5) {
                v.a(th5);
            }
        } catch (Throwable th6) {
            th = th6;
            cursorRawQuery = null;
        }
    }

    public final JSONArray a(h hVar, HashMap<String, JSONObject> map) {
        o oVar;
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObject = map.get(hVar.e);
        JSONArray jSONArray = null;
        if (jSONObject != null && ((jSONArrayOptJSONArray = jSONObject.optJSONArray("item_impression")) == null || jSONArrayOptJSONArray.length() != 0)) {
            jSONArray = jSONArrayOptJSONArray;
        }
        o oVar2 = b.b.a.k.k.f1138a;
        if ((oVar2 != null ? oVar2.a() : false) && jSONArray != null && (oVar = b.b.a.k.k.f1138a) != null) {
            oVar.a("item_impression", jSONArray);
        }
        return jSONArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0070 A[Catch: all -> 0x0011, TRY_LEAVE, TryCatch #0 {all -> 0x0011, blocks: (B:6:0x000d, B:13:0x001a, B:15:0x0026, B:17:0x002a, B:21:0x0033, B:25:0x003b, B:26:0x0048, B:30:0x0050, B:31:0x005b, B:35:0x0063, B:37:0x0070), top: B:48:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(b.b.a.j.i r10, boolean r11, android.database.sqlite.SQLiteDatabase r12, boolean r13) {
        /*
            r9 = this;
            if (r12 != 0) goto La
            b.b.a.j.d$a r12 = r9.e
            android.database.sqlite.SQLiteDatabase r12 = r12.getWritableDatabase()
            r0 = 1
            goto Lb
        La:
            r0 = 0
        Lb:
            if (r0 == 0) goto L13
            r12.beginTransaction()     // Catch: java.lang.Throwable -> L11
            goto L13
        L11:
            r10 = move-exception
            goto L76
        L13:
            r1 = 0
            if (r13 == 0) goto L33
            java.lang.String r13 = "pack"
            r3 = 0
            android.content.ContentValues r4 = r10.b(r3)     // Catch: java.lang.Throwable -> L11
            long r4 = r12.insert(r13, r3, r4)     // Catch: java.lang.Throwable -> L11
            int r13 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r13 >= 0) goto L33
            b.b.a.j.h r10 = r10.s     // Catch: java.lang.Throwable -> L11
            if (r10 == 0) goto L2d
            r9.a(r3)     // Catch: java.lang.Throwable -> L11
        L2d:
            if (r0 == 0) goto L32
            b.b.a.k.x.a(r12)
        L32:
            return
        L33:
            long r3 = r10.p     // Catch: java.lang.Throwable -> L11
            int r13 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r13 <= 0) goto L48
            java.lang.String r4 = "event"
            java.lang.String r5 = r10.e     // Catch: java.lang.Throwable -> L11
            long r7 = r10.p     // Catch: java.lang.Throwable -> L11
            r3 = r9
            r6 = r11
            java.lang.String r13 = r3.a(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L11
            r12.execSQL(r13)     // Catch: java.lang.Throwable -> L11
        L48:
            long r7 = r10.r     // Catch: java.lang.Throwable -> L11
            int r13 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r13 <= 0) goto L5b
            java.lang.String r4 = "eventv3"
            java.lang.String r5 = r10.e     // Catch: java.lang.Throwable -> L11
            r3 = r9
            r6 = r11
            java.lang.String r13 = r3.a(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L11
            r12.execSQL(r13)     // Catch: java.lang.Throwable -> L11
        L5b:
            long r5 = r10.x     // Catch: java.lang.Throwable -> L11
            int r13 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r13 <= 0) goto L6e
            java.lang.String r2 = "event_misc"
            java.lang.String r3 = r10.e     // Catch: java.lang.Throwable -> L11
            r1 = r9
            r4 = r11
            java.lang.String r10 = r1.a(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L11
            r12.execSQL(r10)     // Catch: java.lang.Throwable -> L11
        L6e:
            if (r0 == 0) goto L73
            r12.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L11
        L73:
            if (r0 == 0) goto L7e
            goto L7b
        L76:
            b.b.a.k.v.a(r10)     // Catch: java.lang.Throwable -> L7f
            if (r0 == 0) goto L7e
        L7b:
            b.b.a.k.x.a(r12)
        L7e:
            return
        L7f:
            r10 = move-exception
            if (r0 == 0) goto L85
            b.b.a.k.x.a(r12)
        L85:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.j.d.a(b.b.a.j.i, boolean, android.database.sqlite.SQLiteDatabase, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0130 A[PHI: r11 r15 r17 r18
  0x0130: PHI (r11v2 android.database.Cursor) = (r11v3 android.database.Cursor), (r11v11 android.database.Cursor), (r11v11 android.database.Cursor) binds: [B:66:0x012e, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]
  0x0130: PHI (r15v2 long) = (r15v3 long), (r15v5 long), (r15v5 long) binds: [B:66:0x012e, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]
  0x0130: PHI (r17v3 java.lang.String) = (r17v4 java.lang.String), (r17v7 java.lang.String), (r17v7 java.lang.String) binds: [B:66:0x012e, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]
  0x0130: PHI (r18v2 java.lang.String) = (r18v3 java.lang.String), (r18v5 java.lang.String), (r18v5 java.lang.String) binds: [B:66:0x012e, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONArray a(b.b.a.j.h r21, boolean r22, b.b.a.j.m r23, b.b.a.j.j r24, android.database.sqlite.SQLiteDatabase r25) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.j.d.a(b.b.a.j.h, boolean, b.b.a.j.m, b.b.a.j.j, android.database.sqlite.SQLiteDatabase):org.json.JSONArray");
    }

    @NonNull
    public ArrayList<i> a() {
        Cursor cursorRawQuery;
        ArrayList<i> arrayList = new ArrayList<>();
        i iVar = (i) f1115a.get("pack");
        try {
            cursorRawQuery = this.e.getWritableDatabase().rawQuery("SELECT * FROM pack ORDER BY _id DESC LIMIT 8", null);
            while (cursorRawQuery.moveToNext()) {
                try {
                    iVar = (i) iVar.clone();
                    iVar.a(cursorRawQuery);
                    arrayList.add(iVar);
                } catch (Throwable th) {
                    th = th;
                    try {
                        v.a(th);
                        v.a("queryPack, " + arrayList, (Throwable) null);
                        return arrayList;
                    } finally {
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorRawQuery = null;
        }
        v.a("queryPack, " + arrayList, (Throwable) null);
        return arrayList;
    }

    public void a(ArrayList<i> arrayList, ArrayList<i> arrayList2, ArrayList<i> arrayList3) {
        SQLiteDatabase writableDatabase;
        v.a("setResult, " + arrayList + ", " + arrayList2, (Throwable) null);
        Iterator<i> it = arrayList2.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!arrayList3.contains(next) && Math.abs(System.currentTimeMillis() - next.f1110c) > 864000000) {
                arrayList.add(next);
                it.remove();
            }
        }
        try {
            writableDatabase = this.e.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                try {
                    Iterator<i> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        i next2 = it2.next();
                        if (arrayList3.contains(next2)) {
                            a(next2, true, writableDatabase, false);
                        } else {
                            writableDatabase.execSQL("DELETE FROM pack WHERE _id=?", new String[]{String.valueOf(next2.f1109b)});
                        }
                    }
                } catch (Throwable th) {
                    v.a(th);
                }
                Iterator<i> it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    i next3 = it3.next();
                    if (next3.s != null) {
                        a((String) null);
                    }
                    if (!arrayList3.contains(next3)) {
                        long j = next3.f1109b;
                        int i = next3.m + 1;
                        next3.m = i;
                        writableDatabase.execSQL("UPDATE pack SET _fail=" + i + " WHERE _id=" + j);
                    }
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Throwable th2) {
                th = th2;
                try {
                    v.a(th);
                } finally {
                    x.a(writableDatabase);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            writableDatabase = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0094 A[Catch: all -> 0x00df, TRY_LEAVE, TryCatch #4 {all -> 0x00df, blocks: (B:32:0x008a, B:33:0x008e, B:35:0x0094, B:37:0x009c, B:39:0x00a6, B:42:0x00be, B:44:0x00c8, B:46:0x00d4, B:48:0x00db), top: B:71:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ed A[Catch: all -> 0x00ff, LOOP:2: B:53:0x00e7->B:55:0x00ed, LOOP_END, TRY_LEAVE, TryCatch #3 {all -> 0x00ff, blocks: (B:52:0x00e3, B:53:0x00e7, B:55:0x00ed), top: B:69:0x00e3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(@androidx.annotation.NonNull java.util.ArrayList<b.b.a.j.b> r13) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.j.d.a(java.util.ArrayList):void");
    }
}
