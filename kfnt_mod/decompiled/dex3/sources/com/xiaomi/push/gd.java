package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class gd implements gh {
    private String a;

    /* renamed from: a, reason: collision with other field name */
    private List<gd> f420a;

    /* renamed from: a, reason: collision with other field name */
    private String[] f421a;
    private String b;

    /* renamed from: b, reason: collision with other field name */
    private String[] f422b;
    private String c;

    public gd(String str, String str2, String[] strArr, String[] strArr2) {
        this.f421a = null;
        this.f422b = null;
        this.f420a = null;
        this.a = str;
        this.b = str2;
        this.f421a = strArr;
        this.f422b = strArr2;
    }

    public gd(String str, String str2, String[] strArr, String[] strArr2, String str3, List<gd> list) {
        this.f421a = null;
        this.f422b = null;
        this.f420a = null;
        this.a = str;
        this.b = str2;
        this.f421a = strArr;
        this.f422b = strArr2;
        this.c = str3;
        this.f420a = list;
    }

    public static gd a(Bundle bundle) {
        ArrayList arrayList;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> setKeySet = bundle2.keySet();
        String[] strArr = new String[setKeySet.size()];
        String[] strArr2 = new String[setKeySet.size()];
        int i = 0;
        for (String str : setKeySet) {
            strArr[i] = str;
            strArr2[i] = bundle2.getString(str);
            i++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            ArrayList arrayList2 = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                arrayList2.add(a((Bundle) parcelable));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return new gd(string, string2, strArr, strArr2, string3, arrayList);
    }

    public static Parcelable[] a(List<gd> list) {
        return a((gd[]) list.toArray(new gd[list.size()]));
    }

    public static Parcelable[] a(gd[] gdVarArr) {
        if (gdVarArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[gdVarArr.length];
        for (int i = 0; i < gdVarArr.length; i++) {
            parcelableArr[i] = gdVarArr[i].m350a();
        }
        return parcelableArr;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.a);
        bundle.putString("ext_ns", this.b);
        bundle.putString("ext_text", this.c);
        Bundle bundle2 = new Bundle();
        if (this.f421a != null && this.f421a.length > 0) {
            for (int i = 0; i < this.f421a.length; i++) {
                bundle2.putString(this.f421a[i], this.f422b[i]);
            }
        }
        bundle.putBundle("attributes", bundle2);
        if (this.f420a != null && this.f420a.size() > 0) {
            bundle.putParcelableArray("children", a(this.f420a));
        }
        return bundle;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Parcelable m350a() {
        return a();
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m351a() {
        return this.a;
    }

    public String a(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.f421a == null) {
            return null;
        }
        for (int i = 0; i < this.f421a.length; i++) {
            if (str.equals(this.f421a[i])) {
                return this.f422b[i];
            }
        }
        return null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m352a(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = gr.a(str);
        }
        this.c = str;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return !TextUtils.isEmpty(this.c) ? gr.b(this.c) : this.c;
    }

    @Override // com.xiaomi.push.gh
    public String d() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.a);
        if (!TextUtils.isEmpty(this.b)) {
            sb.append(" ");
            sb.append("xmlns=");
            sb.append("\"");
            sb.append(this.b);
            sb.append("\"");
        }
        if (this.f421a != null && this.f421a.length > 0) {
            for (int i = 0; i < this.f421a.length; i++) {
                if (!TextUtils.isEmpty(this.f422b[i])) {
                    sb.append(" ");
                    sb.append(this.f421a[i]);
                    sb.append("=\"");
                    sb.append(gr.a(this.f422b[i]));
                    sb.append("\"");
                }
            }
        }
        if (!TextUtils.isEmpty(this.c)) {
            sb.append(">");
            sb.append(this.c);
        } else {
            if (this.f420a == null || this.f420a.size() <= 0) {
                str = "/>";
                sb.append(str);
                return sb.toString();
            }
            sb.append(">");
            Iterator<gd> it = this.f420a.iterator();
            while (it.hasNext()) {
                sb.append(it.next().d());
            }
        }
        sb.append("</");
        sb.append(this.a);
        str = ">";
        sb.append(str);
        return sb.toString();
    }

    public String toString() {
        return d();
    }
}
