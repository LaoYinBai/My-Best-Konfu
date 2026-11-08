package com.sigmob.sdk.base.common;

import android.content.Context;
import android.os.Bundle;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.models.SigmobError;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class r extends k {
    protected String a;
    protected k.b b;
    protected BaseAdUnit c;
    protected String d;
    private EventForwardingBroadcastReceiver e;

    protected r(k.b bVar) {
        this.b = null;
        this.b = bVar;
    }

    protected abstract void a(Context context, k.b bVar);

    public void a(Context context, BaseAdUnit baseAdUnit, Bundle bundle) {
        this.e = new EventForwardingBroadcastReceiver(baseAdUnit, this.b, this.a);
        this.e.a(this.e, context);
    }

    public void a(Context context, Map<String, Object> map, BaseAdUnit baseAdUnit) {
        if (!b(baseAdUnit)) {
            this.b.a(baseAdUnit, SigmobError.ERROR_SIGMOB_INFORMATION_LOSE.toString());
            return;
        }
        this.c = baseAdUnit;
        SigmobLog.d("baseAdUnitValid success");
        try {
            this.a = baseAdUnit.getUuid();
            a(context, this.b);
        } catch (ClassCastException unused) {
            SigmobLog.e("LocalExtras contained an incorrect type.");
            this.b.a(baseAdUnit, SigmobError.ERROR_SIGMOB_INFORMATION_LOSE.toString());
        }
    }

    public void a(String str) {
        this.d = str;
    }

    protected abstract boolean b(BaseAdUnit baseAdUnit);

    public void c(BaseAdUnit baseAdUnit) {
        if (this.e != null) {
            this.e.a(this.e);
        }
    }
}
