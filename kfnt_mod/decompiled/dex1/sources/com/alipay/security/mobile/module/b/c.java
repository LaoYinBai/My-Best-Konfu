package com.alipay.security.mobile.module.b;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements FileFilter {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f2712a;

    c(b bVar) {
        this.f2712a = bVar;
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
