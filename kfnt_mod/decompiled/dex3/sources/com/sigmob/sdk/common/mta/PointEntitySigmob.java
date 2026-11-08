package com.sigmob.sdk.common.mta;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PointEntitySigmob extends PointEntityBase {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private String M;
    private String N;
    private String O;
    private String P;
    private String Q;
    private String R;
    private String S;
    private int T;
    private String U;
    private String V;
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
    private String x;
    private String y;
    private String z;

    public static PointEntitySigmob SigmobTracking(String str, String str2, String str3) {
        PointEntitySigmob pointEntitySigmob = new PointEntitySigmob();
        pointEntitySigmob.setAc_type("5");
        pointEntitySigmob.setCategory(str);
        pointEntitySigmob.setAdtype(str3);
        pointEntitySigmob.setPlacement_id(str2);
        return pointEntitySigmob;
    }

    public String getAd_source_channel() {
        return this.m;
    }

    public String getBid_token() {
        return this.P;
    }

    public String getCampaign_id() {
        return this.f;
    }

    public String getContent() {
        return this.R;
    }

    public String getContent_length() {
        return this.A;
    }

    public String getContent_type() {
        return this.B;
    }

    public String getCoordinate() {
        return this.l;
    }

    public String getCreative_id() {
        return this.e;
    }

    public String getCreative_type() {
        return this.n;
    }

    public String getCurrent_time() {
        return this.s;
    }

    public String getDuration() {
        return this.E;
    }

    public String getDuration_seq() {
        return this.J;
    }

    public String getEndcard_loading_state() {
        return this.O;
    }

    public String getError_code() {
        return this.v;
    }

    public String getError_message() {
        return this.u;
    }

    public String getExpand() {
        return this.d;
    }

    public String getFile_name() {
        return this.F;
    }

    public String getFile_size() {
        return this.G;
    }

    public String getFinal_url() {
        return this.h;
    }

    public String getHttp_code() {
        return this.x;
    }

    public String getIs_deeplink() {
        return this.j;
    }

    public String getIs_force() {
        return this.p;
    }

    public String getIs_truncation() {
        return this.q;
    }

    public String getIscached() {
        return this.H;
    }

    public String getIssuccess() {
        return this.I;
    }

    public String getPermission() {
        return this.V;
    }

    public String getPlay_mode() {
        return this.N;
    }

    public String getPlay_process() {
        return this.k;
    }

    public String getPlay_time() {
        return this.r;
    }

    public String getPrice() {
        return this.c;
    }

    public String getProduct_id() {
        return this.i;
    }

    public String getReady_filters() {
        return this.U;
    }

    public String getReason() {
        return this.Q;
    }

    public String getRequest_id() {
        return this.b;
    }

    public String getResponse() {
        return this.y;
    }

    public String getRetry() {
        return this.C;
    }

    public String getSet_close_time() {
        return this.t;
    }

    public String getSkip_show_time() {
        return this.o;
    }

    public String getSkip_state() {
        return this.L;
    }

    public String getSource() {
        return this.D;
    }

    public String getTarget_url() {
        return this.g;
    }

    public int getTemplate_type() {
        return this.T;
    }

    public String getTime_spend() {
        return this.z;
    }

    public String getUrl() {
        return this.w;
    }

    public String getVid() {
        return this.S;
    }

    public String getVideo_duration() {
        return this.M;
    }

    public String getVideo_time_seq() {
        return this.K;
    }

    public void setAd_source_channel(String str) {
        this.m = str;
    }

    public void setBid_token(String str) {
        this.P = str;
    }

    public void setCampaign_id(String str) {
        this.f = str;
    }

    public void setContent(String str) {
        this.R = str;
    }

    public void setContent_length(String str) {
        this.A = str;
    }

    public void setContent_type(String str) {
        this.B = str;
    }

    public void setCoordinate(String str) {
        this.l = str;
    }

    public void setCreative_id(String str) {
        this.e = str;
    }

    public void setCreative_type(String str) {
        this.n = str;
    }

    public void setCurrent_time(String str) {
        this.s = str;
    }

    public void setDuration(String str) {
        this.E = str;
    }

    public void setDuration_seq(String str) {
        this.J = str;
    }

    public void setEndcard_loading_state(String str) {
        this.O = str;
    }

    public void setError_code(String str) {
        this.v = str;
    }

    public void setError_message(String str) {
        this.u = str;
    }

    public void setExpand(String str) {
        this.d = str;
    }

    public void setFile_name(String str) {
        this.F = str;
    }

    public void setFile_size(String str) {
        this.G = str;
    }

    public void setFinal_url(String str) {
        this.h = str;
    }

    public void setHttp_code(String str) {
        this.x = str;
    }

    public void setIs_deeplink(String str) {
        this.j = str;
    }

    public void setIs_force(String str) {
        this.p = str;
    }

    public void setIs_truncation(String str) {
        this.q = str;
    }

    public void setIscached(String str) {
        this.H = str;
    }

    public void setIssuccess(String str) {
        this.I = str;
    }

    public void setPermission(String str) {
        this.V = str;
    }

    public void setPlay_mode(String str) {
        this.N = str;
    }

    public void setPlay_process(String str) {
        this.k = str;
    }

    public void setPlay_time(String str) {
        this.r = str;
    }

    public void setPrice(String str) {
        this.c = str;
    }

    public void setProduct_id(String str) {
        this.i = str;
    }

    public void setReady_filters(String str) {
        this.U = str;
    }

    public void setReason(String str) {
        this.Q = str;
    }

    public void setRequest_id(String str) {
        this.b = str;
    }

    public void setResponse(String str) {
        this.y = str;
    }

    public void setRetry(String str) {
        this.C = str;
    }

    public void setSet_close_time(String str) {
        this.t = str;
    }

    public void setSkip_show_time(String str) {
        this.o = str;
    }

    public void setSkip_state(String str) {
        this.L = str;
    }

    public void setSource(String str) {
        this.D = str;
    }

    public void setTarget_url(String str) {
        this.g = str;
    }

    public void setTemplate_type(int i) {
        this.T = i;
    }

    public void setTime_spend(String str) {
        this.z = str;
    }

    public void setUrl(String str) {
        this.w = str;
    }

    public void setVid(String str) {
        this.S = str;
    }

    public void setVideo_duration(String str) {
        this.M = str;
    }

    public void setVideo_time_seq(String str) {
        this.K = str;
    }
}
