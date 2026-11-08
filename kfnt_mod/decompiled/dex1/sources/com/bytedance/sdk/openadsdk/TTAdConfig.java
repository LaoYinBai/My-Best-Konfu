package com.bytedance.sdk.openadsdk;

import com.bytedance.sdk.openadsdk.AdConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class TTAdConfig implements AdConfig {

    /* renamed from: a, reason: collision with root package name */
    private String f5166a;

    /* renamed from: b, reason: collision with root package name */
    private String f5167b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f5168c;
    private String d;
    private String e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int[] j;
    private boolean k;
    private boolean l;
    private String[] m;
    private boolean n;
    private Map<String, Object> o;
    private TTCustomController p;
    private int q;

    private TTAdConfig() {
        this.f5168c = false;
        this.f = 0;
        this.g = true;
        this.h = false;
        this.i = false;
        this.k = false;
        this.l = false;
        this.n = false;
        this.o = new HashMap();
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getAppId() {
        return this.f5166a;
    }

    public void setAppId(String str) {
        this.f5166a = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getAppName() {
        return this.f5167b;
    }

    public void setAppName(String str) {
        this.f5167b = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isPaid() {
        return this.f5168c;
    }

    public void setPaid(boolean z) {
        this.f5168c = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getKeywords() {
        return this.d;
    }

    public void setKeywords(String str) {
        this.d = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getData() {
        return this.e;
    }

    public void setData(String str) {
        this.e = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public int getTitleBarTheme() {
        return this.f;
    }

    public void setTitleBarTheme(int i) {
        this.f = i;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isAllowShowNotify() {
        return this.g;
    }

    public void setAllowShowNotify(boolean z) {
        this.g = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isDebug() {
        return this.h;
    }

    public void setDebug(boolean z) {
        this.h = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isAllowShowPageWhenScreenLock() {
        return this.i;
    }

    public void setAllowShowPageWhenScreenLock(boolean z) {
        this.i = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public int[] getDirectDownloadNetworkType() {
        return this.j;
    }

    public void setDirectDownloadNetworkType(int... iArr) {
        this.j = iArr;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isUseTextureView() {
        return this.k;
    }

    public void setUseTextureView(boolean z) {
        this.k = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isSupportMultiProcess() {
        return this.l;
    }

    public void setSupportMultiProcess(boolean z) {
        this.l = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String[] getNeedClearTaskReset() {
        return this.m;
    }

    public void setNeedClearTaskReset(String... strArr) {
        this.m = strArr;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isAsyncInit() {
        return this.n;
    }

    public void setAsyncInit(boolean z) {
        this.n = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public TTCustomController getCustomController() {
        return this.p;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    @Deprecated
    public Object getExtra(String str) {
        return this.o.get(str);
    }

    public void setThemeStatus(int i) {
        this.q = i;
    }

    public int getThemeStatus() {
        return this.q;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public Object removeExtra(String str) {
        return this.o.remove(str);
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    @Deprecated
    public void setExtra(String str, Object obj) {
        this.o.put(str, obj);
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public AdConfig.SdkInfo getSdkInfo() {
        return new AdConfig.SdkInfo() { // from class: com.bytedance.sdk.openadsdk.TTAdConfig.1
            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public boolean isPlugin() {
                return true;
            }

            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public String pluginName() {
                return TTAdConstant.BUILT_IN_PLUGIN_NAME;
            }

            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public int sdkVersionCode() {
                return 4021;
            }

            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public String sdkVersionName() {
                return "4.0.2.1";
            }
        };
    }

    public void setCustomController(TTCustomController tTCustomController) {
        this.p = tTCustomController;
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f5170a;

        /* renamed from: b, reason: collision with root package name */
        private String f5171b;
        private String d;
        private String e;
        private int[] j;
        private String[] m;
        private TTCustomController o;
        private int p;

        /* renamed from: c, reason: collision with root package name */
        private boolean f5172c = false;
        private int f = 0;
        private boolean g = true;
        private boolean h = false;
        private boolean i = false;
        private boolean k = false;
        private boolean l = false;
        private boolean n = false;
        private int q = 2;

        public Builder appId(String str) {
            this.f5170a = str;
            return this;
        }

        public Builder appName(String str) {
            this.f5171b = str;
            return this;
        }

        public Builder paid(boolean z) {
            this.f5172c = z;
            return this;
        }

        public Builder keywords(String str) {
            this.d = str;
            return this;
        }

        public Builder data(String str) {
            this.e = str;
            return this;
        }

        public Builder titleBarTheme(int i) {
            this.f = i;
            return this;
        }

        public Builder allowShowNotify(boolean z) {
            this.g = z;
            return this;
        }

        public Builder debug(boolean z) {
            this.h = z;
            return this;
        }

        @Deprecated
        public Builder allowShowPageWhenScreenLock(boolean z) {
            this.i = z;
            return this;
        }

        public Builder directDownloadNetworkType(int... iArr) {
            this.j = iArr;
            return this;
        }

        public Builder useTextureView(boolean z) {
            this.k = z;
            return this;
        }

        public Builder supportMultiProcess(boolean z) {
            this.l = z;
            return this;
        }

        public Builder needClearTaskReset(String... strArr) {
            this.m = strArr;
            return this;
        }

        public Builder asyncInit(boolean z) {
            this.n = z;
            return this;
        }

        public Builder customController(TTCustomController tTCustomController) {
            this.o = tTCustomController;
            return this;
        }

        public Builder themeStatus(int i) {
            this.p = i;
            return this;
        }

        public Builder setPluginUpdateConfig(int i) {
            this.q = i;
            return this;
        }

        public TTAdConfig build() {
            TTAdConfig tTAdConfig = new TTAdConfig();
            tTAdConfig.setAppId(this.f5170a);
            tTAdConfig.setAppName(this.f5171b);
            tTAdConfig.setPaid(this.f5172c);
            tTAdConfig.setKeywords(this.d);
            tTAdConfig.setData(this.e);
            tTAdConfig.setTitleBarTheme(this.f);
            tTAdConfig.setAllowShowNotify(this.g);
            tTAdConfig.setDebug(this.h);
            tTAdConfig.setAllowShowPageWhenScreenLock(this.i);
            tTAdConfig.setDirectDownloadNetworkType(this.j);
            tTAdConfig.setUseTextureView(this.k);
            tTAdConfig.setSupportMultiProcess(this.l);
            tTAdConfig.setNeedClearTaskReset(this.m);
            tTAdConfig.setAsyncInit(this.n);
            tTAdConfig.setCustomController(this.o);
            tTAdConfig.setThemeStatus(this.p);
            tTAdConfig.setExtra("plugin_update_conf", Integer.valueOf(this.q));
            return tTAdConfig;
        }
    }
}
