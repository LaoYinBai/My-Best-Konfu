package com.sigmob.windad.natives;

import com.sigmob.windad.WindAdRequest;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindNativeAdRequest extends WindAdRequest {
    public WindNativeAdRequest(String str, String str2, int i, Map<String, Object> map) {
        super(str, str2, i, map);
        this.a = 5;
    }

    public static WindNativeAdRequest getWindVideoAdRequest(WindAdRequest windAdRequest) {
        if (windAdRequest != null) {
            return new WindNativeAdRequest(windAdRequest.getPlacementId(), windAdRequest.getUserId(), 1, windAdRequest.getOptions());
        }
        return null;
    }
}
