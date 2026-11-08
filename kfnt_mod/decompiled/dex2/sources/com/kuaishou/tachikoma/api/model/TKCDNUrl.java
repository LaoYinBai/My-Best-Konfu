package com.kuaishou.tachikoma.api.model;

import androidx.annotation.Nullable;
import com.kwad.sdk.e.f;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKCDNUrl extends BaseJsonApiParse implements Serializable {
    private static final String CDN = "cdn";
    private static final String IP = "ip";
    private static final String URL = "url";
    private static final String URL_PATTERN = "urlPattern";
    private static final String URL_TYPE = "urlType";
    private static final long serialVersionUID = 8253139331705520143L;
    public String mCdn;
    public Set<Integer> mFeature;
    public Map<String, String> mHeaders;
    public String mIp;
    public boolean mIsFreeTrafficCdn;
    public String mPushCdn;
    public String mUrl;
    public String mUrlPattern;
    public String mUrlType;

    public TKCDNUrl() {
    }

    public TKCDNUrl(TKCDNUrl tKCDNUrl) {
        if (tKCDNUrl != null) {
            this.mCdn = tKCDNUrl.mCdn;
            this.mUrl = tKCDNUrl.mUrl;
            this.mUrlType = tKCDNUrl.mUrlType;
            this.mIsFreeTrafficCdn = tKCDNUrl.mIsFreeTrafficCdn;
            if (tKCDNUrl.mFeature != null) {
                this.mFeature = new HashSet(tKCDNUrl.mFeature);
            }
            this.mPushCdn = tKCDNUrl.mPushCdn;
            if (tKCDNUrl.mHeaders != null) {
                this.mHeaders = new HashMap(tKCDNUrl.mHeaders);
            }
            this.mIp = tKCDNUrl.mIp;
            this.mUrlPattern = tKCDNUrl.mUrlPattern;
        }
    }

    public TKCDNUrl(String str, String str2) {
        this.mCdn = str;
        this.mUrl = str2;
    }

    public TKCDNUrl(String str, String str2, String str3, String str4) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIp = str3;
        this.mUrlPattern = str4;
    }

    public TKCDNUrl(String str, String str2, String str3, String str4, String str5) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIp = str3;
        this.mUrlPattern = str4;
        this.mUrlType = str5;
    }

    public TKCDNUrl(String str, String str2, String str3, String str4, boolean z) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIp = str3;
        this.mUrlPattern = str4;
        this.mIsFreeTrafficCdn = z;
    }

    public TKCDNUrl(String str, String str2, String str3, String str4, boolean z, Set<Integer> set, String str5, String str6, Map<String, String> map) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mUrlType = str5;
        this.mIsFreeTrafficCdn = z;
        this.mFeature = set;
        this.mPushCdn = str6;
        this.mHeaders = map;
        this.mIp = str3;
        this.mUrlPattern = str4;
    }

    public TKCDNUrl(String str, String str2, boolean z) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIsFreeTrafficCdn = z;
    }

    @Override // com.kwad.sdk.e.a
    public void afterParseJson(@Nullable JSONObject jSONObject) {
        super.afterParseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("feature");
            if (jSONArrayOptJSONArray != null) {
                this.mFeature = new HashSet();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    this.mFeature.add(Integer.valueOf(jSONArrayOptJSONArray.getInt(i)));
                }
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("headers");
            if (jSONObjectOptJSONObject != null) {
                this.mHeaders = new HashMap();
                Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    this.mHeaders.put(next, jSONObjectOptJSONObject.optString(next));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.kwad.sdk.e.a
    public void afterToJson(JSONObject jSONObject) throws JSONException {
        super.afterToJson(jSONObject);
        if (this.mFeature != null) {
            f.a(jSONObject, "feature", f.a(this.mFeature));
        }
        if (this.mHeaders != null) {
            f.a(jSONObject, "headers", f.a(this.mHeaders));
        }
    }

    public String getCdn() {
        return this.mCdn;
    }

    public Set<Integer> getFeature() {
        return this.mFeature;
    }

    public Set<Integer> getFeatures() {
        return this.mFeature;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getIp() {
        return this.mIp;
    }

    public String getPath() {
        try {
            return new URL(this.mUrl).getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return this.mUrl;
        }
    }

    public String getPushCdn() {
        return this.mPushCdn;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getUrlPattern() {
        return this.mUrlPattern;
    }

    public String getUrlType() {
        return this.mUrlType;
    }

    public boolean isFreeTrafficCdn() {
        return this.mIsFreeTrafficCdn;
    }

    public void setFeature(Set<Integer> set) {
        this.mFeature = set;
    }
}
