package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.baidu.mobads.sdk.internal.p;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bq implements Observer {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3176a = "APKParser";
    private static final String g = "__xadsdk_downloaded__version__";
    private static final String h = "version";

    /* renamed from: b, reason: collision with root package name */
    private Context f3177b;

    /* renamed from: c, reason: collision with root package name */
    private URL f3178c;
    private String d;
    private final bf e;
    private a f;
    private SharedPreferences i;
    private SharedPreferences.OnSharedPreferenceChangeListener j;

    public interface a {
        void a(bf bfVar);

        void b(bf bfVar);
    }

    public bq(Context context, URL url, bf bfVar, a aVar) {
        this.f3178c = null;
        this.d = null;
        this.j = new br(this);
        this.f3178c = url;
        this.e = bfVar;
        a(context, aVar);
    }

    public bq(Context context, String str, bf bfVar, a aVar) {
        this.f3178c = null;
        this.d = null;
        this.j = new br(this);
        this.d = str;
        this.e = bfVar;
        a(context, aVar);
    }

    private void a(Context context, a aVar) {
        this.f3177b = context;
        this.f = aVar;
        this.i = this.f3177b.getSharedPreferences(g, 0);
        this.i.registerOnSharedPreferenceChangeListener(this.j);
    }

    public void a(String str, String str2) {
        ad adVar = new ad(this.f3177b, this.d != null ? new URL(this.d) : this.f3178c, str, str2, false);
        adVar.addObserver(this);
        adVar.a();
        SharedPreferences.Editor editorEdit = this.i.edit();
        editorEdit.putString(h, this.e.toString());
        if (av.a(this.f3177b).a() >= 9) {
            editorEdit.apply();
        } else {
            editorEdit.commit();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        p pVar = (p) observable;
        if (pVar.l() == p.a.COMPLETED) {
            this.f.a(new bf(this.e, pVar.g(), true));
        }
        if (pVar.l() == p.a.ERROR) {
            this.f.b(new bf(this.e, pVar.g(), false));
        }
    }
}
