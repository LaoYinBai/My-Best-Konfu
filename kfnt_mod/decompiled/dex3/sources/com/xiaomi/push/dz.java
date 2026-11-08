package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.xiaomi.push.ai;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dz extends ai.a {
    private Context a;

    /* renamed from: a, reason: collision with other field name */
    private SharedPreferences f250a;

    /* renamed from: a, reason: collision with other field name */
    private com.xiaomi.push.service.aq f251a;

    public dz(Context context) {
        this.a = context;
        this.f250a = context.getSharedPreferences("mipush_extra", 0);
        this.f251a = com.xiaomi.push.service.aq.a(context);
    }

    private List<hr> a(File file) {
        FileLock fileLockLock;
        RandomAccessFile randomAccessFile;
        dk dkVarM201a = dl.a().m201a();
        String strA = dkVarM201a == null ? "" : dkVarM201a.a();
        FileInputStream fileInputStream = null;
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        synchronized (dr.a) {
            try {
                File file2 = new File(this.a.getExternalFilesDir(null), "push_cdata.lock");
                y.m650a(file2);
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    fileLockLock = randomAccessFile.getChannel().lock();
                    try {
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        while (fileInputStream2.read(bArr) == 4) {
                            try {
                                int iA = ac.a(bArr);
                                byte[] bArr2 = new byte[iA];
                                if (fileInputStream2.read(bArr2) != iA) {
                                    break;
                                }
                                byte[] bArrA = dq.a(strA, bArr2);
                                if (bArrA != null && bArrA.length != 0) {
                                    hr hrVar = new hr();
                                    it.a(hrVar, bArrA);
                                    arrayList.add(hrVar);
                                    a(hrVar);
                                }
                            } catch (Exception unused) {
                                fileInputStream = fileInputStream2;
                                if (fileLockLock != null && fileLockLock.isValid()) {
                                    try {
                                        fileLockLock.release();
                                    } catch (IOException unused2) {
                                    }
                                }
                                y.a(fileInputStream);
                                y.a(randomAccessFile);
                                return arrayList;
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileLockLock != null && fileLockLock.isValid()) {
                                    try {
                                        fileLockLock.release();
                                    } catch (IOException unused3) {
                                    }
                                }
                                y.a(fileInputStream);
                                y.a(randomAccessFile);
                                throw th;
                            }
                        }
                        if (fileLockLock != null && fileLockLock.isValid()) {
                            try {
                                fileLockLock.release();
                            } catch (IOException unused4) {
                            }
                        }
                        y.a(fileInputStream2);
                    } catch (Exception unused5) {
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception unused6) {
                    fileLockLock = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileLockLock = null;
                }
            } catch (Exception unused7) {
                fileLockLock = null;
                randomAccessFile = null;
            } catch (Throwable th4) {
                th = th4;
                fileLockLock = null;
                randomAccessFile = null;
            }
            y.a(randomAccessFile);
        }
        return arrayList;
    }

    private void a() {
        SharedPreferences.Editor editorEdit = this.f250a.edit();
        editorEdit.putLong("last_upload_data_timestamp", System.currentTimeMillis() / 1000);
        editorEdit.commit();
    }

    private void a(hr hrVar) {
        if (hrVar.f500a != hl.AppInstallList || hrVar.f501a.startsWith("same_")) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.f250a.edit();
        editorEdit.putLong("dc_job_result_time_4", hrVar.f499a);
        editorEdit.putString("dc_job_result_4", bm.a(hrVar.f501a));
        editorEdit.commit();
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m208a() {
        if (bg.e(this.a)) {
            return false;
        }
        if ((bg.g(this.a) || bg.f(this.a)) && !c()) {
            return true;
        }
        return (bg.h(this.a) && !b()) || bg.i(this.a);
    }

    private boolean b() {
        if (!this.f251a.a(ho.Upload3GSwitch.a(), true)) {
            return false;
        }
        return Math.abs((System.currentTimeMillis() / 1000) - this.f250a.getLong("last_upload_data_timestamp", -1L)) > ((long) Math.max(Constants.MAX_RETRY_AFTER, this.f251a.a(ho.Upload3GFrequency.a(), 432000)));
    }

    private boolean c() {
        if (!this.f251a.a(ho.Upload4GSwitch.a(), true)) {
            return false;
        }
        return Math.abs((System.currentTimeMillis() / 1000) - this.f250a.getLong("last_upload_data_timestamp", -1L)) > ((long) Math.max(Constants.MAX_RETRY_AFTER, this.f251a.a(ho.Upload4GFrequency.a(), 259200)));
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "1";
    }

    @Override // java.lang.Runnable
    public void run() throws IOException {
        File file = new File(this.a.getExternalFilesDir(null), "push_cdata.data");
        if (!bg.d(this.a)) {
            if (file.length() > 1863680) {
                file.delete();
                return;
            }
            return;
        }
        if (!m208a() && file.exists()) {
            List<hr> listA = a(file);
            if (!ad.a(listA)) {
                int size = listA.size();
                if (size > 4000) {
                    listA = listA.subList(size - 4000, size);
                }
                ic icVar = new ic();
                icVar.a(listA);
                byte[] bArrA = y.a(it.a(icVar));
                ii iiVar = new ii("-1", false);
                iiVar.c(ht.DataCollection.f509a);
                iiVar.a(bArrA);
                dk dkVarM201a = dl.a().m201a();
                if (dkVarM201a != null) {
                    dkVarM201a.a(iiVar, hj.Notification, null);
                }
                a();
            }
            file.delete();
        }
    }
}
