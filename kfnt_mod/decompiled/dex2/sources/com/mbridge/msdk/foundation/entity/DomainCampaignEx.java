package com.mbridge.msdk.foundation.entity;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.click.CommonJumpLoader;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.out.ApkDisplayInfo;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.system.NoProGuard;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DomainCampaignEx extends Campaign implements NoProGuard, Serializable {
    public static final String JSON_KEY_ADV_ID = "adv_id";
    public static final String JSON_KEY_AD_TRACKING_APK_END = "apk_download_end";
    public static final String JSON_KEY_AD_TRACKING_APK_INSTALL = "apk_install";
    public static final String JSON_KEY_AD_TRACKING_APK_START = "apk_download_start";
    public static final String JSON_KEY_AKDLUI = "akdlui";
    public static final String JSON_KEY_APK_ALT = "apk_alt";
    public static final String JSON_KEY_APK_INFO = "apk_info";
    public static final String JSON_KEY_ATAT_TYPE = "atat_type";
    public static final String JSON_KEY_DISPLAY_APK_ALT = "disableApkAlt";
    public static final String JSON_KEY_NTBARPASBL = "ntbarpasbl";
    public static final String JSON_KEY_NTBARPT = "ntbarpt";
    public static final String JSON_KEY_PRE_CLICK = "ttc";
    public static final String JSON_KEY_PRE_CLICK_ERROR_INTERVAL = "ttc_pe";
    public static final String JSON_KEY_PRE_CLICK_INTERVAL = "ttc_ct";
    public static final String JSON_KEY_PRE_CLICK_OTHER_INTERVAL = "ttc_po";
    public static final String JSON_KEY_TTC_CT2 = "ttc_ct2";
    public static final String JSON_KEY_TTC_TYPE = "ttc_type";
    public static final String KEY_BIND_ID = "bind_id";
    public static final String KEY_GH_ID = "gh_id";
    public static final String KEY_GH_PATH = "gh_path";
    public static final String LOOPBACK = "loopback";
    public static final String LOOPBACK_DOMAIN = "domain";
    public static final String LOOPBACK_KEY = "key";
    public static final String LOOPBACK_VALUE = "value";
    public static final String ROVER_KEY_IS_POST = "isPost";
    public static final String ROVER_KEY_MARK = "mark";
    public static final String TAG = "DomainCampaignEx";
    public static final int TTC_CT2_DEFAULT_VALUE = 1800;
    public static final int TTC_CT_DEFAULT_VALUE = 604800;
    private static final long serialVersionUID = 1;
    private String advId;
    private String bindId;
    private String ghId;
    private String ghPath;
    private CommonJumpLoader.JumpLoaderResult jumpResult;
    private Map<String, String> loopbackMap;
    private String loopbackString;
    private int preClickInterval;
    private int roverIsPost;
    private String roverMark;
    private int ttc_ct2;
    private int ttc_type;
    private int apk_alt = 0;
    private int displayApkAlt = 0;
    private int ntbarpt = 0;
    private int ntbarpasbl = 0;
    private int atatType = 0;
    private String akdlui = "";
    private boolean preClick = false;

    public int getDisplayApkAlt() {
        return this.displayApkAlt;
    }

    public void setDisplayApkAlt(int i) {
        this.displayApkAlt = i;
    }

    public void setApkAlt(int i) {
        this.apk_alt = i;
    }

    public int getApkAlt() {
        return this.apk_alt;
    }

    public int getNtbarpt() {
        return this.ntbarpt;
    }

    public void setNtbarpt(int i) {
        this.ntbarpt = i;
    }

    public int getNtbarpasbl() {
        return this.ntbarpasbl;
    }

    public void setNtbarpasbl(int i) {
        this.ntbarpasbl = i;
    }

    public int getAtatType() {
        return this.atatType;
    }

    public void setAtatType(int i) {
        this.atatType = i;
    }

    public String getAkdlui() {
        return this.akdlui;
    }

    public void setAkdlui(String str) {
        this.akdlui = str;
    }

    public String getGhId() {
        return this.ghId;
    }

    public void setGhId(String str) {
        this.ghId = str;
    }

    public String getGhPath() {
        return this.ghPath;
    }

    public void setGhPath(String str) {
        this.ghPath = str;
    }

    public String getBindId() {
        return this.bindId;
    }

    public void setBindId(String str) {
        this.bindId = str;
    }

    public Map<String, String> getLoopbackMap() {
        return this.loopbackMap;
    }

    public void setLoopbackMap(Map<String, String> map) {
        this.loopbackMap = map;
    }

    public String getLoopbackString() {
        return this.loopbackString;
    }

    public void setLoopbackString(String str) {
        this.loopbackString = str;
    }

    public String getRoverMark() {
        return this.roverMark;
    }

    public void setRoverMark(String str) {
        this.roverMark = str;
    }

    public int getRoverIsPost() {
        return this.roverIsPost;
    }

    public void setRoverIsPost(int i) {
        this.roverIsPost = i;
    }

    public int getTtc_ct2() {
        return this.ttc_ct2;
    }

    public void setTtc_ct2(int i) {
        this.ttc_ct2 = i;
    }

    public int getTtc_type() {
        return this.ttc_type;
    }

    public void setTtc_type(int i) {
        this.ttc_type = i;
    }

    public String getAdvId() {
        return this.advId;
    }

    public void setAdvId(String str) {
        this.advId = str;
    }

    public int getPreClickInterval() {
        return this.preClickInterval;
    }

    public void setPreClickInterval(int i) {
        this.preClickInterval = i;
    }

    public boolean isPreClick() {
        return this.preClick;
    }

    public void setPreClick(boolean z) {
        this.preClick = z;
    }

    public CommonJumpLoader.JumpLoaderResult getJumpResult() {
        return this.jumpResult;
    }

    public void setJumpResult(CommonJumpLoader.JumpLoaderResult jumpLoaderResult) {
        this.jumpResult = jumpLoaderResult;
    }

    public static CampaignEx parseCampaignWithBackData(JSONObject jSONObject, CampaignEx campaignEx) {
        if (jSONObject == null || campaignEx == null) {
            return null;
        }
        try {
            campaignEx.setPreClick(jSONObject.optBoolean(JSON_KEY_PRE_CLICK));
            campaignEx.setPreClickInterval(jSONObject.optInt(JSON_KEY_PRE_CLICK_INTERVAL, TTC_CT_DEFAULT_VALUE));
            campaignEx.setAdvId(jSONObject.optString(JSON_KEY_ADV_ID));
            campaignEx.setTtc_type(jSONObject.optInt(JSON_KEY_TTC_TYPE, 3));
            campaignEx.setTtc_ct2(jSONObject.optInt(JSON_KEY_TTC_CT2, TTC_CT2_DEFAULT_VALUE));
            campaignEx.setTimestamp(System.currentTimeMillis());
            campaignEx.setHtmlUrl(jSONObject.optString("html_url"));
            campaignEx.setEndScreenUrl(jSONObject.optString("end_screen_url"));
            campaignEx.setRoverMark(jSONObject.optString(ROVER_KEY_MARK));
            campaignEx.setRoverIsPost(jSONObject.optInt(ROVER_KEY_IS_POST));
            try {
                if (jSONObject.has(LOOPBACK)) {
                    String strOptString = jSONObject.optString(LOOPBACK);
                    if (!TextUtils.isEmpty(strOptString)) {
                        campaignEx.setLoopbackString(strOptString);
                        campaignEx.setLoopbackMap(loopbackStrToMap(strOptString));
                    }
                }
            } catch (Exception unused) {
                s.d("", "loopback parser error");
            }
            String strOptString2 = jSONObject.optString(KEY_GH_ID);
            if (!TextUtils.isEmpty(strOptString2)) {
                campaignEx.setGhId(strOptString2);
                String strOptString3 = jSONObject.optString(KEY_GH_PATH);
                if (!TextUtils.isEmpty(strOptString3)) {
                    campaignEx.setGhPath(com.mbridge.msdk.foundation.tools.n.b(strOptString3));
                }
                campaignEx.setBindId(jSONObject.optString(KEY_BIND_ID));
            }
            campaignEx.setApkAlt(jSONObject.optInt(JSON_KEY_APK_ALT, 0));
            campaignEx.setDisplayApkAlt(jSONObject.optInt(JSON_KEY_DISPLAY_APK_ALT, 0));
            campaignEx.setApkDisplayInfo(ApkDisplayInfo.parseByString(jSONObject.optString(JSON_KEY_APK_INFO)));
            campaignEx.setNtbarpasbl(jSONObject.optInt(JSON_KEY_NTBARPASBL, 0));
            campaignEx.setNtbarpt(jSONObject.optInt(JSON_KEY_NTBARPT, 0));
            campaignEx.setAtatType(jSONObject.optInt(JSON_KEY_ATAT_TYPE, 0));
            campaignEx.setAkdlui(jSONObject.optString(JSON_KEY_AKDLUI, ""));
            return campaignEx;
        } catch (Exception e) {
            e.printStackTrace();
            s.d(TAG, "parse campaign json exception: " + e.getLocalizedMessage());
            return campaignEx;
        }
    }

    protected static String replaceValueByKey(CampaignUnit campaignUnit, CampaignEx campaignEx, String str) {
        if (campaignUnit == null || TextUtils.isEmpty(str) || campaignEx == null) {
            return str;
        }
        try {
            HashMap<String, String> rks = campaignUnit.getRks();
            if (rks != null) {
                rks.entrySet().iterator();
                for (Map.Entry<String, String> entry : rks.entrySet()) {
                    String key = entry.getKey();
                    str = str.replaceAll("\\{" + key + "\\}", entry.getValue());
                }
            }
            HashMap<String, String> aks = campaignEx.getAks();
            if (aks != null) {
                aks.entrySet().iterator();
                for (Map.Entry<String, String> entry2 : aks.entrySet()) {
                    String key2 = entry2.getKey();
                    str = str.replaceAll("\\{" + key2 + "\\}", entry2.getValue());
                }
            }
            HashMap<String, String> epMap = campaignUnit.getEpMap();
            if (epMap != null) {
                for (Map.Entry<String, String> entry3 : epMap.entrySet()) {
                    String key3 = entry3.getKey();
                    str = str.replaceAll("\\{" + key3 + "\\}", entry3.getValue());
                }
            }
            String strReplaceAll = str.replaceAll("\\{c\\}", URLEncoder.encode(campaignUnit.assembCParams(), "utf-8"));
            try {
                Matcher matcher = Pattern.compile("=\\{.*?\\}").matcher(strReplaceAll);
                while (true) {
                    str = strReplaceAll;
                    if (!matcher.find()) {
                        break;
                    }
                    strReplaceAll = str.replace(matcher.group(0), ContainerUtils.KEY_VALUE_DELIMITER);
                }
            } catch (Throwable th) {
                str = strReplaceAll;
                th = th;
                s.b(TAG, th.getMessage(), th);
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return str;
    }

    public static CampaignEx parseCampaign(JSONObject jSONObject, CampaignEx campaignEx) {
        if (jSONObject == null || campaignEx == null) {
            return null;
        }
        try {
            campaignEx.setPreClick(jSONObject.optBoolean(JSON_KEY_PRE_CLICK));
            campaignEx.setPreClickInterval(jSONObject.optInt(JSON_KEY_PRE_CLICK_INTERVAL, TTC_CT_DEFAULT_VALUE));
            campaignEx.setAdvId(jSONObject.optString(JSON_KEY_ADV_ID));
            campaignEx.setTtc_type(jSONObject.optInt(JSON_KEY_TTC_TYPE, 3));
            campaignEx.setTtc_ct2(jSONObject.optInt(JSON_KEY_TTC_CT2, TTC_CT2_DEFAULT_VALUE));
            campaignEx.setRoverMark(jSONObject.optString(ROVER_KEY_MARK));
            campaignEx.setRoverIsPost(jSONObject.optInt(ROVER_KEY_IS_POST));
            try {
                if (jSONObject.has(LOOPBACK)) {
                    String strOptString = jSONObject.optString(LOOPBACK);
                    if (!TextUtils.isEmpty(strOptString)) {
                        campaignEx.setLoopbackString(strOptString);
                        campaignEx.setLoopbackMap(loopbackStrToMap(strOptString));
                    }
                }
            } catch (Exception unused) {
                s.d("", "loopback parser error");
            }
            String strOptString2 = jSONObject.optString(KEY_GH_ID);
            if (!TextUtils.isEmpty(strOptString2)) {
                campaignEx.setGhId(strOptString2);
                String strOptString3 = jSONObject.optString(KEY_GH_PATH);
                if (!TextUtils.isEmpty(strOptString3)) {
                    campaignEx.setGhPath(com.mbridge.msdk.foundation.tools.n.b(strOptString3));
                }
                campaignEx.setBindId(jSONObject.optString(KEY_BIND_ID));
            }
            campaignEx.setBannerHtml(jSONObject.optString("cam_html"));
            campaignEx.setAdHtml(jSONObject.optString("cam_html"));
            campaignEx.setApkAlt(jSONObject.optInt(JSON_KEY_APK_ALT, 0));
            campaignEx.setDisplayApkAlt(jSONObject.optInt(JSON_KEY_DISPLAY_APK_ALT, 0));
            campaignEx.setApkDisplayInfo(ApkDisplayInfo.parseByString(jSONObject.optString(JSON_KEY_APK_INFO)));
            campaignEx.setNtbarpasbl(jSONObject.optInt(JSON_KEY_NTBARPASBL, 0));
            campaignEx.setNtbarpt(jSONObject.optInt(JSON_KEY_NTBARPT, 0));
            campaignEx.setAtatType(jSONObject.optInt(JSON_KEY_ATAT_TYPE, 0));
            campaignEx.setAkdlui(jSONObject.optString(JSON_KEY_AKDLUI, ""));
            return campaignEx;
        } catch (Exception e) {
            e.printStackTrace();
            s.d(TAG, "parse campaign json exception: " + e.getLocalizedMessage());
            return campaignEx;
        }
    }

    public static Map<String, String> loopbackStrToMap(String str) {
        HashMap map = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            HashMap map2 = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(str);
                map2.put(LOOPBACK_DOMAIN, jSONObject.getString(LOOPBACK_DOMAIN));
                map2.put(LOOPBACK_KEY, jSONObject.getString(LOOPBACK_KEY));
                map2.put("value", jSONObject.getString("value"));
                return map2;
            } catch (Throwable unused) {
                map = map2;
                s.d("", "loopbackStrToMap error");
                return map;
            }
        } catch (Throwable unused2) {
        }
    }

    public static m trackingStr2Object(JSONObject jSONObject, m mVar) {
        if (jSONObject == null || mVar == null) {
            return null;
        }
        mVar.c(CampaignEx.processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_KEY_AD_TRACKING_APK_START)));
        mVar.d(CampaignEx.processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_KEY_AD_TRACKING_APK_END)));
        mVar.e(CampaignEx.processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_KEY_AD_TRACKING_APK_INSTALL)));
        return mVar;
    }

    public static JSONObject campaignToJsonObject(JSONObject jSONObject, CampaignEx campaignEx) throws JSONException {
        if (campaignEx == null) {
            return jSONObject;
        }
        jSONObject.put(JSON_KEY_PRE_CLICK, campaignEx.isPreClick());
        jSONObject.put(JSON_KEY_PRE_CLICK_INTERVAL, campaignEx.getPreClickInterval());
        jSONObject.put(JSON_KEY_ADV_ID, campaignEx.getAdvId());
        jSONObject.put(JSON_KEY_TTC_TYPE, campaignEx.getTtc_type());
        jSONObject.put(JSON_KEY_TTC_CT2, campaignEx.getTtc_ct2());
        jSONObject.put(KEY_GH_ID, campaignEx.getGhId());
        jSONObject.put(KEY_GH_PATH, com.mbridge.msdk.foundation.tools.n.a(campaignEx.getGhPath()));
        jSONObject.put(KEY_BIND_ID, campaignEx.getBindId());
        jSONObject.put(JSON_KEY_APK_ALT, campaignEx.getApkAlt());
        jSONObject.put(JSON_KEY_DISPLAY_APK_ALT, campaignEx.getDisplayApkAlt());
        if (campaignEx.getApkDisplayInfo() != null) {
            jSONObject.put(JSON_KEY_APK_INFO, campaignEx.getApkDisplayInfo().toJson());
        }
        jSONObject.put(ROVER_KEY_MARK, campaignEx.getRoverMark());
        jSONObject.put(ROVER_KEY_IS_POST, campaignEx.getRoverIsPost());
        jSONObject.put(CampaignEx.JSON_KEY_NV_T2, campaignEx.getNvT2());
        jSONObject.put(JSON_KEY_NTBARPASBL, campaignEx.getNtbarpasbl());
        jSONObject.put(JSON_KEY_NTBARPT, campaignEx.getNtbarpt());
        jSONObject.put(JSON_KEY_ATAT_TYPE, campaignEx.getAtatType());
        jSONObject.put(JSON_KEY_AKDLUI, campaignEx.getAkdlui());
        return jSONObject;
    }

    public String matchLoopback(String str) {
        Map<String, String> loopbackMap;
        String strReplace;
        try {
            if (TextUtils.isEmpty(str) || (loopbackMap = getLoopbackMap()) == null || loopbackMap.size() <= 0) {
                return str;
            }
            Uri uri = Uri.parse(str);
            String host = uri.getHost();
            String str2 = loopbackMap.get(LOOPBACK_DOMAIN);
            if (TextUtils.isEmpty(host) || !host.contains(str2)) {
                return str;
            }
            String str3 = loopbackMap.get(LOOPBACK_KEY);
            String str4 = loopbackMap.get("value");
            if (!str.contains(str3) && TextUtils.isEmpty(uri.getQueryParameter(str3)) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                strReplace = str + ContainerUtils.FIELD_DELIMITER + str3 + ContainerUtils.KEY_VALUE_DELIMITER + str4;
            } else {
                if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                    return str;
                }
                strReplace = str.replace(str3 + ContainerUtils.KEY_VALUE_DELIMITER + (TextUtils.isEmpty(uri.getQueryParameter(str3)) ? "" : uri.getQueryParameter(str3)), str3 + ContainerUtils.KEY_VALUE_DELIMITER + str4);
            }
            return strReplace;
        } catch (Throwable unused) {
            s.d("", "matchLoopback error");
            return str;
        }
    }

    public boolean needShowIDialog(CampaignEx campaignEx) {
        boolean z = this.apk_alt == 1 && campaignEx.getLinkType() == 3 && campaignEx.getDisplayApkAlt() != 1;
        if (!z) {
            return z;
        }
        try {
            if (w.c(com.mbridge.msdk.foundation.controller.a.f().j(), getPackageName())) {
                return false;
            }
            return z;
        } catch (Throwable th) {
            s.a(TAG, th.getMessage());
            return z;
        }
    }
}
