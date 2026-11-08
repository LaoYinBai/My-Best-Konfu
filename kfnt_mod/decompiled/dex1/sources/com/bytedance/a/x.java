package com.bytedance.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class x implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ac f3552a;

    public x(ac acVar) {
        this.f3552a = acVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (int i = 0; i < this.f3552a.f3332a.size(); i++) {
            try {
                for (String str : new String[]{"openudid", "clientudid", "serial_number", "sim_serial_number", "udid", com.bytedance.b.a.c.h.x}) {
                    try {
                        this.f3552a.a(this.f3552a.f3332a.get(i), str);
                    } catch (Exception e) {
                        de.a("", e);
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
    }
}
