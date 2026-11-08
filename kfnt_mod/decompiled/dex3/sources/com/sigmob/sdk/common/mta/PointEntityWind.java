package com.sigmob.sdk.common.mta;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PointEntityWind extends PointEntityBase {
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;

    public static PointEntityWind WindTracking(String str, String str2, String str3) {
        PointEntityWind pointEntityWind = new PointEntityWind();
        pointEntityWind.setAc_type(PointType.WIND_COMMON);
        pointEntityWind.setCategory(str);
        pointEntityWind.setAdtype(str3);
        pointEntityWind.setPlacement_id(str2);
        return pointEntityWind;
    }

    public String getAb_test() {
        return this.p;
    }

    public String getAd_cnt() {
        return this.v;
    }

    public String getAggr_appid() {
        return this.c;
    }

    public String getAggr_channel_id() {
        return this.b;
    }

    public String getAggr_placement_id() {
        return this.d;
    }

    public String getAggr_waterfall_id() {
        return this.s;
    }

    public String getFeed_cnt() {
        return this.u;
    }

    public String getGdpr_filters() {
        return this.f;
    }

    public String getGroup_id() {
        return this.o;
    }

    public String getInit_filters() {
        return this.k;
    }

    public String getInterval_filters() {
        return this.j;
    }

    public String getIs_enter_sdk() {
        return this.l;
    }

    public String getIs_out_sdk() {
        return this.m;
    }

    public String getIs_success() {
        return this.n;
    }

    public String getLoad_count() {
        return this.e;
    }

    public String getLoad_type() {
        return this.t;
    }

    public String getLoading_filters() {
        return this.h;
    }

    public String getOffer_id() {
        return this.w;
    }

    public String getPlaying_filters() {
        return this.i;
    }

    public String getPldempty_filters() {
        return this.g;
    }

    public String getVlist_element() {
        return this.r;
    }

    public String getVlist_platform() {
        return this.q;
    }

    public void setAb_test(String str) {
        this.p = str;
    }

    public void setAd_cnt(String str) {
        this.v = str;
    }

    public void setAggr_appid(String str) {
        this.c = str;
    }

    public void setAggr_channel_id(String str) {
        this.b = str;
    }

    public void setAggr_placement_id(String str) {
        this.d = str;
    }

    public void setAggr_waterfall_id(String str) {
        this.s = str;
    }

    public void setFeed_cnt(String str) {
        this.u = str;
    }

    public void setGdpr_filters(String str) {
        this.f = str;
    }

    public void setGroup_id(String str) {
        this.o = str;
    }

    public void setInit_filters(String str) {
        this.k = str;
    }

    public void setInterval_filters(String str) {
        this.j = str;
    }

    public void setIs_enter_sdk(String str) {
        this.l = str;
    }

    public void setIs_out_sdk(String str) {
        this.m = str;
    }

    public void setIs_success(String str) {
        this.n = str;
    }

    public void setLoad_count(String str) {
        this.e = str;
    }

    public void setLoad_type(String str) {
        this.t = str;
    }

    public void setLoading_filters(String str) {
        this.h = str;
    }

    public void setOffer_id(String str) {
        this.w = str;
    }

    public void setPlaying_filters(String str) {
        this.i = str;
    }

    public void setPldempty_filters(String str) {
        this.g = str;
    }

    public void setVlist_element(String str) {
        this.r = str;
    }

    public void setVlist_platform(String str) {
        this.q = str;
    }
}
