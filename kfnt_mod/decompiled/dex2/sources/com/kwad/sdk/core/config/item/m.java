package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.utils.ae;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class m extends b<List<com.kwad.sdk.core.response.model.b>> {
    public m() {
        super("posContentInfoList", new ArrayList());
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences.Editor editor) {
        List<com.kwad.sdk.core.response.model.b> listA = a();
        if (ae.a(listA)) {
            JSONArray jSONArray = new JSONArray();
            Iterator<com.kwad.sdk.core.response.model.b> it = listA.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJson());
            }
            editor.putString("posContentInfoList", jSONArray.toString());
        }
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(@NonNull SharedPreferences sharedPreferences) {
        try {
            String string = sharedPreferences.getString("posContentInfoList", "");
            if (!TextUtils.isEmpty(string)) {
                JSONArray jSONArray = new JSONArray(string);
                if (jSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                        com.kwad.sdk.core.response.model.b bVar = new com.kwad.sdk.core.response.model.b();
                        bVar.parseJson(jSONObjectOptJSONObject);
                        arrayList.add(bVar);
                    }
                    if (arrayList.size() > 0) {
                        a((m) arrayList);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        a((m) c());
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray;
        if (jSONObject != null && (jSONArrayOptJSONArray = jSONObject.optJSONArray("posContentInfoList")) != null && jSONArrayOptJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    com.kwad.sdk.core.response.model.b bVar = new com.kwad.sdk.core.response.model.b();
                    bVar.parseJson(jSONObjectOptJSONObject);
                    arrayList.add(bVar);
                }
            }
            if (arrayList.size() > 0) {
                a((m) arrayList);
                return;
            }
        }
        a((m) c());
    }
}
