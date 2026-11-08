package com.mbridge.msdk.foundation.same.net.d;

import com.mbridge.msdk.foundation.same.net.i;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import java.io.UnsupportedEncodingException;

/* compiled from: StringRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends i<String> {
    private static final String c = "f";
    private String d;

    public f(int i, String str, String str2, com.mbridge.msdk.foundation.same.net.e<String> eVar) {
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

    @Override // com.mbridge.msdk.foundation.same.net.i
    protected final k<String> a(com.mbridge.msdk.foundation.same.net.f.c cVar) {
        try {
            return k.a(new String(cVar.b, com.mbridge.msdk.foundation.same.net.g.b.a(cVar.d)), cVar);
        } catch (UnsupportedEncodingException e) {
            s.d(c, e.getMessage());
            return k.a(new com.mbridge.msdk.foundation.same.net.a.a(8, cVar));
        }
    }
}
