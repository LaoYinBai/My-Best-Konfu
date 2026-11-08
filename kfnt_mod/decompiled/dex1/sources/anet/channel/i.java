package anet.channel;

import anet.channel.util.HttpConstant;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class i implements anetwork.channel.a.c {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f455a;

    i(h hVar) {
        this.f455a = hVar;
    }

    @Override // anetwork.channel.a.c
    public boolean handleCache(String str, Map<String, String> map) {
        return "weex".equals(map.get(HttpConstant.F_REFER));
    }
}
