package anetwork.channel.h;

import anet.channel.util.StringUtils;
import com.alipay.sdk.util.f;
import java.util.Collections;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class c implements anetwork.channel.h.a {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f704a;

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        public static c f705a = new c(null);
    }

    /* synthetic */ c(d dVar) {
        this();
    }

    public static c a() {
        return a.f705a;
    }

    private c() {
        this.f704a = Collections.synchronizedMap(new d(this));
    }

    @Override // anetwork.channel.h.a
    public void a(String str, anetwork.channel.i.a aVar) {
        if (StringUtils.isBlank(str)) {
            return;
        }
        StringBuilder sb = new StringBuilder(48);
        sb.append("{\"oneWayTime\" : ");
        sb.append(aVar.m);
        sb.append(", \"totalSize\" : ");
        sb.append(aVar.B);
        sb.append(f.d);
        this.f704a.put(str, sb.toString());
    }
}
