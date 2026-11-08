package anet.channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d {

    /* renamed from: a, reason: collision with root package name */
    private final Map<SessionRequest, List<Session>> f414a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final ReentrantReadWriteLock f415b = new ReentrantReadWriteLock();

    /* renamed from: c, reason: collision with root package name */
    private final ReentrantReadWriteLock.ReadLock f416c = this.f415b.readLock();
    private final ReentrantReadWriteLock.WriteLock d = this.f415b.writeLock();

    d() {
    }

    public void a(SessionRequest sessionRequest, Session session) {
        if (sessionRequest == null || sessionRequest.a() == null || session == null) {
            return;
        }
        this.d.lock();
        try {
            List<Session> arrayList = this.f414a.get(sessionRequest);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.f414a.put(sessionRequest, arrayList);
            }
            if (arrayList.indexOf(session) != -1) {
                return;
            }
            arrayList.add(session);
            Collections.sort(arrayList);
        } finally {
            this.d.unlock();
        }
    }

    public void b(SessionRequest sessionRequest, Session session) {
        this.d.lock();
        try {
            List<Session> list = this.f414a.get(sessionRequest);
            if (list == null) {
                return;
            }
            list.remove(session);
            if (list.size() == 0) {
                this.f414a.remove(sessionRequest);
            }
        } finally {
            this.d.unlock();
        }
    }

    public List<Session> a(SessionRequest sessionRequest) {
        this.f416c.lock();
        try {
            List<Session> list = this.f414a.get(sessionRequest);
            if (list != null) {
                return new ArrayList(list);
            }
            return Collections.EMPTY_LIST;
        } finally {
            this.f416c.unlock();
        }
    }

    public Session a(SessionRequest sessionRequest, int i) {
        this.f416c.lock();
        try {
            List<Session> list = this.f414a.get(sessionRequest);
            Session session = null;
            if (list != null && !list.isEmpty()) {
                for (Session session2 : list) {
                    if (session2 != null && session2.isAvailable() && (i == anet.channel.entity.c.f437c || session2.j.getType() == i)) {
                        session = session2;
                        break;
                    }
                }
                return session;
            }
            return null;
        } finally {
            this.f416c.unlock();
        }
    }

    public List<SessionRequest> a() {
        List<SessionRequest> list = Collections.EMPTY_LIST;
        this.f416c.lock();
        try {
            return this.f414a.isEmpty() ? list : new ArrayList(this.f414a.keySet());
        } finally {
            this.f416c.unlock();
        }
    }

    public boolean c(SessionRequest sessionRequest, Session session) {
        this.f416c.lock();
        try {
            List<Session> list = this.f414a.get(sessionRequest);
            boolean z = false;
            if (list != null) {
                if (list.indexOf(session) != -1) {
                    z = true;
                }
            }
            return z;
        } finally {
            this.f416c.unlock();
        }
    }
}
