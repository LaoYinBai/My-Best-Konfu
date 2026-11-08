package com.huawei.hms.update.a;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.provider.UpdateProvider;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.FileUtil;
import com.huawei.hms.utils.IOUtils;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements com.huawei.hms.update.a.a.a {
    private final Context a;
    private final com.huawei.hms.update.b.d b = new com.huawei.hms.update.b.b();
    private final a c = new a();
    private com.huawei.hms.update.a.a.b d;
    private File e;

    public d(Context context) {
        this.a = context.getApplicationContext();
    }

    private synchronized void a(com.huawei.hms.update.a.a.b bVar) {
        this.d = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(int i, int i2, int i3) {
        if (this.d != null) {
            this.d.a(i, i2, i3, this.e);
        }
    }

    public Context b() {
        return this.a;
    }

    @Override // com.huawei.hms.update.a.a.a
    public void a() {
        HMSLog.i("UpdateDownload", "Enter cancel.");
        a((com.huawei.hms.update.a.a.b) null);
        this.b.b();
    }

    @Override // com.huawei.hms.update.a.a.a
    public void a(com.huawei.hms.update.a.a.b bVar, com.huawei.hms.update.a.a.c cVar) throws Throwable {
        Checker.checkNonNull(bVar, "callback must not be null.");
        HMSLog.i("UpdateDownload", "Enter downloadPackage.");
        a(bVar);
        if (cVar == null || !cVar.a()) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Invalid update info.");
            a(PushConstants.ON_TIME_NOTIFICATION, 0, 0);
            return;
        }
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Invalid external storage for downloading file.");
            a(2204, 0, 0);
            return;
        }
        String str = cVar.b;
        if (TextUtils.isEmpty(str)) {
            HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Download the package,  packageName is null: ");
            a(PushConstants.ON_TIME_NOTIFICATION, 0, 0);
            return;
        }
        this.e = UpdateProvider.getLocalFile(this.a, str + ".apk");
        if (this.e == null) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Failed to get local file for downloading.");
            a(2204, 0, 0);
            return;
        }
        File parentFile = this.e.getParentFile();
        if (parentFile == null || !(parentFile.mkdirs() || parentFile.isDirectory())) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Failed to create directory for downloading file.");
            a(PushConstants.ON_TIME_NOTIFICATION, 0, 0);
        } else if (parentFile.getUsableSpace() < cVar.d * 3) {
            HMSLog.e("UpdateDownload", "In downloadPackage, No space for downloading file.");
            a(2203, 0, 0);
        } else {
            try {
                a(cVar);
            } catch (com.huawei.hms.update.b.a unused) {
                HMSLog.w("UpdateDownload", "In downloadPackage, Canceled to download the update file.");
                a(2101, 0, 0);
            }
        }
    }

    void a(com.huawei.hms.update.a.a.c cVar) throws Throwable {
        b bVarA;
        HMSLog.i("UpdateDownload", "Enter downloadPackage.");
        b bVar = null;
        try {
            try {
                String str = cVar.b;
                if (TextUtils.isEmpty(str)) {
                    HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Download the package,  packageName is null: ");
                    a(PushConstants.ON_TIME_NOTIFICATION, 0, 0);
                    this.b.a();
                    IOUtils.closeQuietly((OutputStream) null);
                    return;
                }
                this.c.a(b(), str);
                if (!this.c.b(cVar.c, cVar.d, cVar.e)) {
                    this.c.a(cVar.c, cVar.d, cVar.e);
                    bVarA = a(this.e, cVar.d, str);
                } else if (this.c.b() != this.c.a()) {
                    bVarA = a(this.e, cVar.d, str);
                    try {
                        bVarA.a(this.c.b());
                    } catch (IOException e) {
                        e = e;
                        bVar = bVarA;
                        HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Failed to download." + e.getMessage());
                        a(PushConstants.ON_TIME_NOTIFICATION, 0, 0);
                        this.b.a();
                        IOUtils.closeQuietly((OutputStream) bVar);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        bVar = bVarA;
                        this.b.a();
                        IOUtils.closeQuietly((OutputStream) bVar);
                        throw th;
                    }
                } else {
                    if (FileUtil.verifyHash(cVar.e, this.e)) {
                        a(2000, 0, 0);
                        this.b.a();
                        IOUtils.closeQuietly((OutputStream) null);
                        return;
                    }
                    this.c.a(cVar.c, cVar.d, cVar.e);
                    bVarA = a(this.e, cVar.d, str);
                }
                int iA = this.b.a(cVar.c, bVarA, this.c.b(), this.c.a(), this.a);
                if (iA != 200 && iA != 206) {
                    HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Download the package, HTTP code: " + iA);
                    a(PushConstants.ON_TIME_NOTIFICATION, 0, 0);
                    this.b.a();
                    IOUtils.closeQuietly((OutputStream) bVarA);
                    return;
                }
                if (FileUtil.verifyHash(cVar.e, this.e)) {
                    a(2000, 0, 0);
                    this.b.a();
                    IOUtils.closeQuietly((OutputStream) bVarA);
                } else {
                    a(PushConstants.DELAY_NOTIFICATION, 0, 0);
                    this.b.a();
                    IOUtils.closeQuietly((OutputStream) bVarA);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    private b a(File file, final int i, final String str) throws IOException {
        return new b(file, i) { // from class: com.huawei.hms.update.a.d.1
            private long d = 0;
            private int e;

            {
                this.e = d.this.c.b();
            }

            @Override // com.huawei.hms.update.a.b, java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) throws IOException {
                super.write(bArr, i2, i3);
                this.e += i3;
                if (this.e > 209715200) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (Math.abs(jCurrentTimeMillis - this.d) > 1000) {
                    this.d = jCurrentTimeMillis;
                    a(this.e);
                }
                if (this.e == i) {
                    a(this.e);
                }
            }

            private void a(int i2) {
                d.this.c.a(d.this.b(), i2, str);
                d.this.a(PushConstants.BROADCAST_MESSAGE_ARRIVE, i2, i);
            }
        };
    }
}
