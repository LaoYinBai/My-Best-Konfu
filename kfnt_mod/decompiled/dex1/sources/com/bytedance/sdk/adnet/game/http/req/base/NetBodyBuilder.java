package com.bytedance.sdk.adnet.game.http.req.base;

import android.text.TextUtils;
import com.bytedance.sdk.adnet.game.http.NetClient;
import com.bytedance.sdk.adnet.game.http.b.c;
import com.bytedance.sdk.adnet.game.http.bean.FileWrapper;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.bytedance.sdk.adnet.game.http.req.base.NetBodyBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.FormBody;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MultipartBody;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody;
import com.bytedance.sdk.adnet.game.ok3.okio.Buffer;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSink;
import com.bytedance.sdk.adnet.game.ok3.okio.ForwardingSink;
import com.bytedance.sdk.adnet.game.ok3.okio.Okio;
import com.bytedance.sdk.adnet.game.ok3.okio.Sink;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class NetBodyBuilder<T extends NetBodyBuilder> extends NetBuilder<T> {
    private RequestBody e;
    private String f;
    private byte[] g;
    private File h;
    private MediaType i;

    /* renamed from: b, reason: collision with root package name */
    private boolean f5053b = false;
    private boolean j = false;

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f5052a = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private Map<String, FileWrapper> f5054c = new HashMap();
    private Map<String, List<FileWrapper>> d = new HashMap();

    static final class ProgressRequestBody extends RequestBody {

        /* renamed from: a, reason: collision with root package name */
        private RequestBody f5056a;

        /* renamed from: b, reason: collision with root package name */
        private NetCallback f5057b;

        private static final class CountingSink extends ForwardingSink {

            /* renamed from: a, reason: collision with root package name */
            NetCallback f5058a;

            /* renamed from: b, reason: collision with root package name */
            long f5059b;

            /* renamed from: c, reason: collision with root package name */
            long f5060c;

            CountingSink(Sink sink, NetCallback netCallback, long j) {
                super(sink);
                this.f5058a = netCallback;
                this.f5059b = j;
                this.f5060c = 0L;
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okio.ForwardingSink, com.bytedance.sdk.adnet.game.ok3.okio.Sink
            public void write(Buffer buffer, long j) {
                this.f5060c += j;
                if (this.f5058a != null && this.f5060c > 0 && this.f5059b > 0) {
                    this.f5058a.onNetProgress(null, this.f5060c, this.f5059b, (this.f5060c * 1.0f) / this.f5059b);
                }
                super.write(buffer, j);
            }
        }

        ProgressRequestBody(RequestBody requestBody, NetCallback netCallback) {
            this.f5056a = requestBody;
            this.f5057b = netCallback;
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
        public long contentLength() {
            try {
                return this.f5056a.contentLength();
            } catch (Throwable unused) {
                return super.contentLength();
            }
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
        public MediaType contentType() {
            return this.f5056a.contentType();
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) {
            BufferedSink bufferedSinkBuffer = Okio.buffer(new CountingSink(bufferedSink, this.f5057b, contentLength()));
            this.f5056a.writeTo(bufferedSinkBuffer);
            bufferedSinkBuffer.flush();
        }
    }

    private RequestBody a(RequestBody requestBody) {
        return new ProgressRequestBody(requestBody, new NetCallback() { // from class: com.bytedance.sdk.adnet.game.http.req.base.NetBodyBuilder.1
            @Override // com.bytedance.sdk.adnet.game.http.cb.NetCallback
            public void onNetProgress(NetBuilder netBuilder, float f, float f2, float f3) {
                super.onNetProgress(netBuilder, f, f2, f3);
                NetBodyBuilder.this.a(f, f2, f3);
            }
        });
    }

    private RequestBody a(Map<String, String> map, Map<String, FileWrapper> map2, Map<String, List<FileWrapper>> map3, boolean z) {
        Map<String, String> mapCommonParams;
        Map<String, String> mapCommonParams2;
        if (map2.isEmpty() && map3.isEmpty() && !z) {
            FormBody.Builder builder = new FormBody.Builder();
            HashMap map4 = new HashMap();
            if (enableCommonParam() && (mapCommonParams2 = NetClient.getInstance().commonParams()) != null && !mapCommonParams2.isEmpty()) {
                for (Map.Entry<String, String> entry : mapCommonParams2.entrySet()) {
                    if (entry != null) {
                        map4.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            for (String str : map.keySet()) {
                map4.put(str, map.get(str));
            }
            if (!map4.isEmpty()) {
                for (String str2 : map4.keySet()) {
                    builder.add(str2, (String) map4.get(str2));
                }
            }
            return builder.build();
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        HashMap map5 = new HashMap();
        if (enableCommonParam() && (mapCommonParams = NetClient.getInstance().commonParams()) != null && !mapCommonParams.isEmpty()) {
            for (Map.Entry<String, String> entry2 : mapCommonParams.entrySet()) {
                if (entry2 != null) {
                    map5.put(entry2.getKey(), entry2.getValue());
                }
            }
        }
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry3 : map.entrySet()) {
                if (entry3 != null) {
                    map5.put(entry3.getKey(), entry3.getValue());
                }
            }
        }
        if (!map5.isEmpty()) {
            for (String str3 : map5.keySet()) {
                type.addFormDataPart(str3, (String) map5.get(str3));
            }
        }
        for (Map.Entry<String, FileWrapper> entry4 : map2.entrySet()) {
            FileWrapper value = entry4.getValue();
            type.addFormDataPart(entry4.getKey(), value.fileName, RequestBody.create(value.contentType, value.file));
        }
        for (Map.Entry<String, List<FileWrapper>> entry5 : map3.entrySet()) {
            String key = entry5.getKey();
            List<FileWrapper> value2 = entry5.getValue();
            if (!TextUtils.isEmpty(key) && value2 != null && value2.size() > 0) {
                for (FileWrapper fileWrapper : value2) {
                    if (fileWrapper != null) {
                        type.addFormDataPart(key, fileWrapper.fileName, RequestBody.create(fileWrapper.contentType, fileWrapper.file));
                    }
                }
            }
        }
        return type.build();
    }

    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    protected void a(Request.Builder builder) {
        Map<String, String> mapCommonParams;
        if (this.j) {
            builder.header("h_gzip_control", "1");
        }
        RequestBody requestBodyA = null;
        if (this.e != null) {
            requestBodyA = this.e;
        } else if (this.i != null) {
            if (!TextUtils.isEmpty(this.f)) {
                requestBodyA = RequestBody.create(this.i, this.f);
            } else if (this.g != null) {
                requestBodyA = RequestBody.create(this.i, this.g);
            } else if (this.h != null) {
                requestBodyA = a(RequestBody.create(this.i, this.h));
            }
        }
        if (requestBodyA == null) {
            requestBodyA = a(this.f5052a, this.f5054c, this.d, this.f5053b);
            if (!this.f5054c.isEmpty()) {
                requestBodyA = a(requestBodyA);
            }
        } else {
            String strUrl = url();
            Map<String, String> map = new HashMap<>();
            if (enableCommonParam() && (mapCommonParams = NetClient.getInstance().commonParams()) != null && !mapCommonParams.isEmpty()) {
                for (String str : mapCommonParams.keySet()) {
                    map.put(str, mapCommonParams.get(str));
                }
            }
            Map<String, String> mapUrlParam = urlParam();
            Map<String, String> mapParam = param();
            if (mapUrlParam != null && !mapUrlParam.isEmpty()) {
                for (String str2 : mapUrlParam.keySet()) {
                    map.put(str2, mapUrlParam.get(str2));
                }
            }
            if (mapParam != null && !mapParam.isEmpty()) {
                for (String str3 : mapParam.keySet()) {
                    map.put(str3, mapUrlParam.get(str3));
                }
            }
            builder.url(a(strUrl, map));
        }
        a(builder, requestBodyA);
    }

    protected abstract void a(Request.Builder builder, RequestBody requestBody);

    public T enableGzip(boolean z) {
        this.j = z;
        return this;
    }

    public Map<String, FileWrapper> fileParam() {
        return this.f5054c;
    }

    public Map<String, List<FileWrapper>> fileParams() {
        return this.d;
    }

    public T forceMultipart(boolean z) {
        this.f5053b = z;
        return this;
    }

    public boolean isEnableGzip() {
        return this.j;
    }

    public boolean isForceMultipart() {
        return this.f5053b;
    }

    public T param(String str, double d) {
        if (!TextUtils.isEmpty(str)) {
            this.f5052a.put(str, d + "");
        }
        return this;
    }

    public T param(String str, float f) {
        if (!TextUtils.isEmpty(str)) {
            this.f5052a.put(str, f + "");
        }
        return this;
    }

    public T param(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            this.f5052a.put(str, i + "");
        }
        return this;
    }

    public T param(String str, long j) {
        if (!TextUtils.isEmpty(str)) {
            this.f5052a.put(str, j + "");
        }
        return this;
    }

    public T param(String str, File file) {
        if (!TextUtils.isEmpty(str) && file != null) {
            this.f5054c.put(str, new FileWrapper(file));
        }
        return this;
    }

    public T param(String str, File file, MediaType mediaType) {
        if (!TextUtils.isEmpty(str) && file != null) {
            this.f5054c.put(str, new FileWrapper(file, mediaType));
        }
        return this;
    }

    public T param(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.f5052a.put(str, str2);
        }
        return this;
    }

    public T param(String str, List<File> list) {
        if (!TextUtils.isEmpty(str) && list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (File file : list) {
                if (file != null) {
                    arrayList.add(new FileWrapper(file));
                }
            }
            if (arrayList.size() > 0) {
                this.d.put(str, arrayList);
            }
        }
        return this;
    }

    public T param(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            this.f5052a.put(str, z + "");
        }
        return this;
    }

    public Map<String, String> param() {
        return this.f5052a;
    }

    public T upBytes(byte[] bArr) {
        return (T) upBytes(bArr, c.f5032c);
    }

    public T upBytes(byte[] bArr, MediaType mediaType) {
        this.g = bArr;
        this.i = mediaType;
        return this;
    }

    public byte[] upBytes() {
        return this.g;
    }

    public T upFile(File file) {
        return file != null ? (T) upFile(file, c.c(file.getName())) : this;
    }

    public T upFile(File file, MediaType mediaType) {
        this.h = file;
        this.i = mediaType;
        return this;
    }

    public File upFile() {
        return this.h;
    }

    public T upJson(String str) {
        return (T) upString(str, c.f5030a);
    }

    public T upJson(JSONArray jSONArray) {
        return jSONArray != null ? (T) upJson(jSONArray.toString()) : this;
    }

    public T upJson(JSONObject jSONObject) {
        return jSONObject != null ? (T) upJson(jSONObject.toString()) : this;
    }

    public String upJson() {
        return this.f;
    }

    public T upRequestBody(RequestBody requestBody) {
        this.e = requestBody;
        return this;
    }

    public RequestBody upRequestBody() {
        return this.e;
    }

    public T upString(String str) {
        return (T) upString(str, c.f5031b);
    }

    public T upString(String str, MediaType mediaType) {
        this.f = str;
        this.i = mediaType;
        return this;
    }

    public String upString() {
        return this.f;
    }
}
