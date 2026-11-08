package com.xiaomi.push;

import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bi {
    private LinkedList<a> a = new LinkedList<>();

    public static class a {
        private static final bi a = new bi();

        /* renamed from: a, reason: collision with other field name */
        public int f153a;

        /* renamed from: a, reason: collision with other field name */
        public Object f154a;

        /* renamed from: a, reason: collision with other field name */
        public String f155a;

        a(int i, Object obj) {
            this.f153a = i;
            this.f154a = obj;
        }
    }

    public static bi a() {
        return a.a;
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m129a() {
        if (this.a.size() > 100) {
            this.a.removeFirst();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized int m130a() {
        return this.a.size();
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized LinkedList<a> m131a() {
        LinkedList<a> linkedList;
        linkedList = this.a;
        this.a = new LinkedList<>();
        return linkedList;
    }

    public synchronized void a(Object obj) {
        this.a.add(new a(0, obj));
        m129a();
    }
}
