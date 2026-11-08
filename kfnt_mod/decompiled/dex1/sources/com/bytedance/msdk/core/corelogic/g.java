package com.bytedance.msdk.core.corelogic;

import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import com.bytedance.msdk.adapter.util.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Integer, Boolean> f4498a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map<Integer, AtomicInteger> f4499b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final AtomicInteger f4500c = new AtomicInteger();
    private final AtomicInteger d = new AtomicInteger();
    private final AtomicBoolean e = new AtomicBoolean(false);
    private final AtomicInteger f = new AtomicInteger();
    private final AtomicBoolean g = new AtomicBoolean(false);
    private final List<Integer> h = new ArrayList();
    private final List<String> i = new CopyOnWriteArrayList();
    private final AtomicBoolean j = new AtomicBoolean(false);

    void a(int i) {
        this.d.set(i);
    }

    boolean a() {
        return this.g.get();
    }

    int b() {
        return this.d.get();
    }

    void b(int i) {
        this.f.set(i);
    }

    void c() {
        if (this.d.get() != 0) {
            this.d.decrementAndGet();
        } else {
            Logger.e("TTMediationSDK", "decrementBidingWaterfall--当前正在请求的serverBidding竞价waterfall数据减一 ---不用再减了不然就变成-1了");
        }
    }

    boolean d() {
        return this.e.get();
    }

    int e() {
        int i;
        synchronized (this) {
            i = this.f.get();
        }
        return i;
    }

    public boolean e(int i) {
        Map<Integer, Boolean> map = this.f4498a;
        return (map == null || map.get(Integer.valueOf(i)) == null || !this.f4498a.get(Integer.valueOf(i)).booleanValue()) ? false : true;
    }

    void f() {
        this.f.set(0);
        this.f4500c.set(0);
        this.f4498a.clear();
        this.f4499b.clear();
        this.d.set(0);
        this.e.set(false);
        this.g.set(false);
        this.i.clear();
        this.h.clear();
        this.j.set(false);
    }

    int d(int i) {
        AtomicInteger atomicInteger = this.f4499b.get(Integer.valueOf(i));
        if (atomicInteger != null) {
            return atomicInteger.get();
        }
        return -1;
    }

    int g() {
        int i;
        synchronized (this) {
            i = this.f4500c.get();
        }
        return i;
    }

    public boolean h() {
        return g() == 0 && e() == 0;
    }

    boolean i() {
        return this.j.get();
    }

    void b(boolean z) {
        this.j.set(z);
    }

    void f(int i) {
        this.h.add(Integer.valueOf(i));
    }

    void a(boolean z) {
        this.e.set(z);
    }

    void b(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        synchronized (this) {
            AtomicInteger atomicInteger = new AtomicInteger();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                Boolean bool = this.f4498a.get(it.next());
                if (bool == null || !bool.booleanValue()) {
                    atomicInteger.incrementAndGet();
                }
            }
            this.f4500c.set(atomicInteger.get());
        }
    }

    void c(List<i> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        synchronized (this) {
            int size = list.size();
            for (String str : this.i) {
                Iterator<i> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        i next = it.next();
                        if (!TextUtils.isEmpty(str) && next != null && str.equals(next.d())) {
                            size--;
                            break;
                        }
                    }
                }
            }
            this.f.set(size);
        }
    }

    void a(String str) {
        synchronized (this) {
            if (!TextUtils.isEmpty(str)) {
                this.i.add(str);
            }
            if (this.f.get() != 0) {
                this.f.decrementAndGet();
            } else {
                Logger.e("TTMediationSDK", "decrementTotalWaterFall--waterfall数量减少一个 ---不用再减了不然就变成-1了");
            }
        }
    }

    void a(int i, int i2) {
        this.f4499b.put(Integer.valueOf(i), new AtomicInteger(i2));
    }

    void c(int i) {
        AtomicInteger atomicInteger = this.f4499b.get(Integer.valueOf(i));
        if (atomicInteger == null || atomicInteger.get() == 0) {
            return;
        }
        atomicInteger.decrementAndGet();
    }

    void a(List<Integer> list) {
        if (list != null) {
            this.f4500c.set(list.size());
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                this.f4498a.put(it.next(), false);
            }
        }
    }

    void a(int i, boolean z) {
        synchronized (this) {
            Boolean bool = this.f4498a.get(Integer.valueOf(i));
            if (bool != null && !bool.booleanValue()) {
                this.f4498a.put(Integer.valueOf(i), Boolean.valueOf(z));
                this.f4500c.decrementAndGet();
            }
        }
    }
}
