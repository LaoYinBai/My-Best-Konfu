package com.mbridge.msdk.foundation.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.MBConfiguration;
import com.mbridge.msdk.system.NoProGuard;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CampaignUnit implements NoProGuard, Serializable {
    public static final String JSON_KEY_ADS = "ads";
    public static final String JSON_KEY_AD_HTML = "cam_html";
    public static final String JSON_KEY_AD_TYPE = "ad_type";
    public static final String JSON_KEY_AD_ZIP = "cam_tpl_url";
    public static final String JSON_KEY_BANNER_HTML = "cam_html";
    public static final String JSON_KEY_BANNER_URL = "cam_tpl_url";
    public static final String JSON_KEY_CSP = "csp";
    public static final String JSON_KEY_DO = "do";
    public static final String JSON_KEY_ENCRYPT_PRICE = "encrypt_p";
    public static final String JSON_KEY_END_SCREEN_URL = "end_screen_url";
    public static final String JSON_KEY_FRAME_ADS = "frames";
    public static final String JSON_KEY_HTML_URL = "html_url";
    public static final String JSON_KEY_IA_EXT1 = "ia_all_ext1";
    public static final String JSON_KEY_IA_EXT2 = "ia_all_ext2";
    public static final String JSON_KEY_IA_ICON = "ia_icon";
    public static final String JSON_KEY_IA_ORI = "ia_ori";
    public static final String JSON_KEY_IA_RST = "ia_rst";
    public static final String JSON_KEY_IA_URL = "ia_url";
    public static final String JSON_KEY_JM_DO = "jm_do";
    public static final String JSON_KEY_MOF_TEMPLATE_URL = "mof_template_url";
    public static final String JSON_KEY_MOF_TPLID = "mof_tplid";
    public static final String JSON_KEY_NSCPT = "nscpt";
    public static final String JSON_KEY_ONLY_IMPRESSION_URL = "only_impression_url";
    public static final String JSON_KEY_PARENT_SESSION_ID = "parent_session_id";
    public static final String JSON_KEY_PV_URLS = "pv_urls";
    public static final String JSON_KEY_REPLACE_TMP = "replace_tmp";
    public static final String JSON_KEY_REQ_EXT_DATA = "req_ext_data";
    public static final String JSON_KEY_RKS = "rks";
    public static final String JSON_KEY_SESSION_ID = "a";
    public static final String JSON_KEY_SH = "sh";
    public static final String JSON_KEY_TEMPLATE = "template";
    public static final String JSON_KEY_TOKEN_RULE = "token_r";
    public static final String JSON_KEY_UNIT_SIZE = "unit_size";
    public static final String JSON_KEY_VCN = "vcn";
    public static final String KEY_IRLFA = "irlfa";
    private static final String TAG = "CampaignUnit";
    private static final long serialVersionUID = 1;
    private String adHtml;
    private int adType;
    private String adZip;
    public ArrayList<CampaignEx> ads;
    private String bannerHtml;
    private String bannerUrl;
    private String csp;
    private String domain;
    private HashMap<String, String> epMap;
    private String htmlUrl;
    private String ia_all_ext1;
    private String ia_all_ext2;
    private String ia_icon;
    private int ia_ori;
    private int ia_rst;
    private String ia_url;
    private int jmDo;
    private List<Frame> listFrames;
    private String onlyImpressionUrl;
    private String parentSessionId;
    private String requestId;
    private HashMap<String, String> rks;
    private String sessionId;
    private String sh;
    private int template;
    private int tokenRule;
    private String unitSize;
    private int vcn;
    private String msg = "";
    private StringBuffer cParams = new StringBuffer();
    private String encryptPrice = "";

    private Object nullToEmpty(Object obj) {
        return obj == null ? "" : obj;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getAdZip() {
        return this.adZip;
    }

    public void setAdZip(String str) {
        this.adZip = str;
    }

    public String getAdHtml() {
        return this.adHtml;
    }

    public void setAdHtml(String str) {
        this.adHtml = str;
    }

    public String getBannerUrl() {
        return this.bannerUrl;
    }

    public void setBannerUrl(String str) {
        this.bannerUrl = str;
    }

    public String getBannerHtml() {
        return this.bannerHtml;
    }

    public void setBannerHtml(String str) {
        this.bannerHtml = str;
    }

    public String getIa_icon() {
        return this.ia_icon;
    }

    public void setIa_icon(String str) {
        this.ia_icon = str;
    }

    public int getIa_rst() {
        return this.ia_rst;
    }

    public void setIa_rst(int i) {
        this.ia_rst = i;
    }

    public String getIa_url() {
        return this.ia_url;
    }

    public void setIa_url(String str) {
        this.ia_url = str;
    }

    public int getIa_ori() {
        return this.ia_ori;
    }

    public void setIa_ori(int i) {
        this.ia_ori = i;
    }

    public String getIa_all_ext1() {
        return this.ia_all_ext1;
    }

    public void setIa_all_ext1(String str) {
        this.ia_all_ext1 = str;
    }

    public String getIa_all_ext2() {
        return this.ia_all_ext2;
    }

    public void setIa_all_ext2(String str) {
        this.ia_all_ext2 = str;
    }

    public String getEncryptPrice() {
        return this.encryptPrice;
    }

    public void setEncryptPrice(String str) {
        this.encryptPrice = str;
    }

    public int getVcn() {
        if (this.vcn > 1) {
            return this.vcn;
        }
        return 1;
    }

    public void setVcn(int i) {
        this.vcn = i;
    }

    public int getTokenRule() {
        if (this.tokenRule == 1) {
            return this.tokenRule;
        }
        return 0;
    }

    public void setTokenRule(int i) {
        this.tokenRule = i;
    }

    public HashMap<String, String> getRks() {
        return this.rks;
    }

    public void setRks(HashMap<String, String> map) {
        this.rks = map;
    }

    public HashMap<String, String> getEpMap() {
        return this.epMap;
    }

    public void setEpMap(HashMap<String, String> map) {
        this.epMap = map;
    }

    public String getCsp() {
        return this.csp;
    }

    public void setCsp(String str) {
        this.csp = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public String getSh() {
        return this.sh;
    }

    public void setSh(String str) {
        this.sh = str;
    }

    public int getJmDo() {
        return this.jmDo;
    }

    public void setJmDo(int i) {
        this.jmDo = i;
    }

    public List<Frame> getListFrames() {
        return this.listFrames;
    }

    public void setListFrames(List<Frame> list) {
        this.listFrames = list;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public String getParentSessionId() {
        return this.parentSessionId;
    }

    public void setParentSessionId(String str) {
        this.parentSessionId = str;
    }

    public int getAdType() {
        return this.adType;
    }

    public void setAdType(int i) {
        this.adType = i;
    }

    public String getUnitSize() {
        return this.unitSize;
    }

    public void setUnitSize(String str) {
        this.unitSize = str;
    }

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public void setHtmlUrl(String str) {
        this.htmlUrl = str;
    }

    public String getOnlyImpressionUrl() {
        return this.onlyImpressionUrl;
    }

    public void setOnlyImpressionUrl(String str) {
        this.onlyImpressionUrl = str;
    }

    public ArrayList<CampaignEx> getAds() {
        return this.ads;
    }

    public void setAds(ArrayList<CampaignEx> arrayList) {
        this.ads = arrayList;
    }

    public int getTemplate() {
        return this.template;
    }

    public void setTemplate(int i) {
        this.template = i;
    }

    public String assembCParams() {
        if (this.cParams != null && this.cParams.length() > 0) {
            return this.cParams.toString();
        }
        try {
            String strA = com.mbridge.msdk.foundation.tools.o.A();
            String strE = com.mbridge.msdk.foundation.tools.o.e(com.mbridge.msdk.foundation.controller.a.f().j());
            String strD = com.mbridge.msdk.foundation.tools.o.d(com.mbridge.msdk.foundation.controller.a.f().j());
            String strValueOf = "";
            String str = "";
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                strValueOf = String.valueOf(com.mbridge.msdk.foundation.tools.o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
                str = com.mbridge.msdk.foundation.tools.o.w(com.mbridge.msdk.foundation.controller.a.f().j()) + "x" + com.mbridge.msdk.foundation.tools.o.x(com.mbridge.msdk.foundation.controller.a.f().j());
            }
            StringBuffer stringBuffer = this.cParams;
            stringBuffer.append(getAdType());
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty("1"));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(Build.VERSION.RELEASE));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(MBConfiguration.SDK_VERSION));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(com.mbridge.msdk.foundation.tools.o.t()));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(str));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(Integer.valueOf(com.mbridge.msdk.foundation.tools.o.s(com.mbridge.msdk.foundation.controller.a.f().j()))));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(com.mbridge.msdk.foundation.tools.o.r(com.mbridge.msdk.foundation.controller.a.f().j())));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(strValueOf));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(strD));
            stringBuffer.append(nullToEmpty(strE));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(com.mbridge.msdk.foundation.tools.o.b(com.mbridge.msdk.foundation.controller.a.f().j())));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(com.mbridge.msdk.foundation.tools.o.i(com.mbridge.msdk.foundation.controller.a.f().j())));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(com.mbridge.msdk.foundation.tools.o.g(com.mbridge.msdk.foundation.controller.a.f().j())));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(strA));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(""));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(com.mbridge.msdk.foundation.tools.o.v()));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(""));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append("");
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(""));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            stringBuffer.append(nullToEmpty(com.mbridge.msdk.foundation.same.a.s + "," + com.mbridge.msdk.foundation.same.a.t));
            stringBuffer.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
            this.cParams = stringBuffer;
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
        }
        return this.cParams.toString();
    }

    public static CampaignUnit parseV5CampaignUnit(JSONObject jSONObject) {
        return parseV5CampaignUnit(jSONObject, "");
    }

    public static CampaignUnit parseCampaignUnit(JSONObject jSONObject) {
        return parseCampaignUnit(jSONObject, "");
    }

    public String getRequestId() {
        try {
            if (!TextUtils.isEmpty(this.requestId)) {
                return this.requestId;
            }
            if (TextUtils.isEmpty(this.onlyImpressionUrl)) {
                return "";
            }
            Uri uri = Uri.parse(this.onlyImpressionUrl);
            if (uri != null) {
                this.requestId = uri.getQueryParameter(CampaignEx.JSON_KEY_AD_K);
            }
            return this.requestId;
        } catch (Exception unused) {
            return "";
        }
    }

    public static CampaignUnit parseV5CampaignUnit(JSONObject jSONObject, String str) {
        return parseCampaignUnit(jSONObject, str);
    }

    private static void getSysIDAndBKUPID(JSONObject jSONObject) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (jSONObject == null || contextJ == null) {
                return;
            }
            String strOptString = jSONObject.optString(com.huawei.updatesdk.service.d.a.b.a);
            if (!TextUtils.isEmpty(strOptString) && !TextUtils.equals(com.mbridge.msdk.foundation.same.a.s, strOptString)) {
                com.mbridge.msdk.foundation.same.a.s = strOptString;
                com.mbridge.msdk.foundation.a.a.a.a().a(com.huawei.updatesdk.service.d.a.b.a, com.mbridge.msdk.foundation.same.a.s);
                SharedPreferences sharedPreferences = contextJ.getSharedPreferences(com.mbridge.msdk.foundation.tools.n.b("H+tU+FeXHM=="), 0);
                if (sharedPreferences != null) {
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString(com.mbridge.msdk.foundation.tools.n.b("H+tU+bfPhM=="), com.mbridge.msdk.foundation.same.a.s);
                    editorEdit.apply();
                }
            }
            String strOptString2 = jSONObject.optString("c");
            if (TextUtils.isEmpty(strOptString2) || TextUtils.equals(com.mbridge.msdk.foundation.same.a.t, strOptString2)) {
                return;
            }
            com.mbridge.msdk.foundation.same.a.t = strOptString2;
            com.mbridge.msdk.foundation.a.a.a.a().a("c", com.mbridge.msdk.foundation.same.a.t);
            SharedPreferences sharedPreferences2 = contextJ.getSharedPreferences(com.mbridge.msdk.foundation.tools.n.b("H+tU+FeXHM=="), 0);
            if (sharedPreferences2 != null) {
                SharedPreferences.Editor editorEdit2 = sharedPreferences2.edit();
                editorEdit2.putString(com.mbridge.msdk.foundation.tools.n.b("H+tU+Fz8"), com.mbridge.msdk.foundation.same.a.t);
                editorEdit2.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CampaignUnit parseCampaignUnit(JSONObject jSONObject, String str) throws JSONException {
        CampaignUnit campaignUnit;
        ArrayList arrayList = null;
        if (jSONObject == null) {
            return null;
        }
        try {
            campaignUnit = new CampaignUnit();
            try {
                String strOptString = jSONObject.optString(JSON_KEY_RKS);
                if (!TextUtils.isEmpty(strOptString)) {
                    JSONObject jSONObject2 = new JSONObject(strOptString);
                    Iterator<String> itKeys = jSONObject2.keys();
                    HashMap<String, String> map = new HashMap<>();
                    while (itKeys != null && itKeys.hasNext()) {
                        String next = itKeys.next();
                        map.put(next, jSONObject2.optString(next));
                    }
                    campaignUnit.setRks(map);
                }
                if (!TextUtils.isEmpty(str)) {
                    HashMap<String, String> map2 = new HashMap<>();
                    map2.put("encrypt_p", "");
                    map2.put(KEY_IRLFA, "");
                    campaignUnit.setEpMap(map2);
                }
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(JSON_KEY_REQ_EXT_DATA);
                int iOptInt = jSONObject.optInt(JSON_KEY_NSCPT, 1);
                String strOptString2 = jSONObject.optString(JSON_KEY_MOF_TEMPLATE_URL, "");
                int iOptInt2 = jSONObject.optInt(JSON_KEY_MOF_TPLID, 0);
                String string = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.toString() : "";
                new JSONArray();
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pv_urls");
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    arrayList = new ArrayList(jSONArrayOptJSONArray.length());
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        arrayList.add(jSONArrayOptJSONArray.getString(i));
                    }
                }
                com.mbridge.msdk.foundation.db.a.a.a().a(jSONObject.optJSONObject(JSON_KEY_REPLACE_TMP), true);
                getSysIDAndBKUPID(jSONObject);
                campaignUnit.setSessionId(jSONObject.optString("a"));
                campaignUnit.setParentSessionId(jSONObject.optString("parent_session_id"));
                campaignUnit.setAdType(jSONObject.optInt("ad_type"));
                campaignUnit.setUnitSize(jSONObject.optString("unit_size"));
                campaignUnit.setHtmlUrl(jSONObject.optString("html_url"));
                campaignUnit.setOnlyImpressionUrl(jSONObject.optString("only_impression_url"));
                campaignUnit.setTemplate(jSONObject.optInt("template"));
                campaignUnit.setJmDo(jSONObject.optInt(JSON_KEY_JM_DO));
                campaignUnit.setIa_icon(jSONObject.optString("ia_icon"));
                campaignUnit.setIa_rst(jSONObject.optInt("ia_rst"));
                campaignUnit.setIa_url(jSONObject.optString("ia_url"));
                campaignUnit.setIa_ori(jSONObject.optInt("ia_ori"));
                campaignUnit.setIa_all_ext1(jSONObject.optString(JSON_KEY_IA_EXT1));
                campaignUnit.setIa_all_ext2(jSONObject.optString(JSON_KEY_IA_EXT2));
                campaignUnit.setVcn(jSONObject.optInt("vcn"));
                campaignUnit.setTokenRule(jSONObject.optInt("token_r"));
                campaignUnit.setEncryptPrice(jSONObject.optString("encrypt_p"));
                JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(JSON_KEY_ADS);
                JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("frames");
                if (jSONArrayOptJSONArray3 != null && jSONArrayOptJSONArray3.length() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    int i2 = 0;
                    while (i2 < jSONArrayOptJSONArray3.length()) {
                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray3.optJSONObject(i2);
                        JSONArray jSONArray = jSONObjectOptJSONObject2.getJSONArray(JSON_KEY_ADS);
                        ArrayList arrayList3 = new ArrayList();
                        int i3 = 0;
                        while (i3 < jSONArray.length()) {
                            int i4 = i3;
                            ArrayList arrayList4 = arrayList3;
                            JSONArray jSONArray2 = jSONArray;
                            ArrayList arrayList5 = arrayList2;
                            JSONObject jSONObject3 = jSONObjectOptJSONObject2;
                            int i5 = i2;
                            JSONArray jSONArray3 = jSONArrayOptJSONArray3;
                            CampaignEx campaign = CampaignEx.parseCampaign(jSONArray.optJSONObject(i3), jSONObject.optString("only_impression_url"), jSONObject.optString("html_url"), jSONObject.optString("end_screen_url"), false, campaignUnit, str);
                            if (campaign != null) {
                                campaign.setKeyIaUrl(campaignUnit.getIa_url());
                                campaign.setKeyIaOri(campaignUnit.getIa_ori());
                                campaign.setKeyIaRst(campaignUnit.getIa_rst());
                                campaign.setKeyIaIcon(campaignUnit.getIa_icon());
                                campaign.setAdType(jSONObject.optInt("ad_type"));
                                campaign.setIa_ext1(jSONObject.optString(CampaignEx.KEY_IA_EXT1));
                                campaign.setIa_ext2(jSONObject.optString(CampaignEx.KEY_IA_EXT2));
                                campaign.setVcn(campaignUnit.getVcn());
                                campaign.setTokenRule(campaignUnit.getTokenRule());
                                campaign.setEncryptPrice(campaignUnit.getEncryptPrice());
                                campaign.setMof_tplid(iOptInt2);
                                campaign.setMof_template_url(strOptString2);
                                campaign.setNscpt(iOptInt);
                                campaign.setPv_urls(arrayList);
                                campaign.setReq_ext_data(string);
                                arrayList4.add(campaign);
                            } else {
                                campaignUnit.setMsg("parse campaign error ,campaign is null");
                            }
                            i3 = i4 + 1;
                            arrayList3 = arrayList4;
                            jSONObjectOptJSONObject2 = jSONObject3;
                            jSONArray = jSONArray2;
                            i2 = i5;
                            jSONArrayOptJSONArray3 = jSONArray3;
                            arrayList2 = arrayList5;
                        }
                        ArrayList arrayList6 = arrayList2;
                        Frame frame = new Frame();
                        frame.setParentSessionId(jSONObject.optString("parent_session_id"));
                        frame.setSessionId(jSONObject.optString("a"));
                        frame.setCampaigns(arrayList3);
                        frame.setTemplate(jSONObjectOptJSONObject2.optInt("template"));
                        arrayList6.add(frame);
                        i2++;
                        arrayList2 = arrayList6;
                        jSONArrayOptJSONArray3 = jSONArrayOptJSONArray3;
                    }
                    campaignUnit.setListFrames(arrayList2);
                    return campaignUnit;
                }
                if (jSONArrayOptJSONArray2 == null || jSONArrayOptJSONArray2.length() <= 0) {
                    return campaignUnit;
                }
                ArrayList<CampaignEx> arrayList7 = new ArrayList<>();
                int i6 = 0;
                while (i6 < jSONArrayOptJSONArray2.length()) {
                    int i7 = i6;
                    CampaignEx campaign2 = CampaignEx.parseCampaign(jSONArrayOptJSONArray2.optJSONObject(i6), jSONObject.optString("only_impression_url"), jSONObject.optString("html_url"), jSONObject.optString("end_screen_url"), false, campaignUnit, str);
                    if (campaign2 != null) {
                        campaign2.setMof_tplid(iOptInt2);
                        campaign2.setMof_template_url(strOptString2);
                        campaign2.setNscpt(iOptInt);
                        campaign2.setPv_urls(arrayList);
                        campaign2.setReq_ext_data(string);
                        campaign2.setVcn(campaignUnit.getVcn());
                        campaign2.setTokenRule(campaignUnit.getTokenRule());
                        campaign2.setEncryptPrice(campaignUnit.getEncryptPrice());
                        arrayList7.add(campaign2);
                    } else {
                        campaignUnit.setMsg("parse campaign error ,campaign is null");
                    }
                    i6 = i7 + 1;
                }
                campaignUnit.setAds(arrayList7);
                return campaignUnit;
            } catch (Exception unused) {
                s.d(TAG, "parse campaign unit exception");
                return campaignUnit;
            }
        } catch (Exception unused2) {
            campaignUnit = null;
        }
    }
}
