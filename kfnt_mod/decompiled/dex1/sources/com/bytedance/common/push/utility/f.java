package com.bytedance.common.push.utility;

import android.util.Pair;
import anet.channel.util.HttpConstant;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class f {

    /* renamed from: a, reason: collision with root package name */
    private static f f3842a = new e();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public boolean f3843a;
    }

    public abstract String a(String str, List<Pair<String, String>> list, Map<String, String> map, a aVar) throws b;

    public abstract String a(String str, Map<String, String> map, a aVar) throws Exception;

    public abstract String a(String str, byte[] bArr, Map<String, String> map, a aVar) throws b;

    public static void a(f fVar) {
        if (fVar == null || fVar == f3842a) {
            return;
        }
        f3842a = fVar;
    }

    public static f a() {
        return f3842a;
    }

    public static byte[] a(byte[] bArr) throws Exception {
        GZIPOutputStream gZIPOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream2.write(bArr);
                gZIPOutputStream2.close();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                th = th;
                gZIPOutputStream = gZIPOutputStream2;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String a(String str) throws Exception {
        a aVar = new a();
        aVar.f3843a = true;
        return a(str, null, aVar);
    }

    public String a(String str, List<Pair<String, String>> list) throws b {
        a aVar = new a();
        aVar.f3843a = true;
        return a(str, list, (Map<String, String>) null, aVar);
    }

    public String a(String str, byte[] bArr, boolean z, String str2, boolean z2) throws b {
        HashMap map = new HashMap();
        if (z) {
            try {
                bArr = a(bArr);
                map.put("Content-Encoding", HttpConstant.GZIP);
            } catch (Exception e) {
                throw new b(0, e.getMessage());
            }
        }
        if (!i.a(str2)) {
            map.put("Content-Type", str2);
        }
        a aVar = new a();
        aVar.f3843a = z2;
        return a(str, bArr, map, aVar);
    }
}
