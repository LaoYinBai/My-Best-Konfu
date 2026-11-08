package com.sigmob.sdk.common.e;

import android.text.TextUtils;
import com.sigmob.volley.n;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class k<T> extends com.sigmob.volley.l<T> {
    private final String b;

    protected k(String str, int i, n.a aVar) {
        super(i, str, aVar);
        this.b = str;
        a((p) new com.sigmob.volley.c(10000, 2, 0.0f));
        a(false);
    }

    @Override // com.sigmob.volley.l
    protected com.sigmob.volley.n<T> a(com.sigmob.volley.i iVar) {
        return com.sigmob.volley.n.a(iVar, com.sigmob.volley.toolbox.h.a(iVar));
    }

    @Override // com.sigmob.volley.l
    public String a() {
        return super.a();
    }

    @Override // com.sigmob.volley.l
    protected s b(s sVar) {
        return super.b(sVar);
    }

    @Override // com.sigmob.volley.l
    public byte[] b() {
        String strA = m.a(l(), h());
        if (strA == null) {
            return null;
        }
        return strA.getBytes();
    }

    @Override // com.sigmob.volley.l
    public Map<String, String> c() {
        TreeMap treeMap = new TreeMap();
        if (!m.a(this.b)) {
            return treeMap;
        }
        String language = Locale.getDefault().getLanguage();
        String strA = null;
        if (com.sigmob.sdk.common.a.n() != null) {
            Locale localeX = com.sigmob.sdk.common.a.n().X();
            if (!localeX.getLanguage().trim().isEmpty()) {
                language = localeX.getLanguage().trim();
            }
            if (!TextUtils.isEmpty(com.sigmob.sdk.common.a.n().t())) {
                strA = com.sigmob.sdk.common.utils.k.a(com.sigmob.sdk.common.a.n().t());
            }
        }
        if (!TextUtils.isEmpty(language)) {
            treeMap.put(i.ACCEPT_LANGUAGE.a(), language);
        }
        if (!TextUtils.isEmpty(strA)) {
            treeMap.put("token", strA);
        }
        return treeMap;
    }
}
