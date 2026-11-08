package com.sigmob.sdk.a;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.AdActivity;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.common.n;
import com.sigmob.sdk.base.common.r;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.sdk.common.utils.i;
import com.sigmob.volley.toolbox.d;
import com.sigmob.volley.toolbox.f;
import com.sigmob.volley.toolbox.g;
import java.io.File;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d extends r {
    File e;
    private g.a f;

    protected d(k.b bVar) {
        super(bVar);
    }

    public File a() {
        return this.e;
    }

    @Override // com.sigmob.sdk.base.common.r
    protected void a(Context context, k.b bVar) {
        this.b = bVar;
        if (this.b != null) {
            this.b.a(this.c);
        }
        if (this.c != null) {
            MaterialMeta material = this.c.getMaterial();
            g gVarC = n.c();
            if (gVarC != null) {
                final String strA = com.sigmob.sdk.common.utils.k.a(material.image_src);
                com.sigmob.volley.toolbox.d dVar = new com.sigmob.volley.toolbox.d();
                dVar.a = material.image_src;
                dVar.b = d.a.PICTURE;
                dVar.c = com.sigmob.sdk.common.utils.d.a() + File.separator + strA + ".temp";
                this.f = gVarC.a(dVar, new f.a() { // from class: com.sigmob.sdk.a.d.1
                    @Override // com.sigmob.volley.toolbox.f.a
                    public void a(com.sigmob.volley.toolbox.d dVar2) {
                        File file;
                        File file2 = new File(dVar2.c);
                        if (file2.exists()) {
                            if (i.a(dVar2.c).equals("gif")) {
                                file = new File(com.sigmob.sdk.common.utils.d.a() + File.separator + strA + ".gif");
                            } else {
                                file = new File(com.sigmob.sdk.common.utils.d.a() + File.separator + strA + ".jpg");
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                            file2.renameTo(file);
                        } else {
                            file = new File(com.sigmob.sdk.common.utils.d.a() + File.separator + strA + ".gif");
                            File file3 = new File(com.sigmob.sdk.common.utils.d.a() + File.separator + strA + ".jpg");
                            if (!file.exists()) {
                                file = file3.exists() ? file3 : null;
                            }
                        }
                        d.this.e = file;
                        if (d.this.b != null) {
                            d.this.b.b(d.this.c);
                        }
                    }

                    @Override // com.sigmob.volley.toolbox.f.a
                    public void a(com.sigmob.volley.toolbox.d dVar2, long j, long j2) {
                    }

                    @Override // com.sigmob.volley.toolbox.f.a
                    public void b(com.sigmob.volley.toolbox.d dVar2) {
                    }

                    @Override // com.sigmob.volley.toolbox.f.a
                    public void c(com.sigmob.volley.toolbox.d dVar2) {
                        if (d.this.b != null) {
                            String message = dVar2.i.getMessage();
                            if (dVar2.i.a != null) {
                                message = String.valueOf(dVar2.i.a.a);
                            }
                            d.this.b.a(d.this.c, message);
                        }
                        SigmobLog.e("onErrorResponse: ", dVar2.i);
                    }
                });
            }
        }
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, BaseAdUnit baseAdUnit, Bundle bundle) {
        if (baseAdUnit == null) {
            baseAdUnit = this.c;
        }
        super.a(context, baseAdUnit, bundle);
        AdActivity.a(context, AdActivity.class, this.a);
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, Map<String, Object> map, BaseAdUnit baseAdUnit) {
        super.a(context, map, baseAdUnit);
    }

    @Override // com.sigmob.sdk.base.common.r
    protected boolean b(BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getMaterial() == null) {
            return false;
        }
        return (TextUtils.isEmpty(baseAdUnit.getCrid()) || TextUtils.isEmpty(baseAdUnit.getMaterial().image_src)) ? false : true;
    }

    @Override // com.sigmob.sdk.base.common.r
    public void c(BaseAdUnit baseAdUnit) {
        super.c(baseAdUnit);
        if (this.f != null) {
            this.f.g();
            this.f = null;
        }
    }
}
