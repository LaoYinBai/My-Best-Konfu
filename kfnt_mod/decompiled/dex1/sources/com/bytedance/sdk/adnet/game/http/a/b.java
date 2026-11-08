package com.bytedance.sdk.adnet.game.http.a;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.adnet.game.http.cb.LogCallback;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Connection;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Headers;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Protocol;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ResponseBody;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http.HttpHeaders;
import com.bytedance.sdk.adnet.game.ok3.okio.Buffer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private static final Charset f5021a = Charset.forName("UTF-8");

    /* renamed from: b, reason: collision with root package name */
    private int f5022b = 4;

    /* renamed from: c, reason: collision with root package name */
    private a f5023c;
    private LogCallback d;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private final StringBuilder f5024a;

        /* renamed from: b, reason: collision with root package name */
        private final String f5025b;

        /* renamed from: c, reason: collision with root package name */
        private final LogCallback f5026c;

        private a(LogCallback logCallback) {
            this.f5026c = logCallback;
            this.f5025b = (logCallback == null || TextUtils.isEmpty(logCallback.logTag())) ? "AD_HTTP_LOG" : logCallback.logTag();
            this.f5024a = new StringBuilder();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i) {
            b(i, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, String str) throws JSONException {
            if (str == null) {
                str = "";
            }
            try {
                str = new JSONObject(str).toString(2);
            } catch (Throwable unused) {
            }
            try {
                str = new JSONArray(str).toString(2);
            } catch (Throwable unused2) {
            }
            for (String str2 : str.split(System.getProperty("line.separator"))) {
                b(i, "│ " + str2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i) {
            b(i, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }

        private void b(int i, String str) {
            if (str == null || str.length() <= 0) {
                return;
            }
            if (this.f5026c == null || !this.f5026c.interceptorLog(str)) {
                Log.println(i, this.f5025b, str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(int i) {
            b(i, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
        }
    }

    public b(LogCallback logCallback) {
        this.d = logCallback;
        this.f5023c = new a(logCallback);
    }

    private Response a(Response response, long j) {
        ResponseBody responseBodyBody;
        this.f5023c.a(this.f5022b);
        try {
            this.f5023c.a(this.f5022b, "【Response】");
            Response responseBuild = response.newBuilder().build();
            this.f5023c.a(this.f5022b, responseBuild.code() + " ｜ " + responseBuild.message() + " (" + j + "ms）");
            this.f5023c.a(this.f5022b, responseBuild.request().url().toString());
            if (this.d.isPrintHeader()) {
                this.f5023c.c(this.f5022b);
                this.f5023c.a(this.f5022b, "【Header】");
                Headers headers = responseBuild.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    this.f5023c.a(this.f5022b, "【KEY】= " + headers.name(i) + "\n" + headers.value(i));
                }
            }
            if (HttpHeaders.hasBody(responseBuild) && (responseBodyBody = responseBuild.body()) != null) {
                this.f5023c.c(this.f5022b);
                this.f5023c.a(this.f5022b, "【Body】");
                if (a(responseBodyBody.contentType())) {
                    byte[] bArrA = a(responseBodyBody.byteStream());
                    this.f5023c.a(this.f5022b, new String(bArrA, b(responseBodyBody.contentType())));
                    response = response.newBuilder().body(ResponseBody.create(responseBodyBody.contentType(), bArrA)).build();
                } else {
                    this.f5023c.a(this.f5022b, "binary body");
                }
            }
        } catch (Throwable unused) {
        }
        this.f5023c.b(this.f5022b);
        return response;
    }

    private String a(Request request) {
        try {
            RequestBody requestBodyBody = request.newBuilder().build().body();
            if (requestBodyBody == null) {
                return "";
            }
            Buffer buffer = new Buffer();
            requestBodyBody.writeTo(buffer);
            return buffer.readString(b(requestBodyBody.contentType()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            if (cause instanceof UnknownHostException) {
                return cause.getMessage();
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    private void a(Request request, Connection connection) {
        this.f5023c.a(this.f5022b);
        try {
            RequestBody requestBodyBody = request.body();
            Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
            this.f5023c.a(this.f5022b, "【Request】");
            this.f5023c.a(this.f5022b, request.method() + " | " + protocol);
            this.f5023c.a(this.f5022b, request.url().toString());
            if (this.d.isPrintHeader()) {
                this.f5023c.c(this.f5022b);
                this.f5023c.a(this.f5022b, "【Header】");
                if (requestBodyBody != null) {
                    if (requestBodyBody.contentType() != null) {
                        this.f5023c.a(this.f5022b, "【KEY】= Content-Type\n" + requestBodyBody.contentType());
                    }
                    if (requestBodyBody.contentLength() != -1) {
                        this.f5023c.a(this.f5022b, "【KEY】= Content-Length\n" + requestBodyBody.contentLength());
                    }
                }
                Headers headers = request.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    String strName = headers.name(i);
                    if (!"Content-Type".equalsIgnoreCase(strName) && !"Content-Length".equalsIgnoreCase(strName)) {
                        this.f5023c.a(this.f5022b, "【KEY】= " + strName + "\n" + headers.value(i));
                    }
                }
            }
            if (requestBodyBody != null) {
                this.f5023c.c(this.f5022b);
                this.f5023c.a(this.f5022b, "【Body】");
                if (a(requestBodyBody.contentType())) {
                    this.f5023c.a(this.f5022b, a(request));
                } else {
                    this.f5023c.a(this.f5022b, "binary body");
                }
            }
        } catch (Throwable unused) {
        }
        this.f5023c.b(this.f5022b);
    }

    private static boolean a(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() != null && mediaType.type().equals(com.baidu.mobads.sdk.internal.a.f3090b)) {
            return true;
        }
        String strSubtype = mediaType.subtype();
        if (strSubtype != null) {
            String lowerCase = strSubtype.toLowerCase();
            if (lowerCase.contains("x-www-form-urlencoded") || lowerCase.contains("json") || lowerCase.contains("xml") || lowerCase.contains(com.baidu.mobads.sdk.internal.a.f)) {
                return true;
            }
        }
        return false;
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    private static Charset b(MediaType mediaType) {
        Charset charset = mediaType != null ? mediaType.charset(f5021a) : f5021a;
        return charset == null ? f5021a : charset;
    }

    public void a(LogCallback logCallback) {
        this.d = logCallback;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Request request = chain.request();
        if (this.d == null || !this.d.isOpen()) {
            return chain.proceed(request);
        }
        a(request, chain.connection());
        long jNanoTime = System.nanoTime();
        try {
            return a(chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime));
        } catch (Exception e) {
            this.f5023c.a(this.f5022b);
            this.f5023c.a(this.f5022b, "【Response】");
            this.f5023c.a(this.f5022b, request.url().toString());
            this.f5023c.c(this.f5022b);
            this.f5023c.a(this.f5022b, "【Exception】");
            this.f5023c.a(this.f5022b, a(e));
            this.f5023c.b(this.f5022b);
            throw e;
        }
    }
}
