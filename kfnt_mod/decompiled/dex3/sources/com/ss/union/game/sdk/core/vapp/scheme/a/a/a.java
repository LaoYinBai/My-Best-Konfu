package com.ss.union.game.sdk.core.vapp.scheme.a.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.union.game.sdk.core.vapp.VBindAccountActivity;
import com.ss.union.game.sdk.core.vapp.scheme.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends com.ss.union.game.sdk.core.vapp.scheme.a.a {
    private static final String l = "v_bind_token";
    private static final String m = "v_nick_name";
    private static final String n = "v_return";
    private static final String o = "vplatform";
    private static final String p = "/bind";

    public a(Intent intent) {
        super(intent);
    }

    @Override // com.ss.union.game.sdk.core.vapp.scheme.a.b
    public boolean b(Context context) {
        return TextUtils.equals(this.c, o) && TextUtils.equals(this.d, p);
    }

    @Override // com.ss.union.game.sdk.core.vapp.scheme.a.b
    public void c(Context context) {
        String queryParameter = this.a.getQueryParameter(l);
        String queryParameter2 = this.a.getQueryParameter(m);
        String queryParameter3 = this.a.getQueryParameter(n);
        if (TextUtils.isEmpty(queryParameter) || TextUtils.isEmpty(queryParameter2)) {
            return;
        }
        if (TextUtils.isEmpty(queryParameter3)) {
            queryParameter3 = "false";
        }
        try {
            VBindAccountActivity.a(context, queryParameter, queryParameter2, Boolean.parseBoolean(queryParameter3), true);
        } catch (Exception e) {
            b.b("start VBindAccountActivity fail: " + e.getMessage());
        }
    }
}
