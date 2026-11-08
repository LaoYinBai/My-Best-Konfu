package com.unicom.xiaowo.login.b;

import android.content.Context;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.unicom.xiaowo.login.c.j;
import com.unicom.xiaowo.login.d.f;
import com.unicom.xiaowo.login.d.g;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b implements j {
    private /* synthetic */ int a;
    private /* synthetic */ Context b;
    private /* synthetic */ a c;

    b(a aVar, int i, Context context) {
        this.c = aVar;
        this.a = i;
        this.b = context;
    }

    @Override // com.unicom.xiaowo.login.c.j
    public final void a(int i, String str) throws JSONException, UnsupportedEncodingException {
        d dVarA;
        Context context;
        String str2;
        if (i == 1) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("code");
                String strOptString2 = jSONObject.optString("msg");
                String strOptString3 = jSONObject.optString("data");
                if (!Constants.FAIL.equals(strOptString)) {
                    d dVarA2 = d.a();
                    Context context2 = this.b;
                    dVarA2.b(strOptString2, strOptString3);
                    return;
                }
                String strDecode = URLDecoder.decode(com.unicom.xiaowo.login.a.a.b(strOptString3, g.a()), "UTF-8");
                f.a("data:" + strDecode);
                if (this.a != 1) {
                    d dVarA3 = d.a();
                    Context context3 = this.b;
                    dVarA3.a("获取accessCode成功", strDecode);
                    return;
                } else {
                    JSONObject jSONObject2 = new JSONObject(strDecode);
                    this.c.a(this.b, jSONObject2.optString("accessCode"), jSONObject2.optString("mobile"), this.a);
                    return;
                }
            } catch (Exception unused) {
                dVarA = d.a();
                context = this.b;
                str2 = "数据解析异常";
            }
        } else if (!TextUtils.isEmpty(str)) {
            d.a().a(this.b, str);
            return;
        } else {
            dVarA = d.a();
            context = this.b;
            str2 = "网络请求失败";
        }
        dVarA.a(context, str2);
    }
}
