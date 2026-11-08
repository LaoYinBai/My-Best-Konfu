package com.mbridge.msdk.foundation.same.net.h;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;

/* compiled from: HeaderBiddingRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends b {
    public f(Context context) {
        super(context);
    }

    @Override // com.mbridge.msdk.foundation.same.net.h.b
    public void a(String str, d dVar) {
        try {
            try {
                int iC = w.c();
                String strD = w.d();
                if (dVar != null) {
                    dVar.a("misk_spt", String.valueOf(iC));
                    if (!TextUtils.isEmpty(strD)) {
                        dVar.a("misk_spt_det", strD);
                    }
                }
            } catch (Exception e) {
                s.a("CampaignRequest", e.getMessage());
            }
        } finally {
            super.a(str, dVar);
        }
    }
}
