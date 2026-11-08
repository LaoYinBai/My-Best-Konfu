package com.ss.union.game.sdk.ad.ad_mediation.a;

import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.FilterWord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private String a;
    private String b;
    private boolean c;
    private List<a> d = new ArrayList();

    public a(FilterWord filterWord) {
        if (filterWord == null) {
            return;
        }
        this.a = filterWord.getId();
        this.b = filterWord.getName();
        this.c = filterWord.getIsSelected();
        if (filterWord.getOptions() != null) {
            Iterator it = filterWord.getOptions().iterator();
            while (it.hasNext()) {
                this.d.add(new a((FilterWord) it.next()));
            }
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public List<a> d() {
        return this.d;
    }

    public boolean e() {
        return (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.b)) ? false : true;
    }

    public boolean f() {
        return (this.d == null || this.d.isEmpty()) ? false : true;
    }
}
