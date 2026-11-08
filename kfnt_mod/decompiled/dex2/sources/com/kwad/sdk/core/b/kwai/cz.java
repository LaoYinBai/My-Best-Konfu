package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.PhotoInfo;
import com.kwad.sdk.live.mode.LiveInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cz implements com.kwad.sdk.core.d<PhotoInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(PhotoInfo photoInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        photoInfo.baseInfo = new PhotoInfo.BaseInfo();
        photoInfo.baseInfo.parseJson(jSONObject.optJSONObject("baseInfo"));
        photoInfo.videoInfo = new PhotoInfo.VideoInfo();
        photoInfo.videoInfo.parseJson(jSONObject.optJSONObject("videoInfo"));
        photoInfo.coverInfo = new PhotoInfo.CoverInfo();
        photoInfo.coverInfo.parseJson(jSONObject.optJSONObject("coverInfo"));
        photoInfo.authorInfo = new PhotoInfo.AuthorInfo();
        photoInfo.authorInfo.parseJson(jSONObject.optJSONObject("authorInfo"));
        photoInfo.liveInfo = new LiveInfo();
        photoInfo.liveInfo.parseJson(jSONObject.optJSONObject("liveInfo"));
        photoInfo.tubeEpisode = new PhotoInfo.TubeEpisode();
        photoInfo.tubeEpisode.parseJson(jSONObject.optJSONObject("tubeEpisode"));
        photoInfo.wallpaperInfo = new PhotoInfo.WallpaperInfo();
        photoInfo.wallpaperInfo.parseJson(jSONObject.optJSONObject("wallpaperInfo"));
        photoInfo.productInfo = new PhotoInfo.ProductInfo();
        photoInfo.productInfo.parseJson(jSONObject.optJSONObject("productInfo"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(PhotoInfo photoInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "baseInfo", photoInfo.baseInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "videoInfo", photoInfo.videoInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "coverInfo", photoInfo.coverInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "authorInfo", photoInfo.authorInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "liveInfo", photoInfo.liveInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "tubeEpisode", photoInfo.tubeEpisode);
        com.kwad.sdk.utils.t.a(jSONObject, "wallpaperInfo", photoInfo.wallpaperInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "productInfo", photoInfo.productInfo);
        return jSONObject;
    }
}
