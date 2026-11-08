package com.ss.union.game.sdk.feedback.module;

import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    public long a;
    public String b;
    public String c;
    public boolean d;
    public List<b> e;
    public long f;

    public static List<c> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                arrayList.add(a(jSONObjectOptJSONObject));
            }
        }
        return arrayList;
    }

    private static c a(@NonNull JSONObject jSONObject) {
        c cVar = new c();
        cVar.a = jSONObject.optLong(AdDownloadModel.JsonKey.ID, -1L);
        cVar.b = jSONObject.optString("feedback_type_name");
        cVar.c = jSONObject.optString(com.umeng.commonsdk.framework.c.a);
        cVar.d = jSONObject.optBoolean("has_new_reply");
        cVar.e = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("images");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                b bVar = new b();
                bVar.a(jSONArrayOptJSONArray.optJSONObject(i));
                cVar.e.add(bVar);
            }
        }
        cVar.f = jSONObject.optLong("created_at");
        return cVar;
    }
}
