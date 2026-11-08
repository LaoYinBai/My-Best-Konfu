package com.sigmob.sdk.common.models;

import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ADStrategy {
    private final int a;
    private final String b;
    private final String c;
    private final int d;
    private final boolean e;
    private final boolean f;
    private String g;
    private String h;
    private Map<String, Object> i;
    private int j;
    private String k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;
    private String q;
    private String r;
    private long s;
    private boolean t;
    private String u;
    private boolean v;

    public ADStrategy(boolean z, int i, String str, String str2, boolean z2, int i2) {
        this.g = "";
        this.i = new HashMap();
        this.t = false;
        this.f = z;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.e = z2;
        this.d = i2;
    }

    public ADStrategy(boolean z, String str, int i, String str2, String str3, boolean z2, int i2) {
        this.g = "";
        this.i = new HashMap();
        this.t = false;
        this.f = z;
        this.g = str;
        this.a = i;
        this.b = str2;
        this.c = str3;
        this.e = z2;
        this.d = i2;
    }

    public void addOptions(HashMap<String, String> map) {
        this.i.putAll(map);
    }

    public String getADStrategyID() {
        return this.j + Constants.COLON_SEPARATOR + this.m;
    }

    public int getAb_flag() {
        return this.d;
    }

    public String getAdScene() {
        return this.q;
    }

    public int getAdType() {
        return this.a;
    }

    public String getAppId() {
        return this.k;
    }

    public String getAppKey() {
        return this.l;
    }

    public String getBidToken() {
        return this.g;
    }

    public int getChannel_id() {
        return this.j;
    }

    public int getElement_id() {
        return this.n;
    }

    public int getExpired_time() {
        return this.o;
    }

    public String getName() {
        return this.h;
    }

    public String getOffer_id() {
        return this.u;
    }

    public Map<String, Object> getOptions() {
        return this.i;
    }

    public String getPlacement_id() {
        return this.m;
    }

    public long getReadyTime() {
        return this.s;
    }

    public String getSig_load_id() {
        return this.r;
    }

    public String getSig_placement_id() {
        return this.b;
    }

    public String getStrategy_id() {
        return this.c;
    }

    public boolean isEnable_ab_test() {
        return this.e;
    }

    public boolean isExpired() {
        return this.o > 0 && System.currentTimeMillis() - this.s > ((long) (this.o * 1000));
    }

    public boolean isExtraCloseCallBack() {
        return this.p;
    }

    public boolean isPersonalizedAdvertisingOn() {
        return this.v;
    }

    public boolean isRightObject() {
        return this.t;
    }

    public boolean isUseMediation() {
        return this.f;
    }

    public void resetReady() {
        this.s = 0L;
    }

    public void setAdScene(String str) {
        this.q = str;
    }

    public void setAppId(String str) {
        this.k = str;
    }

    public void setAppKey(String str) {
        this.l = str;
    }

    public void setBidToken(String str) {
        this.g = str;
    }

    public void setChannel_id(int i) {
        this.j = i;
    }

    public void setElement_id(int i) {
        this.n = i;
    }

    public void setExpired_time(int i) {
        this.o = i;
    }

    public void setExtraCloseCallBack(boolean z) {
        this.p = z;
    }

    public void setName(String str) {
        this.h = str;
    }

    public void setOffer_id(String str) {
        this.u = str;
    }

    public void setPersonalizedAdvertisingOn(boolean z) {
        this.v = z;
    }

    public void setPlacement_id(String str) {
        this.m = str;
    }

    public void setReady() {
        this.s = System.currentTimeMillis();
    }

    public void setRightObject(boolean z) {
        this.t = z;
    }

    public void setSig_load_id(String str) {
        this.r = str;
    }

    public String toString() {
        return "ADStrategy{name='" + this.h + "', options=" + this.i + ", channel_id=" + this.j + ", strategy_id='" + this.c + "', ab_flag=" + this.d + ", enable_ab_test=" + this.e + ", appId='" + this.k + "', appKey='" + this.l + "', adType=" + this.a + ", placement_id='" + this.m + "', sig_placement_id='" + this.b + "', expired_time=" + this.o + ", sig_load_id='" + this.r + "', ready_time=" + this.s + ", isExtraCloseCallBack=" + this.p + ", mUseMediation=" + this.f + '}';
    }
}
