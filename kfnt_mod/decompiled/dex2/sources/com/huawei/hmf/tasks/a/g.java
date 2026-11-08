package com.huawei.hmf.tasks.a;

import android.app.Activity;
import android.app.Fragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class g extends Fragment {
    private static final WeakHashMap<Activity, WeakReference<g>> b = new WeakHashMap<>();
    private final List<WeakReference<com.huawei.hmf.tasks.b<?>>> a = new ArrayList();

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        synchronized (this.a) {
            Iterator<WeakReference<com.huawei.hmf.tasks.b<?>>> it = this.a.iterator();
            while (it.hasNext()) {
                com.huawei.hmf.tasks.b<?> bVar = it.next().get();
                if (bVar != null) {
                    bVar.a();
                }
            }
            this.a.clear();
        }
    }
}
