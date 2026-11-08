package com.bytedance.msdk.api.v2.ad.custom.nativeAd;

import android.view.View;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomAd;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdAppInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMCustomBaseNativeAd extends GMCustomAd {

    /* renamed from: b, reason: collision with root package name */
    private String f4404b;

    /* renamed from: c, reason: collision with root package name */
    private String f4405c;
    private String d;
    private String e;
    private int f;
    private int g;
    private String h;
    private int i;
    private int j;
    private String k;
    private double l;
    private List<String> m;
    private String n;
    private int o;
    private int p;
    private GMNativeAdAppInfo q;
    private Map<String, Object> r = new HashMap();

    public String getActionText() {
        return this.h;
    }

    public int getAdImageMode() {
        return this.o;
    }

    public String getDescription() {
        return this.f4405c;
    }

    public View getExpressView() {
        return null;
    }

    public String getIconUrl() {
        return this.d;
    }

    public int getImageHeight() {
        return this.g;
    }

    public List<String> getImageList() {
        return this.m;
    }

    public String getImageUrl() {
        return this.e;
    }

    public int getImageWidth() {
        return this.f;
    }

    public int getInteractionType() {
        return this.p;
    }

    public Map<String, Object> getMediaExtraInfo() {
        return this.r;
    }

    public GMNativeAdAppInfo getNativeAdAppInfo() {
        return this.q;
    }

    public String getPackageName() {
        return this.k;
    }

    public String getSource() {
        return this.n;
    }

    public double getStarRating() {
        return this.l;
    }

    public String getTitle() {
        return this.f4404b;
    }

    public int getVideoHeight() {
        return this.j;
    }

    public int getVideoWidth() {
        return this.i;
    }

    public void setActionText(String str) {
        this.h = str;
    }

    public void setAdImageMode(int i) {
        this.o = i;
    }

    public void setDescription(String str) {
        this.f4405c = str;
    }

    public void setIconUrl(String str) {
        this.d = str;
    }

    public void setImageHeight(int i) {
        this.g = i;
    }

    public void setImageList(List<String> list) {
        this.m = list;
    }

    public void setImageUrl(String str) {
        this.e = str;
    }

    public void setImageWidth(int i) {
        this.f = i;
    }

    public void setInteractionType(int i) {
        this.p = i;
    }

    public void setMediaExtraInfo(Map<String, Object> map) {
        if (map != null) {
            this.r.putAll(map);
        }
    }

    public void setNativeAdAppInfo(GMNativeAdAppInfo gMNativeAdAppInfo) {
        this.q = gMNativeAdAppInfo;
    }

    public void setPackageName(String str) {
        this.k = str;
    }

    public void setSource(String str) {
        this.n = str;
    }

    public void setStarRating(double d) {
        this.l = d;
    }

    public void setTitle(String str) {
        this.f4404b = str;
    }

    public void setVideoHeight(int i) {
        this.j = i;
    }

    public void setVideoWidth(int i) {
        this.i = i;
    }
}
