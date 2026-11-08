package com.kwai.filedownloader.message;

import com.kwai.filedownloader.message.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    private final List<a> a = new ArrayList();
    private final e.b b;

    public class a {
        private final List<Integer> b = new ArrayList();
        private final Executor c;

        public a(int i) {
            this.c = com.kwai.filedownloader.e.b.a(1, "Flow-" + i);
        }

        public void a(int i) {
            this.b.add(Integer.valueOf(i));
        }

        public void a(final MessageSnapshot messageSnapshot) {
            this.c.execute(new Runnable() { // from class: com.kwai.filedownloader.message.g.a.1
                @Override // java.lang.Runnable
                public void run() {
                    g.this.b.a(messageSnapshot);
                    try {
                        a.this.b.remove(Integer.valueOf(messageSnapshot.m()));
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    g(int i, e.b bVar) {
        this.b = bVar;
        for (int i2 = 0; i2 < i; i2++) {
            this.a.add(new a(i2));
        }
    }

    public void a(MessageSnapshot messageSnapshot) {
        a aVar = null;
        try {
            synchronized (this.a) {
                int iM = messageSnapshot.m();
                Iterator<a> it = this.a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    a next = it.next();
                    if (next.b.contains(Integer.valueOf(iM))) {
                        aVar = next;
                        break;
                    }
                }
                if (aVar == null) {
                    int size = 0;
                    Iterator<a> it2 = this.a.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        a next2 = it2.next();
                        if (next2.b.size() <= 0) {
                            aVar = next2;
                            break;
                        } else if (size == 0 || next2.b.size() < size) {
                            size = next2.b.size();
                            aVar = next2;
                        }
                    }
                }
                aVar.a(iM);
            }
        } finally {
            aVar.a(messageSnapshot);
        }
    }
}
