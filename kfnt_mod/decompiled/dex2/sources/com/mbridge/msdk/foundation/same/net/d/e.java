package com.mbridge.msdk.foundation.same.net.d;

import com.mbridge.msdk.foundation.same.net.i;
import com.mbridge.msdk.foundation.tools.s;
import java.io.UnsupportedEncodingException;

/* compiled from: JsonRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class e<T> extends i<T> {
    private static final String c = "e";
    private final String d;

    public e(int i, String str, String str2, com.mbridge.msdk.foundation.same.net.e<T> eVar) {
        super(i, str, eVar);
        this.d = str2;
    }

    @Override // com.mbridge.msdk.foundation.same.net.i
    public final byte[] e() {
        try {
            if (this.d == null) {
                return null;
            }
            return this.d.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            s.d(c, "Unsupported Encoding while trying to get the bytes of " + this.d + " using utf-8");
            return null;
        }
    }
}
