package com.sigmob.sdk.base.common;

import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.a.b;
import com.sigmob.sdk.common.a.c;
import com.ss.union.game.sdk.core.base.event.INetUploadStrategy;
import com.unity.purchasing.googleplay.Consts;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e implements Serializable {
    private static final long serialVersionUID = 1;
    private final a a;
    private final String b;
    private final com.sigmob.sdk.base.common.a c;
    private final String d;
    private String e;
    private Long f;
    private Long g;
    private Integer h;
    private Integer i;
    private String j;
    private boolean k;

    public enum a {
        TRACKING_URL,
        QUARTILE_EVENT
    }

    public e(a aVar, String str, String str2, com.sigmob.sdk.base.common.a aVar2, String str3) {
        this.d = str3;
        this.a = aVar;
        this.j = str;
        this.b = str2;
        this.c = aVar2;
        this.e = "native";
    }

    public e(a aVar, String str, String str2, String str3) {
        this(aVar, str, str2, null, str3);
    }

    public e(String str, String str2, String str3) {
        this(a.TRACKING_URL, str, str2, str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f1 A[EDGE_INSN: B:63:0x00f1->B:41:0x00f1 BREAK  A[LOOP:0: B:10:0x0068->B:36:0x00e6], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.sigmob.sdk.base.common.e> a(int r22, long r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.common.e.a(int, long):java.util.List");
    }

    private void a(int i) {
        this.i = Integer.valueOf(i);
    }

    public static void a(long j) {
        try {
            com.sigmob.sdk.common.a.a.a().a("tracks", "timestamp < " + (System.currentTimeMillis() - j), new c.a() { // from class: com.sigmob.sdk.base.common.e.1
                @Override // com.sigmob.sdk.common.a.c.a
                public void onFailed(Throwable th) {
                    SigmobLog.e(th.getMessage());
                }

                @Override // com.sigmob.sdk.common.a.c.a
                public void onSuccess() {
                }
            });
        } catch (Throwable th) {
            SigmobLog.e("cleanExpiredAdTracker error", th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.lang.Long] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(long r5) throws java.lang.Throwable {
        /*
            r0 = 0
            r2 = 0
            com.sigmob.sdk.common.a.c r3 = com.sigmob.sdk.common.a.c.a()     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93
            android.database.sqlite.SQLiteDatabase r3 = r3.getReadableDatabase()     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93
            java.lang.String r4 = "select * from tracks"
            android.database.Cursor r4 = r3.rawQuery(r4, r2)     // Catch: java.lang.Throwable -> L8f java.lang.Throwable -> L93
            if (r4 == 0) goto L27
            int r0 = r4.getCount()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            long r0 = (long) r0     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            r4.close()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            r4 = r2
            goto L27
        L1d:
            r5 = move-exception
            r6 = r5
            r5 = r4
            goto La0
        L22:
            r5 = move-exception
            r6 = r5
            r2 = r4
            goto L95
        L27:
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 <= 0) goto L5c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            r0.<init>()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            java.lang.String r1 = "select * from tracks order by id desc limit "
            r0.append(r1)     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            r0.append(r5)     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            android.database.Cursor r5 = r3.rawQuery(r5, r2)     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L22
            java.lang.String r6 = "id"
            int r6 = r5.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            if (r5 == 0) goto L5d
            boolean r0 = r5.moveToLast()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            if (r0 == 0) goto L5d
            long r0 = r5.getLong(r6)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            java.lang.Long r6 = java.lang.Long.valueOf(r0)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            r5.close()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            r5 = r2
            r2 = r6
            goto L5d
        L5c:
            r5 = r4
        L5d:
            if (r2 == 0) goto L89
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            r6.<init>()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            java.lang.String r0 = "id <'"
            r6.append(r0)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            r6.append(r2)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            java.lang.String r0 = "'"
            r6.append(r0)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            com.sigmob.sdk.common.a.a r0 = com.sigmob.sdk.common.a.a.a()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            java.lang.String r1 = "tracks"
            com.sigmob.sdk.base.common.e$2 r2 = new com.sigmob.sdk.base.common.e$2     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            r2.<init>()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            r0.a(r1, r6, r2)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L86
            goto L89
        L84:
            r6 = move-exception
            goto La0
        L86:
            r6 = move-exception
            r2 = r5
            goto L95
        L89:
            if (r5 == 0) goto L9f
            r5.close()
            goto L9f
        L8f:
            r5 = move-exception
            r6 = r5
            r5 = r2
            goto La0
        L93:
            r5 = move-exception
            r6 = r5
        L95:
            java.lang.String r5 = "cleanLimitAdTracker "
            com.sigmob.logger.SigmobLog.e(r5, r6)     // Catch: java.lang.Throwable -> L8f
            if (r2 == 0) goto L9f
            r2.close()
        L9f:
            return
        La0:
            if (r5 == 0) goto La5
            r5.close()
        La5:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.common.e.b(long):void");
    }

    private void b(Long l) {
        this.f = l;
    }

    public String a() {
        return this.e;
    }

    public void a(final c.a aVar) {
        try {
            b.C0018b.a aVar2 = new b.C0018b.a();
            aVar2.a("tracks");
            HashMap map = new HashMap();
            map.put("url", this.j);
            map.put(Consts.INAPP_REQUEST_ID, this.d);
            map.put(INetUploadStrategy.KEY_EVENT, this.b);
            map.put("source", this.e);
            map.put("retryNum", this.i);
            map.put(com.ss.union.game.sdk.redemptionCode.a.t, Long.valueOf(System.currentTimeMillis()));
            aVar2.a(map);
            com.sigmob.sdk.common.a.c.a().a(com.sigmob.sdk.common.a.c.a().getWritableDatabase(), aVar2.a(), new c.a() { // from class: com.sigmob.sdk.base.common.e.3
                @Override // com.sigmob.sdk.common.a.c.a
                public void onFailed(Throwable th) {
                    if (aVar != null) {
                        aVar.onFailed(th);
                    }
                    SigmobLog.e(th.getMessage());
                }

                @Override // com.sigmob.sdk.common.a.c.a
                public void onSuccess() {
                    SigmobLog.d("event: " + e.this.b + " url " + e.this.j + " mRequest_id: " + e.this.d + " insert success! ");
                    if (aVar != null) {
                        aVar.onSuccess();
                    }
                }
            });
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    public void a(Integer num) {
        this.h = num;
    }

    public void a(Long l) {
        this.g = l;
    }

    public void a(String str) {
        this.e = str;
    }

    public int b() {
        return this.i.intValue();
    }

    public void b(String str) {
        this.j = str;
    }

    public void c() {
        Integer num = this.i;
        this.i = Integer.valueOf(this.i.intValue() + 1);
    }

    public void d() {
        b.c.a aVar = new b.c.a();
        aVar.a("tracks");
        aVar.b(" where id=" + this.f.toString());
        HashMap map = new HashMap();
        map.put("retryNum", this.i);
        aVar.a(map);
        com.sigmob.sdk.common.a.c.a().a(com.sigmob.sdk.common.a.c.a().getWritableDatabase(), aVar.a().a(), (c.a) null);
    }

    public Long e() {
        return this.f;
    }

    public void f() {
        if (this.f == null) {
            return;
        }
        com.sigmob.sdk.common.a.a.a().a("tracks", "id ='" + this.f + "'", new c.a() { // from class: com.sigmob.sdk.base.common.e.4
            @Override // com.sigmob.sdk.common.a.c.a
            public void onFailed(Throwable th) {
                SigmobLog.e(th.getMessage());
            }

            @Override // com.sigmob.sdk.common.a.c.a
            public void onSuccess() {
                SigmobLog.d("delete id " + e.this.f);
            }
        });
    }

    public long g() {
        if (this.g == null) {
            return 0L;
        }
        return this.g.longValue();
    }

    public a h() {
        return this.a;
    }

    public Integer i() {
        if (this.h == null) {
            return 0;
        }
        return this.h;
    }

    public String j() {
        return this.j;
    }

    public void k() {
        this.k = true;
    }

    public boolean l() {
        return this.k;
    }

    public String m() {
        return this.b;
    }

    public String n() {
        return this.d;
    }

    public com.sigmob.sdk.base.common.a o() {
        return this.c;
    }
}
