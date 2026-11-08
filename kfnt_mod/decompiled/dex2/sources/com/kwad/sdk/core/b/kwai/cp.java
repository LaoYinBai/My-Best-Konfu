package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.NewsInfo;
import com.kwad.sdk.core.response.model.PhotoInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cp implements com.kwad.sdk.core.d<NewsInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(NewsInfo newsInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        newsInfo.authorInfo = new PhotoInfo.AuthorInfo();
        newsInfo.authorInfo.parseJson(jSONObject.optJSONObject("authorInfo"));
        newsInfo.photoId = jSONObject.optLong("photoId");
        newsInfo.title = jSONObject.optString("title");
        if (jSONObject.opt("title") == JSONObject.NULL) {
            newsInfo.title = "";
        }
        newsInfo.content = jSONObject.optString("content");
        if (jSONObject.opt("content") == JSONObject.NULL) {
            newsInfo.content = "";
        }
        newsInfo.imageInfo = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("imageInfo");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                NewsInfo.ImageInfo imageInfo = new NewsInfo.ImageInfo();
                imageInfo.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                newsInfo.imageInfo.add(imageInfo);
            }
        }
        newsInfo.thumbnailInfo = new ArrayList();
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("thumbnailInfo");
        if (jSONArrayOptJSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                NewsInfo.ImageInfo imageInfo2 = new NewsInfo.ImageInfo();
                imageInfo2.parseJson(jSONArrayOptJSONArray2.optJSONObject(i2));
                newsInfo.thumbnailInfo.add(imageInfo2);
            }
        }
        newsInfo.templateUrl = jSONObject.optString("templateUrl");
        if (jSONObject.opt("templateUrl") == JSONObject.NULL) {
            newsInfo.templateUrl = "";
        }
        newsInfo.publishTimestamp = jSONObject.optLong("publishTimestamp");
        newsInfo.contentSourceType = jSONObject.optInt("contentSourceType");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(NewsInfo newsInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "authorInfo", newsInfo.authorInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "photoId", newsInfo.photoId);
        com.kwad.sdk.utils.t.a(jSONObject, "title", newsInfo.title);
        com.kwad.sdk.utils.t.a(jSONObject, "content", newsInfo.content);
        com.kwad.sdk.utils.t.a(jSONObject, "imageInfo", newsInfo.imageInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "thumbnailInfo", newsInfo.thumbnailInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "templateUrl", newsInfo.templateUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "publishTimestamp", newsInfo.publishTimestamp);
        com.kwad.sdk.utils.t.a(jSONObject, "contentSourceType", newsInfo.contentSourceType);
        return jSONObject;
    }
}
