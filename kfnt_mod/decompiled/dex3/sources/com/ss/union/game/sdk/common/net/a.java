package com.ss.union.game.sdk.common.net;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.adnet.game.http.NetClient;
import com.bytedance.sdk.adnet.game.http.bean.FileWrapper;
import com.bytedance.sdk.adnet.game.http.cb.ICommonParam;
import com.bytedance.sdk.adnet.game.http.cb.LogCallback;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.bytedance.sdk.adnet.game.http.cb.NetResponse;
import com.bytedance.sdk.adnet.game.http.req.OkDownloadBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkGetBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkHeadBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkPostBuilder;
import com.bytedance.sdk.adnet.game.http.req.OkTraceBuilder;
import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.ss.union.game.sdk.common.net.dns.Ok3HttpDNS;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestDownload;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestGet;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestHead;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestTrace;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetApi2Callback;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost;
import com.ss.union.game.sdk.common.net.http.base.response.CoreHttpResponse;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetFileWrapper;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.net.tnc.Ok3TNCInterceptor;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a extends ACoreNetClient {
    a() {
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public void init(Context context, String str) {
        NetClient.init(context.getApplicationContext()).sslUnsafe().cookieOpen().dns(new Ok3HttpDNS(str)).tnc(new Ok3TNCInterceptor(str)).log(new LogCallback() { // from class: com.ss.union.game.sdk.common.net.a.3
            public boolean interceptorLog(String str2) {
                return false;
            }

            public boolean isOpen() {
                return false;
            }

            public String logTag() {
                return "SSRetrofit";
            }
        }).commonHeader(new ICommonParam() { // from class: com.ss.union.game.sdk.common.net.a.2
            public Map<String, String> commonParam() {
                return C0062a.a();
            }
        }).commonParam(new ICommonParam() { // from class: com.ss.union.game.sdk.common.net.a.1
            public Map<String, String> commonParam() {
                return C0062a.b();
            }
        }).build();
    }

    /* renamed from: com.ss.union.game.sdk.common.net.a$4, reason: invalid class name */
    class AnonymousClass4 extends ACoreRequestGet {
        ACoreRequestGet a = this;
        final /* synthetic */ OkGetBuilder b;

        AnonymousClass4(OkGetBuilder okGetBuilder) {
            this.b = okGetBuilder;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet url(String str) {
            this.b.url(str);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public String url() {
            return this.b.url();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet tag(Object obj) {
            this.b.tag(obj);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Object tag() {
            return this.b.tag();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet header(String str, String str2) {
            this.b.header(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> headers() {
            return this.b.headers();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet urlParam(String str, String str2) {
            this.b.urlParam(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> urlParam() {
            return this.b.urlParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet timeoutReadSecond(int i) {
            this.b.timeoutReadSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet timeoutWriteSecond(int i) {
            this.b.timeoutWriteSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet timeoutConnSecond(int i) {
            this.b.timeoutConnSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet enableCommonHeader(boolean z) {
            this.b.enableCommonHeader(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonHeader() {
            return this.b.enableCommonHeader();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet enableCommonParam(boolean z) {
            this.b.enableCommonParam(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonParam() {
            return this.b.enableCommonParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestGet allowResponseNull(boolean z) {
            this.b.allowResponseNull(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void response(final ICoreNetCallback<CoreHttpResponse, ACoreRequestGet> iCoreNetCallback) {
            this.b.response(new NetCallback<Response, OkGetBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.4.1
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkGetBuilder okGetBuilder) {
                    super.onNetStart(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkGetBuilder okGetBuilder) {
                    super.onNetEnd(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkGetBuilder okGetBuilder, NetResponse<Response, OkGetBuilder> netResponse) {
                    super.onNetError(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass4.this.a, a.this.b(AnonymousClass4.this.a, netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkGetBuilder okGetBuilder, NetResponse<Response, OkGetBuilder> netResponse) {
                    super.onNetSuccess(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass4.this.a, a.this.b(AnonymousClass4.this.a, netResponse));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void string(final ICoreNetCallback<String, ACoreRequestGet> iCoreNetCallback) {
            this.b.string(new NetCallback<String, OkGetBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.4.2
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkGetBuilder okGetBuilder) {
                    super.onNetStart(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkGetBuilder okGetBuilder) {
                    super.onNetEnd(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkGetBuilder okGetBuilder, NetResponse<String, OkGetBuilder> netResponse) {
                    super.onNetError(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass4.this.a, a.this.a((a) AnonymousClass4.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkGetBuilder okGetBuilder, NetResponse<String, OkGetBuilder> netResponse) {
                    super.onNetSuccess(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass4.this.a, a.this.a((a) AnonymousClass4.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkGetBuilder okGetBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okGetBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass4.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void json(final ICoreNetCallback<JSONObject, ACoreRequestGet> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkGetBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.4.3
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkGetBuilder okGetBuilder) {
                    super.onNetStart(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkGetBuilder okGetBuilder) {
                    super.onNetEnd(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkGetBuilder okGetBuilder, NetResponse<JSONObject, OkGetBuilder> netResponse) {
                    super.onNetError(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass4.this.a, a.this.a((a) AnonymousClass4.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkGetBuilder okGetBuilder, NetResponse<JSONObject, OkGetBuilder> netResponse) {
                    super.onNetSuccess(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass4.this.a, a.this.a((a) AnonymousClass4.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkGetBuilder okGetBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okGetBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass4.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void jsonArray(final ICoreNetCallback<JSONArray, ACoreRequestGet> iCoreNetCallback) {
            this.b.jsonArray(new NetCallback<JSONArray, OkGetBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.4.4
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkGetBuilder okGetBuilder) {
                    super.onNetStart(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkGetBuilder okGetBuilder) {
                    super.onNetEnd(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkGetBuilder okGetBuilder, NetResponse<JSONArray, OkGetBuilder> netResponse) {
                    super.onNetError(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass4.this.a, a.this.a((a) AnonymousClass4.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkGetBuilder okGetBuilder, NetResponse<JSONArray, OkGetBuilder> netResponse) {
                    super.onNetSuccess(okGetBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass4.this.a, a.this.a((a) AnonymousClass4.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkGetBuilder okGetBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okGetBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass4.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api(final ICoreNetCallback<JSONObject, ACoreRequestGet> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkGetBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.4.5
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkGetBuilder okGetBuilder) {
                    super.onNetStart(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkGetBuilder okGetBuilder) {
                    super.onNetEnd(okGetBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass4.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkGetBuilder okGetBuilder, NetResponse<JSONObject, OkGetBuilder> netResponse) {
                    super.onNetError(okGetBuilder, netResponse);
                    a.this.b((a) AnonymousClass4.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkGetBuilder okGetBuilder, NetResponse<JSONObject, OkGetBuilder> netResponse) {
                    super.onNetSuccess(okGetBuilder, netResponse);
                    a.this.a((a) AnonymousClass4.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkGetBuilder okGetBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okGetBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass4.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api2(final ICoreNetApi2Callback iCoreNetApi2Callback) {
            api(new ICoreNetCallback<JSONObject, ACoreRequestGet>() { // from class: com.ss.union.game.sdk.common.net.a.4.6
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                    super.onNetError(aCoreRequestGet, coreNetResponse);
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetError(coreNetResponse.code(), coreNetResponse.message());
                    }
                }

                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                    super.onNetSuccess(aCoreRequestGet, coreNetResponse);
                    JSONObject jSONObject = coreNetResponse.data;
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetSuccess(jSONObject.optJSONObject("data"));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<CoreHttpResponse, ACoreRequestGet> response() {
            return a.this.b(this.a, this.b.response());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<String, ACoreRequestGet> string() {
            return a.this.a((a) this.a, this.b.string());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONObject, ACoreRequestGet> json() {
            return a.this.a((a) this.a, this.b.json());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONArray, ACoreRequestGet> jsonArray() {
            return a.this.a((a) this.a, this.b.jsonArray());
        }
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public ACoreRequestGet get(String str) {
        return new AnonymousClass4(NetClient.get()).url(str);
    }

    /* renamed from: com.ss.union.game.sdk.common.net.a$5, reason: invalid class name */
    class AnonymousClass5 extends ACoreRequestPost {
        ACoreRequestPost a = this;
        final /* synthetic */ OkPostBuilder b;

        AnonymousClass5(OkPostBuilder okPostBuilder) {
            this.b = okPostBuilder;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public /* synthetic */ ICoreNetRequestPost param(String str, List list) {
            return a(str, (List<File>) list);
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost enableGzip(boolean z) {
            this.b.enableGzip(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public boolean isEnableGzip() {
            return this.b.isEnableGzip();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, String str2) {
            this.b.param(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, int i) {
            this.b.param(str, i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, long j) {
            this.b.param(str, j);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, float f) {
            this.b.param(str, f);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, double d) {
            this.b.param(str, d);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, boolean z) {
            this.b.param(str, z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public Map<String, String> param() {
            return this.b.param();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost forceMultipart(boolean z) {
            this.b.forceMultipart(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public boolean isForceMultipart() {
            return this.b.isForceMultipart();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, File file) {
            this.b.param(str, file);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost param(String str, File file, CoreNetFileWrapper.MediaType mediaType) {
            if (mediaType != null) {
                this.b.param(str, file, MediaType.parse(mediaType.toString()));
            }
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public Map<String, CoreNetFileWrapper> fileParam() {
            Map mapFileParam = this.b.fileParam();
            HashMap map = new HashMap();
            if (mapFileParam != null && !mapFileParam.isEmpty()) {
                for (Map.Entry entry : mapFileParam.entrySet()) {
                    if (entry != null && entry.getValue() != null) {
                        FileWrapper fileWrapper = (FileWrapper) entry.getValue();
                        map.put(entry.getKey(), new CoreNetFileWrapper(fileWrapper.file, fileWrapper.fileName, a.this.a(fileWrapper.contentType), fileWrapper.fileSize));
                    }
                }
            }
            return map;
        }

        public ACoreRequestPost a(String str, List<File> list) {
            this.b.param(str, list);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public Map<String, List<CoreNetFileWrapper>> fileParams() {
            Map mapFileParams = this.b.fileParams();
            HashMap map = new HashMap();
            if (mapFileParams != null && !mapFileParams.isEmpty()) {
                for (Map.Entry entry : mapFileParams.entrySet()) {
                    if (entry != null && entry.getValue() != null) {
                        ArrayList arrayList = new ArrayList();
                        List<FileWrapper> list = (List) entry.getValue();
                        if (list.size() > 0) {
                            for (FileWrapper fileWrapper : list) {
                                if (fileWrapper != null) {
                                    arrayList.add(new CoreNetFileWrapper(fileWrapper.file, fileWrapper.fileName, a.this.a(fileWrapper.contentType), fileWrapper.fileSize));
                                }
                            }
                        }
                        map.put(entry.getKey(), arrayList);
                    }
                }
            }
            return map;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upString(String str) {
            this.b.upString(str);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upString(String str, CoreNetFileWrapper.MediaType mediaType) {
            if (mediaType != null) {
                this.b.upString(str, MediaType.parse(mediaType.toString()));
            } else {
                this.b.upString(str);
            }
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public String upString() {
            return this.b.upString();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upJson(String str) {
            this.b.upJson(str);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upJson(JSONObject jSONObject) {
            this.b.upJson(jSONObject);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upJson(JSONArray jSONArray) {
            this.b.upJson(jSONArray);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public String upJson() {
            return this.b.upJson();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upBytes(byte[] bArr) {
            this.b.upBytes(bArr);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upBytes(byte[] bArr, CoreNetFileWrapper.MediaType mediaType) {
            if (mediaType != null) {
                this.b.upBytes(bArr, MediaType.parse(mediaType.toString()));
            } else {
                this.b.upBytes(bArr);
            }
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public byte[] upBytes() {
            return this.b.upBytes();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upFile(File file) {
            this.b.upFile(file);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost upFile(File file, CoreNetFileWrapper.MediaType mediaType) {
            if (mediaType != null) {
                this.b.upFile(file, MediaType.parse(mediaType.toString()));
            } else {
                this.b.upFile(file);
            }
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequestPost
        public File upFile() {
            return this.b.upFile();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost url(String str) {
            this.b.url(str);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public String url() {
            return this.b.url();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost tag(Object obj) {
            this.b.tag(obj);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Object tag() {
            return this.b.tag();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost header(String str, String str2) {
            this.b.header(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> headers() {
            return this.b.headers();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost urlParam(String str, String str2) {
            this.b.urlParam(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> urlParam() {
            return this.b.urlParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost timeoutReadSecond(int i) {
            this.b.timeoutReadSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost timeoutWriteSecond(int i) {
            this.b.timeoutWriteSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost timeoutConnSecond(int i) {
            this.b.timeoutConnSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost enableCommonHeader(boolean z) {
            this.b.enableCommonHeader(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonHeader() {
            return this.b.enableCommonHeader();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost enableCommonParam(boolean z) {
            this.b.enableCommonParam(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonParam() {
            return this.b.enableCommonParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ACoreRequestPost allowResponseNull(boolean z) {
            this.b.allowResponseNull(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void response(final ICoreNetCallback<CoreHttpResponse, ACoreRequestPost> iCoreNetCallback) {
            this.b.response(new NetCallback<Response, OkPostBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.5.1
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkPostBuilder okPostBuilder) {
                    super.onNetStart(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkPostBuilder okPostBuilder) {
                    super.onNetEnd(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkPostBuilder okPostBuilder, NetResponse<Response, OkPostBuilder> netResponse) {
                    super.onNetError(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass5.this.a, a.this.b(AnonymousClass5.this.a, netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkPostBuilder okPostBuilder, NetResponse<Response, OkPostBuilder> netResponse) {
                    super.onNetSuccess(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass5.this.a, a.this.b(AnonymousClass5.this.a, netResponse));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void string(final ICoreNetCallback<String, ACoreRequestPost> iCoreNetCallback) {
            this.b.string(new NetCallback<String, OkPostBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.5.2
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkPostBuilder okPostBuilder) {
                    super.onNetStart(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkPostBuilder okPostBuilder) {
                    super.onNetEnd(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkPostBuilder okPostBuilder, NetResponse<String, OkPostBuilder> netResponse) {
                    super.onNetError(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass5.this.a, a.this.a((a) AnonymousClass5.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkPostBuilder okPostBuilder, NetResponse<String, OkPostBuilder> netResponse) {
                    super.onNetSuccess(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass5.this.a, a.this.a((a) AnonymousClass5.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkPostBuilder okPostBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okPostBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass5.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void json(final ICoreNetCallback<JSONObject, ACoreRequestPost> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkPostBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.5.3
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkPostBuilder okPostBuilder) {
                    super.onNetStart(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkPostBuilder okPostBuilder) {
                    super.onNetEnd(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkPostBuilder okPostBuilder, NetResponse<JSONObject, OkPostBuilder> netResponse) {
                    super.onNetError(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass5.this.a, a.this.a((a) AnonymousClass5.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkPostBuilder okPostBuilder, NetResponse<JSONObject, OkPostBuilder> netResponse) {
                    super.onNetSuccess(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass5.this.a, a.this.a((a) AnonymousClass5.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkPostBuilder okPostBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okPostBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass5.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void jsonArray(final ICoreNetCallback<JSONArray, ACoreRequestPost> iCoreNetCallback) {
            this.b.jsonArray(new NetCallback<JSONArray, OkPostBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.5.4
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkPostBuilder okPostBuilder) {
                    super.onNetStart(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkPostBuilder okPostBuilder) {
                    super.onNetEnd(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkPostBuilder okPostBuilder, NetResponse<JSONArray, OkPostBuilder> netResponse) {
                    super.onNetError(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass5.this.a, a.this.a((a) AnonymousClass5.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkPostBuilder okPostBuilder, NetResponse<JSONArray, OkPostBuilder> netResponse) {
                    super.onNetSuccess(okPostBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass5.this.a, a.this.a((a) AnonymousClass5.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkPostBuilder okPostBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okPostBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass5.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api(final ICoreNetCallback<JSONObject, ACoreRequestPost> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkPostBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.5.5
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkPostBuilder okPostBuilder) {
                    super.onNetStart(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkPostBuilder okPostBuilder) {
                    super.onNetEnd(okPostBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass5.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkPostBuilder okPostBuilder, NetResponse<JSONObject, OkPostBuilder> netResponse) {
                    super.onNetError(okPostBuilder, netResponse);
                    a.this.b((a) AnonymousClass5.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkPostBuilder okPostBuilder, NetResponse<JSONObject, OkPostBuilder> netResponse) {
                    super.onNetSuccess(okPostBuilder, netResponse);
                    a.this.a((a) AnonymousClass5.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkPostBuilder okPostBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okPostBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass5.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api2(final ICoreNetApi2Callback iCoreNetApi2Callback) {
            api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.common.net.a.5.6
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                    super.onNetError(aCoreRequestPost, coreNetResponse);
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetError(coreNetResponse.code(), coreNetResponse.message());
                    }
                }

                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                    super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                    JSONObject jSONObject = coreNetResponse.data;
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetSuccess(jSONObject.optJSONObject("data"));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<CoreHttpResponse, ACoreRequestPost> response() {
            return a.this.b(this.a, this.b.response());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<String, ACoreRequestPost> string() {
            return a.this.a((a) this.a, this.b.string());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONObject, ACoreRequestPost> json() {
            return a.this.a((a) this.a, this.b.json());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONArray, ACoreRequestPost> jsonArray() {
            return a.this.a((a) this.a, this.b.jsonArray());
        }
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public ACoreRequestPost post(String str) {
        return new AnonymousClass5(NetClient.post()).url(str);
    }

    /* renamed from: com.ss.union.game.sdk.common.net.a$6, reason: invalid class name */
    class AnonymousClass6 extends ACoreRequestHead {
        ACoreRequestHead a = this;
        final /* synthetic */ OkHeadBuilder b;

        AnonymousClass6(OkHeadBuilder okHeadBuilder) {
            this.b = okHeadBuilder;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead url(String str) {
            this.b.url(str);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public String url() {
            return this.b.url();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead tag(Object obj) {
            this.b.tag(obj);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Object tag() {
            return this.b.tag();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead header(String str, String str2) {
            this.b.header(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> headers() {
            return this.b.headers();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead urlParam(String str, String str2) {
            this.b.urlParam(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> urlParam() {
            return this.b.urlParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead timeoutReadSecond(int i) {
            this.b.timeoutReadSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead timeoutWriteSecond(int i) {
            this.b.timeoutWriteSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead timeoutConnSecond(int i) {
            this.b.timeoutConnSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead enableCommonHeader(boolean z) {
            this.b.enableCommonHeader(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonHeader() {
            return this.b.enableCommonHeader();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead enableCommonParam(boolean z) {
            this.b.enableCommonParam(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonParam() {
            return this.b.enableCommonParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestHead allowResponseNull(boolean z) {
            this.b.allowResponseNull(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void response(final ICoreNetCallback<CoreHttpResponse, ACoreRequestHead> iCoreNetCallback) {
            this.b.response(new NetCallback<Response, OkHeadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.6.1
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkHeadBuilder okHeadBuilder) {
                    super.onNetStart(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkHeadBuilder okHeadBuilder) {
                    super.onNetEnd(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkHeadBuilder okHeadBuilder, NetResponse<Response, OkHeadBuilder> netResponse) {
                    super.onNetError(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass6.this.a, a.this.b(AnonymousClass6.this.a, netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkHeadBuilder okHeadBuilder, NetResponse<Response, OkHeadBuilder> netResponse) {
                    super.onNetSuccess(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass6.this.a, a.this.b(AnonymousClass6.this.a, netResponse));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void string(final ICoreNetCallback<String, ACoreRequestHead> iCoreNetCallback) {
            this.b.string(new NetCallback<String, OkHeadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.6.2
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkHeadBuilder okHeadBuilder) {
                    super.onNetStart(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkHeadBuilder okHeadBuilder) {
                    super.onNetEnd(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkHeadBuilder okHeadBuilder, NetResponse<String, OkHeadBuilder> netResponse) {
                    super.onNetError(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass6.this.a, a.this.a((a) AnonymousClass6.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkHeadBuilder okHeadBuilder, NetResponse<String, OkHeadBuilder> netResponse) {
                    super.onNetSuccess(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass6.this.a, a.this.a((a) AnonymousClass6.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkHeadBuilder okHeadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okHeadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass6.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void json(final ICoreNetCallback<JSONObject, ACoreRequestHead> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkHeadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.6.3
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkHeadBuilder okHeadBuilder) {
                    super.onNetStart(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkHeadBuilder okHeadBuilder) {
                    super.onNetEnd(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkHeadBuilder okHeadBuilder, NetResponse<JSONObject, OkHeadBuilder> netResponse) {
                    super.onNetError(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass6.this.a, a.this.a((a) AnonymousClass6.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkHeadBuilder okHeadBuilder, NetResponse<JSONObject, OkHeadBuilder> netResponse) {
                    super.onNetSuccess(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass6.this.a, a.this.a((a) AnonymousClass6.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkHeadBuilder okHeadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okHeadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass6.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void jsonArray(final ICoreNetCallback<JSONArray, ACoreRequestHead> iCoreNetCallback) {
            this.b.jsonArray(new NetCallback<JSONArray, OkHeadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.6.4
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkHeadBuilder okHeadBuilder) {
                    super.onNetStart(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkHeadBuilder okHeadBuilder) {
                    super.onNetEnd(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkHeadBuilder okHeadBuilder, NetResponse<JSONArray, OkHeadBuilder> netResponse) {
                    super.onNetError(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass6.this.a, a.this.a((a) AnonymousClass6.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkHeadBuilder okHeadBuilder, NetResponse<JSONArray, OkHeadBuilder> netResponse) {
                    super.onNetSuccess(okHeadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass6.this.a, a.this.a((a) AnonymousClass6.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkHeadBuilder okHeadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okHeadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass6.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api(final ICoreNetCallback<JSONObject, ACoreRequestHead> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkHeadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.6.5
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkHeadBuilder okHeadBuilder) {
                    super.onNetStart(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkHeadBuilder okHeadBuilder) {
                    super.onNetEnd(okHeadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass6.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkHeadBuilder okHeadBuilder, NetResponse<JSONObject, OkHeadBuilder> netResponse) {
                    super.onNetError(okHeadBuilder, netResponse);
                    a.this.b((a) AnonymousClass6.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkHeadBuilder okHeadBuilder, NetResponse<JSONObject, OkHeadBuilder> netResponse) {
                    super.onNetSuccess(okHeadBuilder, netResponse);
                    a.this.a((a) AnonymousClass6.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkHeadBuilder okHeadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okHeadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass6.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api2(final ICoreNetApi2Callback iCoreNetApi2Callback) {
            api(new ICoreNetCallback<JSONObject, ACoreRequestHead>() { // from class: com.ss.union.game.sdk.common.net.a.6.6
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(ACoreRequestHead aCoreRequestHead, CoreNetResponse<JSONObject, ACoreRequestHead> coreNetResponse) {
                    super.onNetError(aCoreRequestHead, coreNetResponse);
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetError(coreNetResponse.code(), coreNetResponse.message());
                    }
                }

                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(ACoreRequestHead aCoreRequestHead, CoreNetResponse<JSONObject, ACoreRequestHead> coreNetResponse) {
                    super.onNetSuccess(aCoreRequestHead, coreNetResponse);
                    JSONObject jSONObject = coreNetResponse.data;
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetSuccess(jSONObject.optJSONObject("data"));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<CoreHttpResponse, ACoreRequestHead> response() {
            return a.this.b(this.a, this.b.response());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<String, ACoreRequestHead> string() {
            return a.this.a((a) this.a, this.b.string());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONObject, ACoreRequestHead> json() {
            return a.this.a((a) this.a, this.b.json());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONArray, ACoreRequestHead> jsonArray() {
            return a.this.a((a) this.a, this.b.jsonArray());
        }
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public ACoreRequestHead head(String str) {
        return new AnonymousClass6(NetClient.head()).url(str);
    }

    /* renamed from: com.ss.union.game.sdk.common.net.a$7, reason: invalid class name */
    class AnonymousClass7 extends ACoreRequestTrace {
        ACoreRequestTrace a = this;
        final /* synthetic */ OkTraceBuilder b;

        AnonymousClass7(OkTraceBuilder okTraceBuilder) {
            this.b = okTraceBuilder;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace url(String str) {
            this.b.url(str);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public String url() {
            return this.b.url();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace tag(Object obj) {
            this.b.tag(obj);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Object tag() {
            return this.b.tag();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace header(String str, String str2) {
            this.b.header(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> headers() {
            return this.b.headers();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace urlParam(String str, String str2) {
            this.b.urlParam(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> urlParam() {
            return this.b.urlParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace timeoutReadSecond(int i) {
            this.b.timeoutReadSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace timeoutWriteSecond(int i) {
            this.b.timeoutWriteSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace timeoutConnSecond(int i) {
            this.b.timeoutConnSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace enableCommonHeader(boolean z) {
            this.b.enableCommonHeader(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonHeader() {
            return this.b.enableCommonHeader();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace enableCommonParam(boolean z) {
            this.b.enableCommonParam(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonParam() {
            return this.b.enableCommonParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestTrace allowResponseNull(boolean z) {
            this.b.allowResponseNull(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void response(final ICoreNetCallback<CoreHttpResponse, ACoreRequestTrace> iCoreNetCallback) {
            this.b.response(new NetCallback<Response, OkTraceBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.7.1
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkTraceBuilder okTraceBuilder) {
                    super.onNetStart(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkTraceBuilder okTraceBuilder) {
                    super.onNetEnd(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkTraceBuilder okTraceBuilder, NetResponse<Response, OkTraceBuilder> netResponse) {
                    super.onNetError(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass7.this.a, a.this.b(AnonymousClass7.this.a, netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkTraceBuilder okTraceBuilder, NetResponse<Response, OkTraceBuilder> netResponse) {
                    super.onNetSuccess(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass7.this.a, a.this.b(AnonymousClass7.this.a, netResponse));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void string(final ICoreNetCallback<String, ACoreRequestTrace> iCoreNetCallback) {
            this.b.string(new NetCallback<String, OkTraceBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.7.2
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkTraceBuilder okTraceBuilder) {
                    super.onNetStart(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkTraceBuilder okTraceBuilder) {
                    super.onNetEnd(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkTraceBuilder okTraceBuilder, NetResponse<String, OkTraceBuilder> netResponse) {
                    super.onNetError(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass7.this.a, a.this.a((a) AnonymousClass7.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkTraceBuilder okTraceBuilder, NetResponse<String, OkTraceBuilder> netResponse) {
                    super.onNetSuccess(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass7.this.a, a.this.a((a) AnonymousClass7.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkTraceBuilder okTraceBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okTraceBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass7.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void json(final ICoreNetCallback<JSONObject, ACoreRequestTrace> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkTraceBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.7.3
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkTraceBuilder okTraceBuilder) {
                    super.onNetStart(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkTraceBuilder okTraceBuilder) {
                    super.onNetEnd(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkTraceBuilder okTraceBuilder, NetResponse<JSONObject, OkTraceBuilder> netResponse) {
                    super.onNetError(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass7.this.a, a.this.a((a) AnonymousClass7.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkTraceBuilder okTraceBuilder, NetResponse<JSONObject, OkTraceBuilder> netResponse) {
                    super.onNetSuccess(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass7.this.a, a.this.a((a) AnonymousClass7.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkTraceBuilder okTraceBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okTraceBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass7.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void jsonArray(final ICoreNetCallback<JSONArray, ACoreRequestTrace> iCoreNetCallback) {
            this.b.jsonArray(new NetCallback<JSONArray, OkTraceBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.7.4
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkTraceBuilder okTraceBuilder) {
                    super.onNetStart(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkTraceBuilder okTraceBuilder) {
                    super.onNetEnd(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkTraceBuilder okTraceBuilder, NetResponse<JSONArray, OkTraceBuilder> netResponse) {
                    super.onNetError(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass7.this.a, a.this.a((a) AnonymousClass7.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkTraceBuilder okTraceBuilder, NetResponse<JSONArray, OkTraceBuilder> netResponse) {
                    super.onNetSuccess(okTraceBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass7.this.a, a.this.a((a) AnonymousClass7.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkTraceBuilder okTraceBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okTraceBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass7.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api(final ICoreNetCallback<JSONObject, ACoreRequestTrace> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkTraceBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.7.5
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkTraceBuilder okTraceBuilder) {
                    super.onNetStart(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkTraceBuilder okTraceBuilder) {
                    super.onNetEnd(okTraceBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass7.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkTraceBuilder okTraceBuilder, NetResponse<JSONObject, OkTraceBuilder> netResponse) {
                    super.onNetError(okTraceBuilder, netResponse);
                    a.this.b((a) AnonymousClass7.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkTraceBuilder okTraceBuilder, NetResponse<JSONObject, OkTraceBuilder> netResponse) {
                    super.onNetSuccess(okTraceBuilder, netResponse);
                    a.this.a((a) AnonymousClass7.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkTraceBuilder okTraceBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okTraceBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass7.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api2(final ICoreNetApi2Callback iCoreNetApi2Callback) {
            api(new ICoreNetCallback<JSONObject, ACoreRequestTrace>() { // from class: com.ss.union.game.sdk.common.net.a.7.6
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(ACoreRequestTrace aCoreRequestTrace, CoreNetResponse<JSONObject, ACoreRequestTrace> coreNetResponse) {
                    super.onNetError(aCoreRequestTrace, coreNetResponse);
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetError(coreNetResponse.code(), coreNetResponse.message());
                    }
                }

                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(ACoreRequestTrace aCoreRequestTrace, CoreNetResponse<JSONObject, ACoreRequestTrace> coreNetResponse) {
                    super.onNetSuccess(aCoreRequestTrace, coreNetResponse);
                    JSONObject jSONObject = coreNetResponse.data;
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetSuccess(jSONObject.optJSONObject("data"));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<CoreHttpResponse, ACoreRequestTrace> response() {
            return a.this.b(this.a, this.b.response());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<String, ACoreRequestTrace> string() {
            return a.this.a((a) this.a, this.b.string());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONObject, ACoreRequestTrace> json() {
            return a.this.a((a) this.a, this.b.json());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONArray, ACoreRequestTrace> jsonArray() {
            return a.this.a((a) this.a, this.b.jsonArray());
        }
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public ACoreRequestTrace trace(String str) {
        return new AnonymousClass7(NetClient.trace()).url(str);
    }

    /* renamed from: com.ss.union.game.sdk.common.net.a$8, reason: invalid class name */
    class AnonymousClass8 extends ACoreRequestDownload {
        ACoreRequestDownload a = this;
        final /* synthetic */ OkDownloadBuilder b;

        AnonymousClass8(OkDownloadBuilder okDownloadBuilder) {
            this.b = okDownloadBuilder;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestDownload
        public void download(File file, final ICoreNetCallback<File, ACoreRequestDownload> iCoreNetCallback) {
            this.b.download(file, new NetCallback<File, OkDownloadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.8.1
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetStart(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetEnd(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkDownloadBuilder okDownloadBuilder, NetResponse<File, OkDownloadBuilder> netResponse) {
                    super.onNetError(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkDownloadBuilder okDownloadBuilder, NetResponse<File, OkDownloadBuilder> netResponse) {
                    super.onNetSuccess(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkDownloadBuilder okDownloadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okDownloadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass8.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestDownload
        public CoreNetResponse<File, ACoreRequestDownload> download(File file) {
            return a.this.a((a) this.a, this.b.download(file));
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload url(String str) {
            this.b.url(str);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public String url() {
            return this.b.url();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload tag(Object obj) {
            this.b.tag(obj);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Object tag() {
            return this.b.tag();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload header(String str, String str2) {
            this.b.header(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> headers() {
            return this.b.headers();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload urlParam(String str, String str2) {
            this.b.urlParam(str, str2);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public Map<String, String> urlParam() {
            return this.b.urlParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload timeoutReadSecond(int i) {
            this.b.timeoutReadSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload timeoutWriteSecond(int i) {
            this.b.timeoutWriteSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload timeoutConnSecond(int i) {
            this.b.timeoutConnSecond(i);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload enableCommonHeader(boolean z) {
            this.b.enableCommonHeader(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonHeader() {
            return this.b.enableCommonHeader();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload enableCommonParam(boolean z) {
            this.b.enableCommonParam(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public boolean isEnableCommonParam() {
            return this.b.enableCommonParam();
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ACoreRequestDownload allowResponseNull(boolean z) {
            this.b.allowResponseNull(z);
            return this;
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void response(final ICoreNetCallback<CoreHttpResponse, ACoreRequestDownload> iCoreNetCallback) {
            this.b.response(new NetCallback<Response, OkDownloadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.8.2
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetStart(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetEnd(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkDownloadBuilder okDownloadBuilder, NetResponse<Response, OkDownloadBuilder> netResponse) {
                    super.onNetError(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass8.this.a, a.this.b(AnonymousClass8.this.a, netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkDownloadBuilder okDownloadBuilder, NetResponse<Response, OkDownloadBuilder> netResponse) {
                    super.onNetSuccess(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass8.this.a, a.this.b(AnonymousClass8.this.a, netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkDownloadBuilder okDownloadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okDownloadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass8.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void string(final ICoreNetCallback<String, ACoreRequestDownload> iCoreNetCallback) {
            this.b.string(new NetCallback<String, OkDownloadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.8.3
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetStart(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetEnd(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkDownloadBuilder okDownloadBuilder, NetResponse<String, OkDownloadBuilder> netResponse) {
                    super.onNetError(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkDownloadBuilder okDownloadBuilder, NetResponse<String, OkDownloadBuilder> netResponse) {
                    super.onNetSuccess(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkDownloadBuilder okDownloadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okDownloadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass8.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void json(final ICoreNetCallback<JSONObject, ACoreRequestDownload> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkDownloadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.8.4
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetStart(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetEnd(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkDownloadBuilder okDownloadBuilder, NetResponse<JSONObject, OkDownloadBuilder> netResponse) {
                    super.onNetError(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkDownloadBuilder okDownloadBuilder, NetResponse<JSONObject, OkDownloadBuilder> netResponse) {
                    super.onNetSuccess(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkDownloadBuilder okDownloadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okDownloadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass8.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void jsonArray(final ICoreNetCallback<JSONArray, ACoreRequestDownload> iCoreNetCallback) {
            this.b.jsonArray(new NetCallback<JSONArray, OkDownloadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.8.5
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetStart(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetEnd(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkDownloadBuilder okDownloadBuilder, NetResponse<JSONArray, OkDownloadBuilder> netResponse) {
                    super.onNetError(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetError(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkDownloadBuilder okDownloadBuilder, NetResponse<JSONArray, OkDownloadBuilder> netResponse) {
                    super.onNetSuccess(okDownloadBuilder, netResponse);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetSuccess(AnonymousClass8.this.a, a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkDownloadBuilder okDownloadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okDownloadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass8.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api(final ICoreNetCallback<JSONObject, ACoreRequestDownload> iCoreNetCallback) {
            this.b.json(new NetCallback<JSONObject, OkDownloadBuilder>() { // from class: com.ss.union.game.sdk.common.net.a.8.6
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetStart(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetStart(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetStart(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetEnd(OkDownloadBuilder okDownloadBuilder) {
                    super.onNetEnd(okDownloadBuilder);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetEnd(AnonymousClass8.this.a);
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(OkDownloadBuilder okDownloadBuilder, NetResponse<JSONObject, OkDownloadBuilder> netResponse) {
                    super.onNetError(okDownloadBuilder, netResponse);
                    a.this.b((a) AnonymousClass8.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(OkDownloadBuilder okDownloadBuilder, NetResponse<JSONObject, OkDownloadBuilder> netResponse) {
                    super.onNetSuccess(okDownloadBuilder, netResponse);
                    a.this.a((a) AnonymousClass8.this.a, (NetResponse) netResponse, (ICoreNetCallback<JSONObject, a>) iCoreNetCallback);
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetProgress(OkDownloadBuilder okDownloadBuilder, float f, float f2, float f3) {
                    super.onNetProgress(okDownloadBuilder, f, f2, f3);
                    if (iCoreNetCallback != null) {
                        iCoreNetCallback.onNetProgress(AnonymousClass8.this.a, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public void api2(final ICoreNetApi2Callback iCoreNetApi2Callback) {
            api(new ICoreNetCallback<JSONObject, ACoreRequestDownload>() { // from class: com.ss.union.game.sdk.common.net.a.8.7
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(ACoreRequestDownload aCoreRequestDownload, CoreNetResponse<JSONObject, ACoreRequestDownload> coreNetResponse) {
                    super.onNetError(aCoreRequestDownload, coreNetResponse);
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetError(coreNetResponse.code(), coreNetResponse.message());
                    }
                }

                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(ACoreRequestDownload aCoreRequestDownload, CoreNetResponse<JSONObject, ACoreRequestDownload> coreNetResponse) {
                    super.onNetSuccess(aCoreRequestDownload, coreNetResponse);
                    JSONObject jSONObject = coreNetResponse.data;
                    if (iCoreNetApi2Callback != null) {
                        iCoreNetApi2Callback.onNetSuccess(jSONObject.optJSONObject("data"));
                    }
                }
            });
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<CoreHttpResponse, ACoreRequestDownload> response() {
            return a.this.b(this.a, this.b.response());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<String, ACoreRequestDownload> string() {
            return a.this.a((a) this.a, this.b.string());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONObject, ACoreRequestDownload> json() {
            return a.this.a((a) this.a, this.b.json());
        }

        @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
        public CoreNetResponse<JSONArray, ACoreRequestDownload> jsonArray() {
            return a.this.a((a) this.a, this.b.jsonArray());
        }
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public ACoreRequestDownload download(String str) {
        return new AnonymousClass8(NetClient.download()).url(str);
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public void cancelAll() {
        NetClient.cancelAll();
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public void cancel(String str) {
        NetClient.cancel(str);
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public void commonHeader(String str, String str2) {
        C0062a.a(str, str2);
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public Map<String, String> commonHeaders() {
        return NetClient.getInstance().commonHeaders();
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public void commonParam(String str, String str2) {
        C0062a.b(str, str2);
    }

    @Override // com.ss.union.game.sdk.common.net.http.base.in.ACoreNetClient
    public Map<String, String> commonUrlParams() {
        return NetClient.getInstance().commonParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <R extends ICoreNetRequest, T, R1 extends NetBuilder> CoreNetResponse<T, R> a(R r, NetResponse<T, R1> netResponse) {
        return new CoreNetResponse<>(netResponse.data, netResponse.httpCode, netResponse.httpMessage, netResponse.httpBody, netResponse.code, netResponse.message, netResponse.error, netResponse.headers, r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <R extends ICoreNetRequest, R1 extends NetBuilder> CoreNetResponse<CoreHttpResponse, R> b(R r, NetResponse<Response, R1> netResponse) {
        return new CoreNetResponse<>(new CoreHttpResponse((Response) netResponse.data), netResponse.httpCode, netResponse.httpMessage, netResponse.httpBody, netResponse.code, netResponse.message, netResponse.error, netResponse.headers, r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CoreNetFileWrapper.MediaType a(MediaType mediaType) {
        if (mediaType == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(mediaType.toString())) {
                return null;
            }
            return CoreNetFileWrapper.MediaType.parse(mediaType.toString());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <R extends ICoreNetRequest, R1 extends NetBuilder> void a(R r, NetResponse<JSONObject, R1> netResponse, ICoreNetCallback<JSONObject, R> iCoreNetCallback) {
        if (iCoreNetCallback != null) {
            CoreNetResponse<JSONObject, R> coreNetResponseA = a((a) r, (NetResponse) netResponse);
            coreNetResponseA.serverTimestampS = ((JSONObject) netResponse.data).optLong("server_timestamp", System.currentTimeMillis() / 1000);
            if ("success".equals(((JSONObject) netResponse.data).optString("message"))) {
                iCoreNetCallback.onNetSuccess(r, coreNetResponseA);
                return;
            }
            coreNetResponseA.serverError(-1, netResponse.httpBody, "");
            try {
                JSONObject jSONObjectOptJSONObject = ((JSONObject) netResponse.data).optJSONObject("data");
                coreNetResponseA.serverError(jSONObjectOptJSONObject.optInt("error_code", -1), jSONObjectOptJSONObject.optString("error_msg", coreNetResponseA.httpBody), jSONObjectOptJSONObject.optString("logid", ""));
            } catch (Throwable unused) {
            }
            iCoreNetCallback.onNetError(r, coreNetResponseA);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <R extends ICoreNetRequest, R1 extends NetBuilder> void b(R r, NetResponse<JSONObject, R1> netResponse, ICoreNetCallback<JSONObject, R> iCoreNetCallback) {
        if (iCoreNetCallback != null) {
            CoreNetResponse<JSONObject, R> coreNetResponseA = a((a) r, (NetResponse) netResponse);
            coreNetResponseA.serverTimestampS = System.currentTimeMillis() / 1000;
            if (!TextUtils.isEmpty(netResponse.httpBody)) {
                coreNetResponseA.serverError(-1, netResponse.httpBody, "");
                try {
                    JSONObject jSONObjectOptJSONObject = new JSONObject(netResponse.httpBody).optJSONObject("data");
                    coreNetResponseA.serverError(jSONObjectOptJSONObject.optInt("error_code", -1), jSONObjectOptJSONObject.optString("error_msg", coreNetResponseA.httpBody), jSONObjectOptJSONObject.optString("logid", ""));
                } catch (Throwable unused) {
                }
                iCoreNetCallback.onNetError(r, coreNetResponseA);
                return;
            }
            iCoreNetCallback.onNetError(r, coreNetResponseA);
        }
    }

    /* renamed from: com.ss.union.game.sdk.common.net.a$a, reason: collision with other inner class name */
    private static class C0062a {
        private static final Map<String, String> a = new HashMap();
        private static final Map<String, String> b = new HashMap();

        private C0062a() {
        }

        static synchronized Map<String, String> a() {
            return a;
        }

        static synchronized void a(String str, String str2) {
            a.put(str, str2);
        }

        static synchronized Map<String, String> b() {
            b.put("ac", NetworkUtils.getNetworkAccessType());
            b.put(com.ss.union.game.sdk.redemptionCode.a.t, System.currentTimeMillis() + "");
            return b;
        }

        static synchronized void b(String str, String str2) {
            b.put(str, str2);
        }
    }
}
