package com.ss.union.game.sdk.push.b;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends a {
    public String g;

    public c(Context context, int i, String str, String str2, String str3, long j, String str4) {
        super(context, i, str, str2, str3, j);
        this.g = str4;
    }

    public String toString() {
        return "PushArriveEntity{openUrl='" + this.g + "', context=" + this.a + ", from=" + this.b + ", title='" + this.c + "', text='" + this.d + "', imgUrl='" + this.e + "', msgId=" + this.f + '}';
    }
}
