package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class r extends a {
    public static final String a = "uopdta";
    private static final String b = "uop";
    private Context c;

    public r(Context context) {
        super(b);
        this.c = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        SharedPreferences sharedPreferences;
        return (!FieldManager.allow(com.umeng.commonsdk.utils.b.A) || (sharedPreferences = PreferenceWrapper.getDefault(this.c)) == null) ? "" : sharedPreferences.getString(a, "");
    }
}
