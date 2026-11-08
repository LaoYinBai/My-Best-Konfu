package com.sigmob.sdk.base.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sigmob.sdk.base.common.d;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class aa extends com.sigmob.sdk.mraid.d implements d.b {
    protected aa(k.b bVar) {
        super(bVar);
    }

    private static boolean b(MaterialMeta materialMeta) {
        return (materialMeta.creative_type.intValue() != j.CreativeTypeVideo_Tar.a() || TextUtils.isEmpty(materialMeta.endcard_md5) || TextUtils.isEmpty(materialMeta.endcard_url) || TextUtils.isEmpty(materialMeta.video_url)) ? false : true;
    }

    private static boolean c(MaterialMeta materialMeta) {
        return (materialMeta.creative_type.intValue() == j.CreativeTypeVideo_Html_Snippet.a() || materialMeta.creative_type.intValue() == j.CreativeTypeVideo_transparent_html.a()) && materialMeta.html_snippet != null && materialMeta.html_snippet.size() > 10 && !TextUtils.isEmpty(materialMeta.video_url);
    }

    private static boolean d(MaterialMeta materialMeta) {
        return (materialMeta.creative_type.intValue() != j.CreativeTypeVideo_EndCardURL.a() || TextUtils.isEmpty(materialMeta.html_url) || TextUtils.isEmpty(materialMeta.video_url)) ? false : true;
    }

    @Override // com.sigmob.sdk.mraid.d, com.sigmob.sdk.base.common.r
    protected void a(Context context, k.b bVar) {
        super.a(context, bVar);
        d.f().a(this.c, this);
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, BaseAdUnit baseAdUnit, Bundle bundle) {
        d.a(baseAdUnit);
        super.a(context, baseAdUnit, bundle);
    }

    @Override // com.sigmob.sdk.base.common.d.b
    public void a(BaseAdUnit baseAdUnit) {
        if (this.b != null) {
            this.b.b(baseAdUnit);
        }
    }

    @Override // com.sigmob.sdk.base.common.d.b
    public void a(BaseAdUnit baseAdUnit, SigmobError sigmobError, String str) {
        if (this.b != null) {
            this.b.a(baseAdUnit, str);
        }
    }

    @Override // com.sigmob.sdk.mraid.d, com.sigmob.sdk.base.common.r
    public boolean b(BaseAdUnit baseAdUnit) {
        boolean zB = super.b(baseAdUnit);
        MaterialMeta material = baseAdUnit.getMaterial();
        return zB && (baseAdUnit.getCreativeType() == j.CreativeTypeMRAID.a() ? a(material) : b(material) || c(material) || d(material));
    }

    @Override // com.sigmob.sdk.mraid.d, com.sigmob.sdk.base.common.r
    public void c(BaseAdUnit baseAdUnit) {
        d.c(baseAdUnit);
        super.c(baseAdUnit);
    }
}
