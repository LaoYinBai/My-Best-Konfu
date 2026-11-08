package com.sigmob.sdk.mraid;

import android.content.Context;
import android.text.TextUtils;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.common.r;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends r {
    protected d(k.b bVar) {
        super(bVar);
    }

    protected static boolean a(MaterialMeta materialMeta) {
        return materialMeta.creative_type.intValue() == com.sigmob.sdk.base.common.j.CreativeTypeMRAID.a() && (!TextUtils.isEmpty(materialMeta.html_url) || (materialMeta.html_snippet != null && materialMeta.html_snippet.size() > 10));
    }

    public static boolean d(BaseAdUnit baseAdUnit) {
        return (baseAdUnit.getMaterial() == null || TextUtils.isEmpty(baseAdUnit.getCrid())) ? false : true;
    }

    @Override // com.sigmob.sdk.base.common.r
    protected void a(Context context, k.b bVar) {
        this.b = bVar;
        if (this.b != null) {
            this.b.a(this.c);
        }
    }

    @Override // com.sigmob.sdk.base.common.r
    public void a(Context context, Map<String, Object> map, BaseAdUnit baseAdUnit) {
        super.a(context, map, baseAdUnit);
    }

    @Override // com.sigmob.sdk.base.common.r
    protected boolean b(BaseAdUnit baseAdUnit) {
        return d(baseAdUnit);
    }

    @Override // com.sigmob.sdk.base.common.r
    public void c(BaseAdUnit baseAdUnit) {
        super.c(baseAdUnit);
    }
}
