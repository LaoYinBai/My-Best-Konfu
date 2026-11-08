package com.mbridge.msdk.video.module.c;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.net.h.b;
import com.mbridge.msdk.foundation.same.net.h.d;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.out.MBConfiguration;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VideoViewRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends b {
    public a(Context context) {
        super(context);
    }

    @Override // com.mbridge.msdk.foundation.same.net.h.b
    public final void a(String str, d dVar) throws JSONException {
        super.a(str, dVar);
        dVar.a("platform", "1");
        dVar.a("os_version", Build.VERSION.RELEASE);
        dVar.a(CampaignEx.JSON_KEY_PACKAGE_NAME, o.z(this.a));
        dVar.a("app_version_name", o.u(this.a));
        dVar.a("app_version_code", o.t(this.a) + "");
        dVar.a("orientation", o.s(this.a) + "");
        dVar.a(FileDownloadBroadcastHandler.KEY_MODEL, o.t());
        dVar.a("brand", o.v());
        dVar.a("gaid", "");
        dVar.a("gaid2", o.B());
        dVar.a("mnc", o.e(this.a));
        dVar.a("mcc", o.d(this.a));
        int iC = o.C(this.a);
        dVar.a("network_type", iC + "");
        dVar.a("network_str", o.a(this.a, iC) + "");
        dVar.a("language", o.r(this.a));
        dVar.a("timezone", o.x());
        dVar.a("useragent", o.w());
        dVar.a("sdk_version", MBConfiguration.SDK_VERSION);
        dVar.a("gp_version", o.j(this.a));
        dVar.a("screen_size", o.w(this.a) + "x" + o.x(this.a));
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                if (aVarB.E() == 1) {
                    if (o.b(this.a) != null) {
                        jSONObject.put("imei", o.b(this.a));
                    }
                    if (o.i(this.a) != null) {
                        jSONObject.put("mac", o.i(this.a));
                    }
                }
                if (aVarB.F() == 1 && o.g(this.a) != null) {
                    jSONObject.put("android_id", o.g(this.a));
                }
                if (TextUtils.isEmpty(jSONObject.toString())) {
                    return;
                }
                String strA = n.a(jSONObject.toString());
                if (TextUtils.isEmpty(strA)) {
                    return;
                }
                dVar.a("dvi", strA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
