package com.bytedance.push.g;

import android.text.TextUtils;
import android.util.Pair;
import anet.channel.util.HttpConstant;
import com.bytedance.common.push.utility.f;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends com.bytedance.common.push.utility.f {
    @Override // com.bytedance.common.push.utility.f
    public String a(String str, Map<String, String> map, f.a aVar) throws com.bytedance.common.push.utility.b {
        try {
            return a(str, map);
        } catch (Throwable th) {
            if (th instanceof c) {
                throw new com.bytedance.common.push.utility.b(((c) th).a(), th.getMessage());
            }
            throw new com.bytedance.common.push.utility.b(0, th.getMessage());
        }
    }

    @Override // com.bytedance.common.push.utility.f
    public String a(String str, List<Pair<String, String>> list, Map<String, String> map, f.a aVar) throws UnsupportedEncodingException, com.bytedance.common.push.utility.b {
        try {
            new JSONObject();
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < list.size(); i++) {
                Pair<String, String> pair = list.get(i);
                sb.append(((String) pair.first) + "=" + ((String) pair.second));
                if (i < list.size() - 1) {
                    sb.append("&");
                }
            }
            byte[] bytes = sb.toString().getBytes("UTF-8");
            HashMap map2 = new HashMap();
            aVar.f3843a = true;
            return a(str, bytes, map2, aVar);
        } catch (Exception e) {
            throw new com.bytedance.common.push.utility.b(0, e.getMessage());
        }
    }

    @Override // com.bytedance.common.push.utility.f
    public String a(String str, byte[] bArr, Map<String, String> map, f.a aVar) throws com.bytedance.common.push.utility.b {
        try {
            return a(str, bArr, map, "POST", true);
        } catch (Throwable th) {
            if (th instanceof c) {
                throw new com.bytedance.common.push.utility.b(((c) th).a(), th.getMessage());
            }
            throw new com.bytedance.common.push.utility.b(0, th.getMessage());
        }
    }

    public static String a(String str, Map<String, String> map) throws Throwable {
        if (TextUtils.isDigitsOnly(str)) {
            return null;
        }
        return a(str, (byte[]) null, map, "GET", false);
    }

    public static String a(final String str, byte[] bArr, Map<String, String> map, String str2, boolean z) throws Throwable {
        final HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                HashMap map2 = new HashMap();
                map2.put("business_push_version", "3.2.0-honor");
                com.ss.android.message.a.b.a(str, map2);
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            } catch (Throwable th) {
                th = th;
                httpURLConnection = httpURLConnection2;
            }
            try {
                httpURLConnection.setRequestMethod(str2);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setRequestProperty(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (!entry.getKey().isEmpty() && !entry.getValue().isEmpty()) {
                            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }
                }
                if (z) {
                    httpURLConnection.setDoOutput(true);
                } else {
                    httpURLConnection.setDoOutput(false);
                }
                if (str2 == null) {
                    throw new IllegalArgumentException("request method is not null");
                }
                if (bArr != null && bArr.length > 0) {
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(bArr);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    boolean zEquals = HttpConstant.GZIP.equals(httpURLConnection.getHeaderField("Content-Encoding"));
                    String headerField = httpURLConnection.getHeaderField("Content-Type");
                    boolean zA = h.a(headerField);
                    Pair<String, String> pairB = b(headerField);
                    String str3 = (pairB == null || pairB.second == null || !Charset.isSupported((String) pairB.second)) ? null : (String) pairB.second;
                    if (str3 == null) {
                        str3 = "UTF-8";
                    }
                    int[] iArr = {0};
                    byte[] bArrA = h.a(zEquals, -1, inputStream, iArr, new g() { // from class: com.bytedance.push.g.b.1
                        @Override // com.bytedance.push.g.g
                        public void a() {
                            try {
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    });
                    if (bArrA != null && iArr[0] > 0 && iArr[0] <= bArrA.length) {
                        if (zA) {
                            h.a(bArrA, iArr[0]);
                        }
                        String str4 = new String(bArrA, 0, iArr[0], str3);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused) {
                            }
                        }
                        return str4;
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused2) {
                        }
                    }
                    return null;
                }
                throw new c(responseCode, httpURLConnection.getResponseMessage());
            } catch (Throwable th2) {
                th = th2;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            throw th3;
        }
    }

    public static Pair<String, String> b(String str) {
        String strA;
        String strA2 = null;
        if (str == null) {
            return null;
        }
        try {
            d dVar = new d(str);
            strA = dVar.a();
            try {
                strA2 = dVar.a("charset");
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                return new Pair<>(strA, strA2);
            }
        } catch (Throwable th2) {
            th = th2;
            strA = null;
        }
        return new Pair<>(strA, strA2);
    }
}
