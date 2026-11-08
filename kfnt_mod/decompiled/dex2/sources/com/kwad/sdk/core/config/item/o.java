package com.kwad.sdk.core.config.item;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.kwad.sdk.core.response.model.ReportInfo;
import com.kwad.sdk.utils.ae;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class o extends b<List<ReportInfo>> {
    public o() {
        super("reportItems", d());
    }

    private static ArrayList<ReportInfo> d() {
        ArrayList<ReportInfo> arrayList = new ArrayList<>();
        arrayList.add(new ReportInfo(1, "违法违规"));
        arrayList.add(new ReportInfo(2, "色情低俗"));
        arrayList.add(new ReportInfo(3, "作者举报"));
        arrayList.add(new ReportInfo(4, "封面党"));
        arrayList.add(new ReportInfo(5, "不适合未成年"));
        return arrayList;
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(SharedPreferences.Editor editor) {
        List<ReportInfo> listA = a();
        if (ae.a(listA)) {
            JSONArray jSONArray = new JSONArray();
            Iterator<ReportInfo> it = listA.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJson());
            }
            editor.putString("reportItems", jSONArray.toString());
        }
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(SharedPreferences sharedPreferences) {
        try {
            String string = sharedPreferences.getString("reportItems", "");
            if (!TextUtils.isEmpty(string)) {
                JSONArray jSONArray = new JSONArray(string);
                if (jSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                        ReportInfo reportInfo = new ReportInfo();
                        reportInfo.parseJson(jSONObjectOptJSONObject);
                        arrayList.add(reportInfo);
                    }
                    if (arrayList.size() > 0) {
                        a((o) arrayList);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        a((o) c());
    }

    @Override // com.kwad.sdk.core.config.item.b
    public void a(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray;
        if (jSONObject != null && (jSONArrayOptJSONArray = jSONObject.optJSONArray("reportItems")) != null && jSONArrayOptJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    ReportInfo reportInfo = new ReportInfo();
                    reportInfo.parseJson(jSONObjectOptJSONObject);
                    arrayList.add(reportInfo);
                }
            }
            if (arrayList.size() > 0) {
                a((o) arrayList);
                return;
            }
        }
        a((o) c());
    }
}
