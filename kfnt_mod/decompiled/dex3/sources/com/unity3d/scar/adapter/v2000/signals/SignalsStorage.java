package com.unity3d.scar.adapter.v2000.signals;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SignalsStorage {
    private Map<String, QueryInfoMetadata> _placementQueryInfoMap = new HashMap();

    public Map<String, QueryInfoMetadata> getPlacementQueryInfoMap() {
        return this._placementQueryInfoMap;
    }

    public QueryInfoMetadata getQueryInfoMetadata(String str) {
        return this._placementQueryInfoMap.get(str);
    }

    public void put(String str, QueryInfoMetadata queryInfoMetadata) {
        this._placementQueryInfoMap.put(str, queryInfoMetadata);
    }
}
