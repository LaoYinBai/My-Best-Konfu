package com.appsflyer.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a {

    /* renamed from: ˋ, reason: contains not printable characters */
    private String f313;

    a() {
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final void m238(String str) {
        this.f313 = str;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    final void m237(Context context) {
        if (this.f313 != null) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f313)).setFlags(268435456));
        }
    }
}
