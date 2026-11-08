package com.mbridge.msdk.foundation.same.net.d;

import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: JsonArrayRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends e<JSONArray> {
    private static final String c = "c";

    public c(int i, String str, String str2, com.mbridge.msdk.foundation.same.net.e<JSONArray> eVar) {
        super(i, str, str2, eVar);
    }

    @Override // com.mbridge.msdk.foundation.same.net.i
    protected final k<JSONArray> a(com.mbridge.msdk.foundation.same.net.f.c cVar) {
        try {
            return k.a(new JSONArray(new String(cVar.b, com.mbridge.msdk.foundation.same.net.g.b.a(cVar.d))), cVar);
        } catch (UnsupportedEncodingException e) {
            s.d(c, e.getMessage());
            return k.a(new com.mbridge.msdk.foundation.same.net.a.a(8, cVar));
        } catch (JSONException e2) {
            s.d(c, e2.getMessage());
            return k.a(new com.mbridge.msdk.foundation.same.net.a.a(8, cVar));
        }
    }
}
