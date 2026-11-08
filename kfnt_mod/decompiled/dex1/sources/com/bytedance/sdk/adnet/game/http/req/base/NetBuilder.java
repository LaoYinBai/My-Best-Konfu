package com.bytedance.sdk.adnet.game.http.req.base;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.adnet.game.http.NetClient;
import com.bytedance.sdk.adnet.game.http.b.a;
import com.bytedance.sdk.adnet.game.http.b.b;
import com.bytedance.sdk.adnet.game.http.b.c;
import com.bytedance.sdk.adnet.game.http.b.d;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.bytedance.sdk.adnet.game.http.cb.NetResponse;
import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Call;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Callback;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.OkHttpClient;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class NetBuilder<T extends NetBuilder> {

    /* renamed from: c, reason: collision with root package name */
    private String f5063c;
    private Object d;
    private Map<String, String> e;
    private Map<String, String> f;
    private int h;
    private NetCallback o;
    private int g = 0;
    private int i = -1;
    private int j = -1;
    private int k = -1;
    private boolean l = true;
    private boolean m = true;
    private boolean n = false;

    /* renamed from: a, reason: collision with root package name */
    private OkHttpClient f5061a = NetClient.getInstance().getOkHttpClient();

    /* renamed from: b, reason: collision with root package name */
    private Handler f5062b = d.a();

    public NetBuilder() {
        this.h = 0;
        this.h = NetClient.getInstance().retryCount();
    }

    private final void a(NetCallback netCallback) {
        this.o = netCallback;
        try {
            c();
            this.f5061a.newCall(e()).enqueue(new Callback() { // from class: com.bytedance.sdk.adnet.game.http.req.base.NetBuilder.5
                @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    int i;
                    String str;
                    String str2;
                    int i2;
                    String str3;
                    HashMap map;
                    if (!(iOException instanceof SocketException) && NetBuilder.this.g < NetBuilder.this.h && NetBuilder.this.h > 0) {
                        NetBuilder.d(NetBuilder.this);
                        NetBuilder.this.f5061a.newCall(call.request()).enqueue(this);
                        return;
                    }
                    if (a.a(true)) {
                        i = -1;
                        str = "";
                        str2 = "";
                        i2 = 10003;
                        str3 = NetCallback.ERROR_CODE_IO_EXCEPTION_DESC;
                        map = new HashMap();
                    } else {
                        i = -1;
                        str = "";
                        str2 = "";
                        i2 = 10002;
                        str3 = NetCallback.ERROR_CODE_NO_NETWORK_DESC;
                        map = new HashMap();
                    }
                    NetBuilder.this.a(NetResponse.error(i, str, str2, i2, str3, iOException, map, NetBuilder.this));
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Not initialized variable reg: 16, insn: 0x0152: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r16 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:57:0x0152 */
                /* JADX WARN: Removed duplicated region for block: B:39:0x00f1 A[Catch: Throwable -> 0x00ee, all -> 0x0151, TRY_LEAVE, TryCatch #0 {all -> 0x0151, blocks: (B:33:0x00c2, B:36:0x00cb, B:39:0x00f1, B:52:0x0135), top: B:65:0x0003 }] */
                /* JADX WARN: Removed duplicated region for block: B:66:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:69:0x00c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Callback
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onResponse(com.bytedance.sdk.adnet.game.ok3.okhttp3.Call r19, com.bytedance.sdk.adnet.game.ok3.okhttp3.Response r20) throws java.lang.Throwable {
                    /*
                        Method dump skipped, instructions count: 350
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adnet.game.http.req.base.NetBuilder.AnonymousClass5.onResponse(com.bytedance.sdk.adnet.game.ok3.okhttp3.Call, com.bytedance.sdk.adnet.game.ok3.okhttp3.Response):void");
                }
            });
        } catch (Throwable th) {
            a(NetResponse.error(-1, "", "", 10001, NetCallback.ERROR_CODE_PRE_REQUEST_DESC, th, new HashMap(), this));
        }
    }

    private void c() {
        if (this.o == null) {
            return;
        }
        this.f5062b.post(new Runnable() { // from class: com.bytedance.sdk.adnet.game.http.req.base.NetBuilder.1
            @Override // java.lang.Runnable
            public void run() {
                if (NetBuilder.this.o != null) {
                    NetBuilder.this.o.onNetStart(NetBuilder.this);
                }
            }
        });
    }

    static /* synthetic */ int d(NetBuilder netBuilder) {
        int i = netBuilder.g;
        netBuilder.g = i + 1;
        return i;
    }

    private final NetResponse<Response, T> d() {
        try {
            Response responseExecute = this.f5061a.newCall(e()).execute();
            return !responseExecute.isSuccessful() ? NetResponse.error(responseExecute.code(), responseExecute.message(), c.b(responseExecute.body()), 10006, NetCallback.ERROR_CODE_HTTP_CODE_ERROR_DESC, new IOException(NetCallback.ERROR_CODE_HTTP_CODE_ERROR_DESC), c.a(responseExecute), this) : NetResponse.success(responseExecute.code(), responseExecute.message(), "", NetCallback.ERROR_CODE_REQUEST_SUCCESS, NetCallback.ERROR_CODE_REQUEST_SUCCESS_DESC, responseExecute, c.a(responseExecute), this);
        } catch (Throwable th) {
            if ((th instanceof SocketException) || this.g >= this.h || this.h <= 0) {
                return a.a(true) ? NetResponse.error(-1, "", "", 10003, NetCallback.ERROR_CODE_IO_EXCEPTION_DESC, th, new HashMap(), this) : NetResponse.error(-1, "", "", 10002, NetCallback.ERROR_CODE_NO_NETWORK_DESC, th, new HashMap(), this);
            }
            this.g++;
            return d();
        }
    }

    private Request e() throws UnsupportedEncodingException {
        String strEncode;
        String strEncode2;
        Request.Builder builder = new Request.Builder();
        if (this.d != null) {
            builder.tag(this.d);
        }
        String strA = a.a(b.a());
        if (!TextUtils.isEmpty(strA)) {
            builder.header("User-Agent", strA);
        }
        Map<String, String> mapB = b();
        if (!mapB.isEmpty()) {
            for (String str : mapB.keySet()) {
                String str2 = mapB.get(str);
                try {
                    strEncode = URLEncoder.encode(str, "UTF-8");
                    try {
                        strEncode2 = URLEncoder.encode(str2, "UTF-8");
                    } catch (Throwable unused) {
                        Log.e("AdNetSDK", "encoder header error key =" + strEncode + " value =" + str2);
                        strEncode2 = str2;
                        builder.header(strEncode, strEncode2);
                    }
                } catch (Throwable unused2) {
                    strEncode = str;
                }
                builder.header(strEncode, strEncode2);
            }
        }
        String strUrl = url();
        Map<String, String> mapUrlParam = urlParam();
        if (mapUrlParam != null && !mapUrlParam.isEmpty()) {
            strUrl = a(strUrl, mapUrlParam);
        }
        if (this.i != -1 || this.j != -1 || this.k != -1) {
            builder.header("h_out_time_rwc", this.i + "_" + this.j + "_" + this.k);
        }
        builder.url(strUrl);
        a(builder);
        return builder.build();
    }

    protected String a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(str.contains("?") ? "&" : "?");
        for (String str2 : map.keySet()) {
            String strEncode = map.get(str2);
            try {
                strEncode = URLEncoder.encode(map.get(str2), "UTF-8");
            } catch (Throwable unused) {
            }
            sb.append(str2);
            sb.append("=");
            sb.append(strEncode);
            sb.append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    protected void a(final float f, final float f2, final float f3) {
        if (this.o == null) {
            return;
        }
        this.f5062b.post(new Runnable() { // from class: com.bytedance.sdk.adnet.game.http.req.base.NetBuilder.4
            @Override // java.lang.Runnable
            public void run() {
                if (NetBuilder.this.o != null) {
                    NetBuilder.this.o.onNetProgress(NetBuilder.this, f, f2, f3);
                }
            }
        });
    }

    protected void a(final NetResponse netResponse) {
        if (this.o == null) {
            return;
        }
        this.f5062b.post(new Runnable() { // from class: com.bytedance.sdk.adnet.game.http.req.base.NetBuilder.2
            @Override // java.lang.Runnable
            public void run() {
                if (NetBuilder.this.o != null) {
                    NetBuilder.this.o.onNetError(NetBuilder.this, netResponse);
                    NetBuilder.this.o.onNetEnd(NetBuilder.this);
                }
            }
        });
    }

    protected abstract void a(Request.Builder builder);

    protected boolean a() {
        return this instanceof NetBodyBuilder;
    }

    public T allowResponseNull(boolean z) {
        this.n = z;
        return this;
    }

    protected Map<String, String> b() {
        Map<String, String> mapCommonHeaders;
        HashMap map = new HashMap();
        if (this.l && (mapCommonHeaders = NetClient.getInstance().commonHeaders()) != null && !mapCommonHeaders.isEmpty()) {
            for (String str : mapCommonHeaders.keySet()) {
                map.put(str, mapCommonHeaders.get(str));
            }
        }
        if (this.e != null && !this.e.isEmpty()) {
            for (String str2 : this.e.keySet()) {
                map.put(str2, this.e.get(str2));
            }
        }
        return map;
    }

    protected void b(final NetResponse netResponse) {
        if (this.o == null) {
            return;
        }
        this.f5062b.post(new Runnable() { // from class: com.bytedance.sdk.adnet.game.http.req.base.NetBuilder.3
            @Override // java.lang.Runnable
            public void run() {
                if (NetBuilder.this.o != null) {
                    NetBuilder.this.o.onNetSuccess(NetBuilder.this, netResponse);
                    NetBuilder.this.o.onNetEnd(NetBuilder.this);
                }
            }
        });
    }

    public T enableCommonHeader(boolean z) {
        this.l = z;
        return this;
    }

    public boolean enableCommonHeader() {
        return this.l;
    }

    public T enableCommonParam(boolean z) {
        this.m = z;
        return this;
    }

    public boolean enableCommonParam() {
        return this.m;
    }

    public T header(String str, String str2) {
        if (this.e == null) {
            this.e = new LinkedHashMap();
        }
        this.e.put(str, str2);
        return this;
    }

    public Map<String, String> headers() {
        Map<String, String> mapCommonHeaders;
        HashMap map = new HashMap();
        if (this.e != null && !this.e.isEmpty()) {
            for (Map.Entry<String, String> entry : this.e.entrySet()) {
                if (entry != null) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.l && (mapCommonHeaders = NetClient.getInstance().commonHeaders()) != null && !mapCommonHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry2 : mapCommonHeaders.entrySet()) {
                if (entry2 != null) {
                    map.put(entry2.getKey(), entry2.getValue());
                }
            }
        }
        return map;
    }

    public final NetResponse<JSONObject, T> json() {
        NetResponse<String, T> netResponseString = string();
        if (netResponseString.data == null || netResponseString.code != 10000) {
            return NetResponse.error(netResponseString.httpCode, netResponseString.httpMessage, netResponseString.httpBody, netResponseString.code, netResponseString.message, netResponseString.error, netResponseString.headers, netResponseString.request);
        }
        try {
            return NetResponse.success(netResponseString.httpCode, netResponseString.httpMessage, netResponseString.httpBody, NetCallback.ERROR_CODE_REQUEST_SUCCESS, NetCallback.ERROR_CODE_REQUEST_SUCCESS_DESC, new JSONObject(netResponseString.data), netResponseString.headers, this);
        } catch (Throwable unused) {
            return NetResponse.error(netResponseString.code, netResponseString.message, "", 10005, NetCallback.ERROR_CODE_DATA_CONVERT_DESC, new IOException(NetCallback.ERROR_CODE_DATA_CONVERT_DESC), netResponseString.headers, this);
        }
    }

    public final void json(NetCallback<JSONObject, T> netCallback) {
        a(netCallback);
    }

    public final NetResponse<JSONArray, T> jsonArray() {
        NetResponse<String, T> netResponseString = string();
        if (netResponseString.data == null || netResponseString.code != 10000) {
            return NetResponse.error(netResponseString.httpCode, netResponseString.httpMessage, netResponseString.httpBody, netResponseString.code, netResponseString.message, netResponseString.error, netResponseString.headers, netResponseString.request);
        }
        try {
            return NetResponse.success(netResponseString.httpCode, netResponseString.httpMessage, netResponseString.httpBody, NetCallback.ERROR_CODE_REQUEST_SUCCESS, NetCallback.ERROR_CODE_REQUEST_SUCCESS_DESC, new JSONArray(netResponseString.data), netResponseString.headers, this);
        } catch (Throwable unused) {
            return NetResponse.error(netResponseString.code, netResponseString.message, "", 10005, NetCallback.ERROR_CODE_DATA_CONVERT_DESC, new IOException(NetCallback.ERROR_CODE_DATA_CONVERT_DESC), netResponseString.headers, this);
        }
    }

    public final void jsonArray(NetCallback<JSONArray, T> netCallback) {
        a(netCallback);
    }

    public abstract String method();

    public final NetResponse<Response, T> response() {
        return d();
    }

    public final void response(NetCallback<Response, T> netCallback) {
        a(netCallback);
    }

    public final NetResponse<String, T> string() {
        NetResponse<Response, T> netResponseD = d();
        if (netResponseD.data != null) {
            try {
                if (netResponseD.code == 10000) {
                    try {
                        String strString = netResponseD.data.body().string();
                        return NetResponse.success(netResponseD.httpCode, netResponseD.httpMessage, strString, NetCallback.ERROR_CODE_REQUEST_SUCCESS, NetCallback.ERROR_CODE_REQUEST_SUCCESS_DESC, strString, netResponseD.headers, this);
                    } catch (Throwable unused) {
                        NetResponse<String, T> netResponseError = NetResponse.error(netResponseD.code, netResponseD.message, "", 10005, NetCallback.ERROR_CODE_DATA_CONVERT_DESC, new IOException(NetCallback.ERROR_CODE_DATA_CONVERT_DESC), netResponseD.headers, this);
                        try {
                            netResponseD.data.body().close();
                        } catch (Throwable unused2) {
                        }
                        return netResponseError;
                    }
                }
            } finally {
                try {
                    netResponseD.data.body().close();
                } catch (Throwable unused3) {
                }
            }
        }
        return NetResponse.error(netResponseD.httpCode, netResponseD.httpMessage, netResponseD.httpBody, netResponseD.code, netResponseD.message, netResponseD.error, netResponseD.headers, netResponseD.request);
    }

    public final void string(NetCallback<String, T> netCallback) {
        a(netCallback);
    }

    public T tag(Object obj) {
        this.d = obj;
        return this;
    }

    public Object tag() {
        return this.d;
    }

    public T timeoutConnSecond(int i) {
        if (i > 0) {
            this.k = i;
        }
        return this;
    }

    public T timeoutReadSecond(int i) {
        if (i > 0) {
            this.i = i;
        }
        return this;
    }

    public T timeoutWriteSecond(int i) {
        if (i > 0) {
            this.j = i;
        }
        return this;
    }

    public T url(String str) {
        this.f5063c = str;
        return this;
    }

    public String url() {
        return this.f5063c;
    }

    public T urlParam(String str, String str2) {
        if (this.f == null) {
            this.f = new LinkedHashMap();
        }
        this.f.put(str, str2);
        return this;
    }

    public Map<String, String> urlParam() {
        Map<String, String> mapCommonParams;
        HashMap map = new HashMap();
        if (this.f != null && !this.f.isEmpty()) {
            for (Map.Entry<String, String> entry : this.f.entrySet()) {
                if (entry != null) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.m && !a() && (mapCommonParams = NetClient.getInstance().commonParams()) != null && !mapCommonParams.isEmpty()) {
            for (Map.Entry<String, String> entry2 : mapCommonParams.entrySet()) {
                if (entry2 != null) {
                    map.put(entry2.getKey(), entry2.getValue());
                }
            }
        }
        return map;
    }
}
