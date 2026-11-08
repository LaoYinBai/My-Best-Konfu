package com.sigmob.windad.base;

import com.sigmob.windad.WindAdRequest;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindVideoAdRequest extends WindAdRequest {
    public WindVideoAdRequest(String str, String str2, Map<String, Object> map) {
        super(str, str2, map);
        this.a = 1;
    }

    @Deprecated
    public WindVideoAdRequest(String str, String str2, boolean z, Map<String, Object> map) {
        this(str, str2, map);
        this.a = z ? 1 : 4;
    }
}
