package anet.channel.strategy;

import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.util.ALog;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {
    public static d a(JSONObject jSONObject) {
        try {
            return new d(jSONObject);
        } catch (Exception e2) {
            ALog.e("StrategyResultParser", "Parse HttpDns response failed.", null, e2, "JSON Content", jSONObject.toString());
            return null;
        }
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public final String f582a;

        /* renamed from: b, reason: collision with root package name */
        public final a f583b;

        /* renamed from: c, reason: collision with root package name */
        public final String f584c;

        public e(JSONObject jSONObject) {
            this.f582a = jSONObject.optString("ip");
            this.f584c = jSONObject.optString("path");
            this.f583b = new a(jSONObject);
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final int f571a;

        /* renamed from: b, reason: collision with root package name */
        public final String f572b;

        /* renamed from: c, reason: collision with root package name */
        public final int f573c;
        public final int d;
        public final int e;
        public final int f;
        public final String g;
        public final String h;

        public a(JSONObject jSONObject) {
            this.f571a = jSONObject.optInt("port");
            this.f572b = jSONObject.optString("protocol");
            this.f573c = jSONObject.optInt("cto");
            this.d = jSONObject.optInt("rto");
            this.e = jSONObject.optInt("retry");
            this.f = jSONObject.optInt("heartbeat");
            this.g = jSONObject.optString("rtt", "");
            this.h = jSONObject.optString("publickey");
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        public final String f577a;

        /* renamed from: b, reason: collision with root package name */
        public final e[] f578b;

        public c(JSONObject jSONObject) {
            this.f577a = jSONObject.optString(com.alipay.sdk.cons.c.f);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("strategies");
            if (jSONArrayOptJSONArray != null) {
                int length = jSONArrayOptJSONArray.length();
                this.f578b = new e[length];
                for (int i = 0; i < length; i++) {
                    this.f578b[i] = new e(jSONArrayOptJSONArray.optJSONObject(i));
                }
                return;
            }
            this.f578b = null;
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public final String f574a;

        /* renamed from: b, reason: collision with root package name */
        public final int f575b;

        /* renamed from: c, reason: collision with root package name */
        public final String f576c;
        public final String d;
        public final String e;
        public final String[] f;
        public final String[] g;
        public final a[] h;
        public final e[] i;
        public final boolean j;
        public final boolean k;

        public b(JSONObject jSONObject) {
            this.f574a = jSONObject.optString(com.alipay.sdk.cons.c.f);
            this.f575b = jSONObject.optInt("ttl");
            this.f576c = jSONObject.optString("safeAisles");
            this.d = jSONObject.optString("cname", null);
            this.e = jSONObject.optString("unit", null);
            this.j = jSONObject.optInt("clear") == 1;
            this.k = jSONObject.optBoolean("effectNow");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ips");
            if (jSONArrayOptJSONArray != null) {
                int length = jSONArrayOptJSONArray.length();
                this.f = new String[length];
                for (int i = 0; i < length; i++) {
                    this.f[i] = jSONArrayOptJSONArray.optString(i);
                }
            } else {
                this.f = null;
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("sips");
            if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                int length2 = jSONArrayOptJSONArray2.length();
                this.g = new String[length2];
                for (int i2 = 0; i2 < length2; i2++) {
                    this.g[i2] = jSONArrayOptJSONArray2.optString(i2);
                }
            } else {
                this.g = null;
            }
            JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("aisles");
            if (jSONArrayOptJSONArray3 != null) {
                int length3 = jSONArrayOptJSONArray3.length();
                this.h = new a[length3];
                for (int i3 = 0; i3 < length3; i3++) {
                    this.h[i3] = new a(jSONArrayOptJSONArray3.optJSONObject(i3));
                }
            } else {
                this.h = null;
            }
            JSONArray jSONArrayOptJSONArray4 = jSONObject.optJSONArray("strategies");
            if (jSONArrayOptJSONArray4 != null && jSONArrayOptJSONArray4.length() > 0) {
                int length4 = jSONArrayOptJSONArray4.length();
                this.i = new e[length4];
                for (int i4 = 0; i4 < length4; i4++) {
                    this.i[i4] = new e(jSONArrayOptJSONArray4.optJSONObject(i4));
                }
                return;
            }
            this.i = null;
        }
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        public final String f579a;

        /* renamed from: b, reason: collision with root package name */
        public final b[] f580b;

        /* renamed from: c, reason: collision with root package name */
        public final c[] f581c;
        public final String d;
        public final String e;
        public final int f;
        public final int g;
        public final int h;

        public d(JSONObject jSONObject) {
            this.f579a = jSONObject.optString("ip");
            this.d = jSONObject.optString(IWeiboService.ResponseConstants.UID, null);
            this.e = jSONObject.optString(com.alipay.sdk.cons.b.g, null);
            this.f = jSONObject.optInt(DispatchConstants.CONFIG_VERSION);
            this.g = jSONObject.optInt("fcl");
            this.h = jSONObject.optInt("fct");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("dns");
            if (jSONArrayOptJSONArray != null) {
                int length = jSONArrayOptJSONArray.length();
                this.f580b = new b[length];
                for (int i = 0; i < length; i++) {
                    this.f580b[i] = new b(jSONArrayOptJSONArray.optJSONObject(i));
                }
            } else {
                this.f580b = null;
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("hrTask");
            if (jSONArrayOptJSONArray2 != null) {
                int length2 = jSONArrayOptJSONArray2.length();
                this.f581c = new c[length2];
                for (int i2 = 0; i2 < length2; i2++) {
                    this.f581c[i2] = new c(jSONArrayOptJSONArray2.optJSONObject(i2));
                }
                return;
            }
            this.f581c = null;
        }
    }
}
