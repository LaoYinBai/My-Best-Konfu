package com.kwad.sdk.api.loader;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.heytap.mcssdk.mode.CommandMessage;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.sdk.api.core.IKsAdSDK;
import com.kwad.sdk.api.core.RequestParamsUtils;
import com.kwad.sdk.api.core.TLSConnectionUtils;
import com.kwad.sdk.api.loader.a;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class e {
    private String a;
    private String b;
    private int c;
    private Map<String, String> d = new HashMap();
    private IKsAdSDK e;

    public interface a {
        void a(a.b bVar);

        void a(Exception exc);
    }

    e(String str, IKsAdSDK iKsAdSDK) {
        this.b = str;
        this.a = str;
        this.e = iKsAdSDK;
    }

    private String a() throws JSONException {
        int sDKVersionCode;
        String strValueOf = String.valueOf(Loader.get().a(Loader.get().getContext()));
        IKsAdSDK ksAdSDKImpl = Loader.get().getKsAdSDKImpl();
        if (ksAdSDKImpl != null) {
            if (TextUtils.isEmpty(strValueOf)) {
                strValueOf = ksAdSDKImpl.getSDKVersion();
            }
            sDKVersionCode = ksAdSDKImpl.getSDKVersionCode();
        } else {
            sDKVersionCode = -1;
        }
        JSONObject appInfo = Loader.get().getKsAdSDKImpl().getAppInfo();
        JSONObject deviceInfo = Loader.get().getKsAdSDKImpl().getDeviceInfo();
        JSONObject networkInfo = Loader.get().getKsAdSDKImpl().getNetworkInfo();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdkApiVersion", "3.3.17");
            jSONObject.put("sdkApiVersionCode", 3031700);
            jSONObject.put(CommandMessage.SDK_VERSION, strValueOf);
            jSONObject.put("SDKVersionCode", sDKVersionCode);
            jSONObject.put("sdkType", 1);
            jSONObject.put("appInfo", appInfo);
            jSONObject.put("deviceInfo", deviceInfo);
            jSONObject.put("networkInfo", networkInfo);
            jSONObject.put("sdkAbi", s.b());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.e == null) {
            return jSONObject.toString();
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, "3.3.17");
            jSONObject2.put("appId", appInfo.optString("appId"));
            jSONObject2.put("message", this.e.getRM(jSONObject.toString()));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.e.sR(this.b.replace("https://open.e.kuaishou.com", ""), this.d, jSONObject2.toString());
        return jSONObject2.toString();
    }

    private static String a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                try {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } finally {
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        }
        String str = new String(byteArrayOutputStream.toByteArray());
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        return str;
    }

    private URLConnection a(String str, int i, int i2, boolean z) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
        TLSConnectionUtils.wrapHttpURLConnection(uRLConnectionOpenConnection);
        a("Accept-Language", "zh-CN");
        if (i > 0) {
            uRLConnectionOpenConnection.setConnectTimeout(i);
        }
        if (i2 > 0) {
            uRLConnectionOpenConnection.setReadTimeout(i2);
        }
        uRLConnectionOpenConnection.setUseCaches(z);
        uRLConnectionOpenConnection.setDoInput(true);
        a("Connection", "keep-alive");
        a("Charset", C.UTF8_NAME);
        return uRLConnectionOpenConnection;
    }

    private void a(String str, String str2) {
        this.d.put(str, str2);
    }

    @Nullable
    private HttpURLConnection b() throws ProtocolException {
        if (TextUtils.isEmpty(this.a)) {
            return null;
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) a(this.a, 10000, 30000, false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(true);
        a("Content-Type", "application/json; charset=UTF-8");
        a("User-Agent", RequestParamsUtils.getUserAgent());
        if (this.e != null) {
            this.e.addHp(this.d);
        }
        return httpURLConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.kwad.sdk.api.loader.e.a r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = 0
            java.net.HttpURLConnection r1 = r7.b()     // Catch: java.lang.Throwable -> Lbf java.lang.Exception -> Lc2
            if (r1 == 0) goto Lb9
            java.lang.String r0 = r7.a()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.util.Map<java.lang.String, java.lang.String> r2 = r7.d     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            if (r2 == 0) goto L35
            java.util.Map<java.lang.String, java.lang.String> r2 = r7.d     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.util.Set r2 = r2.entrySet()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
        L19:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            if (r3 == 0) goto L35
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.Object r4 = r3.getKey()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.Object r3 = r3.getValue()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r1.setRequestProperty(r4, r3)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            goto L19
        L35:
            r1.connect()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r2.<init>(r3)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r2.write(r0)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            int r0 = r1.getResponseCode()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L83
            java.io.InputStream r0 = r1.getInputStream()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r0 = a(r0)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            com.kwad.sdk.api.loader.a$b r2 = new com.kwad.sdk.api.loader.a$b     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r2.<init>()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r3.<init>(r0)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            com.kwad.sdk.api.core.IKsAdSDK r0 = r7.e     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            if (r0 == 0) goto L7c
            com.kwad.sdk.api.core.IKsAdSDK r0 = r7.e     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r4 = "data"
            java.lang.String r4 = r3.optString(r4)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r0 = r0.getRD(r4)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r4 = "data"
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r5.<init>(r0)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r3.put(r4, r5)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
        L7c:
            r2.a(r3)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r8.a(r2)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            goto Lb9
        L83:
            int r2 = r0 / 100
            r3 = 3
            if (r2 != r3) goto La0
            int r0 = r7.c     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r2 = 21
            if (r0 >= r2) goto Lb9
            java.lang.String r0 = "Location"
            java.lang.String r0 = r1.getHeaderField(r0)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r7.a = r0     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            int r0 = r7.c     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            int r0 = r0 + 1
            r7.c = r0     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r7.b(r8)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            goto Lb9
        La0:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r3.<init>()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r4 = "response code = "
            r3.append(r4)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r3.append(r0)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            r2.<init>(r0)     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
            throw r2     // Catch: java.lang.Exception -> Lb7 java.lang.Throwable -> Lcd
        Lb7:
            r0 = move-exception
            goto Lc6
        Lb9:
            if (r1 == 0) goto Lcc
        Lbb:
            r1.disconnect()     // Catch: java.lang.Exception -> Lcc
            goto Lcc
        Lbf:
            r8 = move-exception
            r1 = r0
            goto Lce
        Lc2:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        Lc6:
            r8.a(r0)     // Catch: java.lang.Throwable -> Lcd
            if (r1 == 0) goto Lcc
            goto Lbb
        Lcc:
            return
        Lcd:
            r8 = move-exception
        Lce:
            if (r1 == 0) goto Ld3
            r1.disconnect()     // Catch: java.lang.Exception -> Ld3
        Ld3:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.api.loader.e.b(com.kwad.sdk.api.loader.e$a):void");
    }

    void a(final a aVar) {
        i.a(new Runnable() { // from class: com.kwad.sdk.api.loader.e.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                e.this.b(aVar);
            }
        });
    }
}
