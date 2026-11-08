package com.alibaba.sdk.android.httpdns.a;

import android.content.Context;
import android.util.Log;
import com.alibaba.sdk.android.beacon.Beacon;
import com.alibaba.sdk.android.httpdns.d.b;
import com.alibaba.sdk.android.httpdns.i;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f2319a;
    private Context mContext = null;

    /* renamed from: a, reason: collision with other field name */
    private b f8a = null;

    /* renamed from: a, reason: collision with other field name */
    private Beacon f7a = null;
    private boolean i = true;

    /* renamed from: a, reason: collision with other field name */
    private final Beacon.OnUpdateListener f6a = new Beacon.OnUpdateListener() { // from class: com.alibaba.sdk.android.httpdns.a.a.1
        @Override // com.alibaba.sdk.android.beacon.Beacon.OnUpdateListener
        public void onUpdate(List<Beacon.Config> list) {
            try {
                a.this.b(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /* renamed from: a, reason: collision with other field name */
    private final Beacon.OnServiceErrListener f5a = new Beacon.OnServiceErrListener() { // from class: com.alibaba.sdk.android.httpdns.a.a.2
        @Override // com.alibaba.sdk.android.beacon.Beacon.OnServiceErrListener
        public void onErr(Beacon.Error error) {
            Log.e("HTTPDNS:BeaconManager", "beacon error. errorCode:" + error.errCode + ", errorMsg:" + error.errMsg);
        }
    };

    private a() {
    }

    public static a a() {
        if (f2319a == null) {
            synchronized (a.class) {
                if (f2319a == null) {
                    f2319a = new a();
                }
            }
        }
        return f2319a;
    }

    private boolean a(Beacon.Config config) {
        if (config == null || !config.key.equalsIgnoreCase("___httpdns_service___")) {
            return false;
        }
        String str = config.value;
        if (str != null) {
            Log.d("HTTPDNS:BeaconManager", "httpdns configs:" + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("ut")) {
                    d(jSONObject.getString("ut"));
                }
                if (jSONObject.has("ip-ranking")) {
                    i(jSONObject.getString("ip-ranking"));
                }
                if (jSONObject.has("status")) {
                    j(jSONObject.getString("status"));
                }
            } catch (Exception e) {
                Log.e("HTTPDNS:BeaconManager", "parse push configs failed.", e);
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Beacon.Config> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Beacon.Config config : list) {
            if (config.key.equalsIgnoreCase("___httpdns_service___")) {
                a(config);
            }
        }
    }

    private boolean d(String str) {
        if (str == null || this.f8a == null) {
            return false;
        }
        Log.d("HTTPDNS:BeaconManager", "is report enabled:" + str);
        if (str.equalsIgnoreCase("disabled")) {
            this.f8a.e(false);
        } else {
            this.f8a.e(true);
        }
        return true;
    }

    private void i(String str) {
        if (str != null) {
            Log.d("HTTPDNS:BeaconManager", "is IP probe enabled:" + str);
            this.i = !str.equalsIgnoreCase("disabled");
        }
    }

    private void j(String str) {
        if (str != null) {
            com.alibaba.sdk.android.httpdns.b.a(!"disabled".equals(str));
            i.e("[beacon] httpdns enable: " + com.alibaba.sdk.android.httpdns.b.a());
        }
    }

    public void a(Context context, String str) {
        this.mContext = context;
        if (this.mContext != null) {
            HashMap map = new HashMap();
            map.put("sdkId", "httpdns");
            map.put("accountId", str);
            this.f7a = new Beacon.Builder().appKey("24657847").appSecret("f30fc0937f2b1e9e50a1b7134f1ddb10").loopInterval(7200000L).extras(map).build();
            this.f7a.addUpdateListener(this.f6a);
            this.f7a.addServiceErrListener(this.f5a);
            this.f7a.start(this.mContext.getApplicationContext());
        }
    }

    public void a(b bVar) {
        this.f8a = bVar;
    }

    public boolean f() {
        return this.i;
    }
}
