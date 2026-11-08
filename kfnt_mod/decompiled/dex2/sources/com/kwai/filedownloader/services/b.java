package com.kwai.filedownloader.services;

import com.kwai.filedownloader.e.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements c.d {
    @Override // com.kwai.filedownloader.e.c.d
    public int a(int i, String str, String str2, boolean z) {
        return a(str, str2, z);
    }

    @Override // com.kwai.filedownloader.e.c.d
    public int a(String str, String str2, boolean z) {
        String str3;
        Object[] objArr;
        if (z) {
            str3 = "%sp%s@dir";
            objArr = new Object[]{str, str2};
        } else {
            str3 = "%sp%s";
            objArr = new Object[]{str, str2};
        }
        return com.kwai.filedownloader.e.f.e(com.kwai.filedownloader.e.f.a(str3, objArr)).hashCode();
    }
}
