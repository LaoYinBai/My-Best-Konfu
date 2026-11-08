package com.bytedance.a;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b8\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001sB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020pH\u0016J\b\u0010q\u001a\u00020pH\u0016J\b\u0010r\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010,\"\u0004\b0\u0010.R\u001a\u00101\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0006\"\u0004\b3\u0010\bR\u001a\u00104\u001a\u000205X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0006\"\u0004\b<\u0010\bR\u001a\u0010=\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0006\"\u0004\b?\u0010\bR\u001a\u0010@\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0006\"\u0004\bB\u0010\bR\u001a\u0010C\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0006\"\u0004\bE\u0010\bR\u001a\u0010F\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0006\"\u0004\bH\u0010\bR\u001a\u0010I\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0006\"\u0004\bK\u0010\bR\u001a\u0010L\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0006\"\u0004\bN\u0010\bR\u001a\u0010O\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0006\"\u0004\bQ\u0010\bR\u001a\u0010R\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u0006\"\u0004\bT\u0010\bR\u001a\u0010U\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u0006\"\u0004\bW\u0010\bR\u001a\u0010X\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u0006\"\u0004\bZ\u0010\bR\u001a\u0010[\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u0006\"\u0004\b]\u0010\bR\u001a\u0010^\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u0006\"\u0004\b`\u0010\bR\u001a\u0010a\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\u0006\"\u0004\bc\u0010\bR\u001a\u0010d\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u0006\"\u0004\bf\u0010\bR\u001a\u0010g\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\u0006\"\u0004\bi\u0010\bR\u001a\u0010j\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\u0006\"\u0004\bl\u0010\b¨\u0006t"}, d2 = {"Lcom/bytedance/applog/alink/model/AttributionData;", "Lcom/bytedance/applog/alink/model/BaseData;", "()V", "accountId", "", "getAccountId", "()Ljava/lang/String;", "setAccountId", "(Ljava/lang/String;)V", "accountName", "getAccountName", "setAccountName", "activationTimestamp", "getActivationTimestamp", "setActivationTimestamp", "activationType", "Lcom/bytedance/applog/alink/model/AttributionData$ActivationType;", "getActivationType", "()Lcom/bytedance/applog/alink/model/AttributionData$ActivationType;", "setActivationType", "(Lcom/bytedance/applog/alink/model/AttributionData$ActivationType;)V", "adId", "getAdId", "setAdId", "adName", "getAdName", "setAdName", "campaignId", "getCampaignId", "setCampaignId", "campaignName", "getCampaignName", "setCampaignName", "creativeId", "getCreativeId", "setCreativeId", "creativeName", "getCreativeName", "setCreativeName", "deeplinkValue", "getDeeplinkValue", "setDeeplinkValue", "isFirstLaunch", "", "()Z", "setFirstLaunch", "(Z)V", "isRetargeting", "setRetargeting", "name", "getName", "setName", "reengagementWindow", "", "getReengagementWindow", "()I", "setReengagementWindow", "(I)V", "touchTimestamp", "getTouchTimestamp", "setTouchTimestamp", "touchType", "getTouchType", "setTouchType", "trAdmaster", "getTrAdmaster", "setTrAdmaster", "trDp", "getTrDp", "setTrDp", "trInstallType", "getTrInstallType", "setTrInstallType", "trParam1", "getTrParam1", "setTrParam1", "trParam2", "getTrParam2", "setTrParam2", "trParam3", "getTrParam3", "setTrParam3", "trParam4", "getTrParam4", "setTrParam4", "trShareuser", "getTrShareuser", "setTrShareuser", "trSiteId", "getTrSiteId", "setTrSiteId", "trSiteName", "getTrSiteName", "setTrSiteName", "utmCampaign", "getUtmCampaign", "setUtmCampaign", "utmContent", "getUtmContent", "setUtmContent", "utmMedium", "getUtmMedium", "setUtmMedium", "utmSource", "getUtmSource", "setUtmSource", "utmTerm", "getUtmTerm", "setUtmTerm", "initWithJson", "", "json", "Lorg/json/JSONObject;", "toJson", "toString", "ActivationType", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class at extends bd {
    public boolean F;
    public boolean m;
    public int n;

    /* renamed from: a, reason: collision with root package name */
    @org.b.a.d
    public String f3371a = "";

    /* renamed from: b, reason: collision with root package name */
    @org.b.a.d
    public String f3372b = "";

    /* renamed from: c, reason: collision with root package name */
    @org.b.a.d
    public String f3373c = "";

    @org.b.a.d
    public String d = "";

    @org.b.a.d
    public String e = "";

    @org.b.a.d
    public String f = "";

    @org.b.a.d
    public String g = "";

    @org.b.a.d
    public String h = "";

    @org.b.a.d
    public String i = "";

    @org.b.a.d
    public String j = "";

    @org.b.a.d
    public String k = "";

    @org.b.a.d
    public String l = "";

    @org.b.a.d
    public String o = "";

    @org.b.a.d
    public String p = "";

    @org.b.a.d
    public String q = "";

    @org.b.a.d
    public String r = "";

    @org.b.a.d
    public String s = "";

    @org.b.a.d
    public String t = "";

    @org.b.a.d
    public String u = "";

    @org.b.a.d
    public String v = "";

    @org.b.a.d
    public String w = "";

    @org.b.a.d
    public String x = "";

    @org.b.a.d
    public String y = "";

    @org.b.a.d
    public String z = "";

    @org.b.a.d
    public String A = "";

    @org.b.a.d
    public String B = "";

    @org.b.a.d
    public String C = "";

    @org.b.a.d
    public a D = a.PROMOTION;

    @org.b.a.d
    public String E = "";

    public enum a {
        PROMOTION,
        ORGANIC
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.TypeCastException */
    @Override // com.bytedance.a.bd
    @org.b.a.d
    public JSONObject a() throws JSONException, TypeCastException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.f3371a);
        jSONObject.put("utm_campaign", this.f3372b);
        jSONObject.put("utm_source", this.f3373c);
        jSONObject.put("utm_medium", this.d);
        jSONObject.put("utm_content", this.e);
        jSONObject.put("utm_term", this.f);
        jSONObject.put("tr_shareuser", this.g);
        jSONObject.put("tr_admaster", this.h);
        jSONObject.put("tr_param1", this.i);
        jSONObject.put("tr_param2", this.j);
        jSONObject.put("tr_param3", this.k);
        jSONObject.put("tr_param4", this.l);
        jSONObject.put("is_retargeting", this.m);
        jSONObject.put("reengagement_window", this.n);
        jSONObject.put("tr_dp", this.o);
        jSONObject.put("deeplink_value", this.p);
        jSONObject.put("tr_site_id", this.q);
        jSONObject.put("tr_site_name", this.r);
        jSONObject.put("account_id", this.s);
        jSONObject.put("account_name", this.t);
        jSONObject.put("campaign_id", this.u);
        jSONObject.put("campaign_name", this.v);
        jSONObject.put("ad_id", this.w);
        jSONObject.put("ad_name", this.x);
        jSONObject.put("creative_id", this.y);
        jSONObject.put("creative_name", this.z);
        jSONObject.put("tr_install_type", this.A);
        jSONObject.put("touch_type", this.B);
        jSONObject.put("touch_timestamp", this.C);
        String strName = this.D.name();
        Locale locale = Locale.ROOT;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
        if (strName == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = strName.toLowerCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        jSONObject.put("activation_type", lowerCase);
        jSONObject.put("activation_timestamp", this.E);
        jSONObject.put("is_first_launch", this.F);
        return jSONObject;
    }

    @Override // com.bytedance.a.bd
    public void a(@org.b.a.d JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
        String strOptString = jSONObject.optString("name");
        Intrinsics.checkExpressionValueIsNotNull(strOptString, "json.optString(\"name\")");
        this.f3371a = strOptString;
        String strOptString2 = jSONObject.optString("utm_campaign");
        Intrinsics.checkExpressionValueIsNotNull(strOptString2, "json.optString(\"utm_campaign\")");
        this.f3372b = strOptString2;
        String strOptString3 = jSONObject.optString("utm_source");
        Intrinsics.checkExpressionValueIsNotNull(strOptString3, "json.optString(\"utm_source\")");
        this.f3373c = strOptString3;
        String strOptString4 = jSONObject.optString("utm_medium");
        Intrinsics.checkExpressionValueIsNotNull(strOptString4, "json.optString(\"utm_medium\")");
        this.d = strOptString4;
        String strOptString5 = jSONObject.optString("utm_content");
        Intrinsics.checkExpressionValueIsNotNull(strOptString5, "json.optString(\"utm_content\")");
        this.e = strOptString5;
        String strOptString6 = jSONObject.optString("utm_term");
        Intrinsics.checkExpressionValueIsNotNull(strOptString6, "json.optString(\"utm_term\")");
        this.f = strOptString6;
        String strOptString7 = jSONObject.optString("tr_shareuser");
        Intrinsics.checkExpressionValueIsNotNull(strOptString7, "json.optString(\"tr_shareuser\")");
        this.g = strOptString7;
        String strOptString8 = jSONObject.optString("tr_admaster");
        Intrinsics.checkExpressionValueIsNotNull(strOptString8, "json.optString(\"tr_admaster\")");
        this.h = strOptString8;
        String strOptString9 = jSONObject.optString("tr_param1");
        Intrinsics.checkExpressionValueIsNotNull(strOptString9, "json.optString(\"tr_param1\")");
        this.i = strOptString9;
        String strOptString10 = jSONObject.optString("tr_param2");
        Intrinsics.checkExpressionValueIsNotNull(strOptString10, "json.optString(\"tr_param2\")");
        this.j = strOptString10;
        String strOptString11 = jSONObject.optString("tr_param3");
        Intrinsics.checkExpressionValueIsNotNull(strOptString11, "json.optString(\"tr_param3\")");
        this.k = strOptString11;
        String strOptString12 = jSONObject.optString("tr_param4");
        Intrinsics.checkExpressionValueIsNotNull(strOptString12, "json.optString(\"tr_param4\")");
        this.l = strOptString12;
        this.m = jSONObject.optBoolean("is_retargeting");
        this.n = jSONObject.optInt("reengagement_window");
        String strOptString13 = jSONObject.optString("tr_dp");
        Intrinsics.checkExpressionValueIsNotNull(strOptString13, "json.optString(\"tr_dp\")");
        this.o = strOptString13;
        String strOptString14 = jSONObject.optString("deeplink_value");
        Intrinsics.checkExpressionValueIsNotNull(strOptString14, "json.optString(\"deeplink_value\")");
        this.p = strOptString14;
        String strOptString15 = jSONObject.optString("tr_site_id");
        Intrinsics.checkExpressionValueIsNotNull(strOptString15, "json.optString(\"tr_site_id\")");
        this.q = strOptString15;
        String strOptString16 = jSONObject.optString("tr_site_name");
        Intrinsics.checkExpressionValueIsNotNull(strOptString16, "json.optString(\"tr_site_name\")");
        this.r = strOptString16;
        String strOptString17 = jSONObject.optString("account_id");
        Intrinsics.checkExpressionValueIsNotNull(strOptString17, "json.optString(\"account_id\")");
        this.s = strOptString17;
        String strOptString18 = jSONObject.optString("account_name");
        Intrinsics.checkExpressionValueIsNotNull(strOptString18, "json.optString(\"account_name\")");
        this.t = strOptString18;
        String strOptString19 = jSONObject.optString("campaign_id");
        Intrinsics.checkExpressionValueIsNotNull(strOptString19, "json.optString(\"campaign_id\")");
        this.u = strOptString19;
        String strOptString20 = jSONObject.optString("campaign_name");
        Intrinsics.checkExpressionValueIsNotNull(strOptString20, "json.optString(\"campaign_name\")");
        this.v = strOptString20;
        String strOptString21 = jSONObject.optString("ad_id");
        Intrinsics.checkExpressionValueIsNotNull(strOptString21, "json.optString(\"ad_id\")");
        this.w = strOptString21;
        String strOptString22 = jSONObject.optString("ad_name");
        Intrinsics.checkExpressionValueIsNotNull(strOptString22, "json.optString(\"ad_name\")");
        this.x = strOptString22;
        String strOptString23 = jSONObject.optString("creative_id");
        Intrinsics.checkExpressionValueIsNotNull(strOptString23, "json.optString(\"creative_id\")");
        this.y = strOptString23;
        String strOptString24 = jSONObject.optString("creative_name");
        Intrinsics.checkExpressionValueIsNotNull(strOptString24, "json.optString(\"creative_name\")");
        this.z = strOptString24;
        String strOptString25 = jSONObject.optString("tr_install_type");
        Intrinsics.checkExpressionValueIsNotNull(strOptString25, "json.optString(\"tr_install_type\")");
        this.A = strOptString25;
        String strOptString26 = jSONObject.optString("touch_type");
        Intrinsics.checkExpressionValueIsNotNull(strOptString26, "json.optString(\"touch_type\")");
        this.B = strOptString26;
        String strOptString27 = jSONObject.optString("touch_timestamp");
        Intrinsics.checkExpressionValueIsNotNull(strOptString27, "json.optString(\"touch_timestamp\")");
        this.C = strOptString27;
        this.D = Intrinsics.areEqual(jSONObject.optString("activation_type"), "promotion") ? a.PROMOTION : a.ORGANIC;
        String strOptString28 = jSONObject.optString("activation_timestamp");
        Intrinsics.checkExpressionValueIsNotNull(strOptString28, "json.optString(\"activation_timestamp\")");
        this.E = strOptString28;
        this.F = jSONObject.optBoolean("is_first_launch");
    }

    @org.b.a.d
    public String toString() {
        String string = a().toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "toJson().toString()");
        return string;
    }
}
