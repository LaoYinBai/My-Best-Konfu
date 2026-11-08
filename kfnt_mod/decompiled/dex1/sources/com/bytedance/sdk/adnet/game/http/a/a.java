package com.bytedance.sdk.adnet.game.http.a;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSink;
import com.bytedance.sdk.adnet.game.ok3.okio.GzipSink;
import com.bytedance.sdk.adnet.game.ok3.okio.Okio;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Interceptor {
    private RequestBody a(final RequestBody requestBody) {
        return new RequestBody() { // from class: com.bytedance.sdk.adnet.game.http.a.a.1
            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public long contentLength() {
                return -1L;
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) {
                BufferedSink bufferedSinkBuffer = Okio.buffer(new GzipSink(bufferedSink));
                requestBody.writeTo(bufferedSinkBuffer);
                bufferedSinkBuffer.close();
            }
        };
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        return (TextUtils.isEmpty(request.header("h_gzip_control")) || request.body() == null) ? chain.proceed(request) : chain.proceed(request.newBuilder().header("Content-Encoding", HttpConstant.GZIP).method(request.method(), a(request.body())).build());
    }
}
