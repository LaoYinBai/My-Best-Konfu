package com.mbridge.msdk.videocommon.b;

import com.heytap.mcssdk.mode.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Cbprule.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private int a;
    private List<Integer> b;

    private c(int i, List<Integer> list) {
        this.a = i;
        this.b = list;
    }

    public final int a() {
        return this.a;
    }

    public final List<Integer> b() {
        return this.b;
    }

    public static c a(JSONObject jSONObject) {
        c cVar = new c(1, Arrays.asList(1, 2, 3, 4));
        if (jSONObject == null) {
            return cVar;
        }
        try {
            int iOptInt = jSONObject.optInt(Message.TYPE);
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("value");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(Integer.valueOf(jSONArrayOptJSONArray.optInt(i)));
                }
            }
            return new c(iOptInt, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
            return cVar;
        }
    }
}
