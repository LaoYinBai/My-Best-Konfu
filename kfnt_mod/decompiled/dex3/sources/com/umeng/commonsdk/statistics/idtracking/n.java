package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.config.FieldManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class n extends a {
    private static final String a = "umtt1";
    private Context b;

    public n(Context context) {
        super(a);
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        Class<?> cls;
        try {
            if (!FieldManager.allow(com.umeng.commonsdk.utils.b.D) || (cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent")) == null) {
                return null;
            }
            return (String) cls.getMethod("getUmtt1", Context.class).invoke(cls, this.b);
        } catch (Throwable unused) {
            return null;
        }
    }
}
