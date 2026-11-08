package com.huawei.hms.aaid.a;

import android.content.Context;
import com.huawei.hms.aaid.entity.AAIDResult;
import java.util.concurrent.Callable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements Callable<AAIDResult> {
    private Context a;

    public a(Context context) {
        this.a = context;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public AAIDResult call() throws Exception {
        if (this.a == null) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_ARGUMENTS_INVALID);
        }
        String strB = com.huawei.hms.aaid.b.a.b(this.a);
        AAIDResult aAIDResult = new AAIDResult();
        aAIDResult.setId(strB);
        return aAIDResult;
    }
}
