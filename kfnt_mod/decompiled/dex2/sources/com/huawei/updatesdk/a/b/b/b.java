package com.huawei.updatesdk.a.b.b;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.common.PackageConstants;
import com.huawei.updatesdk.a.a.c.d;
import com.huawei.updatesdk.service.c.g;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends AsyncTask<Void, Void, Void> {
    private HttpURLConnection a;
    private com.huawei.updatesdk.a.b.b.a c;
    private boolean b = false;
    private Handler d = new a();

    private static class a extends Handler {
        private a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.obj instanceof com.huawei.updatesdk.a.b.b.a) {
                com.huawei.updatesdk.a.b.b.a aVar = (com.huawei.updatesdk.a.b.b.a) message.obj;
                Intent intent = new Intent();
                com.huawei.updatesdk.a.b.c.b bVarA = com.huawei.updatesdk.a.b.c.b.a(intent);
                switch (message.what) {
                    case 2:
                        intent.putExtra("download_apk_size", aVar.b());
                        intent.putExtra("download_apk_already", aVar.a());
                        com.huawei.updatesdk.service.b.c.a().b(bVarA);
                        return;
                    case 3:
                    case 5:
                        break;
                    case 4:
                        g.a(b.d() + "/appmarket.apk", PackageConstants.SERVICES_PACKAGE_APPMARKET);
                        break;
                    default:
                        return;
                }
                Bundle bundle = new Bundle();
                bundle.putInt("download_status_param", message.what);
                intent.putExtras(bundle);
                com.huawei.updatesdk.service.b.c.a().a(bVarA);
            }
        }
    }

    public b(com.huawei.updatesdk.a.b.b.a aVar) {
        this.c = aVar;
    }

    private void a(int i, int i2, int i3) {
        com.huawei.updatesdk.a.b.b.a aVar = new com.huawei.updatesdk.a.b.b.a();
        aVar.a(i);
        aVar.b(i2);
        if (this.d == null) {
            return;
        }
        this.d.sendMessage(this.d.obtainMessage(i3, aVar));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:13:0x0040
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private void a(com.huawei.updatesdk.a.b.b.a r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r3 = this;
            if (r4 == 0) goto La1
            java.lang.String r0 = r4.c()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Le
            goto La1
        Le:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.String r5 = "/appmarket.apk"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53 java.io.FileNotFoundException -> L74
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53 java.io.FileNotFoundException -> L74
            boolean r4 = r3.a(r4, r2, r5)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b java.io.FileNotFoundException -> L4e
            if (r4 != 0) goto L3c
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b java.io.FileNotFoundException -> L4e
            java.lang.String r5 = d()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b java.io.FileNotFoundException -> L4e
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b java.io.FileNotFoundException -> L4e
            com.huawei.updatesdk.a.a.c.d.a(r4)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b java.io.FileNotFoundException -> L4e
        L3c:
            r2.close()     // Catch: java.io.IOException -> L40
            goto L92
        L40:
            java.lang.String r4 = "DownloadMarketTask"
            java.lang.String r5 = "Close FileOutputStream failed!"
            com.huawei.updatesdk.a.a.b.a.a.a.d(r4, r5)
            goto L92
        L48:
            r4 = move-exception
            r1 = r2
            goto L93
        L4b:
            r4 = move-exception
            r1 = r2
            goto L54
        L4e:
            r4 = move-exception
            r1 = r2
            goto L75
        L51:
            r4 = move-exception
            goto L93
        L53:
            r4 = move-exception
        L54:
            java.lang.String r5 = "DownloadMarketTask"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r0.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = "download market package Exception error:"
            r0.append(r2)     // Catch: java.lang.Throwable -> L51
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L51
            r0.append(r4)     // Catch: java.lang.Throwable -> L51
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> L51
            com.huawei.updatesdk.a.a.b.a.a.a.d(r5, r4)     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L92
        L70:
            r1.close()     // Catch: java.io.IOException -> L40
            goto L92
        L74:
            r4 = move-exception
        L75:
            java.lang.String r5 = "DownloadMarketTask"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r0.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = "download market package FileNotFoundException error:"
            r0.append(r2)     // Catch: java.lang.Throwable -> L51
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L51
            r0.append(r4)     // Catch: java.lang.Throwable -> L51
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> L51
            com.huawei.updatesdk.a.a.b.a.a.a.d(r5, r4)     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L92
            goto L70
        L92:
            return
        L93:
            if (r1 == 0) goto La0
            r1.close()     // Catch: java.io.IOException -> L99
            goto La0
        L99:
            java.lang.String r5 = "DownloadMarketTask"
            java.lang.String r0 = "Close FileOutputStream failed!"
            com.huawei.updatesdk.a.a.b.a.a.a.d(r5, r0)
        La0:
            throw r4
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.b.b.b.a(com.huawei.updatesdk.a.b.b.a, java.lang.String):void");
    }

    private void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                com.huawei.updatesdk.a.a.b.a.a.a.d("DownloadMarketTask", "downloadUrlToStream(String urlString,OutputStream outputStream) " + e.toString());
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                com.huawei.updatesdk.a.a.b.a.a.a.d("DownloadMarketTask", "downloadUrlToStream(String urlString,OutputStream outputStream) " + e2.toString());
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 8, insn: 0x00f1: MOVE (r5 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:73:0x00f1 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x00f4: MOVE (r5 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:75:0x00f4 */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.huawei.updatesdk.a.b.b.a r18, java.io.OutputStream r19, java.lang.String r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.b.b.b.a(com.huawei.updatesdk.a.b.b.a, java.io.OutputStream, java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d() {
        String strC = com.huawei.updatesdk.a.b.a.a.a().c();
        if (TextUtils.isEmpty(strC)) {
            return "";
        }
        String str = strC + "/updatesdk";
        File file = new File(str);
        return (file.exists() || file.mkdirs()) ? str : "";
    }

    private void e() {
        if (this.a != null) {
            this.a.disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Void... voidArr) throws Throwable {
        com.huawei.updatesdk.service.d.a.c.a(this);
        a(this.c, d());
        return null;
    }

    public void a() {
        if (this.a != null) {
            this.a.disconnect();
            this.b = true;
        }
        d.a(new File(d()));
    }

    public void b() {
        this.d = null;
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        a();
    }
}
