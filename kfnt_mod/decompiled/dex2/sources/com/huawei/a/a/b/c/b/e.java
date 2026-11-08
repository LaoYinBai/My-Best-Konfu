package com.huawei.a.a.b.c.b;

import android.text.TextUtils;
import com.huawei.a.a.b.a.b;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements f {
    private List<d> a;
    private b b;
    private c c;
    private f d;
    private String e = "";
    private String f;

    public e(String str) {
        this.f = str;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    public void a(com.huawei.a.a.e.a aVar) {
        this.d = aVar;
    }

    public void a(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    public void a(List<d> list) {
        this.a = list;
    }

    @Override // com.huawei.a.a.b.c.b.f
    public JSONObject c() throws JSONException, IOException {
        String str;
        String str2;
        if (this.a == null || this.a.size() == 0) {
            str = "EventUploadModel";
            str2 = "Not have actionEvent to send";
        } else if (this.b == null || this.c == null || this.d == null) {
            str = "EventUploadModel";
            str2 = "model in wrong format";
        } else {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("header", this.b.c());
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObjectC = this.d.c();
            jSONObjectC.put("properties", this.c.c());
            try {
                jSONObjectC.put("events_global_properties", new JSONObject(this.e));
            } catch (JSONException unused) {
                jSONObjectC.put("events_global_properties", this.e);
            }
            jSONObject2.put("events_common", jSONObjectC);
            JSONArray jSONArray = new JSONArray();
            Iterator<d> it = this.a.iterator();
            while (it.hasNext()) {
                JSONObject jSONObjectC2 = it.next().c();
                if (jSONObjectC2 != null) {
                    jSONArray.put(jSONObjectC2);
                } else {
                    com.huawei.a.a.b.e.a.c("EventUploadModel", "custom event is empty,delete this event");
                }
            }
            jSONObject2.put("events", jSONArray);
            try {
                String strA = com.huawei.a.a.b.a.b.a().a(b.a.AES).a(this.f, com.huawei.a.a.b.k.d.a(jSONObject2.toString().getBytes(C.UTF8_NAME)));
                if (TextUtils.isEmpty(strA)) {
                    com.huawei.a.a.b.e.a.c("EventUploadModel", "eventInfo encrypt failed,report over!");
                    return null;
                }
                jSONObject.put("event", strA);
                return jSONObject;
            } catch (UnsupportedEncodingException unused2) {
                str = "EventUploadModel";
                str2 = "getBitZip(): Unsupported coding : utf-8";
            }
        }
        com.huawei.a.a.b.e.a.c(str, str2);
        return null;
    }
}
