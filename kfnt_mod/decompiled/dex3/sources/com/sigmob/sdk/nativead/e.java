package com.sigmob.sdk.nativead;

import android.content.Context;
import com.sigmob.sdk.base.models.BaseAdUnit;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e implements com.sigmob.sdk.base.common.v {
    private int b = 0;
    private BaseAdUnit c = null;
    private String d = "";
    private int e = 0;
    private final HashSet<d> a = new HashSet<>();

    public e(Context context) {
        this.a.add(new d());
    }

    @Override // com.sigmob.sdk.base.common.v
    public void a() {
        Iterator<d> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(this.c);
            this.c.setSessionManager(null);
            this.c = null;
        }
    }

    @Override // com.sigmob.sdk.base.common.v
    public void a(Context context, BaseAdUnit baseAdUnit) {
        Iterator<d> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(context, baseAdUnit);
            this.c = baseAdUnit;
            this.c.setSessionManager(this);
        }
    }

    @Override // com.sigmob.sdk.base.common.v
    public void a(com.sigmob.sdk.base.common.a aVar, int i) {
        Iterator<d> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(this.c, aVar, i);
        }
    }
}
