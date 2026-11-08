package com.bytedance.sdk.openadsdk.downloadnew;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static HttpURLConnection a(String str, Map<String, String> map) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setRequestProperty("accept", "*/*");
                httpURLConnection.setRequestProperty("connection", "Keep-Alive");
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                return ((responseCode < 200 || responseCode >= 300) && responseCode >= 300 && responseCode < 400) ? a(httpURLConnection.getHeaderField("Location"), map) : httpURLConnection;
            } catch (Exception unused) {
                return httpURLConnection;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap map = new HashMap();
        int size = httpURLConnection.getHeaderFields().size();
        for (int i = 0; i < size; i++) {
            map.put(httpURLConnection.getHeaderFieldKey(i), httpURLConnection.getHeaderField(i));
        }
        return map;
    }

    public static a a(String str, List<HttpHeader> list) throws IOException {
        int responseCode;
        HashMap map = new HashMap();
        if (list != null && !list.isEmpty()) {
            for (HttpHeader httpHeader : list) {
                map.put(httpHeader.getName(), httpHeader.getValue());
            }
        }
        HttpURLConnection httpURLConnectionA = a(str, map);
        if (httpURLConnectionA == null || (responseCode = httpURLConnectionA.getResponseCode()) < 200 || responseCode >= 300) {
            return null;
        }
        Map<String, String> mapA = a(httpURLConnectionA);
        InputStream inputStream = httpURLConnectionA.getInputStream();
        String contentEncoding = httpURLConnectionA.getContentEncoding();
        if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.contains(HttpConstant.GZIP)) {
            inputStream = new GZIPInputStream(inputStream);
        }
        return new a(inputStream, mapA, responseCode, httpURLConnectionA);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public InputStream f5290a;

        /* renamed from: b, reason: collision with root package name */
        public Map<String, String> f5291b;

        /* renamed from: c, reason: collision with root package name */
        public int f5292c;
        public HttpURLConnection d;

        public a(InputStream inputStream, Map<String, String> map, int i, HttpURLConnection httpURLConnection) {
            this.f5290a = inputStream;
            this.f5291b = map;
            this.f5292c = i;
            this.d = httpURLConnection;
        }
    }
}
