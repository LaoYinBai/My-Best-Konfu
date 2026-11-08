package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InstallAppManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private static h b;
    private Context a;
    private SharedPreferences c;

    public static h a(Context context) {
        if (b == null) {
            synchronized (h.class) {
                if (b == null) {
                    b = new h(context);
                }
            }
        }
        return b;
    }

    private h(Context context) {
        this.a = context;
    }

    public final CopyOnWriteArraySet<com.mbridge.msdk.foundation.entity.j> a(String str) throws JSONException {
        CopyOnWriteArraySet<com.mbridge.msdk.foundation.entity.j> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        if (this.a != null) {
            try {
                this.c = this.a.getSharedPreferences("installed", 0);
                if (this.c != null) {
                    String string = this.c.getString(str + "_installed", "");
                    if (!TextUtils.isEmpty(string)) {
                        JSONArray jSONArray = new JSONArray(string);
                        for (int i = 0; i < jSONArray.length(); i++) {
                            com.mbridge.msdk.foundation.entity.j jVar = new com.mbridge.msdk.foundation.entity.j();
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            jVar.a(jSONObject.optString("campaignId"));
                            jVar.b(jSONObject.optString("packageName"));
                            copyOnWriteArraySet.add(jVar);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return copyOnWriteArraySet;
    }

    public final void a(Set<com.mbridge.msdk.foundation.entity.j> set) {
        SharedPreferences.Editor editorEdit;
        if (set == null || set.size() < 0) {
            return;
        }
        try {
            String strA = com.mbridge.msdk.foundation.entity.j.a(set);
            if (this.a != null) {
                this.c = this.a.getSharedPreferences("installed", 0);
                if (this.c == null || (editorEdit = this.c.edit()) == null) {
                    return;
                }
                editorEdit.putString(com.mbridge.msdk.foundation.controller.a.f().k() + "_installed", strA);
                editorEdit.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
