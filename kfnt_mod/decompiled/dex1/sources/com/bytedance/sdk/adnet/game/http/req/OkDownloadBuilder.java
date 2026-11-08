package com.bytedance.sdk.adnet.game.http.req;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.bytedance.sdk.adnet.game.http.cb.NetResponse;
import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSink;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource;
import com.bytedance.sdk.adnet.game.ok3.okio.Okio;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OkDownloadBuilder extends NetBuilder<OkDownloadBuilder> {

    /* renamed from: com.bytedance.sdk.adnet.game.http.req.OkDownloadBuilder$1, reason: invalid class name */
    class AnonymousClass1 extends NetCallback<Response, OkDownloadBuilder> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ NetCallback f5040a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ File f5041b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f5042c;

        AnonymousClass1(NetCallback netCallback, File file, Handler handler) {
            this.f5040a = netCallback;
            this.f5041b = file;
            this.f5042c = handler;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(final OkDownloadBuilder okDownloadBuilder, final float f, final float f2, final float f3) {
            this.f5042c.post(new Runnable() { // from class: com.bytedance.sdk.adnet.game.http.req.OkDownloadBuilder.1.2
                @Override // java.lang.Runnable
                public void run() {
                    if (AnonymousClass1.this.f5040a != null) {
                        AnonymousClass1.this.f5040a.onNetProgress(okDownloadBuilder, f, f2, f3);
                    }
                }
            });
        }

        @Override // com.bytedance.sdk.adnet.game.http.cb.NetCallback
        public void onNetEnd(OkDownloadBuilder okDownloadBuilder) {
            super.onNetEnd((AnonymousClass1) okDownloadBuilder);
        }

        public void onNetError(OkDownloadBuilder okDownloadBuilder, NetResponse<Response, OkDownloadBuilder> netResponse) {
            super.onNetError((AnonymousClass1) okDownloadBuilder, (NetResponse<T, AnonymousClass1>) netResponse);
            if (this.f5040a != null) {
                this.f5040a.onNetError(okDownloadBuilder, NetResponse.error(netResponse.httpCode, netResponse.httpMessage, netResponse.httpBody, netResponse.code, netResponse.message, netResponse.error, netResponse.headers, okDownloadBuilder));
                this.f5040a.onNetEnd(okDownloadBuilder);
            }
        }

        @Override // com.bytedance.sdk.adnet.game.http.cb.NetCallback
        public /* bridge */ /* synthetic */ void onNetError(NetBuilder netBuilder, NetResponse netResponse) {
            onNetError((OkDownloadBuilder) netBuilder, (NetResponse<Response, OkDownloadBuilder>) netResponse);
        }

        @Override // com.bytedance.sdk.adnet.game.http.cb.NetCallback
        public void onNetProgress(OkDownloadBuilder okDownloadBuilder, float f, float f2, float f3) {
            super.onNetProgress((AnonymousClass1) okDownloadBuilder, f, f2, f3);
        }

        @Override // com.bytedance.sdk.adnet.game.http.cb.NetCallback
        public void onNetStart(OkDownloadBuilder okDownloadBuilder) {
            super.onNetStart((AnonymousClass1) okDownloadBuilder);
            if (this.f5040a != null) {
                this.f5040a.onNetStart(okDownloadBuilder);
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.bytedance.sdk.adnet.game.http.req.OkDownloadBuilder$1$1] */
        public void onNetSuccess(final OkDownloadBuilder okDownloadBuilder, final NetResponse<Response, OkDownloadBuilder> netResponse) {
            super.onNetSuccess((AnonymousClass1) okDownloadBuilder, (NetResponse<T, AnonymousClass1>) netResponse);
            new Thread() { // from class: com.bytedance.sdk.adnet.game.http.req.OkDownloadBuilder.1.1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:61:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:65:0x00ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:79:? A[SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00a6 -> B:69:0x00a9). Please report as a decompilation issue!!! */
                @Override // java.lang.Thread, java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() throws java.lang.Throwable {
                    /*
                        Method dump skipped, instructions count: 193
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adnet.game.http.req.OkDownloadBuilder.AnonymousClass1.C01521.run():void");
                }
            }.start();
        }

        @Override // com.bytedance.sdk.adnet.game.http.cb.NetCallback
        public /* bridge */ /* synthetic */ void onNetSuccess(NetBuilder netBuilder, NetResponse netResponse) {
            onNetSuccess((OkDownloadBuilder) netBuilder, (NetResponse<Response, OkDownloadBuilder>) netResponse);
        }
    }

    public OkDownloadBuilder() {
        enableCommonParam(false);
    }

    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    protected void a(Request.Builder builder) {
        builder.get();
    }

    public NetResponse<File, OkDownloadBuilder> download(File file) throws Throwable {
        BufferedSource bufferedSourceSource;
        Throwable th;
        BufferedSink bufferedSinkBuffer;
        if (file.exists()) {
            file.delete();
        }
        NetResponse<Response, OkDownloadBuilder> netResponseResponse = response();
        if (netResponseResponse.code != 10000) {
            return NetResponse.error(netResponseResponse.httpCode, netResponseResponse.httpMessage, netResponseResponse.httpBody, netResponseResponse.code, netResponseResponse.message, netResponseResponse.error, netResponseResponse.headers, netResponseResponse.request);
        }
        BufferedSink bufferedSink = null;
        try {
            bufferedSourceSource = netResponseResponse.data.body().source();
            try {
                try {
                    bufferedSinkBuffer = Okio.buffer(Okio.sink(file));
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                bufferedSinkBuffer.writeAll(bufferedSourceSource);
                NetResponse<File, OkDownloadBuilder> netResponseSuccess = NetResponse.success(netResponseResponse.httpCode, netResponseResponse.httpMessage, netResponseResponse.httpBody, netResponseResponse.code, netResponseResponse.message, file, netResponseResponse.headers, netResponseResponse.request);
                if (bufferedSinkBuffer != null) {
                    try {
                        bufferedSinkBuffer.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
                if (bufferedSourceSource != null) {
                    try {
                        bufferedSourceSource.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                return netResponseSuccess;
            } catch (Throwable th6) {
                th = th6;
                bufferedSink = bufferedSinkBuffer;
                NetResponse<File, OkDownloadBuilder> netResponseError = NetResponse.error(netResponseResponse.httpCode, netResponseResponse.httpMessage, netResponseResponse.httpBody, 10004, NetCallback.ERROR_CODE_PARSE_BODY_EXCEPTION_DESC, th, netResponseResponse.headers, netResponseResponse.request);
                if (bufferedSink != null) {
                    try {
                        bufferedSink.close();
                    } catch (Throwable th7) {
                        th7.printStackTrace();
                    }
                }
                if (bufferedSourceSource != null) {
                    try {
                        bufferedSourceSource.close();
                    } catch (Throwable th8) {
                        th8.printStackTrace();
                    }
                }
                return netResponseError;
            }
        } catch (Throwable th9) {
            th = th9;
            bufferedSourceSource = null;
        }
    }

    public void download(File file, NetCallback<File, OkDownloadBuilder> netCallback) {
        response(new AnonymousClass1(netCallback, file, new Handler(Looper.getMainLooper())));
    }

    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    public String method() {
        return "DOWNLOAD";
    }
}
