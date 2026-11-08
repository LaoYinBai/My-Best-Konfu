package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dy extends dx {
    public dy(Context context, int i) {
        super(context, i);
    }

    @Override // com.xiaomi.push.dx
    /* renamed from: a */
    public hl mo205a() {
        return hl.Storage;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "23";
    }

    @Override // com.xiaomi.push.dx
    public String b() {
        return "ram:" + i.m433a() + Constants.ACCEPT_TIME_SEPARATOR_SP + "rom:" + i.m437b() + "|ramOriginal:" + i.m439c() + Constants.ACCEPT_TIME_SEPARATOR_SP + "romOriginal:" + i.d();
    }
}
