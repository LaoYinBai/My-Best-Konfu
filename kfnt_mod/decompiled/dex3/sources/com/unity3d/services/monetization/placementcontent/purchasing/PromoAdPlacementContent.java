package com.unity3d.services.monetization.placementcontent.purchasing;

import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PromoAdPlacementContent extends ShowAdPlacementContent {
    private final PromoMetadata promoMetadata;

    @Override // com.unity3d.services.monetization.placementcontent.core.PlacementContent
    protected String getDefaultEventCategory() {
        return "PROMO";
    }

    public PromoAdPlacementContent(String str, Map<String, Object> map) {
        super(str, map);
        this.promoMetadata = PromoMetadataUtilities.createPromoMetadataFromParamsMap(map);
    }

    public PromoMetadata getMetadata() {
        return this.promoMetadata;
    }
}
