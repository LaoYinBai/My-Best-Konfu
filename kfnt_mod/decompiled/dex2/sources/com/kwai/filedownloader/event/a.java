package com.kwai.filedownloader.event;

import com.kwai.filedownloader.e.d;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private final Executor a = com.kwai.filedownloader.e.b.a(10, "EventPool");
    private final HashMap<String, LinkedList<c>> b = new HashMap<>();

    private void a(LinkedList<c> linkedList, b bVar) {
        for (Object obj : linkedList.toArray()) {
            if (obj != null && ((c) obj).a(bVar)) {
                break;
            }
        }
        if (bVar.a != null) {
            bVar.a.run();
        }
    }

    public boolean a(b bVar) {
        if (d.a) {
            d.e(this, "publish %s", bVar.b());
        }
        if (bVar == null) {
            throw new IllegalArgumentException("event must not be null!");
        }
        String strB = bVar.b();
        LinkedList<c> linkedList = this.b.get(strB);
        if (linkedList == null) {
            synchronized (strB.intern()) {
                linkedList = this.b.get(strB);
                if (linkedList == null) {
                    if (d.a) {
                        d.c(this, "No listener for this event %s", strB);
                    }
                    return false;
                }
            }
        }
        a(linkedList, bVar);
        return true;
    }

    public boolean a(String str, c cVar) {
        boolean zAdd;
        if (d.a) {
            d.e(this, "setListener %s", str);
        }
        if (cVar == null) {
            throw new IllegalArgumentException("listener must not be null!");
        }
        LinkedList<c> linkedList = this.b.get(str);
        if (linkedList == null) {
            synchronized (str.intern()) {
                linkedList = this.b.get(str);
                if (linkedList == null) {
                    HashMap<String, LinkedList<c>> map = this.b;
                    LinkedList<c> linkedList2 = new LinkedList<>();
                    map.put(str, linkedList2);
                    linkedList = linkedList2;
                }
            }
        }
        synchronized (str.intern()) {
            zAdd = linkedList.add(cVar);
        }
        return zAdd;
    }

    public void b(final b bVar) {
        if (d.a) {
            d.e(this, "asyncPublishInNewThread %s", bVar.b());
        }
        if (bVar == null) {
            throw new IllegalArgumentException("event must not be null!");
        }
        this.a.execute(new Runnable() { // from class: com.kwai.filedownloader.event.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.a(bVar);
                } catch (Exception unused) {
                }
            }
        });
    }
}
