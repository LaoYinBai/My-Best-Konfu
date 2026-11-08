package com.bytedance.sdk.adnet.game.ok3.okio;

import com.bytedance.common.utility.StringEncryptUtils;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class HashingSource extends ForwardingSource {
    private final Mac mac;
    private final MessageDigest messageDigest;

    private HashingSource(Source source, ByteString byteString, String str) throws InvalidKeyException {
        super(source);
        try {
            this.mac = Mac.getInstance(str);
            this.mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            this.messageDigest = null;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private HashingSource(Source source, String str) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
            this.mac = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public static HashingSource hmacSha1(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA1");
    }

    public static HashingSource hmacSha256(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA256");
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

    public final ByteString hash() {
        return ByteString.of(this.messageDigest != null ? this.messageDigest.digest() : this.mac.doFinal());
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.ForwardingSource, com.bytedance.sdk.adnet.game.ok3.okio.Source
    public long read(Buffer buffer, long j) throws IllegalStateException {
        long j2 = super.read(buffer, j);
        if (j2 != -1) {
            long j3 = buffer.size - j2;
            long j4 = buffer.size;
            Segment segment = buffer.head;
            while (j4 > j3) {
                segment = segment.prev;
                j4 -= segment.limit - segment.pos;
            }
            while (j4 < buffer.size) {
                int i = (int) ((segment.pos + j3) - j4);
                if (this.messageDigest != null) {
                    this.messageDigest.update(segment.data, i, segment.limit - i);
                } else {
                    this.mac.update(segment.data, i, segment.limit - i);
                }
                j3 = (segment.limit - segment.pos) + j4;
                segment = segment.next;
                j4 = j3;
            }
        }
        return j2;
    }
}
