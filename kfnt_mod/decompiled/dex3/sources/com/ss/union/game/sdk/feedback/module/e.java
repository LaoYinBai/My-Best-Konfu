package com.ss.union.game.sdk.feedback.module;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    private List<String> a;

    public List<String> a() {
        return this.a;
    }

    public void a(JSONArray jSONArray) {
        this.a = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            this.a.add(jSONArray.optString(i));
        }
    }

    public String toString() {
        return "LGUserFeedbackQuestion{types=" + this.a + '}';
    }
}
