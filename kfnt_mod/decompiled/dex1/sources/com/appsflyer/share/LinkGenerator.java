package com.appsflyer.share;

import android.content.Context;
import anet.channel.util.HttpConstant;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.CreateOneLinkHttpTask;
import com.appsflyer.ServerConfigHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LinkGenerator {

    /* renamed from: ʻ, reason: contains not printable characters */
    private String f300;

    /* renamed from: ʼ, reason: contains not printable characters */
    private String f301;

    /* renamed from: ʽ, reason: contains not printable characters */
    private String f302;

    /* renamed from: ˊ, reason: contains not printable characters */
    private String f303;

    /* renamed from: ˋ, reason: contains not printable characters */
    private String f304;

    /* renamed from: ˋॱ, reason: contains not printable characters */
    private String f305;

    /* renamed from: ˎ, reason: contains not printable characters */
    private String f306;

    /* renamed from: ˏ, reason: contains not printable characters */
    private String f307;

    /* renamed from: ॱ, reason: contains not printable characters */
    private String f310;

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private String f311;

    /* renamed from: ᐝ, reason: contains not printable characters */
    private String f312;

    /* renamed from: ͺ, reason: contains not printable characters */
    private Map<String, String> f309 = new HashMap();

    /* renamed from: ˏॱ, reason: contains not printable characters */
    private Map<String, String> f308 = new HashMap();

    public LinkGenerator(String str) {
        this.f304 = str;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final LinkGenerator m235(String str) {
        this.f312 = str;
        return this;
    }

    public LinkGenerator setDeeplinkPath(String str) {
        this.f302 = str;
        return this;
    }

    public LinkGenerator setBaseDeeplink(String str) {
        this.f305 = str;
        return this;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    final LinkGenerator m236(String str) {
        this.f301 = str;
        return this;
    }

    public LinkGenerator setChannel(String str) {
        this.f307 = str;
        return this;
    }

    public String getChannel() {
        return this.f307;
    }

    public LinkGenerator setReferrerCustomerId(String str) {
        this.f310 = str;
        return this;
    }

    public String getMediaSource() {
        return this.f304;
    }

    public Map<String, String> getParameters() {
        return this.f309;
    }

    public LinkGenerator setCampaign(String str) {
        this.f303 = str;
        return this;
    }

    public String getCampaign() {
        return this.f303;
    }

    public LinkGenerator addParameter(String str, String str2) {
        this.f309.put(str, str2);
        return this;
    }

    public LinkGenerator addParameters(Map<String, String> map) {
        if (map != null) {
            this.f309.putAll(map);
        }
        return this;
    }

    public LinkGenerator setReferrerUID(String str) {
        this.f306 = str;
        return this;
    }

    public LinkGenerator setReferrerName(String str) {
        this.f300 = str;
        return this;
    }

    public LinkGenerator setReferrerImageURL(String str) {
        this.f311 = str;
        return this;
    }

    public LinkGenerator setBaseURL(String str, String str2, String str3) {
        if (str == null || str.length() <= 0) {
            this.f301 = String.format(Constants.AF_BASE_URL_FORMAT, ServerConfigHandler.getUrl(Constants.APPSFLYER_DEFAULT_APP_DOMAIN), str3);
        } else {
            if (str2 == null || str2.length() < 5) {
                str2 = Constants.ONELINK_DEFAULT_DOMAIN;
            }
            this.f301 = String.format(Constants.AF_BASE_URL_FORMAT, str2, str);
        }
        return this;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    private StringBuilder m233() {
        StringBuilder sb = new StringBuilder();
        if (this.f301 != null && this.f301.startsWith(HttpConstant.HTTP)) {
            sb.append(this.f301);
        } else {
            sb.append(ServerConfigHandler.getUrl(Constants.BASE_URL_APP_APPSFLYER_COM));
        }
        if (this.f312 != null) {
            sb.append('/');
            sb.append(this.f312);
        }
        this.f308.put(Constants.URL_MEDIA_SOURCE, this.f304);
        sb.append('?');
        sb.append("pid=");
        sb.append(m234(this.f304, "media source"));
        if (this.f306 != null) {
            this.f308.put(Constants.URL_REFERRER_UID, this.f306);
            sb.append('&');
            sb.append("af_referrer_uid=");
            sb.append(m234(this.f306, "referrerUID"));
        }
        if (this.f307 != null) {
            this.f308.put("af_channel", this.f307);
            sb.append('&');
            sb.append("af_channel=");
            sb.append(m234(this.f307, "channel"));
        }
        if (this.f310 != null) {
            this.f308.put(Constants.URL_REFERRER_CUSTOMER_ID, this.f310);
            sb.append('&');
            sb.append("af_referrer_customer_id=");
            sb.append(m234(this.f310, "referrerCustomerId"));
        }
        if (this.f303 != null) {
            this.f308.put(Constants.URL_CAMPAIGN, this.f303);
            sb.append('&');
            sb.append("c=");
            sb.append(m234(this.f303, "campaign"));
        }
        if (this.f300 != null) {
            this.f308.put(Constants.URL_REFERRER_NAME, this.f300);
            sb.append('&');
            sb.append("af_referrer_name=");
            sb.append(m234(this.f300, "referrerName"));
        }
        if (this.f311 != null) {
            this.f308.put(Constants.URL_REFERRER_IMAGE_URL, this.f311);
            sb.append('&');
            sb.append("af_referrer_image_url=");
            sb.append(m234(this.f311, "referrerImageURL"));
        }
        if (this.f305 != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.f305);
            sb2.append(this.f305.endsWith(Constants.URL_PATH_DELIMITER) ? "" : Constants.URL_PATH_DELIMITER);
            if (this.f302 != null) {
                sb2.append(this.f302);
            }
            this.f308.put(Constants.URL_BASE_DEEPLINK, sb2.toString());
            sb.append('&');
            sb.append("af_dp=");
            sb.append(m234(this.f305, "baseDeeplink"));
            if (this.f302 != null) {
                sb.append(this.f305.endsWith(Constants.URL_PATH_DELIMITER) ? "" : "%2F");
                sb.append(m234(this.f302, "deeplinkPath"));
            }
        }
        for (String str : this.f309.keySet()) {
            String string = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append("=");
            sb3.append(m234(this.f309.get(str), str));
            if (!string.contains(sb3.toString())) {
                sb.append('&');
                sb.append(str);
                sb.append('=');
                sb.append(m234(this.f309.get(str), str));
            }
        }
        return sb;
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static String m234(String str, String str2) {
        try {
            return URLEncoder.encode(str, "utf8");
        } catch (UnsupportedEncodingException unused) {
            StringBuilder sb = new StringBuilder("Illegal ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            AFLogger.afInfoLog(sb.toString());
            return "";
        } catch (Throwable unused2) {
            return "";
        }
    }

    public String generateLink() {
        return m233().toString();
    }

    public void generateLink(Context context, CreateOneLinkHttpTask.ResponseListener responseListener) {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
        if (!this.f309.isEmpty()) {
            for (Map.Entry<String, String> entry : this.f309.entrySet()) {
                this.f308.put(entry.getKey(), entry.getValue());
            }
        }
        m233();
        ShareInviteHelper.generateUserInviteLink(context, string, this.f308, responseListener);
    }
}
