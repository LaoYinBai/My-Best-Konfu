package com.xiaomi.push;

import java.io.File;
import java.io.FileFilter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class z implements FileFilter {
    z() {
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
