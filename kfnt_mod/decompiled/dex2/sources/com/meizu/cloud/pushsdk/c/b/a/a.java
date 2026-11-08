package com.meizu.cloud.pushsdk.c.b.a;

import com.meizu.cloud.pushsdk.b.c.i;
import com.meizu.cloud.pushsdk.c.b.c;
import com.meizu.cloud.pushsdk.c.b.g;
import com.meizu.cloud.pushsdk.c.d.d;
import com.meizu.cloud.pushsdk.c.f.e;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends c {
    private final String h;
    private d i;
    private int j;

    public a(c.a aVar) {
        super(aVar);
        this.h = a.class.getSimpleName();
        this.i = new com.meizu.cloud.pushsdk.c.d.a(this.a, this.e);
        if (this.i.a()) {
            return;
        }
        this.i = new com.meizu.cloud.pushsdk.c.d.c(this.e);
        com.meizu.cloud.pushsdk.c.f.c.a(this.h, "init memory store", new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.LinkedList<com.meizu.cloud.pushsdk.c.b.g> a(java.util.LinkedList<com.meizu.cloud.pushsdk.c.b.e> r11) {
        /*
            r10 = this;
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.util.LinkedList r1 = new java.util.LinkedList
            r1.<init>()
            java.util.Iterator r2 = r11.iterator()
        Le:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L2a
            java.lang.Object r3 = r2.next()
            com.meizu.cloud.pushsdk.c.b.e r3 = (com.meizu.cloud.pushsdk.c.b.e) r3
            com.meizu.cloud.pushsdk.b.c.i r3 = r3.a()
            java.util.concurrent.Callable r3 = r10.b(r3)
            java.util.concurrent.Future r3 = com.meizu.cloud.pushsdk.c.b.a.b.a(r3)
            r1.add(r3)
            goto Le
        L2a:
            java.lang.String r2 = r10.h
            java.lang.String r3 = "Request Futures: %s"
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            int r6 = r1.size()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7 = 0
            r5[r7] = r6
            com.meizu.cloud.pushsdk.c.f.c.b(r2, r3, r5)
            r2 = 0
        L40:
            int r3 = r1.size()
            if (r2 >= r3) goto Lc2
            r3 = -1
            java.lang.Object r5 = r1.get(r2)     // Catch: java.util.concurrent.TimeoutException -> L5d java.util.concurrent.ExecutionException -> L6b java.lang.InterruptedException -> L79
            java.util.concurrent.Future r5 = (java.util.concurrent.Future) r5     // Catch: java.util.concurrent.TimeoutException -> L5d java.util.concurrent.ExecutionException -> L6b java.lang.InterruptedException -> L79
            r8 = 5
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.util.concurrent.TimeoutException -> L5d java.util.concurrent.ExecutionException -> L6b java.lang.InterruptedException -> L79
            java.lang.Object r5 = r5.get(r8, r6)     // Catch: java.util.concurrent.TimeoutException -> L5d java.util.concurrent.ExecutionException -> L6b java.lang.InterruptedException -> L79
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch: java.util.concurrent.TimeoutException -> L5d java.util.concurrent.ExecutionException -> L6b java.lang.InterruptedException -> L79
            int r5 = r5.intValue()     // Catch: java.util.concurrent.TimeoutException -> L5d java.util.concurrent.ExecutionException -> L6b java.lang.InterruptedException -> L79
            r3 = r5
            goto L89
        L5d:
            r5 = move-exception
            java.lang.String r6 = r10.h
            java.lang.String r8 = "Request Future had a timeout: %s"
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r5 = r5.getMessage()
            r9[r7] = r5
            goto L86
        L6b:
            r5 = move-exception
            java.lang.String r6 = r10.h
            java.lang.String r8 = "Request Future failed: %s"
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r5 = r5.getMessage()
            r9[r7] = r5
            goto L86
        L79:
            r5 = move-exception
            java.lang.String r6 = r10.h
            java.lang.String r8 = "Request Future was interrupted: %s"
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r5 = r5.getMessage()
            r9[r7] = r5
        L86:
            com.meizu.cloud.pushsdk.c.f.c.a(r6, r8, r9)
        L89:
            java.lang.Object r5 = r11.get(r2)
            com.meizu.cloud.pushsdk.c.b.e r5 = (com.meizu.cloud.pushsdk.c.b.e) r5
            boolean r5 = r5.c()
            if (r5 == 0) goto La8
            com.meizu.cloud.pushsdk.c.b.g r3 = new com.meizu.cloud.pushsdk.c.b.g
            java.lang.Object r5 = r11.get(r2)
            com.meizu.cloud.pushsdk.c.b.e r5 = (com.meizu.cloud.pushsdk.c.b.e) r5
            java.util.LinkedList r5 = r5.b()
            r3.<init>(r4, r5)
            r0.add(r3)
            goto Lbe
        La8:
            com.meizu.cloud.pushsdk.c.b.g r5 = new com.meizu.cloud.pushsdk.c.b.g
            boolean r3 = r10.a(r3)
            java.lang.Object r6 = r11.get(r2)
            com.meizu.cloud.pushsdk.c.b.e r6 = (com.meizu.cloud.pushsdk.c.b.e) r6
            java.util.LinkedList r6 = r6.b()
            r5.<init>(r3, r6)
            r0.add(r5)
        Lbe:
            int r2 = r2 + 1
            goto L40
        Lc2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.c.b.a.a.a(java.util.LinkedList):java.util.LinkedList");
    }

    private Callable<Boolean> a(final Long l) {
        return new Callable<Boolean>() { // from class: com.meizu.cloud.pushsdk.c.b.a.a.3
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean call() {
                return Boolean.valueOf(a.this.i.a(l.longValue()));
            }
        };
    }

    private LinkedList<Boolean> b(LinkedList<Long> linkedList) {
        String str;
        String str2;
        Object[] objArr;
        boolean zBooleanValue;
        LinkedList<Boolean> linkedList2 = new LinkedList<>();
        LinkedList linkedList3 = new LinkedList();
        Iterator<Long> it = linkedList.iterator();
        while (it.hasNext()) {
            linkedList3.add(b.a(a(it.next())));
        }
        com.meizu.cloud.pushsdk.c.f.c.b(this.h, "Removal Futures: %s", Integer.valueOf(linkedList3.size()));
        for (int i = 0; i < linkedList3.size(); i++) {
            try {
                zBooleanValue = ((Boolean) ((Future) linkedList3.get(i)).get(5L, TimeUnit.SECONDS)).booleanValue();
            } catch (InterruptedException e) {
                str = this.h;
                str2 = "Removal Future was interrupted: %s";
                objArr = new Object[]{e.getMessage()};
                com.meizu.cloud.pushsdk.c.f.c.a(str, str2, objArr);
                zBooleanValue = false;
                linkedList2.add(Boolean.valueOf(zBooleanValue));
            } catch (ExecutionException e2) {
                str = this.h;
                str2 = "Removal Future failed: %s";
                objArr = new Object[]{e2.getMessage()};
                com.meizu.cloud.pushsdk.c.f.c.a(str, str2, objArr);
                zBooleanValue = false;
                linkedList2.add(Boolean.valueOf(zBooleanValue));
            } catch (TimeoutException e3) {
                str = this.h;
                str2 = "Removal Future had a timeout: %s";
                objArr = new Object[]{e3.getMessage()};
                com.meizu.cloud.pushsdk.c.f.c.a(str, str2, objArr);
                zBooleanValue = false;
                linkedList2.add(Boolean.valueOf(zBooleanValue));
            }
            linkedList2.add(Boolean.valueOf(zBooleanValue));
        }
        return linkedList2;
    }

    private Callable<Integer> b(final i iVar) {
        return new Callable<Integer>() { // from class: com.meizu.cloud.pushsdk.c.b.a.a.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                return Integer.valueOf(a.this.a(iVar));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() throws InterruptedException {
        String str;
        String str2;
        if (e.a(this.a)) {
            if (this.i.c() > 0) {
                this.j = 0;
                LinkedList<g> linkedListA = a(a(this.i.d()));
                com.meizu.cloud.pushsdk.c.f.c.c(this.h, "Processing emitter results.", new Object[0]);
                LinkedList<Long> linkedList = new LinkedList<>();
                Iterator<g> it = linkedListA.iterator();
                int size = 0;
                int size2 = 0;
                while (it.hasNext()) {
                    g next = it.next();
                    if (next.a()) {
                        linkedList.addAll(next.b());
                        size += next.b().size();
                    } else {
                        size2 += next.b().size();
                        com.meizu.cloud.pushsdk.c.f.c.a(this.h, "Request sending failed but we will retry later.", new Object[0]);
                    }
                }
                b(linkedList);
                com.meizu.cloud.pushsdk.c.f.c.b(this.h, "Success Count: %s", Integer.valueOf(size));
                com.meizu.cloud.pushsdk.c.f.c.b(this.h, "Failure Count: %s", Integer.valueOf(size2));
                if (this.b != null) {
                    if (size2 != 0) {
                        this.b.a(size, size2);
                    } else {
                        this.b.a(size);
                    }
                }
                if (size2 > 0 && size == 0) {
                    if (e.a(this.a)) {
                        com.meizu.cloud.pushsdk.c.f.c.a(this.h, "Ensure collector path is valid: %s", b());
                    }
                    str = this.h;
                    str2 = "Emitter loop stopping: failures.";
                }
            } else {
                if (this.j >= this.d) {
                    com.meizu.cloud.pushsdk.c.f.c.a(this.h, "Emitter loop stopping: empty limit reached.", new Object[0]);
                    this.g.compareAndSet(true, false);
                    if (this.b != null) {
                        this.b.a(true);
                        return;
                    }
                    return;
                }
                this.j++;
                com.meizu.cloud.pushsdk.c.f.c.a(this.h, "Emitter database empty: " + this.j, new Object[0]);
                try {
                    this.f.sleep(this.c);
                } catch (InterruptedException e) {
                    com.meizu.cloud.pushsdk.c.f.c.a(this.h, "Emitter thread sleep interrupted: " + e.toString(), new Object[0]);
                }
            }
            c();
            return;
        }
        str = this.h;
        str2 = "Emitter loop stopping: emitter offline.";
        com.meizu.cloud.pushsdk.c.f.c.a(str, str2, new Object[0]);
        this.g.compareAndSet(true, false);
    }

    @Override // com.meizu.cloud.pushsdk.c.b.c
    public void a() {
        b.a(new Runnable() { // from class: com.meizu.cloud.pushsdk.c.b.a.a.1
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                if (a.this.g.compareAndSet(false, true)) {
                    a.this.c();
                }
            }
        });
    }

    @Override // com.meizu.cloud.pushsdk.c.b.c
    public void a(com.meizu.cloud.pushsdk.c.a.a aVar, boolean z) throws InterruptedException {
        this.i.a(aVar);
        com.meizu.cloud.pushsdk.c.f.c.a(this.h, "isRunning " + this.g + " attemptEmit " + z, new Object[0]);
        if (!z) {
            try {
                this.f.sleep(1L);
            } catch (InterruptedException e) {
                com.meizu.cloud.pushsdk.c.f.c.a(this.h, "Emitter add thread sleep interrupted: " + e.toString(), new Object[0]);
            }
        }
        if (this.g.compareAndSet(false, true)) {
            c();
        }
    }
}
