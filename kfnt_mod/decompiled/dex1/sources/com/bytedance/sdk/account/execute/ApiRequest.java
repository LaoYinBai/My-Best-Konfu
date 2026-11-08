package com.bytedance.sdk.account.execute;

import android.text.TextUtils;
import com.ss.android.account.TTAccountInit;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ApiRequest {
    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public final String method;
    public final Map<String, String> parms;
    public final String url;

    public ApiRequest(String str, String str2, Map<String, String> map) {
        this.url = str;
        this.method = str2;
        this.parms = map;
    }

    public String parameter(String str) {
        if (this.parms == null) {
            return null;
        }
        return this.parms.get(str);
    }

    public static class Builder {
        private String method;
        private Map<String, String> parms;
        private String url;

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder parameter(String str, String str2) {
            if (this.parms == null) {
                this.parms = new HashMap();
            }
            this.parms.put(str, str2);
            return this;
        }

        public Builder parameters(Map<String, String> map) {
            if (this.parms == null) {
                this.parms = map;
            } else if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.parms.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public Builder parameters(Map<String, String> map, Map<String, String> map2) {
            if (this.parms == null) {
                this.parms = new HashMap();
            }
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.parms.put(entry.getKey(), entry.getValue());
                }
            }
            if (map2 != null && !map2.isEmpty()) {
                for (String str : map2.keySet()) {
                    if (!TextUtils.isEmpty(str) && map2.get(str) != null) {
                        this.parms.put(str, map2.get(str));
                    }
                }
            }
            return this;
        }

        public Builder checkSupportMultiLogin() {
            if (this.parms == null) {
                this.parms = new HashMap();
            }
            if (TTAccountInit.getConfig().isSupportMultiLogin()) {
                this.parms.put("multi_login", "1");
            }
            return this;
        }

        public ApiRequest get() {
            this.method = ApiRequest.METHOD_GET;
            return new ApiRequest(this.url, this.method, this.parms);
        }

        public ApiRequest post() {
            this.method = ApiRequest.METHOD_POST;
            return new ApiRequest(this.url, this.method, this.parms);
        }
    }
}
