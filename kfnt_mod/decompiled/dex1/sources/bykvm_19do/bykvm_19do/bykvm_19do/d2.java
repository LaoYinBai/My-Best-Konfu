package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Build;
import android.os.Environment;
import com.appsflyer.share.Constants;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
abstract class d2 {

    /* renamed from: a, reason: collision with root package name */
    boolean f1323a;

    /* renamed from: b, reason: collision with root package name */
    boolean f1324b;

    /* renamed from: c, reason: collision with root package name */
    boolean f1325c;
    boolean d;

    d2(boolean z, boolean z2) {
        this.f1324b = z;
        this.f1325c = z2;
        this.d = false;
    }

    String a(String str, String str2) throws Throwable {
        RandomAccessFile randomAccessFile;
        IOException e;
        RandomAccessFile randomAccessFile2;
        FileLock fileLockLock;
        byte[] bArr;
        int i;
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return str2;
        }
        String str3 = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.snssdk.api.embed/cache";
        String str4 = str3 + Constants.URL_PATH_DELIMITER + str;
        FileLock fileLock = null;
        try {
            File file = new File(str3);
            if (!file.exists() && !file.mkdirs()) {
                return str2;
            }
            File file2 = new File(str4);
            randomAccessFile2 = new RandomAccessFile(file2, "rwd");
            try {
                try {
                    fileLockLock = randomAccessFile2.getChannel().lock();
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                if (file2.isFile() && (i = randomAccessFile2.read((bArr = new byte[129]), 0, 129)) > 0 && i < 129) {
                    String str5 = new String(bArr, 0, i, "UTF-8");
                    if (j2.a(str5)) {
                        if (fileLockLock != null) {
                            try {
                                fileLockLock.release();
                            } catch (Exception unused) {
                            }
                        }
                        try {
                            randomAccessFile2.close();
                        } catch (Exception unused2) {
                        }
                        return str5;
                    }
                }
                byte[] bytes = str2.getBytes("UTF-8");
                randomAccessFile2.setLength(0L);
                randomAccessFile2.write(bytes);
                if (fileLockLock != null) {
                    try {
                        fileLockLock.release();
                    } catch (Exception unused3) {
                    }
                }
                try {
                    randomAccessFile2.close();
                } catch (Exception unused4) {
                }
                return str2;
            } catch (IOException e3) {
                e = e3;
                fileLock = fileLockLock;
                if (Build.VERSION.SDK_INT >= 23) {
                    if (u1.i().f1393c.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                        throw new SecurityException(e);
                    }
                    l0.a(e);
                }
                if (fileLock != null) {
                    try {
                        fileLock.release();
                    } catch (Exception unused5) {
                    }
                }
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (Exception unused6) {
                    }
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                fileLock = fileLockLock;
                Throwable th3 = th;
                randomAccessFile = randomAccessFile2;
                th = th3;
                if (fileLock != null) {
                    try {
                        fileLock.release();
                    } catch (Exception unused7) {
                    }
                }
                if (randomAccessFile == null) {
                    throw th;
                }
                try {
                    randomAccessFile.close();
                    throw th;
                } catch (Exception unused8) {
                    throw th;
                }
            }
        } catch (IOException e4) {
            e = e4;
            randomAccessFile2 = null;
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile = null;
        }
    }

    protected abstract boolean a(JSONObject jSONObject);

    d2(boolean z, boolean z2, boolean z3) {
        this.f1324b = z;
        this.f1325c = z2;
        this.d = z3;
    }
}
