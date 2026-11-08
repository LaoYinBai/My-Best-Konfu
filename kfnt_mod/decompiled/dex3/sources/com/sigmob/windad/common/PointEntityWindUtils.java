package com.sigmob.windad.common;

import android.text.TextUtils;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.mta.PointEntityWind;
import com.sigmob.sdk.common.mta.PointEntityWindError;
import com.sigmob.sdk.common.mta.PointType;
import com.sigmob.windad.WindAdRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PointEntityWindUtils {

    public interface OnPointEntityExtraInfo {
        void onAddExtra(Object obj);
    }

    public static void WindError(String str, WindAdRequest windAdRequest, ADStrategy aDStrategy, int i, String str2, OnPointEntityExtraInfo onPointEntityExtraInfo) {
        PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError(str, i, str2);
        if (onPointEntityExtraInfo != null) {
            onPointEntityExtraInfo.onAddExtra(pointEntityWindErrorWindError);
        }
        if (windAdRequest != null) {
            pointEntityWindErrorWindError.setAdtype(String.valueOf(windAdRequest.getAdType()));
            if (!TextUtils.isEmpty(windAdRequest.getLoadId())) {
                pointEntityWindErrorWindError.setLoad_id(windAdRequest.getLoadId());
            }
            pointEntityWindErrorWindError.setPlacement_id(windAdRequest.getPlacementId());
        }
        if (aDStrategy != null) {
            pointEntityWindErrorWindError.setPlatform(aDStrategy.getName());
            pointEntityWindErrorWindError.setAggr_channel_id(String.valueOf(aDStrategy.getChannel_id()));
            pointEntityWindErrorWindError.setAggr_appid(aDStrategy.getAppId());
            pointEntityWindErrorWindError.setAggr_placement_id(aDStrategy.getPlacement_id());
            pointEntityWindErrorWindError.setAggr_waterfall_id(String.valueOf(aDStrategy.getElement_id()));
            pointEntityWindErrorWindError.setGroup_id(String.valueOf(aDStrategy.getStrategy_id()));
            if (aDStrategy.isEnable_ab_test()) {
                pointEntityWindErrorWindError.setAb_test(String.valueOf(aDStrategy.getAb_flag()));
            }
        }
        pointEntityWindErrorWindError.commit();
    }

    public static void WindInit(String str, WindAdRequest windAdRequest, OnPointEntityExtraInfo onPointEntityExtraInfo) {
        PointEntityWind pointEntityWind = new PointEntityWind();
        pointEntityWind.setAc_type(PointType.WIND_COMMON);
        if (windAdRequest != null) {
            pointEntityWind.setAdtype(String.valueOf(windAdRequest.getAdType()));
            pointEntityWind.setLoad_id(String.valueOf(windAdRequest.getLoadId()));
            pointEntityWind.setPlacement_id(windAdRequest.getPlacementId());
        }
        pointEntityWind.setCategory(str);
        if (onPointEntityExtraInfo != null) {
            onPointEntityExtraInfo.onAddExtra(pointEntityWind);
        }
        pointEntityWind.commit();
    }

    public static void WindTracking(String str, WindAdRequest windAdRequest, ADStrategy aDStrategy, OnPointEntityExtraInfo onPointEntityExtraInfo) {
        PointEntityWind pointEntityWindWindTracking = PointEntityWind.WindTracking(str, windAdRequest.getPlacementId(), String.valueOf(windAdRequest.getAdType()));
        if (onPointEntityExtraInfo != null) {
            onPointEntityExtraInfo.onAddExtra(pointEntityWindWindTracking);
        }
        if (aDStrategy != null) {
            pointEntityWindWindTracking.setPlatform(aDStrategy.getName());
            pointEntityWindWindTracking.setAggr_channel_id(String.valueOf(aDStrategy.getChannel_id()));
            pointEntityWindWindTracking.setAggr_appid(aDStrategy.getAppId());
            pointEntityWindWindTracking.setAggr_placement_id(aDStrategy.getPlacement_id());
            pointEntityWindWindTracking.setAggr_waterfall_id(String.valueOf(aDStrategy.getElement_id()));
            pointEntityWindWindTracking.setGroup_id(String.valueOf(aDStrategy.getStrategy_id()));
            if (aDStrategy.isEnable_ab_test()) {
                pointEntityWindWindTracking.setAb_test(String.valueOf(aDStrategy.getAb_flag()));
            }
        }
        pointEntityWindWindTracking.commit();
    }
}
