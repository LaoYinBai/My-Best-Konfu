package com.xiaomi.push;

import android.os.Bundle;
import com.ss.union.game.sdk.core.glide.request.target.Target;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class gi extends gg {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private a f431a;

    /* renamed from: a, reason: collision with other field name */
    private b f432a;
    private String b;

    public enum a {
        chat,
        available,
        away,
        xa,
        dnd
    }

    public enum b {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public gi(Bundle bundle) {
        super(bundle);
        this.f432a = b.available;
        this.b = null;
        this.a = Target.SIZE_ORIGINAL;
        this.f431a = null;
        if (bundle.containsKey("ext_pres_type")) {
            this.f432a = b.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.b = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.a = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.f431a = a.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public gi(b bVar) {
        this.f432a = b.available;
        this.b = null;
        this.a = Target.SIZE_ORIGINAL;
        this.f431a = null;
        a(bVar);
    }

    @Override // com.xiaomi.push.gg
    public Bundle a() {
        Bundle bundleA = super.a();
        if (this.f432a != null) {
            bundleA.putString("ext_pres_type", this.f432a.toString());
        }
        if (this.b != null) {
            bundleA.putString("ext_pres_status", this.b);
        }
        if (this.a != Integer.MIN_VALUE) {
            bundleA.putInt("ext_pres_prio", this.a);
        }
        if (this.f431a != null && this.f431a != a.available) {
            bundleA.putString("ext_pres_mode", this.f431a.toString());
        }
        return bundleA;
    }

    @Override // com.xiaomi.push.gg
    /* renamed from: a */
    public String mo354a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<presence");
        if (p() != null) {
            sb.append(" xmlns=\"");
            sb.append(p());
            sb.append("\"");
        }
        if (j() != null) {
            sb.append(" id=\"");
            sb.append(j());
            sb.append("\"");
        }
        if (l() != null) {
            sb.append(" to=\"");
            sb.append(gr.a(l()));
            sb.append("\"");
        }
        if (m() != null) {
            sb.append(" from=\"");
            sb.append(gr.a(m()));
            sb.append("\"");
        }
        if (k() != null) {
            sb.append(" chid=\"");
            sb.append(gr.a(k()));
            sb.append("\"");
        }
        if (this.f432a != null) {
            sb.append(" type=\"");
            sb.append(this.f432a);
            sb.append("\"");
        }
        sb.append(">");
        if (this.b != null) {
            sb.append("<status>");
            sb.append(gr.a(this.b));
            sb.append("</status>");
        }
        if (this.a != Integer.MIN_VALUE) {
            sb.append("<priority>");
            sb.append(this.a);
            sb.append("</priority>");
        }
        if (this.f431a != null && this.f431a != a.available) {
            sb.append("<show>");
            sb.append(this.f431a);
            sb.append("</show>");
        }
        sb.append(o());
        gk gkVarA = a();
        if (gkVarA != null) {
            sb.append(gkVarA.m358a());
        }
        sb.append("</presence>");
        return sb.toString();
    }

    public void a(int i) {
        if (i >= -128 && i <= 128) {
            this.a = i;
            return;
        }
        throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
    }

    public void a(a aVar) {
        this.f431a = aVar;
    }

    public void a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.f432a = bVar;
    }

    public void a(String str) {
        this.b = str;
    }
}
