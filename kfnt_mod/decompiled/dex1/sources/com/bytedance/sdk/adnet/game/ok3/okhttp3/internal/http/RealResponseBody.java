package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.MediaType;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.ResponseBody;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class RealResponseBody extends ResponseBody {
    private final long contentLength;
    private final String contentTypeString;
    private final BufferedSource source;

    public RealResponseBody(String str, long j, BufferedSource bufferedSource) {
        this.contentTypeString = str;
        this.contentLength = j;
        this.source = bufferedSource;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.ResponseBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.ResponseBody
    public MediaType contentType() {
        if (this.contentTypeString != null) {
            return MediaType.parse(this.contentTypeString);
        }
        return null;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
