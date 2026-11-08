package com.meizu.cloud.pushsdk.base;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j {
    /* JADX WARN: Multi-variable type inference failed */
    public static String a(String str) {
        com.meizu.cloud.pushsdk.base.a.d dVarA = com.meizu.cloud.pushsdk.base.a.a.a("android.os.SystemProperties").a("get", String.class).a(str);
        if (dVarA.a) {
            return (String) dVarA.b;
        }
        return null;
    }
}
