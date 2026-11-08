package com.huawei.hms.support.log.a;

import android.content.Context;
import android.util.Log;
import com.huawei.hms.support.log.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements b {
    private b a;

    @Override // com.huawei.hms.support.log.b
    public void a(Context context, String str) {
        if (this.a != null) {
            this.a.a(context, str);
        }
    }

    @Override // com.huawei.hms.support.log.b
    public void a(String str, int i, String str2, String str3) {
        Log.println(i, "HMSSDK_" + str2, str3);
        if (this.a != null) {
            this.a.a(str, i, str2, str3);
        }
    }
}
