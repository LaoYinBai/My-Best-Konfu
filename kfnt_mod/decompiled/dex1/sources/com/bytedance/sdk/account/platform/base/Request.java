package com.bytedance.sdk.account.platform.base;

import android.net.Uri;
import android.os.Bundle;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Request {
    public String callerLocalEntry;
    public Bundle extra;
    public Set<String> optionalScopes0;
    public Set<String> optionalScopes1;
    public Uri redirectUri;
    public String responseType;
    public Set<String> scopes;
    public String state;

    public interface ResponseType {
        public static final String CODE = "code";
        public static final String ID_TOKEN = "id_token";
        public static final String TOKEN = "token";
    }

    public class Builder {
        public String callerLocalEntry;
        public Bundle extra;
        public Set<String> optionalScopes0;
        public Set<String> optionalScopes1;
        private Uri redirectUri;
        public Set<String> scopes;
        public String state;

        public Builder() {
        }

        public Builder setScopes(Set<String> set) {
            this.scopes = set;
            return this;
        }

        public Builder setOptionalScopes0(Set<String> set) {
            this.optionalScopes0 = set;
            return this;
        }

        public Builder setOptionalScopes1(Set<String> set) {
            this.optionalScopes1 = set;
            return this;
        }

        public Builder setState(String str) {
            this.state = str;
            return this;
        }

        public Builder setCallerLocalEntry(String str) {
            this.callerLocalEntry = str;
            return this;
        }

        public Builder setExtra(Bundle bundle) {
            this.extra = bundle;
            return this;
        }

        public Builder setRedirectUri(Uri uri) {
            this.redirectUri = uri;
            return this;
        }

        public Request build() {
            Request request = new Request();
            request.scopes = this.scopes;
            request.optionalScopes0 = this.optionalScopes0;
            request.optionalScopes1 = this.optionalScopes1;
            request.state = this.state;
            request.callerLocalEntry = this.callerLocalEntry;
            request.extra = this.extra;
            request.redirectUri = this.redirectUri;
            return request;
        }
    }
}
