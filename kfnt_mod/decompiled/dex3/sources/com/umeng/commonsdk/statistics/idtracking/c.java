package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.config.FieldManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends a {
    private static final String a = "idfa";
    private Context b;

    public c(Context context) {
        super(a);
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        String strA = FieldManager.allow(com.umeng.commonsdk.utils.b.w) ? com.umeng.commonsdk.statistics.common.a.a(this.b) : null;
        return strA == null ? "" : strA;
    }
}
