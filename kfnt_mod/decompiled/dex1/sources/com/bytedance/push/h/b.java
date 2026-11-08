package com.bytedance.push.h;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bytedance.push.settings.LocalFrequencySettings;
import com.bytedance.push.settings.SettingsManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b {

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, com.bytedance.push.e.c> f4830a;

    static synchronized com.bytedance.push.e.c a(Context context, int i) {
        a(context);
        if (f4830a == null) {
            return null;
        }
        return f4830a.get(Integer.valueOf(i));
    }

    @SuppressLint({"CI_UseSparseArrays"})
    private static void a(Context context) {
        if (f4830a != null) {
            return;
        }
        List<com.bytedance.push.e.c> listJ = ((LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class)).j();
        if (listJ == null) {
            f4830a = new HashMap();
            return;
        }
        HashMap map = new HashMap();
        for (com.bytedance.push.e.c cVar : listJ) {
            if (cVar != null) {
                map.put(Integer.valueOf(cVar.f4795c), cVar);
            }
        }
        f4830a = map;
    }

    static synchronized void a(Context context, com.bytedance.push.e.c cVar) {
        a(context);
        if (cVar != null && f4830a != null) {
            f4830a.put(Integer.valueOf(cVar.f4795c), cVar);
            ((LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class)).a(new ArrayList(f4830a.values()));
        }
    }
}
