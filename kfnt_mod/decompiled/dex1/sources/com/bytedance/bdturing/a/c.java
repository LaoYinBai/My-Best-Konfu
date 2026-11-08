package com.bytedance.bdturing.a;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.bytedance.bdturing.e;
import com.bytedance.bdturing.g;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3759a = "UrlBuilder";

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.bdturing.c f3760b;

    /* renamed from: c, reason: collision with root package name */
    private b f3761c;
    private boolean d = false;

    public c(com.bytedance.bdturing.c cVar) {
        this.f3760b = cVar;
        this.f3761c = new b(cVar.c(), cVar.o());
    }

    public void a(Context context) {
        if (this.d) {
            return;
        }
        this.f3761c.a(context.getApplicationContext(), this.f3760b.b());
        this.d = true;
    }

    public void a() {
        this.f3761c.g();
    }

    public String a(Activity activity, int i) {
        String str;
        this.f3761c.a(this.f3760b.b());
        String strE = null;
        switch (i) {
            case 1:
                strE = this.f3761c.e();
                str = "&scene=" + this.f3760b.x();
                break;
            case 2:
                strE = this.f3761c.d();
                str = "&challenge_code=" + this.f3760b.w();
                break;
            default:
                str = null;
                break;
        }
        String strF = this.f3761c.f();
        if (g.c()) {
            if (this.f3760b.p() != null && !this.f3760b.p().isEmpty()) {
                strE = this.f3760b.p();
            }
            if (this.f3760b.q() != null && !this.f3760b.q().isEmpty()) {
                strF = this.f3760b.q();
            }
            Toast.makeText(activity, "url = " + strE + ", verifyHost = " + strF, 1).show();
        }
        try {
            strF = URLEncoder.encode(strF, "utf-8");
        } catch (UnsupportedEncodingException e) {
            g.a(e);
        }
        return (strE + "?") + "aid=" + this.f3760b.c() + "&lang=" + this.f3760b.f() + "&locale=" + this.f3760b.v() + "&app_name=" + this.f3760b.d() + "&ch=" + this.f3760b.i() + "&os_type=" + this.f3760b.a() + "&sdk_version=" + this.f3760b.g() + "&app_key=" + this.f3760b.h() + "&iid=" + this.f3760b.r() + "&vc=" + this.f3760b.e() + "&os_name=" + this.f3760b.m() + "&os_version=" + this.f3760b.l() + "&did=" + this.f3760b.s() + str + "&user_id=" + this.f3760b.t() + "&session_id=" + this.f3760b.u() + "&orientation=" + a(activity) + "&resolution=" + b(activity) + "&region=" + this.f3760b.b().a() + "&device_brand=" + this.f3760b.j() + "&device_model=" + this.f3760b.k() + "&verify_host=" + strF;
    }

    private int a(Activity activity) throws JSONException {
        int i;
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        g.a(f3759a, "rotation current " + rotation);
        switch (rotation) {
            case 0:
            case 2:
                i = 2;
                break;
            case 1:
            case 3:
                i = 1;
                break;
            default:
                return 0;
        }
        e.d(i);
        return i;
    }

    private String b(Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }
}
