package com.ss.union.game.sdk.push.b;

import android.content.Context;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends a {
    public Uri g;

    public b(Context context, int i, String str, String str2, String str3, long j, Uri uri) {
        super(context, i, str, str2, str3, j);
        this.g = uri;
    }

    public String toString() {
        return "NotificationClickEntity{uri=" + this.g + ", context=" + this.a + ", from=" + this.b + ", title='" + this.c + "', text='" + this.d + "', imgUrl='" + this.e + "', msgId=" + this.f + '}';
    }
}
