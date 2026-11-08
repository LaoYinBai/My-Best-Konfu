package com.bun.miitmdid.c.j.b;

import android.database.ContentObserver;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends ContentObserver {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private String f3312a;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private int f3313b;

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private b f3314c;

    @Keep
    public c(b bVar, int i, String str) {
        super(null);
        this.f3314c = bVar;
        this.f3313b = i;
        this.f3312a = str;
    }

    @Override // android.database.ContentObserver
    @Keep
    public native void onChange(boolean z);
}
