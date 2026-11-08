package com.unity3d.services.monetization.core.placementcontent;

import com.unity3d.services.monetization.UnityMonetization;
import com.unity3d.services.monetization.placementcontent.core.CustomEvent;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PlacementContents {
    private static Map<String, PlacementContent> placementContentsMap = new HashMap();
    private static final PlacementContent NOT_AVAILABLE = getNotAvailablePlacementContent();

    public static PlacementContent getPlacementContent(String str) {
        PlacementContent placementContent = placementContentsMap.get(str);
        return placementContent == null ? NOT_AVAILABLE : placementContent;
    }

    public static <T extends PlacementContent> T getPlacementContent(String str, Class<T> cls) {
        PlacementContent placementContent = getPlacementContent(str);
        if (cls.isInstance(placementContent)) {
            return cls.cast(placementContent);
        }
        return null;
    }

    public static PlacementContent putPlacementContent(String str, PlacementContent placementContent) {
        return placementContentsMap.put(str, placementContent);
    }

    public static boolean isReady(String str) {
        PlacementContent placementContent = getPlacementContent(str);
        return placementContent != null && placementContent.isReady();
    }

    public static void removePlacementContent(String str) {
        placementContentsMap.remove(str);
    }

    public static void setPlacementContentState(String str, UnityMonetization.PlacementContentState placementContentState) {
        PlacementContent placementContent = getPlacementContent(str);
        if (placementContent != null) {
            placementContent.setState(placementContentState);
        }
    }

    private static PlacementContent getNotAvailablePlacementContent() {
        HashMap map = new HashMap();
        map.put("type", "NOT_AVAILABLE");
        return new NotAvailablePlacementContent("", map);
    }

    private static final class NotAvailablePlacementContent extends PlacementContent {
        @Override // com.unity3d.services.monetization.placementcontent.core.PlacementContent
        protected String getDefaultEventCategory() {
            return "NOT_AVAILABLE";
        }

        @Override // com.unity3d.services.monetization.placementcontent.core.PlacementContent
        public boolean isReady() {
            return false;
        }

        @Override // com.unity3d.services.monetization.placementcontent.core.PlacementContent
        public void sendCustomEvent(CustomEvent customEvent) {
        }

        public NotAvailablePlacementContent(String str, Map<String, Object> map) {
            super(str, map);
        }

        @Override // com.unity3d.services.monetization.placementcontent.core.PlacementContent
        public UnityMonetization.PlacementContentState getState() {
            return UnityMonetization.PlacementContentState.NOT_AVAILABLE;
        }
    }
}
