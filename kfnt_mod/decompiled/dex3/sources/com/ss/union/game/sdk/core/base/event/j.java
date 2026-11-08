package com.ss.union.game.sdk.core.base.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.provider.Settings;
import java.io.File;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.channels.FileLock;
import java.security.SecureRandom;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class j {
    private static final String a = "openudid";
    private static final String b = "clientudid";
    private static final int c = 13;
    private static final int d = 128;
    private static final String e = "game_openudid.dat";
    private static final String f = "game_clientudid.dat";
    private static final String g = "game_snssdk_openudid";

    j() {
    }

    @SuppressLint({"TrulyRandom", "HardwareIds"})
    public static synchronized String a(Context context) {
        String string;
        try {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e2) {
            g.b("exception when getting ANDROID_ID: ", e2);
            string = null;
        }
        try {
            if (a(string)) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(g, 0);
                String string2 = sharedPreferences.getString("openudid", null);
                if (!b(string2)) {
                    string2 = new BigInteger(64, new SecureRandom()).toString(16);
                    if (string2.charAt(0) == '-') {
                        string2 = string2.substring(1);
                    }
                    int length = 13 - string2.length();
                    if (length > 0) {
                        StringBuilder sb = new StringBuilder();
                        while (length > 0) {
                            sb.append('F');
                            length--;
                        }
                        sb.append(string2);
                        string2 = sb.toString();
                    }
                    String strA = a(e, string2);
                    if (b(strA)) {
                        string2 = strA;
                    }
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString("openudid", string2);
                    editorEdit.apply();
                }
                string = string2;
            }
        } catch (Exception e3) {
            g.b("exception when making openudid:  ", e3);
        }
        return string;
    }

    private static boolean a(String str) {
        return str == null || str.equals("9774d56d682e549c") || str.length() < 13;
    }

    public static synchronized String b(Context context) {
        String string;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(g, 0);
            string = sharedPreferences.getString("clientudid", null);
            if (!b(string)) {
                string = UUID.randomUUID().toString();
                String strA = a(f, string);
                if (b(strA)) {
                    string = strA;
                }
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("clientudid", string);
                editorEdit.apply();
            }
        } catch (Exception e2) {
            g.b("exception when making client_udid: ", e2);
            return "";
        }
        return string;
    }

    private static boolean b(String str) {
        int length;
        if (str == null || (length = str.length()) < 13 || length > d) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'a' || cCharAt > 'f') && ((cCharAt < 'A' || cCharAt > 'F') && cCharAt != '-'))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.io.RandomAccessFile] */
    private static String a(String str, String str2) throws Throwable {
        Throwable th;
        Exception e2;
        FileLock fileLockLock;
        byte[] bArr;
        int i;
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return str2;
        }
        String str3 = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.snssdk.api/cache";
        RandomAccessFile randomAccessFile = str3 + "/" + str;
        FileLock fileLock = null;
        try {
            try {
                File file = new File(str3);
                if (!file.exists() && !file.mkdirs()) {
                    return str2;
                }
                File file2 = new File((String) randomAccessFile);
                randomAccessFile = new RandomAccessFile(file2, "rwd");
                try {
                    fileLockLock = randomAccessFile.getChannel().lock();
                } catch (Exception e3) {
                    e2 = e3;
                }
                try {
                    if (file2.isFile() && (i = randomAccessFile.read((bArr = new byte[129]), 0, 129)) > 0 && i < 129) {
                        String str4 = new String(bArr, 0, i, "UTF-8");
                        if (b(str4)) {
                            if (fileLockLock != null) {
                                try {
                                    fileLockLock.release();
                                } catch (Exception unused) {
                                }
                            }
                            try {
                                randomAccessFile.close();
                            } catch (Exception unused2) {
                            }
                            return str4;
                        }
                    }
                    byte[] bytes = str2.getBytes("UTF-8");
                    randomAccessFile.setLength(0L);
                    randomAccessFile.write(bytes);
                    if (fileLockLock != null) {
                        try {
                            fileLockLock.release();
                        } catch (Exception unused3) {
                        }
                    }
                    try {
                        randomAccessFile.close();
                    } catch (Exception unused4) {
                    }
                    return str2;
                } catch (Exception e4) {
                    e2 = e4;
                    fileLock = fileLockLock;
                    g.b("load openudid exception ", e2);
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Exception unused5) {
                        }
                    }
                    if (randomAccessFile != 0) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception unused6) {
                        }
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    fileLock = fileLockLock;
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Exception unused7) {
                        }
                    }
                    if (randomAccessFile == 0) {
                        throw th;
                    }
                    try {
                        randomAccessFile.close();
                        throw th;
                    } catch (Exception unused8) {
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e5) {
            e2 = e5;
            randomAccessFile = 0;
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile = 0;
        }
    }
}
