package com.vivo.push.util;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class t implements c {
    private static final String a = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".vivo/pushsdk/config";
    private static final String b = a + File.separator + "config.txt";
    private static String c = "SdcardCache";
    private File d;

    t() {
    }

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) throws IOException {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return false;
        }
        File file = new File(a);
        boolean zMkdirs = !file.exists() ? file.mkdirs() : true;
        if (!zMkdirs) {
            return zMkdirs;
        }
        this.d = new File(b);
        if (this.d.exists()) {
            return zMkdirs;
        }
        try {
            this.d.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        return a().getProperty(str, str2);
    }

    @Override // com.vivo.push.util.c
    public final void b(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        Properties propertiesA = a();
        String str3 = b;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                propertiesA.setProperty(str, str2);
                fileOutputStream = new FileOutputStream(str3);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            propertiesA.store(fileOutputStream, (String) null);
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
            } catch (Exception unused) {
            }
        } catch (Exception e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception unused2) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
    }

    private static Properties a() throws Throwable {
        BufferedInputStream bufferedInputStream;
        Properties properties = new Properties();
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(b));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
        } catch (IOException unused) {
        }
        try {
            properties.load(bufferedInputStream);
            bufferedInputStream.close();
        } catch (Exception e2) {
            e = e2;
            bufferedInputStream2 = bufferedInputStream;
            e.printStackTrace();
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            return properties;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
        return properties;
    }
}
