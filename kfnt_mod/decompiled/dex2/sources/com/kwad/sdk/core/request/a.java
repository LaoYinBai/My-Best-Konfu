package com.kwad.sdk.core.request;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.request.model.f;
import com.kwad.sdk.core.request.model.j;
import com.kwad.sdk.core.request.model.k;
import com.kwad.sdk.core.request.model.l;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.kwad.sdk.utils.as;
import com.kwad.sdk.utils.t;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.core.network.d {
    f b;
    private int c;

    public a(f fVar) {
        this(fVar, null);
    }

    public a(f fVar, k kVar) {
        this(fVar, null, false, kVar, null);
    }

    public a(f fVar, @Nullable List<String> list, boolean z, k kVar, j jVar) throws JSONException {
        super(a(fVar));
        this.b = fVar;
        com.kwad.sdk.internal.api.a aVarA = this.b.a();
        if (aVarA != null && !aVarA.b()) {
            a(com.kwad.sdk.core.request.model.b.a(), aVarA);
        }
        JSONArray jSONArray = new JSONArray();
        t.a(jSONArray, fVar.toJson());
        a("impInfo", jSONArray);
        a("universePhotoInfo", kVar);
        if (this.c > 0) {
            a("calledUnionType", this.c);
        }
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_MODIFY_CREATEID");
        if (developValueA != null) {
            String str = "creativeId_" + developValueA.getValue();
            list = new ArrayList<>();
            list.add(str);
        }
        if (list != null) {
            a("preloadIdList", new JSONArray((Collection) list));
            a("preloadCheck", z);
        }
        b("appTag", as.d(KsAdSDKImpl.get().getContext()));
        DevelopMangerPlugin.DevelopValue developValueA2 = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_CAMPAIGNTYPE");
        if (developValueA2 != null) {
            a("campaignType", ((Integer) developValueA2.getValue()).intValue());
        }
        String strA = this.b.a("thirdUserId");
        l lVarA = l.a();
        if (strA != null) {
            lVarA.a(strA);
        }
        if (aVarA != null && !aVarA.a()) {
            a(lVarA, aVarA);
        }
        a("userInfo", lVarA);
        if (jVar != null) {
            a("statusInfo", jVar);
        }
    }

    private static int a(f fVar) {
        try {
            return fVar.a.getScreenOrientation();
        } catch (Throwable unused) {
            return 0;
        }
    }

    private void a(l lVar, com.kwad.sdk.internal.api.a aVar) {
        if (aVar.a != 0) {
            lVar.a = aVar.a;
        }
        if (aVar.b != 0) {
            lVar.b = aVar.b;
        }
        if (TextUtils.isEmpty(aVar.c)) {
            return;
        }
        lVar.c = aVar.c;
    }

    private void a(JSONObject jSONObject, com.kwad.sdk.internal.api.a aVar) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (!TextUtils.isEmpty(aVar.d)) {
            t.a(jSONObject2, "prevTitle", aVar.d);
        }
        if (!TextUtils.isEmpty(aVar.e)) {
            t.a(jSONObject2, "postTitle", aVar.e);
        }
        if (!TextUtils.isEmpty(aVar.f)) {
            t.a(jSONObject2, "historyTitle", aVar.f);
        }
        if (!TextUtils.isEmpty(aVar.g)) {
            t.a(jSONObject2, "channel", aVar.g);
        }
        t.a(jSONObject, "content", jSONObject2);
        a("appInfo", jSONObject);
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() {
        return com.kwad.sdk.d.c();
    }

    public void a(int i) {
        this.c = i;
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public SceneImpl b() {
        if (this.b != null) {
            return this.b.a;
        }
        return null;
    }
}
