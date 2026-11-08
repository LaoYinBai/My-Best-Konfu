package com.unicom.xiaowo.login.b;

import android.content.Context;
import android.text.TextUtils;
import com.unicom.xiaowo.login.c.j;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class c implements j {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ Context c;

    c(a aVar, int i, String str, Context context) {
        this.a = i;
        this.b = str;
        this.c = context;
    }

    @Override // com.unicom.xiaowo.login.c.j
    public final void a(int i, String str) throws JSONException {
        d dVarA;
        String str2;
        if (i != 1) {
            d.a().a(this.c, "网络请求失败");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (TextUtils.isEmpty(jSONObject.optString("access_token"))) {
                d.a().a(this.c, str);
                return;
            }
            if (this.a == 1) {
                jSONObject.put("mobile", this.b);
                dVarA = d.a();
                Context context = this.c;
                str2 = "登录成功";
            } else {
                dVarA = d.a();
                Context context2 = this.c;
                str2 = "获取token成功";
            }
            dVarA.a(str2, jSONObject.toString());
        } catch (Exception unused) {
            d.a().a(this.c, "数据解析异常");
        }
    }
}
