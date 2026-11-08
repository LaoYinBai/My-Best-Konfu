package com.sigmob.sdk.nativead;

import android.content.Context;
import android.os.Bundle;
import com.sigmob.sdk.base.common.AdActivity;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.volley.toolbox.d;
import com.sigmob.volley.toolbox.f;
import java.io.File;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends com.sigmob.sdk.base.common.r {
    private NativeAdBroadcastReceiver e;

    interface a extends k.b, k.c {
        void c();

        void d();

        void e();
    }

    protected c(k.b bVar) {
        super(bVar);
    }

    private static boolean a(MaterialMeta materialMeta) {
        return true;
    }

    @Override // com.sigmob.sdk.base.common.r
    protected void a(Context context, k.b bVar) {
        File adPrivacyTemplateFile = this.c.getAdPrivacyTemplateFile();
        if (adPrivacyTemplateFile == null || adPrivacyTemplateFile.exists()) {
            return;
        }
        com.sigmob.volley.toolbox.d dVar = new com.sigmob.volley.toolbox.d();
        dVar.a = this.c.getadPrivacy().privacy_template_url;
        dVar.c = adPrivacyTemplateFile.getAbsolutePath();
        dVar.b = d.a.OTHER;
        com.sigmob.volley.toolbox.g gVarA = com.sigmob.sdk.base.common.n.a();
        if (gVarA != null) {
            gVarA.a(dVar, new f.a() { // from class: com.sigmob.sdk.nativead.c.1
                @Override // com.sigmob.volley.toolbox.f.a
                public void a(com.sigmob.volley.toolbox.d dVar2) {
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void a(com.sigmob.volley.toolbox.d dVar2, long j, long j2) {
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void b(com.sigmob.volley.toolbox.d dVar2) {
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void c(com.sigmob.volley.toolbox.d dVar2) {
                }
            });
        }
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, BaseAdUnit baseAdUnit, Bundle bundle) {
        super.a(context, baseAdUnit, bundle);
        AdActivity.a(context, AdActivity.class, this.a, bundle, "LandNative");
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, Map<String, Object> map, BaseAdUnit baseAdUnit) {
        super.a(context, map, baseAdUnit);
        if (this.e == null && (this.b instanceof a)) {
            this.e = new NativeAdBroadcastReceiver((a) this.b, this.a);
            this.e.a(this.e, context);
        }
    }

    @Override // com.sigmob.sdk.base.common.r
    protected boolean b(BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getMaterial() != null) {
            return a(baseAdUnit.getMaterial());
        }
        return false;
    }

    @Override // com.sigmob.sdk.base.common.r
    public void c(BaseAdUnit baseAdUnit) {
        super.c(baseAdUnit);
    }
}
