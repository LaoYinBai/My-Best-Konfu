package com.meizu.cloud.pushsdk.c.c;

import com.meizu.cloud.pushsdk.c.a.c;
import com.meizu.cloud.pushsdk.c.f.d;
import com.meizu.cloud.pushsdk.c.f.e;
import com.meizu.cloud.pushsdk.notification.model.NotificationStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private final List<com.meizu.cloud.pushsdk.c.a.b> a;
    private final long b;
    private final String c;

    /* renamed from: com.meizu.cloud.pushsdk.c.c.a$a, reason: collision with other inner class name */
    public static abstract class AbstractC0155a<T extends AbstractC0155a<T>> {
        private List<com.meizu.cloud.pushsdk.c.a.b> a = new LinkedList();
        private long b = System.currentTimeMillis();
        private String c = e.b();

        protected abstract T a();

        public T a(long j) {
            this.b = j;
            return (T) a();
        }
    }

    protected a(AbstractC0155a<?> abstractC0155a) {
        d.a(((AbstractC0155a) abstractC0155a).a);
        d.a(((AbstractC0155a) abstractC0155a).c);
        d.a(!((AbstractC0155a) abstractC0155a).c.isEmpty(), "eventId cannot be empty");
        this.a = ((AbstractC0155a) abstractC0155a).a;
        this.b = ((AbstractC0155a) abstractC0155a).b;
        this.c = ((AbstractC0155a) abstractC0155a).c;
    }

    protected c a(c cVar) {
        cVar.a(NotificationStyle.EXPANDABLE_IMAGE_URL, c());
        cVar.a("ts", Long.toString(b()));
        return cVar;
    }

    public List<com.meizu.cloud.pushsdk.c.a.b> a() {
        return new ArrayList(this.a);
    }

    public long b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
