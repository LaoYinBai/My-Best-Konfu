package com.taobao.accs;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.w;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AccsClientConfig implements Serializable {
    public static final String DEFAULT_CONFIGTAG = "default";
    public static final int SECURITY_OFF = 2;
    public static final int SECURITY_OPEN = 1;
    public static final int SECURITY_TAOBAO = 0;
    private static final String TAG = "AccsClientConfig";
    public static boolean loadedStaticConfig;
    private static Context mContext;
    private boolean mAccsHeartbeatEnable;
    private String mAppKey;
    private String mAppSecret;
    private String mAuthCode;
    private boolean mAutoUnit;
    private String mChannelHost;
    private int mChannelPubKey;
    private int mConfigEnv;
    private boolean mDisableChannel;
    private String mInappHost;
    private int mInappPubKey;
    private boolean mKeepalive;
    private boolean mQuickReconnect;
    private int mSecurity;
    private String mStoreId;
    private String mTag;
    public static final String[] DEFAULT_CENTER_HOSTS = {"msgacs.m.taobao.com", "msgacs.wapa.taobao.com", "msgacs.waptest.taobao.com"};
    private static final String[] DEFAULT_CHANNEL_HOSTS = {"accscdn.m.taobao.com", "acs.wapa.taobao.com", "acs.waptest.taobao.com"};

    @ENV
    public static int mEnv = 0;
    public static Map<String, AccsClientConfig> mReleaseConfigs = new ConcurrentHashMap(1);
    public static Map<String, AccsClientConfig> mPreviewConfigs = new ConcurrentHashMap(1);
    public static Map<String, AccsClientConfig> mDebugConfigs = new ConcurrentHashMap(1);

    @Retention(RetentionPolicy.CLASS)
    public @interface ENV {
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface SECURITY_TYPE {
    }

    static {
        int i;
        loadedStaticConfig = false;
        boolean z = true;
        try {
            Bundle bundleG = w.g(getContext());
            if (bundleG != null) {
                String str = null;
                String string = bundleG.getString("accsConfigTags", null);
                ALog.i(TAG, "init config from xml", "configtags", string);
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                String[] strArrSplit = string.split("\\|");
                if (strArrSplit == null) {
                    strArrSplit = new String[]{string};
                }
                int length = strArrSplit.length;
                int i2 = 0;
                while (i2 < length) {
                    String str2 = strArrSplit[i2];
                    if (TextUtils.isEmpty(str2)) {
                        i = length;
                    } else {
                        int i3 = bundleG.getInt(str2 + "_accsAppkey", -1);
                        String strValueOf = i3 < 0 ? str : String.valueOf(i3);
                        String string2 = bundleG.getString(str2 + "_accsAppSecret");
                        String string3 = bundleG.getString(str2 + "_authCode");
                        boolean z2 = bundleG.getBoolean(str2 + "_keepAlive", z);
                        boolean z3 = bundleG.getBoolean(str2 + "_autoUnit", z);
                        int i4 = bundleG.getInt(str2 + "_inappPubkey", -1);
                        int i5 = bundleG.getInt(str2 + "_channelPubkey", -1);
                        String string4 = bundleG.getString(str2 + "_inappHost");
                        String string5 = bundleG.getString(str2 + "_channelHost");
                        int i6 = bundleG.getInt(str2 + "_configEnv", 0);
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        i = length;
                        sb.append("_disableChannel");
                        boolean z4 = bundleG.getBoolean(sb.toString());
                        if (!TextUtils.isEmpty(strValueOf)) {
                            new Builder().setTag(str2).setConfigEnv(i6).setAppKey(strValueOf).setAppSecret(string2).setAutoCode(string3).setKeepAlive(z2).setAutoUnit(z3).setInappHost(string4).setInappPubKey(i4).setChannelHost(string5).setChannelPubKey(i5).setDisableChannel(z4).build();
                            ALog.i(TAG, "init config from xml", new Object[0]);
                        }
                    }
                    i2++;
                    length = i;
                    z = true;
                    str = null;
                }
                loadedStaticConfig = true;
            }
        } catch (Throwable th) {
            ALog.e(TAG, "init config from xml", th, new Object[0]);
        }
    }

    public static Context getContext() {
        if (mContext != null) {
            return mContext;
        }
        synchronized (AccsClientConfig.class) {
            if (mContext != null) {
                return mContext;
            }
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(cls, new Object[0]);
                mContext = (Context) objInvoke.getClass().getMethod("getApplication", new Class[0]).invoke(objInvoke, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mContext;
        }
    }

    protected AccsClientConfig() {
    }

    @Deprecated
    public static AccsClientConfig getConfig(String str) {
        Map<String, AccsClientConfig> map;
        switch (mEnv) {
            case 1:
                map = mPreviewConfigs;
                break;
            case 2:
                map = mDebugConfigs;
                break;
            default:
                map = mReleaseConfigs;
                break;
        }
        for (AccsClientConfig accsClientConfig : map.values()) {
            if (accsClientConfig.mAppKey.equals(str) && accsClientConfig.mConfigEnv == mEnv) {
                return accsClientConfig;
            }
        }
        ALog.e(TAG, "getConfigByTag return null", "appkey", str);
        return null;
    }

    public static AccsClientConfig getConfigByTag(String str) {
        AccsClientConfig accsClientConfig;
        switch (mEnv) {
            case 0:
                accsClientConfig = mReleaseConfigs.get(str);
                break;
            case 1:
                accsClientConfig = mPreviewConfigs.get(str);
                break;
            case 2:
                accsClientConfig = mDebugConfigs.get(str);
                break;
            default:
                accsClientConfig = mReleaseConfigs.get(str);
                break;
        }
        if (accsClientConfig == null) {
            ALog.e(TAG, "getConfigByTag return null", Constants.KEY_CONFIG_TAG, str);
        }
        return accsClientConfig;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public String getAppSecret() {
        return this.mAppSecret;
    }

    public String getInappHost() {
        return this.mInappHost;
    }

    public String getChannelHost() {
        return this.mChannelHost;
    }

    public int getSecurity() {
        return this.mSecurity;
    }

    public String getAuthCode() {
        return this.mAuthCode;
    }

    public int getInappPubKey() {
        return this.mInappPubKey;
    }

    public int getChannelPubKey() {
        return this.mChannelPubKey;
    }

    public boolean isKeepalive() {
        return this.mKeepalive;
    }

    public boolean isAutoUnit() {
        return this.mAutoUnit;
    }

    public String getTag() {
        return this.mTag;
    }

    public int getConfigEnv() {
        return this.mConfigEnv;
    }

    public boolean getDisableChannel() {
        return this.mDisableChannel;
    }

    public boolean isQuickReconnect() {
        return this.mQuickReconnect;
    }

    public String getStoreId() {
        return this.mStoreId;
    }

    public boolean isAccsHeartbeatEnable() {
        return this.mAccsHeartbeatEnable;
    }

    public String toString() {
        return "AccsClientConfig{Tag=" + this.mTag + ", ConfigEnv=" + this.mConfigEnv + ", AppKey=" + this.mAppKey + ", AppSecret=" + this.mAppSecret + ", InappHost=" + this.mInappHost + ", ChannelHost=" + this.mChannelHost + ", Security=" + this.mSecurity + ", AuthCode=" + this.mAuthCode + ", InappPubKey=" + this.mInappPubKey + ", ChannelPubKey=" + this.mChannelPubKey + ", Keepalive=" + this.mKeepalive + ", AutoUnit=" + this.mAutoUnit + ", DisableChannel=" + this.mDisableChannel + ", QuickReconnect=" + this.mQuickReconnect + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccsClientConfig)) {
            return false;
        }
        AccsClientConfig accsClientConfig = (AccsClientConfig) obj;
        return this.mSecurity == accsClientConfig.mSecurity && this.mInappPubKey == accsClientConfig.mInappPubKey && this.mChannelPubKey == accsClientConfig.mChannelPubKey && this.mKeepalive == accsClientConfig.mKeepalive && this.mAutoUnit == accsClientConfig.mAutoUnit && this.mConfigEnv == accsClientConfig.mConfigEnv && this.mDisableChannel == accsClientConfig.mDisableChannel && this.mQuickReconnect == accsClientConfig.mQuickReconnect && this.mAccsHeartbeatEnable == accsClientConfig.mAccsHeartbeatEnable && this.mAppKey.equals(accsClientConfig.mAppKey) && this.mAppSecret.equals(accsClientConfig.mAppSecret) && this.mInappHost.equals(accsClientConfig.mInappHost) && this.mChannelHost.equals(accsClientConfig.mChannelHost) && this.mStoreId.equals(accsClientConfig.mStoreId) && this.mAuthCode.equals(accsClientConfig.mAuthCode) && this.mTag.equals(accsClientConfig.mTag);
    }

    public static void setAccsConfig(int i, AccsClientConfig accsClientConfig) {
        Map<String, AccsClientConfig> map;
        switch (i) {
            case 1:
                map = mPreviewConfigs;
                break;
            case 2:
                map = mDebugConfigs;
                break;
            default:
                map = mReleaseConfigs;
                break;
        }
        AccsClientConfig accsClientConfig2 = map.get(accsClientConfig.getTag());
        if (accsClientConfig2 != null) {
            ALog.w(TAG, "build conver", "old config", accsClientConfig2);
        }
        map.put(accsClientConfig.getTag(), accsClientConfig);
    }

    public static class Builder {
        private String mAppKey = "";
        private String mTag = "";
        private String mAppSecret = "";
        private String mInappHost = "";
        private String mChannelHost = "";
        private String mAuthCode = "";
        private String mStoreId = "";
        private int mInappPubKey = -1;
        private int mChannelPubKey = -1;
        private boolean mKeepalive = true;
        private boolean mAutoUnit = true;
        private int mConfigEnv = -1;
        private boolean mDisableChannel = false;
        private boolean mQuickReconnect = false;
        private boolean mAccsHeartbeatEnable = false;

        public Builder setAppKey(String str) {
            this.mAppKey = str;
            return this;
        }

        public Builder setAppSecret(String str) {
            this.mAppSecret = str;
            return this;
        }

        public Builder setInappHost(String str) {
            this.mInappHost = str;
            return this;
        }

        public Builder setChannelHost(String str) {
            this.mChannelHost = str;
            return this;
        }

        public Builder setAutoCode(String str) {
            this.mAuthCode = str;
            return this;
        }

        public Builder setInappPubKey(int i) {
            this.mInappPubKey = i;
            return this;
        }

        public Builder setChannelPubKey(int i) {
            this.mChannelPubKey = i;
            return this;
        }

        public Builder setKeepAlive(boolean z) {
            this.mKeepalive = z;
            return this;
        }

        public Builder setAutoUnit(boolean z) {
            this.mAutoUnit = z;
            return this;
        }

        public Builder setConfigEnv(@ENV int i) {
            this.mConfigEnv = i;
            return this;
        }

        public Builder setStoreId(String str) {
            this.mStoreId = str;
            return this;
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public Builder setDisableChannel(boolean z) {
            this.mDisableChannel = z;
            return this;
        }

        public Builder setQuickReconnect(boolean z) {
            this.mQuickReconnect = z;
            return this;
        }

        public Builder setAccsHeartbeatEnable(boolean z) {
            this.mAccsHeartbeatEnable = z;
            return this;
        }

        public AccsClientConfig build() throws AccsException {
            Map<String, AccsClientConfig> map;
            if (TextUtils.isEmpty(this.mAppKey)) {
                throw new AccsException("appkey null");
            }
            AccsClientConfig accsClientConfig = new AccsClientConfig();
            accsClientConfig.mAppKey = this.mAppKey;
            accsClientConfig.mAppSecret = this.mAppSecret;
            accsClientConfig.mAuthCode = this.mAuthCode;
            accsClientConfig.mKeepalive = this.mKeepalive;
            accsClientConfig.mAutoUnit = this.mAutoUnit;
            accsClientConfig.mInappPubKey = this.mInappPubKey;
            accsClientConfig.mChannelPubKey = this.mChannelPubKey;
            accsClientConfig.mInappHost = this.mInappHost;
            accsClientConfig.mChannelHost = this.mChannelHost;
            accsClientConfig.mTag = this.mTag;
            accsClientConfig.mStoreId = this.mStoreId;
            accsClientConfig.mConfigEnv = this.mConfigEnv;
            accsClientConfig.mDisableChannel = this.mDisableChannel;
            accsClientConfig.mQuickReconnect = this.mQuickReconnect;
            accsClientConfig.mAccsHeartbeatEnable = this.mAccsHeartbeatEnable;
            if (accsClientConfig.mConfigEnv < 0) {
                accsClientConfig.mConfigEnv = AccsClientConfig.mEnv;
            }
            if (TextUtils.isEmpty(accsClientConfig.mAppSecret)) {
                accsClientConfig.mSecurity = 0;
            } else {
                accsClientConfig.mSecurity = 2;
            }
            if (TextUtils.isEmpty(accsClientConfig.mInappHost)) {
                accsClientConfig.mInappHost = AccsClientConfig.DEFAULT_CENTER_HOSTS[accsClientConfig.mConfigEnv];
            }
            if (TextUtils.isEmpty(accsClientConfig.mChannelHost)) {
                accsClientConfig.mChannelHost = AccsClientConfig.DEFAULT_CHANNEL_HOSTS[accsClientConfig.mConfigEnv];
            }
            if (TextUtils.isEmpty(accsClientConfig.mTag)) {
                accsClientConfig.mTag = accsClientConfig.mAppKey;
            }
            switch (accsClientConfig.mConfigEnv) {
                case 1:
                    map = AccsClientConfig.mPreviewConfigs;
                    break;
                case 2:
                    map = AccsClientConfig.mDebugConfigs;
                    break;
                default:
                    map = AccsClientConfig.mReleaseConfigs;
                    break;
            }
            ALog.d(AccsClientConfig.TAG, "build", "config", accsClientConfig);
            AccsClientConfig accsClientConfig2 = map.get(accsClientConfig.getTag());
            if (accsClientConfig2 != null) {
                ALog.w(AccsClientConfig.TAG, "build conver", "old config", accsClientConfig2);
            }
            map.put(accsClientConfig.getTag(), accsClientConfig);
            return accsClientConfig;
        }
    }
}
