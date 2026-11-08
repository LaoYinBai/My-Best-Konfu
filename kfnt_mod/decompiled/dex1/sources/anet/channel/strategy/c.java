package anet.channel.strategy;

import anet.channel.util.HttpConstant;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<String, String> f539a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private boolean f540b = true;

    /* JADX INFO: Access modifiers changed from: private */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static c f541a = new c();

        private a() {
        }
    }

    public void a(boolean z) {
        this.f540b = z;
    }

    public String a(String str) {
        if (!this.f540b) {
            return null;
        }
        String str2 = this.f539a.get(str);
        if (str2 != null) {
            return str2;
        }
        this.f539a.put(str, "https");
        return "https";
    }

    public void b(String str) {
        this.f539a.put(str, HttpConstant.HTTP);
    }
}
