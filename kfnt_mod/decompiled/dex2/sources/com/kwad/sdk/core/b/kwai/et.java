package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.PhotoInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class et implements com.kwad.sdk.core.d<PhotoInfo.WallpaperInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(PhotoInfo.WallpaperInfo wallpaperInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        wallpaperInfo.isWallpaperPhoto = jSONObject.optBoolean("isWallpaperPhoto");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(PhotoInfo.WallpaperInfo wallpaperInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "isWallpaperPhoto", wallpaperInfo.isWallpaperPhoto);
        return jSONObject;
    }
}
