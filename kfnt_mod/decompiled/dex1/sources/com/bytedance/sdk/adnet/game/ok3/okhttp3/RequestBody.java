package com.bytedance.sdk.adnet.game.ok3.okhttp3;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.Util;
import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSink;
import com.bytedance.sdk.adnet.game.ok3.okio.ByteString;
import com.bytedance.sdk.adnet.game.ok3.okio.Okio;
import com.bytedance.sdk.adnet.game.ok3.okio.Source;
import java.io.File;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class RequestBody {
    public static RequestBody create(final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody.1
            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public long contentLength() {
                return byteString.size();
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) {
                bufferedSink.write(byteString);
            }
        };
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody.3
                @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
                public void writeTo(BufferedSink bufferedSink) throws Throwable {
                    Source source;
                    Source source2 = null;
                    try {
                        source = Okio.source(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        bufferedSink.writeAll(source);
                        Util.closeQuietly(source);
                    } catch (Throwable th2) {
                        th = th2;
                        source2 = source;
                        Util.closeQuietly(source2);
                        throw th;
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount(bArr.length, i, i2);
        return new RequestBody() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody.2
            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public long contentLength() {
                return i2;
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) {
                bufferedSink.write(bArr, i, i2);
            }
        };
    }

    public long contentLength() {
        return -1L;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink);
}
