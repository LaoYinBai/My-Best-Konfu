package anetwork.channel.h;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d extends LinkedHashMap<String, String> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f706a;

    d(c cVar) {
        this.f706a = cVar;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
        return size() > 100;
    }
}
