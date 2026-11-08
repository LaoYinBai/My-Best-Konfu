package com.sigmob.sdk.nativead;

import android.content.Context;
import com.sigmob.sdk.base.common.e;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.common.models.sigdsp.pb.NativeAdSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.SingleNativeAdSetting;
import com.sigmob.sdk.common.mta.PointCategory;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends com.sigmob.sdk.base.common.f {
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private Boolean l;

    public static b c(BaseAdUnit baseAdUnit) {
        b bVar = new b();
        bVar.b(baseAdUnit);
        return bVar;
    }

    @Override // com.sigmob.sdk.base.common.f
    public void a(Context context, com.sigmob.sdk.common.utils.q qVar, com.sigmob.sdk.common.utils.q qVar2, com.sigmob.sdk.base.a aVar, BaseAdUnit baseAdUnit) throws UnsupportedEncodingException {
        SigMacroCommon macroCommon = baseAdUnit.getMacroCommon();
        if (macroCommon != null) {
            macroCommon.addMarcoKey(SigMacroCommon._DOWNX_, String.valueOf(qVar.a()));
            macroCommon.addMarcoKey(SigMacroCommon._DOWNY_, String.valueOf(qVar.b()));
            macroCommon.addMarcoKey(SigMacroCommon._UPX_, String.valueOf(qVar2.a()));
            macroCommon.addMarcoKey(SigMacroCommon._UPY_, String.valueOf(qVar2.b()));
        }
        b(baseAdUnit, context, (String) null, aVar, String.format("%d,%d,%d,%d", Integer.valueOf(qVar.a()), Integer.valueOf(qVar.b()), Integer.valueOf(qVar2.a()), Integer.valueOf(qVar2.b())), true);
    }

    @Override // com.sigmob.sdk.base.common.f
    public void b(BaseAdUnit baseAdUnit) {
        super.b(baseAdUnit);
        NativeAdSetting nativeAdSetting = baseAdUnit.getNativeAdSetting();
        SingleNativeAdSetting singleNativeSetting = baseAdUnit.getSingleNativeSetting();
        if (singleNativeSetting != null) {
            this.k = singleNativeSetting.use_na_video_component.booleanValue();
        }
        if (nativeAdSetting != null) {
            this.f = nativeAdSetting.preview_page_video_mute.intValue();
            this.g = nativeAdSetting.detail_page_video_mute.intValue();
            this.h = nativeAdSetting.impression_percent.intValue();
            this.i = nativeAdSetting.impression_time.intValue();
            this.e = nativeAdSetting.video_auto_play.intValue();
            this.j = nativeAdSetting.end_impression_time.intValue();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.sigmob.sdk.videoAd.e(e.a.QUARTILE_EVENT, com.sigmob.sdk.base.common.a.AD_PLAY_QUARTER, 0.25f));
        arrayList.add(new com.sigmob.sdk.videoAd.e(e.a.QUARTILE_EVENT, com.sigmob.sdk.base.common.a.AD_PLAY_TWO_QUARTERS, 0.5f));
        arrayList.add(new com.sigmob.sdk.videoAd.e(e.a.QUARTILE_EVENT, com.sigmob.sdk.base.common.a.AD_PLAY_THREE_QUARTERS, 0.75f));
        a(arrayList);
    }

    public void c(Context context, BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.base.common.t.a(PointCategory.TEMPLATE_SHOW, (String) null, baseAdUnit);
    }

    public void d(Context context, BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.base.common.t.a(PointCategory.TEMPLATE_CLOSE, (String) null, baseAdUnit);
    }

    public boolean d() {
        return this.k;
    }

    public int e() {
        return this.e;
    }

    public void e(Context context, BaseAdUnit baseAdUnit) {
        com.sigmob.sdk.base.common.t.b(PointCategory.FEED_CLICK, null, baseAdUnit);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_FEED_CLICK);
    }

    public boolean f() {
        return this.l != null ? this.l.booleanValue() : this.f == 0;
    }

    public boolean g() {
        return this.g != 0;
    }

    public int h() {
        if (this.h != 0) {
            return this.h;
        }
        return 50;
    }

    public int i() {
        if (this.i != 0) {
            return this.i;
        }
        return 1;
    }

    public boolean j() {
        if (e() != 0) {
            return e() == 1 && com.sigmob.sdk.common.a.n().S() == 100;
        }
        return true;
    }
}
