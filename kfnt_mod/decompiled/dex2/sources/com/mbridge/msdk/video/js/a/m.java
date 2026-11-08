package com.mbridge.msdk.video.js.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.video.module.MBridgeContainerView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSRewardVideoV1.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class m extends f {
    private Activity a;
    private MBridgeContainerView b;

    public m(Activity activity, MBridgeContainerView mBridgeContainerView) {
        this.a = activity;
        this.b = mBridgeContainerView;
    }

    @Override // com.mbridge.msdk.video.js.a.f, com.mbridge.msdk.video.js.h
    public final String a() {
        if (this.b == null) {
            super.a();
        } else {
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.b.getCampaign());
                String unitID = this.b.getUnitID();
                com.mbridge.msdk.videocommon.d.c cVarA = com.mbridge.msdk.videocommon.d.b.a() == null ? null : com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), unitID);
                JSONObject jSONObject = new JSONObject();
                if (cVarA != null) {
                    jSONObject = cVarA.G();
                }
                s.a("JSRewardVideoV1", "getEndScreenInfo success campaign = " + this.b.getCampaign());
                return a(arrayList, unitID, "MAL_15.7.57,3.0.1", jSONObject);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return super.a();
    }

    @Override // com.mbridge.msdk.video.js.a.f, com.mbridge.msdk.video.js.g
    public final void toggleCloseBtn(int i) {
        super.toggleCloseBtn(i);
        if (this.b != null) {
            this.b.toggleCloseBtn(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.f, com.mbridge.msdk.video.js.h
    public final void a(String str) {
        super.a(str);
        try {
            if (this.a == null || TextUtils.isEmpty(str) || !str.equals(CampaignEx.JSON_NATIVE_VIDEO_CLICK) || this.b == null) {
                return;
            }
            this.b.triggerCloseBtn(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.f, com.mbridge.msdk.video.js.g
    public final void notifyCloseBtn(int i) {
        super.notifyCloseBtn(i);
        if (this.b != null) {
            this.b.notifyCloseBtn(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.f, com.mbridge.msdk.video.js.h
    public final void c(String str) {
        super.c(str);
        try {
            if (this.a == null || TextUtils.isEmpty(str) || this.b == null) {
                return;
            }
            this.b.handlerPlayableException(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.f, com.mbridge.msdk.video.js.h
    public final void b(String str) {
        super.b(str);
        try {
            if (this.a != null && !TextUtils.isEmpty(str)) {
                if (str.equals("landscape")) {
                    this.a.setRequestedOrientation(0);
                } else if (str.equals("portrait")) {
                    this.a.setRequestedOrientation(1);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private String a(List<CampaignEx> list, String str, String str2, JSONObject jSONObject) throws JSONException {
        if (list == null) {
            return null;
        }
        try {
            if (list.size() <= 0) {
                return null;
            }
            com.mbridge.msdk.foundation.tools.c cVar = new com.mbridge.msdk.foundation.tools.c(com.mbridge.msdk.foundation.controller.a.f().j());
            Object camplistToJson = CampaignEx.parseCamplistToJson(list);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("campaignList", camplistToJson);
            jSONObject2.put("device", cVar.a());
            jSONObject2.put(MBridgeConstans.PROPERTIES_UNIT_ID, str);
            jSONObject2.put("sdk_info", str2);
            jSONObject2.put("unitSetting", jSONObject);
            if (com.mbridge.msdk.b.b.a() != null) {
                String strC = com.mbridge.msdk.b.b.a().c(com.mbridge.msdk.foundation.controller.a.f().k());
                if (!TextUtils.isEmpty(strC)) {
                    JSONObject jSONObject3 = new JSONObject(strC);
                    try {
                        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                        String string = y.b(contextJ, "MBridge_ConfirmTitle" + str, "").toString();
                        String string2 = y.b(contextJ, "MBridge_ConfirmContent" + str, "").toString();
                        String string3 = y.b(contextJ, "MBridge_CancelText" + str, "").toString();
                        String string4 = y.b(contextJ, "MBridge_ConfirmText" + str, "").toString();
                        if (!TextUtils.isEmpty(string)) {
                            jSONObject3.put("confirm_title", string);
                        }
                        if (!TextUtils.isEmpty(string2)) {
                            jSONObject3.put("confirm_description", string2);
                        }
                        if (!TextUtils.isEmpty(string3)) {
                            jSONObject3.put("confirm_t", string3);
                        }
                        if (!TextUtils.isEmpty(string4)) {
                            jSONObject3.put("confirm_c_play", string4);
                        }
                        if (!TextUtils.isEmpty(string4)) {
                            jSONObject3.put("confirm_c_rv", string4);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String strD = com.mbridge.msdk.b.b.a().d(str);
                    if (!TextUtils.isEmpty(strD)) {
                        jSONObject3.put("ivreward", new JSONObject(strD));
                    }
                    jSONObject2.put("appSetting", jSONObject3);
                }
            }
            return jSONObject2.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
