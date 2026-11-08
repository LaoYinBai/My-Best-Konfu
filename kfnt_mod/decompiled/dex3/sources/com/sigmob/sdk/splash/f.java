package com.sigmob.sdk.splash;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.common.n;
import com.sigmob.sdk.base.common.r;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.volley.toolbox.d;
import com.sigmob.volley.toolbox.f;
import com.sigmob.volley.toolbox.g;
import java.io.File;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class f extends r {
    d e;
    private g.a f;
    private SplashAdBroadcastReceiver g;

    interface a extends k.b, k.c {
        void c();

        void d();

        void e();

        void f();
    }

    protected f(k.b bVar) {
        super(bVar);
    }

    public static boolean a(BaseAdUnit baseAdUnit) {
        MaterialMeta material = baseAdUnit.getMaterial();
        if (TextUtils.isEmpty(baseAdUnit.getCrid())) {
            return false;
        }
        return (TextUtils.isEmpty(material.video_url) && TextUtils.isEmpty(material.image_src)) ? false : true;
    }

    @Override // com.sigmob.sdk.base.common.r
    protected void a(Context context, k.b bVar) {
        this.b = bVar;
        if (this.b != null) {
            this.b.a(this.c);
        }
        if (this.c != null) {
            File adPrivacyTemplateFile = this.c.getAdPrivacyTemplateFile();
            if (adPrivacyTemplateFile != null && !adPrivacyTemplateFile.exists()) {
                com.sigmob.volley.toolbox.d dVar = new com.sigmob.volley.toolbox.d();
                dVar.a = this.c.getadPrivacy().privacy_template_url;
                dVar.c = adPrivacyTemplateFile.getAbsolutePath();
                dVar.b = d.a.OTHER;
                com.sigmob.volley.toolbox.g gVarA = n.a();
                if (gVarA != null) {
                    gVarA.a(dVar, new f.a() { // from class: com.sigmob.sdk.splash.f.1
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
            File file = new File(this.c.getSplashFilePath());
            if (file.exists()) {
                file.setLastModified(System.currentTimeMillis());
                if (this.b != null) {
                    this.b.b(this.c);
                    return;
                }
                return;
            }
            com.sigmob.volley.toolbox.g gVarA2 = n.a();
            com.sigmob.volley.toolbox.d dVar2 = new com.sigmob.volley.toolbox.d();
            dVar2.c = this.c.getSplashFilePath();
            dVar2.a = this.c.getSplashURL();
            dVar2.b = d.a.OTHER;
            dVar2.h = false;
            this.f = gVarA2.a(dVar2, new f.a() { // from class: com.sigmob.sdk.splash.f.2
                @Override // com.sigmob.volley.toolbox.f.a
                public void a(com.sigmob.volley.toolbox.d dVar3) {
                    if (f.this.b != null) {
                        f.this.b.b(f.this.c);
                    }
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void a(com.sigmob.volley.toolbox.d dVar3, long j, long j2) {
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void b(com.sigmob.volley.toolbox.d dVar3) {
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void c(com.sigmob.volley.toolbox.d dVar3) {
                    if (f.this.b != null) {
                        String message = dVar3.i.getMessage();
                        if (dVar3.i.a != null) {
                            message = String.valueOf(dVar3.i.a.a);
                        }
                        f.this.b.a(f.this.c, message);
                    }
                    SigmobLog.e("onErrorResponse: ", dVar3.i);
                }
            });
        }
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, BaseAdUnit baseAdUnit, Bundle bundle) {
        if (baseAdUnit == null) {
            baseAdUnit = this.c;
        }
        this.e = (d) baseAdUnit.getAdConfig();
        super.a(context, baseAdUnit, bundle);
        if (this.b instanceof a) {
            this.g = new SplashAdBroadcastReceiver((a) this.b, this.a);
            this.g.a(this.g, context);
        }
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, Map<String, Object> map, BaseAdUnit baseAdUnit) {
        super.a(context, map, baseAdUnit);
    }

    @Override // com.sigmob.sdk.base.common.r
    protected boolean b(BaseAdUnit baseAdUnit) {
        return a(baseAdUnit);
    }

    @Override // com.sigmob.sdk.base.common.r
    public void c(BaseAdUnit baseAdUnit) {
        super.c(baseAdUnit);
        if (this.g != null) {
            this.g.a(this.g);
            this.g = null;
        }
    }
}
