package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IOAdEventListener;
import com.baidu.mobads.sdk.api.IXAdContainerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class ar extends Observable {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3124a = "XAbstractProdTemplate";

    /* renamed from: b, reason: collision with root package name */
    public static final String f3125b = "error_message";

    /* renamed from: c, reason: collision with root package name */
    public static final String f3126c = "error_code";
    protected static final String d = "instanceInfo";
    protected static final String e = "showState";
    protected RelativeLayout f;
    protected Context g;
    public HashMap<String, String> l;
    public String m;
    public String n;
    protected bb h = bb.a();
    public IAdInterListener j = null;
    public boolean k = true;
    public IOAdEventListener i = new a();

    protected void a(IOAdEvent iOAdEvent) {
    }

    protected void a(String str, boolean z) {
    }

    protected void b(IOAdEvent iOAdEvent) {
    }

    protected void b(String str, boolean z) {
    }

    protected void b(boolean z) {
    }

    public abstract void b_();

    protected void c() {
    }

    protected void c(IOAdEvent iOAdEvent) {
    }

    protected void c(String str) {
    }

    protected void d(IOAdEvent iOAdEvent) {
    }

    protected void d(String str) {
    }

    protected void d_() {
    }

    protected void e() {
    }

    protected void e(IOAdEvent iOAdEvent) {
    }

    protected void e(String str) {
    }

    protected void f(IOAdEvent iOAdEvent) {
    }

    protected void h(IOAdEvent iOAdEvent) {
    }

    protected void q() {
    }

    protected void s() {
    }

    protected void t() {
    }

    protected void u() {
    }

    public ar(Context context) {
        this.g = context;
        u.a().a(this.g, new as(this));
    }

    public void i() {
        this.j = (IAdInterListener) aj.a(r.k, ba.a(this.g), (Class<?>[]) new Class[]{Context.class}, this.g);
        if (this.k) {
            return;
        }
        b_();
    }

    public void j() {
        a("", 1);
    }

    public void a(View view, JSONObject jSONObject) {
        if (this.j != null) {
            this.j.onAdTaskProcess(view, a(IAdInterListener.AdCommandType.AD_IMPRESSION, jSONObject));
        }
    }

    public void b(View view, JSONObject jSONObject) {
        if (this.j != null) {
            this.j.onAdTaskProcess(view, a(IAdInterListener.AdCommandType.AD_CLICK, jSONObject));
        }
    }

    public void a(JSONObject jSONObject) {
        if (this.j != null) {
            this.j.onAdTaskProcess(a(IAdInterListener.AdCommandType.HANDLE_EVENT, jSONObject));
        }
    }

    public void a(JSONObject jSONObject, Map<String, Object> map) {
        if (this.j != null) {
            this.j.onAdTaskProcess(a(IAdInterListener.AdCommandType.HANDLE_EVENT, jSONObject), map);
        }
    }

    public void a(String str, Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_type", "server_bidding");
            jSONObject.put("msg", str);
            if (this.j != null) {
                this.j.onAdTaskProcess(a(IAdInterListener.AdCommandType.HANDLE_EVENT, jSONObject), map);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, boolean z, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("uniqueId", str);
            jSONObject.put("result", z);
            jSONObject.put("replacement", str2);
            if (this.j != null) {
                this.j.onAdTaskProcess(a("onBiddingResult", jSONObject), (Map<String, Object>) null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, HashMap<String, Object> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_type", str);
            if (this.j != null) {
                this.j.onAdTaskProcess(a(IAdInterListener.AdCommandType.NOVEL_EVENT, jSONObject), map);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject k() {
        return new JSONObject();
    }

    public JSONObject l() {
        return new JSONObject();
    }

    public String m() {
        JSONObject jSONObjectK = k();
        JSONObject jSONObjectL = l();
        HashMap map = new HashMap();
        map.put("param_info", jSONObjectK);
        map.put("ad_buss_param", jSONObjectL);
        a("get_request_token", (Map<String, Object>) map);
        Object obj = map.get("request_token");
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            a("bidding data is empty", 2);
        }
        if (this.j != null) {
            HashMap map = new HashMap();
            map.put("bidding_data", str);
            a("load_bidding_data", (Map<String, Object>) map);
            return;
        }
        a("Initialization doesn't finish yet.", 1);
    }

    public void n() {
        if (this.j != null) {
            this.j.addEventListener(r.F, this.i);
            this.j.addEventListener(r.H, this.i);
            this.j.addEventListener(r.J, this.i);
            this.j.addEventListener(r.K, this.i);
            this.j.addEventListener(r.U, this.i);
            this.j.addEventListener(r.p, this.i);
            this.j.addEventListener(r.V, this.i);
            this.j.addEventListener(r.q, this.i);
            this.j.addEventListener(r.L, this.i);
            this.j.addEventListener(r.M, this.i);
            this.j.addEventListener(r.I, this.i);
            this.j.addEventListener(r.B, this.i);
            this.j.addEventListener(r.aa, this.i);
            this.j.addEventListener(r.ab, this.i);
            this.j.addEventListener(r.Y, this.i);
            this.j.addEventListener(r.T, this.i);
            this.j.addEventListener(r.ac, this.i);
            this.j.addEventListener(r.ad, this.i);
            this.j.addEventListener(r.ae, this.i);
            this.j.addEventListener(r.af, this.i);
            this.j.addEventListener(r.ag, this.i);
            this.j.addEventListener(r.ah, this.i);
            this.j.addEventListener(r.Z, this.i);
            this.j.addEventListener(r.W, this.i);
        }
    }

    public void o() {
        if (this.j != null) {
            this.j.onAttachedToWindow();
        }
    }

    public void p() {
        if (this.j != null) {
            this.j.onDetachedFromWindow();
        }
    }

    public void b(int i) {
        if (this.j != null) {
            this.j.onWindowVisibilityChanged(i);
        }
    }

    public void a(boolean z) {
        if (this.j != null) {
            this.j.onWindowFocusChanged(z);
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.j != null) {
            return this.j.onKeyDown(i, keyEvent);
        }
        return false;
    }

    public void d() {
        if (this.j != null) {
            this.j.destroyAd();
        }
    }

    protected void g(IOAdEvent iOAdEvent) {
        r();
    }

    protected void a(int i, String str) {
        r();
    }

    protected void a(String str, int i) {
        r();
    }

    public void r() {
        if (this.j != null) {
            this.j.removeAllListeners();
        }
    }

    public class a implements IOAdEventListener {
        public a() {
        }

        @Override // com.baidu.mobads.sdk.api.IOAdEventListener
        public void run(IOAdEvent iOAdEvent) {
            ar.a(new au(this, iOAdEvent));
        }

        private String a(IOAdEvent iOAdEvent) {
            Map<String, Object> data;
            Object obj;
            if (iOAdEvent == null) {
                return null;
            }
            String message = iOAdEvent.getMessage();
            return (!TextUtils.isEmpty(message) || (data = iOAdEvent.getData()) == null || (obj = data.get("msg")) == null || !(obj instanceof String)) ? message : (String) obj;
        }
    }

    public static void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                runnable.run();
            } else {
                new Handler(Looper.getMainLooper()).post(new at(runnable));
            }
        } catch (Exception unused) {
        }
    }

    public void a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        this.l = (HashMap) map;
    }

    public View v() {
        if (this.j != null) {
            return this.j.getAdContainerView();
        }
        return null;
    }

    public JSONObject b(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            return new JSONObject(map);
        } catch (Exception unused) {
            return null;
        }
    }

    public void f(String str) {
        this.n = str;
    }

    private String a(String str, JSONObject jSONObject) {
        return "bdsdk://" + str + "?jsonObj=" + Uri.encode(jSONObject.toString());
    }

    public String g(String str) {
        IXAdContainerFactory iXAdContainerFactoryC;
        u uVarA = u.a();
        if (uVarA != null && (iXAdContainerFactoryC = uVarA.c()) != null) {
            Object remoteParam = iXAdContainerFactoryC.getRemoteParam(str, new Object[0]);
            if (remoteParam instanceof String) {
                return (String) remoteParam;
            }
        }
        return null;
    }
}
