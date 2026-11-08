package com.apm.insight.runtime;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.ICommonParams;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private Context f3026a;

    /* renamed from: b, reason: collision with root package name */
    private ICommonParams f3027b;

    /* renamed from: c, reason: collision with root package name */
    private ICommonParams f3028c;

    public d(@NonNull Context context, @NonNull ICommonParams iCommonParams) {
        this(context, iCommonParams, null);
    }

    public d(@NonNull Context context, @NonNull ICommonParams iCommonParams, d dVar) {
        this.f3026a = context;
        this.f3027b = iCommonParams;
        this.f3028c = dVar == null ? null : dVar.f3027b;
    }

    @Nullable
    public static String a(Map<String, Object> map, String str) {
        Object obj;
        if (map == null || (obj = map.get(str)) == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    public static boolean a(Map<String, Object> map) {
        return map == null || map.isEmpty() || !((map.containsKey("app_version") || map.containsKey("version_name")) && map.containsKey("version_code") && map.containsKey("update_version_code"));
    }

    public Map<String, Object> a() {
        Map<String, Object> mapB = b();
        if (a(mapB, "aid") == null) {
            mapB.put("aid", 4444);
        }
        return mapB;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<java.lang.String, java.lang.Object> b() {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.runtime.d.b():java.util.Map");
    }

    @NonNull
    public ICommonParams c() {
        return this.f3027b;
    }

    public String d() {
        try {
            return this.f3027b.getDeviceId();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String e() {
        try {
            return String.valueOf(this.f3027b.getCommonParams().get("aid"));
        } catch (Throwable unused) {
            return "4444";
        }
    }

    public long f() {
        try {
            return this.f3027b.getUserId();
        } catch (Throwable unused) {
            return 0L;
        }
    }
}
