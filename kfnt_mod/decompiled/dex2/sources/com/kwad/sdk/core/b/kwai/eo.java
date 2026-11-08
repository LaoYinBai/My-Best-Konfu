package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.PhotoInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class eo implements com.kwad.sdk.core.d<PhotoInfo.VideoInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(PhotoInfo.VideoInfo videoInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        videoInfo.videoUrl = jSONObject.optString("videoUrl");
        if (jSONObject.opt("videoUrl") == JSONObject.NULL) {
            videoInfo.videoUrl = "";
        }
        videoInfo.manifest = jSONObject.optString("manifest");
        if (jSONObject.opt("manifest") == JSONObject.NULL) {
            videoInfo.manifest = "";
        }
        videoInfo.firstFrame = jSONObject.optString("firstFrame");
        if (jSONObject.opt("firstFrame") == JSONObject.NULL) {
            videoInfo.firstFrame = "";
        }
        videoInfo.duration = jSONObject.optLong("duration");
        videoInfo.size = jSONObject.optInt("size");
        videoInfo.width = jSONObject.optInt("width");
        videoInfo.height = jSONObject.optInt("height");
        videoInfo.leftRatio = jSONObject.optDouble("leftRatio");
        videoInfo.topRatio = jSONObject.optDouble("topRatio");
        videoInfo.widthRatio = jSONObject.optDouble("widthRatio", new Double("1.0f").doubleValue());
        videoInfo.heightRatio = jSONObject.optDouble("heightRatio", new Double("1.0f").doubleValue());
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(PhotoInfo.VideoInfo videoInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "videoUrl", videoInfo.videoUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "manifest", videoInfo.manifest);
        com.kwad.sdk.utils.t.a(jSONObject, "firstFrame", videoInfo.firstFrame);
        com.kwad.sdk.utils.t.a(jSONObject, "duration", videoInfo.duration);
        com.kwad.sdk.utils.t.a(jSONObject, "size", videoInfo.size);
        com.kwad.sdk.utils.t.a(jSONObject, "width", videoInfo.width);
        com.kwad.sdk.utils.t.a(jSONObject, "height", videoInfo.height);
        com.kwad.sdk.utils.t.a(jSONObject, "leftRatio", videoInfo.leftRatio);
        com.kwad.sdk.utils.t.a(jSONObject, "topRatio", videoInfo.topRatio);
        com.kwad.sdk.utils.t.a(jSONObject, "widthRatio", videoInfo.widthRatio);
        com.kwad.sdk.utils.t.a(jSONObject, "heightRatio", videoInfo.heightRatio);
        return jSONObject;
    }
}
