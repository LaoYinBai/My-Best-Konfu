package com.bytedance.push.utils;

import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d<E> {

    /* renamed from: a, reason: collision with root package name */
    private int f4993a;

    /* renamed from: b, reason: collision with root package name */
    private LinkedList<E> f4994b = new LinkedList<>();

    public d(int i) {
        this.f4993a = i;
    }

    public void a(E e) {
        if (this.f4994b.size() >= this.f4993a) {
            this.f4994b.poll();
        }
        this.f4994b.offer(e);
    }

    public E a(int i) {
        return this.f4994b.get(i);
    }

    public int a() {
        return this.f4994b.size();
    }
}
