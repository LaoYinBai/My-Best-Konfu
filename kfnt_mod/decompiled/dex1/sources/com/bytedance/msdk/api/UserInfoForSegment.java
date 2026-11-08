package com.bytedance.msdk.api;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.msdk.api.v2.GMConfigUserInfoForSegment;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UserInfoForSegment {
    public static final String GENDER_FEMALE = "female";
    public static final String GENDER_MALE = "male";
    public static final String GENDER_UNKNOWN = "unknown";
    public final String TAG = "TTMediationSDK";

    /* renamed from: a, reason: collision with root package name */
    private String f4306a = "";

    /* renamed from: b, reason: collision with root package name */
    private String f4307b = "";

    /* renamed from: c, reason: collision with root package name */
    private String f4308c = "";
    private int d = 0;
    private String e = "";
    private String f = "";
    private Map<String, String> g;

    public boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (obj == null || UserInfoForSegment.class != obj.getClass()) {
            return false;
        }
        UserInfoForSegment userInfoForSegment = (UserInfoForSegment) obj;
        boolean z2 = getAge() == userInfoForSegment.getAge() && TextUtils.equals(getUserValueGroup(), userInfoForSegment.getUserValueGroup()) && TextUtils.equals(getUserId(), userInfoForSegment.getUserId()) && TextUtils.equals(getChannel(), userInfoForSegment.getChannel()) && TextUtils.equals(getSubChannel(), userInfoForSegment.getSubChannel()) && TextUtils.equals(getGender(), userInfoForSegment.getGender());
        Map<String, String> customInfos = userInfoForSegment.getCustomInfos();
        Map<String, String> map = this.g;
        if (map != null && customInfos != null) {
            if (map.size() == customInfos.size()) {
                for (String str : this.g.keySet()) {
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(this.g.get(str), customInfos.get(str))) {
                    }
                }
                z = true;
            }
            z = false;
            break;
        }
        if (this.g != null || customInfos != null) {
            z = false;
            break;
        }
        z = true;
        return z2 && z;
    }

    public int getAge() {
        return this.d;
    }

    public String getChannel() {
        return this.f4307b;
    }

    public Map<String, String> getCustomInfos() {
        return this.g;
    }

    public String getGender() {
        return this.e;
    }

    public String getSubChannel() {
        return this.f4308c;
    }

    public String getUserId() {
        return this.f4306a;
    }

    public String getUserValueGroup() {
        return this.f;
    }

    public void setAge(int i) {
        this.d = i;
    }

    public void setChannel(String str) {
        if (GMConfigUserInfoForSegment.checkValid(str)) {
            this.f4307b = str;
        } else {
            Log.e("TTMediationSDK", "流量分组channer字段存在不合法输入");
        }
    }

    public void setCustomInfos(Map<String, String> map) {
        this.g = new HashMap();
        if (map == null || map.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null) {
                if (!GMConfigUserInfoForSegment.checkValid(entry.getKey())) {
                    Log.e("TTMediationSDK", "流量分组" + entry.getKey() + "字段存在不合法输入");
                } else if (GMConfigUserInfoForSegment.checkValid(entry.getValue())) {
                    this.g.put(entry.getKey(), entry.getValue());
                } else {
                    Log.e("TTMediationSDK", "流量分组" + entry.getKey() + "字段的值" + entry.getValue() + "存在不合法输入");
                }
            }
        }
    }

    public void setGender(String str) {
        if (GMConfigUserInfoForSegment.checkValid(str)) {
            this.e = str;
        } else {
            Log.e("TTMediationSDK", "流量分组gender字段存在不合法输入");
        }
    }

    public void setSubChannel(String str) {
        if (GMConfigUserInfoForSegment.checkValid(str)) {
            this.f4308c = str;
        } else {
            Log.e("TTMediationSDK", "流量分组sub_channer字段存在不合法输入");
        }
    }

    public void setUserId(String str) {
        if (GMConfigUserInfoForSegment.checkValid(str)) {
            this.f4306a = str;
        } else {
            Log.e("TTMediationSDK", "流量分组user_id字段存在不合法输入");
        }
    }

    public void setUserValueGroup(String str) {
        if (GMConfigUserInfoForSegment.checkValid(str)) {
            this.f = str;
        } else {
            Log.e("TTMediationSDK", "流量分组user_value_group字段存在不合法输入");
        }
    }
}
