package com.cgs.out;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CGConfig {
    public String mAppID = "";
    public String mDeviceID = "";
    public String mInstallID = "";
    public String mUserID = "";
    public String mChannel = "";
    public String mConfigStr = "";
    public int mRegionType = CGISDK.REGION_UNSET;
    public CGExtend mExtendInterface = null;
    public Map<String, String> mCustomInfo = new HashMap();

    public static class Builder extends CGConfig {
        public Builder() {
            super(null);
        }

        public CGConfig build() {
            int i = this.mRegionType;
            if (i == 100 || i == 200) {
                return this;
            }
            throw new IllegalArgumentException(String.format("region:%d type must be set!", Integer.valueOf(i)));
        }

        public Builder setApplicationId(String str) {
            this.mAppID = CGConfig.strFmt(str);
            return this;
        }

        public Builder setChannel(String str) {
            this.mChannel = CGConfig.strFmt(str);
            return this;
        }

        public Builder setConfigStr(String str) {
            this.mConfigStr = CGConfig.strFmt(str);
            return this;
        }

        public Builder setDeviceId(String str) {
            this.mDeviceID = CGConfig.strFmt(str);
            return this;
        }

        public Builder setExtendInterface(CGExtend cGExtend) {
            this.mExtendInterface = cGExtend;
            return this;
        }

        public Builder setInstallId(String str) {
            this.mInstallID = CGConfig.strFmt(str);
            return this;
        }

        public Builder setRegionType(int i) {
            this.mRegionType = i;
            return this;
        }

        public Builder setUserId(String str) {
            this.mUserID = CGConfig.strFmt(str);
            return this;
        }
    }

    public CGConfig() {
    }

    public /* synthetic */ CGConfig(a aVar) {
    }

    public static String strFmt(String str) {
        return (str == null || str.length() <= 0) ? "<unset>" : str;
    }

    public String a(String str) {
        return str == null ? "" : str.trim();
    }

    public String[] a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(a(this.mAppID));
        arrayList.add(a(this.mDeviceID));
        arrayList.add(a(this.mInstallID));
        arrayList.add(a(this.mUserID));
        arrayList.add(a(this.mChannel));
        arrayList.add(a(this.mConfigStr));
        arrayList.add(a("" + this.mRegionType));
        for (Map.Entry<String, String> entry : this.mCustomInfo.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey())) {
                arrayList.add(a(entry.getKey()));
                arrayList.add(a(entry.getValue()));
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }
}
