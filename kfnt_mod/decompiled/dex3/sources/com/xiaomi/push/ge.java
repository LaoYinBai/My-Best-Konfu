package com.xiaomi.push;

import android.os.Bundle;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.taobao.accs.common.Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ge extends gg {
    private a a;

    /* renamed from: a, reason: collision with other field name */
    private final Map<String, String> f423a;

    public static class a {
        public static final a a = new a(MonitorConstants.CONNECT_TYPE_GET);
        public static final a b = new a("set");
        public static final a c = new a("result");
        public static final a d = new a("error");
        public static final a e = new a(Constants.KEY_COMMAND);

        /* renamed from: a, reason: collision with other field name */
        private String f424a;

        private a(String str) {
            this.f424a = str;
        }

        public static a a(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            if (a.toString().equals(lowerCase)) {
                return a;
            }
            if (b.toString().equals(lowerCase)) {
                return b;
            }
            if (d.toString().equals(lowerCase)) {
                return d;
            }
            if (c.toString().equals(lowerCase)) {
                return c;
            }
            if (e.toString().equals(lowerCase)) {
                return e;
            }
            return null;
        }

        public String toString() {
            return this.f424a;
        }
    }

    public ge() {
        this.a = a.a;
        this.f423a = new HashMap();
    }

    public ge(Bundle bundle) {
        super(bundle);
        this.a = a.a;
        this.f423a = new HashMap();
        if (bundle.containsKey("ext_iq_type")) {
            this.a = a.a(bundle.getString("ext_iq_type"));
        }
    }

    @Override // com.xiaomi.push.gg
    public Bundle a() {
        Bundle bundleA = super.a();
        if (this.a != null) {
            bundleA.putString("ext_iq_type", this.a.toString());
        }
        return bundleA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public a m353a() {
        return this.a;
    }

    @Override // com.xiaomi.push.gg
    /* renamed from: a, reason: collision with other method in class */
    public String mo354a() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("<iq ");
        if (j() != null) {
            sb.append("id=\"" + j() + "\" ");
        }
        if (l() != null) {
            sb.append("to=\"");
            sb.append(gr.a(l()));
            sb.append("\" ");
        }
        if (m() != null) {
            sb.append("from=\"");
            sb.append(gr.a(m()));
            sb.append("\" ");
        }
        if (k() != null) {
            sb.append("chid=\"");
            sb.append(gr.a(k()));
            sb.append("\" ");
        }
        for (Map.Entry<String, String> entry : this.f423a.entrySet()) {
            sb.append(gr.a(entry.getKey()));
            sb.append("=\"");
            sb.append(gr.a(entry.getValue()));
            sb.append("\" ");
        }
        if (this.a == null) {
            str = "type=\"get\">";
        } else {
            sb.append("type=\"");
            sb.append(m353a());
            str = "\">";
        }
        sb.append(str);
        String strB = b();
        if (strB != null) {
            sb.append(strB);
        }
        sb.append(o());
        gk gkVarA = a();
        if (gkVarA != null) {
            sb.append(gkVarA.m358a());
        }
        sb.append("</iq>");
        return sb.toString();
    }

    public void a(a aVar) {
        if (aVar == null) {
            aVar = a.a;
        }
        this.a = aVar;
    }

    public synchronized void a(Map<String, String> map) {
        this.f423a.putAll(map);
    }

    public String b() {
        return null;
    }
}
