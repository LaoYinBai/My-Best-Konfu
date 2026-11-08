package com.bytedance.common.utility;

import android.util.Pair;
import anet.channel.util.HttpConstant;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class NetworkClient {
    private static final int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 8192;
    private static NetworkClient sDefault = new DummyNetworkClient();

    public static class ReqContext {
        public boolean addCommonParams;
        public boolean fetchResponseHeaders;
        public Map<String, String> responseHeaders;
    }

    public abstract String get(String str, Map<String, String> map, ReqContext reqContext) throws Exception;

    public abstract String post(String str, List<Pair<String, String>> list, Map<String, String> map, ReqContext reqContext) throws CommonHttpException;

    public abstract String post(String str, byte[] bArr, Map<String, String> map, ReqContext reqContext) throws CommonHttpException;

    public byte[] postDataStream(String str, byte[] bArr, Map<String, String> map, ReqContext reqContext) throws CommonHttpException {
        return null;
    }

    public static void setDefault(NetworkClient networkClient) {
        if (networkClient == null || networkClient == sDefault) {
            return;
        }
        sDefault = networkClient;
    }

    public static NetworkClient getDefault() {
        return sDefault;
    }

    public static byte[] compressWithgzip(byte[] bArr) throws Exception {
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

    public String get(String str) throws Exception {
        ReqContext reqContext = new ReqContext();
        reqContext.addCommonParams = true;
        return get(str, null, reqContext);
    }

    public String post(String str, List<Pair<String, String>> list) throws CommonHttpException {
        ReqContext reqContext = new ReqContext();
        reqContext.addCommonParams = true;
        return post(str, list, (Map<String, String>) null, reqContext);
    }

    public String post(String str, byte[] bArr, boolean z, String str2, boolean z2) throws CommonHttpException {
        HashMap map = new HashMap();
        if (z) {
            try {
                bArr = compressWithgzip(bArr);
                map.put("Content-Encoding", HttpConstant.GZIP);
            } catch (Exception e) {
                throw new CommonHttpException(0, e.getMessage());
            }
        }
        if (!StringUtils.isEmpty(str2)) {
            map.put("Content-Type", str2);
        }
        ReqContext reqContext = new ReqContext();
        reqContext.addCommonParams = z2;
        return post(str, bArr, map, reqContext);
    }
}
