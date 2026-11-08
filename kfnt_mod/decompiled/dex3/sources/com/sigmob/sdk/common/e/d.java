package com.sigmob.sdk.common.e;

import com.sigmob.logger.SigmobLog;
import com.sigmob.volley.n;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends k<JSONObject> {
    private final a b;

    public interface a extends n.a {
        void onSuccess(JSONObject jSONObject);
    }

    public d(String str, a aVar, int i) {
        super(str, 0, aVar);
        this.b = aVar;
        a((p) new com.sigmob.volley.c(10000, 2, 0.0f));
        a(false);
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    protected com.sigmob.volley.n<JSONObject> a(com.sigmob.volley.i iVar) {
        try {
            return com.sigmob.volley.n.a(new JSONObject(new String(iVar.b, com.sigmob.volley.toolbox.h.a(iVar.c, "utf-8"))), com.sigmob.volley.toolbox.h.a(iVar));
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return com.sigmob.volley.n.a(new com.sigmob.volley.k(th));
        }
    }

    @Override // com.sigmob.volley.l
    public void a(s sVar) {
        SigmobLog.e("send tracking: " + h() + " fail");
        super.a(sVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    public void a(JSONObject jSONObject) {
        a aVar;
        synchronized (this.a) {
            aVar = this.b;
        }
        SigmobLog.i("send tracking: " + h() + " success");
        if (aVar != null) {
            aVar.onSuccess(jSONObject);
        }
    }
}
