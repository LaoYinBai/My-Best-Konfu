package com.bytedance.pangle.e.a;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {
    public static final void a(c cVar, int i) throws IOException {
        int iB = cVar.b();
        if (iB == i) {
            return;
        }
        throw new IOException("Expected chunk of type 0x" + Integer.toHexString(i) + ", read 0x" + Integer.toHexString(iB) + ".");
    }
}
