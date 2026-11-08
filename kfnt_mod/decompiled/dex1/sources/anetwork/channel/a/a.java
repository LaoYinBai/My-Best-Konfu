package anetwork.channel.a;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface a {
    void clear();

    C0010a get(String str);

    void put(String str, C0010a c0010a);

    void remove(String str);

    /* renamed from: anetwork.channel.a.a$a, reason: collision with other inner class name */
    public static class C0010a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        public byte[] f621a;

        /* renamed from: b, reason: collision with root package name */
        public String f622b;

        /* renamed from: c, reason: collision with root package name */
        public long f623c;
        public long d;
        public long e;
        public Map<String, List<String>> f = Collections.EMPTY_MAP;

        public boolean a() {
            return System.currentTimeMillis() <= this.e;
        }
    }
}
