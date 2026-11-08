package com.huawei.updatesdk.service.otaupdate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {

    public interface a {
        void a(Boolean bool);
    }

    private static class b extends AsyncTask<Void, Void, Boolean> {
        a a;

        public b(a aVar) {
            this.a = aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0064 A[Catch: Exception -> 0x0062, all -> 0x00a3, TRY_LEAVE, TryCatch #0 {all -> 0x00a3, blocks: (B:5:0x001e, B:7:0x0024, B:14:0x0064, B:23:0x0081), top: B:34:0x0002 }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x006d A[EXC_TOP_SPLITTER, PHI: r0 r1
  0x006d: PHI (r0v3 boolean) = (r0v4 boolean), (r0v8 boolean) binds: [B:24:0x009b, B:15:0x006b] A[DONT_GENERATE, DONT_INLINE]
  0x006d: PHI (r1v5 android.database.Cursor) = (r1v7 android.database.Cursor), (r1v10 android.database.Cursor) binds: [B:24:0x009b, B:15:0x006b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Boolean doInBackground(java.lang.Void... r11) throws java.lang.Throwable {
            /*
                r10 = this;
                r11 = 0
                r0 = 0
                com.huawei.updatesdk.a.b.a.a r1 = com.huawei.updatesdk.a.b.a.a.a()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7d
                android.content.Context r1 = r1.b()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7d
                java.lang.String r2 = "content://com.huawei.appmarket.commondata/item/4"
                android.net.Uri r4 = android.net.Uri.parse(r2)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7d
                android.content.ContentResolver r3 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7d
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7d
                if (r1 == 0) goto L64
                boolean r11 = r1.moveToFirst()     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                if (r11 == 0) goto L64
                java.lang.String r11 = "isagree"
                int r11 = r1.getColumnIndex(r11)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.String r11 = r1.getString(r11)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                boolean r11 = r11.booleanValue()     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.String r2 = "isopenautoupdate"
                int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                boolean r2 = r2.booleanValue()     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.String r3 = "isneverreminder"
                int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                boolean r3 = r3.booleanValue()     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
                if (r11 == 0) goto L6b
                if (r2 != 0) goto L6b
                if (r3 != 0) goto L6b
                r0 = 1
                goto L6b
            L62:
                r11 = move-exception
                goto L81
            L64:
                java.lang.String r11 = "AutoUpdateUtil"
                java.lang.String r2 = "cursor == null"
                android.util.Log.e(r11, r2)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> La3
            L6b:
                if (r1 == 0) goto L9e
            L6d:
                r1.close()     // Catch: java.lang.Exception -> L71
                goto L9e
            L71:
                java.lang.String r11 = "AutoUpdateUtil"
                java.lang.String r1 = "cursor Execption"
                com.huawei.updatesdk.a.a.b.a.a.a.d(r11, r1)
                goto L9e
            L79:
                r0 = move-exception
                r1 = r11
                r11 = r0
                goto La4
            L7d:
                r1 = move-exception
                r9 = r1
                r1 = r11
                r11 = r9
            L81:
                java.lang.String r2 = "AutoUpdateUtil"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
                r3.<init>()     // Catch: java.lang.Throwable -> La3
                java.lang.String r4 = "close cursor error: "
                r3.append(r4)     // Catch: java.lang.Throwable -> La3
                java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> La3
                r3.append(r11)     // Catch: java.lang.Throwable -> La3
                java.lang.String r11 = r3.toString()     // Catch: java.lang.Throwable -> La3
                android.util.Log.e(r2, r11)     // Catch: java.lang.Throwable -> La3
                if (r1 == 0) goto L9e
                goto L6d
            L9e:
                java.lang.Boolean r11 = java.lang.Boolean.valueOf(r0)
                return r11
            La3:
                r11 = move-exception
            La4:
                if (r1 == 0) goto Lb1
                r1.close()     // Catch: java.lang.Exception -> Laa
                goto Lb1
            Laa:
                java.lang.String r0 = "AutoUpdateUtil"
                java.lang.String r1 = "cursor Execption"
                com.huawei.updatesdk.a.a.b.a.a.a.d(r0, r1)
            Lb1:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.service.otaupdate.c.b.doInBackground(java.lang.Void[]):java.lang.Boolean");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (this.a != null) {
                this.a.a(bool);
            }
        }
    }

    public void a(Context context, a aVar) {
        if (!com.huawei.updatesdk.service.e.c.c(context) || !h.a().d()) {
            aVar.a(false);
            return;
        }
        b bVar = new b(aVar);
        bVar.executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
        try {
            bVar.get(500L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            bVar.cancel(true);
            Log.e("AutoUpdateUtil", "init AutoUpdateInfo error: " + e.toString());
            aVar.a(false);
        }
    }
}
