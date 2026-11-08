package com.sigmob.sdk.base.a;

import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.d;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.e.f;
import com.sigmob.sdk.common.e.m;
import com.sigmob.sdk.common.models.SigmobError;
import java.net.URL;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {

    public interface a {
        void a(int i, String str, String str2, LoadAdRequest loadAdRequest);

        void a(List<BaseAdUnit> list, LoadAdRequest loadAdRequest);
    }

    public static void a(String str, LoadAdRequest loadAdRequest, a aVar) {
        if (f.a() == null) {
            if (aVar != null) {
                aVar.a(SigmobError.ERROR_SIGMOB_NETWORK.getErrorCode(), "request queue is null", null, loadAdRequest);
                return;
            }
            return;
        }
        try {
            if (m.b(new URL(str).getHost())) {
                f.a().a(new com.sigmob.sdk.base.a.a(str, d.f().l(), loadAdRequest, aVar));
            } else if (aVar != null) {
                aVar.a(SigmobError.ERROR_SIGMOB_NETWORK.getErrorCode(), "network is disconnection", null, loadAdRequest);
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            if (aVar != null) {
                aVar.a(SigmobError.ERROR_SIGMOB_NETWORK.getErrorCode(), th.getMessage(), null, loadAdRequest);
            }
        }
    }
}
