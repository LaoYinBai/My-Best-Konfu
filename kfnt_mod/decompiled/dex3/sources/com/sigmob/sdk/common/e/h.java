package com.sigmob.sdk.common.e;

import com.sigmob.volley.toolbox.j;
import java.util.Map;
import java.util.TreeMap;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h extends com.sigmob.volley.toolbox.j {
    private final String a;

    public h(String str, j.b bVar, SSLSocketFactory sSLSocketFactory) {
        super(bVar, sSLSocketFactory);
        this.a = str;
    }

    @Override // com.sigmob.volley.toolbox.j, com.sigmob.volley.toolbox.a
    public com.sigmob.volley.toolbox.i a(com.sigmob.volley.l<?> lVar, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            map = new TreeMap<>();
        }
        map.put(i.USER_AGENT.a(), this.a);
        return super.a(lVar, map);
    }
}
