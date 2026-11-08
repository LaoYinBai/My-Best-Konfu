package com.kwad.sdk.core.a;

import android.content.Context;
import androidx.annotation.NonNull;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.utils.ar;
import com.kwad.sdk.utils.p;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements i {
    private static String a() {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return "";
        }
        return context.getPackageName() + ar.b(context);
    }

    @Override // com.kwad.sdk.core.a.i
    public String a(String str) {
        try {
            String strA = p.a(0);
            return new String(c.a().a(b.a(strA.getBytes(C.UTF8_NAME), b.a(str.getBytes()))), C.UTF8_NAME);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            return str;
        }
    }

    @Override // com.kwad.sdk.core.a.i
    public void a(String str, Map<String, String> map, String str2) {
        f.a(str, map, str2);
    }

    @Override // com.kwad.sdk.core.a.i
    public void a(@NonNull Map<String, String> map) {
        map.put("Ks-PkgId", a());
        map.put("Ks-Encoding", "2");
    }

    @Override // com.kwad.sdk.core.a.i
    public String b(String str) {
        try {
            return new String(b.b(b.a(p.a(0), c.b().a(str.getBytes()))), C.UTF8_NAME);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
            return str;
        }
    }
}
