package com.mbridge.msdk.interstitial.d;

import android.content.Context;
import android.os.Build;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.net.h.d;
import com.mbridge.msdk.foundation.same.net.h.e;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.out.MBConfiguration;
import org.json.JSONException;

/* compiled from: InterstitialLoadVideoRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends com.mbridge.msdk.foundation.same.net.h.a {
    public a(Context context) {
        super(context);
    }

    @Override // com.mbridge.msdk.foundation.same.net.h.a, com.mbridge.msdk.foundation.same.net.h.b
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
        dVar.a("is_clever", com.mbridge.msdk.foundation.same.a.k);
        e.a(dVar, this.a);
        e.a(dVar);
    }
}
