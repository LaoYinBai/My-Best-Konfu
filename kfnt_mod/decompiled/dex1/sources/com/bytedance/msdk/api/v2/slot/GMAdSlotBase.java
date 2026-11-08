package com.bytedance.msdk.api.v2.slot;

import androidx.annotation.Nullable;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotBaiduOption;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotGDTOption;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMAdSlotBase {

    /* renamed from: a, reason: collision with root package name */
    protected GMAdSlotGDTOption f4444a;

    /* renamed from: b, reason: collision with root package name */
    protected GMAdSlotBaiduOption f4445b;

    /* renamed from: c, reason: collision with root package name */
    protected Map<String, Object> f4446c;
    private boolean d;
    private float e;
    private boolean f;
    private String g;
    private int h;
    private boolean i;

    public static abstract class Builder {

        /* renamed from: a, reason: collision with root package name */
        protected boolean f4447a;

        /* renamed from: b, reason: collision with root package name */
        protected float f4448b;

        /* renamed from: c, reason: collision with root package name */
        protected boolean f4449c;
        protected GMAdSlotGDTOption d;
        protected GMAdSlotBaiduOption e;
        protected String g;
        protected boolean i;
        protected Map<String, Object> f = new HashMap();
        protected int h = 0;
    }

    protected GMAdSlotBase(Builder builder) {
        this.d = builder.f4447a;
        float f = builder.f4448b;
        if (f > 1.0f) {
            builder.f4448b = 1.0f;
        } else if (f < 0.0f) {
            builder.f4448b = 0.0f;
        }
        this.e = builder.f4448b;
        this.f = builder.f4449c;
        GMAdSlotGDTOption gMAdSlotGDTOption = builder.d;
        if (gMAdSlotGDTOption != null) {
            this.f4444a = gMAdSlotGDTOption;
        } else {
            this.f4444a = new GMAdSlotGDTOption.Builder().build();
        }
        GMAdSlotBaiduOption gMAdSlotBaiduOption = builder.e;
        if (gMAdSlotBaiduOption != null) {
            this.f4445b = gMAdSlotBaiduOption;
        } else {
            this.f4445b = new GMAdSlotBaiduOption.Builder().build();
        }
        this.f4446c = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
    }

    public TTVideoOption createTTVideoOption(boolean z) {
        TTVideoOption.Builder builder = new TTVideoOption.Builder();
        builder.setMuted(isMuted());
        builder.setAdmobAppVolume(getVolume());
        builder.useSurfaceView(isUseSurfaceView());
        GMAdSlotGDTOption gMAdSlotGDTOption = getGMAdSlotGDTOption();
        if (gMAdSlotGDTOption != null) {
            builder.setGDTExtraOption(gMAdSlotGDTOption.getGDTExtraOption(z));
        }
        GMAdSlotBaiduOption gMAdSlotBaiduOption = getGMAdSlotBaiduOption();
        if (gMAdSlotGDTOption != null) {
            builder.setBaiduExtraOption(gMAdSlotBaiduOption.getBaiduExtra());
        }
        return builder.build();
    }

    public int getDownloadType() {
        return this.h;
    }

    public GMAdSlotBaiduOption getGMAdSlotBaiduOption() {
        return this.f4445b;
    }

    public GMAdSlotGDTOption getGMAdSlotGDTOption() {
        return this.f4444a;
    }

    @Nullable
    public Map<String, Object> getParams() {
        return this.f4446c;
    }

    public TTRequestExtraParams getTTRequestExtraParams() {
        TTRequestExtraParams tTRequestExtraParams = new TTRequestExtraParams();
        if (getParams() != null && getParams().size() > 0) {
            tTRequestExtraParams.getExtraObject().putAll(getParams());
        }
        return tTRequestExtraParams;
    }

    public String getTestSlotId() {
        return this.g;
    }

    public float getVolume() {
        return this.e;
    }

    public boolean isBidNotify() {
        return this.i;
    }

    public boolean isMuted() {
        return this.d;
    }

    public boolean isUseSurfaceView() {
        return this.f;
    }
}
