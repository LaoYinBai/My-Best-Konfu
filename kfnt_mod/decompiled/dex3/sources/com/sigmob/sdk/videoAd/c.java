package com.sigmob.sdk.videoAd;

import android.os.Handler;
import com.sigmob.sdk.base.common.e;
import com.sigmob.sdk.common.utils.m;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c extends com.sigmob.sdk.common.b.b {
    private final h a;
    private final a c;

    public c(h hVar, a aVar, Handler handler) {
        super(handler);
        m.a.a(hVar);
        m.a.a(aVar);
        this.a = hVar;
        this.c = aVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new e(e.a.QUARTILE_EVENT, com.sigmob.sdk.base.common.a.AD_START, 0.0f));
        arrayList.add(new e(e.a.QUARTILE_EVENT, com.sigmob.sdk.base.common.a.AD_PLAY_QUARTER, 0.25f));
        arrayList.add(new e(e.a.QUARTILE_EVENT, com.sigmob.sdk.base.common.a.AD_PLAY_TWO_QUARTERS, 0.5f));
        arrayList.add(new e(e.a.QUARTILE_EVENT, com.sigmob.sdk.base.common.a.AD_PLAY_THREE_QUARTERS, 0.75f));
        this.c.a(arrayList);
    }

    @Override // com.sigmob.sdk.common.b.b
    public void a() {
        int iQ = this.a.q();
        int iR = this.a.r();
        if (iQ > 0) {
            if (this.a.u()) {
                this.a.b(false);
            }
            this.a.v();
            if (iR + 1000 < iQ && this.a.n()) {
                this.a.t();
            }
            if (this.a.o()) {
                this.a.p();
            }
            for (com.sigmob.sdk.base.common.e eVar : this.c.a(iR, iQ)) {
                this.a.a(eVar.o());
                eVar.k();
            }
            if (iR > iQ) {
                this.a.a(true);
            }
        }
    }
}
