package com.bytedance.push.i;

import android.text.TextUtils;
import com.bytedance.b.a.a.c.e;
import com.bytedance.b.a.c.h;
import com.bytedance.common.push.utility.f;
import com.bytedance.push.ICallback;
import com.bytedance.push.d;
import com.bytedance.push.i;
import com.ss.android.pushmanager.a.a;
import com.ss.android.pushmanager.setting.PushSetting;
import java.nio.charset.Charset;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final String f4845a;

    /* renamed from: b, reason: collision with root package name */
    private final ICallback f4846b;

    public a(String str, ICallback iCallback) {
        this.f4845a = str;
        this.f4846b = iCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.bytedance.push.i.a.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (a.class) {
                    a.this.a();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() throws JSONException {
        if (TextUtils.equals(this.f4845a, PushSetting.getInstance().getAlias())) {
            a(0, "");
            return;
        }
        a.b bVarB = com.ss.android.pushmanager.a.a.b();
        d dVarD = null;
        try {
            dVarD = i.j().d();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bVarB == null || dVarD == null) {
            a(10, "请先初始化BDPush");
            return;
        }
        HashMap map = new HashMap();
        PushSetting.getInstance().getSSIDs(map);
        String str = (String) map.get(h.x);
        String strA = com.ss.android.message.a.b.a(com.ss.android.pushmanager.a.a("/bytepush/set_alias"), bVarB.f());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", dVarD.f4771b);
            jSONObject.put("did", str);
            jSONObject.put("alias", this.f4845a);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            String strA2 = f.a().a(strA, jSONObject.toString().getBytes(Charset.forName("UTF-8")), false, e.f3610c, false);
            if (TextUtils.isEmpty(strA2)) {
                a(12, "服务异常");
                return;
            }
            JSONObject jSONObject2 = new JSONObject(strA2);
            int iOptInt = jSONObject2.optInt("status_code");
            if (iOptInt != 0) {
                a(iOptInt, jSONObject2.optString("message"));
            } else {
                PushSetting.getInstance().setAlias(this.f4845a);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            a(11, "网络异常，请求失败");
        }
    }

    private void a(int i, String str) {
        if (this.f4846b != null) {
            this.f4846b.onMessage(i, str);
        }
    }
}
