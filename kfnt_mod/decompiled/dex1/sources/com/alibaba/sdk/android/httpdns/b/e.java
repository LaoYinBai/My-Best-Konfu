package com.alibaba.sdk.android.httpdns.b;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public ArrayList<g> f2328a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList<g> f2329b;
    public String h;
    public String i;
    public long id;
    public String j;

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("[HostRecord] ");
        sb.append("id:");
        sb.append(this.id);
        sb.append("|");
        sb.append("host:");
        sb.append(this.h);
        sb.append("|");
        sb.append("sp:");
        sb.append(this.i);
        sb.append("|");
        sb.append("time:");
        sb.append(this.j);
        sb.append("|");
        sb.append("ips:");
        if (this.f2328a != null && this.f2328a.size() > 0) {
            Iterator<g> it = this.f2328a.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
        }
        sb.append("|");
        sb.append("ipsv6:");
        if (this.f2329b != null && this.f2329b.size() > 0) {
            Iterator<g> it2 = this.f2329b.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next());
            }
        }
        sb.append("|");
        return sb.toString();
    }
}
