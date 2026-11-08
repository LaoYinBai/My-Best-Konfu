package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetAvailableWaysResponse extends BaseApiResponse {
    private String email;
    private boolean hasEmail;
    private boolean hasMobile;
    private boolean hasOauth;
    private boolean hasPwd;
    private boolean isMostDevice;
    private String mobile;
    private List<String> oauthPlatforms;
    private String token;

    public GetAvailableWaysResponse(boolean z, int i) {
        super(z, i);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public boolean isHasEmail() {
        return this.hasEmail;
    }

    public void setHasEmail(boolean z) {
        this.hasEmail = z;
    }

    public boolean isHasMobile() {
        return this.hasMobile;
    }

    public void setHasMobile(boolean z) {
        this.hasMobile = z;
    }

    public boolean isHasOauth() {
        return this.hasOauth;
    }

    public void setHasOauth(boolean z) {
        this.hasOauth = z;
    }

    public boolean isHasPwd() {
        return this.hasPwd;
    }

    public void setHasPwd(boolean z) {
        this.hasPwd = z;
    }

    public boolean isMostDevice() {
        return this.isMostDevice;
    }

    public void setMostDevice(boolean z) {
        this.isMostDevice = z;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public List<String> getOauthPlatforms() {
        return this.oauthPlatforms;
    }

    public void setOauthPlatforms(List<String> list) {
        this.oauthPlatforms = list;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String toString() {
        return "GetAvailableWaysResponse{email='" + this.email + "', hasEmail=" + this.hasEmail + ", hasMobile=" + this.hasMobile + ", hasOauth=" + this.hasOauth + ", hasPwd=" + this.hasPwd + ", isMostDevice=" + this.isMostDevice + ", mobile='" + this.mobile + "', oauthPlatforms=" + this.oauthPlatforms + ", token='" + this.token + "'}";
    }
}
