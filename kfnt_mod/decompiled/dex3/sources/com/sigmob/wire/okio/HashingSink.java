package com.sigmob.wire.okio;

import com.ss.union.game.sdk.common.util.StringEncryptUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class HashingSink extends ForwardingSink {
    private final MessageDigest messageDigest;

    private HashingSink(Sink sink, String str) {
        super(sink);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public static HashingSink md5(Sink sink) {
        return new HashingSink(sink, "MD5");
    }

    public static HashingSink sha1(Sink sink) {
        return new HashingSink(sink, StringEncryptUtils.SHA_1);
    }

    public static HashingSink sha256(Sink sink) {
        return new HashingSink(sink, StringEncryptUtils.SHA_256);
    }

    public ByteString hash() {
        return ByteString.of(this.messageDigest.digest());
    }

    @Override // com.sigmob.wire.okio.ForwardingSink, com.sigmob.wire.okio.Sink
    public void write(Buffer buffer, long j) {
        Util.checkOffsetAndCount(buffer.size, 0L, j);
        Segment segment = buffer.head;
        long j2 = 0;
        while (j2 < j) {
            int iMin = (int) Math.min(j - j2, segment.limit - segment.pos);
            this.messageDigest.update(segment.data, segment.pos, iMin);
            j2 += iMin;
            segment = segment.next;
        }
        super.write(buffer, j);
    }
}
