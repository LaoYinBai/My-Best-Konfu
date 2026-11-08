package com.ss.union.game.sdk.common.net.http.base.response;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.umeng.message.util.HttpRequest;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CoreHttpResponse {
    private Response a;

    public CoreHttpResponse(Response response) {
        this.a = response;
    }

    public InputStream byteStream() {
        InputStream inputStreamByteStream = this.a.body().byteStream();
        String strHeader = this.a.header(HttpRequest.HEADER_CONTENT_ENCODING);
        if (strHeader == null || !HttpRequest.ENCODING_GZIP.equalsIgnoreCase(strHeader) || (inputStreamByteStream instanceof GZIPInputStream)) {
            return inputStreamByteStream;
        }
        try {
            return new GZIPInputStream(inputStreamByteStream);
        } catch (Throwable unused) {
            return inputStreamByteStream;
        }
    }

    public void close() {
        try {
            this.a.body().close();
        } catch (Throwable unused) {
        }
    }
}
