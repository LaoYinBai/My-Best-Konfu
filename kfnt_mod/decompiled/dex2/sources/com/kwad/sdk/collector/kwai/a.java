package com.kwad.sdk.collector.kwai;

import android.content.Context;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements b {
    protected boolean a;
    protected List<b> b;

    public a() {
        this.a = true;
    }

    public a(boolean z) {
        this.a = z;
    }

    public List<b> a() {
        return this.b;
    }

    @Override // com.kwad.sdk.collector.kwai.b
    public boolean a(Context context) {
        if (!this.a) {
            return false;
        }
        List<b> listA = a();
        if (listA == null || listA.size() <= 0) {
            try {
                return b(context);
            } catch (Throwable unused) {
                return false;
            }
        }
        Iterator<b> it = listA.iterator();
        while (it.hasNext()) {
            if (it.next().a(context)) {
                return true;
            }
        }
        return false;
    }

    protected boolean b(Context context) {
        return false;
    }
}
