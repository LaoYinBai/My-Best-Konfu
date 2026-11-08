package com.bytedance.msdk.core.corelogic;

import java.util.LinkedList;
import java.util.Queue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f4482a;

    /* renamed from: b, reason: collision with root package name */
    private long f4483b;

    /* renamed from: c, reason: collision with root package name */
    private long f4484c;
    private final Queue<Long> d = new LinkedList();

    private a() {
    }

    public static a a() {
        if (f4482a == null) {
            synchronized (a.class) {
                if (f4482a == null) {
                    f4482a = new a();
                }
            }
        }
        return f4482a;
    }

    public void a(long j, long j2) {
        synchronized (a.class) {
            if (this.f4483b != j || this.f4484c != j2) {
                this.f4483b = j;
                this.f4484c = j2;
                this.d.clear();
            }
        }
    }

    public boolean b() {
        synchronized (a.class) {
            if (this.f4483b > 0 && this.f4484c > 0) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (this.d.size() >= this.f4483b) {
                    while (this.d.size() > this.f4483b) {
                        this.d.poll();
                    }
                    if (Math.abs(jCurrentTimeMillis - this.d.peek().longValue()) <= this.f4484c) {
                        return true;
                    }
                    this.d.poll();
                    this.d.offer(Long.valueOf(jCurrentTimeMillis));
                } else {
                    this.d.offer(Long.valueOf(jCurrentTimeMillis));
                }
                return false;
            }
            return false;
        }
    }
}
