package com.apm.insight.runtime;

import androidx.annotation.NonNull;
import com.apm.insight.CrashType;
import com.apm.insight.ICrashCallback;
import com.apm.insight.IOOMCallback;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private final List<ICrashCallback> f3022a = new CopyOnWriteArrayList();

    /* renamed from: b, reason: collision with root package name */
    private final List<ICrashCallback> f3023b = new CopyOnWriteArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final List<ICrashCallback> f3024c = new CopyOnWriteArrayList();
    private final List<ICrashCallback> d = new CopyOnWriteArrayList();
    private final List<IOOMCallback> e = new CopyOnWriteArrayList();

    @NonNull
    public List<IOOMCallback> a() {
        return this.e;
    }

    void a(ICrashCallback iCrashCallback, CrashType crashType) {
        List<ICrashCallback> list;
        switch (crashType) {
            case ALL:
                this.f3022a.add(iCrashCallback);
                this.f3023b.add(iCrashCallback);
                this.f3024c.add(iCrashCallback);
                break;
            case ANR:
                break;
            case JAVA:
                list = this.f3023b;
                list.add(iCrashCallback);
            case LAUNCH:
                list = this.f3022a;
                list.add(iCrashCallback);
            case NATIVE:
                list = this.f3024c;
                list.add(iCrashCallback);
            default:
                return;
        }
        list = this.d;
        list.add(iCrashCallback);
    }

    void a(IOOMCallback iOOMCallback) {
        this.e.add(iOOMCallback);
    }

    @NonNull
    public List<ICrashCallback> b() {
        return this.f3022a;
    }

    void b(ICrashCallback iCrashCallback, CrashType crashType) {
        List<ICrashCallback> list;
        switch (crashType) {
            case ALL:
                this.f3022a.remove(iCrashCallback);
                this.f3023b.remove(iCrashCallback);
                this.f3024c.remove(iCrashCallback);
                break;
            case ANR:
                break;
            case JAVA:
                list = this.f3023b;
                list.remove(iCrashCallback);
            case LAUNCH:
                list = this.f3022a;
                list.remove(iCrashCallback);
            case NATIVE:
                list = this.f3024c;
                list.remove(iCrashCallback);
            default:
                return;
        }
        list = this.d;
        list.remove(iCrashCallback);
    }

    void b(IOOMCallback iOOMCallback) {
        this.e.remove(iOOMCallback);
    }

    @NonNull
    public List<ICrashCallback> c() {
        return this.f3023b;
    }

    @NonNull
    public List<ICrashCallback> d() {
        return this.f3024c;
    }

    @NonNull
    public List<ICrashCallback> e() {
        return this.d;
    }
}
