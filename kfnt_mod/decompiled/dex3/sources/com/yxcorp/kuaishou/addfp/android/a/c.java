package com.yxcorp.kuaishou.addfp.android.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static volatile c a;
    private e b;
    private ConcurrentHashMap c = new ConcurrentHashMap(10);
    private Context d;

    private c(Context context) {
        this.b = null;
        try {
            this.d = context;
            this.b = new e(this.d);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c(context);
                }
            }
        }
        return a;
    }

    private String a(HashMap map) throws Throwable {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream = objectOutputStream2;
        }
        try {
            objectOutputStream.writeObject(map);
            String str = new String(Base64.encode(com.yxcorp.kuaishou.addfp.android.b.b.a(byteArrayOutputStream.toByteArray(), "20212102sjcudiab".getBytes()), 0));
            try {
                objectOutputStream.close();
            } catch (IOException unused) {
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static void a(Context context, String str) throws Throwable {
        FileWriter fileWriter;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        FileWriter fileWriter2 = null;
        try {
            try {
                File file = new File(com.yxcorp.kuaishou.addfp.android.b.b.a(context, false), new String(Base64.decode("Lm91a2R0ZnQ=", 0)));
                new File(file.getParent()).mkdirs();
                fileWriter = new FileWriter(file, false);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
            try {
                fileWriter.close();
            } catch (IOException unused) {
            }
        } catch (Throwable th3) {
            th = th3;
            fileWriter2 = fileWriter;
            if (fileWriter2 != null) {
                try {
                    fileWriter2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public final synchronized Pair a() {
        String strB;
        byte[] bArrDecode;
        byte[] bArrDecode2;
        try {
            if (this.c != null) {
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty((String) this.c.get("cache_e"))) {
                    return Pair.create(Pair.create(Boolean.TRUE, sb.toString()), this.c);
                }
            }
            this.c.clear();
            strB = this.b.b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (!TextUtils.isEmpty(strB)) {
            try {
                bArrDecode = Base64.decode(strB, 0);
            } catch (Throwable unused) {
                bArrDecode = null;
            }
            if (bArrDecode == null) {
                try {
                    bArrDecode2 = Base64.decode(strB, 8);
                } catch (Throwable unused2) {
                }
                try {
                    JSONObject jSONObject = new JSONObject(new String(com.yxcorp.kuaishou.addfp.android.b.b.b(bArrDecode2, "20212102sjcudiab".getBytes())));
                    this.c.put("cache_e", jSONObject.optString("cache_e", ""));
                    this.c.put("cache_m", jSONObject.optString("cache_m", ""));
                    this.c.put("c_time", Long.toString(jSONObject.optLong("c_time", 0L)));
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            } else {
                bArrDecode2 = bArrDecode;
                JSONObject jSONObject2 = new JSONObject(new String(com.yxcorp.kuaishou.addfp.android.b.b.b(bArrDecode2, "20212102sjcudiab".getBytes())));
                this.c.put("cache_e", jSONObject2.optString("cache_e", ""));
                this.c.put("cache_m", jSONObject2.optString("cache_m", ""));
                this.c.put("c_time", Long.toString(jSONObject2.optLong("c_time", 0L)));
            }
            return Pair.create(Pair.create(Boolean.TRUE, "8"), null);
        }
        if (this.c != null && this.c.size() > 0) {
            StringBuilder sb2 = new StringBuilder();
            if (!TextUtils.isEmpty((String) this.c.get("cache_e"))) {
                return Pair.create(Pair.create(Boolean.TRUE, sb2.toString()), this.c);
            }
        }
        return Pair.create(Pair.create(Boolean.TRUE, "8"), null);
    }

    public final LinkedHashMap a(String str) throws Throwable {
        ObjectInputStream objectInputStream = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            try {
                if (!TextUtils.isEmpty(str)) {
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(new ByteArrayInputStream(com.yxcorp.kuaishou.addfp.android.b.b.b(Base64.decode(str.getBytes(), 0), "20212102sjcudiab".getBytes())));
                    try {
                        LinkedHashMap linkedHashMap = (LinkedHashMap) objectInputStream2.readObject();
                        try {
                            objectInputStream2.close();
                        } catch (IOException unused) {
                        }
                        return linkedHashMap;
                    } catch (Throwable th) {
                        th = th;
                        objectInputStream = objectInputStream2;
                        th.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return new LinkedHashMap();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
            return new LinkedHashMap();
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0058 A[Catch: all -> 0x0063, Throwable -> 0x0066, TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0002, B:6:0x0046, B:11:0x0050, B:15:0x0058, B:19:0x0066, B:21:0x0074, B:23:0x0087, B:24:0x008d, B:26:0x0093, B:28:0x0099, B:30:0x00a5, B:32:0x00ab, B:33:0x00ae, B:35:0x00b9, B:38:0x00cd, B:40:0x00d7, B:42:0x00f1, B:37:0x00bf, B:46:0x00f9), top: B:55:0x0002, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[Catch: all -> 0x0063, Throwable -> 0x00f8, TryCatch #4 {Throwable -> 0x00f8, blocks: (B:19:0x0066, B:21:0x0074, B:23:0x0087, B:24:0x008d, B:26:0x0093, B:28:0x0099, B:30:0x00a5, B:32:0x00ab, B:33:0x00ae, B:35:0x00b9, B:38:0x00cd, B:40:0x00d7, B:42:0x00f1, B:37:0x00bf), top: B:59:0x0066, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5 A[Catch: all -> 0x0063, Throwable -> 0x00f8, TryCatch #4 {Throwable -> 0x00f8, blocks: (B:19:0x0066, B:21:0x0074, B:23:0x0087, B:24:0x008d, B:26:0x0093, B:28:0x0099, B:30:0x00a5, B:32:0x00ab, B:33:0x00ae, B:35:0x00b9, B:38:0x00cd, B:40:0x00d7, B:42:0x00f1, B:37:0x00bf), top: B:59:0x0066, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00bf A[Catch: all -> 0x0063, Throwable -> 0x00f8, TryCatch #4 {Throwable -> 0x00f8, blocks: (B:19:0x0066, B:21:0x0074, B:23:0x0087, B:24:0x008d, B:26:0x0093, B:28:0x0099, B:30:0x00a5, B:32:0x00ab, B:33:0x00ae, B:35:0x00b9, B:38:0x00cd, B:40:0x00d7, B:42:0x00f1, B:37:0x00bf), top: B:59:0x0066, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d7 A[Catch: all -> 0x0063, Throwable -> 0x00f8, TryCatch #4 {Throwable -> 0x00f8, blocks: (B:19:0x0066, B:21:0x0074, B:23:0x0087, B:24:0x008d, B:26:0x0093, B:28:0x0099, B:30:0x00a5, B:32:0x00ab, B:33:0x00ae, B:35:0x00b9, B:38:0x00cd, B:40:0x00d7, B:42:0x00f1, B:37:0x00bf), top: B:59:0x0066, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void a(java.lang.String r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yxcorp.kuaishou.addfp.android.a.c.a(java.lang.String, java.lang.String):void");
    }

    public final String b(String str) {
        try {
            File file = new File(com.yxcorp.kuaishou.addfp.android.b.b.a(this.d, false), new String(Base64.decode(str, 0)));
            if (!file.exists()) {
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return sb.toString().trim();
                }
                sb.append(line);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
