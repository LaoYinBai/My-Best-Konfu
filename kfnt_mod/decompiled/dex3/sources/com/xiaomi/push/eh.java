package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class eh {
    private static volatile eh a;

    /* renamed from: a, reason: collision with other field name */
    private int f319a;

    /* renamed from: a, reason: collision with other field name */
    private Context f320a;

    /* renamed from: a, reason: collision with other field name */
    private el f321a;

    /* renamed from: a, reason: collision with other field name */
    private String f322a;

    /* renamed from: a, reason: collision with other field name */
    private HashMap<ej, ek> f323a = new HashMap<>();
    private String b;

    private eh(Context context) {
        this.f320a = context;
        this.f323a.put(ej.SERVICE_ACTION, new en());
        this.f323a.put(ej.SERVICE_COMPONENT, new eo());
        this.f323a.put(ej.ACTIVITY, new ef());
        this.f323a.put(ej.PROVIDER, new em());
    }

    public static eh a(Context context) {
        if (a == null) {
            synchronized (eh.class) {
                if (a == null) {
                    a = new eh(context);
                }
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ej ejVar, Context context, eg egVar) {
        this.f323a.get(ejVar).a(context, egVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m288a(Context context) {
        return com.xiaomi.push.service.ac.m580a(context, context.getPackageName());
    }

    public int a() {
        return this.f319a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public el m289a() {
        return this.f321a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m290a() {
        return this.f322a;
    }

    public void a(int i) {
        this.f319a = i;
    }

    public void a(Context context, String str, int i, String str2, String str3) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            a(i);
            ai.a(this.f320a).a(new ei(this, str, context, str2, str3));
        } else {
            ed.a(context, "" + str, DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A receive a incorrect message");
        }
    }

    public void a(ej ejVar, Context context, Intent intent, String str) {
        if (ejVar != null) {
            this.f323a.get(ejVar).a(context, intent, str);
        } else {
            ed.a(context, "null", DownloadErrorCode.ERROR_HANDLE_COMPLETE, "A receive a incorrect message with empty type");
        }
    }

    public void a(el elVar) {
        this.f321a = elVar;
    }

    public void a(String str) {
        this.f322a = str;
    }

    public void a(String str, String str2, int i, el elVar) {
        a(str);
        b(str2);
        a(i);
        a(elVar);
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }
}
