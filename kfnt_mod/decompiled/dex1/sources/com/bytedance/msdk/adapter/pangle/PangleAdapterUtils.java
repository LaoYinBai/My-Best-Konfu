package com.bytedance.msdk.adapter.pangle;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import com.bytedance.b.a.a.a;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleAdapterUtils {
    public static final double CPM_DEFLAUT_VALUE = 0.0d;

    private static TTAdConstant.RitScenes a(TTAdConstant.GroMoreRitScenes groMoreRitScenes) {
        if (groMoreRitScenes == null) {
            return null;
        }
        switch (groMoreRitScenes) {
            case CUSTOMIZE_SCENES:
                return TTAdConstant.RitScenes.CUSTOMIZE_SCENES;
            case HOME_OPEN_BONUS:
                return TTAdConstant.RitScenes.HOME_OPEN_BONUS;
            case HOME_SVIP_BONUS:
                return TTAdConstant.RitScenes.HOME_SVIP_BONUS;
            case HOME_GET_PROPS:
                return TTAdConstant.RitScenes.HOME_GET_PROPS;
            case HOME_TRY_PROPS:
                return TTAdConstant.RitScenes.HOME_TRY_PROPS;
            case HOME_GET_BONUS:
                return TTAdConstant.RitScenes.HOME_GET_BONUS;
            case HOME_GIFT_BONUS:
                return TTAdConstant.RitScenes.HOME_GIFT_BONUS;
            case GAME_START_BONUS:
                return TTAdConstant.RitScenes.GAME_START_BONUS;
            case GAME_REDUCE_WAITING:
                return TTAdConstant.RitScenes.GAME_REDUCE_WAITING;
            case GAME_MORE_KLLKRTUNITIES:
                return TTAdConstant.RitScenes.GAME_MORE_KLLKRTUNITIES;
            case GAME_FINISH_REWARDS:
                return TTAdConstant.RitScenes.GAME_FINISH_REWARDS;
            case GAME_GIFT_BONUS:
                return TTAdConstant.RitScenes.GAME_GIFT_BONUS;
            default:
                return null;
        }
    }

    private static TTAdConstant.RitScenes a(GMAdConstant.GroMoreRitScenes groMoreRitScenes) {
        if (groMoreRitScenes == null) {
            return null;
        }
        switch (groMoreRitScenes) {
            case CUSTOMIZE_SCENES:
                return TTAdConstant.RitScenes.CUSTOMIZE_SCENES;
            case HOME_OPEN_BONUS:
                return TTAdConstant.RitScenes.HOME_OPEN_BONUS;
            case HOME_SVIP_BONUS:
                return TTAdConstant.RitScenes.HOME_SVIP_BONUS;
            case HOME_GET_PROPS:
                return TTAdConstant.RitScenes.HOME_GET_PROPS;
            case HOME_TRY_PROPS:
                return TTAdConstant.RitScenes.HOME_TRY_PROPS;
            case HOME_GET_BONUS:
                return TTAdConstant.RitScenes.HOME_GET_BONUS;
            case HOME_GIFT_BONUS:
                return TTAdConstant.RitScenes.HOME_GIFT_BONUS;
            case GAME_START_BONUS:
                return TTAdConstant.RitScenes.GAME_START_BONUS;
            case GAME_REDUCE_WAITING:
                return TTAdConstant.RitScenes.GAME_REDUCE_WAITING;
            case GAME_MORE_KLLKRTUNITIES:
                return TTAdConstant.RitScenes.GAME_MORE_KLLKRTUNITIES;
            case GAME_FINISH_REWARDS:
                return TTAdConstant.RitScenes.GAME_FINISH_REWARDS;
            case GAME_GIFT_BONUS:
                return TTAdConstant.RitScenes.GAME_GIFT_BONUS;
            default:
                return null;
        }
    }

    private static Map<String, String> a() {
        return GMMediationAdSdk.getPangleExtraData();
    }

    static JSONArray a(String str) {
        JSONArray jSONArray;
        try {
            jSONArray = TextUtils.isEmpty(str) ? new JSONArray() : new JSONArray(str);
        } catch (Exception e) {
            e.printStackTrace();
            jSONArray = null;
        }
        return jSONArray == null ? new JSONArray() : jSONArray;
    }

    private static void a(AdSlot.Builder builder, String str) {
        TTAdSdk.updatePaid(GMMediationAdSdk.getPanglePaid());
        if (builder != null) {
            builder.setUserData(str);
        }
    }

    public static AdError buildAdError(int i, String str) {
        return new AdError(i, str, i, str);
    }

    public static AdSlot.Builder buildPangleAdSlot(com.bytedance.msdk.api.AdSlot adSlot, String str, String str2, String str3, String str4, boolean z) {
        AdSlot.Builder adCount = new AdSlot.Builder().setCodeId(str).setAdCount(1);
        if (adSlot != null) {
            if (5 == adSlot.getAdType()) {
                adCount.setAdCount(adSlot.getAdCount());
            }
            adCount.setAdloadSeq(adSlot.getAdloadSeq()).setPrimeRit(adSlot.getAdUnitId()).setSupportDeepLink(adSlot.isSupportDeepLink()).setDownloadType(adSlot.getDownloadType());
        }
        if (!TextUtils.isEmpty(str4)) {
            adCount.withBid(str4);
        }
        updateData(adCount, str2, str3, z);
        return adCount;
    }

    public static int dp2px(@NonNull Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static long getAdId(Map<String, Object> map) {
        if (map == null || map.get("ad_id") == null) {
            return 0L;
        }
        return ((Long) map.get("ad_id")).longValue();
    }

    public static long getCreativeId(Map<String, Object> map) {
        if (map == null || map.get("creative_id") == null) {
            return 0L;
        }
        return ((Long) map.get("creative_id")).longValue();
    }

    public static String getCustomRitScenes(Map<TTAdConstant.GroMoreExtraKey, Object> map) {
        if (map == null) {
            return null;
        }
        Object obj = map.get(TTAdConstant.GroMoreExtraKey.CUSTOMIZE_RIT_SCENES);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public static String getPangleData() {
        return GMMediationAdSdk.getPangleData();
    }

    public static String getReqId(Map<String, Object> map) {
        if (map == null || map.get("request_id") == null) {
            return null;
        }
        return (String) map.get("request_id");
    }

    public static TTAdConstant.RitScenes getRitScenes(Map<TTAdConstant.GroMoreExtraKey, Object> map) {
        if (map == null) {
            return null;
        }
        Object obj = map.get(TTAdConstant.GroMoreExtraKey.RIT_SCENES);
        if (obj instanceof TTAdConstant.GroMoreRitScenes) {
            return a((TTAdConstant.GroMoreRitScenes) obj);
        }
        if (obj instanceof GMAdConstant.GroMoreRitScenes) {
            return a((GMAdConstant.GroMoreRitScenes) obj);
        }
        return null;
    }

    public static double getValue(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            return Double.valueOf((String) obj).doubleValue();
        }
        if (obj instanceof Float) {
            return ((Double) obj).doubleValue();
        }
        return 0.0d;
    }

    public static void setPangleData(String str) {
        GMMediationAdSdk.setPangleData(str);
    }

    public static void setPanglePreviewParam(AdSlot.Builder builder) {
        if (builder != null && GMMediationAdSdk.isTestDemo()) {
            try {
                String valueFromPPeInfo = GMMediationAdSdk.getValueFromPPeInfo("tt_pangle_preview_ad_id");
                String valueFromPPeInfo2 = GMMediationAdSdk.getValueFromPPeInfo("tt_pangle_preview_creative_id");
                String valueFromPPeInfo3 = GMMediationAdSdk.getValueFromPPeInfo("tt_pangle_preview_ext");
                if (TextUtils.isEmpty(valueFromPPeInfo) || TextUtils.isEmpty(valueFromPPeInfo2) || TextUtils.isEmpty(valueFromPPeInfo3)) {
                    return;
                }
                builder.setAdId(valueFromPPeInfo).setCreativeId(valueFromPPeInfo2).setExt(valueFromPPeInfo3);
            } catch (Throwable unused) {
            }
        }
    }

    public static void updateData(AdSlot.Builder builder, String str, String str2, boolean z) {
        String string;
        String pangleData = getPangleData();
        Map<String, String> mapA = a();
        try {
            JSONArray jSONArrayA = a(pangleData);
            if (mapA != null && mapA.size() > 0) {
                for (String str3 : mapA.keySet()) {
                    String str4 = mapA.get(str3);
                    if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.putOpt("name", str3);
                        jSONObject.putOpt(a.n, str4);
                        jSONArrayA.put(jSONObject);
                    }
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("name", "mediation_sdk_version");
            jSONObject2.putOpt(a.n, "3.1.0.1");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.putOpt("name", "mediation_req_type");
            jSONObject3.putOpt(a.n, "1");
            JSONObject jSONObject4 = null;
            if (!TextUtils.isEmpty(str)) {
                jSONObject4 = new JSONObject();
                jSONObject4.putOpt("name", "waterfall_abtest");
                jSONObject4.putOpt(a.n, str);
            }
            jSONArrayA.put(jSONObject3);
            jSONArrayA.put(jSONObject2);
            if (jSONObject4 != null) {
                jSONArrayA.put(jSONObject4);
            }
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.putOpt("name", "m_req_id");
                jSONObject5.putOpt(a.n, str2);
                jSONArrayA.put(jSONObject5);
            }
            string = jSONArrayA.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            string = pangleData;
        }
        if (!z) {
            setPanglePreviewParam(builder);
        }
        a(builder, string);
    }
}
