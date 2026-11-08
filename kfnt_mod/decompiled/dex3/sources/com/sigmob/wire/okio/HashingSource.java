package com.sigmob.wire.okio;

import com.ss.union.game.sdk.common.util.StringEncryptUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class HashingSource extends ForwardingSource {
    private final MessageDigest messageDigest;

    private HashingSource(Source source, String str) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public static HashingSource md5(Source source) {
        return new HashingSource(source, "MD5");
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, StringEncryptUtils.SHA_1);
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, StringEncryptUtils.SHA_256);
    }

    public ByteString hash() {
        return ByteString.of(this.messageDigest.digest());
    }

    @Override // com.sigmob.wire.okio.ForwardingSource, com.sigmob.wire.okio.Source
    public long read(Buffer buffer, long j) {
        long j2 = super.read(buffer, j);
        if (j2 != -1) {
            long j3 = buffer.size - j2;
            long j4 = buffer.size;
            Segment segment = buffer.head;
            while (j4 > buffer.size - j2) {
                segment = segment.prev;
                j4 -= segment.limit - segment.pos;
            }
            while (j4 < buffer.size) {
                int i = (int) ((segment.pos + j3) - j4);
                this.messageDigest.update(segment.data, i, segment.limit - i);
                j3 = (segment.limit - segment.pos) + j4;
                j4 = j3;
            }
        }
        return j2;
    }
}
