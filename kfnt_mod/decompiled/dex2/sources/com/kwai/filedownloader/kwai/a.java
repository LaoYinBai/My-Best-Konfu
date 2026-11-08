package com.kwai.filedownloader.kwai;

import com.kwai.filedownloader.e.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements c.a {
    @Override // com.kwai.filedownloader.e.c.a
    public int a(int i, String str, String str2, long j) {
        if (j < 1048576) {
            return 1;
        }
        if (j < 5242880) {
            return 2;
        }
        if (j < 52428800) {
            return 3;
        }
        return j < 104857600 ? 4 : 5;
    }
}
