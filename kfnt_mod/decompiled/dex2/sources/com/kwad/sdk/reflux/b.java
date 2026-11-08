package com.kwad.sdk.reflux;

import com.kwad.sdk.core.download.a.c;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends c {
    private int b;
    private AdTemplate c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private boolean k = false;
    private int l = -1;
    private com.kwad.sdk.core.download.a.b m;
    private int n;
    private int o;
    private a p;

    public interface a {
        void a(b bVar);
    }

    public static b a(AdTemplate adTemplate) {
        String strH;
        AdInfo adInfoJ = d.j(adTemplate);
        b bVar = new b();
        bVar.b(0);
        bVar.k = com.kwad.sdk.core.response.a.a.B(adInfoJ);
        bVar.d = adTemplate.type;
        bVar.c = adTemplate;
        bVar.e = com.kwad.sdk.core.response.a.a.s(adInfoJ);
        List<String> listO = com.kwad.sdk.core.response.a.a.O(adInfoJ);
        if (listO.size() <= 0) {
            if (com.kwad.sdk.core.response.a.a.P(adInfoJ)) {
                strH = com.kwad.sdk.core.response.a.a.h(adInfoJ);
            }
            bVar.j = com.kwad.sdk.core.response.a.a.A(adInfoJ);
            bVar.i = com.kwad.sdk.core.response.a.a.s(adInfoJ);
            bVar.h = com.kwad.sdk.core.response.a.a.at(adInfoJ);
            bVar.f = com.kwad.sdk.core.response.a.a.au(adInfoJ);
            return bVar;
        }
        strH = listO.get(0);
        bVar.g = strH;
        bVar.j = com.kwad.sdk.core.response.a.a.A(adInfoJ);
        bVar.i = com.kwad.sdk.core.response.a.a.s(adInfoJ);
        bVar.h = com.kwad.sdk.core.response.a.a.at(adInfoJ);
        bVar.f = com.kwad.sdk.core.response.a.a.au(adInfoJ);
        return bVar;
    }

    public static List<b> a(List<AdTemplate> list, List<com.kwad.sdk.core.download.a.b> list2) {
        ArrayList arrayList = null;
        if (list != null && list.size() != 0 && list2.size() != 0) {
            if (list.size() != list2.size()) {
                return null;
            }
            arrayList = new ArrayList();
            int i = 0;
            Iterator<AdTemplate> it = list.iterator();
            while (it.hasNext()) {
                b bVarA = a(it.next());
                bVarA.a(list2.get(i));
                bVarA.l = i;
                arrayList.add(bVarA);
                i++;
            }
        }
        return arrayList;
    }

    private void o() {
        if (this.p != null) {
            this.p.a(this);
        }
    }

    public void a(com.kwad.sdk.core.download.a.b bVar) {
        this.m = bVar;
    }

    public void a(a aVar) {
        this.p = aVar;
    }

    public void b() {
        if (!i() || this.m == null) {
            return;
        }
        this.m.a(this);
    }

    public void b(int i) {
        this.b = i;
    }

    public AdTemplate c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public String f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.g;
    }

    public boolean i() {
        return this.k;
    }

    public int j() {
        return this.l;
    }

    public int k() {
        return this.b == 0 ? this.d : this.b;
    }

    public int l() {
        return this.n;
    }

    public int m() {
        return this.o;
    }

    public com.kwad.sdk.core.download.a.b n() {
        return this.m;
    }

    @Override // com.kwad.sdk.api.KsAppDownloadListener
    public void onDownloadFailed() {
        this.n = 11;
        o();
    }

    @Override // com.kwad.sdk.api.KsAppDownloadListener
    public void onDownloadFinished() {
        this.n = 8;
        o();
    }

    @Override // com.kwad.sdk.api.KsAppDownloadListener
    public void onIdle() {
        this.n = 0;
        this.o = 0;
        o();
    }

    @Override // com.kwad.sdk.api.KsAppDownloadListener
    public void onInstalled() {
        this.n = 12;
        o();
    }

    @Override // com.kwad.sdk.api.KsAppDownloadListener
    public void onProgressUpdate(int i) {
        this.n = 2;
        this.o = i;
        o();
    }
}
