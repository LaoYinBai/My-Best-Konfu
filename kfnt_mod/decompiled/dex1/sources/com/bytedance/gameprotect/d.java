package com.bytedance.gameprotect;

import com.bytedance.ttnet.TTNetInit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d {
    static boolean a() {
        try {
            Class.forName(TTNetInit.class.getName());
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
