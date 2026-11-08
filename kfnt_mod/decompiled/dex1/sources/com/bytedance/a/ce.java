package com.bytedance.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.sdk.account.platform.api.IAppAuthService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ce {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f3457a = {1};

    /* renamed from: b, reason: collision with root package name */
    public static final HashMap<String, bu> f3458b = new HashMap<>();

    /* renamed from: c, reason: collision with root package name */
    public static final bu[] f3459c;
    public static final b[] d;
    public final dg e;
    public final a f;
    public String g;

    public static class a extends SQLiteOpenHelper {
        public a(@Nullable Context context, @Nullable String str, @Nullable SQLiteDatabase.CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.beginTransaction();
                Iterator<bu> it = ce.f3458b.values().iterator();
                while (it.hasNext()) {
                    String strC = it.next().c();
                    if (strC != null) {
                        sQLiteDatabase.execSQL(strC);
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                try {
                } finally {
                }
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            onUpgrade(sQLiteDatabase, i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            de.a("onUpgrade, " + i + ", " + i2, null);
            try {
                sQLiteDatabase.beginTransaction();
                Iterator<bu> it = ce.f3458b.values().iterator();
                while (it.hasNext()) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + it.next().d());
                }
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                try {
                    dj.a(sQLiteDatabase);
                    onCreate(sQLiteDatabase);
                } catch (Throwable th) {
                }
            }
            dj.a(sQLiteDatabase);
            onCreate(sQLiteDatabase);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public String f3460a;

        /* renamed from: b, reason: collision with root package name */
        public int f3461b;

        /* renamed from: c, reason: collision with root package name */
        public int f3462c;

        public final void a(bu buVar) {
            String strI = buVar.i();
            if (strI == null || strI.length() <= this.f3461b) {
                return;
            }
            this.f3460a = buVar.h();
            this.f3461b = strI.length();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.f3462c);
            sb.append("-");
            sb.append(this.f3460a);
            sb.append("-");
            sb.append(this.f3461b);
            return sb.toString();
        }
    }

    static {
        f3458b.put("page", new di());
        f3458b.put("launch", new cy());
        f3458b.put("terminate", new dx());
        f3458b.put("pack", new dd());
        f3459c = new bu[]{new cj(), new ct(null, false, null), new co("", new JSONObject())};
        for (bu buVar : f3459c) {
            a(buVar);
        }
        f3458b.put(IAppAuthService.Scope.PROFILE, new dn(null, null));
        d = new b[]{new b(), new b(), new b()};
    }

    public ce(dg dgVar, String str) {
        this.f = new a(dgVar.d, str, null, 40);
        this.e = dgVar;
    }

    public static void a(bu buVar) {
        f3458b.put(buVar.d(), buVar);
    }

    public final String a(bu buVar, String str, boolean z, int i, int i2) {
        StringBuilder sbA = com.bytedance.a.a.a("SELECT * FROM ");
        sbA.append(buVar.d());
        sbA.append(" WHERE ");
        sbA.append(com.bytedance.b.a.a.d.p);
        sbA.append(z ? "='" : "!='");
        sbA.append(str);
        sbA.append("' AND ");
        sbA.append("event_type");
        sbA.append("='");
        sbA.append(i2);
        sbA.append("' ORDER BY ");
        sbA.append("_id");
        sbA.append(" LIMIT ");
        sbA.append(i);
        return sbA.toString();
    }

    public final String a(String str, int i, String str2, boolean z, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(str);
        sb.append(" WHERE ");
        sb.append(com.bytedance.b.a.a.d.p);
        sb.append(z ? "='" : "!='");
        sb.append(str2);
        sb.append("' AND ");
        sb.append("event_type");
        sb.append("='");
        sb.append(i);
        sb.append("' AND ");
        sb.append("_id");
        sb.append("<=");
        sb.append(j);
        return sb.toString();
    }

    public synchronized ArrayList<bu> a() {
        ArrayList<bu> arrayList;
        SQLiteDatabase writableDatabase;
        dn dnVar = (dn) f3458b.get(IAppAuthService.Scope.PROFILE);
        arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            writableDatabase = this.f.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT * FROM profile ORDER BY _id DESC LIMIT 200", null);
                while (cursorRawQuery.moveToNext()) {
                    try {
                        dnVar.a(cursorRawQuery);
                        arrayList.add(dnVar.clone());
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorRawQuery;
                        try {
                            de.a("U SHALL NOT PASS!", th);
                            if (cursor != null) {
                                try {
                                    cursor.close();
                                } catch (Throwable th2) {
                                    th = th2;
                                    de.a("U SHALL NOT PASS!", th);
                                    return arrayList;
                                }
                            }
                            return arrayList;
                        } finally {
                            if (cursor != null) {
                                try {
                                    cursor.close();
                                } catch (Throwable th3) {
                                    de.a("U SHALL NOT PASS!", th3);
                                }
                            }
                            dj.a(writableDatabase);
                        }
                    }
                }
                writableDatabase.setTransactionSuccessful();
                try {
                    cursorRawQuery.close();
                } catch (Throwable th4) {
                    th = th4;
                    de.a("U SHALL NOT PASS!", th);
                    return arrayList;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (Throwable th6) {
            th = th6;
            writableDatabase = null;
        }
        return arrayList;
    }

    public final JSONObject a(cy cyVar, JSONObject jSONObject) throws JSONException {
        if (TextUtils.equals(cyVar.n, this.e.i.c()) && cyVar.m == this.e.i.b()) {
            return jSONObject;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            dj.b(jSONObject2, jSONObject);
            jSONObject2.put("app_version", cyVar.n);
            jSONObject2.put("version_code", cyVar.m);
            return jSONObject2;
        } catch (JSONException e) {
            de.a("U SHALL NOT PASS!", e);
            return jSONObject;
        }
    }

    public final void a(JSONObject jSONObject, boolean z, dd ddVar, SQLiteDatabase sQLiteDatabase) {
        int i;
        int i2;
        int[] iArr;
        dd ddVar2;
        SQLiteDatabase sQLiteDatabase2;
        long[] jArr;
        SQLiteDatabase sQLiteDatabase3;
        dd ddVar3;
        dd ddVar4 = ddVar;
        SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase;
        int[] iArr2 = f3457a;
        int length = iArr2.length;
        int i3 = 0;
        while (i3 < length) {
            int i4 = iArr2[i3];
            JSONArray[] jSONArrayArr = new JSONArray[3];
            long[] jArr2 = new long[3];
            int iA = a(0, sQLiteDatabase, ddVar4.e, z, i4, jSONArrayArr, jArr2);
            if (a(jArr2)) {
                i = i3;
                i2 = length;
                iArr = iArr2;
                SQLiteDatabase sQLiteDatabase5 = sQLiteDatabase4;
                dd ddVar5 = ddVar4;
                ddVar.a(jSONObject, null, null, null, jSONArrayArr, jArr2, null, i4);
                a(ddVar5, z, sQLiteDatabase5, true);
                int iA2 = iA;
                while (iA2 < f3459c.length) {
                    dd ddVar6 = ddVar5;
                    SQLiteDatabase sQLiteDatabase6 = sQLiteDatabase5;
                    long[] jArr3 = jArr2;
                    iA2 = a(iA2, sQLiteDatabase, ddVar5.e, z, i4, jSONArrayArr, jArr2);
                    if (a(jArr3)) {
                        jArr = jArr3;
                        sQLiteDatabase3 = sQLiteDatabase6;
                        ddVar3 = ddVar6;
                        ddVar.a(jSONObject, null, null, null, jSONArrayArr, jArr, null, i4);
                        a(ddVar3, z, sQLiteDatabase3, true);
                    } else {
                        jArr = jArr3;
                        sQLiteDatabase3 = sQLiteDatabase6;
                        ddVar3 = ddVar6;
                    }
                    sQLiteDatabase5 = sQLiteDatabase3;
                    ddVar5 = ddVar3;
                    jArr2 = jArr;
                }
                ddVar2 = ddVar5;
                sQLiteDatabase2 = sQLiteDatabase5;
            } else {
                i = i3;
                i2 = length;
                iArr = iArr2;
                sQLiteDatabase2 = sQLiteDatabase4;
                ddVar2 = ddVar4;
            }
            i3 = i + 1;
            sQLiteDatabase4 = sQLiteDatabase2;
            ddVar4 = ddVar2;
            length = i2;
            iArr2 = iArr;
        }
    }

    public final boolean a(long[] jArr) {
        return jArr[0] > 0 || jArr[1] > 0 || jArr[2] > 0;
    }

    public final void a(SQLiteDatabase sQLiteDatabase, HashMap<String, JSONObject> map) {
        Cursor cursorRawQuery;
        cy cyVar = (cy) f3458b.get("launch");
        try {
            try {
                cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM launch ORDER BY _id LIMIT 5", null);
                while (cursorRawQuery.moveToNext()) {
                    try {
                        cyVar.a(cursorRawQuery);
                        JSONObject jSONObject = new JSONObject();
                        try {
                            cu.a().onSessionBatchEvent(cyVar.f3433b, cyVar.e, jSONObject);
                        } catch (Throwable th) {
                            de.a("U SHALL NOT PASS!", th);
                        }
                        map.put(cyVar.e, jSONObject);
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            de.a("U SHALL NOT PASS!", th);
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            return;
                        } catch (Throwable th3) {
                            if (cursorRawQuery != null) {
                                try {
                                    cursorRawQuery.close();
                                } catch (Throwable th4) {
                                    de.a("U SHALL NOT PASS!", th4);
                                }
                            }
                            throw th3;
                        }
                    }
                }
                cursorRawQuery.close();
            } catch (Throwable th5) {
                de.a("U SHALL NOT PASS!", th5);
            }
        } catch (Throwable th6) {
            th = th6;
            cursorRawQuery = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0093 A[Catch: all -> 0x00de, TRY_LEAVE, TryCatch #3 {all -> 0x00de, blocks: (B:31:0x0089, B:32:0x008d, B:34:0x0093, B:36:0x009b, B:38:0x00a5, B:41:0x00bd, B:43:0x00c7, B:45:0x00d3, B:47:0x00da), top: B:68:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ee A[Catch: all -> 0x0100, LOOP:2: B:52:0x00e8->B:54:0x00ee, LOOP_END, TRY_LEAVE, TryCatch #0 {all -> 0x0100, blocks: (B:51:0x00e4, B:52:0x00e8, B:54:0x00ee), top: B:62:0x00e4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(@androidx.annotation.NonNull java.util.ArrayList<com.bytedance.a.bu> r13) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ce.b(java.util.ArrayList):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0077 A[Catch: all -> 0x0011, TRY_LEAVE, TryCatch #0 {all -> 0x0011, blocks: (B:6:0x000d, B:13:0x001b, B:15:0x0027, B:17:0x002b, B:21:0x0034, B:25:0x003c, B:26:0x004b, B:30:0x0053, B:31:0x0060, B:35:0x0068, B:37:0x0077), top: B:48:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.bytedance.a.dd r11, boolean r12, android.database.sqlite.SQLiteDatabase r13, boolean r14) {
        /*
            r10 = this;
            if (r13 != 0) goto La
            com.bytedance.a.ce$a r13 = r10.f
            android.database.sqlite.SQLiteDatabase r13 = r13.getWritableDatabase()
            r0 = 1
            goto Lb
        La:
            r0 = 0
        Lb:
            if (r0 == 0) goto L14
            r13.beginTransaction()     // Catch: java.lang.Throwable -> L11
            goto L14
        L11:
            r11 = move-exception
            goto L7d
        L14:
            r1 = 0
            if (r14 == 0) goto L34
            java.lang.String r14 = "pack"
            r3 = 0
            android.content.ContentValues r4 = r11.b(r3)     // Catch: java.lang.Throwable -> L11
            long r4 = r13.insert(r14, r3, r4)     // Catch: java.lang.Throwable -> L11
            int r14 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r14 >= 0) goto L34
            com.bytedance.a.cy r11 = r11.t     // Catch: java.lang.Throwable -> L11
            if (r11 == 0) goto L2e
            r10.a(r3)     // Catch: java.lang.Throwable -> L11
        L2e:
            if (r0 == 0) goto L33
            com.bytedance.a.dj.a(r13)
        L33:
            return
        L34:
            long r3 = r11.q     // Catch: java.lang.Throwable -> L11
            int r14 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r14 <= 0) goto L4b
            java.lang.String r4 = "event"
            int r5 = r11.k     // Catch: java.lang.Throwable -> L11
            java.lang.String r6 = r11.e     // Catch: java.lang.Throwable -> L11
            long r8 = r11.q     // Catch: java.lang.Throwable -> L11
            r3 = r10
            r7 = r12
            java.lang.String r14 = r3.a(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L11
            r13.execSQL(r14)     // Catch: java.lang.Throwable -> L11
        L4b:
            long r8 = r11.s     // Catch: java.lang.Throwable -> L11
            int r14 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r14 <= 0) goto L60
            java.lang.String r4 = "eventv3"
            int r5 = r11.k     // Catch: java.lang.Throwable -> L11
            java.lang.String r6 = r11.e     // Catch: java.lang.Throwable -> L11
            r3 = r10
            r7 = r12
            java.lang.String r14 = r3.a(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L11
            r13.execSQL(r14)     // Catch: java.lang.Throwable -> L11
        L60:
            long r6 = r11.y     // Catch: java.lang.Throwable -> L11
            int r14 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r14 <= 0) goto L75
            java.lang.String r2 = "event_misc"
            int r3 = r11.k     // Catch: java.lang.Throwable -> L11
            java.lang.String r4 = r11.e     // Catch: java.lang.Throwable -> L11
            r1 = r10
            r5 = r12
            java.lang.String r11 = r1.a(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L11
            r13.execSQL(r11)     // Catch: java.lang.Throwable -> L11
        L75:
            if (r0 == 0) goto L7a
            r13.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L11
        L7a:
            if (r0 == 0) goto L87
            goto L84
        L7d:
            java.lang.String r12 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r12, r11)     // Catch: java.lang.Throwable -> L88
            if (r0 == 0) goto L87
        L84:
            com.bytedance.a.dj.a(r13)
        L87:
            return
        L88:
            r11 = move-exception
            if (r0 == 0) goto L8e
            com.bytedance.a.dj.a(r13)
        L8e:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ce.a(com.bytedance.a.dd, boolean, android.database.sqlite.SQLiteDatabase, boolean):void");
    }

    public synchronized void a(ArrayList<bu> arrayList) {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = this.f.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                Iterator<bu> it = arrayList.iterator();
                while (it.hasNext()) {
                    writableDatabase.execSQL("DELETE FROM profile WHERE _id=?", new String[]{String.valueOf(it.next().f3433b)});
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Throwable th) {
                th = th;
                try {
                    de.a("U SHALL NOT PASS!", th);
                } finally {
                    dj.a(writableDatabase);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
    }

    public final JSONArray a(cy cyVar, HashMap<String, JSONObject> map) {
        bv bvVar;
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObject = map.get(cyVar.e);
        JSONArray jSONArray = null;
        if (jSONObject != null && ((jSONArrayOptJSONArray = jSONObject.optJSONArray("item_impression")) == null || jSONArrayOptJSONArray.length() != 0)) {
            jSONArray = jSONArrayOptJSONArray;
        }
        bv bvVar2 = bb.f3394a;
        if ((bvVar2 != null ? bvVar2.a() : false) && jSONArray != null && (bvVar = bb.f3394a) != null) {
            bvVar.a("item_impression", jSONArray);
        }
        return jSONArray;
    }

    public final boolean a(String str) {
        StringBuilder sbA = com.bytedance.a.a.a("needLaunch, ");
        sbA.append(this.g);
        sbA.append(", ");
        sbA.append(str);
        de.a(sbA.toString(), null);
        if (TextUtils.equals(str, this.g)) {
            return false;
        }
        this.g = str;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0181 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.util.ArrayList<com.bytedance.a.dd> a(org.json.JSONObject r33) {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ce.a(org.json.JSONObject):java.util.ArrayList");
    }

    public final void a(JSONObject jSONObject, cy cyVar, dd ddVar, SQLiteDatabase sQLiteDatabase, JSONArray[] jSONArrayArr, long[] jArr, ArrayList<dd> arrayList, HashMap<String, JSONObject> map) {
        cy cyVar2;
        long[] jArr2;
        SQLiteDatabase sQLiteDatabase2;
        dd ddVar2;
        SQLiteDatabase sQLiteDatabase3;
        dd ddVar3;
        StringBuilder sbA = com.bytedance.a.a.a("packCurrentData, ");
        sbA.append(cyVar.e);
        de.a(sbA.toString(), null);
        boolean zA = a(cyVar.e);
        int iA = a(0, sQLiteDatabase, cyVar.e, true, 0, jSONArrayArr, jArr);
        JSONArray jSONArrayA = a(cyVar, map);
        if (zA || a(jArr) || jSONArrayA != null) {
            cyVar2 = null;
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            ddVar2 = ddVar;
            ddVar.a(jSONObject, zA ? cyVar : null, null, null, jSONArrayArr, jArr, jSONArrayA, 0);
            if (jSONArrayA != null || iA < f3459c.length) {
                a(ddVar2, true, sQLiteDatabase2, true);
            } else {
                dd ddVar4 = (dd) ddVar.clone();
                ddVar4.j();
                arrayList.add(ddVar4);
            }
        } else {
            cyVar2 = null;
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            ddVar2 = ddVar;
        }
        int iA2 = iA;
        while (iA2 < f3459c.length) {
            dd ddVar5 = ddVar2;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            iA2 = a(iA2, sQLiteDatabase, cyVar.e, true, 0, jSONArrayArr, jArr);
            if (a(jArr2)) {
                sQLiteDatabase3 = sQLiteDatabase4;
                ddVar3 = ddVar5;
                ddVar.a(jSONObject, a(cyVar.e) ? cyVar : cyVar2, null, null, jSONArrayArr, jArr, null, 0);
                a(ddVar3, true, sQLiteDatabase3, true);
            } else {
                sQLiteDatabase3 = sQLiteDatabase4;
                ddVar3 = ddVar5;
            }
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase3;
            ddVar2 = ddVar3;
        }
    }

    public final void a(JSONObject jSONObject, cy cyVar, dd ddVar, di diVar, dx dxVar, SQLiteDatabase sQLiteDatabase, JSONArray[] jSONArrayArr, long[] jArr, HashMap<String, JSONObject> map) {
        SQLiteDatabase sQLiteDatabase2;
        dd ddVar2;
        boolean z;
        cy cyVar2;
        int i;
        dd ddVar3;
        JSONObject jSONObject2;
        dx dxVar2;
        JSONArray jSONArray;
        SQLiteDatabase sQLiteDatabase3;
        dd ddVar4;
        StringBuilder sbA = com.bytedance.a.a.a("packHistoryData, ");
        sbA.append(cyVar.e);
        de.a(sbA.toString(), null);
        JSONArray jSONArrayA = a(cyVar, true, dxVar, diVar, sQLiteDatabase);
        cyVar.o = jSONArrayA.length() == 0;
        int iA = a(0, sQLiteDatabase, cyVar.e, true, 0, jSONArrayArr, jArr);
        JSONArray jSONArrayA2 = a(cyVar, map);
        if (cyVar.o) {
            cyVar2 = a(cyVar.e) ? cyVar : null;
            i = 0;
            ddVar3 = ddVar;
            jSONObject2 = jSONObject;
            z = true;
            dxVar2 = null;
            sQLiteDatabase2 = sQLiteDatabase;
            jSONArray = null;
            ddVar2 = ddVar;
        } else {
            sQLiteDatabase2 = sQLiteDatabase;
            ddVar2 = ddVar;
            z = true;
            cyVar2 = null;
            i = 0;
            ddVar3 = ddVar;
            jSONObject2 = jSONObject;
            dxVar2 = dxVar;
            jSONArray = jSONArrayA;
        }
        ddVar3.a(jSONObject2, cyVar2, dxVar2, jSONArray, jSONArrayArr, jArr, jSONArrayA2, i);
        a(ddVar2, z, sQLiteDatabase2, z);
        int iA2 = iA;
        while (iA2 < f3459c.length) {
            dd ddVar5 = ddVar2;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            iA2 = a(iA2, sQLiteDatabase, cyVar.e, true, 0, jSONArrayArr, jArr);
            if (a(jArr)) {
                sQLiteDatabase3 = sQLiteDatabase4;
                ddVar4 = ddVar5;
                ddVar.a(jSONObject, null, null, null, jSONArrayArr, jArr, null, 0);
                a(ddVar4, true, sQLiteDatabase3, true);
            } else {
                sQLiteDatabase3 = sQLiteDatabase4;
                ddVar4 = ddVar5;
            }
            sQLiteDatabase2 = sQLiteDatabase3;
            ddVar2 = ddVar4;
        }
    }

    public final void a(JSONObject jSONObject, cy cyVar, dx dxVar, di diVar, dd ddVar, SQLiteDatabase sQLiteDatabase, String str, JSONArray[] jSONArrayArr, long[] jArr) {
        long[] jArr2;
        SQLiteDatabase sQLiteDatabase2;
        dd ddVar2;
        SQLiteDatabase sQLiteDatabase3;
        de.a("packLostData, " + str, null);
        cyVar.e = str;
        ddVar.e = str;
        JSONArray jSONArrayA = a(cyVar, false, dxVar, diVar, sQLiteDatabase);
        int iA = a(0, sQLiteDatabase, str, false, 0, jSONArrayArr, jArr);
        cyVar.o = jSONArrayA.length() == 0;
        if (a(jArr) || !cyVar.o) {
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            ddVar2 = ddVar;
            ddVar.a(jSONObject, null, !cyVar.o ? dxVar : null, !cyVar.o ? jSONArrayA : null, jSONArrayArr, jArr, null, 0);
            a(ddVar2, false, sQLiteDatabase2, true);
        } else {
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase;
            ddVar2 = ddVar;
        }
        int iA2 = iA;
        while (iA2 < f3459c.length) {
            dd ddVar3 = ddVar2;
            SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase2;
            iA2 = a(iA2, sQLiteDatabase, str, false, 0, jSONArrayArr, jArr);
            if (a(jArr2)) {
                sQLiteDatabase3 = sQLiteDatabase4;
                ddVar2 = ddVar3;
                ddVar.a(jSONObject, null, null, null, jSONArrayArr, jArr, null, 0);
                a(ddVar2, false, sQLiteDatabase3, true);
            } else {
                sQLiteDatabase3 = sQLiteDatabase4;
                ddVar2 = ddVar3;
            }
            jArr2 = jArr;
            sQLiteDatabase2 = sQLiteDatabase3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009e A[DONT_GENERATE, PHI: r7 r10
  0x009e: PHI (r7v4 android.database.Cursor) = (r7v3 android.database.Cursor), (r7v6 android.database.Cursor), (r7v6 android.database.Cursor) binds: [B:37:0x009c, B:66:0x009e, B:67:0x009e] A[DONT_GENERATE, DONT_INLINE]
  0x009e: PHI (r10v4 long) = (r10v2 long), (r10v6 long), (r10v6 long) binds: [B:37:0x009c, B:66:0x009e, B:67:0x009e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(int r16, android.database.sqlite.SQLiteDatabase r17, java.lang.String r18, boolean r19, int r20, org.json.JSONArray[] r21, long[] r22) {
        /*
            Method dump skipped, instructions count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ce.a(int, android.database.sqlite.SQLiteDatabase, java.lang.String, boolean, int, org.json.JSONArray[], long[]):int");
    }

    @NonNull
    public ArrayList<dd> b() {
        Cursor cursorRawQuery;
        ArrayList<dd> arrayList = new ArrayList<>();
        dd ddVar = (dd) f3458b.get("pack");
        try {
            cursorRawQuery = this.f.getWritableDatabase().rawQuery("SELECT * FROM pack ORDER BY _id DESC LIMIT 8", null);
            while (cursorRawQuery.moveToNext()) {
                try {
                    ddVar = (dd) ddVar.clone();
                    ddVar.a(cursorRawQuery);
                    arrayList.add(ddVar);
                } catch (Throwable th) {
                    th = th;
                    try {
                        de.a("U SHALL NOT PASS!", th);
                        de.a("queryPack, " + arrayList, null);
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
        de.a("queryPack, " + arrayList, null);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0132 A[DONT_GENERATE, PHI: r11 r15 r17 r18
  0x0132: PHI (r11v2 android.database.Cursor) = (r11v3 android.database.Cursor), (r11v11 android.database.Cursor), (r11v11 android.database.Cursor) binds: [B:66:0x0130, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]
  0x0132: PHI (r15v2 long) = (r15v3 long), (r15v5 long), (r15v5 long) binds: [B:66:0x0130, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]
  0x0132: PHI (r17v3 java.lang.String) = (r17v4 java.lang.String), (r17v7 java.lang.String), (r17v7 java.lang.String) binds: [B:66:0x0130, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]
  0x0132: PHI (r18v2 java.lang.String) = (r18v3 java.lang.String), (r18v5 java.lang.String), (r18v5 java.lang.String) binds: [B:66:0x0130, B:51:0x00f5, B:56:0x010a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONArray a(com.bytedance.a.cy r21, boolean r22, com.bytedance.a.dx r23, com.bytedance.a.di r24, android.database.sqlite.SQLiteDatabase r25) {
        /*
            Method dump skipped, instructions count: 411
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ce.a(com.bytedance.a.cy, boolean, com.bytedance.a.dx, com.bytedance.a.di, android.database.sqlite.SQLiteDatabase):org.json.JSONArray");
    }

    public void a(ArrayList<dd> arrayList, ArrayList<dd> arrayList2, ArrayList<dd> arrayList3) {
        SQLiteDatabase writableDatabase;
        de.a("setResult, " + arrayList + ", " + arrayList2, null);
        Iterator<dd> it = arrayList2.iterator();
        while (it.hasNext()) {
            dd next = it.next();
            if (!arrayList3.contains(next) && Math.abs(System.currentTimeMillis() - next.f3434c) > 864000000) {
                arrayList.add(next);
                it.remove();
            }
        }
        try {
            writableDatabase = this.f.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                try {
                    Iterator<dd> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        dd next2 = it2.next();
                        if (arrayList3.contains(next2)) {
                            a(next2, true, writableDatabase, false);
                        } else {
                            writableDatabase.execSQL("DELETE FROM pack WHERE _id=?", new String[]{String.valueOf(next2.f3433b)});
                        }
                    }
                } catch (Throwable th) {
                    de.a("U SHALL NOT PASS!", th);
                }
                Iterator<dd> it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    dd next3 = it3.next();
                    if (next3.t != null) {
                        a((String) null);
                    }
                    if (!arrayList3.contains(next3)) {
                        long j = next3.f3433b;
                        int i = next3.n + 1;
                        next3.n = i;
                        writableDatabase.execSQL("UPDATE pack SET _fail=" + i + " WHERE _id=" + j);
                    }
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Throwable th2) {
                th = th2;
                try {
                    de.a("U SHALL NOT PASS!", th);
                } finally {
                    dj.a(writableDatabase);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            writableDatabase = null;
        }
    }
}
