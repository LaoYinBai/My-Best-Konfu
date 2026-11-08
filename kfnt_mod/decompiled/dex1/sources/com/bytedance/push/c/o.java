package com.bytedance.push.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface o {
    void a(String str);

    public static class a implements o {
        @Override // com.bytedance.push.c.o
        public void a(String str) {
            if (com.bytedance.push.i.l().a()) {
                com.bytedance.push.i.l().a("load so library by DefaultSoLoader");
            }
            System.loadLibrary(str);
        }
    }
}
