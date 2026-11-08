package com.vivo.push.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.vivo.push.c.l;
import com.vivo.push.model.InsideNotificationItem;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class j extends AsyncTask<String, Void, List<Bitmap>> {
    private Context a;
    private InsideNotificationItem b;
    private long c;
    private boolean d;
    private int e = 0;
    private l.a f;

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(List<Bitmap> list) {
        List<Bitmap> list2 = list;
        super.onPostExecute(list2);
        n.c("ImageDownTask", "onPostExecute");
        if (this.b != null) {
            v.b().a("com.vivo.push.notify_key", this.c);
            NotifyAdapterUtil.pushNotification(this.a, list2, this.b, this.c, this.e, this.f);
        }
    }

    public j(Context context, InsideNotificationItem insideNotificationItem, long j, boolean z, l.a aVar) {
        this.a = context;
        this.b = insideNotificationItem;
        this.c = j;
        this.d = z;
        this.f = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0098 A[EXC_TOP_SPLITTER, PHI: r4
  0x0098: PHI (r4v5 java.io.InputStream) = (r4v4 java.io.InputStream), (r4v6 java.io.InputStream) binds: [B:24:0x0096, B:29:0x00a4] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<android.graphics.Bitmap> doInBackground(java.lang.String... r10) throws java.lang.Throwable {
        /*
            r9 = this;
            android.content.Context r0 = r9.a
            com.vivo.push.cache.ClientConfigManagerImpl r0 = com.vivo.push.cache.ClientConfigManagerImpl.getInstance(r0)
            int r0 = r0.getNotifyStyle()
            r9.e = r0
            boolean r0 = r9.d
            r1 = 0
            if (r0 != 0) goto L19
            java.lang.String r10 = "ImageDownTask"
            java.lang.String r0 = "bitmap is not display by forbid net"
            com.vivo.push.util.n.d(r10, r0)
            return r1
        L19:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = 0
            r3 = 0
        L20:
            r4 = 2
            if (r3 >= r4) goto Lbd
            r4 = r10[r3]
            java.lang.String r5 = "ImageDownTask"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "imgUrl="
            r6.<init>(r7)
            r6.append(r4)
            java.lang.String r7 = " i="
            r6.append(r7)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            com.vivo.push.util.n.d(r5, r6)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto Lb4
            java.net.URL r5 = new java.net.URL     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            java.net.URLConnection r4 = r5.openConnection()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            r5 = 30000(0x7530, float:4.2039E-41)
            r4.setConnectTimeout(r5)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            r5 = 1
            r4.setDoInput(r5)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            r4.setUseCaches(r2)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            r4.connect()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            int r5 = r4.getResponseCode()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            java.lang.String r6 = "ImageDownTask"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            java.lang.String r8 = "code="
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            r7.append(r5)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            com.vivo.push.util.n.c(r6, r7)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L84
            java.io.InputStream r4 = r4.getInputStream()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.net.MalformedURLException -> L9c
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeStream(r4)     // Catch: java.io.IOException -> L8f java.net.MalformedURLException -> L9d java.lang.Throwable -> Lac
            goto L86
        L84:
            r4 = r1
            r5 = r4
        L86:
            if (r4 == 0) goto La8
            r4.close()     // Catch: java.lang.Exception -> La8
            goto La8
        L8c:
            r10 = move-exception
            goto Lae
        L8e:
            r4 = r1
        L8f:
            java.lang.String r5 = "ImageDownTask"
            java.lang.String r6 = "IOException"
            com.vivo.push.util.n.a(r5, r6)     // Catch: java.lang.Throwable -> Lac
            if (r4 == 0) goto La7
        L98:
            r4.close()     // Catch: java.lang.Exception -> La7
            goto La7
        L9c:
            r4 = r1
        L9d:
            java.lang.String r5 = "ImageDownTask"
            java.lang.String r6 = "MalformedURLException"
            com.vivo.push.util.n.a(r5, r6)     // Catch: java.lang.Throwable -> Lac
            if (r4 == 0) goto La7
            goto L98
        La7:
            r5 = r1
        La8:
            r0.add(r5)
            goto Lb9
        Lac:
            r10 = move-exception
            r1 = r4
        Lae:
            if (r1 == 0) goto Lb3
            r1.close()     // Catch: java.lang.Exception -> Lb3
        Lb3:
            throw r10
        Lb4:
            if (r3 != 0) goto Lb9
            r0.add(r1)
        Lb9:
            int r3 = r3 + 1
            goto L20
        Lbd:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.j.doInBackground(java.lang.String[]):java.util.List");
    }
}
