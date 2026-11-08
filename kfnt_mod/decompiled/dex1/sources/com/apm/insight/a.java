package com.apm.insight;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private Map<CrashType, List<AttachUserData>> f2753a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private Map<CrashType, List<AttachUserData>> f2754b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private Map<String, String> f2755c = new HashMap();
    private ICrashFilter d = null;

    private void c(CrashType crashType, AttachUserData attachUserData) {
        List<AttachUserData> arrayList;
        if (this.f2753a.get(crashType) == null) {
            arrayList = new ArrayList<>();
            this.f2753a.put(crashType, arrayList);
        } else {
            arrayList = this.f2753a.get(crashType);
        }
        arrayList.add(attachUserData);
    }

    private void d(CrashType crashType, AttachUserData attachUserData) {
        List<AttachUserData> arrayList;
        if (this.f2754b.get(crashType) == null) {
            arrayList = new ArrayList<>();
            this.f2754b.put(crashType, arrayList);
        } else {
            arrayList = this.f2754b.get(crashType);
        }
        arrayList.add(attachUserData);
    }

    private void e(CrashType crashType, AttachUserData attachUserData) {
        List<AttachUserData> list = this.f2753a.get(crashType);
        if (list != null) {
            list.remove(attachUserData);
        }
    }

    private void f(CrashType crashType, AttachUserData attachUserData) {
        List<AttachUserData> list = this.f2754b.get(crashType);
        if (list != null) {
            list.remove(attachUserData);
        }
    }

    @Nullable
    public List<AttachUserData> a(CrashType crashType) {
        return this.f2753a.get(crashType);
    }

    public Map<String, String> a() {
        return this.f2755c;
    }

    void a(AttachUserData attachUserData, CrashType crashType) {
        if (crashType == CrashType.ALL) {
            c(CrashType.LAUNCH, attachUserData);
            c(CrashType.JAVA, attachUserData);
            c(CrashType.CUSTOM_JAVA, attachUserData);
            c(CrashType.NATIVE, attachUserData);
            c(CrashType.ANR, attachUserData);
            crashType = CrashType.DART;
        }
        c(crashType, attachUserData);
    }

    void a(CrashType crashType, AttachUserData attachUserData) {
        if (crashType == CrashType.ALL) {
            e(CrashType.LAUNCH, attachUserData);
            e(CrashType.JAVA, attachUserData);
            e(CrashType.CUSTOM_JAVA, attachUserData);
            e(CrashType.NATIVE, attachUserData);
            e(CrashType.ANR, attachUserData);
            crashType = CrashType.DART;
        }
        e(crashType, attachUserData);
    }

    void a(ICrashFilter iCrashFilter) {
        this.d = iCrashFilter;
    }

    void a(Map<? extends String, ? extends String> map) {
        this.f2755c.putAll(map);
    }

    @Nullable
    public ICrashFilter b() {
        return this.d;
    }

    @Nullable
    public List<AttachUserData> b(CrashType crashType) {
        return this.f2754b.get(crashType);
    }

    void b(AttachUserData attachUserData, CrashType crashType) {
        if (crashType == CrashType.ALL) {
            d(CrashType.LAUNCH, attachUserData);
            d(CrashType.JAVA, attachUserData);
            d(CrashType.CUSTOM_JAVA, attachUserData);
            d(CrashType.NATIVE, attachUserData);
            d(CrashType.ANR, attachUserData);
            crashType = CrashType.DART;
        }
        d(crashType, attachUserData);
    }

    void b(CrashType crashType, AttachUserData attachUserData) {
        if (crashType == CrashType.ALL) {
            f(CrashType.LAUNCH, attachUserData);
            f(CrashType.JAVA, attachUserData);
            f(CrashType.CUSTOM_JAVA, attachUserData);
            f(CrashType.NATIVE, attachUserData);
            f(CrashType.ANR, attachUserData);
            crashType = CrashType.DART;
        }
        f(crashType, attachUserData);
    }
}
