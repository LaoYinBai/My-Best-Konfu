package com.tachikoma.core.component.imageview;

import androidx.annotation.Nullable;
import com.kwad.sdk.e.f;
import com.tachikoma.core.component.network.delegate.BaseJsonCoreParse;
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

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKCDNUrlInner extends BaseJsonCoreParse {
    private static final String CDN = "cdn";
    private static final String IP = "ip";
    private static final String URL = "url";
    private static final String URL_PATTERN = "urlPattern";
    private static final String URL_TYPE = "urlType";
    private static final long serialVersionUID = -4513265386076164033L;
    public String mCdn;
    public Set<Integer> mFeature;
    public Map<String, String> mHeaders;
    public String mIp;
    public boolean mIsFreeTrafficCdn;
    public String mPushCdn;
    public String mUrl;
    public String mUrlPattern;
    public String mUrlType;

    public TKCDNUrlInner() {
    }

    public TKCDNUrlInner(TKCDNUrlInner tKCDNUrlInner) {
        if (tKCDNUrlInner != null) {
            this.mCdn = tKCDNUrlInner.mCdn;
            this.mUrl = tKCDNUrlInner.mUrl;
            this.mUrlType = tKCDNUrlInner.mUrlType;
            this.mIsFreeTrafficCdn = tKCDNUrlInner.mIsFreeTrafficCdn;
            if (tKCDNUrlInner.mFeature != null) {
                this.mFeature = new HashSet(tKCDNUrlInner.mFeature);
            }
            this.mPushCdn = tKCDNUrlInner.mPushCdn;
            if (tKCDNUrlInner.mHeaders != null) {
                this.mHeaders = new HashMap(tKCDNUrlInner.mHeaders);
            }
            this.mIp = tKCDNUrlInner.mIp;
            this.mUrlPattern = tKCDNUrlInner.mUrlPattern;
        }
    }

    public TKCDNUrlInner(String str, String str2) {
        this.mCdn = str;
        this.mUrl = str2;
    }

    public TKCDNUrlInner(String str, String str2, String str3, String str4) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIp = str3;
        this.mUrlPattern = str4;
    }

    public TKCDNUrlInner(String str, String str2, String str3, String str4, String str5) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIp = str3;
        this.mUrlPattern = str4;
        this.mUrlType = str5;
    }

    public TKCDNUrlInner(String str, String str2, String str3, String str4, boolean z) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIp = str3;
        this.mUrlPattern = str4;
        this.mIsFreeTrafficCdn = z;
    }

    public TKCDNUrlInner(String str, String str2, String str3, String str4, boolean z, Set<Integer> set, String str5, String str6, Map<String, String> map) {
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

    public TKCDNUrlInner(String str, String str2, boolean z) {
        this.mCdn = str;
        this.mUrl = str2;
        this.mIsFreeTrafficCdn = z;
    }

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

    public void afterToJson(JSONObject jSONObject) {
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
