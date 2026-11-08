package com.bytedance.sdk.account.platform.onekey;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OnekeyLoginConfig {
    public static final long OVER_TIME = 3000;
    private CMSettingConfig mCMSettingConfig;
    private CTSettingConfig mCTSettingConfig;
    private CUSettingConfig mCUSettingConfig;
    private IOnekeyMonitor mMonitor;

    public OnekeyLoginConfig(IOnekeyMonitor iOnekeyMonitor) {
        this.mMonitor = iOnekeyMonitor;
    }

    public OnekeyLoginConfig setCTSetting(String str, String str2) {
        this.mCTSettingConfig = new CTSettingConfig(str, str2);
        return this;
    }

    protected IOnekeyMonitor getMonitor() {
        return this.mMonitor;
    }

    public OnekeyLoginConfig setCMSetting(String str, String str2) {
        this.mCMSettingConfig = new CMSettingConfig(str, str2);
        return this;
    }

    public OnekeyLoginConfig setCUSetting(String str, String str2) {
        this.mCUSettingConfig = new CUSettingConfig(str, str2);
        return this;
    }

    protected CTSettingConfig getCTSettingConfig() {
        return this.mCTSettingConfig;
    }

    protected CMSettingConfig getCMSettingConfig() {
        return this.mCMSettingConfig;
    }

    protected CUSettingConfig getCUSettingConfig() {
        return this.mCUSettingConfig;
    }

    public static class CMSettingConfig {
        public String mCMAppId;
        public String mCMAppKey;
        public long mOverTime;

        public CMSettingConfig(String str, String str2) {
            this.mCMAppId = str;
            this.mCMAppKey = str2;
        }
    }

    public static class CTSettingConfig {
        public String mCTAppKey;
        public String mCTAppSecret;
        private boolean mIsShowLog;
        private String mLogTag;

        public CTSettingConfig(String str, String str2) {
            this.mCTAppKey = str;
            this.mCTAppSecret = str2;
        }

        public boolean isShowLog() {
            return this.mIsShowLog;
        }

        public void setShowLog(boolean z) {
            this.mIsShowLog = z;
        }

        public String getLogTag() {
            return this.mLogTag;
        }

        public void setLogTag(String str) {
            this.mLogTag = str;
        }
    }

    public static class CUSettingConfig {
        public String mCUAppId;
        public String mCUAppSecret;

        public CUSettingConfig(String str, String str2) {
            this.mCUAppId = str;
            this.mCUAppSecret = str2;
        }
    }
}
