package com.tachikoma.core.component.network.delegate;

import androidx.annotation.RequiresApi;
import com.tachikoma.core.api.IRequestDelegateInner;
import com.tachikoma.core.component.network.ResponseCallBackInner;
import com.umeng.message.util.HttpRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestConnectionDelegate implements IRequestDelegateInner {
    public static String METHOD_GET = "GET";
    public static String METHOD_POST = "POST";
    private Map<String, String> bodys;
    private Map<String, String> header;
    private String host;
    private Map<String, String> params;
    private String path;
    private String scheme;
    private int timeout;
    private Map<String, Object> userInfo;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 26)
    public void configHttpURLConn(HttpURLConnection httpURLConnection, HashMap<String, Object> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String string = entry.getKey().toString();
            Object value = entry.getValue();
            if (string.equals(HttpRequest.HEADER_AUTHORIZATION)) {
                value = "Basic " + new String(Base64.getEncoder().encode("username:password".getBytes()));
            }
            httpURLConnection.setRequestProperty(string, (String) value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private String param2String(HashMap<String, Object> map) {
        if (map == null) {
            return "";
        }
        String str = "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            str = str + "&" + entry.getKey().toString() + "=" + entry.getValue().toString();
        }
        return str;
    }

    private void sendHttpGetRequest(String str, String str2, final int i, final HashMap<String, Object> map, HashMap<String, Object> map2, final ResponseCallBackInner responseCallBackInner) {
        final String str3 = str + param2String(map2);
        new Thread(new Runnable() { // from class: com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.1
            /* JADX WARN: Removed duplicated region for block: B:34:0x0095  */
            @Override // java.lang.Runnable
            @androidx.annotation.RequiresApi(api = 26)
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() throws java.lang.Throwable {
                /*
                    r5 = this;
                    r0 = 0
                    java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L71 java.net.MalformedURLException -> L80
                    java.lang.String r2 = r2     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L71 java.net.MalformedURLException -> L80
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L71 java.net.MalformedURLException -> L80
                    java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L71 java.net.MalformedURLException -> L80
                    java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L71 java.net.MalformedURLException -> L80
                    java.lang.String r0 = "GET"
                    r1.setRequestMethod(r0)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    int r0 = r3     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r1.setConnectTimeout(r0)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r0 = 8000(0x1f40, float:1.121E-41)
                    r1.setReadTimeout(r0)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    java.lang.String r0 = "Content-Type"
                    java.lang.String r2 = "application/json"
                    r1.setRequestProperty(r0, r2)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate r0 = com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.this     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    java.util.HashMap r2 = r4     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.access$000(r0, r1, r2)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    int r0 = r1.getResponseCode()     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r2 = 200(0xc8, float:2.8E-43)
                    if (r0 == r2) goto L48
                    r1.getResponseCode()     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    com.tachikoma.core.component.network.ResponseCallBackInner r0 = r5     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    if (r0 == 0) goto L48
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$1 r0 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$1     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r0.<init>()     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r0)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    if (r1 == 0) goto L47
                    r1.disconnect()
                L47:
                    return
                L48:
                    java.io.InputStream r0 = r1.getInputStream()     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r2.<init>(r0)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate r3 = com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.this     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    java.lang.String r3 = com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.access$100(r3, r2)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r2.close()     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r0.close()     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$2 r0 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$2     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    r0.<init>()     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r0)     // Catch: java.io.IOException -> L68 java.net.MalformedURLException -> L6a java.lang.Throwable -> L92
                    if (r1 == 0) goto L91
                    goto L8e
                L68:
                    r0 = move-exception
                    goto L75
                L6a:
                    r0 = move-exception
                    goto L84
                L6c:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                    goto L93
                L71:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L75:
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$4 r2 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$4     // Catch: java.lang.Throwable -> L92
                    r2.<init>()     // Catch: java.lang.Throwable -> L92
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r2)     // Catch: java.lang.Throwable -> L92
                    if (r1 == 0) goto L91
                    goto L8e
                L80:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L84:
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$3 r2 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$1$3     // Catch: java.lang.Throwable -> L92
                    r2.<init>()     // Catch: java.lang.Throwable -> L92
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r2)     // Catch: java.lang.Throwable -> L92
                    if (r1 == 0) goto L91
                L8e:
                    r1.disconnect()
                L91:
                    return
                L92:
                    r0 = move-exception
                L93:
                    if (r1 == 0) goto L98
                    r1.disconnect()
                L98:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.AnonymousClass1.run():void");
            }
        }).start();
    }

    private void sendHttpPostRequest(String str, String str2, final int i, final HashMap<String, Object> map, HashMap<String, Object> map2, final ResponseCallBackInner responseCallBackInner) {
        final String str3 = str + param2String(map2);
        new Thread(new Runnable() { // from class: com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.2
            /* JADX WARN: Removed duplicated region for block: B:34:0x00b0  */
            @Override // java.lang.Runnable
            @androidx.annotation.RequiresApi(api = 26)
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() throws java.lang.Throwable {
                /*
                    r5 = this;
                    r0 = 0
                    java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8c java.net.MalformedURLException -> L9b
                    java.lang.String r2 = r2     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8c java.net.MalformedURLException -> L9b
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8c java.net.MalformedURLException -> L9b
                    java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8c java.net.MalformedURLException -> L9b
                    java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8c java.net.MalformedURLException -> L9b
                    java.lang.String r0 = "POST"
                    r1.setRequestMethod(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    int r0 = r3     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r1.setConnectTimeout(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r0 = 8000(0x1f40, float:1.121E-41)
                    r1.setReadTimeout(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate r0 = com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.this     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    java.util.HashMap r2 = r4     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.access$000(r0, r1, r2)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r0 = 1
                    r1.setDoInput(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r1.setDoInput(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    java.lang.String r0 = ""
                    java.io.OutputStream r2 = r1.getOutputStream()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r3.<init>(r2)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    byte[] r0 = r0.getBytes()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r3.write(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r3.flush()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r3.close()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r2.close()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    int r0 = r1.getResponseCode()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r2 = 200(0xc8, float:2.8E-43)
                    if (r0 == r2) goto L63
                    r1.getResponseCode()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    com.tachikoma.core.component.network.ResponseCallBackInner r0 = r5     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    if (r0 == 0) goto L63
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$1 r0 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$1     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r0.<init>()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    if (r1 == 0) goto L62
                    r1.disconnect()
                L62:
                    return
                L63:
                    java.io.InputStream r0 = r1.getInputStream()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r2.<init>(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate r3 = com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.this     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    java.lang.String r3 = com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.access$100(r3, r2)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r2.close()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r0.close()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$2 r0 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$2     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    r0.<init>()     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r0)     // Catch: java.io.IOException -> L83 java.net.MalformedURLException -> L85 java.lang.Throwable -> Lad
                    if (r1 == 0) goto Lac
                    goto La9
                L83:
                    r0 = move-exception
                    goto L90
                L85:
                    r0 = move-exception
                    goto L9f
                L87:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                    goto Lae
                L8c:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L90:
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$4 r2 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$4     // Catch: java.lang.Throwable -> Lad
                    r2.<init>()     // Catch: java.lang.Throwable -> Lad
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r2)     // Catch: java.lang.Throwable -> Lad
                    if (r1 == 0) goto Lac
                    goto La9
                L9b:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L9f:
                    com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$3 r2 = new com.tachikoma.core.component.network.delegate.RequestConnectionDelegate$2$3     // Catch: java.lang.Throwable -> Lad
                    r2.<init>()     // Catch: java.lang.Throwable -> Lad
                    com.tachikoma.core.utility.UIThreadUtil.runOnUiThread(r2)     // Catch: java.lang.Throwable -> Lad
                    if (r1 == 0) goto Lac
                La9:
                    r1.disconnect()
                Lac:
                    return
                Lad:
                    r0 = move-exception
                Lae:
                    if (r1 == 0) goto Lb3
                    r1.disconnect()
                Lb3:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.network.delegate.RequestConnectionDelegate.AnonymousClass2.run():void");
            }
        }).start();
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void get(ResponseCallBackInner responseCallBackInner) {
        send(this.path != null ? this.host.concat(this.path) : this.host, METHOD_GET, this.timeout, this.header != null ? new HashMap<>(this.header) : new HashMap<>(), this.params != null ? new HashMap<>(this.params) : new HashMap<>(), responseCallBackInner);
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void post(ResponseCallBackInner responseCallBackInner) {
        send(this.path != null ? this.host.concat(this.path) : this.host, METHOD_POST, this.timeout, this.header != null ? new HashMap<>(this.header) : new HashMap<>(), this.params != null ? new HashMap<>(this.params) : new HashMap<>(), responseCallBackInner);
    }

    public void send(String str, String str2, int i, HashMap<String, Object> map, HashMap<String, Object> map2, ResponseCallBackInner responseCallBackInner) {
        if (METHOD_GET.equals(str2)) {
            sendHttpGetRequest(str, str2, i, map, map2, responseCallBackInner);
        } else if (METHOD_POST.equals(str2)) {
            sendHttpPostRequest(str, str2, i, map, map2, responseCallBackInner);
        }
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setBody(Map<String, String> map) {
        this.bodys = map;
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setHeader(Map<String, String> map) {
        this.header = map;
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setHost(String str) {
        this.host = str;
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setInterval(int i) {
        this.timeout = i;
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setParams(Map<String, String> map) {
        this.params = map;
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setPath(String str) {
        this.path = str;
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setScheme(String str) {
        this.scheme = str;
    }

    @Override // com.tachikoma.core.api.IRequestDelegateInner
    public void setUserInfo(Map<String, Object> map) {
        this.userInfo = map;
    }
}
