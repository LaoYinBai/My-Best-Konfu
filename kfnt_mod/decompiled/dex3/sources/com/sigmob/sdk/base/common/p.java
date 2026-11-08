package com.sigmob.sdk.base.common;

import android.content.Context;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseAdUnit;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class p implements v {
    private final Set<o> a = new HashSet();
    private BaseAdUnit b;

    public p(Context context) {
        this.a.add(new u());
    }

    @Override // com.sigmob.sdk.base.common.v
    public void a() {
        SigmobLog.d("endDisplaySession() called");
        Iterator<o> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(this.b);
        }
    }

    public void a(int i, int i2) {
        SigmobLog.d("onVideoPrepared() called  mDuration = [" + i + "]");
        Iterator<o> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(this.b, i, i2);
        }
    }

    @Override // com.sigmob.sdk.base.common.v
    public void a(Context context, BaseAdUnit baseAdUnit) {
        this.b = baseAdUnit;
        Iterator<o> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(context, baseAdUnit, (String) null);
            baseAdUnit.setSessionManager(this);
        }
    }

    public void a(Context context, BaseAdUnit baseAdUnit, String str) {
        this.b = baseAdUnit;
        Iterator<o> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(context, baseAdUnit, str);
            baseAdUnit.setSessionManager(this);
        }
    }

    @Override // com.sigmob.sdk.base.common.v
    public void a(a aVar, int i) {
        SigmobLog.d("startDeferredDisplaySession() called");
        Iterator<o> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(this.b, aVar, i);
        }
    }

    public void a(boolean z, int i) {
        Iterator<o> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(this.b, z, i);
        }
    }
}
