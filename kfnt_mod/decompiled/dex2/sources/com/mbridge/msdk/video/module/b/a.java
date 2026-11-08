package com.mbridge.msdk.video.module.b;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.net.g.d;
import com.mbridge.msdk.foundation.same.report.d.b;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.s;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: VideoViewReport.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static HashMap<String, ArrayList<String>> a = new HashMap<>();

    public static void a(String str) {
        a.remove(str);
    }

    public static void a(String str, String str2) {
        try {
            if (com.mbridge.msdk.foundation.controller.a.f().j() == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return;
            }
            new com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j()).c(0, d.c().a, com.mbridge.msdk.foundation.same.report.d.a(str, com.mbridge.msdk.foundation.controller.a.f().j(), str2), new b() { // from class: com.mbridge.msdk.video.module.b.a.1
                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void a(String str3) {
                    s.d("VideoViewReport", str3);
                }

                @Override // com.mbridge.msdk.foundation.same.report.d.b
                public final void b(String str3) {
                    s.d("VideoViewReport", str3);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            s.d("VideoViewReport", e.getMessage());
        }
    }

    public static void a(Context context, CampaignEx campaignEx) {
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().n() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().n(), false, false);
    }

    public static void b(Context context, CampaignEx campaignEx) {
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().h() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().h(), false, false);
    }

    public static void c(Context context, CampaignEx campaignEx) {
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().i() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().i(), false, false);
    }

    public static void d(Context context, CampaignEx campaignEx) {
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().j() == null) {
            return;
        }
        String campaignUnitId = campaignEx.getCampaignUnitId();
        ArrayList<String> arrayList = a.get(campaignUnitId);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            a.put(campaignUnitId, arrayList);
        }
        if (arrayList.contains(campaignEx.getId())) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().j(), false, false);
        arrayList.add(campaignEx.getId());
    }

    public static void a(Context context, CampaignEx campaignEx, int i, int i2) {
        try {
            String[] strArrO = campaignEx.getNativeVideoTracking().o();
            if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || strArrO == null) {
                return;
            }
            String[] strArr = new String[strArrO.length];
            for (int i3 = 0; i3 < strArrO.length; i3++) {
                String str = strArrO[i3];
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("endscreen_type", i);
                String string = jSONObject.toString();
                if (!TextUtils.isEmpty(string)) {
                    string = n.a(string);
                }
                if (!TextUtils.isEmpty(string)) {
                    str = str + "&value=" + URLEncoder.encode(string);
                }
                strArr[i3] = campaignEx.getSpareOfferFlag() == 1 ? str + "&to=1&cbt=" + campaignEx.getCbt() + "&tmorl=" + i2 : str + "&to=0&cbt=" + campaignEx.getCbt() + "&tmorl=" + i2;
            }
            com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), strArr, false, true);
        } catch (Throwable unused) {
            s.d("", "reportEndcardshowData error");
        }
    }

    public static void e(Context context, CampaignEx campaignEx) {
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().p() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().p(), false, false);
    }

    public static void f(Context context, CampaignEx campaignEx) {
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().k() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().k(), false, false);
    }

    public static void a(Context context, CampaignEx campaignEx, int i, int i2, int i3) {
        int i4;
        if (i2 == 0 || context == null || campaignEx == null) {
            return;
        }
        try {
            List<Map<Integer, String>> listG = campaignEx.getNativeVideoTracking().g();
            int i5 = ((i + 1) * 100) / i2;
            if (listG != null) {
                for (int i6 = 0; i6 < listG.size(); i6 = i4 + 1) {
                    Map<Integer, String> map = listG.get(i6);
                    if (map == null || map.size() <= 0) {
                        i4 = i6;
                    } else {
                        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
                        i4 = i6;
                        while (it.hasNext()) {
                            Map.Entry<Integer, String> next = it.next();
                            int iIntValue = next.getKey().intValue();
                            String value = next.getValue();
                            String str = campaignEx.getSpareOfferFlag() == 1 ? value + "&to=1&cbt=" + campaignEx.getCbt() + "&tmorl=" + i3 : value + "&to=0&cbt=" + campaignEx.getCbt() + "&tmorl=" + i3;
                            if (iIntValue <= i5 && !TextUtils.isEmpty(str)) {
                                com.mbridge.msdk.click.b.a(context, campaignEx, campaignEx.getCampaignUnitId(), new String[]{str}, false, true);
                                it.remove();
                                listG.remove(i4);
                                i4--;
                            }
                        }
                    }
                }
            }
        } catch (Throwable unused) {
            s.d("", "reportPlayPercentageData error");
        }
    }

    public static void a(CampaignEx campaignEx, Map<Integer, String> map, String str, int i) {
        if (campaignEx == null || map == null) {
            return;
        }
        try {
            if (map.size() > 0) {
                Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, String> next = it.next();
                    Integer key = next.getKey();
                    String value = next.getValue();
                    if (i == key.intValue() && !TextUtils.isEmpty(value)) {
                        com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, value, false, false);
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(CampaignEx campaignEx, String str) {
        if (campaignEx != null) {
            try {
                if (campaignEx.getAdUrlList() == null || campaignEx.getAdUrlList().size() <= 0) {
                    return;
                }
                for (String str2 : campaignEx.getAdUrlList()) {
                    if (!TextUtils.isEmpty(str2)) {
                        com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, str, str2, false, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(CampaignEx campaignEx, com.mbridge.msdk.videocommon.b.d dVar, String str, String str2, String str3) {
        if (campaignEx == null || dVar == null) {
            return;
        }
        try {
            com.mbridge.msdk.video.module.c.a aVar = new com.mbridge.msdk.video.module.c.a(com.mbridge.msdk.foundation.controller.a.f().j());
            com.mbridge.msdk.foundation.same.net.h.d dVar2 = new com.mbridge.msdk.foundation.same.net.h.d();
            dVar2.a("user_id", n.a(str2));
            dVar2.a("cb_type", "1");
            dVar2.a(CampaignEx.JSON_KEY_REWARD_NAME, dVar.a());
            dVar2.a(CampaignEx.JSON_KEY_REWARD_AMOUNT, dVar.b() + "");
            dVar2.a(MBridgeConstans.PROPERTIES_UNIT_ID, str);
            dVar2.a("click_id", campaignEx.getRequestIdNotice());
            if (!TextUtils.isEmpty(str3)) {
                dVar2.a(PushConstants.EXTRA, str3);
            }
            aVar.a("", dVar2);
            String string = campaignEx.getHost() + "/addReward?";
            String str4 = "";
            String strTrim = dVar2.a().trim();
            if (!TextUtils.isEmpty(strTrim)) {
                if (!string.endsWith("?") && !string.endsWith(ContainerUtils.FIELD_DELIMITER)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(string.contains("?") ? ContainerUtils.FIELD_DELIMITER : "?");
                    string = sb.toString();
                }
                str4 = string + strTrim;
            }
            String str5 = str4;
            s.d("VideoViewReport", "rewardUrl:" + str5);
            com.mbridge.msdk.click.b.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx, campaignEx.getCampaignUnitId(), str5, false, false);
        } catch (Throwable th) {
            s.b("VideoViewReport", th.getMessage(), th);
        }
    }
}
