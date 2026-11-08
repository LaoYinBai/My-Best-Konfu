package com.ss.android.download.api.config;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadNetworkFactory {

    public interface CompressType {
        public static final int DEFLATER = 2;
        public static final int GZIP = 1;
        public static final int NONE = 0;
    }

    public interface ContentType {
        public static final String JSON = "application/json; charset=utf-8";
        public static final String TT_DATA = "application/octet-stream;tt-data=a";
    }

    void execute(String str, String str2, Map<String, Object> map, IHttpCallback iHttpCallback);

    void postBody(String str, byte[] bArr, String str2, int i, IHttpCallback iHttpCallback);
}
