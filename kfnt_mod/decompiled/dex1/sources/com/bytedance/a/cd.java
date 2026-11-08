package com.bytedance.a;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bytedance.common.utility.NetworkUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cd extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3456a;

    public cd(Context context) {
        super(true, true);
        this.f3456a = context;
    }

    @Override // com.bytedance.a.af
    @SuppressLint({"MissingPermission"})
    public boolean a(JSONObject jSONObject) {
        be.a(jSONObject, "access", NetworkUtils.getNetworkAccessType(this.f3456a));
        return true;
    }
}
