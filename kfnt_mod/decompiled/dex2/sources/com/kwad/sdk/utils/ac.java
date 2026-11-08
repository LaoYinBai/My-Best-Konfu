package com.kwad.sdk.utils;

import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ac {
    private Map<String, com.kwad.sdk.core.download.a.b> a;
    private Map<String, com.kwad.sdk.core.webview.kwai.c> b;

    public com.kwad.sdk.core.download.a.b a(String str) {
        return this.a.get(str);
    }

    public void a() {
        Iterator<com.kwad.sdk.core.download.a.b> it = this.a.values().iterator();
        while (it.hasNext()) {
            it.next().j();
        }
        this.a.clear();
        this.b.clear();
    }

    public void a(String str, com.kwad.sdk.core.download.a.b bVar) {
        this.a.put(str, bVar);
    }

    public void a(String str, com.kwad.sdk.core.webview.kwai.c cVar) {
        this.b.put(str, cVar);
    }

    public com.kwad.sdk.core.webview.kwai.c b(String str) {
        return this.b.get(str);
    }
}
