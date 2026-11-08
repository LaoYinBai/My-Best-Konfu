package com.bytedance.push.settings.storage;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h implements com.bytedance.push.settings.i {

    /* renamed from: a, reason: collision with root package name */
    private static final ConcurrentHashMap<String, i> f4966a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private static final ConcurrentHashMap<String, i> f4967b = new ConcurrentHashMap<>();

    @Override // com.bytedance.push.settings.i
    public i a(Context context, boolean z, String str) {
        i dVar;
        if (z) {
            i iVar = f4967b.get(str);
            if (iVar != null) {
                return iVar;
            }
            Log.d("PushProvider", "SharedPreferenceStorageFactory#create");
            if (PushProvider.a(context)) {
                dVar = new c(context, str);
            } else {
                dVar = new d(context, str);
            }
            f4967b.put(str, dVar);
            return dVar;
        }
        i iVar2 = f4966a.get(str);
        if (iVar2 != null) {
            return iVar2;
        }
        g gVar = new g(context, str);
        f4966a.put(str, gVar);
        return gVar;
    }
}
