package com.bytedance.a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bp extends bz {

    /* renamed from: a, reason: collision with root package name */
    public final AccountManager f3420a;

    /* renamed from: b, reason: collision with root package name */
    public Account f3421b;

    /* renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap<String, String> f3422c = new ConcurrentHashMap<>();

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Account f3423a;

        public a(Account account) {
            this.f3423a = account;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (bp.this.f3422c != null && bp.this.f3422c.size() > 0 && bp.this.f3420a != null) {
                    for (Map.Entry<String, String> entry : bp.this.f3422c.entrySet()) {
                        if (entry != null) {
                            bp.this.f3420a.setUserData(this.f3423a, entry.getKey(), entry.getValue());
                        }
                    }
                    bp.this.f3422c.clear();
                }
            } catch (Exception e) {
                de.a("", e);
            }
        }
    }

    public bp(Context context) {
        this.f3420a = AccountManager.get(context);
    }

    @Override // com.bytedance.a.bz
    public String a(String str) {
        Account account = this.f3421b;
        if (account == null) {
            return this.f3422c.get(str);
        }
        try {
            return this.f3420a.getUserData(account, str);
        } catch (Throwable th) {
            de.a("", th);
            return null;
        }
    }

    public void a(Account account) {
        if (account != null) {
            this.f3421b = account;
            ConcurrentHashMap<String, String> concurrentHashMap = this.f3422c;
            if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
                return;
            }
            this.e.post(new a(account));
        }
    }

    @Override // com.bytedance.a.bz
    public void a(String str, String str2) {
        Account account = this.f3421b;
        if (account == null) {
            this.f3422c.put(str, str2);
            return;
        }
        if (str == null || str2 == null) {
            return;
        }
        try {
            this.f3420a.setUserData(account, str, str2);
        } catch (Throwable th) {
            de.a("", th);
        }
    }

    @Override // com.bytedance.a.bz
    public void a(String str, String[] strArr) {
        if (str == null || strArr == null) {
            return;
        }
        a(str, TextUtils.join("\n", strArr));
    }

    @Override // com.bytedance.a.bz
    public String[] b(String str) {
        String strA = a(str);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return strA.split("\n");
    }

    @Override // com.bytedance.a.bz
    @SuppressLint({"MissingPermission"})
    public void c(String str) {
        ConcurrentHashMap<String, String> concurrentHashMap = this.f3422c;
        if (concurrentHashMap != null && concurrentHashMap.containsKey(str)) {
            this.f3422c.remove(str);
        }
        try {
            if (this.f3421b != null && this.f3420a != null) {
                this.f3420a.setUserData(this.f3421b, str, null);
            }
        } catch (Exception unused) {
        }
        bz bzVar = this.d;
        if (bzVar != null) {
            bzVar.c(str);
        }
    }
}
