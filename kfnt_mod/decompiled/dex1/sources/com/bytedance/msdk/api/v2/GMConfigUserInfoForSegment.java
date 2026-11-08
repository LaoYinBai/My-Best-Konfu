package com.bytedance.msdk.api.v2;

import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMConfigUserInfoForSegment {
    public static final String GENDER_FEMALE = "female";
    public static final String GENDER_MALE = "male";
    public static final String GENDER_UNKNOWN = "unknown";
    public final String TAG = "TTMediationSDK";

    /* renamed from: a, reason: collision with root package name */
    private String f4345a = "";

    /* renamed from: b, reason: collision with root package name */
    private String f4346b = "";

    /* renamed from: c, reason: collision with root package name */
    private String f4347c = "";
    private int d = 0;
    private String e = "";
    private String f = "";
    private Map<String, String> g;

    public static boolean checkValid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("[A-Za-z0-9-_]{1,100}");
    }

    public boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (obj == null || GMConfigUserInfoForSegment.class != obj.getClass()) {
            return false;
        }
        GMConfigUserInfoForSegment gMConfigUserInfoForSegment = (GMConfigUserInfoForSegment) obj;
        boolean z2 = getAge() == gMConfigUserInfoForSegment.getAge() && TextUtils.equals(getUserValueGroup(), gMConfigUserInfoForSegment.getUserValueGroup()) && TextUtils.equals(getUserId(), gMConfigUserInfoForSegment.getUserId()) && TextUtils.equals(getChannel(), gMConfigUserInfoForSegment.getChannel()) && TextUtils.equals(getSubChannel(), gMConfigUserInfoForSegment.getSubChannel()) && TextUtils.equals(getGender(), gMConfigUserInfoForSegment.getGender());
        Map<String, String> customInfos = gMConfigUserInfoForSegment.getCustomInfos();
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
        return this.f4346b;
    }

    public Map<String, String> getCustomInfos() {
        return this.g;
    }

    public String getGender() {
        return this.e;
    }

    public String getSubChannel() {
        return this.f4347c;
    }

    public String getUserId() {
        return this.f4345a;
    }

    public String getUserValueGroup() {
        return this.f;
    }

    public void setAge(int i) {
        this.d = i;
    }

    public void setChannel(String str) {
        if (checkValid(str)) {
            this.f4346b = str;
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
                if (!checkValid(entry.getKey())) {
                    Log.e("TTMediationSDK", "流量分组" + entry.getKey() + "字段存在不合法输入");
                } else if (checkValid(entry.getValue())) {
                    this.g.put(entry.getKey(), entry.getValue());
                } else {
                    Log.e("TTMediationSDK", "流量分组" + entry.getKey() + "字段的值" + entry.getValue() + "存在不合法输入");
                }
            }
        }
    }

    public void setGender(String str) {
        if (checkValid(str)) {
            this.e = str;
        } else {
            Log.e("TTMediationSDK", "流量分组gender字段存在不合法输入");
        }
    }

    public void setSubChannel(String str) {
        if (checkValid(str)) {
            this.f4347c = str;
        } else {
            Log.e("TTMediationSDK", "流量分组sub_channer字段存在不合法输入");
        }
    }

    public void setUserId(String str) {
        if (checkValid(str)) {
            this.f4345a = str;
        } else {
            Log.e("TTMediationSDK", "流量分组user_id字段存在不合法输入");
        }
    }

    public void setUserValueGroup(String str) {
        if (checkValid(str)) {
            this.f = str;
        } else {
            Log.e("TTMediationSDK", "流量分组user_value_group字段存在不合法输入");
        }
    }
}
