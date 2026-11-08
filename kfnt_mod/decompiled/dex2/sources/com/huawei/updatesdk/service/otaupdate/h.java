package com.huawei.updatesdk.service.otaupdate;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.kwad.sdk.collector.AppStatusRules;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private static h a = new h();
    private String b;
    private String c;
    private String d = null;

    private static class a extends AsyncTask<Void, Void, String> {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0065 A[Catch: Exception -> 0x0060, all -> 0x00ac, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x0060, blocks: (B:5:0x001d, B:7:0x0023, B:14:0x0065), top: B:36:0x001d }] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String doInBackground(java.lang.Void... r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.String r8 = "content://com.huawei.appmarket.commondata/item/1"
                android.net.Uri r1 = android.net.Uri.parse(r8)
                r8 = 0
                com.huawei.updatesdk.a.b.a.a r0 = com.huawei.updatesdk.a.b.a.a.a()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7f
                android.content.Context r0 = r0.b()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7f
                android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7f
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
                android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7f
                if (r0 == 0) goto L65
                boolean r1 = r0.moveToFirst()     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> Lac
                if (r1 == 0) goto L65
                java.lang.String r1 = "homecountry"
                int r1 = r0.getColumnIndex(r1)     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> Lac
                java.lang.String r1 = r0.getString(r1)     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> Lac
                com.huawei.updatesdk.service.otaupdate.h r8 = com.huawei.updatesdk.service.otaupdate.h.a()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                r8.b(r1)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                com.huawei.updatesdk.service.a.a r8 = com.huawei.updatesdk.service.a.a.a()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                r8.b(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                com.huawei.updatesdk.service.a.a r8 = com.huawei.updatesdk.service.a.a.a()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                r8.b(r1)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                java.lang.String r8 = "ServiceZoneUtil"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                r2.<init>()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                java.lang.String r3 = "AccountZoneFromHiApp = "
                r2.append(r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                r2.append(r1)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                android.util.Log.i(r8, r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> Lac
                r8 = r1
                goto L6c
            L5e:
                r8 = move-exception
                goto L83
            L60:
                r1 = move-exception
                r6 = r1
                r1 = r8
                r8 = r6
                goto L83
            L65:
                java.lang.String r1 = "ServiceZoneUtil"
                java.lang.String r2 = "cursor == null: "
                android.util.Log.e(r1, r2)     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> Lac
            L6c:
                if (r0 == 0) goto Lab
                r0.close()     // Catch: java.lang.Exception -> L72
                goto Lab
            L72:
                java.lang.String r0 = "ServiceZoneUtil"
                java.lang.String r1 = "cursor Execption"
                com.huawei.updatesdk.a.a.b.a.a.a.d(r0, r1)
                goto Lab
            L7a:
                r0 = move-exception
                r6 = r0
                r0 = r8
                r8 = r6
                goto Lad
            L7f:
                r0 = move-exception
                r1 = r8
                r8 = r0
                r0 = r1
            L83:
                java.lang.String r2 = "ServiceZoneUtil"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lac
                r3.<init>()     // Catch: java.lang.Throwable -> Lac
                java.lang.String r4 = "close cursor error: "
                r3.append(r4)     // Catch: java.lang.Throwable -> Lac
                java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> Lac
                r3.append(r8)     // Catch: java.lang.Throwable -> Lac
                java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> Lac
                android.util.Log.e(r2, r8)     // Catch: java.lang.Throwable -> Lac
                if (r0 == 0) goto Laa
                r0.close()     // Catch: java.lang.Exception -> La3
                goto Laa
            La3:
                java.lang.String r8 = "ServiceZoneUtil"
                java.lang.String r0 = "cursor Execption"
                com.huawei.updatesdk.a.a.b.a.a.a.d(r8, r0)
            Laa:
                r8 = r1
            Lab:
                return r8
            Lac:
                r8 = move-exception
            Lad:
                if (r0 == 0) goto Lba
                r0.close()     // Catch: java.lang.Exception -> Lb3
                goto Lba
            Lb3:
                java.lang.String r0 = "ServiceZoneUtil"
                java.lang.String r1 = "cursor Execption"
                com.huawei.updatesdk.a.a.b.a.a.a.d(r0, r1)
            Lba:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.service.otaupdate.h.a.doInBackground(java.lang.Void[]):java.lang.String");
        }
    }

    private h() {
    }

    public static h a() {
        return a;
    }

    public void a(Context context) {
        this.c = null;
        if (com.huawei.updatesdk.service.e.c.b(context)) {
            if (Math.abs(System.currentTimeMillis() - com.huawei.updatesdk.service.a.a.a().d()) < AppStatusRules.DEFAULT_START_TIME) {
                String strE = com.huawei.updatesdk.service.a.a.a().e();
                a().b(strE);
                Log.i("ServiceZoneUtil", "less than one day from the last query, return accountZone:" + strE);
                return;
            }
            a aVar = new a();
            aVar.executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
            try {
                aVar.get(1000L, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                aVar.cancel(true);
                Log.e("ServiceZoneUtil", "init AccountZone error: " + e.toString());
            }
        }
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return !TextUtils.isEmpty(this.b) ? this.b : this.c;
    }

    public void c(String str) {
        this.d = str;
    }

    public boolean d() {
        if (this.b != null) {
            return this.b.equals(this.c);
        }
        return true;
    }
}
