package com.kwad.sdk.reward;

import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.at;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class m {

    static class a {
        private boolean a;
        private String b;

        public a(String str) {
            JSONObject jSONObject;
            this.b = "-";
            try {
                jSONObject = new JSONObject(str);
            } catch (Throwable th) {
                th.printStackTrace();
                this.a = false;
                this.b = "数据解析失败";
                jSONObject = null;
            }
            a(jSONObject);
        }

        public void a(@Nullable JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optBoolean("isValid");
                this.b = jSONObject.toString();
            }
        }

        public boolean a() {
            return this.a;
        }
    }

    public static void a(final AdTemplate adTemplate, AdInfo adInfo) {
        final String strAq = com.kwad.sdk.core.response.a.a.aq(adInfo);
        com.kwad.sdk.core.d.a.a("ServerCallbackHandle", "handleRewardVerify callbackUrl: " + strAq);
        if (at.a(strAq)) {
            return;
        }
        com.kwad.sdk.utils.i.a(new Runnable() { // from class: com.kwad.sdk.reward.m.1
            private void a() {
                com.kwad.sdk.core.report.d.b(adTemplate, 0, FirebaseAnalytics.Param.SUCCESS);
            }

            private void a(String str) {
                com.kwad.sdk.core.report.d.b(adTemplate, 1, str);
            }

            @Override // java.lang.Runnable
            public void run() {
                String str;
                try {
                    com.kwad.sdk.core.network.c cVarDoGet = KsAdSDKImpl.get().getProxyForHttp().doGet(strAq, null);
                    if (cVarDoGet == null) {
                        str = "Network Error: url invalid";
                    } else if (cVarDoGet.a == 200) {
                        a aVar = new a(cVarDoGet.b);
                        if (aVar.a()) {
                            a();
                            return;
                        }
                        str = aVar.b;
                    } else {
                        str = "Network Error: " + cVarDoGet.b;
                    }
                    a(str);
                } catch (Throwable th) {
                    a("Request Error: " + th.getMessage());
                }
            }
        });
    }
}
