package com.ss.union.game.sdk.vcenter.c.a;

import android.app.Activity;
import android.content.Intent;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.vcenter.b.a.e;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends com.ss.union.game.sdk.vcenter.b.a.a {
    private static final String a = "for_result_code";
    private static final int b = Integer.MIN_VALUE;

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public int a() {
        return 1002;
    }

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public void a(e.a aVar, Intent intent, com.ss.union.game.sdk.vcenter.b.a.c cVar) {
    }

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public e.b a(e.a aVar, Intent intent) {
        Activity topActivity = ActivityUtils.getTopActivity();
        int iA = a(aVar.b());
        if (iA != Integer.MIN_VALUE) {
            topActivity.startActivityForResult(intent, iA);
        } else {
            topActivity.startActivity(intent);
        }
        return e.b.a(aVar);
    }

    private int a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optInt(a, Integer.MIN_VALUE);
        }
        return Integer.MIN_VALUE;
    }
}
