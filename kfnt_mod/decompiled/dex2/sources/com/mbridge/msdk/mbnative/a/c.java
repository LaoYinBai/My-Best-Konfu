package com.mbridge.msdk.mbnative.a;

import com.mbridge.msdk.out.Campaign;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: AdmobCache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c extends b<String, List<Campaign>> {
    private static Map<String, Map<Long, Object>> a = new HashMap();

    @Override // com.mbridge.msdk.mbnative.a.b
    public final void a(String str, Campaign campaign, String str2) {
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final /* bridge */ /* synthetic */ void a(String str, List<Campaign> list) {
        a(str, list, a);
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final /* bridge */ /* synthetic */ void a(String str, List<Campaign> list, String str2) {
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final /* synthetic */ List<Campaign> b(String str, int i) {
        return a(str, a, i);
    }

    @Override // com.mbridge.msdk.mbnative.a.b
    public final void a(String str) {
        try {
            a(str, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
