package com.bytedance.msdk.core.corelogic;

import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.b0;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.p;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.TToast;
import com.bytedance.msdk.adapter.listener.ITTAdatperCallback;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static Comparator<TTBaseAd> f4495a = null;

    /* renamed from: b, reason: collision with root package name */
    private static String f4496b = "KEY_LOAD_SEQ";

    /* renamed from: c, reason: collision with root package name */
    private static String f4497c = "KEY_LOAD_SEQ_TIME";

    static class a implements Comparator<TTBaseAd> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(TTBaseAd tTBaseAd, TTBaseAd tTBaseAd2) {
            if (tTBaseAd.getCpm() > tTBaseAd2.getCpm()) {
                return -1;
            }
            if (tTBaseAd.getCpm() < tTBaseAd2.getCpm()) {
                return 1;
            }
            return (tTBaseAd.getCpm() == tTBaseAd2.getCpm() && tTBaseAd.getAdNetworkPlatformId() == 1) ? -1 : 0;
        }
    }

    public static Comparator<TTBaseAd> a() {
        Comparator<TTBaseAd> comparator = f4495a;
        return comparator != null ? comparator : b();
    }

    public static void a(List<TTBaseAd> list, Comparator<TTBaseAd> comparator) {
        if (comparator == null) {
            b0.a(list);
        } else {
            b0.a(list, comparator);
        }
    }

    public static Comparator<TTBaseAd> b() {
        a aVar = new a();
        f4495a = aVar;
        return aVar;
    }

    public static boolean b(String str) {
        return true;
    }

    public static String c() {
        return UUID.randomUUID().toString();
    }

    public static void a(String str, TTBaseAd tTBaseAd) {
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().D() && tTBaseAd != null && b(str, tTBaseAd)) {
            TToast.show(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), String.format("test_suceess ,test_adn:%1$s,slot_id : %2$s", com.bytedance.msdk.base.a.a(tTBaseAd.getAdNetworkPlatformId()), tTBaseAd.getAdNetworkSlotId()));
        }
    }

    static void a(AdError adError, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().D() || adError == null || dVar == null) {
            return;
        }
        if (20001 == adError.code) {
            TToast.show(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), String.format("error_adn:%1$s no ads，please check ad network", dVar.c()));
        } else {
            TToast.show(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), String.format("test error_adn:%1$s  error_slot_id: %2$s  error_code:%3$d  error_message:%4$s", dVar.c(), dVar.d(), Integer.valueOf(adError.thirdSdkErrorCode), adError.thirdSdkErrorMessage));
        }
    }

    public static String a(String str) {
        return !TextUtils.isEmpty(str) ? str.substring(0, 1).toUpperCase().concat(str.substring(1).toLowerCase()) : str;
    }

    static void a(String str, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar, TTBaseAd tTBaseAd) {
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().D() && dVar != null && b(str, tTBaseAd)) {
            TToast.show(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), String.format("fill_suceess ,test_adn:%1$s,slot_id : %2$s", dVar.c(), dVar.d()));
        }
    }

    private static boolean b(String str, TTBaseAd tTBaseAd) {
        if (tTBaseAd == null) {
            return false;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVarB = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().b(str);
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().e(str) && cVarB != null && cVarB.e() != null && cVarB.e().size() > 0 && cVarB.t() != null && cVarB.t().size() > 0;
    }

    public static String a(String str, String str2, String str3) {
        return "com.bytedance.msdk.adapter." + com.bytedance.msdk.base.a.a(str2) + "." + String.format(str, str2, str3);
    }

    public static TTAbsAdLoaderAdapter a(Map<String, TTAbsAdLoaderAdapter> map, i iVar, String str) {
        String strA = p.a(str + "_" + iVar.d() + Math.abs(iVar.m()) + Math.abs(iVar.w()));
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = map != null ? map.get(strA) : null;
        if (tTAbsAdLoaderAdapter == null) {
            try {
                tTAbsAdLoaderAdapter = (TTAbsAdLoaderAdapter) Class.forName(str).newInstance();
                if (map != null) {
                    map.put(strA, tTAbsAdLoaderAdapter);
                }
            } catch (Exception e) {
                Logger.e("TTMediationSDK", "创建广告网络adapter加载器失败：class=" + str + ",异常信息：" + e.toString());
                return null;
            }
        }
        return tTAbsAdLoaderAdapter;
    }

    public static Map<String, Object> a(i iVar, AdSlot adSlot, Map<String, Object> map, ITTAdatperCallback iTTAdatperCallback, GMNetworkRequestInfo gMNetworkRequestInfo, int i, int i2, boolean z) {
        HashMap map2 = new HashMap();
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a(iVar.e());
        if (aVarA != null) {
            map2.put("tt_ad_network_config_appid", aVarA.a());
            map2.put("tt_ad_network_config_appKey", aVarA.b());
        } else {
            String strE = iVar.e();
            String strA = gMNetworkRequestInfo != null ? com.bytedance.msdk.base.a.a(gMNetworkRequestInfo.getAdNetworkFlatFromId()) : null;
            if (gMNetworkRequestInfo != null && iVar.m() == -4 && !TextUtils.isEmpty(strE) && strE.equals(strA)) {
                map2.put("tt_ad_network_config_appid", gMNetworkRequestInfo.getAppId());
                map2.put("tt_ad_network_config_appKey", gMNetworkRequestInfo.getAppKey());
            }
        }
        map2.put("tt_ad_network_callback", iTTAdatperCallback);
        map2.put("tt_ad_origin_type", Integer.valueOf(iVar.p()));
        if (adSlot != null) {
            TTRequestExtraParams reuestParam = adSlot.getReuestParam();
            if (reuestParam != null) {
                map2.putAll(reuestParam.getExtraObject());
            }
            int imgAcceptedWidth = adSlot.getImgAcceptedWidth();
            int imgAcceptedHeight = adSlot.getImgAcceptedHeight();
            if (imgAcceptedWidth < 0) {
                imgAcceptedWidth = 0;
            }
            if (imgAcceptedHeight < 0) {
                imgAcceptedHeight = 0;
            }
            map2.put(TTRequestExtraParams.PARAM_AD_HEIGHT, Integer.valueOf(imgAcceptedHeight));
            map2.put(TTRequestExtraParams.PARAM_AD_WIDTH, Integer.valueOf(imgAcceptedWidth));
            map2.put(TTRequestExtraParams.PARAM_AD_TYPE, Integer.valueOf(adSlot.getAdType()));
        }
        map2.put("key_mediation_rit_req_type", Integer.valueOf(i));
        map2.put("key_mediation_rit_req_type_src", Integer.valueOf(i2));
        map2.put("key_is_from_developer_req", Boolean.valueOf(z));
        if (map != null && map.size() > 0) {
            map2.putAll(map);
        }
        return map2;
    }

    static i a(String str, String str2, int i, int i2) {
        i iVar = new i();
        iVar.b(str2);
        iVar.a(str);
        iVar.b(0);
        iVar.d(OnekeyLoginConstants.CU_RESULT_SUCCESS);
        iVar.e("1");
        iVar.f(i);
        iVar.i(i2);
        iVar.h(3);
        iVar.f("%1$s%2$sAdapter");
        return iVar;
    }

    private static boolean a(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return ((calendar.get(1) == calendar2.get(1)) && calendar.get(2) == calendar2.get(2)) && calendar.get(5) == calendar2.get(5);
    }

    static int d() {
        long jA = u.a((String) null, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).a(f4497c, 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean zA = a(new Date(jA), new Date(jCurrentTimeMillis));
        u.a((String) null, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).b(f4497c, jCurrentTimeMillis);
        int iA = u.a((String) null, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).a(f4496b, 0);
        if (!zA) {
            iA = 0;
        }
        int i = iA + 1;
        u.a((String) null, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).b(f4496b, i);
        return i;
    }
}
