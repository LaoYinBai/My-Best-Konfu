package c.a.a.a.a;

import android.content.Context;
import android.util.Log;
import anet.channel.strategy.dispatch.DispatchConstants;
import java.util.Calendar;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class am implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private static int f2063a = 3;

    /* renamed from: b, reason: collision with root package name */
    private ap f2064b;

    /* renamed from: c, reason: collision with root package name */
    private Context f2065c;

    am(Context context, ap apVar) {
        this.f2064b = apVar;
        this.f2065c = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        byte[] bArr;
        String strValueOf = String.valueOf(System.currentTimeMillis());
        try {
            str = at.c() + "/v1/getInfo";
            long timeInMillis = com.ss.sys.ces.b.b().a ? Calendar.getInstance().getTimeInMillis() : 0L;
            bArr = (byte[]) com.ss.sys.ces.a.meta(301, this.f2065c, (Object) null);
            long timeInMillis2 = com.ss.sys.ces.b.b().a ? Calendar.getInstance().getTimeInMillis() : 0L;
            if (com.ss.sys.ces.b.b().a) {
                Log.d("CZL_Efficient", "[Efficient] selas : " + (timeInMillis2 - timeInMillis));
            }
        } catch (Throwable unused) {
            aj.f2060a = 500;
        }
        if (bArr == null || bArr.length <= 0) {
            throw new NullPointerException("NullPointerException");
        }
        HashMap map = new HashMap();
        map.put("os", DispatchConstants.ANDROID);
        map.put("app_id", aj.f2062c + "");
        map.put("version", "1.0.3");
        map.put("version_code", "4");
        map.put(com.bytedance.b.a.a.a.h, strValueOf);
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= f2063a) {
                break;
            }
            JSONObject jSONObject = new JSONObject(av.a(str, map, bArr));
            i2 = jSONObject.getInt("code");
            if (i2 == 500) {
                i++;
                bm.a(i * 3 * 1000);
            } else if (i2 == 200) {
                aj.f2061b = true;
                aj.d = jSONObject.getString("token_id");
            } else if (i2 == 202) {
                aj.f2061b = true;
                String string = jSONObject.getString("token_id");
                aj.d = string;
                com.ss.sys.ces.a.meta(302, this.f2065c, string);
            }
        }
        aj.f2060a = i2;
        bg.c("Init finish");
        ap apVar = this.f2064b;
        if (apVar != null) {
            apVar.a(aj.a());
        }
    }
}
