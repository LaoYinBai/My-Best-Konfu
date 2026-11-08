package com.sigmob.sdk.common.e;

import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.models.ModelBuilderCreator;
import com.sigmob.sdk.common.models.ssp.pb.SdkConfigRequest;
import com.sigmob.sdk.common.models.ssp.pb.SdkConfigResponse;
import com.sigmob.volley.n;
import com.sigmob.volley.p;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j extends k<SdkConfigResponse> {
    private final a b;
    private final SdkConfigRequest.Builder c;

    public interface a extends n.a {
        void a(SdkConfigResponse sdkConfigResponse);
    }

    public j(String str, a aVar) {
        super(str, 1, aVar);
        this.b = aVar;
        a((p) new com.sigmob.volley.c(10000, 2, 0.0f));
        a(false);
        this.c = ModelBuilderCreator.createSdkConfigRequest();
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    protected com.sigmob.volley.n<SdkConfigResponse> a(com.sigmob.volley.i iVar) {
        try {
            return com.sigmob.volley.n.a(SdkConfigResponse.ADAPTER.decode(iVar.b), com.sigmob.volley.toolbox.h.a(iVar));
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return com.sigmob.volley.n.a(new com.sigmob.volley.k(th));
        }
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public String a() {
        return "application/octet-stream";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    public void a(SdkConfigResponse sdkConfigResponse) {
        this.b.a(sdkConfigResponse);
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public byte[] b() {
        if (!TextUtils.isEmpty(com.sigmob.sdk.common.a.n().X().getCountry())) {
            this.c.country(com.sigmob.sdk.common.a.n().X().getCountry());
        }
        if (!TextUtils.isEmpty(com.sigmob.sdk.common.a.n().X().getLanguage())) {
            this.c.language(com.sigmob.sdk.common.a.n().X().getLanguage().toUpperCase());
        }
        if (!TextUtils.isEmpty(com.sigmob.sdk.common.a.n().t())) {
            this.c.android_id(com.sigmob.sdk.common.a.n().t());
        }
        if (!TextUtils.isEmpty(com.sigmob.sdk.common.a.n().y())) {
            this.c.imei(com.sigmob.sdk.common.a.n().y());
        }
        this.c.geo(ModelBuilderCreator.createGeo().build());
        this.c.device(ModelBuilderCreator.createDevice().build());
        if (!TextUtils.isEmpty(com.sigmob.sdk.common.a.n().w())) {
            this.c.gaid(com.sigmob.sdk.common.a.n().w());
        }
        SdkConfigRequest sdkConfigRequestBuild = this.c.build();
        SigmobLog.d("send SdkConfig Request: " + sdkConfigRequestBuild.toString());
        return sdkConfigRequestBuild.encode();
    }
}
